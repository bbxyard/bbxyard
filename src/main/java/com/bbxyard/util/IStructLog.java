package com.bbxyard.util;

/**
 * @brief 结构化日志类
 * @author bbxyard
 */
public interface IStructLog {
	/**
	 * @brief 用户事件通知
	 * @author bbxyard
	 */
	interface EventCallback {
		void onFinish(int status);
	}
	
	/**
	 * @brief 日志结点
	 * @author bbxyard
	 *
	 */
	interface ISLNode {
		void put(String key, Object value);
	}
	
	/**
	 * @brief 打开日志类
	 * @param config
	 * @param params
	 * @return
	 */
	int open(String config, String params);
	void close();
	ISLNode createNode();
	
	/**
	 * @brief 提交写入接构化信息
	 * @param node
	 * @param cb 可为空
	 */
	void write(ISLNode node, final EventCallback cb);
}
