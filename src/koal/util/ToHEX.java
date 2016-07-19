package koal.util;

/**
 * 
 * @author C579
 *
 */
public class ToHEX {

	/**
	 * byte[] 数组转换为十六进制
	 * 
	 * @param theByte
	 *            byte[] 类型 待转换的bye[]类型数组
	 * @return 转换后的十六进制字符串
	 */
	public static String toHex(byte[] theByte) {
		// 将byte类型转化为十六进制
		StringBuffer hexString = new StringBuffer();

		// 转换为十六进制的实现
		for (int i = 0; i < theByte.length; i++) {
			String hex = Integer.toHexString(0xff & theByte[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/**
	 * byte[] 数组转换为十六进制
	 * 
	 * @param theByte
	 *            byte[] 类型 待转换的bye[]类型数组
	 * @return 转换后的十六进制字符串
	 */
	public static String toHexMethod2(byte[] theByte) {
		// 将byte类型转化为十六进制
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < theByte.length; i++) {
			hexString.append(Integer.toString((theByte[i] & 0xff) + 0x100, 16).substring(1));
		}
		return hexString.toString();
	}
}
