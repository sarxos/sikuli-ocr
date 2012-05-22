package com.sarxos.ocr.sikulix;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sarxos.ocr.sikulix.util.ImageFrame;


public class OCRTest {

	private static OCR ocr = null;

	@BeforeClass
	public static void init() {
		OCR.setStoragePath("src/main/resources/glyphs");
		ocr = OCR.getSpec("test");
	}

	@Test
	public void test_read1() {
		ImageFrame f = new ImageFrame("src\\main\\resources\\images\\test.png");
		String text = ocr.read(f.getBounds());
		System.out.println(text);
		Assert.assertEquals("TEST1234", text);
		f.close();
	}

	@Test
	public void test_read2() {
		ImageFrame f = new ImageFrame("src\\main\\resources\\images\\test2.png");
		String text = ocr.read(f.getBounds());
		System.out.println(text);
		Assert.assertEquals("ABCDEFGHIJKL", text);
		f.close();
	}

	@Test
	public void test_read3() {
		ImageFrame f = new ImageFrame("src\\main\\resources\\images\\test3.png");
		String text = ocr.read(f.getBounds());
		System.out.println(text);
		Assert.assertEquals("0123456789", text);
		f.close();
	}

	@Test
	public void test_read4() {
		ImageFrame f = new ImageFrame("src\\main\\resources\\images\\test4.png");
		String text = ocr.read(f.getBounds());
		System.out.println(text);
		Assert.assertEquals("MNOPRSTUVWXYZ", text);
		f.close();
	}

	@Test
	public void test_read5() {
		ImageFrame f = new ImageFrame("src\\main\\resources\\images\\test5.png");
		String text = ocr.read(f.getBounds());
		System.out.println(text);
		Assert.assertEquals("ABCD1234", text);
	}
}
