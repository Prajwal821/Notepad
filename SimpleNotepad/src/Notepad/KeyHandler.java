package Notepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

	HOME gui;
	
	
	public KeyHandler(HOME gui) {
		this.gui= gui;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
			gui.file.save();
		}
		if(e.isShiftDown() && e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
			gui.file.saveAs();
		}
		if(e.isAltDown() &&  e.getKeyCode()==KeyEvent.VK_F) {
			gui.menuFile.doClick();
		}
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
			gui.edit.undo();
		}
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
			gui.edit.redo();
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
