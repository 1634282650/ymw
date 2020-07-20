package dxhualuo.io;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;

public class Path {
    /**
     *      获得Web工程根目录真实路径
     * @param httpServletRequest request
     * @return  Web根目录真实路径
     */
    public static String getWebProjectRealPath(HttpServletRequest httpServletRequest){
        return httpServletRequest.getServletContext().getRealPath("/");
    }
    /**
     *      获得项目的Src路径
     * @return  src路径
     */
    public static String getSrcPath(){
        return getSrcPathFile("/");
    }
    /**
     *      获得项目的Src路径
     * @return  src路径
     */
    public static String getSrcPathFile(String fileName){
        URL resourceUrl;
        try {
            resourceUrl = Class.forName("dx.Init").getClassLoader().getResource(fileName);
        } catch (ClassNotFoundException e) {
            resourceUrl = Path.class.getClassLoader().getResource(fileName);
        }
        if(resourceUrl != null){
            fileName = resourceUrl.getFile();
            fileName = fileName.substring(1);
            return fileName;
        }
        return null;

    }
}
