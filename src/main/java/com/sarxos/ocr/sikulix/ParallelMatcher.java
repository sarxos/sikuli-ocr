package com.sarxos.ocr.sikulix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.sikuli.script.Match;
import org.sikuli.script.Region;


/**
 * Parallel matcher is used to execute matching operation in separate thread.
 * 
 * @author Bartoz Firyn (SarXos)
 */
public class ParallelMatcher implements Callable<List<Match>> {

	/**
	 * Glyph to be recognized
	 */
	private Glyph glyph = null;

	/**
	 * Region in which we should search for given glyph
	 */
	private Region region = null;

	/**
	 * Match - glyph mapping
	 */
	private Map<Match, Glyph> mapping = null;

	/**
	 * Latch
	 */
	private CountDownLatch latch = null;

	/**
	 * Construct me.
	 * 
	 * @param glyph - glyph to be recognized
	 * @param region - region in which we should search for glyps
	 * @param mapping - mapping
	 * @param latch - latch
	 */
	public ParallelMatcher(Glyph glyph, Region region, Map<Match, Glyph> mapping, CountDownLatch latch) {
		this.glyph = glyph;
		this.region = region;
		this.mapping = mapping;
		this.latch = latch;
	}

	@Override
	public List<Match> call() throws Exception {
		try {

			List<Match> matches = new ArrayList<Match>();
			Iterator<Match> all = region.findAllNow(glyph.getPattern());

			if (all == null) {
				return null;
			}

			while (all.hasNext()) {
				Match m = all.next();
				matches.add(m);
				mapping.put(m, glyph);
			}

			return matches;

		} finally {
			latch.countDown();
		}
	}
}
