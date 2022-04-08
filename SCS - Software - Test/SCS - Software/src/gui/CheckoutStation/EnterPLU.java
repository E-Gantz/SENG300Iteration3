package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterPLU extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPLUEntry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					ScanningScreen sTest = new ScanningScreen(basic);
					EnterPLU frame = new EnterPLU(basic, sTest);
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
	public EnterPLU(DataPasser dataPass, ScanningScreen scanScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		contentPane.add(btnBackToScanning);
		
		JLabel lblEnterPlu = new JLabel("Please enter in your PLU code:");
		contentPane.add(lblEnterPlu);
		
		textFieldPLUEntry = new JTextField();
		contentPane.add(textFieldPLUEntry);
		textFieldPLUEntry.setColumns(10);
		
		JButton btnEnterPLU = new JButton("Enter");
		btnEnterPLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setPLUEntered(textFieldPLUEntry.getText());
				
			}
		});
		contentPane.add(btnEnterPLU);
	}

}
