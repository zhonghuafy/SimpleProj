package org.fe.ek.test.common.util;

import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @program: SimpleProj
 * @description: SHA256
 * @author: Wang Zhenhua
 * @create: 2019-10-24
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-10-24
 **/
public class SHA256 {

    /**
     * sha256加密
     * @param strText
     * @return
     */
    public static String encrypt(@NotNull String strText) {
        String strType = "SHA-256";
        String strResult = null;
        try
        {
            // SHA 加密开始
            // 创建加密对象 并傳入加密類型
            MessageDigest messageDigest = MessageDigest.getInstance(strType);
            // 传入要加密的字符串
            messageDigest.update(strText.getBytes());
            // 得到 byte 類型结果
            byte byteBuffer[] = messageDigest.digest();
            // 將 byte 轉換爲 string
            StringBuffer strHexString = new StringBuffer();
            // 遍歷 byte buffer
            for (int i = 0; i < byteBuffer.length; i++)
            {
                String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1)
                {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            // 得到返回結果
            strResult = strHexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return strResult;
    }

    /**
     * sha256加密并使用base64编码
     * @param strText
     * @return
     */
    public static String encryptWithBase64(@NotNull String strText) {
        return Base64.getEncoder().encodeToString(encrypt(strText).getBytes());
    }
}
