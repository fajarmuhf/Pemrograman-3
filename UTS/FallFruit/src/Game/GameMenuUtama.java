package Game;

import Game.BuatGambar;

import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

import Game.LinearGradient;
import Game.Clock;
import Game.Aksi;
import Game.simpanText;
import Game.Sql.SaveGame;
import Game.utility.Player;
import Game.utility.PlayerLevel;

public class GameMenuUtama {
	final static JFrame frame = new JFrame("DC Fruit, Drop and Catch Fruit");
	final static java.util.List<BuatGambar> Map = new ArrayList<BuatGambar>();
	final static java.util.List<BuatGambar> ItemDrop = new ArrayList<BuatGambar>();
	static int avatarPos;
	static JPanel gridku = new JPanel();
	static JPanel gridkuContent2 = new JPanel();
	public static int sudahMain = 0;
	public static PlayerLevel player = new PlayerLevel();
	public static SaveGame savegame = new SaveGame();
	
	public GameMenuUtama(){
		String[] name = {"Bubu", "Dan", "Aku","Papah","Baba"};
		main(name);
	}
	
    public static void main(String[] arg){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		simpanText tulis = new simpanText();
		
		gridku.setBackground(new Color(0,191,255));
		gridku.setLayout(new GridLayout(2,1));
		gridku.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		
		LinearGradient gridku2 = new LinearGradient(new Color(65,105,225),new Color(0,0,205));
		gridku2.setBackground(new Color(0,0,128));
		gridku2.setLayout(new GridLayout(2,1));
		
		gridkuContent2.setBackground(new Color(0,0,128));
		gridkuContent2.setBackground(null);
		gridkuContent2.setLayout(new GridLayout(5,1));
		
		JPanel flowku = new JPanel();
		flowku.setBackground(null);
		flowku.setBackground(new Color(0,191,255));
		flowku.setLayout(new GridBagLayout());
		
		JPanel flowku2 = new JPanel();
		flowku2.setBackground(new Color(0,191,255));
		flowku2.setLayout(new GridBagLayout());
		
		JPanel flowku3 = new JPanel();
		flowku3.setBackground(new Color(0,191,255));
		flowku3.setLayout(new GridBagLayout());
		
		JPanel flowku4 = new JPanel();
		flowku4.setBackground(new Color(0,191,255));
		flowku4.setLayout(new GridBagLayout());
		
		JPanel flowku5 = new JPanel();
		flowku5.setBackground(new Color(0,191,255));
		flowku5.setLayout(new GridBagLayout());
		
		JPanel gridku3 = new JPanel( new FlowLayout() );
		
		final JLabel pembuat = new JLabel("Dibuat oleh : Fajar Muhammad F - 2014");
		pembuat.setForeground(new Color(0,0,139));
		pembuat.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		final Random random = new Random();
		
		final JButton ButtonLogin = new JButton("Login");
		ButtonLogin.setForeground(new Color(0,0,0));
		//ButtonLogin.setBackground(null);
		//ButtonLogin.setOpaque(false);
		//ButtonLogin.setContentAreaFilled(false);
		//ButtonLogin.setBorderPainted(false);
		ButtonLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		//ButtonLogin.setBorder(null);
		ButtonLogin.addActionListener(new AksiUtama(frame,gridku,gridku3,new JTextField("",10),GameDrop.frame));
		ButtonLogin.setActionCommand("1");
		/*try {
			Image img = ImageIO.read(new File("src/Game/images/button/login.png"));
			ButtonLogin.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		
		}
		
		ButtonLogin.addMouseListener(new MouseListener() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	try {
					Image img = ImageIO.read(new File("src/Game/images/button/login.png"));
					ButtonLogin.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
				
				}
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	try {
					Image img = ImageIO.read(new File("src/Game/images/button/login_h.png"));
					ButtonLogin.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
				
				}
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	try {
					Image img = ImageIO.read(new File("src/Game/images/button/login.png"));
					ButtonLogin.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
				
				}
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	try {
					Image img = ImageIO.read(new File("src/Game/images/button/login_r.png"));
					ButtonLogin.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
				
				}
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    
		    }
		});*/
		
		JButton ButtonRegister = new JButton("Register");
		ButtonRegister.setForeground(new Color(0,0,0));
		ButtonRegister.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		ButtonRegister.addActionListener(new AksiUtama(frame,gridku,gridku3,new JTextField("",10),GameDrop.frame));
		ButtonRegister.setActionCommand("6");

		/*Dimension d = flowku.getPreferredSize();
		Dimension big = new Dimension( (int)(d.getWidth()/2),(int)(d.getHeight()/2));
        flowku.setPreferredSize(big);*/
		
		JButton ButtonBantuan = new JButton("Bantuan");
		ButtonBantuan.setForeground(new Color(0,0,0));
		ButtonBantuan.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		ButtonBantuan.addActionListener(new AksiUtama(frame,gridku,gridku3,new JTextField("",10),GameDrop.frame));
		ButtonBantuan.setActionCommand("2");
		
		JButton ButtonTentang = new JButton("HighScore");
		ButtonTentang.setForeground(new Color(0,0,0));
		ButtonTentang.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		ButtonTentang.addActionListener(new AksiUtama(frame,gridku,gridku3,new JTextField("",10),GameDrop.frame));
		ButtonTentang.setActionCommand("3");
		
		JButton ButtonKeluar = new JButton("Keluar");
		ButtonKeluar.setForeground(new Color(0,0,0));
		ButtonKeluar.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		ButtonKeluar.addActionListener(new AksiUtama(frame,gridku,gridku3,new JTextField("",10),GameDrop.frame));
		ButtonKeluar.setActionCommand("exit");
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx=1.0;  
		c.anchor=GridBagConstraints.WEST;  
		c.fill=GridBagConstraints.HORIZONTAL;  
		c.insets=new Insets(20,80,20,80);  
		c.gridx=0;  
		c.gridy=0;  
		
		GridBagConstraints d = new GridBagConstraints();
		d.weightx=1.0;  
		d.anchor=GridBagConstraints.WEST;  
		d.fill=GridBagConstraints.HORIZONTAL;  
		d.insets=new Insets(0,100,0,100);  
		d.gridx=0;  
		d.gridy=0;  
 
		
		flowku.add(ButtonLogin,c);
		flowku2.add(ButtonRegister,c);
		flowku3.add(ButtonBantuan,c);
		flowku4.add(ButtonTentang,c);
		flowku5.add(ButtonKeluar,c);
		
		gridkuContent2.add(flowku);
		gridkuContent2.add(flowku2);
		gridkuContent2.add(flowku3);
		gridkuContent2.add(flowku4);
		gridkuContent2.add(flowku5);
				
		final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
				
		gridku.add(Judul);
		gridku.add(gridkuContent2);
		
		gridku3.add(pembuat);
		
		//frame.addKeyListener(new Aksi(valueSkor));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		/*try {
			Image img = ImageIO.read(new File("src/Game/images/background.jpg"));
			frame.setContentPane(new JLabel(new ImageIcon(img)));
		} catch (IOException ex) {
		
		}*/
		frame.setLayout(new BorderLayout());
		frame.add(gridku,BorderLayout.CENTER);
		frame.add(gridku3,BorderLayout.SOUTH);
		frame.setSize(screenSize);
		frame.setResizable(true); // THEN  resizable = false
		frame.setSize(300,470);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
		//frame.setReziable(true);
	}

	JPanel contentPanelku = new JPanel();
					
}

