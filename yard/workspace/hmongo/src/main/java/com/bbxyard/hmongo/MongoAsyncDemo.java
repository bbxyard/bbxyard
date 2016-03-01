package com.bbxyard.hmongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;


public class MongoAsyncDemo {	
	
	public static final int DEMO_TIMEOUT = 100; 
	
	public static void showCount(MongoCollection<Document> collection) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		final AtomicLong cnt = new AtomicLong();
		System.out.println("Counting the number of documents");
		collection.count(new SingleResultCallback<Long>() {
			@Override
			public void onResult(final Long result, final Throwable t) {
				cnt.set(result);
				latch.countDown();
			}
		});
		boolean counted = latch.await(DEMO_TIMEOUT, TimeUnit.SECONDS);
		assert(counted);
		System.out.println(" - Count result: " + cnt.get());
	}
		
	
	public static void dropAndReAdd(final MongoCollection<Document> collection) {
		final CountDownLatch latch = new CountDownLatch(1);
		
		// 执行
		collection.drop(new SingleResultCallback<Void>() {
			@Override
			public void onResult(Void arg0, Throwable arg1) {
				System.out.println("The orginal collection dropped!! Let's add new documents!!");
				
				// insert-one
				HashMap< String, Object> map = new HashMap<>();
				map.put("sno", "20034487");
				map.put("wno", "20070063");				
				Document doc = new Document(map);
				doc.append("name", "boxu");
				collection.insertOne(doc, new SingleResultCallback<Void>() {
					@Override
					public void onResult(final Void result, final Throwable t) {
						System.out.println(" - single documents added!!");
					}
				});
				
				// insert-many
				List<Document> docs = new ArrayList<Document>();
				for (int i  = 0; i < 100; ++i) {
					docs.add(new Document("id-" +i, i));					
				}
				collection.insertMany(docs, new SingleResultCallback<Void>() {
					@Override
					public void onResult(final Void result, final Throwable t) {
						System.out.println(" - documents added!!");
						latch.countDown();
					}
				});
			}
		});

		// 等待完成
		try {
			boolean finished = latch.await(DEMO_TIMEOUT, TimeUnit.SECONDS);
			assert(finished);
			System.out.print(" - Add new documents finished!!");
		} catch (InterruptedException e) {
			System.out.println("Operation Timeout!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		
		// print all db
		final CountDownLatch listDbsLatch = new CountDownLatch(1);
		System.out.println("dbs: ");
		mongoClient.listDatabaseNames().forEach(new Block<String>() {
			@Override
			public void apply(final String name) {
				System.out.println("-" + name);
			}},
			new SingleResultCallback<Void>() {
				@Override
				public void onResult(final Void result, final Throwable t) {
					listDbsLatch.countDown();
				}
			}
		);
		boolean listedAllDbs = listDbsLatch.await(DEMO_TIMEOUT, TimeUnit.SECONDS);
		assert(listedAllDbs);
		
		// all
		final MongoDatabase db = mongoClient.getDatabase("bbx");
		final MongoCollection<Document> collection = db.getCollection("busybox");
		
		// test
		dropAndReAdd(collection);
		showCount(collection);
		
		// 关闭DB连接
		mongoClient.close();
	}
}
