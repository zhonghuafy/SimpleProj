package org.fe.ek.test.proj.service.pdf.basic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.fe.ek.test.common.util.FontUtil;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @program: SimpleProj
 * @description: pdf服务基类
 * @author: Wang Zhenhua
 * @create: 2019-11-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-11-06
 **/
@Slf4j
public abstract class BasicPdfSrv {

    protected final float PADDINGTOP = 2;

    protected final float CELL_FIXED_HEIGHT = 30;

    protected final float CELL_CONTENT_FIXED_HEIGHT = 100;

    protected final Integer CONTENT_FONT_SIZE =12;

    protected final Integer TITLE_FONT_SIZE = 22;

    protected final Integer FOOTER_FONT_SIZE = 10;

    protected final float BORDER = 0.4f;

    protected BaseFont baseFont;

    /**
     * 创建pdf
     * @param object
     * @return
     */
    public byte[] createPdf(@NotNull Object object) {
        if (object == null) {
            return new byte[0];
        }
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            initial(object);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            titleImage(document);
            titleInfo(document);
            basicInfo(document);
            detailInfo(document);
            footerInfo(document);
            document.close();
            writer.close();

            return outputStream.toByteArray();
        } catch (Exception ex) {
            log.error("error to create pdf! {}", ex.getMessage());
            ex.printStackTrace();
            throw new CmException(CmErrCode.E100000,"生成pdf异常！");
        }
    }

    /**
     * 初始化
     * @throws IOException
     * @throws DocumentException
     */
    protected void initial(Object object) throws IOException, DocumentException {
        baseFont = createFont_HN(FontUtil.getSimSun());
        extentInitial(object);
    }

    /**
     * 供子类实现的初始化
     */
    protected abstract void extentInitial(Object object) throws IOException, DocumentException;

    /**
     * 页面最上方的标题图
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void titleImage(Document document) throws IOException, DocumentException;

    /**
     * 标题信息
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void titleInfo(Document document) throws IOException, DocumentException;

    /**
     * 标题下方正文上方的基础信息
     *
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void basicInfo(Document document) throws IOException, DocumentException;

    /**
     * 正文详情
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void detailInfo(Document document) throws IOException, DocumentException;

    /**
     * 页脚信息
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void footerInfo(Document document) throws IOException, DocumentException;

    /**
     * 添加指定数量的空行
     * @param document
     * @param number
     * @throws DocumentException
     */
    protected void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(Chunk.NEWLINE);
        }
    }

    /**
     * 创建字体: horizontal writing, not embedded
     * @param name
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    protected BaseFont createFont_HN(@NotNull String name) throws IOException, DocumentException {
        return BaseFont.createFont(name + ",1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
    }

    protected static String nvl(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    protected PdfPCell createCell(String content, Font font) {
        return createCell(content, font, Element.ALIGN_LEFT, PADDINGTOP);
    }

    /**
     * 创建单元格
     * @param content
     * @param font
     * @param align
     * @param paddingTop
     * @return
     */
    protected PdfPCell createCell(String content, Font font, int align, float paddingTop) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(paddingTop);
        cell.setBorderWidth(0);
        cell.setFixedHeight(CELL_FIXED_HEIGHT);
        return cell;
    }

    /**
     * 创建放置内容的单元格。高度固定为默认值
     * @param title
     * @param content
     * @param colspan
     * @return
     */
    protected PdfPCell createContentCell(String title, String content, int colspan) {
        PdfPCell conCell = new PdfPCell();
        conCell.setColspan(colspan);
        conCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        conCell.setVerticalAlignment(Element.ALIGN_TOP);
        conCell.setPadding(PADDINGTOP);
        conCell.setFixedHeight(CELL_CONTENT_FIXED_HEIGHT);
        Paragraph titlePgh = new Paragraph(title, new Font(baseFont, CONTENT_FONT_SIZE, Font.BOLD));
        titlePgh.setAlignment(Element.ALIGN_LEFT);
        conCell.addElement(titlePgh);

        Paragraph conPgh = new Paragraph(content, new Font(baseFont, CONTENT_FONT_SIZE, Font.NORMAL));
        conPgh.setAlignment(Element.ALIGN_LEFT);
        conCell.addElement(conPgh);

        return conCell;
    }

    /**
     * 在表格中创建一个签名图像
     * @param title 签名单元格中在签名图像签名放置一个标题
     * @param signature 图像地址
     * @param colspan 合并的列单元格
     * @return
     */
    protected PdfPCell createSignCell(String title, String signature, int colspan) {
        PdfPCell signCell = new PdfPCell();
        signCell.setColspan(colspan);
        signCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        signCell.setVerticalAlignment(Element.ALIGN_TOP);
        signCell.setPadding(PADDINGTOP);
        signCell.setFixedHeight(CELL_CONTENT_FIXED_HEIGHT);
        if (!StringUtils.isEmpty(title)) {
            Paragraph titlePgh = new Paragraph(title, new Font(baseFont, CONTENT_FONT_SIZE, Font.BOLD));
            titlePgh.setAlignment(Element.ALIGN_LEFT);
            signCell.addElement(titlePgh);
        }
        if (!StringUtils.isEmpty(signature)) {
            Image image = createImage(signature,100,30);
            if (image != null) {
                signCell.addElement(image);
            }
        }
        return  signCell;
    }

    /**
     * 创建指定大小的图像
     * @param imgUrl
     * @param fitWidth
     * @param fitHeight
     * @return
     */
    protected Image createImage(String imgUrl, float fitWidth, float fitHeight) {
        if (StringUtils.isEmpty(imgUrl)) {
            return null;
        }
        try {
            Image image = Image.getInstance(imgUrl);
            image.scaleToFit(fitWidth,fitHeight);
            return image;
        }catch (Exception ex) {
            log.error("error to create image, {}: {}",imgUrl,ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 基础的标题图实现
     * 图片居左，占用单独段落，图片下方一个空行
     * @param document
     * @param imgUrl
     * @param fitWidth
     * @param fitHeight
     * @throws IOException
     * @throws DocumentException
     */
    protected void titleImage(Document document, String imgUrl, float fitWidth, float fitHeight) throws IOException, DocumentException {
        Image image = createImage(imgUrl,fitWidth,fitHeight);
        if (image != null) {
            Paragraph hosImg = new Paragraph();
            hosImg.setAlignment(Element.ALIGN_LEFT);
            hosImg.add(image);
            document.add(hosImg);
            addEmptyLine(document,1);
        }
        image = null;
    }

}
