package com.kjnext.dmart.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.kjnext.dmart.hibernate.DocumetRepository;

public interface DocumentService {
	/*
	 * Stores docs
	 */
	Integer storeDocument(byte[] bytes);

	DocumetRepository retrieveDocument(Integer docId);

	Integer deleteDocument(Integer docId);

	public byte[] getBytesFromFile(File file) throws IOException;
	
	public InputStream getDocumentAsInputStream(Integer docId);
	
	public byte[] getDocumentAsByteArray(Integer docId);
}
