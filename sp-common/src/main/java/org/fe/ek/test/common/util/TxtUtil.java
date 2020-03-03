package org.fe.ek.test.common.util;

import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;

import javax.validation.constraints.NotEmpty;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @program: SimpleProj
 * @description: txt util
 * @author: Wang Zhenhua
 * @create: 2020-03-03
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-03
 **/
public class TxtUtil {

    /**
     * read all string in the file
     * @param filePath
     * @return
     */
    public static String readAll(@NotEmpty String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"can not read txt file with error: " + ex.getMessage());
        }
    }

    /**
     * read one line of the txt
     * @param filePath
     * @param lineNumber
     * @return
     */
    public static String readLine(@NotEmpty String filePath, int lineNumber) {
        try {
            if (lineNumber < 0) { // || lineNumber > txtLineNumber(filePath)
                throw new CmException(CmErrCode.E100000, "parameter lineNumber error");
            }
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            ) {
                for (int i = 0; i < lineNumber; i++) {
                    reader.readLine();
                }
                return reader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"can not read txt file with error: " + ex.getMessage());
        }
    }

    /**
     * get line number of the file
     * @param filePath
     * @return
     */
    public static int lineNumber(@NotEmpty String filePath) {
        try {
            try (
                    LineNumberReader lnReader = new LineNumberReader(new InputStreamReader(new FileInputStream(filePath)));
            ) {
                lnReader.skip(Long.MAX_VALUE);
                return lnReader.getLineNumber() + 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"can not read txt file with error: " + ex.getMessage());
        }
    }

    public static void createAndWrite(@NotEmpty String filePath, String content) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter writer = new FileWriter(file);
            ) {
                writer.write(content);
                writer.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"can not write txt file with error: " + ex.getMessage());
        }
    }
}
