package scheduler;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.utility.hibernate.HibernateUtil;
 

public class ForUserDetail implements Job  

{
	
	@SuppressWarnings("unchecked")
	
	public void execute(JobExecutionContext context)throws JobExecutionException  
	
	{
		 
		List<UserDetail> taskList;
		UserDetail tempUser;
		Date checkDate;
		String databaseDate;
		Integer id;
		UsersService tmpUsersService=new UsersServiceImpl();
		String flag="EXPIRED";
		
		
		try{
			 	Date date = new Date();
			 	DateFormat gmtFormat = new SimpleDateFormat("yyyy/MM/dd");
			 	TimeZone istTime = TimeZone.getTimeZone("IST");
			 	gmtFormat.setTimeZone(istTime);
			 	String sysDate=gmtFormat.format(date);
			 		  
			 	taskList=HibernateUtil.getSession().createCriteria(UserDetail.class).list();
			 		for(UserDetail s: taskList){
				  
				  
			 			id=s.getUserEmpId();
			 			System.out.println(id);
			 			checkDate=s.getExpiryDate();
			 				if(checkDate!=null){
			 					databaseDate=gmtFormat.format(s.getExpiryDate());
				
			 					if(databaseDate!=null){
			 						if(databaseDate.equals(sysDate)){
			 							tempUser=tmpUsersService.getUserDetail(id);
			 							tempUser.setStatus(flag);
			 							tmpUsersService.updateUser(tempUser);
			 						}else{
			 							System.out.println("not found");
			 						}
			 					}
			 				} 
			 		}
			}catch(Exception e){
			e.printStackTrace();
			}
			
		}
		
	
	}
	
