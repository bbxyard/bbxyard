package com.bbxyard.util;

import com.bbxyard.util.impl.StructMongoAsyncLog;
import com.bbxyard.util.impl.StructMongoLog;

/**
 * @brief 结构化日志工厂类
 * @author bbxyard
 *
 */
public class StructLogFactory {
	
	/**
	 * 
	 * @param name 
	 * @return
	 */
	public static IStructLog create(String name) {
		// 以下语法限Java1.7+
		IStructLog slog = null;
		switch (name) {
		case "mongo":
			slog = new StructMongoLog();
			break;
		case "mongo-async":
			slog = new StructMongoAsyncLog();
			break;
		default:
			break;
		}
		return slog;
	}
	
	public static void destory(IStructLog slog) {
		if (slog != null) {
			slog.close();
			slog = null;
		}
	}

}
