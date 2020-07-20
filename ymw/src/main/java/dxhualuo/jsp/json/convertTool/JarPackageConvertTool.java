package dxhualuo.jsp.json.convertTool;

import dxhualuo.jsp.json.SupportJSONConversion;

import java.lang.reflect.InvocationTargetException;

public interface JarPackageConvertTool {
    String convert(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    default String baseConvert(Object obj){
        return ((SupportJSONConversion)obj).toJSON();
    }
}
