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

import java.awt.event.KeyListener;

import io.github.lxgaming.mcc.Configuration;
import io.github.lxgaming.mcc.commands.ClientCommand;
import io.github.lxgaming.mcc.commands.MCCCommand;
import io.github.lxgaming.mcc.util.ClientManager;
import io.github.lxgaming.mcc.util.LogManager;

public class KeyEvent implements KeyListener {

	@Override
	public void keyTyped(java.awt.event.KeyEvent event) {
		if (MCCFrame.INPUT.getText().length() >= 100) {
			event.consume();
		}
		return;
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent event) {
		if (event.isControlDown() == true || event.isAltDown() == true) {
			return;
		}
		
		if (MCCFrame.INPUT.hasFocus() != true) {
			MCCFrame.INPUT.requestFocusInWindow();
			if (java.awt.event.KeyEvent.getKeyText(event.getKeyCode()).length() == 1) {
				MCCFrame.INPUT.setText(MCCFrame.INPUT.getText() + event.getKeyChar());
			}
		}
		return;
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent event) {
		if (event.getKeyCode() == 10) {
			if (MCCFrame.INPUT.getText().length() == 0) {
				LogManager.info("Message cannot be blank");
				return;
			}
			
			if (MCCFrame.INPUT.getText().startsWith(Configuration.COMMANDPREFIX)) {
				MCCCommand.mcc(MCCFrame.INPUT.getText().substring(Configuration.COMMANDPREFIX.length()));
				ClientCommand.client(MCCFrame.INPUT.getText().substring(Configuration.COMMANDPREFIX.length()));
			} else {
				ClientManager.sendMessage(MCCFrame.INPUT.getText());
			}
			MCCFrame.INPUT.setText("");
		}
		return;
	}
}
