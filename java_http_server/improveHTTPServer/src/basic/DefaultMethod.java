package basic;

import http.ConstantValues;
import http.Method;
import http.Request;
import http.Response;
import rfc.FullResponse;

/**
 * 框架提供的预定义方法 当协议发现一个请求但并无相应的处理方法实现时， 可以返回该方法，以维护框架的一致。
 */
public class DefaultMethod extends Method {

	@Override
	public Response execute(Request request) {
		Response response = new FullResponse();
		String status = "HTTP/1.0 "// 生成状态码
				+ ConstantValues.StatusCode.NOT_IMPLEMENTED.getCode()
				+ " "
				+ ConstantValues.StatusCode.NOT_IMPLEMENTED
				+ ConstantValues.CRLF;
		response.addHeader(status);
		response.addHeader(ConstantValues.CRLF);
		return response;
	}

}
