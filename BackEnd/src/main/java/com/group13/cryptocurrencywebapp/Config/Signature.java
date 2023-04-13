package com.group13.cryptocurrencywebapp.config;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * <pre>
 * Class Name: Signature
 * 
 * Date Created: March 15, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Helper class that implements methods for hashing and signing payloads before
 * they are sent in API calls.
 * Used by API requests to the exchanges Binance and Gemini
 * 
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
public class Signature {
	/**
	 * HMAC_SHA256 String variable that holds hashing algorithm name for HMAC_SHA256
	 */
	final String HMAC_SHA256 = "HmacSHA256";
	/**
	 * HMAC_SHA38 String variable that holds hashing algorithm name for HMAC_SHA384
	 */
	final String HMAC_SHA384 = "HmacSHA384";

	/**
	 * Helper function that convert byte array to hex string
	 * 
	 * @param bytes array of bytes to be converted to HEX String
	 * @return String converted String that represents HEX notation of byte array
	 */
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

	/**
	 * Helper function that hashes a String of data using HMAC_256 algorithm and
	 * secret API key.
	 * 
	 * @param data String data to be hashed
	 * @param key  String API key used as hashing key by the algorithm
	 * @return call to bytesToHex function to convert hashed byte array into String
	 */
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

	/**
	 * Helper function that encodes a String payload using Base64 algorithm
	 * 
	 * @param payload String data to be encoded
	 * @return array of bytes resulting from the enconding process of the String
	 *         payload
	 */
	public byte[] encodeBase64(String payload) throws UnsupportedEncodingException {
		byte[] encodeMe = payload.getBytes("UTF-8");
		byte[] encodedBytes = Base64.getEncoder().encode(encodeMe);
		return encodedBytes;
	}

	/**
	 * Helper function that hashes an array of bytes of data using HMAC_384
	 * algorithm and secret API key.
	 * 
	 * @param data array of bytes of data to be hashed
	 * @param key  String API key used as hashing key by the algorithm
	 * @return String representation of the hashed byte array
	 */
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