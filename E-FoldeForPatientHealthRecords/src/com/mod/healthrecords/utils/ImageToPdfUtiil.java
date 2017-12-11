package com.mod.healthrecords.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FilenameUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mod.Docx2Pdf.Docx2PDF;
import com.mod.healthrecords.beans.dto.PatientHealthReportDTO;

public class ImageToPdfUtiil {

	public static boolean convertAndSaveImageAsPdf(String image_path, String pdf_path,
			PatientHealthReportDTO patientHealthReport) throws DocumentException, MalformedURLException, IOException {
		boolean flag = false;
		PdfStamper pdfStamper = null;
		String ext = FilenameUtils.getExtension(image_path);
		System.out.println(ext);
		Document convertJpgToPdf = new Document();
		
		// Get the input image to Convert to PDF
		if (!("pdf".equalsIgnoreCase(ext))) {
			if("docx".equalsIgnoreCase(ext)){
				System.out.println("ImageToPdfUtiil.convertAndSaveImageAsPdf().if.inside if");
				Docx2PDF docx2pdf=new Docx2PDF();
				docx2pdf.ConvertToPDF(image_path, pdf_path);
			}else if("jpeg".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "png".equalsIgnoreCase(ext) || "tif".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext)){
				Image convertJpg = Image.getInstance(image_path);
				// Create PdfWriter for Document to hold physical file
				PdfWriter.getInstance(convertJpgToPdf, new FileOutputStream(pdf_path));
				// convertJpg.setCompressionLevel(25);
				convertJpgToPdf.open();
				// Add image to Document
				flag = convertJpgToPdf.add(convertJpg);
				System.out.println("Inside If::" + flag);
			}
		} else {
			System.out.println("path in else::"+pdf_path);
			pdfStamper = new PdfStamper(new PdfReader(image_path), new FileOutputStream(pdf_path));
			pdfStamper.close();
			return true;
		}

		convertJpgToPdf.close();

		System.out.println("OutSide If::" + flag);
		return flag;
	}

}
