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
	 * @param data �����������
	 * @return base64 String���� 
	 */
	public static  String toBase64(byte[] data){
		//Base64����
		String base64 = Base64.getEncoder().encodeToString(data);
		return base64;
	}
}
