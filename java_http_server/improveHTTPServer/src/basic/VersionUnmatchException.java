package basic;

import http.HttpException;

/**
 * 客户端请求的HTTP服务的版本与当前实现的版本不匹配
 */
public class VersionUnmatchException extends HttpException {

	public VersionUnmatchException(String msg) {
		super(msg);
	}

}
