package com.sarxos.ocr.sikulix.util;

import java.io.File;

import org.sikuli.script.Pattern;


/**
 * Just another utility class.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class U {

	/**
	 * Pattern from file.
	 * 
	 * @param path - image file path
	 * @param similarity - similarity fraction
	 * @return Pattern created from image
	 */
	public static Pattern pattern(String path, float similarity) {
		return pattern(path).similar(similarity);
	}

	/**
	 * Pattern from file.
	 * 
	 * @param path - image file path
	 * @param similarity - similarity fraction
	 * @return Pattern created from image
	 */
	public static Pattern pattern(String path) {
		return new Pattern(new File(path).getAbsolutePath());
	}
}
