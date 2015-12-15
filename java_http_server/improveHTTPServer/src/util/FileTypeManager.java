package util;

import java.io.File;

public class FileTypeManager {
	private FileTypeManager() {
	}
	/**
	 * 确定请求文件的类型
	 */
	public static String getType(File file) {
		String name = file.getName();
		if (name.endsWith("htm") || name.endsWith("html"))
			return "text/html";
		else if (name.endsWith("jpg"))
			return "image/jpg";
		else if (name.endsWith("gif"))
			return "image/gif";
		else
			return "error";
	}
}
