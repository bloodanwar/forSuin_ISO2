package presentacion;

import java.awt.Component;

import javax.swing.JOptionPane;

public class YesMockOptionPane extends DefaultOptionPane {
	@Override
	public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
		return JOptionPane.YES_OPTION;
	}
	@Override
	public boolean showErrorFrame() {
		return false;
	}
}