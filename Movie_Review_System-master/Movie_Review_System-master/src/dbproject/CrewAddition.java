package dbproject;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
public class CrewAddition {

	private JFrame frame;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	Connection conn=null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	private String getIdMovie()
	{
		try
		{
			String name=MovieAddition.getMovie();
			String query="select * from movie where name=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs=pst.executeQuery();
			rs.next();
			String id=rs.getString("idMovie");
			return id;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private void fillComboBox()
	{
		comboBox.removeAllItems();
		try
		{
			String query="select * from producer";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				comboBox.addItem(rs.getString("name"));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	private void fillComboBox_1()
	{
		comboBox_1.removeAllItems();
		try
		{
			String query="select * from director";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				comboBox_1.addItem(rs.getString("name"));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	private void fillComboBox_2()
	{
		comboBox_2.removeAllItems();
		try
		{
			String query="select * from writer";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				comboBox_2.addItem(rs.getString("name"));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	private String getIdProducer()
	{
		try
		{
			String query="select * from producer where name =?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, (String)comboBox.getSelectedItem());
			ResultSet rs=pst.executeQuery();
			rs.next();
			String s=rs.getString("idProducer");
			return s;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	private String getIdDirector()
	{
		try
		{
			String query="select * from director where name =?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, (String)comboBox_1.getSelectedItem());
			ResultSet rs=pst.executeQuery();
			rs.next();
			String s=rs.getString("idDirector");
			return s;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	private String getIdWriter()
	{
		try
		{
			String query="select * from writer where name =?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, (String)comboBox_2.getSelectedItem());
			ResultSet rs=pst.executeQuery();
			rs.next();
			String s=rs.getString("idWriter");
			return s;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrewAddition window = new CrewAddition();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrewAddition() 
	{
		initialize();
		conn=database.getConnection();
		fillComboBox();
		fillComboBox_1();
		fillComboBox_2();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(46, 70, 119, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(206, 70, 119, 20);
		frame.getContentPane().add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(369, 70, 119, 20);
		frame.getContentPane().add(comboBox_2);
		
		JLabel lblEnterProducer = new JLabel("Enter Producer");
		lblEnterProducer.setBounds(46, 45, 92, 14);
		frame.getContentPane().add(lblEnterProducer);
		
		JLabel lblEnterDirecor = new JLabel("Enter Direcor");
		lblEnterDirecor.setBounds(206, 45, 76, 14);
		frame.getContentPane().add(lblEnterDirecor);
		
		JLabel lblEnterWriter = new JLabel("Enter Writer");
		lblEnterWriter.setBounds(369, 45, 83, 14);
		frame.getContentPane().add(lblEnterWriter);
		
		textField = new JTextField();
		textField.setBounds(537, 111, 140, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(537, 146, 61, 14);
		frame.getContentPane().add(lblName);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"producer", "director", "writer"}));
		comboBox_3.setBounds(537, 171, 119, 20);
		frame.getContentPane().add(comboBox_3);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String s=textField.getText();
					String c=(String)comboBox_3.getSelectedItem();
					String query="insert into "+c+" (name) values(?) ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, s);
					pst.execute();
					fillComboBox();
					fillComboBox_1();
					fillComboBox_2();
					JOptionPane.showMessageDialog(null, "Successfully added");
					textField.setText("");
					
				}catch(Exception e)
				{
					System.out.println(e);
				}
				
			}
		});
		btnSubmit.setBounds(537, 202, 105, 23);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lbl = new JLabel("");
		lbl.setBounds(46, 146, 46, 14);
		frame.getContentPane().add(lbl);
		
		JLabel lbl_1 = new JLabel("");
		lbl_1.setBounds(206, 146, 46, 14);
		frame.getContentPane().add(lbl_1);
		
		JLabel lbl_2 = new JLabel("");
		lbl_2.setBounds(369, 146, 46, 14);
		frame.getContentPane().add(lbl_2);
		
		JLabel lblEnterNewCrew = new JLabel("Enter New Crew");
		lblEnterNewCrew.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterNewCrew.setBounds(537, 72, 140, 17);
		frame.getContentPane().add(lblEnterNewCrew);
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String idMovie=getIdMovie();
					String idProducer=getIdProducer();
					String idDirector=getIdDirector();
					String idWriter=getIdWriter();
					String query="insert into movie_has_producer(idMovie,idProducer) values(?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, idMovie);
					pst.setString(2, idProducer);
					pst.execute();
					
					String query1="insert into movie_has_director(idMovie,idDirector) values(?,?)";
					PreparedStatement pst1=conn.prepareStatement(query1);
					pst1.setString(1, idMovie);
					pst1.setString(2, idDirector);
					pst1.execute();
					
					String query2="insert into movie_has_writer(idMovie,idWriter) values(?,?)";
					PreparedStatement pst2=conn.prepareStatement(query2);
					pst2.setString(1, idMovie);
					pst2.setString(2, idWriter);
					pst2.execute();
					
					JOptionPane.showMessageDialog(null, "Successfully updated crew");
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
		});
		btnSubmit_1.setBounds(10, 328, 89, 23);
		frame.getContentPane().add(btnSubmit_1);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Movie.main(null);
			}
		});
		btnDone.setBounds(10, 366, 89, 23);
		frame.getContentPane().add(btnDone);
	}
}
