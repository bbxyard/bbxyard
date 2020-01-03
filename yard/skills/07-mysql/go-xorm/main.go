package main

import (
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
	"github.com/go-xorm/xorm"
)

const DriverName = "mysql"
const MasterDataSourceName = "root:passwd@tcp(127.0.0.1:53306)/smoke?charset=utf8"

type GoXormUser struct {
	Id         int `xorm:"not null pk autoincr"`
	Name       string
	Age        int
	SysCreated int
	SysUpdated int
}

var engine *xorm.Engine

func main() {
	engine = newEngin()

	//execute()
	//ormInsert()
	query()
	//ormGet()
	//ormGetCols()
	//ormCount()
	// ormFindRows()
	//ormUpdate()
	//ormOmitUpdate()
	//ormMustColsUpdate()
}

// 连接到数据库
func newEngin() *xorm.Engine {
	engine, err := xorm.NewEngine(DriverName, MasterDataSourceName)
	if err != nil {
		log.Fatal(newEngin, err)
		return nil
	}
	// Debug模式，打印全部的SQL语句，帮助对比，看ORM与SQL执行的对照关系
	engine.ShowSQL(true)
	return engine
}

// 通过query方法查询
func query() {
	sql := "SELECT * FROM go_xorm_user"
	//results, err := engine.Query(sql)
	//results, err := engine.QueryInterface(sql)
	results, err := engine.QueryString(sql)
	if err != nil {
		log.Fatal("query", sql, err)
		return
	}
	total := len(results)
	if total == 0 {
		fmt.Println("没有任何数据", sql)
	} else {
		for i, data := range results {
			fmt.Printf("%d = %v\n", i, data)
		}
	}
}
