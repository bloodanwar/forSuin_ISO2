package presentacion;

import java.awt.Component;

import javax.swing.JOptionPane;

public interface OptionPane {
	int showConfirmDialog(Component parentComponent, Object message, String title, int optionType);
	boolean showErrorFrame();
}

class DefaultOptionPane implements OptionPane {
	@Override
	public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
		return JOptionPane.showConfirmDialog(parentComponent,message,title,optionType);
	}

	@Override
	public boolean showErrorFrame() {
		return true;
	}
}


