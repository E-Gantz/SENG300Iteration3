package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class CreditSelection extends JFrame {

	private JPanel contentPane;
	public CreditPin creditPin;
	public CardScanScreen cardScan;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen ssTest = new ScanningScreen(basic);
					CheckoutScreen cTest = new CheckoutScreen(basic, ssTest);
					CardScanScreen css = new CardScanScreen(basic, cTest, new HashMap<String, HashMap<String, String>>());
					CreditSelection frame = new CreditSelection(basic, css, new HashMap<String, HashMap<String, String>>(), cTest);
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
	public CreditSelection(DataPasser dataPass, 
			  CardScanScreen css, 
			  HashMap<String,HashMap<String,String>> result,
			  CheckoutScreen checkoutScreen) {
		cardScan = css;
		setTitle("CreditSelection");
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
		CreditSelection me = this;
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
				creditPin = new CreditPin(dataPass, me);
				creditPin.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnInsert);
	}

}
