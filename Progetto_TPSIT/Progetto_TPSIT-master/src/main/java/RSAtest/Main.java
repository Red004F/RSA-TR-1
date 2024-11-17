package RSAtest;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    private static final RSA es = new RSA();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        BigInteger crypted;
        String pick;

        do {
            System.out.println("[1] Encrypt message" + "\n[2] Decrypt message" + "\n[0] Exit program");
            pick = scanner.nextLine();

            String message;
            if (pick.equals("1")) {
                log.info("String to encrypt: ");
                message = scanner.nextLine();

                crypted = es.encryption(message);

                log.info("Encrypted string: {}", crypted);

            } else if (pick.equals("2")) {
                log.info("String to decrypt: ");
                crypted = scanner.nextBigInteger();

                String decrypted = es.decryptionToString(crypted);

                log.info("Decrypted string: {}", decrypted);
            }

        } while (!pick.equals("0"));


    }
}