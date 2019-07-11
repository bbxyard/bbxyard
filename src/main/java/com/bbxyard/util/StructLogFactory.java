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
	 * @brief 创建结构化对象类
	 * @param name 可取值"mongo|mongo-async"
	 * @return
	 */
	public static IStructLog create(String name) {
		// 以下语法限Java1.7+
		IStructLog slog = null;
//		switch (name) {
//		case "mongo":
//			slog = new StructMongoLog();
//			break;
//		case "mongo-async":
//			slog = new StructMongoAsyncLog();
//			break;
//		default:
//			break;
//		}
		if (name.equals("mongo")) {
			slog = new StructMongoLog();
		} else if (name.equals("mongo-async")) {
			slog = new StructMongoAsyncLog();
		}
		return slog;
	}
	
	/**
	 * @brief 可选关闭方法，由GC自动完成即可
	 * @param slog
	 */
	public static void destory(IStructLog slog) {
		if (slog != null) {
			slog.close();
			slog = null;
		}
	}

}
