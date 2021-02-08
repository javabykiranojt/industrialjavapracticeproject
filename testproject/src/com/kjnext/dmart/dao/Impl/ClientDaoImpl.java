package com.kjnext.dmart.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.ClientDao;
import com.kjnext.dmart.hibernate.ClientDetails;
import com.kjnext.dmart.hibernate.ClientDocuments;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.utility.hibernate.HibernateUtil;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.vo.Status;

public class ClientDaoImpl implements ClientDao {

	public void addClient(Clients clients) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(clients);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public void removeClient(Clients clients) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.delete(clients);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public void updateClient(Clients clients) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(clients);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Clients loadClientDetails(Integer clientId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			Clients clients = (Clients) hibernateSession.load(Clients.class,
					clientId);
			return clients;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/** *********** Start by SHIVTEJ ****************** */

	public void addClientDetails(ClientDetails clientDetails) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(clientDetails);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/** ******* End By SHIVTEJ ****************************** */

	// ****** START BY SNEHA
	public List<Location> getLocationList() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<Location> locationList = hibernateSession.createCriteria(
					Location.class).list();
			return locationList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public void addLocationDetails(Location location) {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(location);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public List<LocationArea> getLocationAreaList() {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<LocationArea> locationAreaList = hibernateSession
					.createCriteria(LocationArea.class).list();
			return locationAreaList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public void addLocationAreaDetails(LocationArea locationArea) {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(locationArea);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}
	
	public void reassignFeedback(UserFeedback userFeedback){
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try{
			UserFeedback usrFeedback = (UserFeedback) hibernateSession.load(UserFeedback.class, userFeedback.getFeedbackId());
			usrFeedback.setFeedbackId(userFeedback.getFeedbackId());
			usrFeedback.setStatus(userFeedback.getStatus());

			usrFeedback.setRemarkByClient(userFeedback.getRemarkByClient());
			//usrFeedback.setModifiedDtm(new Date());
			
			//usrFeedback.setSentStatus("N");
			
			
			hibernateSession.update(usrFeedback);
			
		}catch(Exception e){
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession = null;
		}
	}
	// ****** END BY SNEHA

	@SuppressWarnings("unchecked")
	public List<Clients> showAllClients() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<Clients> clientsList = (List<Clients>) HibernateUtil
					.getSession().createCriteria(Clients.class).list();
			List<Clients> lists=new ArrayList<Clients>();
			for(Clients clt:clientsList){
				System.out.println(clt.getUserDetail().getStatus());
				if("ACTIVE".equals(clt.getUserDetail().getStatus())){
							lists.add(clt);											
							System.out.println(clt.getClientName());																																	
				}
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public void addUserDetails(UserDetail userDetail) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(userDetail);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public List<Clients> getClientsOnLocation(Integer locId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<Clients> clientsList = hibernateSession.createCriteria(
					Clients.class).createCriteria("clientDetails")
					.createCriteria("location").add(
					Expression.eq("locId", locId)).list();
			ArrayList<Clients> list=new ArrayList<Clients>();
			for(Clients clt:clientsList){
				System.out.println(clt.getUserDetail().getStatus());
				if("ACTIVE".equals(clt.getUserDetail().getStatus())){
					list.add(clt);
				}
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public List<UserDetail> checkEmailDulication(String emailId) {
		
		System.out.println("checking email duplication for client in clientdaoIpml");
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
			List<UserDetail> checkEmailduplicationList  = hibernateSession
			.createCriteria(UserDetail.class).add(Restrictions.eq("emailId", emailId)).list();
			return checkEmailduplicationList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		
		
		
		//return null;
	}
public List<Clients> getExistedClientsAddPoints() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List<Integer> clientId = new ArrayList<Integer>();
		List<List<Clients>> clientlist = new ArrayList<List<Clients>>();
		List<Clients> clientReturn = new ArrayList<Clients>();
		try {
			// getting data in Task client point Table and getting client id
			// from it
			List<TaskClientPoints> taskclientspointList = hibernateSession
					.createCriteria(TaskClientPoints.class).list();
			for (TaskClientPoints points : taskclientspointList) {
				Integer cid = points.getClients().getClientId();
				System.out.println(cid);
				clientId.add(cid);
			}

			// getting client corresponding to id from client table
			List<Clients> c = null;
			for (Integer cid : clientId) {
				c = hibernateSession.createCriteria(Clients.class).add(
						Restrictions.eq("clientId", cid)).list();
				clientlist.add(c);
			}

			for (List<Clients> ctList : clientlist) {
				System.out.println("333333333333");
				for (Clients ctl1 : ctList) {
					clientReturn.add(ctl1);
				}
			}

			return clientReturn;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

		public List<Clients> viewStores(Integer userEmpId){
			System.out.println("---"+userEmpId);
			Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			try{
				List<Clients> storeList=(List<Clients>)hibernateSession.createCriteria(Clients.class).createCriteria("userDetail").add(Expression.eq("userEmpId", userEmpId)).list();
			
				return storeList;
			}catch(Exception e){
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
			
		}

		public void addClientDocument(ClientDocuments clientDocuments) {
			Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			try{
				hibernateSession.save(clientDocuments);
			}catch(Exception e){
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
			
		}

		public int getDocId(Integer clientId) {
			Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			try{
				Query query= hibernateSession.createSQLQuery("select docId from client_documents where client_id="+clientId);
				 
				List<Integer> list=query.list();
				System.out.println(list.get(0));
				return (Integer)list.get(0);
				
			}catch(Exception e){
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
		}
}
