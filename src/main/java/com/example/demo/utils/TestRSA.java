package com.example.demo.utils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class TestRSA {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, Base64DecodingException {
        Map<String, String> keyMap = RSAUtils.createKeys(512);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("生成公钥: \n\r" + publicKey);
        System.out.println("生成私钥： \n\r" + privateKey);

        String publicKey2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2ONg8dKOo4tFufQz6//jxQ9cr\n" +
                "N/Qpp2SOAAr0sIXuWnLBhOv9XD9QnVNraA0ESZ2DFtlUhAQ9uAqAsWsLbx8gyKme\n" +
                "Dp/0B2/TKjae9LDWn8aqQPqb/MSW1iDoh0YpJy+C92fHkeagwqTdKSUPDy4Pb1p3\n" +
                "sKXuIPbI541uEFw3nwIDAQAB";
        String privateKey2 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALY42Dx0o6ji0W59\n" +
                "DPr/+PFD1ys39CmnZI4ACvSwhe5acsGE6/1cP1CdU2toDQRJnYMW2VSEBD24CoCx\n" +
                "awtvHyDIqZ4On/QHb9MqNp70sNafxqpA+pv8xJbWIOiHRiknL4L3Z8eR5qDCpN0p\n" +
                "JQ8PLg9vWnewpe4g9sjnjW4QXDefAgMBAAECgYAKk6oZeDu3pdK6Mw/50bnFZ0ap\n" +
                "/4lwkYLIqz/3i/d6Kk3W5bw87InQSBF7foKi6RL3dPAQc8k7O9+AMMwh0cVgrLBB\n" +
                "bbtsDquJImof61fPxbeOQg+i/qQwJwPu94kH2+mEv3FDi2v2+YIRgI27Hb5hiZCV\n" +
                "GP/tpI09ApMfcDYC4QJBAN18zljIuBbF1CFwL+TpUP6zNL02xowimWDM8wqn6lBu\n" +
                "1kkb1KaW0OCPyX+VhpiGe7ATR9nH1AHtYWnR1SxMVPMCQQDSnbqUZ2ZU1Iqc4TIg\n" +
                "N9V50cc9YPhICj92qwsOyeUiklRSXg2F3CsKHxp8Hn7O2SgTH2/M1qfzxbt9uU7o\n" +
                "im2lAkA55GdjGYPUn/zkiJhcMwfyqxC77FdVRa3leWyFeoJqZ64nCdk5ee465Bp8\n" +
                "+K5nq68kxMVhDTRDFLMDn88idwk1AkEAwcuulcU67M2RKd8w72JQxMHZUcn7StZb\n" +
                "JQcnqpjthM0eV5gBW22GEM8j4Jdy2UQJ0ua3ScUuT2lfJsUvLVvKnQJBAM0G7doq\n" +
                "hyg0L8L4f9d0UAquGJAi/WTvSjBuvLPeXs04JHuic3M0kxfzI5fUelSNU+vtjJRS\n" +
                "wdZ52ofEuQPD6+A=";
        System.out.println("公钥: \n\r" + publicKey2);
        System.out.println("私钥： \n\r" + privateKey2);

        System.out.println("公钥加密——私钥解密");
        String str = "zsy";
        System.out.println("\r明文：\r\n" + str);
        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println("密文：\r\n" + encodedData);
        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);
    }
}
