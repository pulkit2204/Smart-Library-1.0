package SLibrary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminSection extends JFrame {

	private JPanel contentPane;
	int xx,xy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSection frame = new AdminSection();
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
	public AdminSection() {
		Connect();
		AdminSection_Load();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 462);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x=arg0.getXOnScreen();
				int y=arg0.getYOnScreen();
				AdminSection.this.setLocation(x-xx, y-xy);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx=e.getX();
				xy=e.getY();
			}
		});
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolkit = getToolkit();
		Dimension size=toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel lblNewLabel = new JLabel("LIBRARIAN DETAILS");
		lblNewLabel.setBounds(300, 11, 311, 31);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(29, 79, 56, 25);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		ImageIcon img1 =new ImageIcon(this.getClass().getResource("/Category Pic1.png"));
		lblNewLabel_1.setIcon(img1);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAME");
		lblNewLabel_2.setBounds(29, 118, 100, 28);
		ImageIcon img2 =new ImageIcon(this.getClass().getResource("/Author Name.png"));
		lblNewLabel_2.setIcon(img2);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("USERNAME");
		lblNewLabel_3.setBounds(24, 157, 119, 33);
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		ImageIcon img3 =new ImageIcon(this.getClass().getResource("/Login Pic 1.png"));
		lblNewLabel_3.setIcon(img3);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PASSWORD");
		lblNewLabel_4.setBounds(29, 199, 114, 33);
		lblNewLabel_4.setBorder(null);
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		ImageIcon img4 =new ImageIcon(this.getClass().getResource("/Login Pic 2.png"));
		lblNewLabel_4.setIcon(img4);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("ADDRESS");
		lblNewLabel_5.setBounds(29, 234, 100, 40);
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 16));
		ImageIcon img5 =new ImageIcon(this.getClass().getResource("/Author Address.png"));
		lblNewLabel_5.setIcon(img5);
		contentPane.add(lblNewLabel_5);
	
		txtid = new JTextField();
		txtid.setToolTipText("Enter Librarian ID");
		txtid.setBounds(139, 76, 154, 30);
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(Character.isLetter(c))
				{
					txtid.setEditable(false);
					JOptionPane.showMessageDialog(AdminSection.this,"Enter Numbers Only");
					
				}else {
					txtid.setEditable(true);
				
				}
			}
		});
		txtid.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtid.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setToolTipText("Enter Librarian Name");
		txtname.setBounds(139, 117, 154, 31);
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt1) {
				char n=evt1.getKeyChar();
				if(Character.isDigit(n))
				{
					
					txtname.setEditable(false);
					JOptionPane.showMessageDialog(AdminSection.this,"Enter Aphabets Only");
					
				}else {
					txtname.setEditable(true);
					
				}
			}
		});
		txtname.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtname.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtuser = new JTextField();
		txtuser.setToolTipText("Enter Librarian Username");
		txtuser.setBounds(139, 156, 154, 31);
		txtuser.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtuser.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		JLabel lblNewLabel_6 = new JLabel("");
		txtpassword = new JTextField();
		txtpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN="^[A-Z0-9]{1,30}[a-zA-Z0-9]{1,20}[@#$*&^%][0-9]{1,20}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match=patt.matcher(txtpassword.getText());
				if(!match.matches())
				{lblNewLabel_6.setText("incorrect");
				}else {lblNewLabel_6.setText(" ");}
				}
		
		});
		txtpassword.setToolTipText("Enter Librarian Password");
		txtpassword.setBounds(139, 201, 154, 31);
		txtpassword.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtpassword.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		contentPane.add(txtpassword);
		txtpassword.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setToolTipText("Enter Librarian Address");
		txtaddress.setBounds(139, 243, 154, 34);
		txtaddress.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtaddress.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setToolTipText("Click to ADD Details.");
		btnNewButton.setBounds(29, 294, 119, 49);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String name = txtname.getText();
				String username=txtuser.getText();
				String password = txtpassword.getText();
				String address = txtaddress.getText();
				
				try {
					String query="select * from AdminSection where id='"+id+"'";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            if(rs.next()) {
		            	JOptionPane.showMessageDialog(null,"Data exist already");
		            	txtid.setText("");
						txtname.setText(""); 
						txtuser.setText("");
						txtpassword.setText("");
						txtaddress.setText(""); 
		            }
		            else {
					pst = con.prepareStatement("insert into AdminSection(id,name,username,password,address) values(?,?,?,?,?)");
		            pst.setString(1,id);
					pst.setString(2,name);
					pst.setString(3,username);
					pst.setString(4,password);
					pst.setString(5,address);
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Librarian Added");
						
						txtid.setText("");
						txtname.setText(""); 
						txtuser.setText("");
						txtpassword.setText("");
						txtaddress.setText(""); 
						txtname.requestFocus();
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Error");
					}}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		ImageIcon img6 =new ImageIcon(this.getClass().getResource("/add.png"));
		btnNewButton.setIcon(img6);
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("Click to UPDATE Details.");
		btnNewButton_1.setBounds(211, 294, 132, 49);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String name = txtname.getText();
				String username=txtuser.getText();
				String password = txtpassword.getText();
				String address = txtaddress.getText();
				
				try {
					pst = con.prepareStatement("update AdminSection set name=?, username=?, password=?, address=? where id=?");
					  	
						pst.setString(1,name);
						pst.setString(2,username);
						pst.setString(3,password);
						pst.setString(4,address);
						pst.setString(5,id);
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Librarian Updated");
						txtid.setText("");
						txtname.setText(""); 
						txtuser.setText("");
						txtpassword.setText("");
						txtaddress.setText(""); 
						txtname.requestFocus();
						
					}
					else
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		ImageIcon img7 =new ImageIcon(this.getClass().getResource("/update.png"));
		btnNewButton_1.setIcon(img7);
		btnNewButton_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setToolTipText("Click to DELETE details.");
		btnNewButton_2.setBounds(29, 361, 119, 49);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();

				try {
					pst = con.prepareStatement("delete from AdminSection where id=?");
		           
					pst.setString(1,id);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Librarian Deleted");
						txtid.setText("");
						txtname.setText(""); 
						txtuser.setText("");
						txtpassword.setText("");
						txtaddress.setText(""); 
						txtname.requestFocus();
							
					}
					else
					{
						JOptionPane.showMessageDialog(AdminSection.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
			
		ImageIcon img8 =new ImageIcon(this.getClass().getResource("/trash.png"));
		btnNewButton_2.setIcon(img8);
		btnNewButton_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.setToolTipText("Click to CANCEL.");
		btnNewButton_3.setBounds(211, 360, 132, 49);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin l=new AdminLogin();
				l.setVisible(true);
			}
		});
		ImageIcon img9 =new ImageIcon(this.getClass().getResource("/MainPic8.png"));
		btnNewButton_3.setIcon(img9);
		btnNewButton_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
		btnNewButton_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		contentPane.add(btnNewButton_3);
		
		
		JButton btnNewButton_4 = new JButton("View Table");
		btnNewButton_4.setToolTipText("Click to VIEW TABLE.");
		btnNewButton_4.setBounds(542, 321, 158, 34);
		ImageIcon img10 =new ImageIcon(this.getClass().getResource("/view.png"));
		btnNewButton_4.setIcon(img10);
		btnNewButton_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setBackground(SystemColor.inactiveCaption);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from AdminSection";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            table.setModel(DbUtils.resultSetToTableModel(rs));
		            	
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		contentPane.add(btnNewButton_4);
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Table");
		scrollPane.setBounds(429, 76, 357, 234);
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.orange);
		table.setForeground(Color.white);
		table.setSelectionBackground(Color.black);
		table.setSelectionForeground(Color.yellow);
		table.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,14));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("");
		label.setToolTipText("Close");
		label.setBounds(754, 11, 32, 44);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		ImageIcon img =new ImageIcon(this.getClass().getResource("/close.png"));
		label.setIcon(img);
		contentPane.add(label);
		
		JButton btnNewButton_5 = new JButton("SEARCH");
		btnNewButton_5.setToolTipText("Click here to SEARCH.");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from AdminSection where id=?";
				try {
					
					pst = con.prepareStatement(sql);
					pst.setString(1, txtid.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						
						String add1=rs.getString("name");
						txtname.setText(add1);
						String add2=rs.getString("username");
						txtuser.setText(add2);
						String add4= rs.getString("password");
						txtpassword.setText(add4);
						String add3=rs.getString("address");
						txtaddress.setText(add3);
						rs.close();
						pst.close();	
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Lbrarian ID not Found");
					}					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		});
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		ImageIcon img11 =new ImageIcon(this.getClass().getResource("/Zoom-icon.png"));
		btnNewButton_5.setIcon(img11);
		btnNewButton_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_5.setBounds(300, 75, 119, 32);
		contentPane.add(btnNewButton_5);
		
		
		lblNewLabel_6.setBounds(320, 199, 99, 24);
		contentPane.add(lblNewLabel_6);
		
		setUndecorated(true);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtuser;
	private JTextField txtpassword;
	private JTextField txtaddress;
	private JTable table;

	public void Connect()
	{
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","pulkit");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void AdminSection_Load() {
		int as;
		
		try {
			
			Statement st= con.createStatement();
			rs=st.executeQuery("select * from AdminSection");
	         	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
