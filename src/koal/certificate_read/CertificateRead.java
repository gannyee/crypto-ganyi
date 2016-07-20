package koal.certificate_read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateRead {
	
	public static void main(String[] args) throws CertificateException, IOException {
		X509Certificate t = certificateRead();
		 System.out.println("版本号:  "+t.getVersion());  
		  
	        System.out.println("序列号:  "+t.getSerialNumber().toString(16));  
	        System.out.println("全名 : "+t.getSubjectDN());  
	        System.out.println("签发者全名: "+t.getIssuerDN());  
	        System.out.println("有效期起始日 : "+t.getNotBefore());  
	        System.out.println("有效期截至日 : "+t.getNotAfter());  
	        System.out.println("签名算法:  "+t.getSigAlgName());  
	        byte[] sig=t.getSignature();  
	        System.out.println("签名: "+new BigInteger(sig).toString(16));
	        PublicKey pk=t.getPublicKey();  
	        
	        byte[ ] pkenc=pk.getEncoded();  
	        System.out.println("公钥");  
	}
	
	public static X509Certificate certificateRead() throws CertificateException, IOException{
		CertificateFactory cf = CertificateFactory.getInstance("x.509");
		FileInputStream in = new FileInputStream("C://Users//C579//Desktop//ca//服务器证书.cer");
		Certificate c = cf.generateCertificate(in);
		in.close();
		X509Certificate x = (X509Certificate) c;
		return x;
	}
}
