package dxhualuo.jsp.json.convertTool;

import dxhualuo.jsp.json.JSON;
import dxhualuo.reflect.ReflectObject;

import java.lang.reflect.InvocationTargetException;

public class Jackson implements JarPackageConvertTool {
    private ReflectObject jackson;

    public Jackson() {
        jackson = new ReflectObject("com.fasterxml.jackson.databind.ObjectMapper");
    }

    public String convert(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(JSON.isImplementForSupportJSONConversion(obj)){
            return baseConvert(obj);
        }
        return (String)(jackson.method("writeValueAsString", obj));
    }
}
