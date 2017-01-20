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
