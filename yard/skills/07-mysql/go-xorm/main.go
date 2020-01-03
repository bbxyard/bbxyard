package main

import (
	"fmt"
	"log"
	"time"

	_ "github.com/go-sql-driver/mysql"
	"github.com/go-xorm/xorm"
)

const DriverName = "mysql"
const MasterDataSourceName = "root:passwd@tcp(127.0.0.1:53306)/smoke?charset=utf8"

type GoXormUser struct {
	Id   int `xorm:"not null pk autoincr"`
	Name string
	Age  int
	CAt  int
	MAt  int
}

var engine *xorm.Engine

func main() {
	engine = newEngin()

	execSQL()

	ormDelete()
	ormInsert()
	queryByRawSQL()
	ormGet()
	ormGetFields()
	ormCount()

	ormUpdate()
	ormGet()

	ormOmitUpdate()
	ormGet()

	// 查询多行
	ormFindRows()

	ormMustColsUpdate()
	queryByRawSQL()
}

// 连接到数据库
func newEngin() *xorm.Engine {
	engine, err := xorm.NewEngine(DriverName, MasterDataSourceName)
	if err != nil {
		log.Fatal("newEngin", err)
		return nil
	}
	// Debug模式，打印全部的SQL语句，帮助对比，看ORM与SQL执行的对照关系
	engine.ShowSQL(true)
	return engine
}

// 通过execute方法执行更新
func execSQL() {
	sql := `INSERT INTO go_xorm_user values(NULL, 'da-vinci', 1452, 0, 0)`
	affected, err := engine.Exec(sql)
	if err != nil {
		log.Fatal("execute error", err)
	} else {
		id, _ := affected.LastInsertId()
		rows, _ := affected.RowsAffected()
		fmt.Println("execute id=", id, ", rows=", rows)
	}
}

// 通过query方法查询
func queryByRawSQL() {
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
		for i, item := range results {
			fmt.Printf("%d = %v\n", i, item)
		}
	}
}

// 删除
func ormDelete() {
	user := &GoXormUser{
		Id: 4487,
	}
	affectedCnt, err := engine.Delete(user)
	if err != nil {
		log.Fatal("ormDelete error", err)
	} else {
		fmt.Println("affected row count is:", affectedCnt)
	}
}

// 根据models的结构映射数据
func ormInsert() {
	user := &GoXormUser{
		Id:   4487,
		Name: "74you",
		Age:  34,
	}
	id, err := engine.Insert(user)
	if err != nil {
		log.Fatal("ormInser error", err)
	} else {
		fmt.Println("ormInsert id =", id)
		fmt.Printf("%v\n", *user)
	}
}

// 获取数据
func ormGet() {
	user := &GoXormUser{Id: 4487}
	ok, err := engine.Get(user)
	if ok {
		fmt.Printf("Got User: %v\n", *user)
	} else if err != nil {
		log.Fatal("ormGet error", err)
	} else {
		fmt.Println("ormGet empy id=", user.Id)
	}
}

// 获取制定字段
func ormGetFields() {
	user := &GoXormUser{Id: 4487}
	// ok, err := engine.Cols("name", "age").Get(user)
	ok, err := engine.Cols("age").Get(user)
	if ok {
		fmt.Printf("%v\n", user)
	} else if err != nil {
		log.Fatal("ormGetFields error", err)
	} else {
		fmt.Println("ormGetFields empty id=", user.Id)
	}
}

// 统计
func ormCount() {
	//count, err := engine.Count(&UserInfo{})
	//count, err := engine.Where("name=?", "74you").Count(&UserInfo{})
	count, err := engine.Count(&GoXormUser{Name: "74you"})
	if err != nil {
		log.Fatal("ormCount error", err)
	} else {
		fmt.Printf("count=%v\n", count)
	}
}

// 更新数据
func ormUpdate() {
	// 全部更新
	user := &GoXormUser{MAt: 2020}
	ok, err := engine.Update(user)
	println("update all:", ok, err)
	// by ID
	user2 := &GoXormUser{Name: "魔鬼撒旦"}
	ok, err = engine.ID(4487).Update(user2)
	println("update by id:", ok, err)
}

// 排除某个字段
func ormOmitUpdate() {
	info := &GoXormUser{Id: 4487}
	ok, _ := engine.Get(info)
	if ok {
		if info.MAt > 0 {
			ok, _ := engine.ID(info.Id).Omit("c_at").
				Update(&GoXormUser{CAt: 0, MAt: int(time.Now().Unix())})
			fmt.Printf("ormOmitUpdate, rows=%d, m_at=%d\n", ok, 0)
		} else {
			ok, _ := engine.ID(info.Id).Omit("c_at").
				Update(&GoXormUser{CAt: 1, MAt: int(time.Now().Unix())})
			fmt.Printf("ormOmitUpdate, rows=%d, c_at=%d\n", ok, 0)
		}
	}
}

// 查找多行数据
func ormFindRows() {
	list := make([]GoXormUser, 0)
	err := engine.Cols("name", "id").
		Where("age>?", 3000).
		Limit(10).
		Asc("id", "c_at").Find(&list)
	if err != nil {
		log.Fatal("ormFindRows error", err)
	} else {
		fmt.Printf("%v\n", list)
	}
}

// 字段为空也可以更新（0, 空字符串等）
func ormMustColsUpdate() {
	info := &GoXormUser{Id: 1}
	ok, _ := engine.Get(info)
	if ok {
		if info.CAt > 0 {
			ok, _ := engine.ID(info.Id).
				MustCols("c_at").
				Update(&GoXormUser{CAt: 0, MAt: int(time.Now().Unix())})
			fmt.Printf("ormMustColsUpdate, rows=%d, "+
				"c_at=%d\n", ok, 0)
		} else {
			ok, _ := engine.ID(info.Id).
				MustCols("c_at").
				Update(&GoXormUser{CAt: 1, MAt: int(time.Now().Unix())})
			fmt.Printf("ormMustColsUpdate, rows=%d, "+
				"c_at=%d\n", ok, 0)
		}
	}
}
