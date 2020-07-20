package dxhualuo.reflect;

public class Proxy {
    public static Object newProxyInstance(java.lang.reflect.InvocationHandler handler, Object obj){
        return java.lang.reflect.Proxy.newProxyInstance(handler.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}
