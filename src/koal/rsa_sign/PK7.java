package koal.rsa_sign;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSEncryptedData;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSEnvelopedDataParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientInformationStore;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import koal.parse_keyFile.ParseKeyFile;

/**
 * 
 * @author C579
 *
 */
public class PK7 {
	
	private static X509Certificate certificate;
	private static PrivateKey privateKey;
	/**
	 * 工作函数，预处理密钥文件，解析证书和密钥
	 * @param keyFile String类型，密钥文件的路径
	 * @param keyPass String类， 密钥
	 */
	public PK7(String keyFile,String keyPass) {
		// TODO Auto-generated constructor stub
		//解析传密钥文件
		ParseKeyFile pkf = new ParseKeyFile(keyFile, keyPass);
		X509Certificate certificate = pkf.getCertificate();
		PrivateKey privateKey = pkf.getPrivateKey();
	}
	
	/**
	 * pkcs7签名
	 * @param data 待签名的数据
	 * @param keyFile 密钥文件
	 * @param keyPass 密钥密码
	 * @return byte[] 签名或的文件
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws OperatorCreationException
	 * @throws CMSException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public byte[] sign(String data) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, OperatorCreationException, CMSException, NoSuchProviderException, InvalidKeyException, SignatureException{
		//创建CMS
		List cerList = new ArrayList();
		CMSTypedData msg = new CMSProcessableByteArray(data.getBytes());
		cerList.add(certificate);
		Store certs = new JcaCertStore(cerList);
		CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
		//初始化签名
		ContentSigner cs = new JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(privateKey);
		gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(cs,certificate));
		//添加证书
		gen.addCertificates(certs);
		//获得签名数据
		CMSSignedData signedData = gen.generate(msg,false);
		return signedData.getEncoded();
	}
	
	/**
	 * pkcs7证书验签
	 * @param data String类型 待验签的数据
	 * @return verified boolean类型 验签是否成功
	 * @throws IOException
	 * @throws CMSException
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 */
	public static boolean verify(String data) throws IOException, CMSException, OperatorCreationException, CertificateException {
		
		//是否验签成功标志
		boolean verified = false;
		//添加BouncyCastle作为安全提供
		Security.addProvider(new BouncyCastleProvider());
		//新建PKCS#7签名数据处理对象
		CMSSignedData signedData = new CMSSignedData(data.getBytes());
		
		//获得证书信息
		Store certs = signedData.getCertificates();
		//获得签名者信息
		SignerInformationStore signers = signedData.getSignerInfos();
		Collection c = signers.getSigners();
		Iterator it = c.iterator();
		
		//验签所有签名者的信息
		while (it.hasNext()) {
			SignerInformation signerInfor = (SignerInformation) it.next();
			
			//证书链
			Collection certCollection = certs.getMatches(signerInfor.getSID());
			Iterator certIt = certCollection.iterator();
			X509CertificateHolder certHolder = (X509CertificateHolder) certIt.next();
			X509Certificate cert = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certHolder);
			
			//验证数字签字
            if (signerInfor.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
            	verified = true;
            }else{
            	verified = false;
            }
		}
		return verified;
	}
	

	/**
	 * PKCS#7数字信封加密
	 * @param text String类型 待加密的原文数据
	 * @return bye[]类型  加密后的密文
	 * @throws CertificateEncodingException
	 * @throws CMSException
	 * @throws IOException
	 * 代码参考：http://www.bouncycastle.org/docs/pkixdocs1.4/org/bouncycastle/cms/CMSEnvelopedDataGenerator.html
	 */
	public static byte[] pk7encrypt(String text) throws CertificateEncodingException, CMSException, IOException{
		CMSTypedData ctd = new CMSProcessableByteArray(text.getBytes());
		CMSEnvelopedDataGenerator cedg = new CMSEnvelopedDataGenerator();
		cedg.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(certificate).setProvider("BC"));
		CMSEnvelopedData ed = cedg.generate(ctd,new JceCMSContentEncryptorBuilder(CMSAlgorithm.DES_EDE3_CBC).setProvider("BC").build());
		return ed.getEncoded();
	}
	
	
	/**
	 * PKCS#7数字信封解密
	 * @param cipher String类型 密文
	 * @return bye[]类型 原文数据
	 * @throws CMSException
	 * @throws IOException
	 * 代码参考：http://www.bouncycastle.org/docs/pkixdocs1.4/org/bouncycastle/cms/CMSEnvelopedDataParser.html
	 */
	public static byte[] pk7decrypy(String cipher) throws CMSException, IOException{
		CMSEnvelopedDataParser cedp = new CMSEnvelopedDataParser(cipher.getBytes());
		RecipientInformationStore ris = cedp.getRecipientInfos();
		Collection collection = ris.getRecipients();
		Iterator iterator = collection.iterator();
		
		if(iterator.hasNext()){
			RecipientInformation ri = (RecipientInformation) iterator.next();
			byte[] content = ri.getContent(new JceKeyTransEnvelopedRecipient(privateKey).setProvider("BC"));
			return content;
		}
		return null;
	}
}
