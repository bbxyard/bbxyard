/**
 * @name logger
 * @description 基本Logger
 */

const SUB_COM_LOGGER = (function() {

  const strfmt = SUB_COM_STRFMT;
  const { debugPrint, CycleTask } = SUB_COM_MISC;

  const LogLevelNameTable = [
    { id: 0, name: 'none', alias: 'NUL' },
    { id: 1, name: 'crit', alias: 'CRT' },
    { id: 2, name: 'error', alias: 'ERR' },
    { id: 4, name: 'warning', alias: 'WRN' },
    { id: 8, name: 'info', alias: 'INF' },
    { id: 16, name: 'verbose', alias: 'VRB' },
    { id: 32, name: 'debug', alias: 'DBG' },
    { id: 64, name: 'perf', alias: 'PRF' },
    { id: 128, name: 'trace', alias: 'TRC' }
  ];

  /**
   * @name 日志等级辅助类
   */
  class LogLevelHelper {
    constructor(maxCombLevel) {
      this.maxCombLevel = maxCombLevel; // 仅输出这个等级以下的日志.
    }
    canOutput(id) {
      return id <= this.maxCombLevel;
    }
    setOutputLevel(combLevel) {
      this.maxCombLevel = combLevel;
    }

    getStdNameById(id) {
      const index = this.getIndex(id);
      const res = LogLevelNameTable[index];
      return res;
    }
    getStdNameByName(name) {
      return this.verify(name).alias;
    }
    getId(name) {
      return this.verify(name).id;
    }

    /**
     * @description 内部方法 
     */
    getIndex(id) {
      let index = 0;
      for (; 2 << index <= id; ++index) {}
      return (2 << index === id && index < LogLevelNameTable.length) ? index : 0;
    }
    verify(nameOrId) {
      let res = { index: LogLevelNameTable[0].id, canOutput: false, name: '' };
      if (typeof nameOrId === 'number') {
        const id = nameOrId;
        const index = this.getIndex(id);
        const item = LogLevelNameTable[index];
        res = { index, item, canOutput: this.canOutput(item.id), name: item.alias };
      } else if (typeof nameOrId === 'string') {
        const name = nameOrId;
        const upName = name.toUpperCase();
        const lowName = name.toLowerCase();
        for (let i = 0; i < LogLevelNameTable.length; ++i) {
          const item = LogLevelNameTable[i];
          if (lowName === item.name || upName === item.alias) {
            res = { index: i, item: item, canOutput: this.canOutput(item.id), name: item.alias };
            break;
          }
        }
      }
      return res;
    }
  };

  /**
   * @class LoggerBase
   * @description 日志基类
   */
  class LoggerBase {
    constructor() {
      this.option = { fmt: 'json', level: 255 };
      this.history = [];  // 环形Buffer todo
      this.levelHelper = new LogLevelHelper(this.option.level);
    }

    write2(prompt, ...args) {
      return this.write({prompt, args});
    }
    write3(level, prompt, ...args) {
      return this.write({level, prompt, args});
    }
    write4(subject, level, prompt, ...args) {
      return this.write4({subject, level, prompt, args});
    }
    write5(errno, subject, level, prompt, ...args) {
      return this.write({errno, subject, level, prompt, args});
    }

    write(combObj) {
      // 对Level进行格式化规整.
      combObj.canOutput = true;
      if (combObj.level !== undefined) {
        const res = this.levelHelper.verify(combObj.level);
        combObj.canOutput = res.canOutput;
        combObj.level = res.name;
      }
      const fn = (this.option.fmt === 'json') ? strfmt.jtsprintf : strfmt.tsprintf;
      const item = fn(combObj);
      this.writeOneItem(item);
      // this.history.push(item);
    }

    // 写日志-子类可重写此方法-模板方法模式.
    writeOneItem(item) {

    }
  }

  /**
   * @name SyncLogger
   * @description 同步写入日志类
   */
  class SyncLogger extends LoggerBase {
    constructor() {
      super();
      this.file = null;
    }
    writeOneItem(item) {
      console.log(item);
    }
  }

  /**
   * @class AsyncLogger
   * @description 异步写入日志类
   */
  class AsyncLogger extends LoggerBase {
    constructor() {
      super();
      this.task = null;
      this.items = [];
      this.reqItems = [];
      this.retryCnt = 0;
      this.status = 'done';
    }

    open(option = {}, freezed = true) {
      // 如果已有，要先关闭.
      this.close();

      // 更新选项
      option.interval = option.interval || 2000;
      option.maxRetryCnt = option.maxRetryCnt || 5;
      this.option = Object.assign({}, super.option, this.option, option);
    
      // 子类自己open
      const that = this;
      this.task = new CycleTask();
      this.task.start( function() {
        // 有任务还没有结束，则直接返回.
        if (that.status !== 'done') {
          return;
        }
        // 转移数据到请求状态.
        that.transferItems2Req();
        if (that.reqItems.length === 0) {
          return;
        }
        // 置为运行状态.
        that.status = 'runnig';
        that.doWriteAllItem(that.reqItems).then(res => {
          that.resetRetryCount();
          that.clearReq();
          that.status = 'done';
        }).catch(err => {
          console.log('write error. try later');
          // 连错N次，则丢弃.
          if (that.verifyMaxRetry()) {
            that.clearReq();
            console.log('write error and reach max try. Discard. items-cnts: %d', that.reqItems.length);
          }
          that.status = 'done';
        });
      }, this.option.interval);
    }
    close() {
      if (this.task) {
        this.task.stop();
        this.task = null;
      }
    }

    writeOneItem(item) {
      this.items.push(item);
    }

    resetRetryCount() {
      this.retryCnt = 0;
    }
    verifyMaxRetry() {
      const b = ( ++ this.retryCnt >= this.option.maxRetryCnt);
      if (b) this.resetRetryCount();
      return b;
    }

    // 开始写: 将数据追加到reqItems中
    // 成功写: 将reqItems清空
    transferItems2Req() {
      if (this.items.length > 0) {
        this.reqItems = this.reqItems.concat(this.items);
        this.items = [];
      } 
    }
    clearReq() {
      this.reqItems = [];
    }

    // 派生类，可以继续模版方法模式.
    async doWriteAllItem(items) {
      debugPrint(items);
      return Promise.resolve('done'); 
    }
  }

  let logger = new AsyncLogger();

  /**
   * @description 外部接口.
   */
  const out = {
    SyncLogger,
    AsyncLogger,
    logger
  };

  return out;

})();
