package helpers;

import java.util.Random;

public class Server {
	public String randString(String id, int length) {
		String charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Str = id + "_";
		Random rand = new Random();
		for(int i = 0; i < length; i++) {
			Str += charSet.toCharArray()[rand.nextInt(charSet.length())];
		}
		return Str;
	}
}
