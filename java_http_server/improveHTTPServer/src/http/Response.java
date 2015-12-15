package http;

import java.io.InputStream;

public abstract class Response {
	// 请求的头部
	StringBuffer header = new StringBuffer();
	// WEB对象，这里简化成以流的形式来表达
	InputStream input;
	// 为头部添加一行
	public void addHeader(String line) {
		header.append(line);
	}
	public StringBuffer getHeader() {
		return header;
	}

	public void setHeader(StringBuffer header) {
		this.header = header;
	}
	public InputStream getInput() {
		return input;
	}
	public void setInput(InputStream input) {
		this.input = input;
	}
}