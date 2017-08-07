import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 目标类：无需实现任何接口
class Business {
    public void process() {
        System.out.println("Business process");
    }
}

// 拦截器类：实现MethodInterceptor接口
class MyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        // o 是代理类的对象。CGLib生成的代理类是目标类的子类
        // + System.out.println(Business.class.isInstance(o)); 会打印 true
        // objects 是参数列表
        // method 是目标方法
        // 直接使用 method.invoke(o, objects); 方式，或导致StackOverFlow
        // + 因为 代理类重写了父类的该方法，所以其实调用的是代理类的方法，
        // + 而代理类的方法，又调用了intercept方法。。。

        // 因此，调用父类的方法，应该使用MethodProxy.invokeSuper()方法
        System.out.println("before invoke " + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after invoke " + method.getName());
        return result;
    }
}

public class CGLibDynamicProxy {
    public static void main(String[] args) throws Throwable {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Business.class);
        enhancer.setCallback(new MyInterceptor());
        Business business = (Business) enhancer.create();
        business.process();
    }
}

