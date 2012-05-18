package com.sarxos.ocr.sikulix;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.FileUtils;


/**
 * Read glyphs.
 * 
 * @author Bartosz Firyn (SarXos)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "glyphs")
public class Glyphs {

	/**
	 * Classes to be deserialized by JAXB
	 */
	private static final Class<?>[] CLASSES = new Class[] {
		Glyphs.class,
		Glyph.class,
	};

	/**
	 * Static instance.
	 */
	private static Glyphs instance = new Glyphs();

	/**
	 * XML unmarshaller.
	 */
	private transient Unmarshaller unmarshaller = null;

	/**
	 * Single glyph element to be marshalled as XML element.
	 */
	@XmlElement(name = "glyph")
	private List<Glyph> glyphs = null;

	/**
	 * Constructor required by JAXB. It should not be used.
	 */
	public Glyphs() {
		try {
			JAXBContext context = JAXBContext.newInstance(CLASSES);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get static Glyphs instance.
	 * 
	 * @return
	 */
	public static Glyphs getInstance() {
		return instance;
	}

	public List<Glyph> getGlyphs() {
		return glyphs;
	}

	public List<Glyph> load(String name) {
		File path = new File(name);
		if (path.exists()) {
			if (path.isDirectory()) {
				return loadFromDir(path);
			} else {
				return loadFromGLP(path);
			}
		} else {
			throw new RuntimeException("Path " + name + " does not exist");
		}
	}

	private List<Glyph> loadFromDir(File path) {

		String root = path.getAbsolutePath();

		File p = new File(root + "/glyphs.xml");
		if (!p.exists()) {
			throw new RuntimeException("Missing glyphs file in " + path);
		}

		Object o = null;
		try {
			o = unmarshaller.unmarshal(FileUtils.openInputStream(p));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<Glyph> glyphs = ((Glyphs) o).getGlyphs();
		for (Glyph g : glyphs) {
			g.relativize(root);
		}

		return glyphs;
	}

	private static List<Glyph> loadFromGLP(File p) {
		// TODO read glyps from glp file
		return null;
	}

	public static void main(String[] args) {
		for (Glyph g : Glyphs.getInstance().load("src/main/resources/glyphs/numbers")) {
			System.out.println(g);
		}
	}
}
