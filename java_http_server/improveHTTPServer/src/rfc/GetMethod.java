// 处理GET请求的方法
package rfc;

import http.ConstantValues;
import http.Fields;
import http.Method;
import http.Request;
import http.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import util.FileTypeManager;

public class GetMethod extends Method {
	@Override
	public Response execute(Request request) {
		try {
			Response response = new FullResponse();
			Fields fields = request.getFields();
			String path = request.getUri();
			if (path == null) {// 请求中的uri为空，BAD_REQUEST
				String status = "HTTP/1.0 "
						+ ConstantValues.StatusCode.BAD_REQUEST.getCode()
						+ ConstantValues.StatusCode.BAD_REQUEST
						+ ConstantValues.CRLF; // 状态码
				response.addHeader(status);
				return response;
			}
			File file = new File(path);
			if (fields != null) {
				String modifiedSince = fields.getValue("If-Modified-Since");
				if (modifiedSince != null) {// 条件GET
				}
			}
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				String status = "HTTP/1.0 "
						+ ConstantValues.StatusCode.OK.getCode() + " "
						+ ConstantValues.StatusCode.OK + ConstantValues.CRLF; // 状态码
				String server = "Server:BenHuang's server "
						+ ConstantValues.CRLF; // 服务器描述
				String contentType = "Content-type:"
						+ FileTypeManager.getType(file) + ConstantValues.CRLF; // 文件类型信息
				String contentLength = "Content-length:" + file.length()
						+ ConstantValues.CRLF;
				response.addHeader(status);
				response.addHeader(server);
				response.addHeader(contentType);
				response.addHeader(contentLength);
				response.setInput(fileInputStream);
				return response;
			} catch (FileNotFoundException e) {// 文件不存在
				response = new FullResponse();
				String status = "HTTP/1.0 "
						+ ConstantValues.StatusCode.NOT_FOUND.getCode() + " "
						+ ConstantValues.StatusCode.NOT_FOUND
						+ ConstantValues.CRLF; // 状态码
				response.addHeader(status);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Response response = new FullResponse();
			String status = "HTTP/1.0 "
					+ ConstantValues.StatusCode.INTERNAL_SERVER_ERROR.getCode()
					+ " " + ConstantValues.StatusCode.INTERNAL_SERVER_ERROR
					+ ConstantValues.CRLF; // 状态码
			response.addHeader(status);
			return response;
		}
	}
}