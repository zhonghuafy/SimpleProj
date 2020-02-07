package org.fe.ek.test.model.util;

/**
 * @program: SimpleProj
 * @description: CodecUtil
 * @author: Wang Zhenhua
 * @create: 2020-02-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-06
 **/
public class CodecUtil {
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String encodeHexString(String key16, String str) {
        try {
            return bytesToHexString(rc4(key16, str.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String decodeHexString(String key16, String str) {
        try {
            return new String(rc4(key16, hexStringToBytes(str)));
        } catch (Exception e) {
            return null;
        }
    }

//    public static String encodeBase64(String key64, byte[] buf) throws Exception{
//        return new BASE64Encoder().encodeBuffer(rc4(key64, buf)).replace("\n", "").replace("\r", "");
//
//    }
//
//    public static String encodeBase64(byte[] buf) {
//        return new BASE64Encoder().encodeBuffer(buf).replace("\n", "").replace("\r", "");
//    }
//
//    public static byte[] decodeBase64(String key64, String str) throws Exception {
//        return rc4(key64, new BASE64Decoder().decodeBuffer(str));
//    }
//
//    public static byte[] decodeBase64(String str) throws Exception {
//        return new BASE64Decoder().decodeBuffer(str);
//    }

    public static byte[] rc4(String key, byte[] buf) throws Exception {
        int[] box = new int[256];
        byte[] k = key.getBytes();
        int i = 0, x = 0, t = 0, l = k.length;

        for (i = 0; i < 256; i++) {
            box[i] = i;
        }

        for (i = 0; i < 256; i++) {
            x = (x + box[i] + k[i % l]) % 256;

            t = box[x];
            box[x] = box[i];
            box[i] = t;
        }

        t = 0;
        i = 0;
        l = buf.length;
        int o = 0, j = 0;
        byte[] out = new byte[l];
        int[] ibox = new int[256];
        System.arraycopy(box, 0, ibox, 0, 256);

        for (int c = 0; c < l; c++) {
            i = (i + 1) % 256;
            j = (j + ibox[i]) % 256;

            t = ibox[j];
            ibox[j] = ibox[i];
            ibox[i] = t;

            o = ibox[(ibox[i] + ibox[j]) % 256];
            out[c] = (byte) (buf[c] ^ o);
        }
        return out;
    }
}
