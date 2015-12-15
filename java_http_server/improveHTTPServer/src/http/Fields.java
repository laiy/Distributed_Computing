// 用于存储HTTP头部域和其它执行请求时需要的参数
// 其中HTTP头部域HeaderField=FieldName:FieldValue
package http;

import java.util.Map;
import java.util.TreeMap;

public abstract class Fields {
	Map<String, String> fields = new TreeMap<String, String>();
	// 加入一个头部域
	public void addFieldValue(String fieldName, String value) {
		if (validateFieldValue(fieldName, value))
			fields.put(fieldName, value);
	}
	// 获得一个头部域的值
	public String getValue(String fieldName) {
		return fields.get(fieldName);
	}
	// 校验一个头部域及其值是否合法
	protected abstract boolean validateFieldValue(String fieldName, String value);
}
