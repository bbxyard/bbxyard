package com.bbxyard.util.impl;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.bbxyard.util.IStructLog;
import com.bbxyard.util.Util;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONSerializers;

/**
 * 
 * @author bbxyard
 *
 */
public class StructMongoAsyncLog implements IStructLog{
	
	/**
	 * 
	 * @author bbxyard
	 *
	 */
	class SLNodeImpl implements ISLNode {
		
		public SLNodeImpl() {
			this.map = Util.newHashMap();
		}
		
		public Map<String, Object> getMap() {
			return map;
		}

		@Override
		public void put(String key, Object value) {
			map.put(key, value);
		}

		@Override
		public String toString() {
			String sout = JSON.serialize(map);
			return sout;
		};

		private Map<String, Object> map;
	}
	
	
	/**
	 * @brief open
	 * @author bbxyard
	 * @param config
	 * 		config: mongodb://%s:%s@%s:%d/%s", "user", "password", "localhost", 27017, "mydb"
	 * 			e.g. mongodb://127.0.0.1:27017/mydb
	 *     		e.g. mongodb://user:passwd@127.0.0.1:27017/mydb
	 *  @param params "dname=xx;cname=yy"
	 */
	@Override
	public int open(String config, String params) {
		this.mongoClient = MongoClients.create(config);
		HashMap<String, String> map = Util.parseStringParams(params);
		this.mongoDB = this.mongoClient.getDatabase(map.get("dname"));
		this.collection  = this.mongoDB.getCollection(map.get("cname"));
		return 0;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (null != mongoClient) {
			mongoClient.close();
			mongoClient = null;
		}
	}

	@Override
	public ISLNode createNode() {
		SLNodeImpl node = new SLNodeImpl();
		return node;
	}

	@Override
	public void write(ISLNode node, final EventCallback ecb) {
		SLNodeImpl snode = (SLNodeImpl)node;
		Map<String, Object> valueMap = snode.getMap();
		Document doc = new Document(valueMap);
		collection.insertOne(doc, new SingleResultCallback<Void>() {
			@Override
			public void onResult(Void arg0, Throwable arg1) {
				if (ecb != null) {
					ecb.onFinish(0);
				}
			}
		});
	}

	private MongoClient mongoClient;
	private MongoDatabase mongoDB;
	private MongoCollection<Document> collection;
}
