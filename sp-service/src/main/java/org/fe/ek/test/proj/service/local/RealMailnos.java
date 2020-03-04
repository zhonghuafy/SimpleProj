package org.fe.ek.test.proj.service.local;

import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: SimpleProj
 * @description: read txt file and return data in the file
 * @author: Wang Zhenhua
 * @create: 2020-03-04
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-04
 **/
class RealMailnos {

    /**
     * the current line number that indicates where to read.
     */
    private static volatile AtomicInteger current = new AtomicInteger();

    private static String path = "src\\main\\resources\\mailnos.txt";

    /**
     * read a batch data (number of BoPortraitGenerator.PACKAGE_ITEM_SIZE lines).
     * if the cursor moves to the end of file, cursor will back to the front and re-read from the first line.
     * @return
     */
    public static List<String> generateMailnoBatch() {
        try {
            int size = fileSize();
            List<String> list = new LinkedList<>();
            try (RandomAccessFile reader = new RandomAccessFile(new File(path), "r")) {
                synchronized (RealMailnos.class) {
                    int lineNumber = current.getAndIncrement();
                    moveCursor(reader, lineNumber);
                    for (int i = 0; i < BoPortraitGenerator.PACKAGE_ITEM_SIZE; i++) {
                        if (lineNumber >= size) {
                            lineNumber = 0;
                            reader.seek(0);
                        }
                        list.add(reader.readLine());
                        lineNumber++;
                    }
                    current.set(lineNumber);
                }
                return list;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"error to read txt file: " + ex.getMessage());
        }
    }

    /**
     * read one line from file
     * @return
     */
    public static String generateMailno() {
        try {
            int size = fileSize();
            current.compareAndSet(size, 0);
            int lineNumber = current.getAndIncrement();
            if (lineNumber < 0 || lineNumber > size) {
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));) {
                for (int i = 0; i < lineNumber; i++) {
                    reader.readLine();
                }
                return reader.readLine();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"error to read txt file: " + ex.getMessage());
        }
    }

    private static void moveCursor(RandomAccessFile reader, int lineNumber) throws IOException {
        for (int i = 0; i < lineNumber; i++) {
            reader.readLine();
        }
    }

    public static int fileSize() {
        try {
            try(LineNumberReader lnReader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)))) {
                lnReader.skip(Long.MAX_VALUE);
                return lnReader.getLineNumber() + 1;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"error to read txt file: " + ex.getMessage());
        }
    }
}
