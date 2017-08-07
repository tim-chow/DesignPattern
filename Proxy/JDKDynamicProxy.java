import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IBusiness {
    void process();
}

class BusinessImpl implements IBusiness {
    @Override
    public void process() {
        System.out.println("Business processing");
    }
}

class MyInvocationHandler implements InvocationHandler {
    private IBusiness target;

    public MyInvocationHandler(IBusiness target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy 是代理类的对象
        // 每个Method对象都代表一个类的一个方法，method参数代表的是 接口中定义的方法
        // args是参数列表
        System.out.println("before invoke " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after invoke " + method.getName());
        return result;
    }
}

public class JDKDynamicProxy {
    public static void main(String[] args) {
        IBusiness business = new BusinessImpl();
        // Proxy.newProxyInstance 会生成一个类的字节码。
        // 这个新生成的类就是代理类。代理类继承Proxy类，
        // + 并实现newProxyInstance方法的第二个参数所指定的所有的接口。
        // 同时，代理类的构造方法接受一个InvocationHandler对象；
        // 代理类有若干个Method类型的成员变量。

        // Proxy.newProxyInstance 还会使用newProxyInstance的第三个参数
        // + 所指定的Invocation实例创建代理类的对象；
        // 并且 遍历所有接口的所有方法，为每个方法生成一个Method对象，
        // 然后使用这些Method对象初始化代理对象的成员变量
        business = (IBusiness) Proxy.newProxyInstance(business.getClass().getClassLoader(),
                business.getClass().getInterfaces(),
                new MyInvocationHandler(business));
        business.process();
    }
}

