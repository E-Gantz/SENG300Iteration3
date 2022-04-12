package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GiftSelection extends JFrame {

	private JPanel contentPane;

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
					GiftSelection frame = new GiftSelection(basic, css);
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
	public GiftSelection(DataPasser dataPass, CardScanScreen css) {
		setTitle("GiftSelection");
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
		
		JButton btnTap = new JButton("Tap");
		contentPane.add(btnTap);
		
		JButton btnSwipe = new JButton("Swipe");
		contentPane.add(btnSwipe);
		
		JButton btnInsert = new JButton("Insert");
		contentPane.add(btnInsert);
	}

}
