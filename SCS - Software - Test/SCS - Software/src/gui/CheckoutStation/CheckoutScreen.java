package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CheckoutScreen extends JFrame {

	private JPanel contentPane;
	public AddBagsScreen plasticBagScreen;
	public MembershipCardScreen memberScreen;
	public JLabel lblPaid;
	public JLabel lblTotal;
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
		CheckoutScreen me = this;
		setTitle("CartMart Checkout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
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
		btnGoToAddBags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plasticBagScreen = new AddBagsScreen(dataPass, me);
				setVisible(false);
				plasticBagScreen.setVisible(true);
			}
		});
		panel.add(btnGoToAddBags);
		
		JButton btnGoToMembership = new JButton("Enter Membership");
		btnGoToMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberScreen = new MembershipCardScreen(dataPass, me);
				setVisible(false);
				memberScreen.setVisible(true);
				
			}
		});
		panel.add(btnGoToMembership);
		
		JPanel panel_payment = new JPanel();
		contentPane.add(panel_payment, BorderLayout.CENTER);
		panel_payment.setLayout(new GridLayout(2, 3, 0, 0));
		
		lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		panel_payment.add(lblTotal);
		
		lblPaid = new JLabel("Paid:");
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

	public void updateLblPaid(String paid){
		lblPaid.setText("Paid:" + paid);
	}
	
	public void updateLblTotal(String paid){
		lblTotal.setText("Paid:" + paid);
	}
	
}
