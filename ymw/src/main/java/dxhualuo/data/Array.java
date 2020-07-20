package dxhualuo.data;

import java.util.Arrays;

/**
 *  Array数组工具类
 * @author dx_hualuo
 */
public class Array {
    /**
     *  将Integer数组转为int数组
     * @param array Integer数组
     * @return int数组
     */
    public static int[] devanningInteger(Integer[] array){
        return Arrays.stream(array).mapToInt(Integer::intValue).toArray();
    }
    /**
     *  将Double数组转为double数组
     * @param array Double数组
     * @return double数组
     */
    public static double[] devanningDouble(Double[] array){
        return Arrays.stream(array).mapToDouble(Double::shortValue).toArray();
    }
    /**
     *  将Long数组转为long数组
     * @param array Long数组
     * @return long数组
     */
    public static long[] devanningLong(Long[] array){
        return Arrays.stream(array).mapToLong(Long::longValue).toArray();
    }
    /**
     *  将Character数组转为char数组
     * @param array Character数组
     * @return char数组
     */
    public static char[] devanningCharacter(Character[] array){
        char[] chars = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] = array[i];
        }
        return chars;
    }
    /**
     *  将Byte数组转为byte数组
     * @param array Byte数组
     * @return byte数组
     */
    public static byte[] devanningByte(Byte[] array){
        byte[] chars = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] = array[i];
        }
        return chars;
    }
}
