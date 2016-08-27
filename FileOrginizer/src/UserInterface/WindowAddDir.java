package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;

import javax.swing.JTextField;

public class WindowAddDir
{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void AddDirectory()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					WindowAddDir window = new WindowAddDir();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowAddDir() 
	{
		initialize();
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(28, 209, 380, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	

}
