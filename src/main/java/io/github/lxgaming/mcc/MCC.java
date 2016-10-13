package io.github.lxgaming.mcc;

import io.github.lxgaming.mcc.frame.MCCFrame;
import io.github.lxgaming.mcc.util.ClientManager;
import io.github.lxgaming.mcc.util.Environment;
import io.github.lxgaming.mcc.util.LogManager;

public class MCC {

	public static void main(String[] args) {
		LogManager.info("Minecraft Chat v" + Configuration.VERSION);
		LogManager.info("Author - LX_Gaming");
		LogManager.info("Website - http://lxgaming.github.io");
		LogManager.info(Environment.getJavaVendor() + " - " + Environment.getJavaVersion());
		LogManager.info(Environment.getOSName() + " - " + Environment.getOSVersion() + " - " + Environment.getOSArch());
		Configuration.loadConfig();
		MCCFrame frame = new MCCFrame();
		frame.setVisible(true);
		ClientManager.authenticate();
	}
}