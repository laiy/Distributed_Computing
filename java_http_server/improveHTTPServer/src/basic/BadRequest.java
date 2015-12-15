package basic;

import http.Request;

/**
 * 框架预定义的请求 当解析用户的字符串请求消息时，若发现消息语法、格式或版本不兼容 可以用这个请求描述，以维护整个框架系统运作的正常。
 */
public class BadRequest extends Request {
}
