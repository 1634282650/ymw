package dxhualuo.jvm;

/**
 * @author dx_hualuo
 */
public class System {
    /**
     *  允许虚拟机自动保存生成的文件
     */
    public static void enableSaveGeneratedFiles(){
        java.lang.System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    /**
     *  不允许虚拟机自动保存生成的文件
     */
    public static void disableSaveGeneratedFiles(){
        java.lang.System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "false");
    }
}
