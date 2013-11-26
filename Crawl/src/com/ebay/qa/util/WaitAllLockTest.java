package com.ebay.qa.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class WaitAllLockTest {

	@Test
	public void test() {
		int numOfThead = 12;
		final WaitAllLock lock = new WaitAllLock(numOfThead);

		for (int i = 0; i < numOfThead; i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						/**
						 * RELEASE HERE
						 */
						lock.releaseThis();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			thread.start();
		}

		try {
			/**
			 * WAIT HERE
			 */
			lock.waitAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(0, lock.getNumToWait());
	}

}
