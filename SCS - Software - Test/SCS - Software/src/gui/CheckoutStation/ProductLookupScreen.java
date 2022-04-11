package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Numeral;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductLookupScreen extends JFrame {

	private JPanel contentPane;
	public Barcode appleBarcode;
	public Numeral[] code1 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.one};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen sTest = new ScanningScreen(basic);
					ProductLookupScreen frame = new ProductLookupScreen(basic, sTest);
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
	public ProductLookupScreen(DataPasser dataPass, ScanningScreen scanScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnBackToScanning = new JButton("Go Back");
		btnBackToScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scanScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		contentPane.add(btnBackToScanning, BorderLayout.NORTH);
		
		JButton btnAddPockyApple = new JButton("Picture of PockyFlavouredApple");
		btnAddPockyApple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String items = "";
				String tempPrice = "";
				appleBarcode = new Barcode(code1);
				dataPass.setLookupBarcode(appleBarcode);
				dataPass.addLookupProduct(appleBarcode);
								
				for (int i = 0; i < dataPass.pcart.getItemNames().size(); i++) {
					items = items + dataPass.pcart.getItemNames().get(i);
					items = items + "\n";
				}
				
				tempPrice = "$" + dataPass.pcart.getTotalPrice().toString();
				
				items = items + "\n" + tempPrice;
				scanScreen.textReciept.setText(items);
				
				scanScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
					
		});
		contentPane.add(btnAddPockyApple, BorderLayout.CENTER);
	}

}
