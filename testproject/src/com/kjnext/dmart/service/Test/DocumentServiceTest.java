package com.kjnext.dmart.service.Test;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.service.DocumentService;
import com.kjnext.dmart.service.Impl.DocumentServiceImpl;

public class DocumentServiceTest extends TestCase {

	DocumentService documentService;

	@Override
	protected void setUp() throws Exception {
		documentService = new DocumentServiceImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		documentService = null;
	}

	public void testStoreDocument() throws IOException {
		File file = new File("abc.txt");
		byte[] bytes = documentService.getBytesFromFile(file);
		documentService.storeDocument(bytes);
	}

	public void testRetriveDocument() throws IOException {
		DocumetRepository documetRepository = documentService
				.retrieveDocument(1);
		assertNotNull(documetRepository);
	}

}
