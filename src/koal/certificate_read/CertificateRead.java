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
		 System.out.println("�汾��:  "+t.getVersion());  
		  
	        System.out.println("���к�:  "+t.getSerialNumber().toString(16));  
	        System.out.println("ȫ�� : "+t.getSubjectDN());  
	        System.out.println("ǩ����ȫ��: "+t.getIssuerDN());  
	        System.out.println("��Ч����ʼ�� : "+t.getNotBefore());  
	        System.out.println("��Ч�ڽ����� : "+t.getNotAfter());  
	        System.out.println("ǩ���㷨:  "+t.getSigAlgName());  
	        byte[] sig=t.getSignature();  
	        System.out.println("ǩ��: "+new BigInteger(sig).toString(16));
	        PublicKey pk=t.getPublicKey();  
	        
	        byte[ ] pkenc=pk.getEncoded();  
	        System.out.println("��Կ");  
	}
	
	public static X509Certificate certificateRead() throws CertificateException, IOException{
		CertificateFactory cf = CertificateFactory.getInstance("x.509");
		FileInputStream in = new FileInputStream("C://Users//C579//Desktop//ca//������֤��.cer");
		Certificate c = cf.generateCertificate(in);
		in.close();
		X509Certificate x = (X509Certificate) c;
		return x;
	}
}
