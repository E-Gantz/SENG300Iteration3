package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.Card;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class CardScanScreen extends JFrame {

	private JPanel panel1;
	public CheckoutScreen checkoutScreen;
	public CreditSelection credit;
	public DebitSelection debit;
	public GiftSelection gift;
	public Card cardToPayWith;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					HashMap<String,HashMap<String,String>> basicRes = new HashMap<String,HashMap<String,String>>();
					ScanningScreen ssTest = new ScanningScreen(basic);
					CheckoutScreen cTest = new CheckoutScreen(basic, ssTest);
					CardScanScreen frame = new CardScanScreen(basic, cTest,basicRes);
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
	public CardScanScreen(DataPasser dataPass, 
						  CheckoutScreen checkout,
						  HashMap<String, HashMap<String, String>> hashMap) {
		CardScanScreen me = this;
		checkoutScreen = checkout;
		setTitle("Select a card type:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				checkout.setVisible(true);
				dispose();
			}
		});
		
		
		panel1.add(btnGoBack);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credit = new CreditSelection(dataPass, me, hashMap, checkoutScreen);
				setVisible(false);
				credit.setVisible(true);
			}
		});
		panel1.add(btnCredit);
		
		JButton btnDebit = new JButton("Debit");
		btnDebit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debit = new DebitSelection(dataPass, me, hashMap, checkoutScreen);
				setVisible(false);
				debit.setVisible(true);
			}
		});
		panel1.add(btnDebit);
		
		JButton btnGift = new JButton("Gift");
		btnGift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gift = new GiftSelection(dataPass, me);
				setVisible(false);
				gift.setVisible(true);
				
			}
		});
		panel1.add(btnGift);
	}
}
