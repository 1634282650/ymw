package dxhualuo.reflect;

import dxhualuo.data.MapList;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Methods implements Serializable {
    private boolean complete = false;
    private MapList<String, Method> method;


    public Methods() {
        this.method = new MapList<>();
    }
    public Methods(Method method) {
        this.method = new MapList<>();
        addMethod(method);
    }

    public Methods(Method[] method, boolean complete) {
        this.method = new MapList<>();
        addMethod(method);
        this.complete = complete;
    }

    public Methods(Method[] methods) {
        this.method = new MapList<>();
        addMethod(methods);
    }

    public boolean isComplete(){
        return complete;
    }

    public void setComplete(){
        complete = true;
    }

    public Method[] getMethod(String methodName) throws NoSuchMethodException {
        List<Method> list = method.get(methodName);
        if(list == null || list.size() == 0){
            throw new NoSuchMethodException();
        }
        Method[] me = new Method[list.size()];
        method.get(methodName).toArray(me);
        return me;
    }
    public Method getMethod(String methodName, Class<?>... parType) throws NoSuchMethodException {
        Method[] mts = getMethod(methodName);
        ArrayList<Method> list = new ArrayList<>();
        for (Method method: list) {
            method.getParameterTypes();
        }
        return null;
    }

    public Method[] getMethods(){
        Method[] result = new Method[method.size()];
        method.values().toArray(result);

        return result;
    }

    public void addMethod(Method method){
        if(isComplete()){
            return;
        }
        this.method.put(method.getName(), method);
    }

    public void addMethod(Method[] method){
        for (Method me: method) {
            addMethod(me);
        }
    }


}
