package koal.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author C579
 *
 */
public class WriteToFile {
	
	
	/**
	 * ���ַ���д���ļ�
	 * @param filenPath String����  �ļ���·��
	 * @param data String���� ��д���ļ����ַ�������
	 */
	public static void wirteToFile(String filenPath,String data){
		
		//ʵ����file
		File file = new File(filenPath);
		
		//����ļ������ڣ��򴴽����ļ�
		if((!file.exists())){
			try {
				file.createNewFile();
			} catch (IOException e) {
				//�ļ��ļ�����ʧ��
				System.out.println("�ļ�����ʧ�ܣ�");
			}
		}
		
		//�ļ�д��
		try {
			FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(data);
			bufferedWriter.close();
		} catch (IOException e) {
			//�ļ��������쳣
			System.out.println("�ļ������ڣ�");
		}
		
	}
}
