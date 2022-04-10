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
import javax.swing.JSplitPane;
import java.awt.GridLayout;

public class AddBagsScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPBagEntry;
	private String pBagBuilder;

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
	public AddBagsScreen(DataPasser dataPass, CheckoutScreen checkScreen) {
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
				checkScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		
		JLabel lblEnterPBags = new JLabel("Please enter how many plastic bags you require:");
		panel.add(lblEnterPBags);
		
		textFieldPBagEntry = new JTextField();
		panel.add(textFieldPBagEntry);
		textFieldPBagEntry.setColumns(10);
		pBagBuilder = "";
		
		JButton btnEnterPLU = new JButton("Enter");
		panel.add(btnEnterPLU);
		btnEnterPLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setPlasticBags(textFieldPBagEntry.getText());
				
			}
		});
		
		JPanel panelTenKey = new JPanel();
		splitPane.setRightComponent(panelTenKey);
		panelTenKey.setLayout(new GridLayout(4, 4, 0, 0));
		
		JButton btnTouch8 = new JButton("8");
		btnTouch8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "8";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		
		JButton btnTouch7 = new JButton("7");
		btnTouch7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "7";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch7);
		panelTenKey.add(btnTouch8);
		JButton btnTouch9 = new JButton("9");
		btnTouch9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "9";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch9);
		
		JButton btnTouch1 = new JButton("1");
		btnTouch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "1";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		
		JButton btnTouch4 = new JButton("4");
		btnTouch4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "4";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch4);
		
		JButton btnTouch5 = new JButton("5");
		btnTouch5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "5";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch5);
		
		JButton btnTouch6 = new JButton("6");
		btnTouch6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "6";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch6);
		panelTenKey.add(btnTouch1);
		
		JButton btnTouch2 = new JButton("2");
		btnTouch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "2";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch2);
		
		JButton btnTouch0 = new JButton("0");
		btnTouch0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "0";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		
		JButton btnTouchClear = new JButton("Clear");
		btnTouchClear.setBackground(Color.CYAN);
		btnTouchClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = "";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		
		JButton btnTouch3 = new JButton("3");
		btnTouch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBagBuilder = pBagBuilder + "3";
				textFieldPBagEntry.setText(pBagBuilder);
			}
		});
		panelTenKey.add(btnTouch3);
		panelTenKey.add(btnTouchClear);
		panelTenKey.add(btnTouch0);
	}

}
