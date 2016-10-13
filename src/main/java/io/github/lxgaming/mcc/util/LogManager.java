package io.github.lxgaming.mcc.util;

import io.github.lxgaming.mcc.frame.MCCFrame;

public class LogManager {
	
	public static void info(String string) {
		System.out.println("[" + Date.getTime() + " INFO]: " + string);
		MCCFrame.OUTPUT.append("[" + Date.getTime() + " INFO]: " + string + "\n");
		MCCFrame.OUTPUT.setCaretPosition(MCCFrame.OUTPUT.getDocument().getLength());
	}
	
	public static void warn(String string) {
		System.out.println("[" + Date.getTime() + " WARN]: " + string);
		MCCFrame.OUTPUT.append("[" + Date.getTime() + " WARN]: " + string + "\n");
		MCCFrame.OUTPUT.setCaretPosition(MCCFrame.OUTPUT.getDocument().getLength());
	}
	
	public static void error(String string) {
		System.out.println("[" + Date.getTime() + " ERROR]: " + string);
		MCCFrame.OUTPUT.append("[" + Date.getTime() + " ERROR]: " + string + "\n");
		MCCFrame.OUTPUT.setCaretPosition(MCCFrame.OUTPUT.getDocument().getLength());
	}
}