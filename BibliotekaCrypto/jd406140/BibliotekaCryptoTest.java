package pl.edu.mimuw.crypto.jd406140;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class BibliotekaCryptoTest {

    private BibliotekaCrypto biblioteka;

    @Before
    public void setUp () {

        biblioteka = new BibliotekaCrypto();
    }

    @Test(expected = InvalidKeySpecException.class)
    public void probujStworzycKluczOTPZeZlaDlugoscia1 () throws InvalidKeySpecException {

        biblioteka.generujKluczOTP(0);
    }

    @Test(expected = InvalidKeySpecException.class)
    public void probujStworzycKluczOTPZeZlaDlugoscia2 () throws InvalidKeySpecException {

        biblioteka.generujKluczOTP(-55);
    }

    @Test(expected = InvalidKeySpecException.class)
    public void probujStworzycKluczAESZeZlaDlugoscia1 () throws InvalidKeySpecException {

        biblioteka.generujKluczAES(0);
    }

    @Test(expected = InvalidKeySpecException.class)
    public void probujStworzycKluczAESZeZlaDlugoscia2 () throws InvalidKeySpecException {

        biblioteka.generujKluczAES(-12);
    }

    @Test(expected = InvalidKeySpecException.class)
    public void probujStworzycKluczAESZeZlaDlugoscia3 () throws InvalidKeySpecException {

        biblioteka.generujKluczAES(1024);
    }

    @Test
    public void sprobujZmienicStringaNaBityIZPowrotem () {

        String tekstPrzedKonwersja = "123aSdfgh!@#$%  ";

        byte[] tekstWBitach = BibliotekaCrypto.konwertujStringNaTabliceBitow(tekstPrzedKonwersja);

        String tekstPoKonwersji = BibliotekaCrypto.konwertujTabliceBitowNaString(tekstWBitach);

        assertEquals(tekstPrzedKonwersja, tekstPoKonwersji);
    }

    @Test
    public  void sprubujZaszyfrowacStringaOTPIOdszyfrowac1 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException {

        String tekst = "q";

        SecretKey kluczOTP = biblioteka.generujKluczOTP(tekst.length() * 16);

        byte[] tekstZaszyfrowanyOTP = biblioteka.szyfrujOTP(tekst, kluczOTP);

        String tekstOdszyfrowany = biblioteka.deszyfrujOTPJakoNapis(tekstZaszyfrowanyOTP, kluczOTP);

        assertEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacStringaOTPIOdszyfrowac2 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException {

        String tekst = "qwerty123456 L!@#";

        SecretKey kluczOTP = biblioteka.generujKluczOTP(tekst.length() + 6789);

        byte[] tekstZaszyfrowanyOTP = biblioteka.szyfrujOTP(tekst, kluczOTP);

        String tekstOdszyfrowany = biblioteka.deszyfrujOTPJakoNapis(tekstZaszyfrowanyOTP, kluczOTP);

        assertEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacStringaOTPIOdszyfrowac3 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException {

        String tekst = "qwerty123456 L!@#";

        SecretKey kluczOTP = biblioteka.generujKluczOTP(tekst.length() * 16);

        byte[] tekstZaszyfrowanyOTP = biblioteka.szyfrujOTP(tekst, kluczOTP);

        String tekstOdszyfrowany = biblioteka.deszyfrujOTPJakoNapis(tekstZaszyfrowanyOTP, kluczOTP);

        assertEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacTabliceBitowOTPIOdszyfrowac1 () throws InvalidKeySpecException,
            NoSuchAlgorithmException, InvalidKeyException {

        byte [] tekst = {0};

        SecretKey kluczOTP = biblioteka.generujKluczOTP(tekst.length);

        byte[] tekstZaszyfrowanyOTP = biblioteka.szyfrujOTP(tekst, kluczOTP);

        byte[] tekstOdszyfrowany = biblioteka.deszyfrujOTP(tekstZaszyfrowanyOTP, kluczOTP);

        assertArrayEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacTabliceBitowOTPIOdszyfrowac2 () throws InvalidKeySpecException,
            NoSuchAlgorithmException, InvalidKeyException {

        byte [] tekst = {0, 1, 0, 0, 1,};

        SecretKey kluczOTP = biblioteka.generujKluczOTP(tekst.length);

        byte[] tekstZaszyfrowanyOTP = biblioteka.szyfrujOTP(tekst, kluczOTP);

        byte[] tekstOdszyfrowany = biblioteka.deszyfrujOTP(tekstZaszyfrowanyOTP, kluczOTP);

        assertArrayEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacTabliceBitowAESIOdszyfrowac1 () throws InvalidKeySpecException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        byte [] tekst = {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1,
                0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
                0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
        };

        SecretKey kluczAES = biblioteka.generujKluczAES(128);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        byte[] tekstOdszyfrowany = biblioteka.deszyfrujAES(tekstZaszyfrowanyAES, kluczAES);

        assertArrayEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacTabliceBitowAESIOdszyfrowac2 () throws InvalidKeySpecException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        byte [] tekst = {0, 1, 0, 0, 1,};

        SecretKey kluczAES = biblioteka.generujKluczAES(192);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        byte[] tekstOdszyfrowany = biblioteka.deszyfrujAES(tekstZaszyfrowanyAES, kluczAES);

        assertArrayEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacTabliceBitowAESIOdszyfrowac3 () throws InvalidKeySpecException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        byte [] tekst = {0, 1, 0, 0, 1,};

        SecretKey kluczAES = biblioteka.generujKluczAES(256);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        byte[] tekstOdszyfrowany = biblioteka.deszyfrujAES(tekstZaszyfrowanyAES, kluczAES);

        assertArrayEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacStringaAESIOdszyfrowac1 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        String tekst = "qwerty123456 L!@#";

        SecretKey kluczAES = biblioteka.generujKluczAES(128);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        String tekstOdszyfrowany = biblioteka.deszyfrujAESJakoNapis(tekstZaszyfrowanyAES, kluczAES);

        assertEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacStringaAESIOdszyfrowac2 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        String tekst = "q";

        SecretKey kluczAES = biblioteka.generujKluczAES(192);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        String tekstOdszyfrowany = biblioteka.deszyfrujAESJakoNapis(tekstZaszyfrowanyAES, kluczAES);

        assertEquals(tekst, tekstOdszyfrowany);
    }

    @Test
    public  void sprubujZaszyfrowacStringaAESIOdszyfrowac3 () throws InvalidKeySpecException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        String tekst = "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L" +
                "qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L!@#qwerty123456 L";

        SecretKey kluczAES = biblioteka.generujKluczAES(256);

        byte[] tekstZaszyfrowanyAES = biblioteka.szyfrujAES(tekst, kluczAES);

        String tekstOdszyfrowany = biblioteka.deszyfrujAESJakoNapis(tekstZaszyfrowanyAES, kluczAES);

        assertEquals(tekst, tekstOdszyfrowany);
    }


}