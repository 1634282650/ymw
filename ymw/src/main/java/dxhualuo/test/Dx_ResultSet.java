package dxhualuo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Dx_ResultSet implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("proxy method!");
        return null;
    }
}
