package grafica;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import backend.FileVuoto;
import backend.Utils;

public class Main extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String VERSION="2.0.1";
	
	private JTextArea areaNomi = new JTextArea();
	private JTextField num = new JTextField(5);

	
	
	public Main() {
		aggiungiComponenti();
		
		
		this.setTitle("Turn maker "+VERSION);
		
		this.setSize(470, 600);
		this.setMinimumSize(new Dimension(470, 300));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void aggiungiComponenti() {
		JPanel p = new JPanel(), p2 = new JPanel();
		this.add(p);

		p.setLayout(new BorderLayout());
		p.add(new JScrollPane(areaNomi), BorderLayout.CENTER);
		p.add(p2, BorderLayout.NORTH);
		
		JButton btnShuffle = new JButton("Shuffle"), btnOpenFile=new JButton("Open File");
		btnShuffle.addActionListener(this);
		btnShuffle.setActionCommand("SHUFFLE");
		btnOpenFile.addActionListener(this);
		btnOpenFile.setActionCommand("OPEN");
		
		
		p2.add(new JLabel("People per turn:"));
		p2.add(num);
		p2.add(btnShuffle);
		p2.add(new JLabel("For adding people:"));
		p2.add(btnOpenFile);

	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("OPEN")) {
			try {
				open();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(command.equals("SHUFFLE")){
			shuffle();
		}
		
	}

	private void open() throws IOException {
		File f = new File(Utils.file);
		if(!f.exists()) f.createNewFile();
		
		Runtime.getRuntime().exec("C:\\Windows\\notepad.exe "+Utils.file);
		
		
	}

	private void shuffle() {
		try {
			areaNomi.setText(Utils.getTextFormatted(num.getText()));
		} catch (FileVuoto e1) {
			
			JOptionPane.showMessageDialog(this, 
					"The file containing the name is empty\n\nClick open file to edit the file by adding name", 
					"Empty file", JOptionPane.ERROR_MESSAGE);
		}
	}
}
