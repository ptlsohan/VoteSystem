package com.java;

import java.util.concurrent.ExecutionException;

public class Driver {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		boolean open=true;
		VotingService ms = new VotingService();
		ms.createMotion("motion1");
		System.out.println("Motion state:"+ms.getState());
		if(ms.openVoting()) {
		System.out.println("Motion state:"+ms.getState());
		
		while(open) {
		open=ms.submitVote();
		}
		ms.closeVoting();
		System.out.println("Motion state:"+ms.getState());
		Thread.sleep(120000);
		ms.closeVoting();
		}
	}

}
