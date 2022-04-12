package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class BanknoteSelection extends JFrame{
	
	private JPanel contentPane;
	public JButton btnClose;
	public JButton btnFive;
	public JButton btnTen;
	public JButton btnTwenty;
	public JButton btnFifty;
	public JButton btnHundred;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public BanknoteSelection(DataPasser dataPass, CheckoutScreen checkout) {
		setTitle("Banknote Selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setFound(8);
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnClose);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		btnFive = new JButton("5");
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addFive();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: $" + dataPass.paidString;
				checkout.lblPaid.setText(total);
				dispose();
			}
		});
		panel_1.add(btnFive);
		
		btnTen = new JButton("10");
		btnTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addTen();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: $" + dataPass.paidString;
				checkout.lblPaid.setText(total);
				dispose();
			}
		});
		panel_1.add(btnTen);
		
		btnTwenty = new JButton("20");
		btnTwenty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addTwenty();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: $" + dataPass.paidString;
				checkout.lblPaid.setText(total);
				dispose();
			}
		});
		panel_1.add(btnTwenty);
		
		btnFifty = new JButton("50");
		btnFifty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addFifty();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: $" + dataPass.paidString;
				checkout.lblPaid.setText(total);
				dispose();
			}
		});
		panel_1.add(btnFifty);
		
		btnHundred = new JButton("100");
		btnHundred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addHundred();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: $" + dataPass.paidString;
				checkout.lblPaid.setText(total);
				dispose();	
			}
		});
		panel_1.add(btnHundred);
		
	}

}
