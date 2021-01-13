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
import javax.swing.JComboBox;
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

public class Book extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtcontent;
	private JTextField txtno;
	private JTextField txtedition;
	private JTextField txtid;
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book frame = new Book();
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
	public Book() {
		Connect();
		Book_Load();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 476);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x=arg0.getXOnScreen();
				int y=arg0.getYOnScreen();
				Book.this.setLocation(x-xx, y-xy);
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
		
		JLabel lblNewLabel = new JLabel("Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(396, 0, 110, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		ImageIcon img2 =new ImageIcon(this.getClass().getResource("/Author Name.png"));
		lblNewLabel_1.setIcon(img2);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(37, 80, 100, 28);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {

				char c=e1.getKeyChar();
				if(Character.isDigit(c))
				{
					txtname.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Alphabets Only");
					
				}else {
					txtname.setEditable(true);
				
				}
			}
		});
		txtname.setToolTipText("Enter Book NAME");
		txtname.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtname.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtname.setBounds(166, 80, 176, 28);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Category");
		ImageIcon img3 =new ImageIcon(this.getClass().getResource("/Main Pic1.png"));
		lblNewLabel_2.setIcon(img3);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(37, 121, 118, 24);
		contentPane.add(lblNewLabel_2);
		
		JComboBox txtcategory = new JComboBox();
		txtcategory.setToolTipText("Choose Book CATEGORY");
		txtcategory.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtcategory.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtcategory.setModel(new DefaultComboBoxModel(new String[] {"Romance", "Action and Adventure", "Classics", "Comic Book", "Mystery", "Fantasy", "Historical Fiction", "Horror", "Literary Fiction", "Science Fiction (Sci-Fi)", "Short Stories", "Thrillers", "Biographies", "Cookbooks", "Essays", "Education"}));
		txtcategory.setBounds(166, 119, 176, 28);
		contentPane.add(txtcategory);
		
		JLabel lblNewLabel_3 = new JLabel("Author");
		ImageIcon img4 =new ImageIcon(this.getClass().getResource("/Main Pic2.png"));
		lblNewLabel_3.setIcon(img4);
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(37, 169, 100, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Publisher");
		ImageIcon img5 =new ImageIcon(this.getClass().getResource("/MainPic3.png"));
		lblNewLabel_4.setIcon(img5);
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(37, 204, 118, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Contents");
		ImageIcon img6 =new ImageIcon(this.getClass().getResource("/Book Content.png"));
		lblNewLabel_5.setIcon(img6);
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_5.setBounds(37, 239, 118, 29);
		contentPane.add(lblNewLabel_5);
		
		txtcontent = new JTextField();
		txtcontent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e4) {
				char c=e4.getKeyChar();
				if(Character.isDigit(c))
				{
					txtcontent.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Alphabets Only");
					
				}else {
					txtcontent.setEditable(true);
				
				}
			}
		});
		txtcontent.setToolTipText("Enter Book CONTENTS");
		txtcontent.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtcontent.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtcontent.setBounds(166, 239, 176, 28);
		contentPane.add(txtcontent);
		txtcontent.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("No. of Pages");
		ImageIcon img7 =new ImageIcon(this.getClass().getResource("/Book Pages.png"));
		lblNewLabel_6.setIcon(img7);
		lblNewLabel_6.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_6.setBounds(37, 279, 135, 28);
		contentPane.add(lblNewLabel_6);
		
		txtno = new JTextField();
		txtno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e5) {
				char c=e5.getKeyChar();
				if(Character.isLetter(c))
				{
					txtno.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Numbers Only");
					
				}else {
					txtno.setEditable(true);
				
				}
			}
		});
		txtno.setToolTipText("Enter Book No. OF PAGES");
		txtno.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtno.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtno.setBounds(166, 279, 176, 28);
		contentPane.add(txtno);
		txtno.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Edition");
		ImageIcon img8 =new ImageIcon(this.getClass().getResource("/Book Edition.png"));
		lblNewLabel_7.setIcon(img8);
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_7.setBounds(37, 318, 110, 36);
		contentPane.add(lblNewLabel_7);
		
		txtedition = new JTextField();
		txtedition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e6) {
				char c=e6.getKeyChar();
				if(Character.isLetter(c))
				{
					txtedition.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Numbers Only");
					
				}else {
					txtedition.setEditable(true);
				
				}
			}
		});
		txtedition.setToolTipText("Enter Book EDITION");
		txtedition.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtedition.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtedition.setBounds(166, 322, 176, 28);
		contentPane.add(txtedition);
		txtedition.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setToolTipText("Click to ADD Details.");
		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		ImageIcon img91 =new ImageIcon(this.getClass().getResource("/add.png"));
		btnNewButton.setIcon(img91);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtid.getText();
				String name = txtname.getText();
				String category = txtcategory.getSelectedItem().toString();
				String author = txtauthor.getText();
				String publisher=txtpub.getText();
				String content= txtcontent.getText();
				String pages=txtno.getText();
				String edition=txtedition.getText();				
				
				
				try {
					String query="select * from BOOK where id='"+id+"'";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            if(rs.next()) {
		            	JOptionPane.showMessageDialog(null,"Data exist already");
		            	txtid.setText("");
						txtname.setText(""); 
						txtcategory.setSelectedIndex(-1);
						txtauthor.setText(""); 
						txtpub.setText(""); 
						txtcontent.setText("");
						txtno.setText("");
		            }
		            else {
					pst = con.prepareStatement("insert into BOOK(id,name,category,author,publisher,content,pages,edition) values(?,?,?,?,?,?,?,?)");
		            pst.setString(1,id);
					pst.setString(2,name);
					pst.setString(3,category);
					pst.setString(4,author);
					pst.setString(5,publisher);
					pst.setString(6,content);
					pst.setString(7,pages);
					pst.setString(8,edition);
					
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(Book.this,"Book Created");
						
						txtid.setText("");
						txtname.setText(""); 
						txtcategory.setSelectedIndex(-1);
						txtauthor.setText(""); 
						txtpub.setText(""); 
						txtcontent.setText("");
						txtno.setText("");
						txtedition.setText(""); 
						txtname.requestFocus();
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(Book.this,"Error");
					}}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton.setBounds(106, 357, 110, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("ID");
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_8.setBounds(37, 41, 48, 28);
		ImageIcon img1 =new ImageIcon(this.getClass().getResource("/Category Pic1.png"));
		lblNewLabel_8.setIcon(img1);
		contentPane.add(lblNewLabel_8);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c))
				{
					txtid.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Numbers Only");
					
				}else {
					txtid.setEditable(true);
				
				}
			}
		});
		txtid.setToolTipText("Enter Book ID");
		txtid.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtid.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtid.setBounds(166, 41, 124, 28);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("Click to UDPATE Details.");
		btnNewButton_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		ImageIcon img10 =new ImageIcon(this.getClass().getResource("/update.png"));
		btnNewButton_1.setIcon(img10);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String name = txtname.getText();
				String category = txtcategory.getSelectedItem().toString();
				String author = txtauthor.getText();
				String publisher=txtpub.getText();
				String contents= txtcontent.getText();
				String noofpages=txtno.getText();
				String edition=txtedition.getText();				
				
				
				try {
					pst = con.prepareStatement("update BOOK set name=?, category=?, author=? , publisher=? , content=? , pages=? , edition=? where id=?");
		            pst.setString(1,id);
					pst.setString(2,name);
					pst.setString(3,category);
					pst.setString(4,author);
					pst.setString(5,publisher);
					pst.setString(6,contents);
					pst.setString(7,noofpages);
					pst.setString(8,edition);
					
					int k = pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(Book.this,"Book Updated");
						
						txtid.setText("");
						txtname.setText(""); 
						txtcategory.setSelectedIndex(-1);
						txtauthor.setText(""); 
						txtpub.setText(""); 
						txtcontent.setText("");
						txtno.setText("");
						txtedition.setText(""); 
						txtname.requestFocus();
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(Book.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_1.setBounds(300, 357, 110, 48);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setToolTipText("Click to DELETE Details.");
		btnNewButton_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		ImageIcon img11 =new ImageIcon(this.getClass().getResource("/trash.png"));
		btnNewButton_2.setIcon(img11);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();
				
				
				try {
					pst = con.prepareStatement("delete from BOOK where id=?");
		           
					pst.setString(1,id);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(Book.this,"Book Deleted");
						txtid.setText("");
						txtname.setText(""); 
						txtcategory.setSelectedIndex(-1);
						txtauthor.setText(""); 
						txtpub.setText(""); 
						txtcontent.setText("");
						txtno.setText("");
						txtedition.setText(""); 
						txtname.requestFocus();
							
					}
					else
					{
						JOptionPane.showMessageDialog(Book.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_2.setBounds(106, 416, 110, 48);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setToolTipText("Click to CANCEL.");
		btnNewButton_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
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
		btnNewButton_3.setBounds(300, 416, 110, 48);
		contentPane.add(btnNewButton_3);
		
		txtauthor = new JTextField();
		txtauthor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e2) {

				char c=e2.getKeyChar();
				if(Character.isDigit(c))
				{
					txtauthor.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Alphabets Only");
					
				}else {
					txtauthor.setEditable(true);
				
				}
			}
		});
		txtauthor.setToolTipText("Enter Book AUHTOR");
		txtauthor.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtauthor.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtauthor.setBounds(166, 158, 176, 28);
		contentPane.add(txtauthor);
		txtauthor.setColumns(10);
		
		txtpub = new JTextField();
		txtpub.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e3) {

				char c=e3.getKeyChar();
				if(Character.isDigit(c))
				{
					txtpub.setEditable(false);
					JOptionPane.showMessageDialog(Book.this,"Enter Alphabets Only");
					
				}else {
					txtpub.setEditable(true);
				
				}
			}
		});
		txtpub.setToolTipText("Enter Book PUBLISHER");
		txtpub.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtpub.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtpub.setBounds(166, 202, 176, 28);
		contentPane.add(txtpub);
		txtpub.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("TABLE");
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(456, 55, 424, 299);
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
		ImageIcon img9 =new ImageIcon(this.getClass().getResource("/view.png"));
		btnNewButton_4.setIcon(img9);
		btnNewButton_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setBackground(SystemColor.inactiveCaption);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from BOOK";
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
		btnNewButton_4.setBounds(606, 368, 157, 41);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setToolTipText("Close");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_9.setBounds(848, 11, 32, 44);
		ImageIcon img =new ImageIcon(this.getClass().getResource("/close.png"));
		lblNewLabel_9.setIcon(img);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton_5 = new JButton("SEARCH");
		btnNewButton_5.setToolTipText("Click here to SEARCH.");
		ImageIcon img14 =new ImageIcon(this.getClass().getResource("/Zoom-icon.png"));
		btnNewButton_5.setIcon(img14);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from BOOK where id=?";
				try {
					
					pst = con.prepareStatement(sql);
					pst.setString(1, txtid.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						
						String add1=rs.getString("name");
						txtname.setText(add1);
						String add2=rs.getString("category");
						txtcategory.setSelectedItem(add2);
						String add3=rs.getString("author");
						txtauthor.setText(add3);
						String add4=rs.getString("publisher");
						txtpub.setText(add4);	
						String add5=rs.getString("content");
						txtcontent.setText(add5);	
						String add6=rs.getString("pages");
						txtno.setText(add6);	
						String add7=rs.getString("edition");
						txtedition.setText(add7);
						rs.close();
						pst.close();	
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Book ID not Found");
					}					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		});
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_5.setBounds(311, 39, 135, 32);
		contentPane.add(btnNewButton_5);
		
		setUndecorated(true);
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtauthor;
	private JTextField txtpub;
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

	public void Book_Load() {
		int b;
		
		try {
			
			Statement st= con.createStatement();
			rs=st.executeQuery("select * from BOOK");
	         	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}



