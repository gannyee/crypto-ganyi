package koal.util;

import java.util.Base64;

/**
 * 
 * @author C579
 *
 */
public class ToBase64 {
	
	/**
	 * 
	 * @param data 待编码的数据
	 * @return base64 String类型 
	 */
	public static  String toBase64(byte[] data){
		//Base64编码
		String base64 = Base64.getEncoder().encodeToString(data);
		return base64;
	}
}
