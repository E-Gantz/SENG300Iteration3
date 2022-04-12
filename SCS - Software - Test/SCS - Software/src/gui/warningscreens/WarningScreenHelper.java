package gui.warningscreens;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class WarningScreenHelper extends Shell {	

	/**
	 * Create the shell.
	 * @param display
	 */
	public WarningScreenHelper(Display display, String textToDisplay) {
		super(display, SWT.SHELL_TRIM);
		setSize(794, 186);

		Label displayedText = new Label(this, SWT.CENTER);
		displayedText.setAlignment(SWT.CENTER);
		displayedText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		displayedText.setText(textToDisplay);
		displayedText.setFont(SWTResourceManager.getFont("Lucida Console", 13, SWT.NORMAL));
		displayedText.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		displayedText.setBounds(33, 21, 697, 64);
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Warning...");
		setSize(500, 2);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void displayInkWarning()
	{
		//display error screen if printer out of ink
		try {
			Display display = Display.getDefault();
			WarningScreenHelper warningScreenHelper = new WarningScreenHelper(display,"\nPrinter out of ink please wait for assistance");
			warningScreenHelper.open();
			warningScreenHelper.layout();
			while (!warningScreenHelper.isDisposed()) { //waiting on attendant class.
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			
			warningScreenHelper.dispose();
		} catch (Exception theBetterE) {
			theBetterE.printStackTrace();
		}
	}

	public void displayPaperWarning()
	{
		//display error screen if printer out of ink
		try {
			Display display = Display.getDefault();
			WarningScreenHelper warningScreenHelper = new WarningScreenHelper(display,"\nPrinter out of paper please wait for assistance");
			//warningScreen.setWarningText("Weight discrepancy found please wait for assistance");
			warningScreenHelper.open();
			warningScreenHelper.layout();
			while (!warningScreenHelper.isDisposed()) { //waiting on attendant class.
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			
			warningScreenHelper.dispose();
		} catch (Exception theBetterE) {
			theBetterE.printStackTrace();
		}
	}
	
	public void displayWeightWarning()
	{
		//display error screen if printer out of ink
		try {
			Display display = Display.getDefault();
			WarningScreenHelper warningScreenHelper = new WarningScreenHelper(display,"\nWeight discrepancy detected please wait for assistance");
			//warningScreen.setWarningText("Weight discrepancy found please wait for assistance");
			warningScreenHelper.open();
			warningScreenHelper.layout();
			while (!warningScreenHelper.isDisposed()) { //waiting on attendant class.
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			
			warningScreenHelper.dispose();
		} catch (Exception theBetterE) {
			theBetterE.printStackTrace();
		}
	}
	
	
}





