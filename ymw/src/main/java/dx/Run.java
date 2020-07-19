package dx;
import util.ConfigManager;

/**
 * @author dx_hualuo
 */
public class Run {
    public static void main(String[] args) {
        System.out.println(ConfigManager.getProperty("url"));
    }
}
