package com.bbxyard.hmongo;

import com.bbxyard.util.IStructLog;
import com.bbxyard.util.StructLogFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void testMongoAdd(IStructLog slog) {
		for (int i = 0; i < 40; ++i) {
			IStructLog.ISLNode node = slog.createNode();
			node.put("name", "boxu" + i);
			node.put("sno", i + 2000);
			node.put("age", i * 10);
			slog.write(node, new IStructLog.EventCallback() {
				@Override
				public void onFinish(int status) {
					System.out.printf("add finished!! status=%d\n", status );
				}
			});
		}
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        // mongo-test
        String[] names = {"mongo-async", "mongo"};
        for (String name : names) {
        	IStructLog slog = StructLogFactory.create(name);
        	slog.open("mongodb://127.0.0.1:27017", "dname=test;cname=c0301");
        	testMongoAdd(slog);
        	slog.close();
            StructLogFactory.destory(slog);
		}
    }
    
}
