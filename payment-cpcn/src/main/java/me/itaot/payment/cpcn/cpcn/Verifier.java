package me.itaot.payment.cpcn.cpcn;

public abstract interface Verifier {
	public abstract boolean verify(String paramString1, String paramString2)
			throws Exception;

	public abstract boolean verify(byte[] paramArrayOfByte1,
                                   byte[] paramArrayOfByte2) throws Exception;
}