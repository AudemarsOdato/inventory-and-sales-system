package utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// must be used only in the forntend
public class JCrypt {
        // generating salt
        // adding salt to hashed password
        // hash password
        public String hash(String password) {
                try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

                        StringBuilder hexString = new StringBuilder();
                        for (byte bit : hashBytes) {
                                String hex = Integer.toHexString(0xff & bit);
                                if (hex.length() == 1) {
                                        hexString.append('0');
                                }
                                hexString.append(hex);
                        }
                        return hexString.toString();
                }
                catch (NoSuchAlgorithmException error) {
                        error.printStackTrace();
                }

                return "";
        }

        public boolean isMatch(String password, String hashed) {
                return hashed.equals(hash(password));
        }
}