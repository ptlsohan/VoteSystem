package com.java;

import java.util.concurrent.ExecutionException;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VotingService ms = new VotingService();
		ms.createMotion("motion1");
		ms.openVoting();
		ms.closeVoting();

	}

}
