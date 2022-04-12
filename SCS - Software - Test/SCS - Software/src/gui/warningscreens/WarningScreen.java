package gui.warningscreens;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class WarningScreen extends Shell {
	WarningScreenHelper warningScreenHelper;

	/**
	 * Create the shell.
	 * @param display
	 */
	public WarningScreen() {
		Display display = Display.getDefault();
		warningScreenHelper = new WarningScreenHelper(display, "");
	}
	
	public void displayInkWarning()
	{
		warningScreenHelper.displayInkWarning();
	}

	public void displayPaperWarning()
	{
		warningScreenHelper.displayPaperWarning();
	}
	
	public void displayWeightWarning()
	{
		
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void main(String []args)
	{
		WarningScreen w = new WarningScreen();
		w.displayPaperWarning();
	}
	
}




