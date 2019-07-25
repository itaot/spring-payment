package me.itaot.payment.cpcn.cpcn;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;

import me.itaot.payment.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateVerifier implements Verifier {

	private static final Logger log = LoggerFactory.getLogger(CertificateVerifier.class);

	private PublicKey publicKey;
	private String algorithm = "SHA1withRSA";

	public CertificateVerifier(String file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		this.publicKey = cf.generateCertificate(fis).getPublicKey();
	}

	public CertificateVerifier(String file, String algorithm) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		this.publicKey = cf.generateCertificate(fis).getPublicKey();
		this.algorithm = algorithm;
	}

	public boolean verify(String message, String signature) throws Exception {
		log.info("Start verify process ... ");
		log.info("Start hex2bytes ... ");
		byte[] bytes = StringUtil.hex2bytes(signature);
		log.info("Start new signature ... ");
		Signature sig = Signature.getInstance(this.algorithm);
		log.info("Start init verify ...");
		sig.initVerify(this.publicKey);
		log.info("Start update message ... ");
		sig.update(message.getBytes("UTF-8"));
		log.info("Execuete over ... ");
		return sig.verify(bytes);
	}

	public boolean verify(byte[] data, byte[] signature) throws Exception {
		Signature sig = Signature.getInstance(this.algorithm);
		sig.initVerify(this.publicKey);
		sig.update(data);
		return sig.verify(signature);
	}

	public String getAlgorithm() {
		return this.algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
}