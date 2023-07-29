package Notepad;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
	HOME gui;
	String fileName;
	String fileAddress;
	
	public Function_File(HOME gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		
		gui.textArea.setText("");;
		gui.window.setTitle("New");
		fileName = null;
		fileName = null;
		
	}
	//*************************
	public void open() {
		
		FileDialog fDialog = new FileDialog(gui.window, "Open", FileDialog.LOAD);
		fDialog.setVisible(true);
		
		if(fDialog.getFile() != null) {
			fileName = fDialog.getFile();
			fileAddress = fDialog.getDirectory();
			gui.window.setTitle(fileName);
		}
		System.out.println("File address and file name "+ fileAddress + fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName)); // you need the address to read a file
		
			gui.textArea.setText("");
			
			String line = null;
			
			while((line = br.readLine()) !=null) {
				
				gui.textArea.append(line + "\n");
			}
			br.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File Not Opened!");
		}
	}
	//**************************
	public void save() {
		if(fileName==null) {
			saveAs();
		}
		else {
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName);
				fw.write(gui.textArea.getText());
				gui.window.setTitle(fileName);
				fw.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("SOMETHING WRONG");
			}
		}
	}
	//**************************
	public void saveAs() {
		
		FileDialog fDialog = new FileDialog(gui.window, "Save", FileDialog.SAVE );
		fDialog.setVisible(true);
		
		if(fDialog.getFile()!=null) {
			fileName = fDialog.getFile();
			fileAddress = fDialog.getDirectory();
			gui.window.setTitle(fileName);
		}
		try {
			FileWriter fw = new FileWriter(fileAddress + fileName);
			fw.write(gui.textArea.getText());
			fw.close();
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("SOMETHING WRONG!");
		}
	}
	
	public void exit() {
		System.exit(0);
	}
}
