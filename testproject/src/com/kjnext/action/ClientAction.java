package com.kjnext.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kjnext.dmart.dao.TaskDao;
import com.kjnext.dmart.dao.Impl.ClientDaoImpl;
import com.kjnext.dmart.dao.Impl.TaskDaoImpl;
import com.kjnext.dmart.hibernate.ClientDetails;
import com.kjnext.dmart.hibernate.ClientDocuments;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.DocumentService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.DocumentServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.utility.OutCome;

public class ClientAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	ClientService clientService = new ClientServiceImpl();
	UsersService   userService=new UsersServiceImpl();
	DocumentService documentService = new DocumentServiceImpl();
	TaskDao taskDao= new TaskDaoImpl();
	private Tasks tasks;
	List<UserDetail> clientname1;
	List<Clients> clientsList = null;
	List<UserDetail> userDetailList=null;
	ClientDetails clientDetails;
	Clients clients;
	String navigationFlag;
	private Users users;
	private UserDetail userDetail;
	private String userDetailStatus="ACTIVE";
	private Integer id ;
	private String uidName;
	private String userAddress;
	 List<Location> locationList; 
	 List<LocationArea> locationAreaList;
	 Location location;
	 Integer userEmpId;
	 Integer area1;
	 Integer area2;
	 Integer locId;
	 Integer locId1;
	private InputStream feedbackForm;
	private int feedbackFormLength;
	private Integer docId;
	List<Location> locationList2;
	UserDetail cltname;
	private Integer taskId;
	private File clientDoc;
	private String clientname;
	private String clientDocFileName;
	private String clientDocContentType;
	private List<UserDocuments> documentList;
	private String pageTitle;
	private OutCome outCome =new OutCome();
	private List<Clients> storeList;
	private File offlineFeedback;	
	private String offlineFeedbackFileName;	
	private Integer offlineFeedbackId;
	private ClientDocuments clientDocuments;
	
	/**
	 * @return the clientDocuments
	 */
	public ClientDocuments getClientDocuments() {
		return clientDocuments;
	}
	/**
	 * @param clientDocuments the clientDocuments to set
	 */
	public void setClientDocuments(ClientDocuments clientDocuments) {
		this.clientDocuments = clientDocuments;
	}
	public UsersService getUserService() {
		return userService;
	}
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}
	public List<UserDetail> getClientname1() {
		return clientname1;
	}
	public void setClientname1(List<UserDetail> clientname1) {
		this.clientname1 = clientname1;
	}
	public UserDetail getCltname() {
		return cltname;
	}
	public void setCltname(UserDetail cltname) {
		this.cltname = cltname;
	}
	public List<Location> getLocationList2() {
		return locationList2;
	}
	public void setLocationList2(List<Location> locationList2) {
		this.locationList2 = locationList2;
	}
	public String showAllClients() {
		clientsList = clientService.showAllClients();
	
		return SUCCESS;
	}
	/******************    Start by SHIVTEJ    *************************************/
	
	public String differentLocationAreas()
	{
		
		locationAreaList = userService.getAreasOnLocation(location.getLocId());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		try{
			response.getWriter().write(
				"<select>");
			for (LocationArea locationArea : locationAreaList)
			{
				response.getWriter().write(
					"<option  value=" + locationArea.getAreaId() + ">"
					+ locationArea.getAreaName() + "</option>");
			}
			response.getWriter().write("</select>");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String showUserDetails(){
		pageTitle=getText("show.client");
		if (getNavigationFlag()!=null) {
			if (getNavigationFlag().equals("clientAdded")) {
				outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				 outCome.addMessage(new OutCome.Message("showAllClients.ClientAction.success.add"));
				 addActionMessage(outCome);
			}else if (getNavigationFlag().equals("storeAdded")) {
				outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				 outCome.addMessage(new OutCome.Message("showAllClients.ClientAction.success.storeAdded"));
				 addActionMessage(outCome);
			}
			
		}
		userDetailList=userService.showAllUser();
		
	return SUCCESS;
	}
	
	
	public String addNewClient(){
		locationAreaList=new ArrayList<LocationArea>();
		System.out.println(getLocId1());
		System.out.println(getLocId());
		locationAreaList = userService.getAreasOnLocation(getLocId());
		locationList=userService.getAllLocation();
		return SUCCESS;
	}	

		
	public String generateCredential()
	{	pageTitle=getText("generate.credential");
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
		return "success";
	}
	public String generateCredentialInput()
	{
		
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
		clientService.generateCredential(userDetail);
		setNavigationFlag("success");
		
		return "success";
	}
	
		// Amit...		
	
	public String addClientInput() throws IOException{	
		byte[] bytesOfflineFeedbackformat = null;
		documentList = new ArrayList<UserDocuments>();
		try {
			if (offlineFeedback != null) {
			
			
			
				bytesOfflineFeedbackformat = UsersServiceImpl
						.getBytesFromFile(offlineFeedback);

				UserDocuments userDocuments = usersService
						.loadToDB(bytesOfflineFeedbackformat);
				System.out.println(userDocuments.getDocumetRepository().getDocId());
				offlineFeedbackId = userDocuments.getDocumetRepository().getDocId();
				if (userDocuments != null) {
					userDocuments.setDocumentName(offlineFeedbackFileName);
					System.out.println(offlineFeedbackFileName);
					documentList.add(userDocuments);
				}
				
			OutCome outCome=new OutCome();
			clientname1=userService.showAllUser();
			List<String> ctname=new ArrayList<String>();
			List<String> ctname1=new ArrayList<String>();
			for(UserDetail cltname:clientname1){
				cltname.getFirstName();
				cltname.getLastName();
				ctname.add(cltname.getFirstName());
			ctname1.add(cltname.getLastName());
			}
			
			locationList2=userService.getAllLocation();
			
			List<Integer> city=new ArrayList<Integer>(); 
			for(Location loc:locationList2){
				 city.add(loc.getLocId());			 
			 }
			
			 if(city.contains(getLocId1()) && ctname.contains(userDetail.getFirstName()) && ctname1.contains(userDetail.getLastName()) ){
				 outCome.setStatus(OutCome.Status.FAILURE);
				 outCome.addMessage(new OutCome.Message("This Client Name & Location already exist"));
				 addActionMessage(outCome);
				 addNewClient();
			 }
			  else{ 						
				  uidName = (String) session.get("UserName");
				  id = (Integer) session.get("uId");				
			
				  LocationArea clientLocationArea=new LocationArea();
				  clientLocationArea.setAreaId(area2);
			
				  Location clientLocation=new Location();
				  clientLocation.setLocId(getLocId());
			

				  LocationArea storeLocationArea=new LocationArea();
				  storeLocationArea.setAreaId(area1);
			
				  Location storeLocation=new Location();
				  storeLocation.setLocId(getLocId1());
			
				/**
				 * data to insert in userDetail table
				 */
			
				  userDetail.setlocationArea(clientLocationArea);
				  userDetail.setLocation(clientLocation);
				  userDetail.setStatus(userDetailStatus);
				  userDetail.setCreatedDtm(new Date());
				  userDetail.setCreatedUId(id);
				  clientService.addUserDetails(userDetail);
			
				
				/**
				 * data to be inserted in clientDetails
				 */
				  clientDetails.setlocationArea(storeLocationArea);
				  clientDetails.setLocation(storeLocation);
			
				  clientService.addClientDetails(clientDetails);
				
				/**
				 * data to be inserted in Clients
				 */
				
				/*ClientDetails clientDetails = new ClientDetails();
				clientDetails.setCltDetailId(userService.getMaxClientEmpId());*/
				
				  clients.setClientDetails(clientDetails);
	
				/*UserDetail userDetail = new UserDetail();
				userDetail.setUserEmpId(userService.getMaxEmpId());*/
				  clients.setUserDetail(userDetail);
				  clients.setCreatedDtm(new Date());
				  clients.setCreatedUId(id);
				 
				  	
				  System.out.println(offlineFeedbackId);
				  DocumetRepository documetRepository = new DocumetRepository();
				  documetRepository.setDocId(offlineFeedbackId);
					
				  	clients.setDocname(offlineFeedbackFileName);		
				  clients.setDocumetRepository(documetRepository);
				  
				  clientService.addClient(clients);		  
				List<Clients> clList=   clientService.getClientsOnLocation(getLocId());
				  for (Clients client : clList) {
					if (client.getUserDetail().getFirstName().equals(clients.getUserDetail().getFirstName())
							&& client.getDocumetRepository().getDocId() == clients.getDocumetRepository().getDocId()) {
						new UsersServiceImpl().addUserDocuments(client.getUserDetail().getUserEmpId(), documentList);
					}
				}
				  locationAreaList=new ArrayList<LocationArea>();
				  locationList=userService.getAllLocation();
				  setNavigationFlag("success");			
			  }
			 addClientDocument(userDetail.getUserEmpId(),clients.getDocumetRepository().getDocId(),clients.getDocname());
			}
			 else {		
				 	addNewClient();
//				 	locationList=userService.getAllLocation();
				 	//locationAreaList = userService.getAreasOnLocation(getLocId1());
					OutCome outCome=new OutCome();
					outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
					outCome.addFailureMessage(new OutCome.Message("feedback.updated.byUser.fail"));
					addActionMessage(outCome);
				}
				
			 
			return SUCCESS;		
		}  catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void addClientDocument(Integer clientId, Integer docId, String docName) {
		try {
		ClientDocuments clientDocuments=new ClientDocuments();
		clientDocuments.setDocId(docId);
		clientDocuments.setClientId(clientId);
		clientDocuments.setDocumentName(docName);
		ClientDaoImpl clientDaoImpl=new ClientDaoImpl();
		clientDaoImpl.addClientDocument(clientDocuments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean addDocumentToClient(){
		try {
			if (offlineFeedback != null) {
				DocumetRepository documetRepository = new DocumetRepository();
				documetRepository.setDocId(offlineFeedbackId);
				Clients client = new Clients();				
				client.setDocumetRepository(documetRepository);
				clientService.updateClient(client);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public String addStore(){
		pageTitle=getText("add.store");
		locationAreaList=new ArrayList<LocationArea>();
		locationList=userService.getAllLocation();
		return SUCCESS;
	}
	
	public String addStoreInput(){
		
				
		
		LocationArea LocationArea=new LocationArea();
		LocationArea.setAreaId(area1);
		
		Location Location=new Location();
		Location.setLocId(getLocId());
		
		System.out.println(clients);
		clientDetails.setlocationArea(LocationArea);
		clientDetails.setLocation(Location);
		if(clientDetails!=null){
		clientService.addClientDetails(clientDetails);
		}
		
		
		clients.setClientDetails(clientDetails);

		if(clientDetails!=null){
			clients.setUserDetail(userDetail);
		}
		if(clients!=null){
			clients.setCreatedDtm(new Date());
			clients.setCreatedUId((Integer) session.get("uId"));
			clientService.addClient(clients);
		}
		locationAreaList=new ArrayList<LocationArea>();
		locationList=userService.getAllLocation();
		setNavigationFlag("success");
		return SUCCESS;
	}
	
	
	
	
	public String updateClient(){ 
		pageTitle=getText("update.client");
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
					
		locationList=userService.getAllLocation();
		if(userDetail.getLocation()!=null)
		locationAreaList = userService.getAreasOnLocation(userDetail.getLocation().getLocId());
		else
			locationAreaList=new ArrayList<LocationArea>();
		return SUCCESS;
	}
	
	public String updateClientInput(){
		try{
			
			id = (Integer) session.get("uId");
			userDetail=userService.getUserDetail(userDetail.getUserEmpId());
	userDetail.setStatus(userDetailStatus);		
	userDetail.setUserEmpId(userDetail.getUserEmpId());	
	userDetail.setFirstName(userDetail.getFirstName());
	userDetail.setLastName(userDetail.getLastName());
	userDetail.setContactNo(userDetail.getContactNo());
	userDetail.setlocationArea(userDetail.getlocationArea());
	userDetail.setModifiedDtm(new Date());
	userDetail.setEmailId(userDetail.getEmailId());
	userDetail.setModifiedUId(id);
	userService.updateUser(userDetail);
	
	
	setNavigationFlag("success");
	
	}catch(Exception e){
		e.printStackTrace();
		}
	locationList=userService.getAllLocation();
	locationAreaList = userService.getAreasOnLocation(userDetail.getLocation().getLocId());
	
	return SUCCESS;
	}
	
	public String preDelete(){
		
		
		
		return SUCCESS;
	}
	
	public String deleteClient(){
		
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
		userDetail.setUserEmpId(userDetail.getUserEmpId());	
		userDetail.setStatus("INACTIVE");
		userService.updateUser(userDetail);
		
		setNavigationFlag("success");
		return SUCCESS;
	}
	
	public String getOfflineFeedbackForm()
    {
        if(docId==null)
        {
            addActionError("Sorry !!! Offline feedback form is not available.");
            List<Tasks>    data=taskDao.userFeedbackClientNameDate(taskId);
            for (Tasks tasks : data) {
                String dateWithTime=tasks.getScheduleDate().toString();
                String [] dateOnly=dateWithTime.split(" ");
                addActionMessage("<b>Client: </b>"+tasks.getClients()+"<br><br><b>Task Assigned Date: </b>"+dateOnly[0]+"</b><br><br>");
                
                this.tasks = tasks;
            }
            return "formNotAvailable";
        }
        feedbackForm = documentService.getDocumentAsInputStream(docId);
        feedbackFormLength = documentService.getDocumentAsByteArray(docId).length;
        return SUCCESS;
    }

/***************** By amarjit****************************/
	/*public String uploadClientDocInput(){
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
		userDetail.setUserEmpId(userDetail.getUserEmpId());	
		clientname = userDetail.getFirstName().toUpperCase()+" "+userDetail.getLastName().toUpperCase();
		return SUCCESS;
	}
	
	public String uploadClientDoc(){
		documentList = new ArrayList<UserDocuments>();
		OutCome outCome = new OutCome();
		byte[] clientDocByteFormat = null;
		try {
			if (null != clientDoc) {
				clientDocByteFormat = UsersServiceImpl
						.getBytesFromFile(clientDoc);

				UserDocuments userDocuments = userService
						.loadToDB(clientDocByteFormat);
				if (userDocuments != null) {
					userDocuments.setDocumentName(clientDocFileName);
					documentList.add(userDocuments);
					}
			}
			
			if (userDetail != null && documentList != null) {
					
				userService.addUserDocuments(getUserDetail().getUserEmpId(), documentList);
				
				setNavigationFlag("success");
					
					return SUCCESS;
			}
			else{
				userDetail=userService.getUserDetail(userDetail.getUserEmpId());
				userDetail.setUserEmpId(userDetail.getUserEmpId());	
				clientname = userDetail.getFirstName().toUpperCase()+" "+userDetail.getLastName().toUpperCase();
				outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message("clientUploadErr.message"));
				addActionMessage(outCome);
			}
			
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}*/
	
	/***Amit ***/
	
	public String viewStores(){
		pageTitle=getText("view.store");
	 storeList=clientService.viewStores(userDetail.getUserEmpId());
	 /*for(Clients list:storeList){
		 System.out.println(list.getClientDetails().getCltFirstName());
		 System.out.println(list.getClientDetails().getlocationArea());
		 System.out.println(list.getUserDetail().getFirstName());
	 }	*/
	 return "success";
	}
/******************    End by SHIVTEJ    *************************************/


	//Getter And Setter  

	public List<Clients> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	
	
	

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
	}
	public List<UserDetail> getUserDetailList() {
		return userDetailList;
	}
	public void setUserDetailList(List<UserDetail> userDetailList) {
		this.userDetailList = userDetailList;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUidName() {
		return uidName;
	}
	public void setUidName(String uidName) {
		this.uidName = uidName;
	}
	public List<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
	public List<LocationArea> getLocationAreaList() {
		return locationAreaList;
	}
	public void setLocationAreaList(List<LocationArea> locationAreaList) {
		this.locationAreaList = locationAreaList;
	}
	public Integer getArea1() {
		return area1;
	}
	public void setArea1(Integer area1) {
		this.area1 = area1;
	}
	public Integer getArea2() {
		return area2;
	}
	public void setArea2(Integer area2) {
		this.area2 = area2;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public Integer getLocId1() {
		return locId1;
	}
	public void setLocId1(Integer locId1) {
		this.locId1 = locId1;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Integer getUserEmpId() {
		return userEmpId;
	}
	public void setUserEmpId(Integer userEmpId) {
		this.userEmpId = userEmpId;
	}
	public InputStream getFeedbackForm() {
		return feedbackForm;
	}
	public void setFeedbackForm(InputStream feedbackForm) {
		this.feedbackForm = feedbackForm;
	}
	public int getFeedbackFormLength() {
		return feedbackFormLength;
	}
	public void setFeedbackFormLength(int feedbackFormLength) {
		this.feedbackFormLength = feedbackFormLength;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Tasks getTasks() {
		return tasks;
	}
	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}
	public File getClientDoc() {
		return clientDoc;
	}
	public void setClientDoc(File clientDoc) {
		this.clientDoc = clientDoc;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getClientDocFileName() {
		return clientDocFileName;
	}
	public void setClientDocFileName(String clientDocFileName) {
		this.clientDocFileName = clientDocFileName;
	}
	public String getClientDocContentType() {
		return clientDocContentType;
	}
	public void setClientDocContentType(String clientDocContentType) {
		this.clientDocContentType = clientDocContentType;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public File getOfflineFeedback() {
		return offlineFeedback;
	}
	public void setOfflineFeedback(File offlineFeedback) {
		this.offlineFeedback = offlineFeedback;
	}
	public String getOfflineFeedbackFileName() {
		return offlineFeedbackFileName;
	}
	public void setOfflineFeedbackFileName(String offlineFeedbackFileName) {
		this.offlineFeedbackFileName = offlineFeedbackFileName;
	}
	public Integer getOfflineFeedbackId() {
		return offlineFeedbackId;
	}
	public void setOfflineFeedbackId(Integer offlineFeedbackId) {
		this.offlineFeedbackId = offlineFeedbackId;
	}
	public OutCome getOutCome() {
		return outCome;
	}
	public void setOutCome(OutCome outCome) {
		this.outCome = outCome;
	}
	public List<Clients> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<Clients> storeList) {
		this.storeList = storeList;
	}
	
	
	
}
