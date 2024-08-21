import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Company extends JFrame implements ActionListener {
	Connection conn;
	JButton luu, thoat, sua, xoa, clear;
	JLabel ten, namsinh, gioitinh, hsl, chucvu, id;
	JTextField tenJT, hslJT, idJT;
	JComboBox gioitinhJC, namsinhJC, chucvuJC;
	JPanel P1a, P1b, P2, main, jpnmain;

	public Company(String st) {
		super(st);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container ct = this.getContentPane();
		ct.setLayout(new BorderLayout());

		JMenuBar mnb = new JMenuBar();
		setJMenuBar(mnb);
		JMenu mnuFile = new JMenu("File");
		JMenuItem mnuItemFileNew = new JMenuItem("New");
		JMenuItem mnuItemFileOpen = new JMenuItem("Open");
		JMenuItem mnuItemFileExit = new JMenuItem("Exit");
		mnuFile.add(mnuItemFileNew);
		mnuFile.add(mnuItemFileOpen);
		mnuFile.addSeparator();
		mnuFile.add(mnuItemFileExit);

		JMenu mnuEdit = new JMenu("Edit");
		JMenuItem mnuItemUndo = new JMenuItem("Undo");
		JMenuItem mnuItemRedo = new JMenuItem("Redo");
		mnuEdit.add(mnuItemUndo);
		mnuEdit.add(mnuItemRedo);

		JMenu mnuHelp = new JMenu("Help");
		JMenuItem mnuItemWelcome = new JMenuItem("Welcome");
		mnuHelp.add(mnuItemWelcome);

		mnb.add(mnuFile);
		mnb.add(mnuEdit);
		mnb.add(mnuHelp);

		mnuItemFileExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "BẠN CÓ CHẮC CHẮN THOÁT HỆ THỐNG KHÔNG?", "Thoát hệ thống",
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_NO_OPTION)
					System.exit(0);
			}

		});

		id = new JLabel("ID nhân sự:");
		ten = new JLabel("Tên nhân sự:");
		namsinh = new JLabel("Năm sinh:");
		gioitinh = new JLabel("Giới tính:");
		hsl = new JLabel("Hệ số lương:");
		chucvu = new JLabel("Chức vụ:");

		idJT = new JTextField();
		tenJT = new JTextField();
		gioitinhJC = new JComboBox();
		gioitinhJC.addItem("Nam");
		gioitinhJC.addItem("Nu");
		namsinhJC = new JComboBox();
		for (int i = 1970; i < 2022; i++)
			namsinhJC.addItem(i);
		hslJT = new JTextField();
		chucvuJC = new JComboBox();
		chucvuJC.addItem("QUAN LY");
		chucvuJC.addItem("THU QUY");
		chucvuJC.addItem("NHAN VIEN");

		main = new JPanel();
		main.setLayout(new GridLayout(2, 1));

		jpnmain = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.RED);
		TitledBorder tborder = BorderFactory.createTitledBorder(border, "Nhập thông tin");
		jpnmain.setBorder(tborder);
		jpnmain.setLayout(new BorderLayout());

		P1a = new JPanel();
		P1a.setLayout(new GridLayout(6, 1));
		P1b = new JPanel();
		P1b.setLayout(new GridLayout(6, 1));
		P1a.add(id);
		P1b.add(idJT);
		P1a.add(ten);
		P1b.add(tenJT);
		P1a.add(namsinh);
		P1b.add(namsinhJC);
		P1a.add(gioitinh);
		P1b.add(gioitinhJC);
		P1a.add(hsl);
		P1b.add(hslJT);
		P1a.add(chucvu);
		P1b.add(chucvuJC);
		P1a.setBackground(Color.YELLOW);

		P2 = new JPanel();
		P2.setLayout(new GridLayout(5, 0));
		Font font = new Font("Serif", Font.BOLD, 14);
		luu = new JButton("LƯU MỚI THÔNG TIN");
		luu.setFont(font);
		sua = new JButton("CHỈNH SỬA");
		sua.setFont(font);
		xoa = new JButton("XÓA THÔNG TIN");
		xoa.setFont(font);
		clear = new JButton("CLEAR");
		clear.setFont(font);
		thoat = new JButton("THOÁT HỆ THỐNG");
		thoat.setFont(font);

		P2.add(luu);
		P2.add(sua);
		P2.add(xoa);
		P2.add(clear);
		P2.add(thoat);

		DefaultTableModel dm = new DefaultTableModel();
		Object[] column = { "ID nhân sự", "Tên nhân sự", "Năm sinh", "Giới tính", "Hệ số lương", "Chức vụ" };
		Object[] row = new Object[6];
		dm.setColumnIdentifiers(column);

		JTable table = new JTable(dm);
		JScrollPane sc = new JScrollPane(table);
		Font font1 = new Font("Serif", Font.BOLD, 12);
		table.setFont(font1);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try { 
					int row = table.getSelectedRow();
					idJT.setText((String)table.getValueAt(row, 0));
					tenJT.setText((String) table.getValueAt(row, 1));
					namsinhJC.setSelectedItem((String) table.getValueAt(row, 2));
					gioitinhJC.setSelectedItem((String) table.getValueAt(row, 3));
					hslJT.setText((String) table.getValueAt(row, 4));
					chucvuJC.setSelectedItem((String) table.getValueAt(row, 5));
				} catch (Exception e1 )
				{
				e1.printStackTrace();	
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		ConnectDB condb = new ConnectDB();
		String sql = "Select * from TTNS order by [He so luong] DESC";
		try {
			ResultSet rs = condb.GetData("TTNS", sql);
			table.removeAll();
			while (rs.next()) {
				Vector vec = new Vector();
				vec.add(rs.getString("ID nhan su"));
				vec.add(rs.getString("Ten nhan su"));
				vec.add(rs.getString("Nam sinh"));
				vec.add(rs.getString("Gioi tinh"));
				vec.add(rs.getString("He so luong"));
				vec.add(rs.getString("Chuc vu"));
				dm.addRow(vec);
			}
			table.setModel(dm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		luu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (idJT.getText().equals("") || tenJT.getText().equals("") || namsinhJC.getSelectedItem().equals("")
						|| gioitinhJC.getSelectedItem().equals("") || hslJT.getText().equals("")
						|| chucvuJC.getSelectedItem().equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu vào!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				
				else {
					try {
						String sqlinsert = "Insert into TTNS values ('" + idJT.getText() + "', '" + tenJT.getText()
						+ "',  '" + namsinhJC.getSelectedItem() + "', '"
						+  gioitinhJC.getSelectedItem() + "', '" + hslJT.getText() + "','"
						+  chucvuJC.getSelectedItem() + "' )";
					int record = condb.executeDB(sqlinsert);
					if (record > 0)
						JOptionPane.showMessageDialog(null, "BẠN ĐÃ HOÀN TẤT NHẬP THÔNG TIN CHO NHÂN VIÊN!",
								"Thành công", JOptionPane.PLAIN_MESSAGE);

					int rowCount = dm.getRowCount();
					// Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
						dm.removeRow(i);
					}

					ResultSet rs = condb.GetData("TTNS", sql);
					table.removeAll();
					while (rs.next()) {
						Vector vec = new Vector();
						vec.add(rs.getString("ID nhan su"));
						vec.add(rs.getString("Ten nhan su"));
						vec.add(rs.getString("Nam sinh"));
						vec.add(rs.getString("Gioi tinh"));
						vec.add(rs.getString("He so luong"));
						vec.add(rs.getString("Chuc vu"));
						dm.addRow(vec);
					}
					table.setModel(dm);
					} catch (Exception e2)
					{
						e2.printStackTrace();
					}
					idJT.setText("");
					tenJT.setText("");
					namsinhJC.setSelectedItem("");
					gioitinhJC.setSelectedItem("");
					hslJT.setText("");
					chucvuJC.setSelectedItem("");

				}
			}
		});
		sua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sqlUpdate = "UPDATE TTNS SET [Ten nhan su]= '" + tenJT.getText() + "',  [Nam sinh]='"
						+  namsinhJC.getSelectedItem() + "',  [Gioi tinh] = '"
						+  gioitinhJC.getSelectedItem() + "', [He so luong]= '" + hslJT.getText()
						+ "', [Chuc vu] ='" +  chucvuJC.getSelectedItem() + "'WHERE [ID nhan su] ='"
						+ idJT.getText() + "'";
				if (idJT.getText().equals("") || tenJT.getText().equals("") || namsinhJC.getSelectedItem().equals("")
						|| gioitinhJC.getSelectedItem().equals("") || hslJT.getText().equals("")
						|| chucvuJC.getSelectedItem().equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu vào!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int record = 0;
						if (JOptionPane.showConfirmDialog(null, "BẠN CÓ CHẮC CHẮN CHỈNH SỬA KHÔNG?", "Sửa thông tin?",
								JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_NO_OPTION)
							record = condb.executeDB(sqlUpdate);
						if (record > 0)
							JOptionPane.showMessageDialog(null, "BẠN ĐÃ HOÀN TẤT CHỈNH SỬA THÔNG TIN CHO NHÂN VIÊN!",
									"Thành công", JOptionPane.PLAIN_MESSAGE);

						int rowCount = dm.getRowCount();
						// Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
							dm.removeRow(i);
						}

						ResultSet rs = condb.GetData("TTNS", sql);
						table.removeAll();
						while (rs.next()) {
							Vector vec = new Vector();
							vec.add(rs.getString("ID nhan su"));
							vec.add(rs.getString("Ten nhan su"));
							vec.add(rs.getString("Nam sinh"));
							vec.add(rs.getString("Gioi tinh"));
							vec.add(rs.getString("He so luong"));
							vec.add(rs.getString("Chuc vu"));
							dm.addRow(vec);
						}
						table.setModel(dm);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					idJT.setText("");
					tenJT.setText("");
					namsinhJC.setSelectedItem("");
					gioitinhJC.setSelectedItem("");
					hslJT.setText("");
					chucvuJC.setSelectedItem("");
				}
			}

		});
		xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sqlDelete = "DELETE FROM TTNS WHERE [ID nhan su] ='" + idJT.getText() + "'";
				if (idJT.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập [ID nhan su] vào!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int record = 0;
						if (JOptionPane.showConfirmDialog(null, "BẠN CÓ CHẮC CHẮN XÓA KHÔNG?", "Xóa thông tin?",
								JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_NO_OPTION)
							record = condb.executeDB(sqlDelete);
						if (record > 0)
							JOptionPane.showMessageDialog(null, "BẠN ĐÃ HOÀN TẤT XÓA THÔNG TIN CHO NHÂN VIÊN!",
									"Thành công", JOptionPane.PLAIN_MESSAGE);

						int rowCount = dm.getRowCount();
						// Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
							dm.removeRow(i);
						}
				
						ResultSet rs = condb.GetData("TTNS",sql);
						table.removeAll();
						while (rs.next()) {
							Vector vec = new Vector();
							vec.add(rs.getString("ID nhan su"));
							vec.add(rs.getString("Ten nhan su"));
							vec.add(rs.getString("Nam sinh"));
							vec.add(rs.getString("Gioi tinh"));
							vec.add(rs.getString("He so luong"));
							vec.add(rs.getString("Chuc vu"));
							dm.addRow(vec);
						}
						table.setModel(dm);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					idJT.setText("");
					tenJT.setText("");
					namsinhJC.setSelectedItem("");
					gioitinhJC.setSelectedItem("");
					hslJT.setText("");
					chucvuJC.setSelectedItem("");

				}
			}

		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
					idJT.setText("");
					tenJT.setText("");
					namsinhJC.setSelectedItem("");
					gioitinhJC.setSelectedItem("");
					hslJT.setText("");
					chucvuJC.setSelectedItem("");
				}

		});
		
		
		ActionListener click1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "BẠN CÓ CHẮC CHẮN THOÁT KHÔNG?", "Thoát hệ thống?",
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_NO_OPTION)
					System.exit(0);
			}
		};
		thoat.addActionListener(click1);
		

		jpnmain.add(P1a, BorderLayout.WEST);
		jpnmain.add(P1b, BorderLayout.CENTER);
		jpnmain.add(P2, BorderLayout.EAST);

		main.add(jpnmain);
		main.add(sc);

		ct.add(main, BorderLayout.CENTER);

		this.pack();
		this.setSize(600, 400);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getActionCommand());

	}

}
