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
	 * ����������Ԥ������Կ�ļ�������֤�����Կ
	 * @param keyFile String���ͣ���Կ�ļ���·��
	 * @param keyPass String�࣬ ��Կ
	 */
	public PK7(String keyFile,String keyPass) {
		// TODO Auto-generated constructor stub
		//��������Կ�ļ�
		ParseKeyFile pkf = new ParseKeyFile(keyFile, keyPass);
		X509Certificate certificate = pkf.getCertificate();
		PrivateKey privateKey = pkf.getPrivateKey();
	}
	
	/**
	 * pkcs7ǩ��
	 * @param data ��ǩ��������
	 * @param keyFile ��Կ�ļ�
	 * @param keyPass ��Կ����
	 * @return byte[] ǩ������ļ�
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
		//����CMS
		List cerList = new ArrayList();
		CMSTypedData msg = new CMSProcessableByteArray(data.getBytes());
		cerList.add(certificate);
		Store certs = new JcaCertStore(cerList);
		CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
		//��ʼ��ǩ��
		ContentSigner cs = new JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(privateKey);
		gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(cs,certificate));
		//���֤��
		gen.addCertificates(certs);
		//���ǩ������
		CMSSignedData signedData = gen.generate(msg,false);
		return signedData.getEncoded();
	}
	
	/**
	 * pkcs7֤����ǩ
	 * @param data String���� ����ǩ������
	 * @return verified boolean���� ��ǩ�Ƿ�ɹ�
	 * @throws IOException
	 * @throws CMSException
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 */
	public static boolean verify(String data) throws IOException, CMSException, OperatorCreationException, CertificateException {
		
		//�Ƿ���ǩ�ɹ���־
		boolean verified = false;
		//���BouncyCastle��Ϊ��ȫ�ṩ
		Security.addProvider(new BouncyCastleProvider());
		//�½�PKCS#7ǩ�����ݴ������
		CMSSignedData signedData = new CMSSignedData(data.getBytes());
		
		//���֤����Ϣ
		Store certs = signedData.getCertificates();
		//���ǩ������Ϣ
		SignerInformationStore signers = signedData.getSignerInfos();
		Collection c = signers.getSigners();
		Iterator it = c.iterator();
		
		//��ǩ����ǩ���ߵ���Ϣ
		while (it.hasNext()) {
			SignerInformation signerInfor = (SignerInformation) it.next();
			
			//֤����
			Collection certCollection = certs.getMatches(signerInfor.getSID());
			Iterator certIt = certCollection.iterator();
			X509CertificateHolder certHolder = (X509CertificateHolder) certIt.next();
			X509Certificate cert = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certHolder);
			
			//��֤����ǩ��
            if (signerInfor.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
            	verified = true;
            }else{
            	verified = false;
            }
		}
		return verified;
	}
	

	/**
	 * PKCS#7�����ŷ����
	 * @param text String���� �����ܵ�ԭ������
	 * @return bye[]����  ���ܺ������
	 * @throws CertificateEncodingException
	 * @throws CMSException
	 * @throws IOException
	 * ����ο���http://www.bouncycastle.org/docs/pkixdocs1.4/org/bouncycastle/cms/CMSEnvelopedDataGenerator.html
	 */
	public static byte[] pk7encrypt(String text) throws CertificateEncodingException, CMSException, IOException{
		CMSTypedData ctd = new CMSProcessableByteArray(text.getBytes());
		CMSEnvelopedDataGenerator cedg = new CMSEnvelopedDataGenerator();
		cedg.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(certificate).setProvider("BC"));
		CMSEnvelopedData ed = cedg.generate(ctd,new JceCMSContentEncryptorBuilder(CMSAlgorithm.DES_EDE3_CBC).setProvider("BC").build());
		return ed.getEncoded();
	}
	
	
	/**
	 * PKCS#7�����ŷ����
	 * @param cipher String���� ����
	 * @return bye[]���� ԭ������
	 * @throws CMSException
	 * @throws IOException
	 * ����ο���http://www.bouncycastle.org/docs/pkixdocs1.4/org/bouncycastle/cms/CMSEnvelopedDataParser.html
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
