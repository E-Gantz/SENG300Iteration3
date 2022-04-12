package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MembershipCardScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMembershipID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen sTest = new ScanningScreen(basic);
					CheckoutScreen cTest = new CheckoutScreen(basic, sTest);
					MembershipCardScreen frame = new MembershipCardScreen(basic, cTest);
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
	public MembershipCardScreen(DataPasser dataPass, CheckoutScreen checkoutScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBackToCheckout = new JButton("Go Back");
		btnBackToCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToCheckout.setForeground(Color.YELLOW);
		btnBackToCheckout.setBackground(Color.RED);
		contentPane.add(btnBackToCheckout);
		
		JLabel lblNewLabel = new JLabel("Please scan your membership card:");
		contentPane.add(lblNewLabel);
		
		textFieldMembershipID = new JTextField();
		contentPane.add(textFieldMembershipID);
		textFieldMembershipID.setColumns(10);
		
		JButton btnEnterMembership = new JButton("Enter");
		btnEnterMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setMembershipID(textFieldMembershipID.getText());
				checkoutScreen.setVisible(true);
				dispose();
				
			}
		});
		contentPane.add(btnEnterMembership);
	}

}
