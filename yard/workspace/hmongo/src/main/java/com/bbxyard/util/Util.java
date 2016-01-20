package com.bbxyard.util;

import java.util.HashMap;

public class Util {
	public static HashMap<String, String> parseStringParams(String params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] attrs = params.split(";");
		for (String attr: attrs) {
			String[] sub = attr.split("=");
			if (sub.length < 2)
				continue;
			map.put(sub[0], sub[1]);
		}
		return map;
	}
}
