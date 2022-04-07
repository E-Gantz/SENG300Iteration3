package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen frame = new ScanningScreen(basic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScanningScreen(DataPasser dataPass) {
		setTitle("CartMart Selfcheckout Scanning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ScanningScreen me = this;
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JButton btnFinishScan = new JButton("Checkout");
		btnFinishScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutScreen = new CheckoutScreen(dataPass, me);
				
				
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
		
		JButton btnEnterPLU = new JButton("<html>Enter <br>PLU Code</html>");
		panel.add(btnEnterPLU);
		
		JButton btnProductLookup = new JButton("<html>Product <br>Lookup</html>");
		panel.add(btnProductLookup);
		
		JButton btnAddBags = new JButton("Add your bags");
		splitPane.setLeftComponent(btnAddBags);
	}

}
