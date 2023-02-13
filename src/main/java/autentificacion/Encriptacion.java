package autentificacion;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Encriptacion {
    private static final String ALGORITMO = "AES";

    public static byte[] generateAESKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return key;
    }
    private static final byte[] CLAVE_SECRETA = generateAESKey();


    public static String encriptar(String mensaje) throws Exception{
        Key key = new SecretKeySpec(CLAVE_SECRETA, ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encriptado = cipher.doFinal(mensaje.getBytes());

        return Base64.getEncoder().encodeToString(encriptado);
    }
}