package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScanningScreen extends JFrame {

	private JPanel contentPane;
	public CheckoutScreen checkoutScreen;
	public AddOwnBag ownBagScreen;
	public EnterPLU pluScreen;
	public ProductLookupScreen productLookupScreen;
	public JTextArea textReciept;
	public JButton btnFinishScan;
	public JButton btnEnterPLU;
	public JButton btnProductLookup;
	public JButton btnAddBags;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ScanningScreen(DataPasser dataPass) {
		setTitle("CartMart Selfcheckout Scanning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ScanningScreen me = this;
		
		textReciept = new JTextArea();
		contentPane.add(textReciept, BorderLayout.CENTER);
		
		btnFinishScan = new JButton("Checkout");
		btnFinishScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutScreen = new CheckoutScreen(dataPass, me);
				dataPass.setFound(4);
				
				setVisible(false);
				checkoutScreen.setVisible(true);
			}
		});
		contentPane.add(btnFinishScan, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		btnEnterPLU = new JButton("<html>Enter <br>PLU Code</html>");
		btnEnterPLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluScreen = new EnterPLU(dataPass, me);
				setVisible(false);
				pluScreen.setVisible(true);
				dataPass.setFound(2);
			}
		});
		panel.add(btnEnterPLU);
		
		btnProductLookup = new JButton("<html>Product <br>Lookup</html>");
		btnProductLookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productLookupScreen = new ProductLookupScreen(dataPass, me);
				setVisible(false);
				productLookupScreen.setVisible(true);
			}
		});
		
		panel.add(btnProductLookup);
		
		btnAddBags = new JButton("Add your bags");
		btnAddBags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownBagScreen = new AddOwnBag(dataPass, me);
				setVisible(false);
				ownBagScreen.setVisible(true);
				dataPass.setFound(3);
			}
		});
		splitPane.setLeftComponent(btnAddBags);
	}
	
	public void updateScreen(String reciept) {
		textReciept.setText(reciept);
	}

}
