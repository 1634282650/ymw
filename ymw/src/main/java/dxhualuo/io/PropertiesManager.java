package dxhualuo.io;

import java.io.*;
import java.util.Properties;

/**
 *  配置文件管理器
 * @author dx_hualuo
 */
public class PropertiesManager {
    /**
     * 配置文件存储位置
     */
    private Properties config;

    /**
     *  通过绝对路径path来读取配置文件
     * @param path 配置文件的绝对路径
     */
    public PropertiesManager(String path) throws IOException {
        InputStream is = null;
        File file = new File(path);
        try{
            if(!file.exists()){
                throw new FileNotFoundException("文件"+path+"未找到！");
            }
            is = new FileInputStream(file);
            config = new Properties();
            config.load(is);
        }catch (Exception ex){
            throw ex;
        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    /**
     *  获得配置文件指定key的value值
     * @param key 键
     * @return 值
     */
    public String getValue(String key){
        return config.getProperty(key);
    }

    /**
     *  临时设置配置文件指定key的value值，如果没有则添加。
     * @param key 键
     * @param value 值
     */
    public void setValue(String key,String value){
        config.setProperty(key, value);
    }

    /**
     *  保存到文件
     * @param path 文件路径
     * @throws IOException 保存时可能发生的IO异常
     */
    public void saveToFile(String path) throws IOException {
        FileOutputStream os = new FileOutputStream(new File(path));
        config.store(os, null);
    }
}
