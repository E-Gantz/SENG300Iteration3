package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.devices.OverloadException;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.GridLayout;

public class EnterPLU extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPLUEntry;
	private String pluBuilder;
	public JButton btnEnterPLU;
	public JButton btnBackToScanning;
	public JButton btnTouch0;
	public JButton btnTouch1;
	public JButton btnTouch2;
	public JButton btnTouch3;
	public JButton btnTouch4;
	public JButton btnTouch5;
	public JButton btnTouch6;
	public JButton btnTouch7;
	public JButton btnTouch8;
	public JButton btnTouch9;
	public JButton btnTouchClear;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EnterPLU(DataPasser dataPass, ScanningScreen scanScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnBackToScanning = new JButton("Go Back");
		panel.add(btnBackToScanning);
		btnBackToScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setFound(5);
				scanScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		
		JLabel lblEnterPlu = new JLabel("Please enter in your PLU code:");
		panel.add(lblEnterPlu);
		
		textFieldPLUEntry = new JTextField();
		panel.add(textFieldPLUEntry);
		textFieldPLUEntry.setColumns(10);
		pluBuilder = "";
		btnEnterPLU = new JButton("Enter");
		panel.add(btnEnterPLU);
		btnEnterPLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String items = "";
				String tempPrice = "";
				dataPass.setPLUEntered(textFieldPLUEntry.getText());
				try {
					dataPass.addPLU();
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for (int i = 0; i < dataPass.pcart.getItemNames().size(); i ++) {
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
		
		JPanel panelTenKey = new JPanel();
		splitPane.setRightComponent(panelTenKey);
		panelTenKey.setLayout(new GridLayout(4, 4, 0, 0));
		
		btnTouch8 = new JButton("8");
		btnTouch8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "8";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		
		btnTouch7 = new JButton("7");
		btnTouch7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "7";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch7);
		panelTenKey.add(btnTouch8);
		btnTouch9 = new JButton("9");
		btnTouch9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "9";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch9);
		
		btnTouch1 = new JButton("1");
		btnTouch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "1";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		
		btnTouch4 = new JButton("4");
		btnTouch4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "4";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch4);
		
		btnTouch5 = new JButton("5");
		btnTouch5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "5";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch5);
		
		btnTouch6 = new JButton("6");
		btnTouch6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "6";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch6);
		panelTenKey.add(btnTouch1);
		
		btnTouch2 = new JButton("2");
		btnTouch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "2";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch2);
		
		btnTouch0 = new JButton("0");
		btnTouch0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "0";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		
		btnTouchClear = new JButton("Clear");
		btnTouchClear.setBackground(Color.CYAN);
		btnTouchClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = "";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		
		btnTouch3 = new JButton("3");
		btnTouch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pluBuilder = pluBuilder + "3";
				textFieldPLUEntry.setText(pluBuilder);
			}
		});
		panelTenKey.add(btnTouch3);
		panelTenKey.add(btnTouchClear);
		panelTenKey.add(btnTouch0);
	}

}
