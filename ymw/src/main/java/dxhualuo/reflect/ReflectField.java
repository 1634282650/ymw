package dxhualuo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ReflectField {
    //静态区域=======================================================================================================================
    static {
        Methods = new HashMap<>();
    }
    private static HashMap<String, Fields> Methods;
    public static Field getField(Class<?> _class, String fieldName) throws NoSuchFieldException {
        String className = _class.getName();
        if(Methods.containsKey(className)){
            Fields fields = Methods.get(className);
            Field oneMethod;
            if((oneMethod = fields.getField(fieldName)) != null){
                return oneMethod;
            }
            else{
                Field result = _class.getDeclaredField(fieldName);
                fields.addField(result);
                return result;
            }
        }else{
            Field field = _class.getDeclaredField(fieldName);
            Fields fields = new Fields(field);
            Methods.put(className, fields);
            return field;
        }
    }
    public static Field getField(String className, String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        return getField(ReflectClass.getClass(className), fieldName);
    }
    public static Field[] getFields(Class<?> _class){
        String className = _class.getName();
        if(Methods.containsKey(className)){
            Fields fields = Methods.get(className);
            if(fields.isComplete()){
                return fields.getFields();
            }
            else{
                Field[] result = _class.getDeclaredFields();
                fields.addField(result);
                fields.setComplete();
                return result;
            }
        }else{
            Field[] fields = _class.getDeclaredFields();
            Fields fields1 = new Fields(fields,true);
            Methods.put(className, fields1);
            return fields;
        }
    }
    public static Field[] getFields(String className) throws ClassNotFoundException {
        return getFields(ReflectClass.getClass(className));
    }
    public static Fields getAllFieldToFields(Class<?> _class){
        String className = _class.getName();
        if(Methods.containsKey(className)){
            Fields fields = Methods.get(className);
            if(fields.isComplete()){
                return fields;
            }
            else{
                Field[] result = _class.getDeclaredFields();
                fields.addField(result);
                fields.setComplete();
                return fields;
            }
        }else{
            Field[] fields = _class.getDeclaredFields();
            Fields fields1 = new Fields(fields,true);
            Methods.put(className, fields1);
            return fields1;
        }
    }
    public static Fields getAllFieldToFields(String className) throws ClassNotFoundException {
        return getAllFieldToFields(ReflectClass.getClass(className));
    }
    //非静态区域==========================================================================================================================
    private Object obj;
    private Class<?> thisClass;
    private Field thisField;
    private boolean isStatic = false;

    public ReflectField(Object obj, Field field, Class<?> clazz){
        this.obj = obj;
        this.thisField = field;
        this.thisClass = clazz;
        this.isStatic = isStatic(this.thisField);
    }

    public ReflectField(Field field, Class<?> clazz){
        this.thisField = field;
        this.thisClass = clazz;
        this.isStatic = isStatic(this.thisField);
    }

    public Object value() throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            return thisField.get(thisClass);
        }
        return thisField.get(obj);
    }

    public Object value(Object val) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            thisField.set(thisClass, val);
        }
        thisField.set(obj, val);
        return val;
    }
    
    public Object valueUseObject(Object obj) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            return thisField.get(thisClass);
        }
        return thisField.get(obj);
    }

    public Object valueUseObject(Object obj, Object val) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            thisField.set(thisClass, val);
        }
        thisField.set(obj, val);
        return val;
    }

    public Field toField(){
        return this.thisField;
    }

    public String getName(){
        return thisField.getName();
    }

    public static boolean isStatic(Field field){
        return Modifier.isStatic(field.getModifiers());
    }
}
