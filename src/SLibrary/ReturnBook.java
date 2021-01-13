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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtrdate;
	private JTextField txtday;
	private JTextField txtfine;
	private JTextField txtname;
	private JTextField txtbook;
	int xx,xy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		Connect();
	    ReturnBook_Load();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 470);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x=arg0.getXOnScreen();
				int y=arg0.getYOnScreen();
				ReturnBook.this.setLocation(x-xx, y-xy);
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
		
		JLabel lblNewLabel = new JLabel("Return Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(338, 5, 169, 31);
		contentPane.add(lblNewLabel);
		
		Toolkit toolkit = getToolkit();
		Dimension size=toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel lblNewLabel_1 = new JLabel("MEMBER ID");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(10, 58, 169, 25);
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
					JOptionPane.showMessageDialog(ReturnBook.this,"Enter Numbers Only");
					
				}else {
					txtid.setEditable(true);
				
				}
			}
		});
		txtid.setToolTipText("Enter ID");
		txtid.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtid.setBounds(165, 56, 137, 29);
		txtid.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MEMBER NAME");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(10, 100, 169, 25);
		ImageIcon img2 =new ImageIcon(this.getClass().getResource("/Author Name.png"));
		lblNewLabel_2.setIcon(img2);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("BOOK NAME");
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(10, 136, 137, 29);
		ImageIcon img3 =new ImageIcon(this.getClass().getResource("/Main Pic4.png"));
		lblNewLabel_4.setIcon(img3);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("RETURN DATE");
		lblNewLabel_6.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_6.setBounds(10, 179, 159, 29);
		ImageIcon img4 =new ImageIcon(this.getClass().getResource("/Return Date.png"));
		lblNewLabel_6.setIcon(img4);
		contentPane.add(lblNewLabel_6);
		
		txtrdate = new JTextField();
		txtrdate.setToolTipText("Enter RETURN DATE");
		txtrdate.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtrdate.setBounds(165, 178, 206, 31);
		txtrdate.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtrdate);
		txtrdate.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("DAYS ELAP");
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_7.setBounds(10, 225, 137, 29);
		ImageIcon img5 =new ImageIcon(this.getClass().getResource("/Days Elap.png"));
		lblNewLabel_7.setIcon(img5);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("FINE");
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_8.setBounds(10, 265, 86, 29);
		ImageIcon img6 =new ImageIcon(this.getClass().getResource("/fine.png"));
		lblNewLabel_8.setIcon(img6);
		contentPane.add(lblNewLabel_8);
		
		txtday = new JTextField();
		txtday.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c))
				{
					txtday.setEditable(false);
					JOptionPane.showMessageDialog(ReturnBook.this,"Enter Numbers Only");
					
				}else {
					txtday.setEditable(true);
				
				}
			}
		});
		txtday.setToolTipText("Enter DAYS ELAP");
		txtday.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtday.setBounds(165, 225, 206, 29);
		txtday.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtday);
		txtday.setColumns(10);
		
		txtfine = new JTextField();
		txtfine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c))
				{
					txtfine.setEditable(false);
					JOptionPane.showMessageDialog(ReturnBook.this,"Enter Numbers Only");
					
				}else {
					txtfine.setEditable(true);
				
				}
			}
		});
		txtfine.setToolTipText("Enter FINE");
		txtfine.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtfine.setBounds(165, 264, 134, 31);
		txtfine.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtfine);
		txtfine.setColumns(10);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.setToolTipText("Click to ADD Details.");
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
				String retdate = txtrdate.getText();
				String dayselap = txtday.getText();
				String fine = txtfine.getText();
				
				try {
					String query="select * from RETURNBOOK where id='"+id+"'";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            if(rs.next()) {
		            	JOptionPane.showMessageDialog(null,"Data exist already");
		            	txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtrdate.setText("");
						txtday.setText("");
						txtfine.setText("");
		            }
		            else {
					pst = con.prepareStatement("insert into RETURNBOOK(id,name,book,retdate,dayselap,fine) values(?,?,?,?,?,?)");
		            pst.setString(1,id);
					pst.setString(2,name);
					pst.setString(3,book);
					pst.setString(4,retdate);
					pst.setString(5,dayselap);
					pst.setString(6,fine);
		         
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(ReturnBook.this,"Returnbook Created");
						
						txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtrdate.setText("");
						txtday.setText("");
						txtfine.setText("");
						txtname.requestFocus();						
					}
					else
					{
						JOptionPane.showMessageDialog(ReturnBook.this,"Error");
					}
		            }
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}	
			}
		});
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton.setBounds(104, 319, 116, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("Click to UDPATE Details");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img10 =new ImageIcon(this.getClass().getResource("/update.png"));
		btnNewButton_1.setIcon(img10);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String name = txtname.getText();
				String book = txtbook.getText();
				String retdate = txtrdate.getText();
				String dayselap = txtday.getText();
				String fine = txtfine.getText();
				
				try {
					pst = con.prepareStatement("update RETURNBOOK set name=?, book=?, retdate=?, dayselap=?, fine=? where id=?");
						pst.setString(1,name);
						pst.setString(2,book);
						pst.setString(3,retdate);
						pst.setString(4,dayselap);
						pst.setString(5,fine);
					    pst.setString(6,id);
					int k = pst.executeUpdate();
					
					if(k==1)
					{
                        JOptionPane.showMessageDialog(ReturnBook.this,"Returnbook Updated");
						
                        txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtrdate.setText("");
						txtday.setText("");
						txtfine.setText("");
						txtname.requestFocus();							
					}
					else
					{
						JOptionPane.showMessageDialog(ReturnBook.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}		
			}
		});
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_1.setBounds(266, 319, 125, 44);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setToolTipText("Click to DELETE Details.");
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		ImageIcon img11 =new ImageIcon(this.getClass().getResource("/trash.png"));
		btnNewButton_2.setIcon(img11);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				try {
					pst = con.prepareStatement("delete from ISSUEBOOK where id=?");
		           
					pst.setString(1,id);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(ReturnBook.this,"Book Deleted");
						txtid.setText("");
						txtname.setText(""); 
						txtbook.setText("");
						txtrdate.setText("");
						txtday.setText("");
						txtfine.setText("");
						txtname.requestFocus();
							
					}
					else
					{
						JOptionPane.showMessageDialog(ReturnBook.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_2.setBounds(104, 374, 116, 44);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setToolTipText("Click to Cancel.");
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
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
		btnNewButton_3.setBounds(266, 374, 125, 44);
		contentPane.add(btnNewButton_3);
		
		txtname = new JTextField();
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {
				char c=e1.getKeyChar();
				if(Character.isDigit(c))
				{
					txtname.setEditable(false);
					JOptionPane.showMessageDialog(ReturnBook.this,"Enter Alphabets Only");
					
				}else {
					txtname.setEditable(true);
				
				}
			}
		});
		txtname.setToolTipText("Enter MEMBER NAME");
		txtname.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtname.setBounds(165, 98, 206, 29);
		txtname.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtbook = new JTextField();
		txtbook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e2) {
				char c=e2.getKeyChar();
				if(Character.isDigit(c))
				{
					txtbook.setEditable(false);
					JOptionPane.showMessageDialog(ReturnBook.this,"Enter Alphabets Only");
					
				}else {
					txtbook.setEditable(true);
				
				}
			}
		});
		txtbook.setToolTipText("Enter BOOK NAME");
		txtbook.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtbook.setBounds(165, 135, 206, 31);
		txtbook.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(txtbook);
		txtbook.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("TABLE");
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(475, 47, 388, 261);
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
					String query="select * from RETURNBOOK";
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
		btnNewButton_4.setBounds(584, 319, 162, 44);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_3 = new JLabel("***Maximum-2 Months Book will be kept. After that according to the days elapsed, fine will be charged.***");
		lblNewLabel_3.setBounds(178, 434, 634, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setToolTipText("Close");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_9.setBounds(831, 5, 32, 44);
		ImageIcon img =new ImageIcon(this.getClass().getResource("/close.png"));
		lblNewLabel_9.setIcon(img);
		contentPane.add(lblNewLabel_9);
		
		
		JButton btnNewButton_6 = new JButton("SEARCH");
		btnNewButton_6.setToolTipText("Click to SEARCH.");
		ImageIcon img13 =new ImageIcon(this.getClass().getResource("/Zoom-icon.png"));
		btnNewButton_6.setIcon(img13);
		btnNewButton_6.addActionListener(new ActionListener() {
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
		btnNewButton_6.setForeground(Color.DARK_GRAY);
		btnNewButton_6.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_6.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_6.setBounds(338, 47, 127, 39);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("CALCULATE");
		ImageIcon img14 =new ImageIcon(this.getClass().getResource("/calculator.png"));
		btnNewButton_5.setIcon(img14);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dayselap, fine;
				 dayselap = Integer.parseInt(txtday.getText());
				 				
				
				try {
				 if(dayselap>0) {
					
					fine = dayselap*10;
					
				}
				else
				{
					fine = 0;
					
				}
				
				txtfine.setText(String.valueOf(fine));
				
				} catch(NumberFormatException e1) {
					e1.printStackTrace();
				}
		   }
		});
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_5.setBounds(309, 265, 156, 39);
		contentPane.add(btnNewButton_5);
		
		setUndecorated(true);
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
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
	
	public void ReturnBook_Load() {
		int r;
		
		try {
			
			Statement st= con.createStatement();
			rs=st.executeQuery("select * from RETURNBOOK");
	         	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

