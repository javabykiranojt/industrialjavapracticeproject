package com.kjnext.dmart.service.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.service.DocumentService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.DocumentServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.Status;

import junit.framework.TestCase;

public class UserServiceTest extends TestCase
{
 UsersService usersService=null;
 DocumentService documentService=new DocumentServiceImpl();
 
 UserDetail userDetail=new UserDetail();
 

	protected void setUp() throws Exception 
	{
	  usersService=new UsersServiceImpl();
	}
	
	protected void tearDown() throws Exception 
	{
		usersService=null;
	}
	
	
	public void testAddNewUser() throws IOException 
	{
		File file1 = new File("abc.txt");
		File file2 = new File("pqr.txt");
		byte[] bytes1 = documentService.getBytesFromFile(file1);
		int docId1 = documentService.storeDocument(bytes1);
		byte[] bytes2 = documentService.getBytesFromFile(file2);
		int docId2 = documentService.storeDocument(bytes2);
		
		UserDocuments userDocuments=new UserDocuments();
		
		DocumetRepository documetRepository=new DocumetRepository();
		documetRepository.setDocId(docId1);
		userDocuments.setDocumetRepository(documetRepository);
		userDocuments.setDocumentName(file1.getName());
		
		
		UserDocuments userDocuments1=new UserDocuments();
		DocumetRepository documetRepository1=new DocumetRepository();
		documetRepository1.setDocId(docId2);
		userDocuments1.setDocumetRepository(documetRepository1);
		userDocuments1.setDocumentName(file2.getName());
		
		
		
		Location location=new Location();
		location.setLocId(2);
		Tasks tasks=new Tasks();
		tasks.setTaskId(1);
		Set<Tasks> taskSet=new HashSet<Tasks>();
		taskSet.add(tasks);
		
		//userDetail.setAddress("Jalna");
		userDetail.setContactNo("95954");
		userDetail.setEmailId("kj@gmail.com");
		userDetail.setFirstName("ram");
		userDetail.setGender("Male");
		userDetail.setLastName("sdgf");
		userDetail.setLocation(location);
		userDetail.setStatus(Status.PENDING_USER.toString());
		userDetail.setTaskses(taskSet);
		userDetail.setCreatedDtm(new Date());
		
		userDocuments.setCreatedDtm(new Date());
		userDocuments.setUserDetail(userDetail);
		userDocuments1.setUserDetail(userDetail);
		userDocuments1.setCreatedDtm(new Date());
		List<UserDocuments> documList= new ArrayList<UserDocuments>();
		documList.add(userDocuments1);
		documList.add(userDocuments);
		
	//	usersService.addNewUser(userDetail,documList);
	}

	public void testUpdateUser() 
	{
		Location location=new Location();
		location.setLocId(1);
		Tasks tasks=new Tasks();
		tasks.setTaskId(1);
		Set<Tasks> taskSet=new HashSet<Tasks>();
		taskSet.add(tasks);
		
		userDetail.setUserEmpId(3);	
		//userDetail.setAddress("Karve");
		userDetail.setContactNo("95543321");
		userDetail.setEmailId("kran@gmail.com");
		userDetail.setGender("Male");
		userDetail.setFirstName("kiran");
		userDetail.setLastName("Kothawade");
		userDetail.setLocation(location);
		userDetail.setStatus("active");
		userDetail.setTaskses(taskSet);
		//usersService.updateUser(userDetail);
	}
	
	public void testApproveUser() 
	{
		Location location=new Location();
		location.setLocId(1);
		Tasks tasks=new Tasks();
		tasks.setTaskId(1);
		Set<Tasks> taskSet=new HashSet<Tasks>();
		taskSet.add(tasks);
		
		userDetail.setUserEmpId(3);	
		//userDetail.setAddress("Karve");
		userDetail.setContactNo("95543321");
		userDetail.setEmailId("kran@gmail.com");
		userDetail.setGender("Male");
		userDetail.setFirstName("kiran");
		userDetail.setLastName("Kothawade");
		userDetail.setLocation(location);
		userDetail.setStatus("Deactive");
		//usersService.approveUser(userDetail);
	}

	public void testRemoveUser() 
	{
		Location location=new Location();
		location.setLocId(1);
		Tasks tasks=new Tasks();
		tasks.setTaskId(1);
		Set<Tasks> taskSet=new HashSet<Tasks>();
		taskSet.add(tasks);
		
		userDetail.setUserEmpId(3);	
		//userDetail.setAddress("Karve");
		userDetail.setContactNo("95543321");
		userDetail.setEmailId("kran@gmail.com");
		userDetail.setGender("Male");
		userDetail.setFirstName("kiran");
		userDetail.setLastName("Kothawade");
		userDetail.setLocation(location);
		userDetail.setStatus("Deactive");
	    //usersService.updateUser(userDetail);
	}

	
	
	public void testCreateUnamePassWord()
	{
		//String sd=usersService.createUnamePassWord(6);
	}
	
	
	public void testGetAllUser()
	{
	//List<UserDetail> userList=usersService.getAllUser();
	}
	
	public void testGetAllApprovedUser()
	{
		//List<UserDetail> approvedUserList=usersService.getAllApprovedUser();
	}


	public void testGetAllPendingUser() 
	{
		//List<UserDetail> pendingUserList=usersService.getAllPendingUser();
	}

	public void testGetAllRejectedUser() 
	{
		//List<UserDetail> rejectedUserList=usersService.getAllRejectedUser();
	}
	public void testGetAllActiveUser()
	{
		List<UserDetail> activeUserList=usersService.getAllActiveUser();
	}
	
}
