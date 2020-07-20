package dxhualuo.reflect;

import java.lang.reflect.Method;

public class RenameMethod {
    private Method[] methods;
    RenameMethod(Method[] method) {
        methods = method;
        SafetyCheck();
    }

    RenameMethod(Method method){
        methods = new Method[1];
        methods[0] = method;
    }

    RenameMethod(Method[] method,boolean isSafety) {
        methods = method;
        if (!isSafety) {
            SafetyCheck();
        }
    }

    static Method getMethod(Method[] methods, Class<?>[] parameter) throws NoSuchMethodException {
        for (Method method: methods) {
            if(ReflectClass.equalsParTypes(method.getParameterTypes(), parameter)){
                return method;
            }
        }
        throw new NoSuchMethodException();
    }

    Method getMethod(Class<?>[] parameter){
        for (Method method: methods) {
            if(ReflectClass.equalsParTypes(method.getParameterTypes(), parameter)){
                return method;
            }
        }
        return null;
    }

    public int count(){
        return methods.length;
    }

    public Method get(int index){
        try {
            return methods[index];
        } catch (Exception e) {
            return null;
        }
    }

    String getName(){
        if(methods != null && methods.length > 0){
            return methods[0].getName();
        }
        return null;
    }

    Method[] getMethods(){
        return methods;
    }
    private void SafetyCheck(){
        if (methods == null || methods.length == 0) {
            throw new NullPointerException();
        }
        String name = methods[0].getName();
        for (Method method : methods) {
            if (!method.getName().equals(name)) {
                throw new RuntimeException("不是同名重载方法！");
            }
        }
    }
}
