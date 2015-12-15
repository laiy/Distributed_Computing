// 描述用户的请求
package http;

public abstract class Request {
	// 请求要执行的方法的名字
	String methodName;
	// 执行方法时所需要的资源位置
	String uri;
	// 执行方法时需要的其它条件
	Fields fields;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}
}