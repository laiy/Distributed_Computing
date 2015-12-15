// 为提高协议的各版本的兼容性，框架所提供的协议抽象类/
package http;

public abstract class AbstractProtocol implements Protocol {
	// 当前协议的版本号
	protected String version;
	// 旧的协议
	protected Protocol oldProtocol;

	abstract public Request generateRequest(String message);

	abstract public Method handleRequest(Request request);

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public Protocol getOldProtocol() {
		return oldProtocol;
	}
	public void setOldProtocol(Protocol oldProtocol) {
		this.oldProtocol = oldProtocol;
	}
}
