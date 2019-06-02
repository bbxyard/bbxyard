const mysql = require('mysql');


class DBDemo {
  constructor() {
    this.db = mysql.createConnection({
      host     : '127.0.0.1',
      user     : 'test',
      password : '',
      port     : '3306',
      database : 'test',
    });
    this.db.connect();
  }

  close() {
    this.db.end();
  }

  handleError(err) {
    console.error(err);
    throw err;
  }

  // 增
  add() {
    const ADD_SQL_PAIR_LIST = [
      // ['INSERT INTO demo_user(id, name, age) VALUES(128, ?, ?)', ['0x80', 128]],
      ['insert into demo_user(name, age) values(?, ?)', ['boxu', 34]]
    ];
    console.log('-----INSERT----');
    for (const item of ADD_SQL_PAIR_LIST) {
      this.db.query(item[0], item[1], (err, result) => {
        if (err) this.handleError(err);
        console.log('[ADD] RUN: ', item[0], item[1]);
        console.log('INSERT ID is: ', result);
        console.log('done\n');
      });
    }
  }

  // 改
  update() {
    const UP_SQL_PAIR_LIST = [
      ['update demo_user set name=?,age=? where id=?', ['char-bound', 64, 128]]
    ];
    console.log('-----UPDATE----');
    for (const item of UP_SQL_PAIR_LIST) {
      this.db.query(item[0], item[1], (err, result) => {
        if (err) this.handleError(err);
        console.log('[UPDATE] RUN: ', item[0], item[1]);
        console.log('UPDATE 影响条目数: ', result);
        console.log('done\n');
      });
    }
  }

  // 删除
  del() {
    const DEL_SQL_PAIR_LIST = [
      ['delete from demo_user where id=?', [128]],
      ['delete from demo_user where name=?', ['boxu']],
    ];
    console.log('-----DELETE----');
    for (const item of DEL_SQL_PAIR_LIST) {
      this.db.query(item[0], item[1], (err, result) => {
        if (err) this.handleError(err);
        console.log('[DELETE] RUN: ', item[0], item[1]);
        console.log('DELETE 影响条目数: ', result);
        console.log('done\n');
      });
    }
  }

  // 查询
  query() {
    const QUERY_SQL_LIST = [
      'show databases',
      'select * from test.demo_user',
      'select name,age from test.demo_user where id > 2',
    ];
    for (const sql of QUERY_SQL_LIST) {
      this.db.query(sql, (err, result) => {
        if (err) this.handleError(err);
        console.log('[QUERY] RUN: ', sql);
        console.log(result);
        console.log('done\n');
      });
    }
  }
}


// Run
const demo = new DBDemo();
demo.add();
demo.update();
demo.del();
demo.query();
demo.close();
