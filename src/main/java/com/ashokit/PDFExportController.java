package com.ashokit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PDFExportController {

	@Autowired
	BookRepository bookRepo;
	@GetMapping(value="/books" , produces=MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
		List<Book> book = (List<Book>) bookRepo.findAll();
		ByteArrayInputStream bis = DatabasePDFService.playerPDFReport(book);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=books.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}
