package com.example.demo.controller;

import java.io.File;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.QRCodeUtil;

@Controller
public class Controlador {
	
	 @RequestMapping(value = "/createCommonQRCode")
	    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
	        ServletOutputStream stream = null;
	        try {
	            stream = response.getOutputStream();
	            // Usa herramientas para generar código QR
	            QRCodeUtil.encode(url, stream);
	        } catch (Exception e) {
	            e.getStackTrace();
	        } finally {
	            if (stream != null) {
	                stream.flush();
	                stream.close();
	            }
	        }
	    }

	    /**
	           * Generar código QR con logo según url
	     */
	    @RequestMapping(value = "/createLogoQRCode")
	    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
	        ServletOutputStream stream = null;
	        try {
	            stream = response.getOutputStream();
	            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
	                    + "templates" + File.separator + "logo1.png";
	            // Usa herramientas para generar código QR
	            QRCodeUtil.encode(url, logoPath, stream, true);
	        } catch (Exception e) {
	            e.getStackTrace();
	        } finally {
	            if (stream != null) {
	                stream.flush();
	                stream.close();
	            }
	        }
	    }
	    
	    @GetMapping("/qr")
	    public String qr() {
			return "qr/qr";
	    }
}
