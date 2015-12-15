// HTTP协议是对一串请求消息进行处理,并把处理结果作为响应返回
package http;

public interface Protocol {
	// 根据消息，产生请求
	public Request generateRequest(String message);
	// 根据请求，返回处理请求的命令方法
	public Method handleRequest(Request request);
}
