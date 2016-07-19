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
	 * 将字符串写入文件
	 * @param filenPath String类型  文件的路径
	 * @param data String类型 待写入文件的字符串内容
	 */
	public static void wirteToFile(String filenPath,String data){
		
		//实例化file
		File file = new File(filenPath);
		
		//如果文件不存在，则创建新文件
		if((!file.exists())){
			try {
				file.createNewFile();
			} catch (IOException e) {
				//文件文见创建失败
				System.out.println("文件创建失败！");
			}
		}
		
		//文件写入
		try {
			FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(data);
			bufferedWriter.close();
		} catch (IOException e) {
			//文件不存在异常
			System.out.println("文件不存在！");
		}
		
	}
}
