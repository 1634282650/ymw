package dxhualuo.jsp.json.convertTool;

import dxhualuo.data.Strings;
import dxhualuo.reflect.ReflectObject;

/**
 * @author dx_hualuo
 */
public class ConvertToolFactory {
    public static JarPackageConvertTool getConvertTool(String toolName){
        ReflectObject obj;
        obj = new ReflectObject("dx_hualuo.jsp.json.convertTool." + Strings.headUpperCase(toolName));
        return (JarPackageConvertTool)obj.getObject();
    }
    public static JarPackageConvertTool getConvertTool(){
        return getConvertTool("Jackson");
    }
}
