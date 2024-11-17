package RSAtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private static final Logger log = LogManager.getLogger(RSA.class);

    private static BigInteger n;
    private static BigInteger e;
    private static BigInteger d;

    public RSA() {

        //generazione di due numeri primi grandi
        SecureRandom rand = new SecureRandom();
        int bitLenght = 1024;
        BigInteger p = BigInteger.probablePrime(bitLenght / 2, rand);
        log.info("Generated prime number p: {}", p);
        BigInteger q = BigInteger.probablePrime(bitLenght / 2, rand);
        log.info("Generated prime number q: {}", q);

        //prodotto p*q
        n = p.multiply(q);
        log.info("Generated n: {}", n);

        //funzione di Eulero (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        log.info("Generated ϕ(n): {}", phi);

        //esponente pubblico
        do e = new BigInteger(phi.bitLength(), rand);
        while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE)) ;
        log.info("Generated e: {}", e);

        //esponente privato
        d = e.modInverse(phi);
        log.info("Generated d: {}", d);
    }

    public BigInteger encryption(String message) {
        BigInteger crypted = new BigInteger(message.getBytes()).modPow(e, n);
        return crypted;
    }

    public BigInteger decryption(BigInteger message) {
        return message.modPow(d, n);
    }

    public String decryptionToString(BigInteger crypted) {
        return new String(crypted.modPow(d, n).toByteArray());
    }

}
