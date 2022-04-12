package gui.SupervisionStation;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.CheckoutStation.DataPasser;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField loginID;
	public HomeScreen loggedIn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					SupervisionDataPasser basic2 = new SupervisionDataPasser();
					LoginScreen frame = new LoginScreen(basic, basic2);
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
	public LoginScreen(DataPasser dataPass, SupervisionDataPasser superDataPass) {
		setTitle("SupervisionStation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// This is important to be able to return on logout
		LoginScreen me = this;
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String employeeId = loginID.getText();
				// Insert login class here
				dataPass.setEmployeeIDLogin(employeeId);
				loginID.setText("");
				loggedIn = new HomeScreen(superDataPass, me);
				
				
				loggedIn.setVisible(true);
				setVisible(false);
				
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Enter your Employee ID:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		loginID = new JTextField();
		GridBagConstraints gbc_loginID = new GridBagConstraints();
		gbc_loginID.insets = new Insets(0, 0, 5, 5);
		gbc_loginID.gridx = 3;
		gbc_loginID.gridy = 3;
		contentPane.add(loginID, gbc_loginID);
		loginID.setColumns(10);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 4;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		JButton btnShutdown = new JButton("Shutdown");
		btnShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Shutting Down a machine goes here
				setVisible(false);
				dispose();
			}
		});
		GridBagConstraints gbc_btnShutdown = new GridBagConstraints();
		gbc_btnShutdown.gridx = 6;
		gbc_btnShutdown.gridy = 8;
		contentPane.add(btnShutdown, gbc_btnShutdown);
	}

}
