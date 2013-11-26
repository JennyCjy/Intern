package com.ebay.qa.util;

public class WaitAllLock {

	private int numToWait;

	public WaitAllLock() {
		this(1);
	}

	public WaitAllLock(int numToWait) {
		setNumToWait(numToWait);
	}

	public int getNumToWait() {
		return numToWait;
	}

	private void setNumToWait(int numToWait) {
		this.numToWait = numToWait;
	}

	synchronized public void waitAll() throws InterruptedException {
		synchronized (this) {
			this.wait();
		}
	}

	synchronized public void releaseThis() {
		synchronized (this) {
			numToWait--;
			if (numToWait<=0) {
				this.notify();
			}
		}
	}

}
