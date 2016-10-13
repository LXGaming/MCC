package io.github.lxgaming.mcc.commands;

import io.github.lxgaming.mcc.Configuration;
import io.github.lxgaming.mcc.util.ClientManager;
import io.github.lxgaming.mcc.util.LogManager;

public class MCCCommand {
	
	public static void mcc(String command) {
		String[] args = command.split(" ");
		
		if (command.toLowerCase().equals("help")) {
			LogManager.info("MCC Commands:");
			LogManager.info("  " + Configuration.COMMANDPREFIX + "Auth - Authenticate with Mojang Servers.");
			LogManager.info("  " + Configuration.COMMANDPREFIX + "Connect - Connect to a Minecraft Server.");
			LogManager.info("  " + Configuration.COMMANDPREFIX + "Disconnect - Disconnect from a Minecraft Server.");
			LogManager.info("  " + Configuration.COMMANDPREFIX + "Theme - Change GUI Theme.");
			LogManager.info("  " + Configuration.COMMANDPREFIX + "Exit - Close the program.");
			return;
		}
		
		if (command.toLowerCase().startsWith("theme")) {
			if (args.length == 1) {
				LogManager.info("MCC Themes:");
				LogManager.info("  White");
				LogManager.info("  Dark");
			} else if (args.length == 2) {
				Configuration.THEME = args[1];
				Configuration.saveConfig("Theme");
				LogManager.info("Restart the application for the theme to take effect.");
			}
			return;
		}
		
		if (command.toLowerCase().equals("exit")) {
			LogManager.info("Exiting...");
			ClientManager.disconnect("Connection Closed");
			System.exit(0);
			return;
		}
	}
}