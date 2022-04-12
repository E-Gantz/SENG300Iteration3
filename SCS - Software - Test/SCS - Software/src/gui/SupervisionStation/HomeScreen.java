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

import org.lsmr.selfcheckout.devices.OverloadException;

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
					SupervisionDataPasser basic = new SupervisionDataPasser();
					DataPasser basic2 = new DataPasser();
					LoginScreen lFrame = new LoginScreen(basic2,basic);
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
	public HomeScreen(SupervisionDataPasser dataPass, LoginScreen loginFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
// This code can be copied and pasted to add additional tabs for more station, just use find and replace on the number		
		
		final int station1 = 1;
		final int station2 = 2; 
		final int station3 = 3; 
		final int station4 = 4; 
		JPanel Station1 = new JPanel();
		tabbedPane.addTab("Station1", null, Station1, null);
		Station1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation1Start = new JButton("Start");
		Station1.add(btnStation1Start);
		btnStation1Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.startStation(station1);
			}
		});
		
		JButton btnStation1AddInk = new JButton("Refill Ink");
		Station1.add(btnStation1AddInk);
		btnStation1AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addInk(station1);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation1AddCoin = new JButton("Refill Coin");
		Station1.add(btnStation1AddCoin);
		btnStation1AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.refillCoin(station1);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation1ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station1.add(btnStation1ApproveWeight);
		btnStation1ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.approveWeight(station1);
			}
		});
		
		JButton btnStation1Shutdown = new JButton("Shutdown");
		Station1.add(btnStation1Shutdown);
		btnStation1Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.shutdownStation(station1);

			}
		});
		
		JButton btnStation1AddPaper = new JButton("Refill paper");
		Station1.add(btnStation1AddPaper);
		btnStation1AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addPaper(station1);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation1AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station1.add(btnStation1AddBanknote);
		btnStation1AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.refillBankNote(station1);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton btnStation1BlockStation = new JButton("<html>Block<br>Station</html>");
		Station1.add(btnStation1BlockStation);
		btnStation1BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.blockStation(station1);
			}
		});
		
		
		
// Start of tab 2		
		JPanel Station2 = new JPanel();
		tabbedPane.addTab("Station2", null, Station2, null);
		Station2.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation2Start = new JButton("Start");
		Station2.add(btnStation2Start);
		btnStation2Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.startStation(station2);
			}
		});
		
		JButton btnStation2AddInk = new JButton("Refill Ink");
		Station2.add(btnStation2AddInk);
		btnStation2AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addInk(station2);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation2AddCoin = new JButton("Refill Coin");
		Station2.add(btnStation2AddCoin);
		btnStation2AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.refillCoin(station2);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation2ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station2.add(btnStation2ApproveWeight);
		btnStation2ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.approveWeight(station2);
			}
		});
		
		JButton btnStation2Shutdown = new JButton("Shutdown");
		Station2.add(btnStation2Shutdown);
		btnStation2Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.shutdownStation(station2);

			}
		});
		
		JButton btnStation2AddPaper = new JButton("Refill paper");
		Station2.add(btnStation2AddPaper);
		btnStation2AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addPaper(station2);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnStation2AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station2.add(btnStation2AddBanknote);
		btnStation2AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.refillBankNote(station2);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton btnStation2BlockStation = new JButton("<html>Block<br>Station</html>");
		Station2.add(btnStation2BlockStation);
		btnStation2BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.blockStation(station2);
			}
		});
		
		JPanel Station3 = new JPanel();
		tabbedPane.addTab("Station3", null, Station3, null);
		Station3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation3Start = new JButton("Start");
		Station3.add(btnStation3Start);
		btnStation3Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation3AddInk = new JButton("Refill Ink");
		Station3.add(btnStation3AddInk);
		btnStation3AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation3AddCoin = new JButton("Refill Coin");
		Station3.add(btnStation3AddCoin);
		btnStation3AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation3ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station3.add(btnStation3ApproveWeight);
		btnStation3ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation3Shutdown = new JButton("Shutdown");
		Station3.add(btnStation3Shutdown);
		btnStation3Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.shutdownStation(station3);

			}
		});
		
		JButton btnStation3AddPaper = new JButton("Refill paper");
		Station3.add(btnStation3AddPaper);
		btnStation3AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation3AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station3.add(btnStation3AddBanknote);
		btnStation3AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		JButton btnStation3BlockStation = new JButton("<html>Block<br>Station</html>");
		Station3.add(btnStation3BlockStation);
		btnStation3BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel Station4 = new JPanel();
		tabbedPane.addTab("Station4", null, Station4, null);
		Station4.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnStation4Start = new JButton("Start");
		Station4.add(btnStation4Start);
		btnStation4Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation4AddInk = new JButton("Refill Ink");
		Station4.add(btnStation4AddInk);
		btnStation4AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation4AddCoin = new JButton("Refill Coin");
		Station4.add(btnStation4AddCoin);
		btnStation4AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation4ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station4.add(btnStation4ApproveWeight);
		btnStation4ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation4Shutdown = new JButton("Shutdown");
		Station4.add(btnStation4Shutdown);
		btnStation4Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.shutdownStation(station4);
			}
		});
		
		JButton btnStation4AddPaper = new JButton("Refill paper");
		Station4.add(btnStation4AddPaper);
		btnStation4AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStation4AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station4.add(btnStation4AddBanknote);
		btnStation4AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		JButton btnStation4BlockStation = new JButton("<html>Block<br>Station</html>");
		Station4.add(btnStation4BlockStation);
		btnStation4BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		
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
