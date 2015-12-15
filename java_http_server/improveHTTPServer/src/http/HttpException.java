// HTTP协议处理过程中可能产生的异常
package http;

@SuppressWarnings("serial")
public class HttpException extends Exception {
	public HttpException(String msg) {
		super(msg);
	}
}
