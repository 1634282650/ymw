package dxhualuo.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
		@author 17868
 */

public class Files {
    /**
     *      判断文件或目录是否存在
     * @param url   地址
     * @return  boolean
     */
	public static boolean isExist(String url){
	    return new File(url).exists();
    }

    /**
     *      获得文件输出流
     * @param url   文件地址
     * @return  FileOutputStream对象
     * @throws FileNotFoundException    文件未找到异常
     */
    public static FileOutputStream getFileOutputStream(String url) throws FileNotFoundException {
	    return new FileOutputStream(new File(url));
    }

    /**
     *      获得文件输入流
     * @param url   文件地址
     * @return  FileOutputStream对象
     * @throws FileNotFoundException    文件未找到异常
     */
    public static FileInputStream getFileInputStream(String url) throws FileNotFoundException {
        return new FileInputStream(new File(url));
    }

    /**
     *      创建目录，包括子目录
     * @param url   完整目录名
     * @return  是否创建成功
     */
    public static boolean mkdirs(String url){
        return new File(url).mkdirs();
    }
}
