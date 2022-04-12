package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ThanksScreen extends JFrame {

	private JPanel contentPane;
	public DataPasser newData;


	/**
	 * Create the frame.
	 */
	public ThanksScreen(DataPasser dataPass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblThanksForMakingMeDoAllUI = new JLabel("Thanks For Shopping With CartMart SelfCheckoutSystem");
		lblThanksForMakingMeDoAllUI.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblThanksForMakingMeDoAllUI, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.coinrunner.setCheckoutTotal(BigDecimal.ZERO);
				dataPass.pcart.clearCart();
				dataPass.totalPaid = new BigDecimal(0);
				//newData = new DataPasser(dataPass.station, dataPass.payCard, dataPass.giftDB);
				StartScreen newStart = new StartScreen(dataPass);
				newStart.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton, BorderLayout.CENTER);

		
	}

}
