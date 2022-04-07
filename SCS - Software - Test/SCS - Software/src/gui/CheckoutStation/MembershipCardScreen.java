package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;

public class MembershipCardScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMembershipID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembershipCardScreen frame = new MembershipCardScreen();
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
	public MembershipCardScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBackToCheckout = new JButton("Go Back");
		btnBackToCheckout.setForeground(Color.YELLOW);
		btnBackToCheckout.setBackground(Color.RED);
		contentPane.add(btnBackToCheckout);
		
		JLabel lblNewLabel = new JLabel("Please enter your membership number or scan the card:");
		contentPane.add(lblNewLabel);
		
		textFieldMembershipID = new JTextField();
		contentPane.add(textFieldMembershipID);
		textFieldMembershipID.setColumns(10);
		
		JButton btnEnterMembership = new JButton("Enter");
		contentPane.add(btnEnterMembership);
	}

}
