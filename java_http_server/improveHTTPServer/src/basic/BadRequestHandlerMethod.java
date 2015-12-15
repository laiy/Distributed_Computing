package basic;

import http.ConstantValues;
import http.Method;
import http.Request;
import http.Response;
import rfc.FullResponse;

/**
 * 对不合法的请求进行处理的方法
 */
public class BadRequestHandlerMethod extends Method {

	@Override
	public Response execute(Request request) {
		Response response = new FullResponse();
		String status = "HTTP/1.0 "// 生成状态码
				+ ConstantValues.StatusCode.BAD_REQUEST.getCode()
				+ " "
				+ ConstantValues.StatusCode.BAD_REQUEST + ConstantValues.CRLF;
		response.addHeader(status);
		// response.addHeader(ConstantValues.CRLF);
		return response;
	}

}
