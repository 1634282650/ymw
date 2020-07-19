import java.util.HashMap;

//测试类
public class DataTest {

    public static void main(String[] args) {
        System.out.println("测试");
        HashMap<Integer, String> objectObjectHashMap = new HashMap<Integer, String>();
        objectObjectHashMap.put(1,"admin");
        objectObjectHashMap.remove("admin");
        System.out.println(objectObjectHashMap.size());
    }
}
