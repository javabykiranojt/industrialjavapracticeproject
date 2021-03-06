package com.kjnext.dmart.hibernate;

import java.util.Date;


/**
 * UserDocuments generated by MyEclipse - Hibernate Tools
 */

public class UserDocuments  implements java.io.Serializable {


    // Fields    

     private Integer uidDocId;
     private UserDetail userDetail;
     private DocumetRepository documetRepository;
     private String documentName;
     private Date createdDtm;
     private Date modifiedDtm;
     private Integer createdUId;
     private Integer modifiedUId;


    // Constructors

    /** default constructor */
    public UserDocuments() {
    }

	/** minimal constructor */
    public UserDocuments(Integer uidDocId, UserDetail userDetail, DocumetRepository documetRepository, String documentName) {
        this.uidDocId = uidDocId;
        this.userDetail = userDetail;
        this.documetRepository = documetRepository;
        this.documentName = documentName;
    }
    
    /** full constructor */
    public UserDocuments(Integer uidDocId, UserDetail userDetail, DocumetRepository documetRepository, String documentName, Date createdDtm, Date modifiedDtm, Integer createdUId, Integer modifiedUId) {
        this.uidDocId = uidDocId;
        this.userDetail = userDetail;
        this.documetRepository = documetRepository;
        this.documentName = documentName;
        this.createdDtm = createdDtm;
        this.modifiedDtm = modifiedDtm;
        this.createdUId = createdUId;
        this.modifiedUId = modifiedUId;
    }

   
    // Property accessors

    public Integer getUidDocId() {
        return this.uidDocId;
    }
    
    public void setUidDocId(Integer uidDocId) {
        this.uidDocId = uidDocId;
    }

    public UserDetail getUserDetail() {
        return this.userDetail;
    }
    
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public DocumetRepository getDocumetRepository() {
        return this.documetRepository;
    }
    
    public void setDocumetRepository(DocumetRepository documetRepository) {
        this.documetRepository = documetRepository;
    }

    public String getDocumentName() {
        return this.documentName;
    }
    
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Date getCreatedDtm() {
        return this.createdDtm;
    }
    
    public void setCreatedDtm(Date createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Date getModifiedDtm() {
        return this.modifiedDtm;
    }
    
    public void setModifiedDtm(Date modifiedDtm) {
        this.modifiedDtm = modifiedDtm;
    }

    public Integer getCreatedUId() {
        return this.createdUId;
    }
    
    public void setCreatedUId(Integer createdUId) {
        this.createdUId = createdUId;
    }

    public Integer getModifiedUId() {
        return this.modifiedUId;
    }
    
    public void setModifiedUId(Integer modifiedUId) {
        this.modifiedUId = modifiedUId;
    }
   








}