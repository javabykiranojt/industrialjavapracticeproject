package scheduler;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.Impl.TaskServiceImpl;
import com.kjnext.utility.hibernate.HibernateUtil;
 

public class ForTasks implements Job  

{
	
	@SuppressWarnings("unchecked")
	//
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		 
		List<Tasks> taskList;
		Tasks tempTasks;
		Date checkDate;
		String databaseDate;
		Integer id;
		TaskService tmpTaskService=new TaskServiceImpl();
		String flag="EXPIRED";
		
		
		try{
			 	Date date = new Date();
			 	DateFormat gmtFormat = new SimpleDateFormat("yyyy/MM/dd");
			 	TimeZone istTime = TimeZone.getTimeZone("IST");
			 	gmtFormat.setTimeZone(istTime);
			 	String sysDate=gmtFormat.format(date);
			 		  
			 	taskList=HibernateUtil.getSession().createCriteria(Tasks.class).list();
			 		for(Tasks s: taskList){
			 			id=s.getTaskId();
			 			System.out.println(id);
			 			checkDate=s.getExpiryDate();
			 				if(checkDate!=null){
			 					databaseDate=gmtFormat.format(s.getExpiryDate());
				
			 					if(databaseDate!=null){
			 						if(databaseDate.equals(sysDate)){
			 							tempTasks=tmpTaskService.loadTaskOnTaskId(id);
			 							tempTasks.setStatus(flag);
			 							tmpTaskService.updateTask(tempTasks);
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
	


