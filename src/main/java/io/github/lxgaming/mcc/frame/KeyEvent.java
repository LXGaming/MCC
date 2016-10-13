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