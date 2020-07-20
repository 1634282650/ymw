package dxhualuo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dx_hualuo
 */
public class ReflectObject {
    /**
     *  当前反射对象，包装的对象
     */
    protected Object obj;
    /**
     *  当前反射对象的Class
     */
    Class<?> thisClass;
    /**
     *  当前反射对象所包含的方法
     */
    private Methods method;
    /**
     *  当前反射对象所包含的字段
     */
    private Fields field;
    /**
     *   通过class对象和构造方法参数创建反射对象
     * @param aClass 通过这个类创建反射对象实例
     * @param par 构造方法参数
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     */
     public ReflectObject(Class<?> aClass, Object... par) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        cheakParameter(par);
        if(par.length == 0) {
            obj = aClass.getDeclaredConstructor((Class<?>) null).newInstance();
        }
        else {
            try {
                obj = aClass.getDeclaredConstructor(ReflectClass.getParametersClass(par)).newInstance(par);
            } catch (NoSuchMethodException e) {
                 Constructor constructor = ReflectClass.getClassConstructor(aClass, ReflectClass.getParametersClass(par));
                 obj = constructor.newInstance(par);
            }
        }
        this.thisClass = aClass;
    }

    /**
     *  通过类名创建反射实例
     * @param className
     * @param par
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public ReflectObject(String className, Object... par) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this(ReflectClass.getClass(className), par);
    }
    public ReflectObject(Object obj){
        this.obj = obj;
        thisClass = obj.getClass();
    }

    /**
     *      执行方法，不带参数
     * @param methodName 方法名
     * @return  返回执行方法的返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException 非法访问异常
     */
    public Object method(String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        cheakSetMethod();
        Method method = RenameMethod.getMethod(this.method.getMethod(methodName), ReflectClass.getParametersClass());
        method.setAccessible(true);
        return method.invoke(obj);
    }

    public Object method(String methodName, Object... par) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        cheakSetMethod();
        cheakParameter(par);
        Method method = RenameMethod.getMethod(this.method.getMethod(methodName), ReflectClass.getParametersClass(par));
        method.setAccessible(true);
        return method.invoke(obj, par);
    }

    public ReflectMethod getMethod(String methodName) throws NoSuchMethodException {
        cheakSetMethod();
        return new ReflectMethod(obj, method.getMethod(methodName));
    }

    public Object field(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        cheakSetField();
        Field field = this.field.getField(fieldName);
        if(field == null){
            field = ReflectField.getField(thisClass, fieldName);
        }
        field.setAccessible(true);
        return field.get(obj);
    }
    public Object field(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        cheakSetField();
        val = cheakObject(val);
        Field field = this.field.getField(fieldName);
        if(field == null){
            field = ReflectField.getField(thisClass, fieldName);
        }
        field.setAccessible(true);
        field.set(obj, val);
        return val;
    }
    public ReflectField getField(String fieldName) throws NoSuchFieldException {
        cheakSetField();
        Field field = this.field.getField(fieldName);
        if(field == null){
            field = ReflectField.getField(thisClass, fieldName);
        }
        return new ReflectField(obj, field, thisClass);
    }
    public Object getObject(){
        return obj;
    }

    private void cheakSetMethod(){
        if(method == null){
            method = ReflectMethod.getAllMethodToMethods(thisClass);
        }
    }
    private void cheakSetField(){
        if(field == null){
            field = ReflectField.getAllFieldToFields(thisClass);
        }
    }
    private void cheakParameter(Object... obj){
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].getClass() == ReflectObject.class){
                obj[i] = ((ReflectObject)obj[i]).getObject();
            }
        }
    }
    public Class<?> getThisClass(){
        return thisClass;
    }
    private Object cheakObject(Object obj){
        if (obj.getClass() == ReflectObject.class){
            obj = ((ReflectObject)obj).getObject();
        }
        return obj;
    }
}
