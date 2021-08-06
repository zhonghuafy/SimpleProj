package org.fe.ek.test.common.util;

import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.springframework.core.io.ClassPathResource;

import javax.validation.constraints.NotEmpty;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: SimpleProj
 * @description: txt util
 * @author: Wang Zhenhua
 * @create: 2020-03-03
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-03
 **/
public class TxtUtil {

    /**
     * 从指定文件从 start 行开始读取 readSize 行数的数据。当达到最后一行时，返回第一行继续读取
     * @param fileName 文件名
     * @param start 从该行开始读取
     * @param readSize 读取readSize行数的数据
     * @return
     */
    public static List<String> readGivenLinesGoBackReachEnd(String fileName, int start, int readSize) {
        RandomAccessFile randomReader = null;
        try {
            int fileSize = lineNumber(fileName);
            List<String> list = new LinkedList<>();
            randomReader = new RandomAccessFile(new File(fileName), "r");
            int lineNumber = start;
            moveCursor(randomReader, lineNumber);
            for (int i = 0; i < readSize; i++) {
                if (lineNumber >= fileSize) {
                    lineNumber = 0;
                    randomReader.seek(0);
                }
                list.add(randomReader.readLine());
                lineNumber++;
            }
            //current cursor at lineNumber
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (randomReader != null) {
                try {
                    randomReader.close();
                }catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private static void moveCursor(RandomAccessFile reader, int lineNumber) throws IOException {
        for (int i = 0; i < lineNumber; i++) {
            reader.readLine();
        }
    }

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
                    //ClassPathResource: resource目录下文件直接写文件名
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()));
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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
