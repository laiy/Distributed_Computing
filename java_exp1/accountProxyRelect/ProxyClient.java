//�ͻ��˳���
public class ProxyClient {
    public static void main(String args[])throws Exception {
        //������̬������ʵ��
        StaticServiceProxy Service1 = new StaticServiceProxy("localhost", 8000);
        System.out.println(Service1.getAccount("Zhang3"));
        //������̬������ʵ��
        AccountService Service2 = (AccountService)DynamicProxyFactory.getProxy
            (AccountService.class, "localhost", 8000);
        System.out.println(Service2.getAccount("Li4"));
    }
}
