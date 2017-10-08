import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Notepad {
	
	public TextArea textArea;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notepad window = new Notepad();
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
	public Notepad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		textArea = new TextArea();
		textArea.setBounds(0, 0, 794, 550);
		frame.getContentPane().add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new OpenListener());
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new SaveListener());
		JMenuItem clearItem = new JMenuItem("Clear");
		clearItem.addActionListener(new ClearListener());
		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.addActionListener(new CloseListener());
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(clearItem);
		fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		
		JMenu aboutMenu = new JMenu("About");
		JMenuItem aboutAuthorItem = new JMenuItem("Author");
		aboutAuthorItem.addActionListener(new AuthorListener());
		JMenuItem aboutProgramItem = new JMenuItem("Program");
		aboutProgramItem.addActionListener(new ProgramListener());
		aboutMenu.add(aboutAuthorItem);
		aboutMenu.add(aboutProgramItem);
		menuBar.add(aboutMenu);
	}
	
	private class OpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			fileChooser.setFileFilter(filter);
			if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					textArea.setText("");
					Scanner scanner = new Scanner(file);
					while(scanner.hasNext()) {
						textArea.append(scanner.nextLine() + "\n");
					}
					scanner.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					PrintWriter printWriter = new PrintWriter(file + ".txt");
					Scanner scanner = new Scanner(textArea.getText());
					while(scanner.hasNext()) {
						printWriter.println(scanner.nextLine() + "\n");
					}
					printWriter.close();
					scanner.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class CloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	    }
	}
	
	private class ProgramListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(null, "Just a simple notepad");
	    }
	}
	
	private class AuthorListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JOptionPane.showMessageDialog(null, "Author: £ukasz Parulski IIIB");
	    }
	}
	
	private class ClearListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        textArea.setText("");
	    }
	}
}
