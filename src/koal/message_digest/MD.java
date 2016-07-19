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
	 * 生成信息摘要操作
	 * @param algorithm String类型 信息摘要算法类型
	 * @param filePath String类型 文件的路径
	 * @return  mdByte byte[] 类型 生成摘要原文
	 */
	public static byte[] md(String algorithm,String filePath) {
		byte[] mdByte = null;
		try {
			// 初始化md5算法
			MessageDigest md = MessageDigest.getInstance(algorithm);
			// 读取要输入的文件
			FileInputStream file = new FileInputStream(filePath);

			// 文件内容加密
			byte[] dataBytes = new byte[1024];
			int read = 0;
			while ((read = file.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, read);
			}
			
			// 读取加密后的密文
			mdByte = md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			//函数库算法不存在报错
			System.out.println("信息摘要算法" + algorithm + "不存在！");
		} catch (FileNotFoundException e) {
			//读取空文件错误
			System.out.println("读取的文件不存在");
		} catch (IOException e) {
			//文件读取错误
			System.out.println("文件读取失败");
		}
		return mdByte;
	}
}
