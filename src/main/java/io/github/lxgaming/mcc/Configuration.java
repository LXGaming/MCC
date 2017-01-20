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

package io.github.lxgaming.mcc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import io.github.lxgaming.mcc.util.LogManager;

public class Configuration {
	
	public static File CONFIGFILE = new File("config.json");
	public static JSONObject CONFIG = null;
	public static String VERSION = "0.1.0";
	public static String EMAIL = "";
	public static String CLIENTTOKEN = "";
	public static String ACCESSTOKEN = "";
	public static String CONNECTMESSAGE = "true";
	public static String COMMANDPREFIX = "!";
	public static String FONTNAME = "Segoe UI";
	public static String FONTSIZE = "12"; 
	public static String THEME = "light";
	
	public static void loadConfig() {
		LogManager.info("Loading Configuration File...");
		if (!CONFIGFILE.exists()) {
			saveConfig("Creating");
		}
		
		try {
			CONFIG = new JSONObject(new String(Files.readAllBytes(Paths.get(CONFIGFILE.getPath())), "UTF-8"));
			EMAIL = CONFIG.getString("Email");
			CLIENTTOKEN = CONFIG.getString("ClientToken");
			ACCESSTOKEN = CONFIG.getString("AccessToken");
			CONNECTMESSAGE = CONFIG.getString("ConnectMessage");
			COMMANDPREFIX = CONFIG.getString("CommandPrefix");
			FONTNAME = CONFIG.getString("FontName");
			FONTSIZE = CONFIG.getString("FontSize");
			THEME = CONFIG.getString("Theme");
			LogManager.info("Configuration File Successfully Loaded");
		} catch (Exception ex) {
			LogManager.error("Configuration File Failed to Load");
		}
		return;
	}
	
	public static void saveConfig(String comment) {
		try {
			Files.write(Paths.get(CONFIGFILE.getPath()), new JSONObject()
					.put("Email", EMAIL)
					.put("ClientToken", CLIENTTOKEN)
					.put("AccessToken", ACCESSTOKEN)
					.put("ConnectMessage", CONNECTMESSAGE)
					.put("CommandPrefix", COMMANDPREFIX)
					.put("FontName", FONTNAME)
					.put("FontSize", FONTSIZE)
					.put("Theme", THEME)
					.toString().getBytes());
			LogManager.info("Configuration File Successfully Saved - " + comment + ".");
		} catch (Exception ex) {
			LogManager.error("Configuration File Failed to Save - " + comment + ".");
		}
		return;
	}
}
