package com.example.constructora.reports;



import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.servlet.http.HttpServlet;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class ReportService extends HttpServlet {

    public static void generateObrasReport(JFrame jFrame) throws SQLException {
//        System.out.println("GENEREMOS EL INFORME");
//        JasperReport report = (JasperReport) JRLoader.loadObject(Objects.requireNonNull(getClass().getResource("/reports/FINAL_OBRAS_PAGO.jasper")));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, JDBCUtils.getConnection());
////                    JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\MAURY\\development\\constructora\\reportePagoObras.pdf");
//
//        if (jasperPrint!=null) {
//            System.out.println("GENEREMOS EL INFORME NO NULLLLLLL");
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
//        }
    }
}
