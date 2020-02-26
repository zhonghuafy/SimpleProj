package org.fe.ek.test.proj.service.pdf.fsexp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.util.DateFormatUtil;
import org.fe.ek.test.proj.service.pdf.basic.BasicPdfSrv;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;

/**
 * @program: SimpleProj
 * @description: an example to create a pdf file
 * @author: Wang Zhenhua
 * @create: 2019-11-01
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-11-01
 **/
@Service
@Slf4j
public class ReportPdfSrv extends BasicPdfSrv {

    private final String FOOTER_TEXT = "远程诊疗是异地医疗咨询活动，会诊医师与申请会诊医师" +
            "之间的关系属于医学知识的咨询关系，对病人的诊断与治疗的决定权属于收治病人的医疗机构" +
            "（引自原卫生部卫办发【1999】第2号）。";

    /**
     * 子类的初始化
     */
    @Override
    protected void extentInitial(Object object) throws IOException, DocumentException {
    }

    @Override
    protected void titleImage(Document document) throws IOException, DocumentException {
        super.titleImage(document,"http://a-image-url",200,50);
    }

    @Override
    protected void titleInfo(Document document) throws IOException, DocumentException {
        Paragraph hosTitle = new Paragraph("title", new Font(baseFont, TITLE_FONT_SIZE, Font.BOLD));
        hosTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(hosTitle);

        Paragraph secTitle = new Paragraph(secTitle(), new Font(baseFont, TITLE_FONT_SIZE, Font.BOLD));
        secTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(secTitle);
        addEmptyLine(document,1);
    }

    @Override
    protected void basicInfo(Document document) throws IOException, DocumentException {
        StringBuilder code = new StringBuilder("编号：");
        code.append("a code");
        Paragraph basic = new Paragraph(code.toString(),new Font(baseFont, CONTENT_FONT_SIZE, Font.NORMAL));
        basic.setAlignment(Element.ALIGN_RIGHT);
        document.add(basic);
    }

    @Override
    protected void detailInfo(Document document) throws IOException, DocumentException {
        Font boldFont = new Font(baseFont, CONTENT_FONT_SIZE, Font.BOLD);
        Font font = new Font(baseFont, CONTENT_FONT_SIZE, Font.NORMAL);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        PdfPCell cell11 = createCell("申请医院：",boldFont);
        cell11.setBorderWidth(BORDER);
        table.addCell(cell11);

        PdfPCell cell12 = createCell("example",font);
        cell12.setBorderWidth(BORDER);
        cell12.setColspan(2);
        table.addCell(cell12);

        PdfPCell cell13 = createCell("申请科室：",boldFont);
        cell13.setBorderWidth(BORDER);
        table.addCell(cell13);

        PdfPCell cell14 = createCell("example",font);
        cell14.setBorderWidth(BORDER);
        cell14.setColspan(2);
        table.addCell(cell14);

        PdfPCell cell21 = createCell("会诊日期：",boldFont);
        cell21.setBorderWidth(BORDER);
        table.addCell(cell21);

        PdfPCell cell22 = createCell(DateFormatUtil.formatDate(new Date()),font);
        cell22.setBorderWidth(BORDER);
        cell22.setColspan(2);
        table.addCell(cell22);

        PdfPCell cell23 = createCell("会诊专家：",boldFont);
        cell23.setBorderWidth(BORDER);
        table.addCell(cell23);

        PdfPCell cell24 = createCell("example",font);
        cell24.setBorderWidth(BORDER);
        cell24.setColspan(2);
        table.addCell(cell24);

        PdfPCell cell31 = createCell("专家职称：",boldFont);
        cell31.setBorderWidth(BORDER);
        table.addCell(cell31);

        PdfPCell cell32 = createCell("example",font);
        cell32.setBorderWidth(BORDER);
        cell32.setColspan(2);
        table.addCell(cell32);

        PdfPCell cell33 = createCell("会诊科室：",boldFont);
        cell33.setBorderWidth(BORDER);
        table.addCell(cell33);

        PdfPCell cell34 = createCell("example",font);
        cell34.setBorderWidth(BORDER);
        cell34.setColspan(2);
        table.addCell(cell34);

        PdfPCell cell41 = createCell("患者姓名：",boldFont);
        cell41.setBorderWidth(BORDER);
        table.addCell(cell41);

        PdfPCell cell42 = createCell("example",font);
        cell42.setBorderWidth(BORDER);
        table.addCell(cell42);

        PdfPCell cell43 = createCell("性别：",boldFont);
        cell43.setBorderWidth(BORDER);
        table.addCell(cell43);

        PdfPCell cell44 = createCell("example",font);
        cell44.setBorderWidth(BORDER);
        table.addCell(cell44);

        PdfPCell cell45 = createCell("年龄：",boldFont);
        cell45.setBorderWidth(BORDER);
        table.addCell(cell45);

        PdfPCell cell46 = createCell("example",font);
        cell46.setBorderWidth(BORDER);
        table.addCell(cell46);

        PdfPCell diagCell = createContentCell("专家咨询诊断参考意见：","example",6);
        diagCell.setBorderWidth(BORDER);
        table.addCell(diagCell);

        PdfPCell trmtCell = createContentCell("专家咨询治疗建议：","example",6);
        trmtCell.setBorderWidth(BORDER);
        table.addCell(trmtCell);

        PdfPCell signCell = createSignCell("会诊医师签字：","example",6);
        signCell.setBorderWidth(BORDER);
        table.addCell(signCell);

        document.add(table);
    }

    @Override
    protected void footerInfo(Document document) throws IOException, DocumentException {
        addEmptyLine(document,1);
        Paragraph footer = new Paragraph();
        footer.setAlignment(Element.ALIGN_LEFT);

        Phrase title = new Phrase("注：",new Font(baseFont,FOOTER_FONT_SIZE,Font.BOLD));
        footer.add(title);
        Phrase text = new Phrase(FOOTER_TEXT,new Font(baseFont,FOOTER_FONT_SIZE,Font.NORMAL));
        footer.add(text);
        document.add(footer);
    }

    private String secTitle() {
        return "专家会诊报告";
    }

}
