package design.petterns.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理工厂，用来创建代理对象
// jdk动态代理
public class ProxyFactory {

    private TrainStation station = new TrainStation();

    public SellTickets getProxyObject() {
        //使用Proxy获取代理对象
        /*
         * @param   loader the class loader to define the proxy class 类加载器，用于加载代理类，使用真实对象的类加载器即可
         * @param   interfaces the list of interfaces for the proxy class
         *          to implement
         * @param   h the invocation handler to dispatch method invocations to
         * @return  a proxy instance with the specified invocation handler of a
         *          proxy class that is defined by the specified class loader
         *          and that implements the specified interfaces
         */

        // 真是对象的类加载器
        ClassLoader classLoader = station.getClass().getClassLoader();
        // 真是对象所实现的接口
        Class<?>[] interfaces = station.getClass().getInterfaces();

        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            // 代理对象的调用处理程序
            /*
                InvocationHandler中invoke方法参数说明：
                    proxy ： 代理对象
                    method ： 对应于在代理对象上调用的接口方法的 Method 实例
                    args ： 代理对象调用接口方法时传递的实际参数


                    proxy – the proxy instance that the method was invoked on
                    method – the Method instance corresponding to the interface method invoked on the proxy instance. The declaring class of the Method object will be the interface that the method was declared in, which may be a superinterface of the proxy interface that the proxy class inherits the method through.
                    args – an array of objects containing the values of the arguments passed in the method invocation on the proxy instance, or null if interface method takes no arguments. Arguments of primitive types are wrapped in instances of the appropriate primitive wrapper class, such as java.lang.Integer or java.lang.Boolean.

             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理点收取一些服务费用(JDK动态代理方式)");
                Object result = method.invoke(station, args);

                return result;
            }
        });

        return sellTickets;
    }

}
