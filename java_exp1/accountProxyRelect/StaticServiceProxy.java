// 静态代理类，实现AccountService接口
public class StaticServiceProxy implements AccountService {
    private String host;
    private int port;

    public StaticServiceProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getAccount(String Name) {
        Connector connector = null;
        try {
            connector = new Connector(host, port);
            RemoteCall call = new RemoteCall("AccountService", "getAccount", new Class[] { String.class }, new Object[] { Name });
            connector.send(call);
            call = (RemoteCall)connector.receive();
            Object result = call.getResult();
            return (String)result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally  {
            if (connector != null) connector.close();
        }
        return Name;
    }
}
