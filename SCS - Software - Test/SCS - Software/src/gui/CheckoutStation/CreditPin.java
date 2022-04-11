package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.ChipFailureException;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.GridLayout;

public class CreditPin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPinEntry;
	private String pinBuilder;

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
	public CreditPin(DataPasser dataPass, CreditSelection creditSelection) {
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
		
		JButton btnBackToScanning = new JButton("Go Back");
		panel.add(btnBackToScanning);
		btnBackToScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditSelection.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		
		JLabel lblEnterPin = new JLabel("Please Enter your Pin Number:");
		panel.add(lblEnterPin);
		
		textFieldPinEntry = new JTextField();
		panel.add(textFieldPinEntry);
		textFieldPinEntry.setColumns(10);
		pinBuilder = "";
		JButton btnEnterPin = new JButton("Enter");
		panel.add(btnEnterPin);
		btnEnterPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{ 
					dataPass.makeInsertPayment(new HashMap<String, HashMap<String, String>>(), textFieldPinEntry.getText());
					setVisible(false);
					creditSelection.setVisible(true);
				} catch (Exception e1) {}
			}
		});
		
		JPanel panelTenKey = new JPanel();
		splitPane.setRightComponent(panelTenKey);
		panelTenKey.setLayout(new GridLayout(4, 4, 0, 0));
		
		JButton btnTouch8 = new JButton("8");
		btnTouch8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "8";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		
		JButton btnTouch7 = new JButton("7");
		btnTouch7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "7";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch7);
		panelTenKey.add(btnTouch8);
		JButton btnTouch9 = new JButton("9");
		btnTouch9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "9";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch9);
		
		JButton btnTouch1 = new JButton("1");
		btnTouch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "1";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		
		JButton btnTouch4 = new JButton("4");
		btnTouch4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "4";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch4);
		
		JButton btnTouch5 = new JButton("5");
		btnTouch5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "5";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch5);
		
		JButton btnTouch6 = new JButton("6");
		btnTouch6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "6";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch6);
		panelTenKey.add(btnTouch1);
		
		JButton btnTouch2 = new JButton("2");
		btnTouch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "2";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch2);
		
		JButton btnTouch0 = new JButton("0");
		btnTouch0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "0";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		
		JButton btnTouchClear = new JButton("Clear");
		btnTouchClear.setBackground(Color.CYAN);
		btnTouchClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = "";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		
		JButton btnTouch3 = new JButton("3");
		btnTouch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinBuilder = pinBuilder + "3";
				textFieldPinEntry.setText(pinBuilder);
			}
		});
		panelTenKey.add(btnTouch3);
		panelTenKey.add(btnTouchClear);
		panelTenKey.add(btnTouch0);
	}

}
