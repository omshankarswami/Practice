package com.mydocking.payload;

public class ResponcePayload {
	
	private long code;
	private String message;
	private Object data;
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponcePayload [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
}
