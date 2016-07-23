package com.koal;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 
 * @author C579
 *
 */
public class WriteToFile {

	/*public static void main(String[] args) {
		String s1 = "German police are engaged in a huge anti-terror manhunt in the city of Munich after nine people died in a shooting at a shopping centre."
				+ "Thousands of people stranded by the emergency and unable to get home were offered shelter by local residents. The initiative was launched" 
				+ "with the Twitter hashtag #Offenetür (open door)." + "who describe the operation as" +  "an acute terror situation"+", say the first reports of a shooting in Hanauer Street came in just before 18:00 (16:00 GMT).";
		
		String form = "RAW";
		String filenPath = "G:\\test\\output." + form;
		WriteToFile wtf = new WriteToFile();
		wtf.wirteToFile(filenPath, s1.getBytes(), form);
	}*/
	/**
	 * 将字符串写入文件
	 * 
	 * @param filenPath
	 *            String类型 文件的路径
	 * @param data
	 *            String类型 待写入文件的字符串内容
	 */
	public static void wirteToFile(String filenPath, byte[] data,String form) {

		// 实例化file
		File file;
		DataFormTranslate dft = new DataFormTranslate();
		// 如果文件不存在，则创建新文件
		if (filenPath.equals("")) {
			file  = new File("G:\\test\\outsd."+form);
		}else{
			
			file  = new File(filenPath);
			if(!file.exists()){
				try {
					//文件不存在，创建文件
					file.createNewFile();
				} catch (IOException e) {
					// 文件创建失败
					e.printStackTrace();
					System.out.println("文件创建失败！");
				}
			}
		}

		
		try {
			// 文件写入
			FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			OutputStream out =new FileOutputStream(file);
			
			if(form.toUpperCase().equals("RAW")){       //原始数据格式
				//out.write(data);
				bufferedWriter.write(new String(data));
			}else if(form.toUpperCase().equals("B64")){ //base64编码格式
				bufferedWriter.write(dft.byteArrayToBase64(data));
			}else if(form.toUpperCase().equals("HEX")){  //16进制字符串格式
				bufferedWriter.write(dft.byteArrayToHex(data));
			}
			bufferedWriter.close();
		} catch (IOException e) {
			// 文件写入异常
			e.printStackTrace();
			System.out.println("文件写入失败！");
		}
		

	}
}
