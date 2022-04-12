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
import javax.swing.SwingConstants;

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
		setTitle("ThE ReAl WoRlD Checkout Machines");

// This code can be copied and pasted to add additional tabs for more station, just use find and replace on the number

		JPanel Station1 = new JPanel();
		tabbedPane.addTab("Station1", null, Station1, null);
		Station1.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnStation1Start = new JButton("Start");
		Station1.add(btnStation1Start);
		btnStation1Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 started");
				dataPass.startStation(1);
			}
		});

		JButton btnStation1AddInk = new JButton("Refill Ink");
		Station1.add(btnStation1AddInk);
		btnStation1AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 ink refilled");
				try {
					dataPass.addInk(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1AddCoin = new JButton("Refill Coin");
		Station1.add(btnStation1AddCoin);
		btnStation1AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 coin refilled");
				try {
					dataPass.refillCoin(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station1.add(btnStation1ApproveWeight);
		btnStation1ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 weight approved");
				dataPass.approveWeight(1);
			}
		});

		JButton btnStation1Shutdown = new JButton("Shutdown");
		Station1.add(btnStation1Shutdown);
		btnStation1Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 shutdown");
				dataPass.shutdownStation(1);
			}
		});

		JButton btnStation1AddPaper = new JButton("<html>Refill<br>Paper<html>");
		Station1.add(btnStation1AddPaper);
		btnStation1AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 paper refilled");
				try {
					dataPass.addPaper(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station1.add(btnStation1AddBanknote);
		btnStation1AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 banknote refilled");
				try {
					dataPass.refillBankNote(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});


		JButton btnStation1BlockStation = new JButton("<html>Block<br>Station</html>");
		Station1.add(btnStation1BlockStation);
		btnStation1BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 blocked");
				dataPass.blockStation(1);
			}
		});

		JButton btnStation1RemoveProduct = new JButton("<html>Remove<br>Product<html>");
		Station1.add(btnStation1RemoveProduct);
		btnStation1RemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.removeProductFromCart(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1LoopupProduct = new JButton("<html>Lookup<br>Product<html>");
		Station1.add(btnStation1LoopupProduct);
		btnStation1LoopupProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addProductToCart(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1EmptyCoinStorage = new JButton("<html>Empty<br>Coin<br>Storage<html>");
		Station1.add(btnStation1EmptyCoinStorage);
		btnStation1EmptyCoinStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 conin storage emptied");
				try {
					dataPass.emptiesCoin(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation1EmptyBanknoteStorage = new JButton("<html>Empty<br>Banknote<br>Storage<html>");
		Station1.add(btnStation1EmptyBanknoteStorage);
		btnStation1EmptyBanknoteStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 1 banknote storage emptied");
				try {
					dataPass.emptiesBanknote(1);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});



// Start of tab 2

		JPanel Station2 = new JPanel();
		tabbedPane.addTab("Station2", null, Station2, null);
		Station2.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnStation2Start = new JButton("Start");
		Station2.add(btnStation2Start);
		btnStation2Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 started");
				dataPass.startStation(2);
			}
		});

		JButton btnStation2AddInk = new JButton("Refill Ink");
		Station2.add(btnStation2AddInk);
		btnStation2AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 ink refilled");
				try {
					dataPass.addInk(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2AddCoin = new JButton("Refill Coin");
		Station2.add(btnStation2AddCoin);
		btnStation2AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 coin refilled");
				try {
					dataPass.refillCoin(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station2.add(btnStation2ApproveWeight);
		btnStation2ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 weight approved");
				dataPass.approveWeight(2);
			}
		});

		JButton btnStation2Shutdown = new JButton("Shutdown");
		Station2.add(btnStation2Shutdown);
		btnStation2Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 shutdown");
				dataPass.shutdownStation(2);
			}
		});

		JButton btnStation2AddPaper = new JButton("<html>Refill<br>Paper<html>");
		Station2.add(btnStation2AddPaper);
		btnStation2AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 paper refilled");
				try {
					dataPass.addPaper(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station2.add(btnStation2AddBanknote);
		btnStation2AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 banknote refilled");
				try {
					dataPass.refillBankNote(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});


		JButton btnStation2BlockStation = new JButton("<html>Block<br>Station</html>");
		Station2.add(btnStation2BlockStation);
		btnStation2BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 blocked");
				dataPass.blockStation(2);
			}
		});

		JButton btnStation2RemoveProduct = new JButton("<html>Remove<br>Product<html>");
		Station2.add(btnStation2RemoveProduct);
		btnStation2RemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.removeProductFromCart(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2LoopupProduct = new JButton("<html>Lookup<br>Product<html>");
		Station2.add(btnStation2LoopupProduct);
		btnStation2LoopupProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addProductToCart(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2EmptyCoinStorage = new JButton("<html>Empty<br>Coin<br>Storage<html>");
		Station2.add(btnStation2EmptyCoinStorage);
		btnStation2EmptyCoinStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 conin storage emptied");
				try {
					dataPass.emptiesCoin(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation2EmptyBanknoteStorage = new JButton("<html>Empty<br>Banknote<br>Storage<html>");
		Station2.add(btnStation2EmptyBanknoteStorage);
		btnStation2EmptyBanknoteStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 2 banknote storage emptied");
				try {
					dataPass.emptiesBanknote(2);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});


// station 3
		JPanel Station3 = new JPanel();
		tabbedPane.addTab("Station3", null, Station3, null);
		Station3.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnStation3Start = new JButton("Start");
		Station3.add(btnStation3Start);
		btnStation3Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 started");
				dataPass.startStation(3);
			}
		});

		JButton btnStation3AddInk = new JButton("Refill Ink");
		Station3.add(btnStation3AddInk);
		btnStation3AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 ink refilled");
				try {
					dataPass.addInk(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3AddCoin = new JButton("Refill Coin");
		Station3.add(btnStation3AddCoin);
		btnStation3AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 coin refilled");
				try {
					dataPass.refillCoin(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station3.add(btnStation3ApproveWeight);
		btnStation3ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 weight approved");
				dataPass.approveWeight(3);
			}
		});

		JButton btnStation3Shutdown = new JButton("Shutdown");
		Station3.add(btnStation3Shutdown);
		btnStation3Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 shutdown");
				dataPass.shutdownStation(3);
			}
		});

		JButton btnStation3AddPaper = new JButton("<html>Refill<br>Paper<html>");
		Station3.add(btnStation3AddPaper);
		btnStation3AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 paper refilled");
				try {
					dataPass.addPaper(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station3.add(btnStation3AddBanknote);
		btnStation3AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 banknote refilled");
				try {
					dataPass.refillBankNote(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});


		JButton btnStation3BlockStation = new JButton("<html>Block<br>Station</html>");
		Station3.add(btnStation3BlockStation);
		btnStation3BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 blocked");
				dataPass.blockStation(3);
			}
		});

		JButton btnStation3RemoveProduct = new JButton("<html>Remove<br>Product<html>");
		Station3.add(btnStation3RemoveProduct);
		btnStation3RemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.removeProductFromCart(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3LoopupProduct = new JButton("<html>Lookup<br>Product<html>");
		Station3.add(btnStation3LoopupProduct);
		btnStation3LoopupProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addProductToCart(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3EmptyCoinStorage = new JButton("<html>Empty<br>Coin<br>Storage<html>");
		Station3.add(btnStation3EmptyCoinStorage);
		btnStation3EmptyCoinStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 conin storage emptied");
				try {
					dataPass.emptiesCoin(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation3EmptyBanknoteStorage = new JButton("<html>Empty<br>Banknote<br>Storage<html>");
		Station3.add(btnStation3EmptyBanknoteStorage);
		btnStation3EmptyBanknoteStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 banknote storage emptied");
				try {
					dataPass.emptiesBanknote(3);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});


// station 4
		JPanel Station4 = new JPanel();
		tabbedPane.addTab("Station4", null, Station4, null);
		Station4.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnStation4Start = new JButton("Start");
		Station4.add(btnStation4Start);
		btnStation4Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 started");
				dataPass.startStation(4);
			}
		});

		JButton btnStation4AddInk = new JButton("Refill Ink");
		Station4.add(btnStation4AddInk);
		btnStation4AddInk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 ink refilled");
				try {
					dataPass.addInk(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4AddCoin = new JButton("Refill Coin");
		Station4.add(btnStation4AddCoin);
		btnStation4AddCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 coin refilled");
				try {
					dataPass.refillCoin(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4ApproveWeight = new JButton("<html>Approve<br>Weight</html>");
		Station4.add(btnStation4ApproveWeight);
		btnStation4ApproveWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 3 weight approved");
				dataPass.approveWeight(3);
			}
		});

		JButton btnStation4Shutdown = new JButton("Shutdown");
		Station4.add(btnStation4Shutdown);
		btnStation4Shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 shutdown");
				dataPass.shutdownStation(4);
			}
		});

		JButton btnStation4AddPaper = new JButton("<html>Refill<br>Paper<html>");
		Station4.add(btnStation4AddPaper);
		btnStation4AddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 paper refilled");
				try {
					dataPass.addPaper(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4AddBanknote = new JButton("<html>Refill <br>Banknote</html>");
		Station4.add(btnStation4AddBanknote);
		btnStation4AddBanknote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 banknote refilled");
				try {
					dataPass.refillBankNote(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4BlockStation = new JButton("<html>Block<br>Station</html>");
		Station4.add(btnStation4BlockStation);
		btnStation4BlockStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 blocked");
				dataPass.blockStation(4);
			}
		});

		JButton btnStation4RemoveProduct = new JButton("<html>Remove<br>Product<html>");
		Station4.add(btnStation4RemoveProduct);
		btnStation4RemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.removeProductFromCart(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4LoopupProduct = new JButton("<html>Lookup<br>Product<html>");
		Station4.add(btnStation4LoopupProduct);
		btnStation4LoopupProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataPass.addProductToCart(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4EmptyCoinStorage = new JButton("<html>Empty<br>Coin<br>Storage<html>");
		Station4.add(btnStation4EmptyCoinStorage);
		btnStation4EmptyCoinStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 conin storage emptied");
				try {
					dataPass.emptiesCoin(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnStation4EmptyBanknoteStorage = new JButton("<html>Empty<br>Banknote<br>Storage<html>");
		Station4.add(btnStation4EmptyBanknoteStorage);
		btnStation4EmptyBanknoteStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Station 4 banknote storage emptied");
				try {
					dataPass.emptiesBanknote(4);
				} catch (OverloadException e1) {
					e1.printStackTrace();
				}
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
