package com.portal.registrtion.AEC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AECRegistrationPortal {

	private JFrame frame;
	private JTextField textname;
	private JTextField textreg;
	private JTextField textmob;
	private JTextField textcourse;
	private JTextField textemail;
	private JTable Table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AECRegistrationPortal window = new AECRegistrationPortal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public AECRegistrationPortal() throws SQLException {
		initialize();
		table_update();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void table_update() throws SQLException{
		int c;
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/aecregistration","root","");
			stmt = con.prepareStatement("Select * from studentdatabase");
			
			rs =stmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			c= rsm.getColumnCount();
			
			DefaultTableModel dfm = (DefaultTableModel)Table.getModel();
			dfm.setRowCount(0);
			
			while(rs.next()) {
				
				Vector v2= new Vector();
				
				for(int a=1; a<=c; a++) {
					v2.add(rs.getInt("id"));
					v2.add(rs.getString("Name"));
					v2.add(rs.getString("Registration"));
					v2.add(rs.getString("Mobile"));
					v2.add(rs.getString("Course"));
					v2.add(rs.getString("Email"));
				}
				
				dfm.addRow(v2);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 945, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AEC Registration Portal");
		lblNewLabel.setBounds(355, 30, 284, 36);
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registration", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.BLACK));
		panel.setBounds(35, 107, 447, 333);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Name = new JLabel("Name");
		Name.setFont(new Font("Tahoma", Font.BOLD, 15));
		Name.setBounds(29, 40, 125, 16);
		panel.add(Name);
		
		JLabel Registration = new JLabel("Registration no");
		Registration.setFont(new Font("Tahoma", Font.BOLD, 15));
		Registration.setBounds(29, 80, 125, 16);
		panel.add(Registration);
		
		JLabel Mobile = new JLabel("Mobile no");
		Mobile.setFont(new Font("Tahoma", Font.BOLD, 15));
		Mobile.setBounds(29, 121, 125, 16);
		panel.add(Mobile);
		
		JLabel Course = new JLabel("Course");
		Course.setFont(new Font("Tahoma", Font.BOLD, 15));
		Course.setBounds(29, 165, 125, 16);
		panel.add(Course);
		
		JLabel Email = new JLabel("Email Id");
		Email.setFont(new Font("Tahoma", Font.BOLD, 15));
		Email.setBounds(29, 210, 125, 16);
		panel.add(Email);
		
		textname = new JTextField();
		textname.setBounds(184, 37, 237, 22);
		panel.add(textname);
		textname.setColumns(10);
		
		textreg = new JTextField();
		textreg.setColumns(10);
		textreg.setBounds(184, 77, 237, 22);
		panel.add(textreg);
		
		textmob = new JTextField();
		textmob.setColumns(10);
		textmob.setBounds(184, 118, 237, 22);
		panel.add(textmob);
		
		textcourse = new JTextField();
		textcourse.setColumns(10);
		textcourse.setBounds(184, 162, 237, 22);
		panel.add(textcourse);
		
		textemail = new JTextField();
		textemail.setColumns(10);
		textemail.setBounds(184, 207, 237, 22);
		panel.add(textemail);
		
		JButton Add = new JButton("ADD");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Name = textname.getText();
				String Registration = textreg.getText();
				String Mobile = textmob.getText();
				String Course = textcourse.getText();
				String Email = textemail.getText();
				
				Connection con;
				PreparedStatement stmt;
				
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/aecregistration","root","");
					stmt = con.prepareStatement("insert into studentdatabase(Name, Registration, Mobile, Course, Email)values(?,?,?,?,?);");
					stmt.setString(1, Name);
					stmt.setString(2, Registration);
					stmt.setString(3, Mobile);
					stmt.setString(4, Course);
					stmt.setString(5, Email);
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(Add, "Record added");
					table_update();
					
					textname.setText("");
					textreg.setText("");
					textmob.setText("");
					textcourse.setText("");
					textemail.setText("");
					textname.requestFocus();
					
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		Add.setFont(new Font("Tahoma", Font.BOLD, 15));
		Add.setBounds(106, 271, 97, 25);
		panel.add(Add);
		
		JButton Edit = new JButton("EDIT");
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel dfm = (DefaultTableModel)Table.getModel();
				int selectedIndex =Table.getSelectedRow();
				
				Connection con;
				PreparedStatement stmt;
				
				try {
					
					int id = Integer.parseInt(dfm.getValueAt(selectedIndex, 0).toString());
					
					String Name = textname.getText();
					String Registration = textreg.getText();
					String Mobile = textmob.getText();
					String Course = textcourse.getText();
					String Email = textemail.getText();
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/aecregistration","root","");
					stmt = con.prepareStatement("update studentdatabase set Name = ?, Registration = ?, Mobile = ?, Course = ?, Email = ? where Id = ?");
					stmt.setString(1, Name);
					stmt.setString(2, Registration);
					stmt.setString(3, Mobile);
					stmt.setString(4, Course);
					stmt.setString(5, Email);
					stmt.setInt(6, id);
					
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(Add, "Record Updated");
					table_update();
					
					textname.setText("");
					textreg.setText("");
					textmob.setText("");
					textcourse.setText("");
					textemail.setText("");
					textname.requestFocus();
					
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		Edit.setFont(new Font("Tahoma", Font.BOLD, 15));
		Edit.setBounds(215, 271, 97, 25);
		panel.add(Edit);
		
		JButton Delete = new JButton("DELETE");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel dfm = (DefaultTableModel)Table.getModel();
				int selectedIndex =Table.getSelectedRow();
				
				Connection con;
				PreparedStatement stmt;
				
				try {
					
					int id = Integer.parseInt(dfm.getValueAt(selectedIndex, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm Delete","Warning",JOptionPane.YES_NO_OPTION);
					
					if(dialogResult == JOptionPane.YES_OPTION) {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/aecregistration","root","");
					stmt = con.prepareStatement("Delete from studentdatabase where Id = ?");
					
					stmt.setInt(1, id);					
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(Add, "Record Delete");
					table_update();
					
					textname.setText("");
					textreg.setText("");
					textmob.setText("");
					textcourse.setText("");
					textemail.setText("");
					textname.requestFocus();
					}
					
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		Delete.setFont(new Font("Tahoma", Font.BOLD, 15));
		Delete.setBounds(324, 271, 97, 25);
		panel.add(Delete);
		
		Table = new JTable();
		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfm = (DefaultTableModel)Table.getModel();
				int selectedIndex =Table.getSelectedRow();
				
				textname.setText(dfm.getValueAt(selectedIndex, 1).toString());
				textreg.setText(dfm.getValueAt(selectedIndex, 2).toString());
				textmob.setText(dfm.getValueAt(selectedIndex, 3).toString());
				textcourse.setText(dfm.getValueAt(selectedIndex,4).toString());
				textemail.setText(dfm.getValueAt(selectedIndex, 5).toString());
				
			}
		});
		Table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Registration", "Mobile", "Course", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		Table.getColumnModel().getColumn(2).setPreferredWidth(80);
		Table.setBorder(null);
		Table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Table.setFillsViewportHeight(true);
		Table.setColumnSelectionAllowed(true);
		Table.setCellSelectionEnabled(true);
		Table.setBounds(501, 124, 412, 316);
		frame.getContentPane().add(Table);
	}
}
