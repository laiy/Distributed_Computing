package rfc;

import http.Request;

/**
 * HTTP1.0所必须实现的完整请求 HTTP1.0之前的请求：GET URI HTTP1.0的请求：GET URI HTTP_VERSION
 */
public class FullRequest extends Request {
	// 请求行中HTTP的版本
	String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
