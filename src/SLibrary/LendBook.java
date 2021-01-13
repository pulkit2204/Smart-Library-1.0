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

public class LendBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtdate;
	private JTextField txtrdate;
	int xx,xy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendBook frame = new LendBook();
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
	public LendBook() {
		Connect();
		LendBook_Load();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 462);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x=arg0.getXOnScreen();
				int y=arg0.getYOnScreen();
				LendBook.this.setLocation(x-xx, y-xy);
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
		
		JLabel lblNewLabel = new JLabel("Issue Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(368, 11, 153, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MEMBER ID ");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(10, 71, 161, 31);
		ImageIcon img1 =new ImageIcon(this.getClass().getResource("/Category Pic1.png"));
		lblNewLabel_1.setIcon(img1);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c))
				{
					txtid.setEditable(false);
					JOptionPane.showMessageDialog(LendBook.this,"Enter Numbers Only");
					
				}else {
					txtid.setEditable(true);
				
				}
			}
		});
		txtid.setToolTipText("Enter MEMBER ID");
		txtid.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtid.setBounds(171, 68, 101, 36);
		txtid.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MEMBER NAME");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(10, 124, 153, 31);
		ImageIcon img2 =new ImageIcon(this.getClass().getResource("/Author Name.png"));
		lblNewLabel_2.setIcon(img2);
		contentPane.add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {
				char c=e1.getKeyChar();
				if(Character.isDigit(c))
				{
					txtname.setEditable(false);
					JOptionPane.showMessageDialog(LendBook.this,"Enter Alphabets Only");
					
				}else {
					txtname.setEditable(true);
				
				}
			}
		});
		txtname.setToolTipText("Enter MEMBER NAME");
		txtname.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtname.setBounds(171, 121, 175, 36);
		txtname.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("BOOK NAME");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(10, 179, 137, 31);
		ImageIcon img3 =new ImageIcon(this.getClass().getResource("/Main Pic4.png"));
		lblNewLabel_3.setIcon(img3);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ISSUE DATE ");
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(10, 221, 137, 44);
		ImageIcon img4 =new ImageIcon(this.getClass().getResource("/date.png"));
		lblNewLabel_4.setIcon(img4);
		contentPane.add(lblNewLabel_4);
		
		txtdate = new JTextField();
		txtdate.setToolTipText("ENTER ISSUE DATE");
		txtdate.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtdate.setBounds(171, 225, 175, 36);
		txtdate.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtdate);
		txtdate.setColumns(10);
		
		txtrdate = new JTextField();
		txtrdate.setToolTipText("Enter RETURN DATE");
		txtrdate.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtrdate.setBounds(171, 272, 175, 36);
		txtrdate.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtrdate);
		txtrdate.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("RETURN DATE ");
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_5.setBounds(10, 278, 161, 25);
		ImageIcon img5 =new ImageIcon(this.getClass().getResource("/Return Date.png"));
		lblNewLabel_5.setIcon(img5);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Issue");
		btnNewButton.setToolTipText("Click to ISSUE Book.");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img91 =new ImageIcon(this.getClass().getResource("/add.png"));
		btnNewButton.setIcon(img91);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtid.getText();
				String name = txtname.getText();
				String book = txtbook.getText();
				String b_date= txtdate.getText();
				String r_date= txtrdate.getText();
				try {
					String query="select * from ISSUEBOOK where id='"+id+"'";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            if(rs.next()) {
		            	JOptionPane.showMessageDialog(null,"Data exist already");
		            	txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtdate.setText(""); 
						txtrdate.setText("");
		            }
		            else {
					pst = con.prepareStatement("insert into ISSUEBOOK(id,name,book,b_date,r_date) values(?,?,?,?,?)");
		            pst.setString(1,id);
					pst.setString(2,name);
					pst.setString(3,book);
					pst.setString(4,b_date);
					pst.setString(5,r_date);
					
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(LendBook.this,"Book Issued");
						
						txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtdate.setText(""); 
						txtrdate.setText(""); 
						txtname.requestFocus();
					}
					else
					{
						JOptionPane.showMessageDialog(LendBook.this,"Error");
					}}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton.setBounds(48, 334, 115, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("Click to UPDATE Details.");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img10 =new ImageIcon(this.getClass().getResource("/update.png"));
		btnNewButton_1.setIcon(img10);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String name = txtname.getText();
				String book = txtbook.getText();
				String b_date= txtdate.getText();
				String r_date= txtrdate.getText();				
				
				try {
						pst = con.prepareStatement("update ISSUEBOOK set name=?, book=?, b_date=? , r_date=?  where id=?");
					 	
						pst.setString(1,name);
						pst.setString(2,book);
						pst.setString(3,b_date);
						pst.setString(4,r_date);
						pst.setString(5,id);
					
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(LendBook.this,"Book Updated");
						txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtdate.setText(""); 
						txtrdate.setText(""); 
						txtname.requestFocus();
					}
					else
					{
						JOptionPane.showMessageDialog(LendBook.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_1.setBounds(222, 334, 124, 42);
		contentPane.add(btnNewButton_1);
		ImageIcon img11 =new ImageIcon(this.getClass().getResource("/trash.png"));
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setToolTipText("Click to CANCEL.");
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img12 =new ImageIcon(this.getClass().getResource("/back-icon.png"));
		btnNewButton_3.setIcon(img12);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main m=new Main();
				m.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_3.setBounds(153, 389, 124, 46);
		contentPane.add(btnNewButton_3);
		
		txtbook = new JTextField();
		txtbook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e2) {
				char c=e2.getKeyChar();
				if(Character.isDigit(c))
				{
					txtbook.setEditable(false);
					JOptionPane.showMessageDialog(LendBook.this,"Enter Alphabets Only");
					
				}else {
					txtbook.setEditable(true);
				
				}
			}
		});
		txtbook.setToolTipText("Enter BOOK NAME");
		txtbook.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtbook.setBounds(171, 176, 175, 36);
		txtbook.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtbook);
		txtbook.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("TABLE");
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(453, 66, 414, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.orange);
		table.setForeground(Color.white);
		table.setSelectionBackground(Color.black);
		table.setSelectionForeground(Color.yellow);
		table.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,14));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_4 = new JButton("View Table");
		btnNewButton_4.setToolTipText("Click to VIEW TABLE");
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setBackground(SystemColor.inactiveCaption);
		btnNewButton_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img9 =new ImageIcon(this.getClass().getResource("/view.png"));
		btnNewButton_4.setIcon(img9);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from ISSUEBOOK";
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
		btnNewButton_4.setBounds(556, 361, 153, 36);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setToolTipText("Close");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_9.setBounds(835, 11, 32, 44);
		ImageIcon img =new ImageIcon(this.getClass().getResource("/close.png"));
		lblNewLabel_9.setIcon(img);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton_5 = new JButton("SEARCH");
		btnNewButton_5.setToolTipText("Click here to SEARCH.");
		ImageIcon img14 =new ImageIcon(this.getClass().getResource("/Zoom-icon.png"));
		btnNewButton_5.setIcon(img14);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from ISSUEBOOK where id=?";
				try {
					
					pst = con.prepareStatement(sql);
					pst.setString(1, txtid.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						
						String add1=rs.getString("name");
						txtname.setText(add1);
						String add2=rs.getString("book");
						txtbook.setText(add2);
						String add3=rs.getString("b_date");
						txtdate.setText(add3);		
						String add4=rs.getString("r_date");
						txtrdate.setText(add4);
						rs.close();
						pst.close();	
					}
					else {
						
						JOptionPane.showMessageDialog(null, "IssueBook ID not Found");
					}					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		});
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_5.setBounds(318, 68, 124, 36);
		contentPane.add(btnNewButton_5);
		
		
		setUndecorated(true);
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtbook;
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

	public void LendBook_Load() {
		int lb;
		
		try {
			
			Statement st= con.createStatement();
			rs=st.executeQuery("select * from ISSUEBOOK");
	         	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
