package org.fe.ek.test.proj.service.pdf.basic;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: SimpleProj
 * @description: 生成pdf时统一的响应头
 * @author: Wang Zhenhua
 * @create: 2019-11-13
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-11-13
 **/
public class PdfHttpHeader {

    private PdfHttpHeader() {}

    public static HttpHeaders setHeader(String filename) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        String pdfFileName = URLEncoder.encode(filename, "UTF-8");
        headers.add("Access-Control-Expose-Headers","Content-Disposition");
        headers.add("Content-Disposition", "attachment;filename=\"" + pdfFileName + "\"");
        headers.add("x-suggested-filename",pdfFileName);
        return headers;
    }
}
