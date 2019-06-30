package dbproject;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Movie extends JFrame{

	private JFrame frame;
	private JScrollPane scrollPane;
	private static JTable table;
	private static String name;
	
	private void refreshTable()
	{
		try
		{
		
			String query="select * from movie";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 * Launch the application.
	 */
	public static String getDataTable()
	{
		int row=table.getSelectedRow();
		return name=(table.getModel().getValueAt(row, 1)).toString();
	}
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Movie window = new Movie();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn=null;
	private JButton btnLogout;
	private JTextField textField;
	private JLabel lblTypeToSearch;

	/**
	 * Create the application.
	 */
	public Movie() {
		initialize();
		conn=database.getConnection();
		try
		{
			String query="select * from movie";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(138, 11, 359, 14);
			frame.getContentPane().add(lblNewLabel);
			rs.close();
			pst.close();
			String s=login_page.getUserid();
			s="Welcome "+s;
			lblNewLabel.setText(s);
			
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					login_page.main(null);
				}
			});
			btnLogout.setBounds(10, 394, 89, 23);
			frame.getContentPane().add(btnLogout);
			
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					try
					{
						String s=textField.getText();
						String query="select * from movie where name like ?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, "%"+s+"%");
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}
			});
			textField.setBounds(10, 36, 305, 28);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			lblTypeToSearch = new JLabel("Type to Search");
			lblTypeToSearch.setBounds(325, 47, 89, 14);
			frame.getContentPane().add(lblTypeToSearch);
			
			JButton btnRefresh = new JButton("Refresh");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refreshTable();
				}
			});
			btnRefresh.setBounds(862, 394, 89, 23);
			frame.getContentPane().add(btnRefresh);
			
		}catch(Exception w)
		{
			System.out.println(w);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnClickToSubmit = new JButton("Click to submit a new movie");
		btnClickToSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MovieAddition.main(null);
			}
		});
		btnClickToSubmit.setBounds(402, 394, 200, 23);
		frame.getContentPane().add(btnClickToSubmit);
		
		table=new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MovieDetails.main(null);
			
			}
		});
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 75, 964, 308);
		frame.getContentPane().add(scrollPane);
		
		
	}
}
