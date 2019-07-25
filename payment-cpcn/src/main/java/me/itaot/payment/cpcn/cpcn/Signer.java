package me.itaot.payment.cpcn.cpcn;

public abstract interface Signer {
	public abstract String sign(String paramString) throws Exception;

	public abstract byte[] sign(byte[] paramArrayOfByte) throws Exception;
}