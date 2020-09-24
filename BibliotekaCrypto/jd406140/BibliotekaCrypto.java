package pl.edu.mimuw.crypto.jd406140;

import pl.edu.mimuw.crypto.ISzyfrator;

import javax.crypto.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.IvParameterSpec;

public class BibliotekaCrypto implements ISzyfrator {

    @Override
    public SecretKey generujKluczOTP(int dlugosc) throws InvalidKeySpecException {

        return new OTPKey(dlugosc);
    }

    @Override
    public SecretKey generujKluczAES(int dlugosc) throws InvalidKeySpecException {

        try {

            if ((dlugosc != 128) && (dlugosc != 192) && (dlugosc != 256)) {

                throw new InvalidKeySpecException("zła długość");
            }

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(dlugosc);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;
        }
        catch (NoSuchAlgorithmException noSuchAlgo) {

            System.out.println(" Nie ma takiego algorytmu " + noSuchAlgo);
            throw new RuntimeException();
        }
    }

    @Override
    public byte[] szyfrujOTP(String tekst, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException {

        byte[] tekstWBitach = konwertujStringNaTabliceBitow(tekst);
        return szyfrujOTP(tekstWBitach, klucz);
    }

    @Override
    public byte[] szyfrujAES(String tekst, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException {

        byte[] tekstWBitach = konwertujStringNaTabliceBitow(tekst);
        return szyfrujAES(tekstWBitach, klucz);
    }

    @Override
    public byte[] szyfrujOTP(byte[] tekst, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException {

        if ((klucz instanceof OTPKey) && (!klucz.isDestroyed())){

            byte[] kluczWBitach = klucz.getEncoded();
            byte[] tekstZaszyfrowany = new byte[tekst.length];

            if (kluczWBitach.length < tekst.length) {

                throw new InvalidKeyException("za krótki klucz");
            }
            for (int indeks = 0; indeks < tekst.length; indeks++) {

                if ((tekst[indeks] != 0) && (tekst[indeks] != 1)) {

                    throw new NoSuchAlgorithmException("algorytm tylko dla bajtów 0 i 1");
                }

                if (tekst[indeks] == kluczWBitach[indeks]) {

                    tekstZaszyfrowany[indeks] = 0;
                }
                else {

                    tekstZaszyfrowany[indeks] = 1;
                }
            }
            return tekstZaszyfrowany;
        }
        else {

            throw new InvalidKeyException("zły klucz");
        }
    }

    @Override
    public byte[] szyfrujAES(byte[] tekst, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException {

        try {

            byte[] wektorInicjujacyIV = new byte[16];
            SecureRandom generatorLosowosci = new SecureRandom();
            generatorLosowosci.nextBytes(wektorInicjujacyIV);

            Cipher SzyfrAESDoSzyfrowania = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            SzyfrAESDoSzyfrowania.init(Cipher.ENCRYPT_MODE, klucz, new IvParameterSpec(wektorInicjujacyIV));

            byte[] tekstZaszyfrowanyAES = SzyfrAESDoSzyfrowania.doFinal(tekst);

            byte[] tekstZaszyfrowanyAESZWektorem = new byte[wektorInicjujacyIV.length + tekstZaszyfrowanyAES.length];

            for (int indeks = 0; indeks < (wektorInicjujacyIV.length + tekstZaszyfrowanyAES.length); indeks++) {

                if (indeks < wektorInicjujacyIV.length) {

                    tekstZaszyfrowanyAESZWektorem[indeks] = wektorInicjujacyIV[indeks];
                }
                else {

                    tekstZaszyfrowanyAESZWektorem[indeks] = tekstZaszyfrowanyAES[indeks - wektorInicjujacyIV.length];
                }
            }
            return tekstZaszyfrowanyAESZWektorem;
        }
        catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public byte[] deszyfrujOTP(byte[] szyfrogram, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException {

        return szyfrujOTP(szyfrogram, klucz);
    }

    @Override
    public byte[] deszyfrujAES(byte[] szyfrogram, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, BadPaddingException {

        try {

            byte[] wektorInicjujacyIV = new byte[16];
            byte[] szyfrogramBezWektora = new byte[szyfrogram.length - wektorInicjujacyIV.length];

            for (int indeks = 0; indeks < szyfrogram.length; indeks++) {

                if (indeks < wektorInicjujacyIV.length) {

                    wektorInicjujacyIV[indeks] = szyfrogram[indeks];
                }
                else {
                    szyfrogramBezWektora[indeks - wektorInicjujacyIV.length] = szyfrogram[indeks];
                }
            }

            Cipher SzyfrAESDoDeszyfrowania = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            SzyfrAESDoDeszyfrowania.init(Cipher.DECRYPT_MODE, klucz, new IvParameterSpec(wektorInicjujacyIV));

            return SzyfrAESDoDeszyfrowania.doFinal(szyfrogramBezWektora);
        }
        catch (InvalidAlgorithmParameterException | IllegalBlockSizeException e) {

            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public String deszyfrujOTPJakoNapis(byte[] szyfrogram, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException {

        byte[] tekstOdszyfrowany = szyfrujOTP(szyfrogram, klucz);
        return konwertujTabliceBitowNaString(tekstOdszyfrowany);
    }

    @Override
    public String deszyfrujAESJakoNapis(byte[] szyfrogram, SecretKey klucz)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, BadPaddingException {

        byte[] tekstOdszyfrowany = deszyfrujAES(szyfrogram, klucz);
        return konwertujTabliceBitowNaString(tekstOdszyfrowany);

    }

    public static byte[] konwertujStringNaTabliceBitow (String tekst) {

        char[] tekstWTablicyZnakow = tekst.toCharArray();
        byte[] tekstWBitach = new byte[(tekstWTablicyZnakow.length) * 16];
        int indeks = 0;

        for (char znak : tekstWTablicyZnakow) {

            int znakPomocniczy = znak;

            for (int i = 0; i < 16; i++, indeks++) {

                if (znakPomocniczy >= 32768) { // 32768 = 2^15
                    tekstWBitach[indeks] = 1;
                } else {
                    tekstWBitach[indeks] = 0;
                }

                znakPomocniczy %= 32768; // 32768 = 2^15
                znakPomocniczy *= 2;
            }
        }
        return tekstWBitach;
    }

    public static String konwertujTabliceBitowNaString (byte[] tekstWBitach) {

        int indeks = 0;
        StringBuilder tekst = new StringBuilder();

        while (indeks < tekstWBitach.length) {

            char znakPomocniczy = 0;

            for (int i = 0; i < 16; i++, indeks++) {

                znakPomocniczy *= 2;

                if ((indeks < tekstWBitach.length) && (tekstWBitach[indeks] == 1)) {
                    znakPomocniczy++;
                }
            }
            tekst.append(znakPomocniczy);
        }
        return tekst.toString();
    }

}
