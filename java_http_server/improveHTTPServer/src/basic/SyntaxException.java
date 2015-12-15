package basic;

import http.HttpException;

/**
 * 客户端的请求消息语法、或格式有误
 */
public class SyntaxException extends HttpException {

	public SyntaxException(String msg) {
		super(msg);
	}
}
