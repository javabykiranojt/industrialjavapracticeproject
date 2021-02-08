package scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronScheluder {

	static {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sche = sf.getScheduler();
			sche.start();

			/*************** For Tasks ********************/
			// Define your job(any java class) class name in .class format
			JobDetail job = JobBuilder.newJob(ForTasks.class).withIdentity("hey").build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("hey")
					// Run once a day at midnight 0 0 * * *
					// "0 0 12 * * ?" Fire at 12pm (noon) every day
					// "0/30 * * * * ?" Fire at every 30 seconds every day
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
			sche.scheduleJob(job, trigger);

			/**************** For UserDetail **************************/
			JobDetail job1 = JobBuilder.newJob(ForUserDetail.class).withIdentity("hey1").build();
			Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("hey1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();

			JobDetail job2 = JobBuilder.newJob(ForUserDetail.class).withIdentity("hey1").build();
			Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("hey1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
			// schedule your trigger for job
			sche.scheduleJob(job2, trigger2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}