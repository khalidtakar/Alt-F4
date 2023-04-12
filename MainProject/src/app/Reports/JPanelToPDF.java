package app.Reports;

import java.awt.*;
import java.io.FileOutputStream;
import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class JPanelToPDF {
    public JPanelToPDF(){}

    public void makePDF(JPanel panel){
        try {
            // Create a new PDF document
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
            document.open();

            // Calculate the scaling factor to fit the panel within the PDF page
            double scaleX = PageSize.A4.getWidth() / panel.getWidth();
            double scaleY = PageSize.A4.getHeight() / panel.getHeight();
            double scale = Math.min(scaleX, scaleY);

            // Create a PdfContentByte object for the page
            PdfContentByte contentByte = writer.getDirectContent();

            // Create a Graphics2D object for the PdfContentByte, scaled to fit the panel
            Graphics2D g2d = contentByte.createGraphics(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            g2d.scale(scale, scale);

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