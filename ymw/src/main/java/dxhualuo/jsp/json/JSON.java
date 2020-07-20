package dxhualuo.jsp.json;

import dxhualuo.jsp.json.convertTool.JarPackageConvertTool;
import dxhualuo.reflect.ReflectClass;

import java.util.HashMap;

public class JSON {
    //jackson
    private static HashMap<String, JarPackageConvertTool> toolMap;
    public static String ConvertToJSON(Object obj){
        if(obj == null){
            throw new NullPointerException();
        }
        if(isImplementForSupportJSONConversion(obj)){
            return ((SupportJSONConversion)obj).toJSON();
        }
        return "";
    }
    private static String useJarPackageToolConvert(Object obj) throws Exception {
        if (toolMap == null) {
            toolMap = new HashMap<>();
            return null;
        }
        for(JarPackageConvertTool tool : toolMap.values()){
            try{
                return tool.convert(obj);
            }catch (Exception e){}
        }
        throw new Exception("没有可以正确转换的工具！");
    }
    public static boolean isImplementForSupportJSONConversion(Object obj){
        return ReflectClass.isExtendOrImplementFrom(obj.getClass(), SupportJSONConversion.class);
    }
}
