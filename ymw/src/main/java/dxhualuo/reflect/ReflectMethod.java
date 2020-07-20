package dxhualuo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ReflectMethod {
    //静态区域=======================================================================================================================
    static {
        methods = new HashMap<>();
    }

    private static HashMap<String, Methods> methods;



    public static Method[] getMethod(Class<?> _class, String methodName) throws NoSuchMethodException {
        String className = _class.getName();
        if (methods.containsKey(className)) {
            Methods _methods = ReflectMethod.methods.get(className);
            Method[] oneMethod;
            if ((oneMethod = _methods.getMethod(methodName)) != null) {
                return oneMethod;
            } else {
                Method[] result = _class.getDeclaredMethods();
                _methods.addMethod(result);
                return _methods.getMethod(methodName);
            }
        } else {
            Method[] method = _class.getDeclaredMethods();
            Methods methods = new Methods(method);
            ReflectMethod.methods.put(className, methods);
            return methods.getMethod(methodName);
        }
    }

    public static Method[] getMethod(String className, String methodName) throws ClassNotFoundException, NoSuchMethodException {
        return getMethod(ReflectClass.getClass(className), methodName);
    }

    public static Method[] getMethods(Class<?> _class) {
        String className = _class.getName();
        if (methods.containsKey(className)) {
            Methods methods = ReflectMethod.methods.get(className);
            if (methods.isComplete()) {
                return methods.getMethods();
            } else {
                Method[] result = _class.getDeclaredMethods();
                methods.addMethod(result);
                methods.setComplete();
                return result;
            }
        } else {
            Method[] method = _class.getDeclaredMethods();
            Methods methods = new Methods(method, true);
            ReflectMethod.methods.put(className, methods);
            return method;
        }
    }

    public static Method[] getMethods(String className) throws ClassNotFoundException {
        return getMethods(ReflectClass.getClass(className));
    }
    public static Methods getAllMethodToMethods(String className) throws ClassNotFoundException {
        return getAllMethodToMethods(ReflectClass.getClass(className));
    }
    public static Methods getAllMethodToMethods(Class<?> _class){
        String className = _class.getName();
        if (methods.containsKey(className)) {
            Methods _methods = ReflectMethod.methods.get(className);
            if (_methods.getMethods() != null && !_methods.isComplete()) {
                return _methods;
            } else {
                Method[] result = _class.getDeclaredMethods();
                _methods.addMethod(result);
                _methods.setComplete();
                return _methods;
            }
        } else {
            Method[] method = _class.getDeclaredMethods();
            Methods methods = new Methods(method);
            methods.setComplete();
            ReflectMethod.methods.put(className, methods);
            return methods;
        }
    }

    //非静态区域==========================================================================================================================
    private Object obj;
    private RenameMethod thisMethod;
    private boolean isStatic = false;

    public ReflectMethod(Object obj, Method[] method) {
        this.obj = obj;
        this.thisMethod = new RenameMethod(method);
        this.isStatic = isStatic(method[0]);
    }

    public ReflectMethod(Method[] method) {
        this.thisMethod = new RenameMethod(method);
        this.isStatic = isStatic(method[0]);
    }

    public ReflectMethod(Class<?> aClass, Method method) {
        this.thisMethod = new RenameMethod(method);
        this.isStatic = isStatic(method);
    }

    public Object execute(Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(ReflectClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(this.obj, par);
    }
    public Object executeUseObject(Object obj, Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(ReflectClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(obj, par);
    }
    public Object executeUseObject(ReflectObject obj, Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(ReflectClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(obj.getObject(), par);
    }

    public Method[] toMethod() {
        return this.thisMethod.getMethods();
    }

    public String getName(){
        return thisMethod.getName();
    }

    public static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }
}


