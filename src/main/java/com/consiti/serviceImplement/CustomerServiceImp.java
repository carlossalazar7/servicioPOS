package com.consiti.serviceImplement;

import com.consiti.service.CustomerService;
import com.consiti.utils.ReadProperties;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImp implements CustomerService {
    Connection con;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
    String message;

    public String exportReport(String reportFormat) {

        try {
            Class.forName(ReadProperties.getProperty("jasper.driver"));
            con = DriverManager.getConnection(
                    ReadProperties.getProperty("jasper.ruta"),
                    ReadProperties.getProperty("jasper.user"),
                    ReadProperties.getProperty("jasper.password")
            );

            String path = ReadProperties.getProperty("jasper.save.customer");

            File file = ResourceUtils.getFile(ReadProperties.getProperty("jasper.load.report.customer"));
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "BackEnd Team");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\clientes.html");
                message = "report generated in path : " + path + "/clientes.html";
            }
            if (reportFormat.equalsIgnoreCase("excel")) {
                JRXlsxExporter exporter = new JRXlsxExporter();
                SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
                reportConfigXLS.setSheetNames(new String[]{"customers"});
                exporter.setConfiguration(reportConfigXLS);
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path + "\\clientes_" + formatter.format(date) + ".xlsx"));
                exporter.exportReport();
                message = "report generated in path : " + path + "/clientes_" + formatter.format(date) + ".xlsx";
            }
            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\clientes_" + formatter.format(date) + ".pdf");
                message = "report generated in path : "  + path + "/clientes_" + formatter.format(date) + ".pdf";
            }
            return message;
        } catch (FileNotFoundException e) {
            return "plantilla no encontrada";
        } catch (SQLException e) {
            return "error de conexion";
        } catch (ClassNotFoundException e) {
            return "driver MySQL no encontrado";
        } catch (JRException e) {
            return e.getMessage();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}