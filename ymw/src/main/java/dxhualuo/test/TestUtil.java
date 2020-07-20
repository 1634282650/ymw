package dxhualuo.test;

/**
 * @author dx_hualuo
 */
public class TestUtil {
    public static long testMethodTime(Runnable testMethod){
        long time = System.currentTimeMillis();
        testMethod.run();
        return System.currentTimeMillis() - time;
    }
    public static Exception textMethod(Runnable testMethod){
        try{
            testMethod.run();
        }catch (Exception e){
            return e;
        }
        return null;
    }
}
