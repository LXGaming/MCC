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

package io.github.lxgaming.mcc.commands;

import io.github.lxgaming.mcc.util.ClientManager;
import io.github.lxgaming.mcc.util.LogManager;

public class ClientCommand {
	
	public static void client(String command) {
		String[] args = command.split(" ");
		
		if (command.toLowerCase().startsWith("auth")) {
			if (args.length == 1) {
				ClientManager.authenticate();
				return;
			} else if (args.length == 2) {
				ClientManager.authenticate(null, args[1]);
				return;
			} else if (args.length == 3) {
				ClientManager.authenticate(args[1], args[2]);
				return;
			}
			LogManager.warn("Invalid Args!");
			LogManager.info("Usage: Auth <Email> Password");
			return;
		}
		
		if (command.toLowerCase().startsWith("connect")) {
			if (args.length == 1) {
				LogManager.info("Usage: Connect Host <Port>");
				return;
			} else if (args.length == 2) {
				ClientManager.connect(args[1], 25565);
				return;
			} else if (args.length == 3) {
				if (args[2].matches("[0-9]+")) {
					ClientManager.connect(args[1], Integer.valueOf(args[2]));
					return;
				}
			}
			LogManager.warn("Invalid Args!");
			LogManager.info("Usage: Connect Host <Port>");
			return;
		}
		
		if (command.toLowerCase().equals("disconnect")) {
			ClientManager.disconnect("Connection Closed");
			return;
		}
	}
}
