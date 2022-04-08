package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBagsScreen extends JFrame {

	private JPanel contentPane;
	public JTextField textBagNumber;

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
					AddBagsScreen frame = new AddBagsScreen(basic, cTest);
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
	public AddBagsScreen(DataPasser dataPass, CheckoutScreen checkoutScreen) {
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
		
		JLabel lbAddPlastic = new JLabel("Please enter the number of plastic bags used:");
		contentPane.add(lbAddPlastic);
		
		textBagNumber = new JTextField();
		contentPane.add(textBagNumber);
		textBagNumber.setColumns(10);
		
		JButton btnEnterBags = new JButton("Enter");
		btnEnterBags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textBagNumber.getText() != "") {
				dataPass.setPlasticBags(textBagNumber.getText());
				}
			}
		});
		contentPane.add(btnEnterBags);
	}

}
