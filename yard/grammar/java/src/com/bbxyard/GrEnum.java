/**
 * @author bbxyard
 * @desc 枚举学习
 */
package com.bbxyard;

enum ErrorCode {
	ERR_FORBID("Forbid", 403),
	ERR_NOT_FOUND("丢失", 404),
	ERR_SUCC("OK", 200);
	
	private String desc;
	private int num;

	
	ErrorCode(String desc, int  num) {
		this.desc = desc;
		this.num = num;
	}
	
	// static
	public static String getDesc(int num) {
		String ret = null;
		for (ErrorCode ec : ErrorCode.values()) {
			if (ec.getNum() == num) {
				ret = ec.getDesc();
				break;
			}				
		}
		return ret;
	}

	// get - set method
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}	
}


public class GrEnum {
	public static void main(String[] args) {
		System.out.println("ERROR 404 means: " + ErrorCode.getDesc(404));
		System.out.println("ERROR 404 means: " + ErrorCode.ERR_NOT_FOUND.getDesc());
		System.out.println("ERROR 501 means: " + ErrorCode.getDesc(501));
	}
}
