package conf

const DriverName = "mysql"

type DbConf struct {
	Host   string
	Port   int
	User   string
	Pwd    string
	DbName string
}

var MasterDbConfig DbConf = DbConf{
	Host:   "127.0.0.1",
	Port:   53306,
	User:   "root",
	Pwd:    "passwd",
	DbName: "go_iris_superstar",
}

var SlaveDbConfig DbConf = DbConf{
	Host:   "127.0.0.1",
	Port:   53306,
	User:   "root",
	Pwd:    "passwd",
	DbName: "go_iris_superstar",
}
