package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckoutScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen ssTest = new ScanningScreen(basic);
					
					CheckoutScreen frame = new CheckoutScreen(basic, ssTest);
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
	public CheckoutScreen(DataPasser dataPass, ScanningScreen scanScreen) {
		setTitle("CartMart Checkout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JButton btnGoToScanning = new JButton("Go Back");
		btnGoToScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scanScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnGoToScanning.setBackground(Color.RED);
		btnGoToScanning.setForeground(Color.YELLOW);
		panel.add(btnGoToScanning);
		
		JButton btnGoToAddBags = new JButton("Add Plastic Bags");
		panel.add(btnGoToAddBags);
		
		JButton btnGoToMembership = new JButton("Enter Membership");
		panel.add(btnGoToMembership);
		
		JPanel panel_payment = new JPanel();
		contentPane.add(panel_payment, BorderLayout.CENTER);
		panel_payment.setLayout(new GridLayout(2, 3, 0, 0));
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		panel_payment.add(lblTotal);
		
		JLabel lblPaid = new JLabel("Paid:");
		panel_payment.add(lblPaid);
		
		JLabel lblPaymentMethod = new JLabel("<html>Choose a payment<br> method or insert coins:</html>");
		panel_payment.add(lblPaymentMethod);
		
		JButton btnEnterBanknotes = new JButton("Banknote");
		panel_payment.add(btnEnterBanknotes);
		
		JButton btnGoToCreditScan = new JButton("Credit");
		panel_payment.add(btnGoToCreditScan);
		
		JButton btnGoToCardScan = new JButton("Gift Card");
		panel_payment.add(btnGoToCardScan);
	}

}
