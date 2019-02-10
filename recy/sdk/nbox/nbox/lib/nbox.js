
/* Auto Gen nbox-0.2.3 on 2018-04-07 by bbxyard */


/* Auto Gen nbox-0.2.3 on 2018-04-07 by bbxyard */

/**
 * @file misc.js
 * @description 小函数集合
 */

const SUB_COM_MISC = (function() {

  /**
   * @name 调试功能区
   * @param {String} prompt 
   * @param {String} msg 
   */
  function debugPrint(...args) {
    console.log(...args);
  }
  function debugPrintObj(obj) {
    console.log(JSON.stringify(obj));
  }

  /**
   * @name 字符串、数值转换区
   * @param {Number} n 
   * @param {Number} padDigit 
   */
  function toString16(n, padDigit) {
    var s = '00000000' + n.toString(16).toUpperCase();
    var r = s.slice(padDigit * (-1));
    return r;
  }

  function pad0(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
  }

  function atof2(str) {
    if (typeof str != "string") {
      return str;
    }
    var re = /([0-9]+.[0-9]{2})[0-9]*/;
    var fval = str.replace(re,"$1");
    return parseFloat(fval);
  }

  function getRandom(N) {
    return 1 + Math.floor(Math.random() * N);
  }

  function getUnixTSmsec(dt) {
    dt = dt || (new Date());
    var msec = dt.getTime();
    return msec;
  }

  function getUnixTS(dt) {
    let sec = Math.round(getUnixTSmsec(dt) / 1000);
    return sec;
  }

  function UnixTS2DateTime(stamp) {
    let dt = new Date();
    dt.setTime(stamp * 1000);
    return dt;
  }
  function UnixTS2DateTimeHRStr(stamp) {
    let dt = UnixTS2DateTime(stamp);
    return genDataTimeHRStr(dt);
  }

  function genDataTimeHRStr(date) {
    date = date || new Date();
    var year    = date.getFullYear();
    var month   = date.getMonth() + 1;
    var day     = date.getDate();
    var hour    = date.getHours();
    var minute  = date.getMinutes();
    var second  = date.getSeconds();
    return [year, month, day].map(pad0).join('-') + ' ' +
          [hour, minute, second].map(pad0).join(':');
  }

  function genDateTimeStr(sep, prefix, suffix) {
    sep    = sep || "";
    prefix = prefix || "";
    suffix = suffix || "";
    var date    = new Date();
    var year    = toString16(date.getFullYear(), 4);
    var month   = toString16(date.getMonth() + 1, 2);
    var day     = toString16(date.getDate(), 2);
    var hour    = toString16(date.getHours(), 2);
    var minute  = toString16(date.getMinutes(), 2);
    var second  = toString16(date.getSeconds(), 2);
    var msec    = toString16(date.getMilliseconds(), 2);
    var mid = [year, month + day, hour + minute, second + msec].map(pad0).join(sep);
    var out = prefix + mid + suffix;
    return out;
  }

  /**
   * @name 计算与当前时间差
   * @param {UnixTS} timestamp 
   */
  function getTimeDiff(timestamp) {
    let currentTime = (new Date()).getTime();
    let dur = currentTime - timestamp * 1000;
    if (dur < 0) return null;

    let hour = Math.floor(dur / (1000 * 60 * 60));
    let min = Math.floor(dur / (1000 * 60) % 60);
    let sec = Math.floor(dur / 1000 % 60);
    let day = Math.floor(hour / 24);
    let week = Math.floor(day / 7);
    let month = Math.floor(day / 30);
    let year = Math.floor(day / 365);

    const info = { y: year, m: month, w: week, d: day, H: hour, M: min, S: sec};
    const nArr = [year, month, week, day, hour, min, sec];
    const cnArr = ['年', '月', '周', '天', '小时', '分钟', '秒'].map(item => item + '前');
    const enArr = ['year(s)', 'month(s)', 'week(s)', 'day(s)', 'hour(s)', 'minute(s)', 'second(s)'].map((item) => item + " ago" );
    // 找到第一个不为0的即可.
    let diff = {info: info, cn: '刚刚', en: 'just'};
    for (let i = 0; i < nArr.length; ++i) {
      if (nArr[i] > 0) {
        diff.cn = nArr[i] + cnArr[i];
        diff.en = nArr[i] + ' ' + enArr[i];
        break;
      }
    }
    return diff;
  }

  function getTimeDiffCN(timestamp) {
    const diff = getTimeDiff(timestamp);
    const newDiff = { info: diff.info, desc: diff.cn };
    return newDiff;
  }

  function getTimeDiffEN(timestamp) {
    const diff = getTimeDiff(timestamp);
    const newDiff = { info: diff.info, desc: diff.en };
    return newDiff;
  }

  /**
   * @name 获得文件扩展名
   * @param {String} pathname 路径
   */
  function pathName2ExtName(pathname) {
    var extension = /\.([^.]*)$/.exec(pathname);
    if (extension) {
      extension = extension[1].toLowerCase();
    }
    return extension;
  }

  /**
   * @name 周期性任务
   * @param cb
   * @param interval
   * @constructor
   */
  function CycleTask() {
    this.start = function(cb, interval) {
      this.timer     = null;
      this.interval  = interval || 1000;
      this.timer = setInterval(cb, this.interval);
    };
    this.stop = function() {
      if (this.timer) {
        clearInterval(this.timer);
        this.timer = null;
      }
    };
  }

  /**
   * 对外接口
   * @type {Object}
   */
  const out = {
    // debug调试
    debugPrint,
    debugPrintObj,

    // 数字、串处理
    toString16,
    pad0,
    atof2,
    getRandom,

    // 时间日期
    getUnixTSmsec,
    getUnixTS,
    getTimeDiff,
    getTimeDiffCN,
    getTimeDiffEN,
    UnixTS2DateTime,
    UnixTS2DateTimeHRStr,
    genDataTimeHRStr,
    genDateTimeStr,

    // 文件路径
    pathName2ExtName,

    // 任务相关
    CycleTask
  };

  return out;

})();

// console.log('misc: ', SUB_COM_MISC);

/**
 * @file validator.js
 * @description 数字、字符串校验相关
 */

const SUB_COM_VALIDATOR = (function() {

  class Validator {
    /**
     * 验证必填元素
     */
    static required(value) {
      if (typeof value === 'number') {
        value = value.toString()
      } else if (typeof value === 'boolean') {
        return !0
      }

      return value && value.length > 0
    }

    /**
     * 重复验证
     */
    static noDuplicate(values) {
      for (let i = 0; i < values.length; i++) {
        for (let j = 0; j < values.length; j++) {
          if (values[i] == values[j] && i != j) {
            return false;
          }
        }
      }
      return true;
    }
    /**
     * 验证电子邮箱格式
     */
    static email(value) {
      return this.optional(value) || /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test(value)
    }
    /**
     * 验证手机格式
     */
    static tel(value) {
      return this.optional(value) || /^1[34578]\d{9}$/.test(value)
    }
    /**
     * 验证URL格式
     */
    static url(value) {
      return this.optional(value) || /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(value)
    }
    /**
     * 验证日期格式
     */
    static date(value) {
      return this.optional(value) || !/Invalid|NaN/.test(new Date(value).toString())
    }
    /**
     * 验证ISO类型的日期格式
     */
    static dateISO(value) {
      return this.optional(value) || /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(value)
    }
    /**
     * 验证十进制数字
     */
    static number(value) {
      return this.optional(value) || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value)
    }
    /**
     * 验证整数
     */
    static digits(value) {
      return this.optional(value) || /^\d+$/.test(value)
    }
    /**
     * 验证身份证号码
     */
    static idcard(value) {
      return this.optional(value) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(value)
    }
    /**
     * 验证两个输入框的内容是否相同
     */
    static equalTo(value, param) {
      return this.optional(value) || value === that.scope.detail.value[param]
    }
    /**
     * 验证是否包含某个值
     */
    static contains(value, param) {
      return this.optional(value) || value.indexOf(param) >= 0
    }
    /**
     * 验证最小长度
     */
    static minlength(value, param) {
      return this.optional(value) || value.length >= param
    }
    /**
     * 验证最大长度
     */
    static maxlength(value, param) {
      return this.optional(value) || value.length <= param
    }
    /**
     * 验证一个长度范围[min, max]
     */
    static rangelength(value, param) {
      return this.optional(value) || (value.length >= param[0] && value.length <= param[1])
    }
    /**
     * 验证最小值
     */
    static min(value, param) {
      return this.optional(value) || Number(value) >= Number(param);
    }
    /**
     * 验证最大值
     */
    static max(value, param) {
      return this.optional(value) || Number(value) <= Number(param);
    }

    /**
     * 验证时间
     */
    static after(value, param) {
      return this.optional(value) || value >= param;
    }
    /**
     * 验证时间
     */
    static before(value, param) {
      return this.optional(value) || value <= param;
    }

    /**
     * 验证一个值范围[min, max]
     */
    static range(value, param) {
      return this.optional(value) || (value >= param[0] && value <= param[1])
    }
    /**
     * 判断输入值是否为空
     */
    static optional(value) {
      return !this.required(value) && 'dependency-mismatch'
    }
    /**
     * 判断升级条件
     */
    static upgradeFee(value, param) {
      return (param - value) > 0 || param == null;
    }
  }

  const out = { Validator };

  return out;

})();

/**
 * @file strfmt
 * @description 字符串格式化
 */

const SUB_COM_STRFMT = (function() {

  // import { sprintf, vsprintf } from 'sprintf-js';
  const spf = require('sprintf-js');
  const { sprintf, vsprintf } = spf;
  const { genDataTimeHRStr } = SUB_COM_MISC;

  /**
   * @name tsprintf
   * @description time-sprintf
   * @param {Object} obj {level: info, errno, subject, prompt}
   */
  function tsprintf(obj) {
    let stime = genDataTimeHRStr();
    let combFmt = '%s';
    let combArgs = [stime];
    if (obj.level) {
      combFmt += ' | %s';
      combArgs.push(obj.level.toUpperCase());
    }
    if (obj.subject) {
      combFmt += ' | %s';
      combArgs.push(obj.subject);
    }
    if (obj.errno !== undefined) {
      combFmt += (obj.errno === 0) ? ' | SUCC' : ` | FAIL=${obj.errno}`;
    }
    if (obj.prompt) {
      combFmt += ' | ' + obj.prompt;
      combArgs = combArgs.concat(obj.args);
    }
    let s = vsprintf(combFmt, combArgs);
    return s;
  }
  function tsprintf2(prompt, ...args) {
    return tsprintf({prompt, args});
  }
  function tsprintf3(level, prompt, ...args) {
    return tsprintf({level, prompt, args});
  }
  function tsprintf4(subject, level, prompt, ...args) {
    return tsprintf({subject, level, prompt, args});
  }
  function tsprintf5(errno, subject, level, prompt, ...args) {
    return tsprintf({errno, subject, level, prompt, args});
  }

  /**
   * @name jtsprintf
   * @description json-time-sprintf
   * @param {Object} obj {level: info, errno, subject, prompt}
   */
  function jtsprintf(obj) {
    let stime = genDataTimeHRStr();
    let res = {stime: stime};
    if (obj.level) {
      res.level = obj.level.toUpperCase();
    }
    if (obj.subject) {
      res.subject = obj.subject;
    }
    if (obj.errno !== undefined) {
      res.errno = obj.errno;
      if (obj.errmsg !== undefined) {
        res.errmsg = obj.errmsg;
      } else {
        res.errmsg = (obj.errno === 0) ? 'SUCC' : `FAIL=${obj.errno}`;
      }
    }
    if (obj.prompt) {
      res.content = vsprintf(obj.prompt, obj.args);
    }
    return res;
  }
  function jtsprintf2(prompt, ...args) {
    return jtsprintf({prompt, args});
  }
  function jtsprintf3(level, prompt, ...args) {
    return jtsprintf({level, prompt, args});
  }
  function jtsprintf4(subject, level, prompt, ...args) {
    return jtsprintf({subject, level, prompt, args});
  }
  function jtsprintf5(errno, subject, level, prompt, ...args) {
    return jtsprintf({errno, subject, level, prompt, args});
  }

  /**
   * @description 对外接口
   */
  const out = {
    sprintf,

    // 带时间日期plain字符串版本
    tsprintf,
    tsprintf2,
    tsprintf3,
    tsprintf4,
    tsprintf5,

    // 带时间日期json对象版本.
    jtsprintf,
    jtsprintf2,
    jtsprintf3,
    jtsprintf4,
    jtsprintf5
  };

  return out;
})();

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
    }

    open(option = {}, freezed = true) {
      // 更新选项
      option.interval = option.interval || 500;
      this.option = Object.assign({}, super.option, this.option, option);
    
      // 子类自己open
      let that = this;
      this.task = new CycleTask();
      this.task.start( function() {
        let item = null;
        while ((item = that.items.pop()) != null) {
          that.doWriteOneItem(item);
        }
      }, this.option.interval);
    }
    close() {
      this.task.stop();
    }

    writeOneItem(item) {
      this.items.push(item);
    }

    handle() {

    }

    // 派生类，可以继续模版方法模式.
    doWriteOneItem(item) {
      debugPrint(item);
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

/**
 * @file com.js
 * @description 公共小工具集
 */

/**
 * @name SUB_COM_OVERRIDE
 * @description 私有override接口
 */
const SUB_COM_OVERRIDE = {
  debugLog: function(...args) {
    console.log(...args);
  },
  traceLog: function(...args) {
    console.log(...args);
  }
};

/**
 * @name SUB_COM
 * @description COM合一
 */
const SUB_COM = Object.assign(
  {},
  SUB_COM_MISC,
  SUB_COM_STRFMT,
  SUB_COM_LOGGER,
  SUB_COM_VALIDATOR,
  SUB_COM_OVERRIDE
);

module.exports.com = SUB_COM;

/* Copyright (C) 2013-2018 bbxyard. All rights reserved */


/* Auto Gen nbox-0.2.3 on 2018-04-07 by bbxyard */

/**
 * @file wxapp
 * @description 微信相关封装
 */

const SUB_WX_MISC = (function() {

  // import wepy from 'wepy';
  const wepy = require('wepy');

  /**
   * @name 用户交互
   * @param {String} title 
   * @param {String} msg 
   * @param {Boolean} showCancel 
   * @param {Function} cb 
   */
  function msgBox(title, msg, showCancel, cb) {
    showCancel = showCancel || false;
    cb = cb || null;
    wepy.showModal({
      title: title,
      content: msg,
      showCancel: showCancel,
      success: cb
    });
  }
  
  function showToast(title, timeout) {
    if (null === timeout) timeout = 3000;
    setTimeout(function(){
      wepy.showToast({
        title: title,
        //duration: timeout,
        mask: true
      })
    }, timeout);
  
  }
  
  function showLoading(title, timeout) {
    if (null === timeout) timeout = 1000;
    if (wepy.showLoading) {
      wepy.showNavigationBarLoading();
      wepy.showLoading({ title: title, mask: true });
      setTimeout( function() { hideLoading("TIMEOUT") }, timeout);
    }
  }
  
  function showSuccToast(title, dur) {
    if (null === dur) dur = 3000;
    wepy.showToast({
      title: title,
      icon: 'success',
      duration: dur,
      mask: true
    });
  }
  
  function showLoadingToast(title, dur) {
    if (null === dur) dur = 3000;
    wepy.showToast({
      title: title,
      icon: 'loading',
      duration: dur,
      mask: true
    })
  }
  
  function hideLoading(reason) {
    wepy.hideLoading();
    wepy.hideNavigationBarLoading();
  }
  
  /**
   * redirect Url
   * @param  {[type]} url [description]
   * @return {[type]}     [description]
   */
  function redirect(url) {
    // 判断页面是否需要登录
    const needLogin = false;
    if (needLogin) {
      wepy.redirectTo({
        url: '/pages/auth/login/login'
      });
      return false;
    } else {
      wepy.redirectTo({url: url});
    }
  }
  
  /**
   * [showErrorToast description]
   * @param  {[type]} msg [description]
   * @return {[type]}     [description]
   */
  function showErrorToast(msg) {
    wepy.showToast({
      title: msg,
      image: '/static/images/icon_error.png'
    });
  }
  
  async function openWXSetting() {
    const res = await wepy.openSetting();
    console.log('[System][wepy.openSetting]: ', res);
    return res;
  }
  
  async function getWXSetting() {
    const res = await wepy.getSetting();
    console.log('[System][wepy.getSetting]: ', res);
    return res;
  }
  
  /**
   * 对外接口
   * @type {Object}
   */
  const out = {
    // 用户交互
    msgBox,
    showToast,
    showLoading,
    showSuccToast,
    showLoadingToast,
    hideLoading,
  
    // 微信逻辑
    redirect,
    showErrorToast,
    openWXSetting,
    getWXSetting,
    checkSession: wepy.checkSession
  };

  return out;

})();

/**
 * @file wx.js
 * @description 微信相关操作封装
 */

const SUB_WX = Object.assign(
  {},
  SUB_WX_MISC
);

module.exports.wx = SUB_WX;

/* Copyright (C) 2013-2018 bbxyard. All rights reserved */


/* Auto Gen nbox-0.2.3 on 2018-04-07 by bbxyard */

/**
 * @file foo.js
 * @description 接口导出规范样品文件 - foo
 */
const SUB_FOOBAR_FOO = (function() {

  function add(x, y) {
    return x + y;
  }

  function mult(x, y) {
    return x * y;
  }

  const foo = {
    addF: add,
    multF: mult
  };

  return foo;

})();

/**
 * @file bar.js
 * @description 接口导出规范样品文件 - bar
 */
const SUB_FOOBAR_BAR = (function() {

  function add(x, y) {
    return x + y;
  }

  function mult(x, y) {
    return x * y;
  }

  const out = {
    add,
    mult
  };

  return out;

})();

const SUB_FOOBAR = Object.assign(
  {},
  SUB_FOOBAR_FOO,
  SUB_FOOBAR_BAR
);

module.exports.foobar = SUB_FOOBAR;

/* Copyright (C) 2013-2018 bbxyard. All rights reserved */

/**
 * @file index.js
 * @description nbox 统一对外接口文件
 */

function help() {
  console.log('ALL: ', module.exports);
}

const NBOX = Object.assign(
  {},
  SUB_COM,
  SUB_WX,
  SUB_FOOBAR,
  help
);

module.exports.nbox = NBOX;
module.exports.help = help;

/* Copyright (C) 2013-2018 bbxyard. All rights reserved */
