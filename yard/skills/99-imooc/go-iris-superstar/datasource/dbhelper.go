package datasource

import (
	"fmt"
	"go-iris-superstar/conf"
	"log"
	"sync"

	"github.com/go-xorm/xorm"
)

var (
	masterEngine *xorm.Engine
	slaveEngine  *xorm.Engine
	lock         sync.Mutex
)

// 主库，单例
func InstanceMaster() *xorm.Engine {
	if masterEngine != nil {
		return masterEngine
	}
	lock.Lock()
	defer lock.Unlock()

	if masterEngine != nil {
		return masterEngine
	}
	c := conf.MasterDbConfig
	driverSource := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8",
		c.User, c.Pwd, c.Host, c.Port, c.DbName)
	engine, err := xorm.NewEngine(conf.DriverName, driverSource)
	if err != nil {
		log.Fatal("dbhelper.DbInstanceMaster,", err)
		return nil
	}

	// Debug模式，打印全部的SQL语句，帮助对比，看ORM与SQL执行的对照关系
	engine.ShowSQL(false)
	engine.SetTZLocation(conf.SysTimeLocation)

	// 性能优化的时候才考虑，加上本机的SQL缓存
	cacher := xorm.NewLRUCacher(xorm.NewMemoryStore(), 1000)
	engine.SetDefaultCacher(cacher)

	masterEngine = engine
	return engine
}

// 从库，单例
func InstanceSlave() *xorm.Engine {
	if slaveEngine != nil {
		return slaveEngine
	}
	lock.Lock()
	defer lock.Unlock()

	if slaveEngine != nil {
		return slaveEngine
	}
	c := conf.SlaveDbConfig
	driverSource := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8",
		c.User, c.Pwd, c.Host, c.Port, c.DbName)
	engine, err := xorm.NewEngine(conf.DriverName, driverSource)
	if err != nil {
		log.Fatal("dbhelper.DbInstanceSlave,", err)
		return nil
	}

	// Debug模式，打印全部的SQL语句，帮助对比，看ORM与SQL执行的对照关系
	engine.ShowSQL(false)
	engine.SetTZLocation(conf.SysTimeLocation)

	// 性能优化的时候才考虑，加上本机的SQL缓存
	cacher := xorm.NewLRUCacher(xorm.NewMemoryStore(), 1000)
	engine.SetDefaultCacher(cacher)

	slaveEngine = engine
	return engine
}
