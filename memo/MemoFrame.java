package day1031.memo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoFrame extends JFrame implements ActionListener{
	JMenuBar bar;
	JMenu file;
	JMenuItem[] items = new JMenuItem[2]; 
	JTextArea area;
	JScrollPane scroll;
	String[] item_name = {"열기", "저장"};
	
	FileInputStream fis;
	FileOutputStream fos;
	JFileChooser chooser = new JFileChooser();
	public MemoFrame(String title) {
		setTitle(title);
		
		bar = new JMenuBar();
		file = new JMenu("파일");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		for (int i = 0; i < item_name.length; i++) {
			items[i] = new JMenuItem(item_name[i]);
			file.add(items[i]);
			
		}
		
		bar.add(file);
		add(scroll);
		setJMenuBar(bar);
		
		items[0].addActionListener(this);
		items[1].addActionListener(this);
		
		setBounds(200, 200, 600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(items[0])) {
			open();
		}else if(obj.equals(items[1])) {
			save();
		}
		
	}
	
	public void open() {
		chooser.showOpenDialog(this);
		String filename = chooser.getSelectedFile().toString();
		
		try {
			fis = new FileInputStream(filename);
			int data;
			StringBuffer str = new StringBuffer();
			while(true) {
				data = fis.read();
				if(data == -1) break;
				str.append((char)data);
				area.setText(str.toString());
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void save() {
		chooser.showSaveDialog(this);
		try {
			fos = new FileOutputStream(chooser.getSelectedFile().toString());
			
			String data;
			data = area.getText();
			
			for (int i = 0; i < data.length(); i++) {
				fos.write((int)data.charAt(i));
			}
			System.out.println("저장 성공");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoFrame("Memo");
	}

}






