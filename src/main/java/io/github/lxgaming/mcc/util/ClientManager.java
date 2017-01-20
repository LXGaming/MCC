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

package io.github.lxgaming.mcc.util;

import java.net.Proxy;

import org.spacehq.mc.auth.service.AuthenticationService;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.packet.ingame.client.ClientChatPacket;
import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

import io.github.lxgaming.mcc.Configuration;
import io.github.lxgaming.mcc.listeners.ConnectionListener;
import io.github.lxgaming.mcc.listeners.MessageListener;

public class ClientManager {
	
	public static AuthenticationService AUTHENTICATION = null;
	public static MinecraftProtocol PROTOCOL = null;
	public static Client CLIENT = null;
	
	public static void authenticate() {
		LogManager.info("Authenticating using AccessToken...");
		
		if (Configuration.EMAIL.equals("") || Configuration.CLIENTTOKEN.equals("") || Configuration.ACCESSTOKEN.equals("")) {
			LogManager.warn("Invalid Credentials.");
			LogManager.info("Use '" + Configuration.COMMANDPREFIX + "Auth Email Password' to authenticate.");
			return;
		}
		
		setAuthentication();
		
		if (AUTHENTICATION == null) {
			LogManager.error("Internal Authentication Error!");
			return;
		}
		
		try {
			AUTHENTICATION.setUsername(Configuration.EMAIL);
			AUTHENTICATION.setPassword(null);
			AUTHENTICATION.setAccessToken(Configuration.ACCESSTOKEN);
			AUTHENTICATION.login();
			LogManager.info("Successfully Authenticated using AccessToken.");
		} catch (Exception ex) {
			LogManager.error("Failed to Authenticate using AccessToken!\n" + ex.getMessage());
			ex.printStackTrace();
			AUTHENTICATION = null;
			return;
		}
		
		Configuration.ACCESSTOKEN = AUTHENTICATION.getAccessToken();
		PROTOCOL = new MinecraftProtocol(AUTHENTICATION.getSelectedProfile(), AUTHENTICATION.getAccessToken());
		Configuration.saveConfig("AccessToken");
		return;
	}
	
	public static void authenticate(String EMAIL, String PASSWORD) {
		LogManager.info("Authenticating using Email and Password...");
		
		if (EMAIL == null) {
			LogManager.warn("Email provided was null, Using Email from Config.");
			EMAIL = Configuration.EMAIL;
		}
		
		if (PASSWORD == null) {
			LogManager.error("Password provided was null!");
			return;
		}
		
		if (EMAIL.equals("") || PASSWORD.equals("")) {
			LogManager.error("Email or Password is Blank!");
			return;
		}
		
		setAuthentication();
		
		if (AUTHENTICATION == null) {
			LogManager.error("Internal Authentication Error!");
			return;
		}
		
		try {
			AUTHENTICATION.setUsername(EMAIL);
			AUTHENTICATION.setPassword(PASSWORD);
			AUTHENTICATION.setAccessToken(null);
			AUTHENTICATION.login();
			LogManager.info("Successfully Authenticated using Email and Password.");
		} catch (Exception ex) {
			LogManager.error("Failed to Authenticate with Email and Password!\n" + ex.getMessage());
			AUTHENTICATION.setPassword(null);
			AUTHENTICATION = null;
			return;
		}
		
		Configuration.EMAIL = EMAIL;
		Configuration.ACCESSTOKEN = AUTHENTICATION.getAccessToken();
		PROTOCOL = new MinecraftProtocol(AUTHENTICATION.getSelectedProfile(), AUTHENTICATION.getAccessToken());
		Configuration.saveConfig("Email and AccessToken");
		return;
	}
	
	public static void connect(String HOST, int PORT) {
		LogManager.info("Connecting to '" + HOST + ":" + PORT + "'...");
		
		if (PROTOCOL == null) {
			LogManager.error("Protocol is null, Try Authenticating first!");
			return;
		}
		
		if (HOST == null || HOST.equals("")) {
			LogManager.error("Host is null or blank!");
			return;
		}
		
		if (CLIENT != null) {
			if (CLIENT.getSession().isConnected() == true) {
				LogManager.warn("Client is currently connected to " + CLIENT.getHost());
				LogManager.info("Use " + Configuration.COMMANDPREFIX + "disconnect");
				return;
			}
			CLIENT.getSession().connect();
			return;
		}
		
		CLIENT = new Client(HOST, PORT, PROTOCOL, new TcpSessionFactory(Proxy.NO_PROXY));
		CLIENT.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, Proxy.NO_PROXY);
		CLIENT.getSession().addListener(new ConnectionListener());
		CLIENT.getSession().addListener(new MessageListener());
		CLIENT.getSession().connect();
	}
	
	public static void disconnect(String message) {
		if (CLIENT == null) {
			LogManager.error("Client is null!");
			return;
		}
		
		if (CLIENT.getSession().isConnected() != true) {
			LogManager.warn("Client is not connected!");
			return;
		}
		CLIENT.getSession().disconnect(message + ".");
		return;
	}
	
	public static void sendMessage(String message) {
		if (PROTOCOL == null || CLIENT == null) {
			LogManager.error("Protocol or Client is null!");
			return;
		}
		
		if (CLIENT.getSession().isConnected() != true) {
			LogManager.warn("Client is not connected!");
			return;
		}
		
		CLIENT.getSession().send(new ClientChatPacket(message));
		return;
	}
	
	private static void setAuthentication() {
		if (Configuration.CLIENTTOKEN.equals("")) {
			AUTHENTICATION = new AuthenticationService();
			Configuration.CLIENTTOKEN = AUTHENTICATION.getClientToken();
			Configuration.saveConfig("ClientToken");
		} else {
			AUTHENTICATION = new AuthenticationService(Configuration.CLIENTTOKEN);
		}
		return;
	}
}
