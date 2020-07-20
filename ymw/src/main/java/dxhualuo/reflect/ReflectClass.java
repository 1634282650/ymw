package dxhualuo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectClass {
    private static HashMap<String, Class> map;
    static {
        map = new HashMap<>();
        map.put("int", int.class);
        map.put("double", double.class);
        map.put("float", float.class);
        map.put("boolean", boolean.class);
        map.put("long", long.class);
        map.put("short", short.class);
        map.put("byte", byte.class);
        map.put("char", char.class);
    }
    public static Class getClass(String className) throws ClassNotFoundException {
        Class result = map.get(className);
        if(result == null){
            result = Class.forName(className);
            map.put(className,result);
        }
        return result;
    }
    public static boolean isExtendOrImplementFrom(Class<?> child, Class<?> fatherClassOrInterface){
        return fatherClassOrInterface.isAssignableFrom(child);
    }

    static Class<?>[] getParametersClass(Object... par){
        Class<?>[] _class = new Class<?>[par.length];
        for (int i = 0; i < par.length; i++) {
            _class[i] = par[i].getClass();
        }
        return _class;
    }

    static boolean equalsParTypes(Class<?>[] c1, Class<?>[] c2){
        if(c1.length == c2.length){
            for (int i = 0; i < c1.length; i++) {
                if(c1[i] != c2[i]){
                    if (!isExtendOrImplementFrom(c2[i],c1[i])) {
                        Class<?> clazz;
                        if((clazz = convertToPrimitiveClass(c2[i])) != null){
                            if(c1[i] != clazz){
                                return false;
                            }
                        }else if((clazz = convertToPrimitiveClass(c1[i])) != null){
                            if(c2[i] != clazz){
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    static Constructor getClassConstructor(Class<?> clazz, Class<?>[] parType) throws NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor con: constructors) {
            if(equalsParTypes(con.getParameterTypes(), parType)){
                return con;
            }
        }
        throw new NoSuchMethodException();
    }

    private static Class<?> convertToPrimitiveClass(Class<?> PackagingClass){
        switch (PackagingClass.getName()){
            case "java.lang.Integer": return int.class;
            case "java.lang.Double": return double.class;
            case "java.lang.Character": return char.class;
            case "java.lang.Long": return long.class;
            case "java.lang.Short": return short.class;
            case "java.lang.Byte": return byte.class;
            case "java.lang.Boolean": return boolean.class;
            case "java.lang.Float": return float.class;
            default: return null;
        }
    }

    private Class<?> _class;
    public ReflectClass(Class<?> _class){
        this._class = _class;
    }
    public ReflectClass(String className) throws ClassNotFoundException {
        this._class = ReflectClass.getClass(className);
    }

    public ReflectMethod StaticMethod(String methodName) throws NoSuchMethodException {
        Method[] method = ReflectMethod.getMethod(_class, methodName);
        if(method == null || method.length == 0){
            throw new NoSuchMethodException();
        }
        if (ReflectMethod.isStatic(method[0])) {
            return new ReflectMethod(_class, method);
        }
        throw new NoSuchMethodException();
    }

    public Object StaticMethod(String methodName, Object... par) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = _class.getDeclaredMethod(methodName);
        if (ReflectMethod.isStatic(method)) {
            method.invoke(null, par);
        }
        throw new NoSuchMethodException();
    }

    public Object StaticField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field fl = ReflectField.getField(_class, fieldName);
        if (ReflectField.isStatic(fl)) {
            return fl.get(_class);
        }else{
            throw new NoSuchFieldException();
        }
    }

    public Object StaticField(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        Field fl = ReflectField.getField(_class, fieldName);
        if (ReflectField.isStatic(fl)) {
            fl.set(_class, val);
        }else{
            throw new NoSuchFieldException();
        }
        return val;
    }

    public boolean isExtendOrImplementFrom(Class<?> fatherClassOrInterface){
        return fatherClassOrInterface.isAssignableFrom(_class);
    }

    public Class<?> toClass(){
        return _class;
    }
}
