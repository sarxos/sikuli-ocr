package com.sarxos.ocr.sikulix.util;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * Display image on screen.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class ImageFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 7468239430795306535L;

	/**
	 * Display image on screen.
	 * 
	 * @param path image path
	 */
	public ImageFrame(String path) {

		File f = new File(path);
		if (!f.exists()) {
			throw new IllegalArgumentException("Image path does not exists: " + path);
		}

		ImageIcon image = new ImageIcon(path);
		JLabel imageLabel = new JLabel(image);

		addKeyListener(this);
		setContentPane(imageLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLocation(0, 0);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ImageFrame("src\\main\\resources\\images\\test.png");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			close();
		}
	}

	/**
	 * Close frame.
	 */
	public void close() {
		WindowEvent we = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
	}
}
