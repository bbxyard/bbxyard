package com.bbxyard.hmongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class MongoDemo {
	
	public static void add() {

	    //先查询所有数据
	    // queryAll();
		DBCollection users = getCollection("127.0.0.1", 27017, "bbx", "users"); 
	    System.out.println("count: " + users.count());

	    DBObject user = new BasicDBObject();
	    user.put("userid", "boxu2");
	    user.put("passwd", "123123");
	    user.put("age", 30);
	    users.save(user);
	  
	    //扩展字段，随意添加字段，不影响现有数据
	    user.put("sex", "男");
	    System.out.println(users.save(user).getN());
	    
	    //添加多条数据，传递Array对象
	    user = new BasicDBObject("name", "Brian");
	    System.out.println(users.insert(user).getN());

	    //添加List集合
	    user = new BasicDBObject("name", "Brian4");
	    List<DBObject> list = new ArrayList<DBObject>();
	    list.add(user);

	    DBObject user2 = new BasicDBObject("name", "lucy2");
	    user.put("age", 22);
	    list.add(user2);

	    //添加List集合
	    System.out.println(users.insert(list).getN());	    

	    //查询下数据，看看是否添加成功
	    System.out.println("count: " + users.count());

	    // queryAll();

	}
	
	static DBCollection getCollection(String host, int port, String dbname, String cname) {
	    MongoClient mg = new MongoClient(host, port);
	
	    //查询所有的Database
	    for (String name : mg.getDatabaseNames()) {
	        System.out.println("dbName: " + name);
	    }
	    
	    DB db = mg.getDB(dbname);
	    //查询所有的聚集集合
	    for (String name : db.getCollectionNames()) {
	        System.out.println("collectionName: " + name);
	    }
	    
	    DBCollection collection = db.getCollection(cname);  
	    return collection;
	}
	
	static void test() {
        DBCollection users = getCollection("127.0.0.1", 27017, "bbx", "users"); 
        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            System.out.println(cur.next());
        }

        System.out.println(cur.count());
        System.out.println(cur.getCursorId());
        System.out.println(JSON.serialize(cur));
	}
	
	public static void main(String[] args) {
		test();
		add();
		System.out.println("hello");
	}
}
