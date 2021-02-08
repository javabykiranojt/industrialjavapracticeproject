package com.kjnext.dmart.vo;

public class SearchCriteriaTask {

	Integer taskId;

	Integer userEmpId;

	Integer clientId;

	Status status;

	Integer locId;
	
	String taskUniqueName;

	/**
	 * @return the taskUniqueName
	 */
	public String getTaskUniqueName() {
		return taskUniqueName;
	}

	/**
	 * @param taskUniqueName the taskUniqueName to set
	 */
	public void setTaskUniqueName(String taskUniqueName) {
		this.taskUniqueName = taskUniqueName;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getUserEmpId() {
		return userEmpId;
	}

	public void setUserEmpId(Integer userEmpId) {
		this.userEmpId = userEmpId;
	}

}
