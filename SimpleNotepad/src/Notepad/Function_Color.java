package Notepad;

import java.awt.Color;

public class Function_Color {
  HOME gui;
  
  public Function_Color(HOME gui) {
	  this.gui = gui;
  }
  public void changeColor(String color) {
	   
	  switch(color) {
	  case "White":
		  gui.window.getContentPane().setBackground(Color.white);
		  gui.textArea.setBackground(Color.white);
		  gui.textArea.setForeground(Color.black);
		  break;
	  case "Black":
		  gui.window.getContentPane().setBackground(Color.black);
		  gui.textArea.setBackground(Color.black);
		  gui.textArea.setForeground(Color.white);
		  break;
	  case "Blue":
		  gui.window.getContentPane().setBackground(Color.blue);
		  gui.textArea.setBackground(Color.blue);
		  gui.textArea.setForeground(Color.white);
		  break;
	  case "Green":          // search for rgb color and we can add any color to background.
		  gui.window.getContentPane().setBackground(new Color(11, 219, 18));
		  gui.textArea.setBackground(new Color(11, 219, 18));
		  gui.textArea.setForeground(Color.WHITE);
		  break;
	  }
	  
  }
  
}
