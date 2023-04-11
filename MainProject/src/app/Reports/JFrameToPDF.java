package app.Reports;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class JFrameToPDF {

    public JFrameToPDF(){}

    public void makePDF(JPanel panel){
        try {
            // Create a new PDF document
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("panel.pdf"));
            document.open();

            // Add a new page to the document
            writer.setPageSize(document.getPageSize());
            writer.newPage();

            // Create a PdfContentByte object for the page
            PdfContentByte contentByte = writer.getDirectContent();

            // Create a Graphics2D object for the PdfContentByte
            Graphics2D g2d = contentByte.createGraphics(document.getPageSize().getWidth(), document.getPageSize().getHeight());

            // Paint the JPanel onto the Graphics2D object
            panel.paint(g2d);

            // Dispose the Graphics2D object
            g2d.dispose();

            // Close the PDF document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}