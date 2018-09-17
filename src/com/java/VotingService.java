package com.java;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VotingService {
	Motion m=null;
	ConcurrentHashMap<Boolean,Integer> result=new ConcurrentHashMap<>();
	ExecutorService service= Executors.newCachedThreadPool();
	Set<Integer> voterSet = new HashSet<>();

	public void createMotion(String name) {
		 m = new Motion(name);
	}
	public String getState() {
		return m.getMotionState();
	}
	public boolean openVoting()  {
		if(m.getMotionState()!="Created") {
			System.out.println("Motion not available");
			return false;
		}
		result.put(true, 0);
		result.put(false, 0);
		m.setMotionState("Opened");
		m.setOpenTime(LocalDateTime.now());
		return true;
			
	}
	
	public boolean submitVote() throws InterruptedException, ExecutionException {
		if(m.getMotionState()!="Opened") {
			System.out.println("Motion is not open for voting");
			return false;
		}
			Future<Integer> future = service.submit(()->{
				return getInput();
			}) ;
			int choice = future.get();
			if(choice==1) {
				result.put(true, result.get(true)+1);
				
			}else if(choice ==0) {
				result.put(false, result.get(false)+1);
			}
			if(voterSet.size()==3) {
				return false;
			}
		return true;
	}
	
	public void closeVoting() {
		LocalDateTime time = LocalDateTime.now();
		long diff=m.getOpenTime().until(time,ChronoUnit.MINUTES);
	if (diff<15) {
		System.out.println("Cannot close voting system \n Time Elapsed<15min");
		return;
	}
		m.setMotionState("Closed");
		getResult();
	}
	public int getInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your voterId:");
		int id = Integer.parseInt(sc.nextLine());
		if(voterSet.contains(id)) {
			System.out.println("You have already voted");
			return -1;
		}
		voterSet.add(id);
		System.out.println("Enter your vote(y/n)");
		char vote = sc.nextLine().charAt(0);
		
		return vote=='y' || vote=='Y'?1:0;
	}
	
	public void getResult() {
		if(result.get(true)==result.get(false)) {
			System.out.println(m.getName() +" TIED");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter VP's vote(y/n)");
			char vpVote = sc.nextLine().charAt(0);
			sc.close();
			if(vpVote=='y' || vpVote=='Y') {
				System.out.println(m.getName() +" PASSED");
			}
			else {
				System.out.println(m.getName() +" FAILED");
			}
			
		}
		else if(result.get(true)>result.get(false)) {
			System.out.println(m.getName() +" PASSED");
		}else {
			System.out.println(m.getName() +" FAILED");
		}
	
	}
}
