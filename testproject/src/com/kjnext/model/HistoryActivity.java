package com.kjnext.model;

import java.util.Date;

public class HistoryActivity {
	private Date date;
	private String activityDetails;
	
	
	public String getActivityDetails() {
		return activityDetails;
	}
	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
