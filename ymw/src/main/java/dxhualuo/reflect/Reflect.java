package dxhualuo.reflect;

import dxhualuo.reflect.exception.NotAnArray;

import java.lang.reflect.InvocationTargetException;

public final class Reflect {
    public static ReflectObject newInstance(Class<?> clazz, Object... par) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return new ReflectObject(clazz, par);
    }

    public static ReflectObject newInstance(String className, Object... par) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return newInstance(ReflectClass.getClass(className), par);
    }

    public static ReflectArray newArray(Class<?> clazz, int... length){
        if(length.length == 1){
            return new ReflectArray(clazz, length[0]);
        }
        return new ReflectArray(clazz, length);
    }

    public static ReflectArray newArray(String className, int... length) throws ClassNotFoundException {
        return newArray(ReflectClass.getClass(className), length);
    }

    public static ReflectArray convertToReflectArray(ReflectObject object){
        if(object.getClass() == ReflectArray.class){
            return (ReflectArray)object;
        }
        else if(object.thisClass.isArray()){
            return new ReflectArray(object);
        }
        else{
            throw new NotAnArray(object.thisClass);
        }
    }
}
