// 其中HTTP头部域HeaderField=FieldName:FieldValue
package http;

public abstract class Handler {
	// 处理器所依赖的协议
	protected Protocol protocol;

	public Handler(Protocol protocol) {
		setProtocol(protocol);
	}
	// 对请求消息进行处理并返回响应
	public Response handle(String message) {
		//根据消息产生请求
		Request request = protocol.generateRequest(message);
		//根据请求返回相应的处理方法
		Method method = protocol.handleRequest(request);
		//执行方法处理请求，生成响应做为处理结果，并返回该响应
		return method.execute(request);
	}
	public Protocol getProtocol() {
		return protocol;
	}
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

}
