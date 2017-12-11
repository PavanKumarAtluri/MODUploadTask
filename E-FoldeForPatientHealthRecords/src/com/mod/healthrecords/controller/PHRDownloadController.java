package com.mod.healthrecords.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("phrDownloadController")
@RequestMapping("/phrDetailsDisplay/phrDownload")
public class PHRDownloadController {
	@RequestMapping("/phrDownloadHandler.htm")
	public void download(HttpServletResponse res, @RequestParam("path") String fileName) throws Exception {
		File file = null;
		InputStream is = null;
		OutputStream os = null;

		res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

		file = new File(fileName);

		is = new FileInputStream(file);

		os = res.getOutputStream();

		IOUtils.copy(is, os);
	}
}
