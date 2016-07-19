package koal.util;

/**
 * 
 * @author C579
 *
 */
public class ToHEX {

	/**
	 * byte[] ����ת��Ϊʮ������
	 * 
	 * @param theByte
	 *            byte[] ���� ��ת����bye[]��������
	 * @return ת�����ʮ�������ַ���
	 */
	public static String toHex(byte[] theByte) {
		// ��byte����ת��Ϊʮ������
		StringBuffer hexString = new StringBuffer();

		// ת��Ϊʮ�����Ƶ�ʵ��
		for (int i = 0; i < theByte.length; i++) {
			String hex = Integer.toHexString(0xff & theByte[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/**
	 * byte[] ����ת��Ϊʮ������
	 * 
	 * @param theByte
	 *            byte[] ���� ��ת����bye[]��������
	 * @return ת�����ʮ�������ַ���
	 */
	public static String toHexMethod2(byte[] theByte) {
		// ��byte����ת��Ϊʮ������
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < theByte.length; i++) {
			hexString.append(Integer.toString((theByte[i] & 0xff) + 0x100, 16).substring(1));
		}
		return hexString.toString();
	}
}
