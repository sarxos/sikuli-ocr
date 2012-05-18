package com.sarxos.ocr.sikulix;

import java.util.concurrent.ThreadFactory;


/**
 * Daemon threads factory.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class DaemonThreadFactory implements ThreadFactory {

	/**
	 * How many threads has already been created.
	 */
	private int i = 0;

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "matcher-thread-" + ++i);
		t.setDaemon(true);
		return t;
	}
}