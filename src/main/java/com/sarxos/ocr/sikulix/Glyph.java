package com.sarxos.ocr.sikulix;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.sikuli.script.Pattern;

import com.sarxos.ocr.sikulix.util.U;


/**
 * Single glyph to be recognized by OCR.
 * 
 * @author Bartosz Firyn (SarXos)
 */
@XmlRootElement(name = "glyph")
@XmlAccessorType(XmlAccessType.FIELD)
public class Glyph {

	private transient Pattern pattern = null;

	/**
	 * Percentage similarity value.
	 */
	@XmlAttribute(name = "similarity")
	private int similarity = 95;

	/**
	 * Character representing the glyph.
	 */
	@XmlAttribute(name = "char")
	private String character = null;

	/**
	 * Image filename.
	 */
	@XmlAttribute(name = "image")
	private String file = null;

	/**
	 * Relative image path.
	 */
	private transient String path = null;

	public Pattern getPattern() {
		if (pattern == null) {
			pattern = U.pattern(path, (float) similarity / 100);
		}
		return pattern;
	}

	public String getCharacter() {
		return character;
	}

	public String getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "glyph[" + character + "](" + file + ")";
	}

	public void relativize(String path) {
		this.path = path + "/" + file;
	}
}
