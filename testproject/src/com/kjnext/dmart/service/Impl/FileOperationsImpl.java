package com.kjnext.dmart.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import com.kjnext.dmart.dao.Impl.UsersDaoImpl;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.service.FileOperations;
import com.kjnext.utility.hibernate.HibernateUtil;

public class FileOperationsImpl implements FileOperations
{
  UsersDaoImpl daoImpl=new UsersDaoImpl();
  
   /*
    *getting document list for selected user
    *
    */
	public List getUserDocumentList(Integer uid) 
	{
		  List<DocumetRepository> fileList=daoImpl.getUserDocumentList(uid);
		  return fileList;
	}

	
	/*
	 * 
	 * getting file from db
	 */
	public InputStream getUserDocumentFile(Integer docid)
	{
		try 
		{
			DocumetRepository documetRepository=(DocumetRepository)HibernateUtil.getSession().get(DocumetRepository.class,docid);
			byte[] bytes=toByteArray(documetRepository.getDocument());
			InputStream fileInputStream=new ByteArrayInputStream(bytes);;
			return fileInputStream;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
   
	/*
	 * 
	 * converting FileStream into byte
	 */
	public byte[] toByteArray(Blob fromImageBlob) 
	{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    try
		    {
		      return toByteArrayImpl(fromImageBlob, baos);
		    } catch (Exception e) 
		    {
		    	e.printStackTrace();
		    }
		return null;
	}
	

	/*
	 *
	 *Converting fileStream to Byte implementation
	 */
	public byte[] toByteArrayImpl(Blob fromImageBlob, ByteArrayOutputStream baos) throws SQLException, IOException
	{
		byte buf[] = new byte[4000];
	    int dataSize;
	    InputStream is=null;
	    try
	     {
	      is = fromImageBlob.getBinaryStream();
	      while((dataSize = is.read(buf)) != -1)
	      {
	        baos.write(buf, 0, dataSize);
	      }
	     }
	    
	    finally {
	      if(is != null)
	      {
	        is.close();
	      }
	    }
	    return baos.toByteArray();
	}

}
