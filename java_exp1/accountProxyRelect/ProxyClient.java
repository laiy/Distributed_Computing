//客户端程序
public class ProxyClient {
    public static void main(String args[])throws Exception {
        //创建静态代理类实例
        StaticServiceProxy Service1 = new StaticServiceProxy("localhost", 8000);
        System.out.println(Service1.getAccount("Zhang3"));
        //创建动态代理类实例
        AccountService Service2 = (AccountService)DynamicProxyFactory.getProxy
            (AccountService.class, "localhost", 8000);
        System.out.println(Service2.getAccount("Li4"));
    }
}
