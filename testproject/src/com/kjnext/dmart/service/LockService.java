package com.kjnext.dmart.service;


public interface LockService {
	void LockFunction(Integer uid,String function);
	void UnLockFunction(Integer uid,String function);
	String LockedByUser(String function);
}
