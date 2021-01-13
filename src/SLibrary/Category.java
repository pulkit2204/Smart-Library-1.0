package SLibrary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Category extends JFrame {

	private JPanel contentPane;
	private JTextField txtcategory;
	int xx,xy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
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
	public Category() {
		
		Connect();
		Category_Load();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Toolkit toolkit = getToolkit();
		Dimension size=toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 882, 443);
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(176, 196, 222));
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x=arg0.getXOnScreen();
				int y=arg0.getYOnScreen();
				Category.this.setLocation(x-xx, y-xy);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx=e.getX();
				xy=e.getY();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CATEGORY");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(350, 11, 175, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CATEGORY NAME");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(10, 179, 175, 40);
		ImageIcon img2 =new ImageIcon(this.getClass().getResource("/Main Pic1.png"));
		lblNewLabel_1.setIcon(img2);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("STATUS");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(31, 235, 106, 36);
		ImageIcon img3 =new ImageIcon(this.getClass().getResource("/Category Pic2.png"));
		lblNewLabel_2.setIcon(img3);
		panel.add(lblNewLabel_2);
		
		txtcategory = new JTextField();
		txtcategory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {
				char c=e1.getKeyChar();
				if(Character.isDigit(c))
				{
					txtcategory.setEditable(false);
					JOptionPane.showMessageDialog(Category.this,"Enter Numbers Only");
					
				}else {
					txtcategory.setEditable(true);
				
				}
			}
		});
		txtcategory.setToolTipText("Enter Category NAME");
		txtcategory.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtcategory.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtcategory.setBounds(182, 184, 216, 30);
		panel.add(txtcategory);
		txtcategory.setColumns(10);
		
		JComboBox txtstatus = new JComboBox();
		txtstatus.setToolTipText("Select STATUS");
		txtstatus.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtstatus.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtstatus.setModel(new DefaultComboBoxModel(new String[] {"Available", "Not Available"}));
		txtstatus.setBounds(182, 238, 216, 30);
		panel.add(txtstatus);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setToolTipText("Click to ADD Details.");
		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		ImageIcon img4 =new ImageIcon(this.getClass().getResource("/add.png"));
		btnNewButton.setIcon(img4);
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String category = txtcategory.getText();
				String status = txtstatus.getSelectedItem().toString();
				
				try {
					String query="select * from CATEGORY where id='"+id+"'";
		            pst = con.prepareStatement(query);
		            rs = pst.executeQuery();
		            if(rs.next()) {
		            	JOptionPane.showMessageDialog(null,"Data exist already");
		            	txtid.setText("");
						txtcategory.setText(""); 
						txtstatus.setSelectedIndex(-1);
						txtcategory.requestFocus();
		            }
		            else {
					pst = con.prepareStatement("insert into CATEGORY(Id,Category_name,Status) values(?,?,?)");
		            pst.setString(1,id);
					pst.setString(2,category);
					pst.setString(3,status);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(Category.this,"Category Created");
						txtid.setText("");
						txtcategory.setText(""); 
						txtstatus.setSelectedIndex(-1);
						txtcategory.requestFocus();
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(Category.this,"Error");
					}}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(103, 299, 134, 46);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("Click to UPDATE Details.");
		btnNewButton_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		ImageIcon img5 =new ImageIcon(this.getClass().getResource("/update.png"));
		btnNewButton_1.setIcon(img5);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtid.getText();
				String category = txtcategory.getText();
				String status = txtstatus.getSelectedItem().toString();
				
				try {
					pst = con.prepareStatement("update CATEGORY set Category_name=?, Status=? where id=?");
		            pst.setString(1,category);
					pst.setString(2,status);
					pst.setString(3,id);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(Category.this,"Category Updated");
						txtid.setText("");
						txtcategory.setText(""); 
						txtstatus.setSelectedIndex(-1);
						txtcategory.requestFocus();	
					}
					else
					{
						JOptionPane.showMessageDialog(Category.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_1.setBounds(247, 299, 137, 46);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setToolTipText("Click to DELETE Details.");
		btnNewButton_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		ImageIcon img6 =new ImageIcon(this.getClass().getResource("/trash.png"));
		btnNewButton_2.setIcon(img6);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id = txtid.getText();
				try {
					pst = con.prepareStatement("delete from CATEGORY where id=?");
		           
					pst.setString(1,id);
					int k = pst.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(Category.this,"Category Deleted");
						txtid.setText("");
						txtcategory.setText(""); 
						txtstatus.setSelectedIndex(-1);
						txtcategory.requestFocus();	
					}
					else
					{
						JOptionPane.showMessageDialog(Category.this,"Error");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(103, 356, 134, 46);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setToolTipText("Click to CANCEL.");
		btnNewButton_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		ImageIcon img7 =new ImageIcon(this.getClass().getResource("/back-icon.png"));
		btnNewButton_3.setIcon(img7);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main m=new Main();
				m.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(247, 356, 137, 46);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(31, 122, 46, 46);
		ImageIcon img1 =new ImageIcon(this.getClass().getResource("/Category Pic1.png"));
		lblNewLabel_3.setIcon(img1);
		panel.add(lblNewLabel_3);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c))
				{
					txtid.setEditable(false);
					JOptionPane.showMessageDialog(Category.this,"Enter Numbers Only");
					
				}else {
					txtid.setEditable(true);
				
				}
			}
		});
		txtid.setToolTipText("Enter Category ID");
		txtid.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtid.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		txtid.setBounds(103, 130, 142, 30);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("TABLE");
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(408, 84, 464, 261);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.orange);
		table.setForeground(Color.white);
		table.setSelectionBackground(Color.black);
		table.setSelectionForeground(Color.yellow);
		table.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,14));
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_4 = new JButton("View Table");
		btnNewButton_4.setToolTipText("Click to VIEW TABLE.");
		btnNewButton_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_4.setBackground(SystemColor.inactiveCaption);
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		ImageIcon img8 =new ImageIcon(this.getClass().getResource("/view.png"));
		btnNewButton_4.setIcon(img8);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from CATEGORY";
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
		btnNewButton_4.setBounds(554, 359, 175, 40);
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setToolTipText("Close");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_4.setBounds(840, 11, 32, 40);
		ImageIcon img =new ImageIcon(this.getClass().getResource("/close.png"));
		lblNewLabel_4.setIcon(img);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_5 = new JButton("SEARCH");
		btnNewButton_5.setToolTipText("Click here to SEARCH.");
		ImageIcon img11 =new ImageIcon(this.getClass().getResource("/Zoom-icon.png"));
		btnNewButton_5.setIcon(img11);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from CATEGORY where id=?";
				try {
					
					pst = con.prepareStatement(sql);
					pst.setString(1, txtid.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						
						String add1=rs.getString("category_name");
						txtcategory.setText(add1);
						String add2=rs.getString("status");
						txtstatus.setSelectedItem(add2);
						rs.close();
						pst.close();	
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Category ID not Found");
					}					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		});
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton_5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		btnNewButton_5.setBounds(255, 124, 133, 36);
		panel.add(btnNewButton_5);
		
		
		setUndecorated(true);
	}

Connection con;
PreparedStatement pst;
ResultSet rs;
private JTextField txtid;
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

public void Category_Load() {
	int c;
	
	try {
		
		Statement st= con.createStatement();
		rs=st.executeQuery("select * from CATEGORY");
   
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}

