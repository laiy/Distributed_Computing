// 处理请求的方法
package http;

public abstract class Method {
	// 处理请求，生成响应
	public abstract Response execute(Request request);
}
