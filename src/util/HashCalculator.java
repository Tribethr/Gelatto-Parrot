package util;

import java.security.MessageDigest;

public class HashCalculator {

	public static StringBuffer calulateHash(String toCalculate) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(toCalculate.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		
		return sb;
	}

}