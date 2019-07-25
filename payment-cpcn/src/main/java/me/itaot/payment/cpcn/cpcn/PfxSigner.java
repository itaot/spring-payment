package me.itaot.payment.cpcn.cpcn;

import me.itaot.payment.tools.StringUtil;

import java.security.PrivateKey;
import java.security.Signature;

public class PfxSigner
  implements Signer
{
  private PrivateKey privateKey;
  private String algorithm = "SHA1withRSA";

  public PfxSigner(String pfxFile, String password) throws Exception {
    this.privateKey = SecurityUtil.getPrivateKeyFromPFX(pfxFile, password);
  }

  public PfxSigner(String pfxFile, String password, String algorithm) throws Exception {
    this.privateKey = SecurityUtil.getPrivateKeyFromPFX(pfxFile, password);
    this.algorithm = algorithm;
  }

  public String sign(String message) throws Exception {
    Signature signature = Signature.getInstance(this.algorithm);
    signature.initSign(this.privateKey);
    signature.update(message.getBytes("UTF-8"));
    return StringUtil.bytes2hex(signature.sign());
  }

  public byte[] sign(byte[] bytes) throws Exception {
    Signature signature = Signature.getInstance(this.algorithm);
    signature.initSign(this.privateKey);
    signature.update(bytes);
    return signature.sign();
  }

  public String getAlgorithm() {
    return this.algorithm;
  }

  public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
}