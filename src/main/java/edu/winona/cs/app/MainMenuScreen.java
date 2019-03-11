//package edu.winona.cs.app;
//package org.eclipse.ui.internal;
//
//import org.eclipse.swt.widgets.Display;
//
//import java.awt.Image;
//
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.wb.swt.SWTResourceManager;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Canvas;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.PaintEvent;
//import org.eclipse.swt.events.PaintListener;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.GC;
////import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.ImageData;
//import org.eclipse.swt.graphics.ImageLoader;
//import org.eclipse.swt.custom.CLabel;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.ui.internal.ImageCycleFeedbackBase;
//import org.eclipse.ui.internal.AnimationEngine;
//
public class MainMenuScreen {
//	private CLabel label;
//	
//	public MainMenuScreen(Shell shlMainMenu, CLabel item, Image[] images) {
//		super(shlMainMenu, images);
//		label = item;
//	}
//	
//	public void initialize(AnimationEngine engine) {
//		Color background = label.getParent().getBackground();
//		Display display = label.getParent().getDisplay();
//	}
//	
//	public void saveStoppedImage() {
//		org.eclipse.swt.graphics.Image stoppedImage = label.getImage();
//	}
//
//	/**
//	 * Launch the application.
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Display display = Display.getDefault();
//		Shell shlMainMenu = new Shell();
//		shlMainMenu.setBackground(SWTResourceManager.getColor(102, 204, 204));
//		shlMainMenu.setSize(350, 450);
//		shlMainMenu.setText("Main Menu");
//		
//		Button newGameButton = new Button(shlMainMenu, SWT.NONE);
//		newGameButton.setBounds(96, 10, 147, 49);
//		newGameButton.setText("New Game");
//		
//		Button loadGameButton = new Button(shlMainMenu, SWT.NONE);
//		loadGameButton.setBounds(96, 83, 147, 49);
//		loadGameButton.setText("Load Game");
//		
//		Button highScoreButton = new Button(shlMainMenu, SWT.NONE);
//		highScoreButton.setBounds(96, 156, 147, 49);
//		highScoreButton.setText("Check High Scores");
//		
//		Button gameSettingsButton = new Button(shlMainMenu, SWT.NONE);
//		gameSettingsButton.setBounds(96, 231, 147, 49);
//		gameSettingsButton.setText("Game Settings");
//		
//		Button logoutButton = new Button(shlMainMenu, SWT.NONE);
//		logoutButton.setBounds(10, 369, 147, 49);
//		logoutButton.setText("Logout");
//		
//		
//		
//	    
//	      
//	      
//
//		shlMainMenu.open();
//		shlMainMenu.layout();
//		while (!shlMainMenu.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
}
