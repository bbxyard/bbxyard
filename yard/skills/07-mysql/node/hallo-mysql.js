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

  // 查询
  query() {
    const QUERY_SQL_LIST = [
      'show databases',
      'select * from test.demo_user',
      'select name,age from test.demo_user where id > 2',
    ];
    for (const sql of QUERY_SQL_LIST) {
      this.db.query(sql, (err, result)=>{
        if (err) {
          console.log('err: ', err);
          return;
        }
        console.log('RUN: ', sql);
        console.log(result);
        console.log('done\n');
      });
    }
  }

}


// Run
const demo = new DBDemo();
demo.query();
demo.close();
