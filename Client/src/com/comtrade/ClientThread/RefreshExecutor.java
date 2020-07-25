package com.comtrade.ClientThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RefreshExecutor {
	public static void run() {
		System.out.println("Running: "+ new java.util.Date());
		
	}
	public static void main(String[]args) {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(RefreshExecutor::run, 0, 1, TimeUnit.SECONDS);
	}

}
