package main

import (
	"github.com/astaxie/beego/migration"
)

// DO NOT MODIFY
type User_20190902_133215 struct {
	migration.Migration
}

// DO NOT MODIFY
func init() {
	m := &User_20190902_133215{}
	m.Created = "20190902_133215"

	migration.Register("User_20190902_133215", m)
}

// Run the migrations
func (m *User_20190902_133215) Up() {
	// use m.SQL("CREATE TABLE ...") to make schema update
	m.SQL("CREATE TABLE user(`id` int(11) DEFAULT NULL,`name` varchar(128) NOT NULL,`gender` int(11) DEFAULT NULL,`age` int(11) DEFAULT NULL)")
}

// Reverse the migrations
func (m *User_20190902_133215) Down() {
	// use m.SQL("DROP TABLE ...") to reverse schema update
	m.SQL("DROP TABLE `user`")
}
