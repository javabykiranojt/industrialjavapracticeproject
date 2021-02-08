package com.kjnext.dmart.dao.Impl;

import org.hibernate.Session;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.DocumentDao;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.utility.hibernate.HibernateUtil;

public class DocumentDaoImpl implements DocumentDao {

	public Integer deleteDocument(Integer docId) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumetRepository retrieveDocument(Integer docId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		DocumetRepository documetRepository =null;
		try {
		 documetRepository = (DocumetRepository) hibernateSession.get(DocumetRepository.class, docId);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return documetRepository;
	}

	public Integer storeDocument(DocumetRepository documetRepository) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(documetRepository);
		}  catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return documetRepository.getDocId();
	}

}
