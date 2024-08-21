import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	JFrame f ;
	JLabel lbUser;
	JTextField tfUser;
	JLabel lbPass;
	JPasswordField tfPass;
	JButton btnLogin;

	public Login(String st) {
		f = new JFrame("ĐĂNG NHẬP");
		Font font1 = new Font("Serif", Font.BOLD, 19);
		lbUser = new JLabel("Username:");
		lbUser.setFont(font1);
		tfUser = new JTextField(10);
		lbPass = new JLabel("Password:");
		tfPass = new JPasswordField(10);
		lbPass.setFont(font1);
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setForeground(Color.WHITE);
		Font font2 = new Font("SansSerif", Font.BOLD, 16);
		btnLogin.setFont(font2);
		
		f.setLocation(300, 300);
		f.setLayout(new GridLayout(3, 1));
		f.add(lbUser);
		f.add(tfUser);
		f.add(lbPass);
		f.add(tfPass);
		f.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = tfUser.getText();
				String password = tfPass.getText();
				try {
					ConnectDB cdb = new ConnectDB();
					cdb.connect();
					Connection conn = DriverManager.getConnection("jdbc:sqlserver://HP;databaseName=Company;user=sa;password=sa");
					PreparedStatement st = conn.prepareStatement("SELECT * FROM Login WHERE Username = ? AND Password = ?");
					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(btnLogin, "Bạn đã đăng nhập thành công.","Đăng nhập thành công", JOptionPane.PLAIN_MESSAGE);
						Company company = new Company("HỆ THỐNG QUẢN LÝ CÔNG TY!");
					} else {
						JOptionPane.showMessageDialog(btnLogin, "Username hoặc password sai! Vui lòng nhập lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		});
		
		f.pack();
		f.setSize(300, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}