package com.kjnext.dmart.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface FileOperations 
{
	
	 public List getUserDocumentList(Integer uid);
	 
	 public  InputStream getUserDocumentFile(Integer docid);
	 
	 public byte[] toByteArray(Blob fromImageBlob);
	 
	 public byte[] toByteArrayImpl(Blob fromImageBlob,ByteArrayOutputStream baos) throws SQLException, IOException;

}
