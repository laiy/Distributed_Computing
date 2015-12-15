// 处理器实现类
package rfc;

import http.Handler;
import http.Protocol;

public class HttpHandler extends Handler {
	// 与处理器相关联的协议
	public HttpHandler(Protocol protocol) {
		super(protocol);
	}
}
