package gui.CheckoutStation;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DebitSelection extends JFrame {

	private JPanel contentPane;
	public DebitPin debitScreen;
	public CheckoutScreen checkout;
 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DebitSelection(DataPasser dataPass, 
						  CardScanScreen css, 
						  HashMap<String,HashMap<String,String>> result,
						  CheckoutScreen checkoutScreen) {
		DebitSelection me = this;
		checkout = checkoutScreen;
		setTitle("GiftSelection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				css.setVisible(true);
				dispose();				
			}
		});
		contentPane.add(btnGoBack);
		
		JButton btnTap = new JButton("Tap");
		btnTap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.makeTapPayment(result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				checkoutScreen.setVisible(true);
				checkoutScreen.updateLblPaid(checkoutScreen.lblTotal.getText().substring(6));
				checkoutScreen.checkPaid();
//				dataPass.paidString = checkoutScreen.lblTotal.getText().substring(6);
				dataPass.paidString = dataPass.totalAmount;
				checkoutScreen.checkPaid();
				dispose();
			}
		});
		contentPane.add(btnTap);
		
		JButton btnSwipe = new JButton("Swipe");
		btnSwipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.makeSwipePayment(result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				checkoutScreen.setVisible(true);
				checkoutScreen.updateLblPaid(checkoutScreen.lblTotal.getText().substring(6));
				checkoutScreen.checkPaid();
//				dataPass.paidString = checkoutScreen.lblTotal.getText().substring(6);
				dataPass.paidString = dataPass.totalAmount;
				checkoutScreen.checkPaid();
				dispose();
			}
		});
		contentPane.add(btnSwipe);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debitScreen = new DebitPin(dataPass, me);
				debitScreen.setVisible(true);
				setVisible(false);
				
			}
		});
		contentPane.add(btnInsert);
	}

}
