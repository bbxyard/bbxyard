package com.bbxyard.util.impl;

import java.util.HashMap;

import com.bbxyard.util.IStructLog;
import com.bbxyard.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class StructMongoLog implements IStructLog {
	
	class SLNodeImpl implements ISLNode {
		public SLNodeImpl() {
			obj = new BasicDBObject();
		}
		public DBObject getDBObject() {
			return obj;
		}

		@Override
		public void put(String key, Object value) {
			obj.put(key, value);
		}

		@Override
		public String toString() {
			String sout = JSON.serialize(obj);
			return sout;
		}



		private DBObject obj;
	}

	/**
	 * @param config: mongodb://%s:%s@%s:%d/%s", "user", "password", "localhost", 27017, "mydb"
	 * 	e.g. mongodb://127.0.0.1:27017/mydb
	 *     e.g. mongodb://user:passwd@127.0.0.1:27017/mydb
	 */
	@SuppressWarnings("deprecation")
	@Override	
	public int open(String config, String params) {
		HashMap<String, String> map = Util.parseStringParams(params);		
		MongoClientURI uri = new MongoClientURI(config);		
		mg = new MongoClient(uri);
		db = mg.getDB(map.get("dname"));	
		dbc = db.getCollection(map.get("cname"));
		return 0;
	}

	@Override
	public void close() {
		if (null != mg) {
			mg.close();
			mg = null;
		}
	}

	@Override
	public ISLNode createNode() {
		SLNodeImpl node = new SLNodeImpl();
		return node;
	}

	@Override
	public void write(ISLNode node, final EventCallback ecb) {
		DBObject object = ((SLNodeImpl)node).getDBObject();
		int status = 0;
		try {
			dbc.save(object);			
		} catch (Exception e) {
			e.printStackTrace();
			status = -1;
		}
		// 通知完成.
		if (ecb != null) {
			ecb.onFinish(status);
		}
	}

	private MongoClient mg;
	private DB db;
	private DBCollection dbc;
}
