package com.kjnext.dmart.dao;

import com.kjnext.dmart.hibernate.DocumetRepository;

public interface DocumentDao {
	Integer storeDocument(DocumetRepository documetRepository );

	DocumetRepository retrieveDocument(Integer docId);

	Integer deleteDocument(Integer docId);
}
