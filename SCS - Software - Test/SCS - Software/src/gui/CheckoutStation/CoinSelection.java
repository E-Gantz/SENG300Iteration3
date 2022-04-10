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

public class CoinSelection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoinSelection frame = new CoinSelection();
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
	public CoinSelection(DataPasser dataPass, CheckoutScreen checkout) {
		setTitle("Coin Selector");
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
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnClose);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNickel = new JButton("Nickel");
		btnNickel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addNickel();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: " + dataPass.paidString;
				checkout.lblPaid.setText(total);
			}
		});
		panel_1.add(btnNickel);
		
		JButton btnDime = new JButton("Dime");
		btnDime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addDime();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: " + dataPass.paidString;
				checkout.lblPaid.setText(total);
			}
		});
		panel_1.add(btnDime);
		
		JButton btnQuarters = new JButton("Quarter");
		panel_1.add(btnQuarters);
		
		JButton btnLoonie = new JButton("Loonie");
		btnLoonie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addLoonie();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: " + dataPass.paidString;
				checkout.lblPaid.setText(total);
			}
		});
		panel_1.add(btnLoonie);
		
		JButton btnToonie = new JButton("Toonie");
		btnToonie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addToonie();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: " + dataPass.paidString;
				checkout.lblPaid.setText(total);
	
			}
		});
		panel_1.add(btnToonie);
		btnQuarters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addQuarter();
				} catch (DisabledException | OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String total = "Paid: " + dataPass.paidString;
				checkout.lblPaid.setText(total);
			}
		});
	}

}
