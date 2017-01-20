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
