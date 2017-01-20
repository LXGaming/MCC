/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.mcc.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import io.github.lxgaming.mcc.Configuration;
import io.github.lxgaming.mcc.util.LogManager;

public class MCCFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static JPanel MAINPANEL = new JPanel(new BorderLayout());
	private static JPanel BOTTOMPANEL = new JPanel(new BorderLayout());
	public static JTextArea OUTPUT = new JTextArea();
	private static JScrollPane OUTPUTSCROLLPANE = new JScrollPane(OUTPUT);
	public static JTextField INPUT = new JTextField();
	private static JScrollPane INPUTSCROLLPANE = new JScrollPane(INPUT);
	private static KeyEvent KEYEVENT = new KeyEvent();
	
	public MCCFrame() {
		LogManager.info("Loading MCC Frame...");
		setTitle("Minecraft Chat v" + Configuration.VERSION);
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		getContentPane().add(MAINPANEL);
		addKeyListener(KEYEVENT);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			LogManager.error("Failed to set Look and Feel!");
			ex.printStackTrace();
		}
		LogManager.info("MCC Frame Successfully Loaded.");
		return;
	}
	
	private void initComponents() {
		LogManager.info("Initializing Components...");
		setTheme();
		setFont();
		OUTPUT.setEditable(false);
		OUTPUT.addKeyListener(KEYEVENT);
		OUTPUTSCROLLPANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		OUTPUTSCROLLPANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		OUTPUTSCROLLPANE.addKeyListener(KEYEVENT);
		INPUT.setEditable(true);
		INPUT.addKeyListener(KEYEVENT);
		INPUTSCROLLPANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		INPUTSCROLLPANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		INPUTSCROLLPANE.addKeyListener(KEYEVENT);
		BOTTOMPANEL.add(INPUT, BorderLayout.CENTER);
		BOTTOMPANEL.addKeyListener(KEYEVENT);
		MAINPANEL.add(BOTTOMPANEL, BorderLayout.SOUTH);
		MAINPANEL.add(OUTPUTSCROLLPANE, BorderLayout.CENTER);
		MAINPANEL.addKeyListener(KEYEVENT);
		LogManager.info("Successfully Initialized Components.");
		return;
	}
	
	private void setTheme() {
		LogManager.info("Setting Theme...");
		if (Configuration.THEME.equalsIgnoreCase("dark")) {
			LogManager.info("Dark Theme Enabled.");
			OUTPUT.setForeground(Color.WHITE);
			OUTPUT.setBackground(Color.DARK_GRAY);
			INPUT.setForeground(Color.WHITE);
			INPUT.setBackground(Color.DARK_GRAY);
		} else {
			LogManager.info("White Theme Enabled.");
			OUTPUT.setForeground(Color.BLACK);
			OUTPUT.setBackground(Color.WHITE);
			INPUT.setForeground(Color.BLACK);
			INPUT.setBackground(Color.WHITE);
		}
		return;
	}
	
	private void setFont() {
		if (Configuration.FONTSIZE.matches("[0-9]+") && !Configuration.FONTNAME.equals("")) {
			LogManager.info("Setting Custom Font.");
			OUTPUT.setFont(new Font(Configuration.FONTNAME.toLowerCase(), Font.PLAIN, Integer.valueOf(Configuration.FONTSIZE)));
			INPUT.setFont(new Font(Configuration.FONTNAME.toLowerCase(), Font.PLAIN, Integer.valueOf(Configuration.FONTSIZE)));
			return;
		}
		LogManager.info("Using Default Font.");
		return;
	}
}
