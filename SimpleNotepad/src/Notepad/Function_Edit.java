package Notepad;

public class Function_Edit {
	HOME gui;
	public Function_Edit(HOME gui) {
		this.gui = gui;
	}
	public void undo() {
		
		gui.um.undo();
	}
	public void redo() {
		
		gui.um.redo();
	}
}
