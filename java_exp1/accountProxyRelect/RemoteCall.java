// 远程调用对象
import java.io.*;

public class RemoteCall implements Serializable {
    private String className; //表示类名或接口名
    private String methodName; //表示方法名
    private Class[] paramTypes; //表示方法参数类型
    private Object[] params; //表示方法参数值

    //如果方法正常执行，则result为方法返回值，如果方法抛出异常，那么result为该异常。
    private Object result;
    public RemoteCall(){}
    public RemoteCall(String className, String methodName, Class[] paramTypes,
        Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
    public Class[] getParamTypes() { return paramTypes; }
    public void setParamTypes(Class[] paramTypes) { this.paramTypes = paramTypes; }
    public Object[] getParams() { return params; }
    public void setParams(Object[] params) { this.params = params; }
    public Object getResult() { return result; }
    public void setResult(Object result) { this.result = result; }
    public String toString() { return "className=" + className + " methodName=" + methodName; }
}
