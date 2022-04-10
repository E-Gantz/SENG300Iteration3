package gui.SupervisionStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import gui.CheckoutStation.DataPasser;

public class HomeScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPasser basic = new DataPasser();
					LoginScreen lFrame = new LoginScreen(basic);
					HomeScreen frame = new HomeScreen(basic,lFrame);

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
	public HomeScreen(DataPasser dataPass, LoginScreen loginFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
// This code can be copied and pasted to add additional tabs for more station, just use find and replace on the number		
		JPanel Station1 = new JPanel();
		tabbedPane.addTab("Station1", null, Station1, null);
		Station1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation1Start = new JButton("Start");
		Station1.add(btnStation1Start);
		
		JButton btnStation1AddInk = new JButton("Add Ink");
		Station1.add(btnStation1AddInk);
		
		JButton btnStation1AddCoins = new JButton("Add Coin");
		Station1.add(btnStation1AddCoins);
		
		JButton btnStation1RemoveCoin = new JButton("<html>Remove<br>Coin</html>");
		Station1.add(btnStation1RemoveCoin);
		
		JButton btnStation1ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station1.add(btnStation1ApproveWeight);
		
		JButton btnStation1Shutdown = new JButton("Shutdown");
		Station1.add(btnStation1Shutdown);
		
		JButton btnStation1AddPaper = new JButton("Add paper");
		Station1.add(btnStation1AddPaper);
		
		JButton btnStation1AddBanknote = new JButton("<html>Add <br>Banknote</html>");
		Station1.add(btnStation1AddBanknote);
		
		JButton btnStation1RemoveBanknote = new JButton("<html>Remove<br>Banknote</html>");
		Station1.add(btnStation1RemoveBanknote);
		
		JButton btnStation1BlockStation = new JButton("<html>Block<br>Station</html>");
		Station1.add(btnStation1BlockStation);
		
		
		
// Start of tab 2		
		JPanel Station2 = new JPanel();
		tabbedPane.addTab("Station2", null, Station2, null);
		Station2.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation2Start = new JButton("Start");
		Station2.add(btnStation2Start);
		
		JButton btnStation2AddInk = new JButton("Add Ink");
		Station2.add(btnStation2AddInk);
		
		JButton btnStation2AddCoin = new JButton("Add Coin");
		Station2.add(btnStation2AddCoin);
		
		JButton btnStation2RemoveCoin = new JButton("<html>Remove<br>Coin</html>");
		Station2.add(btnStation2RemoveCoin);
		
		JButton btnStation2ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station2.add(btnStation2ApproveWeight);
		
		JButton btnStation2Shutdown = new JButton("Shutdown");
		Station2.add(btnStation2Shutdown);
		
		JButton btnStation2AddPaper = new JButton("Add paper");
		Station2.add(btnStation2AddPaper);
		
		JButton btnStation2AddBanknote = new JButton("<html>Add <br>Banknote</html>");
		Station2.add(btnStation2AddBanknote);
		
		JButton btnStation2RemoveBanknote = new JButton("<html>Remove<br>Banknote</html>");
		Station2.add(btnStation2RemoveBanknote);
		
		JButton btnStation2BlockStation = new JButton("<html>Block<br>Station</html>");
		Station2.add(btnStation2BlockStation);
		
		JPanel Station3 = new JPanel();
		tabbedPane.addTab("Station3", null, Station3, null);
		Station3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation3Start = new JButton("Start");
		Station3.add(btnStation3Start);
		
		JButton btnStation3AddInk = new JButton("Add Ink");
		Station3.add(btnStation3AddInk);
		
		JButton btnStation3AddCoin = new JButton("Add Coin");
		Station3.add(btnStation3AddCoin);
		
		JButton btnStation3RemoveCoin = new JButton("<html>Remove<br>Coin</html>");
		Station3.add(btnStation3RemoveCoin);
		
		JButton btnStation3ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station3.add(btnStation3ApproveWeight);
		
		JButton btnStation3Shutdown = new JButton("Shutdown");
		Station3.add(btnStation3Shutdown);
		
		JButton btnStation3AddPaper = new JButton("Add paper");
		Station3.add(btnStation3AddPaper);
		
		JButton btnStation3AddBanknote = new JButton("<html>Add <br>Banknote</html>");
		Station3.add(btnStation3AddBanknote);
		
		JButton btnStation3RemoveBanknote = new JButton("<html>Remove<br>Banknote</html>");
		Station3.add(btnStation3RemoveBanknote);
		
		JButton btnStation3BlockStation = new JButton("<html>Block<br>Station</html>");
		Station3.add(btnStation3BlockStation);
		
		JPanel Station4 = new JPanel();
		tabbedPane.addTab("Station4", null, Station4, null);
		Station4.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation4Start = new JButton("Start");
		Station4.add(btnStation4Start);
		
		JButton btnStation4AddInk = new JButton("Add Ink");
		Station4.add(btnStation4AddInk);
		
		JButton btnStation4AddCoin = new JButton("Add Coin");
		Station4.add(btnStation4AddCoin);
		
		JButton btnStation4RemoveCoin = new JButton("<html>Remove<br>Coin</html>");
		Station4.add(btnStation4RemoveCoin);
		
		JButton btnStation4ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station4.add(btnStation4ApproveWeight);
		
		JButton btnStation4Shutdown = new JButton("Shutdown");
		Station4.add(btnStation4Shutdown);
		
		JButton btnStation4AddPaper = new JButton("Add paper");
		Station4.add(btnStation4AddPaper);
		
		JButton btnStation4AddBanknote = new JButton("<html>Add <br>Banknote</html>");
		Station4.add(btnStation4AddBanknote);
		
		JButton btnStation4RemoveBanknote = new JButton("<html>Remove<br>Banknote</html>");
		Station4.add(btnStation4RemoveBanknote);
		
		JButton btnStation4BlockStation = new JButton("<html>Block<br>Station</html>");
		Station4.add(btnStation4BlockStation);

		
// Logout Sends you back to login screen		
		JPanel Logout = new JPanel();
		tabbedPane.addTab("Logout", null, Logout, null);
		Logout.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		Logout.add(btnLogout);
	}

}
