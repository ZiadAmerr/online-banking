
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Helpers {
    public static String getHash(String input) {
        // This function was obtained from:
        // https://www.geeksforgeeks.org/md5-hash-in-java/
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputBytes = input.getBytes();
            byte[] hashBytes = md.digest(inputBytes);
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : hashBytes) {
                sb.append(String.format("%02x", hashByte));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
