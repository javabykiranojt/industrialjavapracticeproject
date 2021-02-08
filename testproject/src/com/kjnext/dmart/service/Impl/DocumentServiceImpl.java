package com.kjnext.dmart.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.DocumentDao;
import com.kjnext.dmart.dao.Impl.DocumentDaoImpl;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.service.DocumentService;

public class DocumentServiceImpl implements DocumentService {

	DocumentDao documentDao = new DocumentDaoImpl();

	public DocumetRepository retrieveDocument(Integer docId) {
		return documentDao.retrieveDocument(docId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kjnext.dmart.service.DocumentService#storeDocument(byte[])
	 */
	public Integer storeDocument(byte[] bytes) {
		/*InputStream inputStream = new ByteArrayInputStream(bytes);
		Blob blob = null;
		try {
			blob = Hibernate.createBlob(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DocumetRepository documetRepository = new DocumetRepository();
		documetRepository.setDocument(blob);
		documentDao.storeDocument(documetRepository);
		return documetRepository.getDocId();*/
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		if (bytes != null) {
			InputStream inputStream = new ByteArrayInputStream(bytes);
			DocumetRepository documetRepository = new DocumetRepository();
			Blob blob = null;
			try {
				blob = Hibernate.createBlob(inputStream);
				documetRepository.setDocument(blob);
				hibernateSession.save(documetRepository);				
				return documetRepository.getDocId();
			} catch (Exception e) {
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
		}

		return null;
	}

	public Integer deleteDocument(Integer docId) {
		// TODO Auto-generated method stub
		return null;
	}

	// Returns the contents of the file in a byte array.
	public byte[] getBytesFromFile(File file) throws IOException {
		 if(file!=null){
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
		 }else {
			return null;
		}
	}

	private byte[] toByteArray(Blob fromImageBlob) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(fromImageBlob, baos);
		} catch (Exception e) {
		}
		return null;
	}

	private byte[] toByteArrayImpl(Blob fromImageBlob,
			ByteArrayOutputStream baos) throws SQLException, IOException {
		byte buf[] = new byte[4000];
		int dataSize;
		InputStream is = fromImageBlob.getBinaryStream();

		try {
			while ((dataSize = is.read(buf)) != -1) {
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return baos.toByteArray();
	}

	/*
	 * this method returns specified document(blob) as Array of byte
	 */
	public byte[] getDocumentAsByteArray(Integer docId) {
		DocumetRepository documetRepository = retrieveDocument(docId);
		byte[] bytes = toByteArray(documetRepository.getDocument());
		return bytes;
	}

	/*
	 * this method returns specified document(blob) as InputStream
	 */
	public InputStream getDocumentAsInputStream(Integer docId) {
		byte[] bytes = getDocumentAsByteArray(docId);
		InputStream fileInputStream = new ByteArrayInputStream(bytes);
		return fileInputStream;
	}
}
