package dxhualuo.calculate;

/**
 * @author dx_hualuo
 */
public class Maths {
	/**
	 * 计算一个Double值的几次方
	 *
	 * @param num 要计算的数
	 * @param squ 几次方
	 * @return num的squ次方的结果
	 */
	public static double square(double num, int squ) {
		double value = 0;
		value = num;
		for (int resqu = 1; resqu < squ; resqu++) {
			value *= num;
		}
		return value;
	}
}