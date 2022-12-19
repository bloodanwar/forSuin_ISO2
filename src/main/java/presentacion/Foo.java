package presentacion;

import javax.swing.JOptionPane;

public class Foo {
	OptionPane optionPane = new DefaultOptionPane();

	public boolean confirm() {
		return optionPane.showConfirmDialog(null, "choose yes or no", "Please confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}    

	public boolean error() {
		return optionPane.showErrorFrame();
	}   
	
	public void setOptionPane(OptionPane o) {
		this.optionPane = o;
	}
}

