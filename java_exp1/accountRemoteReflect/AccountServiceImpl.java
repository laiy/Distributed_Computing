//远程对象接口的实现类
public class AccountServiceImpl implements AccountService {
    public String getAccount(String Name) {
        return "Account id: " + Name;
    }
}
