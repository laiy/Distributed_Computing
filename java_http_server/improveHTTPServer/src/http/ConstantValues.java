// HTTP协议中所使用到的常量
package http;

public class ConstantValues {
	// 回车换行
	public static final String CRLF = System.getProperty("line.separator");
	// 服务器的响应状态码
	public enum StatusCode {
		OK(200), BAD_REQUEST(400), NOT_FOUND(404), INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(
				501);
		private StatusCode(int code) {
			this.code = code;
		}
		private int code; // 状态代码

		public int getCode() {
			return code;
		}
		public static void main(String[] args) {
			System.out.print(StatusCode.BAD_REQUEST);
		}

	}
}
