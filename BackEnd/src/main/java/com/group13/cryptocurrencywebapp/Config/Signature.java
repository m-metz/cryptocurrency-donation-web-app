package com.group13.cryptocurrencywebapp.config;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Signature {
	final String HMAC_SHA256 = "HmacSHA256";
	final String HMAC_SHA384 = "HmacSHA384";

	// convert byte array to hex string
	private String bytesToHex(byte[] bytes) {
		final char[] hexArray = "0123456789abcdef".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0, v; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public String getSignature(String data, String key) {
		byte[] hmacSha256 = null;
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA256);
			Mac mac = Mac.getInstance(HMAC_SHA256);
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(data.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha256", e);
		}
		return bytesToHex(hmacSha256);
	}

	public byte[] encodeBase64(String payload) throws UnsupportedEncodingException {
		byte[] encodeMe = payload.getBytes("UTF-8");
		byte[] encodedBytes = Base64.getEncoder().encode(encodeMe);
		return encodedBytes;
	}

	public String signHmacSha384(byte[] data, String key) {
		byte[] hmacSha384 = null;
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA384);
			Mac mac = Mac.getInstance(HMAC_SHA384);
			mac.init(secretKeySpec);
			hmacSha384 = mac.doFinal(data);
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha384", e);
		}
		return new String(Hex.encodeHexString(hmacSha384));

	}

}