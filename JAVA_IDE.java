package helloworld;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class JAVA_IDE extends JFrame {
	
	
	
	
    private JPanel contentPane;
	private JTextArea InputArea;
	private JTextArea OutputArea;
	final JFileChooser myFileChooser = new JFileChooser();
	private String InputString = " ";
	private StringBuffer saveDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAVA_IDE frame = new JAVA_IDE();
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
	public JAVA_IDE() {
		setTitle("\uC790\uBC14IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 373);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				InputArea.setText(" ");
				JAVA_IDE.this.setTitle(getTitle() + " - 새자바") ;
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Open");
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int intRet = myFileChooser.showOpenDialog(JAVA_IDE.this);
				if(intRet == JFileChooser.APPROVE_OPTION) {
					try {
				         String strLine;
				         File myFile = myFileChooser.getSelectedFile();
				         JAVA_IDE.this.setTitle(getTitle() + " - " + myFile.getName());
				         BufferedReader myReader = new BufferedReader(new FileReader(myFile.getAbsolutePath()));
				         InputArea.setText(myReader.readLine());
				         while((strLine = myReader.readLine()) != null) {
				        	 InputArea.append("\n"+strLine);
				         }
				         myReader.close();
				        		 
					}catch(IOException ie) {
						System.out.println(ie+ "==> 입출력오류 발생");
					}
				}
				
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Save");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int intRet = myFileChooser.showSaveDialog(JAVA_IDE.this);
				if (intRet == JFileChooser.APPROVE_OPTION) {
					try {
						File myFile = myFileChooser.getSelectedFile();
						JAVA_IDE.this.setTitle(getTitle() + "-" + myFile.getName());
						PrintWriter myWriter = new PrintWriter(new BufferedWriter(new FileWriter(myFile.getAbsolutePath())));
						myWriter.write(InputArea.getText());
						myWriter.close();
					}catch(IOException ie) {
						System.out.println(ie+ "==> 입출력 오류 발생");
					}
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_8);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Editer");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Copy");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputArea.copy();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Paste");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputArea.paste();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Cut");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputArea.cut();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Compile");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputString = InputArea.getText();
				saveDate = new StringBuffer (InputString);
				
				
				String fileName = (ClassName()+".java");
				FileUtil.save(saveDate, fileName);
				
				Cmd cmd = new Cmd();
				try {
					String command = cmd.inputCommand("cd C:\\JAVA IDE\\"+" && "+"javac "+fileName);
					cmd.execCommand(command);
					File f = new File("C:\\JAVA IDE\\"+ClassName()+".class");
					if(f.exists()) {
						OutputArea.setText("컴파일성공");
					}else {
						OutputArea.setText("컴파일실패");
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
			  }
				
					
			}
				
				
			
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Run");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = ClassName();
				Cmd cmd = new Cmd();
				try {
					String command = cmd.inputCommand("cd C:\\JAVA IDE\\"+" && "+"java "+fileName);
					String result = cmd.execCommand(command);
					OutputArea.setText(result);
				}catch(Exception ex) {
					ex.printStackTrace();
				 
					
					
				}
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_3 = new JMenu("\uAC1C\uBC1C\uC790");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("\uC815\uBCF4");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newChang();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		OutputArea = new JTextArea();
		scrollPane.setViewportView(OutputArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);
		
		InputArea = new JTextArea();
		scrollPane_1.setViewportView(InputArea);
		
		
		
	}
	public String ClassName() {
		String className = InputArea.getText();
		String []strArray = className.split(" ");
		for (int i = 0 ; i < strArray.length; i++) {
			if(strArray [i].equals("class")) {
				className = strArray[i+1];
				break;
			}
		}
		return className;
		
	}
	public class newChang extends JFrame{
		newChang(){
			setTitle("개발자 정보");
			JPanel newChangContainer = new JPanel();
			setContentPane(newChangContainer);
			
			JLabel NewLabel = new JLabel("황대윤 학번:183631");
			JLabel NewLabe2 = new JLabel("김기혁 학번:183903");
			

			newChangContainer.add(NewLabel);
			newChangContainer.add(NewLabe2);
			
			setSize(300,100);
	        setResizable(false);
	        setVisible(true);

			
		}
		
		
	}
	

}
