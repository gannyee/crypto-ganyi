package koal.rsa_sign;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import koal.key_generate.KeyGenerate;

/**
 * 
 * @author C579
 *
 */
public class Sign {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		KeyPair keyPair = KeyGenerate.keyPairGenerate("RSA");
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		String message = "hello world";
		byte[] data = message.getBytes();
		byte[] signData = sign(data, privateKey);
		System.out.println("sign:  " + new BigInteger(signData).toString(16));
		System.out.println(verify(signData, publicKey));
	}

	/**
	 * 
	 * @param data
	 *            byte[]类型 待签字的信息
	 * @param privateKey
	 *            PrivateKey类型 用于数字签字的密钥
	 * @return 签字后的数据
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static byte[] sign(byte[] data, PrivateKey privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {

		// Security.addProvider(new BouncyCastleProvider());
		Signature signer = Signature.getInstance("SHA256WithRSA");
		// Signature signer = Signature.getInstance("RSA","BC");
		signer.initSign(privateKey);
		signer.update(data);
		return signer.sign();
	}

	public static boolean verify(byte[] data, PublicKey publicKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException, SignatureException {
		/*
		 * Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding","BC");
		 * cipher.init(Cipher.DECRYPT_MODE, publicKey);
		 * 
		 * byte[] decSig = cipher.doFinal(data);
		 * 
		 * ASN1InputStream aIn = new ASN1InputStream(decSig); ASN1Sequence seq =
		 * (ASN1Sequence) aIn.readObject(); System.out.println("asn1: " +
		 * ASN1Dump.dumpAsString(seq));
		 * 
		 * MessageDigest hash = MessageDigest.getInstance("SHA-256","BC");
		 * hash.update(data);
		 * 
		 * ASN1OctetString signHash = (ASN1OctetString) seq.getObjectAt(1);
		 * System.out.println(MessageDigest.isEqual(hash.digest(),
		 * signHash.getOctets()));
		 */
		// Security.addProvider(new BouncyCastleProvider());
		Signature signer = Signature.getInstance("SHA256WithRSA");
		// Signature signer = Signature.getInstance("RSA","BC");
		signer.initVerify(publicKey);
		signer.update(data);
		return signer.verify(data);
	}
}
