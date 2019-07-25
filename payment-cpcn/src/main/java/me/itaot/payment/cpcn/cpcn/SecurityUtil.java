package me.itaot.payment.cpcn.cpcn;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.sun.jersey.core.util.Base64;
import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.tools.StringUtil;

public class SecurityUtil {
	public static final String DIGEST_ALGORITHM_MD5 = "MD5";
	public static final String DIGEST_ALGORITHM_SHA1 = "SHA-1";
	public static final String SIGNATURE_ALGORITHM_MD5_WITH_RSA = "MD5withRSA";
	public static final String SIGNATURE_ALGORITHM_SHA1_WITH_RSA = "SHA1withRSA";
	public static final String SIGNATURE_ALGORITHM_SHA256_WITH_RSA = "SHA256withRSA";
	static byte[] KEY_DATA = { -28, -72, -83, -23, -121, -111, -26, -108, -81,
			-28, -69, -104, -26, -100, -119, -23, -103, -112, -27, -123, -84,
			-27, -113, -72 };

	static byte[] IV_DATA = { 67, 112, 99, 110, 49, 64, 51, 52 };

	public static String[] SIGNATURE_ALGORITHMS_IN_USE = { "SHA1withRSA",
			"SHA256withRSA" };

	public static PrivateKey getPrivateKeyFromPFX(String pfxfilename,
			String password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

		FileInputStream fis = new FileInputStream(pfxfilename);
		keyStore.load(fis, password.toCharArray());
		fis.close();

		Enumeration aliases = keyStore.aliases();

		String alias = (String) aliases.nextElement();

		return ((PrivateKey) keyStore.getKey(alias, password.toCharArray()));
	}

	public static PrivateKey getPrivateKeyFromPFXForRSA(String pfxFileName,
			String pfxPassword) throws Exception {
		FileInputStream file_inputstream = new FileInputStream(pfxFileName);
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

		keyStore.load(file_inputstream, pfxPassword.toCharArray());

		String alias = null;
		Enumeration aliases = keyStore.aliases();
		if (aliases.hasMoreElements()) {
			alias = ((String) aliases.nextElement()).toString();
		}

		Key key = keyStore.getKey(alias, pfxPassword.toCharArray());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		return keyFactory.generatePrivate(keySpec);
	}

	public static byte[] digest(String message, String algorithm)
			throws Exception {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(message.getBytes("UTF-8"));
		return md.digest();
	}

	public static byte[] digest(String message) throws Exception {
		return digest(message, "MD5");
	}

	public static X509Certificate generateCertificate(String base64string)
			throws CertificateException, UnsupportedEncodingException {
		if (!(base64string.startsWith("--"))) {
			base64string = "-----BEGIN CERTIFICATE-----\n" + base64string
					+ "\n-----END CERTIFICATE-----\n";
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(
				base64string.getBytes("UTF-8"));
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		return ((X509Certificate) cf.generateCertificate(bais));
	}

	public static X509Certificate generateCertificate(String base64string,
			String charset) throws CertificateException,
			UnsupportedEncodingException {
		if (!(base64string.startsWith("--"))) {
			base64string = "-----BEGIN CERTIFICATE-----\n" + base64string
					+ "\n-----END CERTIFICATE-----\n";
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(
				base64string.getBytes(charset));
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		return ((X509Certificate) cf.generateCertificate(bais));
	}

	public static X509Certificate generateCertificate(FileInputStream fis)
			throws CertificateException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		return ((X509Certificate) cf.generateCertificate(fis));
	}

	public static KeyManager[] getKeyManagers(String keyStore,
			String keyStoreType, String keyStorePassword) throws Exception {
		String algorithm = KeyManagerFactory.getDefaultAlgorithm();
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
		KeyStore ks = KeyStore.getInstance(keyStoreType);

		FileInputStream fis = new FileInputStream(keyStore);
		ks.load(fis, keyStorePassword.toCharArray());
		fis.close();
		kmf.init(ks, keyStorePassword.toCharArray());

		KeyManager[] kms = kmf.getKeyManagers();
		return kms;
	}

	public static TrustManager[] getTrustManagers(String trustStore,
			String trustStoreType, String trustStorePassword) throws Exception {
		String algorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(algorithm);
		KeyStore keyStore = KeyStore.getInstance(trustStoreType);

		FileInputStream fileInputStream = new FileInputStream(trustStore);
		keyStore.load(fileInputStream, trustStorePassword.toCharArray());
		fileInputStream.close();
		trustManagerFactory.init(keyStore);
		TrustManager[] tms = trustManagerFactory.getTrustManagers();
		return tms;
	}

	public static byte[] decryptRSAToByte(String encryptedDataBase64,
			PrivateKey privateKey) throws PaymentException {
		if (StringUtil.isEmpty(encryptedDataBase64)) {
			throw new PaymentException("700000", "密文不能为空");
		}
		if (null == privateKey) {
			throw new PaymentException("700000", "私钥不能为空");
		}

		byte[] plainBinary = null;
		try {
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(2, privateKey);

			byte[] encodedBytes = Base64.decode(encryptedDataBase64);
			plainBinary = rsaCipher.doFinal(encodedBytes);
		} catch (Exception e) {
			System.err.println("RSADecrypt Exception:- " + e);
		}

		return plainBinary;
	}

	public static String convertSignatureAlgorithm(String algorithm) {
		String result = null;
		algorithm = StringUtil.trim(algorithm);
		for (String algorithmInUse : SIGNATURE_ALGORITHMS_IN_USE) {
			if (algorithmInUse.equalsIgnoreCase(algorithm)) {
				result = algorithmInUse;
				break;
			}
		}
		return result;
	}

	public static byte[] DES3_CBC_Decrypt(byte[] ivData, byte[] keyData,
			byte[] cipherText) {
		SecretKeySpec key3Des = new SecretKeySpec(keyData, "DESede");
		IvParameterSpec IvSpec = new IvParameterSpec(ivData);
		byte[] plainText = null;
		try {
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(2, key3Des, IvSpec);
			plainText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			System.err.println("DecryptCipher Exception:- " + e);
		}
		return plainText;
	}

	public static byte[] DES3_CBC_Encrypt(byte[] ivData, byte[] keyData,
			byte[] plainText) throws Exception {
		SecretKeySpec key3Des = new SecretKeySpec(keyData, "DESede");
		IvParameterSpec IvSpec = new IvParameterSpec(ivData);
		byte[] cipherText = null;
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(1, key3Des, IvSpec);
		cipherText = cipher.doFinal(plainText);

		return cipherText;
	}

	public static String DES3_Decrypt(String cipherText) throws Exception {
		String plainText = "";
		if (StringUtil.isNotEmpty(cipherText)) {
			SecretKeySpec key3Des = new SecretKeySpec(KEY_DATA, "DESede");
			IvParameterSpec IvSpec = new IvParameterSpec(IV_DATA);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(2, key3Des, IvSpec);
			plainText = new String(cipher.doFinal(StringUtil
					.hex2bytes(cipherText)));
		}

		return plainText;
	}

	public static String DES3_Encrypt(String plainText) throws Exception {
		String cipherText = "";
		if (StringUtil.isNotEmpty(plainText)) {
			cipherText = StringUtil.bytes2hex(DES3_CBC_Encrypt(IV_DATA,
					KEY_DATA, plainText.getBytes("UTF-8")));
		}

		return cipherText;
	}

	public static String DES3_CBC_Decrypt(String cipherText) throws Exception {
		String plainText = "";

		if (StringUtil.isNotEmpty(cipherText)) {
			SecretKeySpec key3Des = new SecretKeySpec(KEY_DATA, "DESede");
			IvParameterSpec IvSpec = new IvParameterSpec(IV_DATA);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(2, key3Des, IvSpec);
			plainText = new String(cipher.doFinal(StringUtil
					.hex2bytes(cipherText)));
		}

		return plainText;
	}

	public static String DES3_CBC_Encrypt(String plainText) throws Exception {
		String cipherText = "";
		if (StringUtil.isNotEmpty(plainText)) {
			cipherText = StringUtil.bytes2hex(DES3_CBC_Encrypt(IV_DATA,
					KEY_DATA, plainText.getBytes("UTF-8")));
		}

		return cipherText;
	}
}