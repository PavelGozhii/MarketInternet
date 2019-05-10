package service;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CodeGenerator {
    private static final Random random = new Random();
    private static final Logger logger = Logger.getLogger(CodeGenerator.class);

    public static int generateCode() {
        int limit = 9999;
        int randomNumber = random.nextInt(limit);
        logger.info("Generating Code");
        return randomNumber;
    }

    public static String generateId(String str) {
        str = str.replace(" ", "");
        logger.info("Generating Id");
        return str.substring(0, 4) + generateCode();
    }

    public static String getSHA512SecurePsssword(String passwordToHash) {
        String generatedPassword = null;
        String salt = "123";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            logger.debug("Hash was created");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Not such algorithm exception");
        }
        return generatedPassword;
    }
}
