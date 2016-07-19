package koal.message_digest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import koal.io.WriteToFile;
import koal.util.ToBase64;
import koal.util.ToHEX;

/**
 * 
 * @author C579
 *
 */
public class MD {

	
	/**
	 * ������ϢժҪ����
	 * @param algorithm String���� ��ϢժҪ�㷨����
	 * @param filePath String���� �ļ���·��
	 * @return  mdByte byte[] ���� ����ժҪԭ��
	 */
	public static byte[] md(String algorithm,String filePath) {
		byte[] mdByte = null;
		try {
			// ��ʼ��md5�㷨
			MessageDigest md = MessageDigest.getInstance(algorithm);
			// ��ȡҪ������ļ�
			FileInputStream file = new FileInputStream(filePath);

			// �ļ����ݼ���
			byte[] dataBytes = new byte[1024];
			int read = 0;
			while ((read = file.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, read);
			}
			
			// ��ȡ���ܺ������
			mdByte = md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			//�������㷨�����ڱ���
			System.out.println("��ϢժҪ�㷨" + algorithm + "�����ڣ�");
		} catch (FileNotFoundException e) {
			//��ȡ���ļ�����
			System.out.println("��ȡ���ļ�������");
		} catch (IOException e) {
			//�ļ���ȡ����
			System.out.println("�ļ���ȡʧ��");
		}
		return mdByte;
	}
}
