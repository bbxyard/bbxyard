package com.bbxyard.util;


public interface IStructLog {
	interface EventCallback {
		void onFinish(int status);
	}
	
	interface ISLNode {
		void put(String key, Object value);
	}
	
	int open(String config, String params);
	void close();
	ISLNode createNode();
	void write(ISLNode node, final EventCallback cb);
}
