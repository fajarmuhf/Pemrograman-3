package Game;

import Game.BuatGambar;

import Game.Sql.*;
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
import Game.AksiUtama;

public class GameDrop {
	final static java.util.List<BuatGambar> Map = new ArrayList<BuatGambar>();
	final static java.util.List<BuatGambar> ItemDrop = new ArrayList<BuatGambar>();
	final static java.util.List<Timer> ItemTimer = new ArrayList<Timer>();
	final static JFrame frame = new JFrame("DC Fruit, Drop and Catch Fruit");
	static int avatarPos;
	static String namaPlayer = "Unknown";
	static int levelPlayer = 1;
	public static java.util.Timer timerku = new Timer();
	public static java.util.Timer timerAku = new Timer();
	public static java.util.Timer timerAnim = new Timer();
	final static Clock waktu = new Clock();
	final static JLabel valueSkor = new JLabel("0");
	final static JLabel valueLives = new JLabel("3");
	public static TimerTask tts3;
	public static TimerTask tts2;
	public static TimerTask tts;
	public final static JLabel valueTimer = new JLabel("0");
	final static JLabel valueNama = new JLabel(namaPlayer);
	public static Random random = new Random();
		
		
	public static JFrame frame2;
	public static JPanel grid;
	public static JPanel grid2;
	public static JTextField Nama2 = new JTextField("",10);
	
	public GameDrop(JFrame x,JPanel y,JPanel z,JTextField ck,String nama,int level){
		namaPlayer = nama;
		levelPlayer = level;
		frame2 = x;
		grid = y;
		grid2 = z;
		Nama2 = ck;
	}
	
	public void setLevel(int x){
		this.levelPlayer = x;
	}
	public int getLevel(){
		return this.levelPlayer;
	}
	
    public static void main(String[] arg){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("Menu");
		
		JMenuItem miFileReset = new JMenuItem("Bermain ulang");
		JMenuItem miFileAwal = new JMenuItem("Menu awal");
		
		menuFile.add(miFileReset);
		menuFile.add(miFileAwal);
		
		menuBar.add(menuFile);
		
		frame.setJMenuBar(menuBar);
		
		JPanel gridku = new JPanel();
		gridku.setBackground(new Color(0,191,255));
		gridku.setLayout(new GridLayout(8,8));
		gridku.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		
		LinearGradient gridku2 = new LinearGradient(new Color(65,105,225),new Color(0,0,205));
		gridku2.setBackground(new Color(0,0,128));
		gridku2.setLayout(new GridLayout(5,5));
		
		JPanel gridku3 = new JPanel( new FlowLayout() );
		
		miFileAwal.addActionListener(new AksiUtama(frame2,grid,grid2,Nama2,frame));
		miFileAwal.setActionCommand("menu awal");
		
		miFileReset.addActionListener(new AksiUtama(frame2,grid,grid2,Nama2,frame));
		miFileReset.setActionCommand("reset");
		
		levelPlayer = GameMenuUtama.player.getLevel();
		
		final JLabel pembuat = new JLabel("Dibuat oleh : Fajar Muhammad F - 2014");
		pembuat.setForeground(new Color(0,0,139));
		pembuat.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel levelText = new JLabel("Level : ");
		levelText.setForeground(new Color(224,255,255));
		levelText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		final JLabel valueLevel = new JLabel(String.valueOf(levelPlayer));
		valueLevel.setForeground(new Color(224,255,255));
		valueLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel skor = new JLabel("Skor : ");
		skor.setForeground(new Color(224,255,255));
		skor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		valueSkor.setForeground(new Color(224,255,255));
		valueSkor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
		JLabel timer = new JLabel("Timer : ");
		timer.setForeground(new Color(224,255,255));
		timer.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		valueTimer.setForeground(new Color(224,255,255));
		valueTimer.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel namaText = new JLabel("Nama : ");
		namaText.setForeground(new Color(224,255,255));
		namaText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		valueNama.setText(namaPlayer);
		valueNama.setForeground(new Color(224,255,255));
		valueNama.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lives = new JLabel("Lives : ");
		lives.setForeground(new Color(224,255,255));
		lives.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		valueLives.setForeground(new Color(224,255,255));
		valueLives.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
		waktu.setTimeClock(0,1,30);
		waktu.decreaseSeconds();
		valueTimer.setText(waktu.displayTime());		
				
		
		tts = new TimerTask() {
			@Override
			public void run() {
				updateWaktu();
			};
		};
		
		timerku.scheduleAtFixedRate(tts,new java.util.Date(),1000);
						
		int x=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				final BuatGambar si = new BuatGambar("src/Game/images/blank.png",0.05,0.05);
				si.setIndexGambar(x);
				gridku.add(si);
				Map.add(si);
				x++;
			}
		}
		
		setAvatarPos(59);
		
		Map.get(getAvatarPos()).changeImage("src/Game/images/basket.png");
		
		tts2 = new TimerTask() {
			@Override
			public void run() {
				acakJatuhBenda();
			};
		};
		
		timerAku.scheduleAtFixedRate(tts2,new java.util.Date(),3000+random.nextInt(3000));
		
		gridku2.add(levelText);
		gridku2.add(valueLevel);
		
		gridku2.add(skor);
		gridku2.add(valueSkor);
		
		gridku2.add(timer);
		gridku2.add(valueTimer);
		
		gridku2.add(lives);
		gridku2.add(valueLives);
		
		gridku2.add(namaText);
		gridku2.add(valueNama);
		
		gridku3.add(pembuat);
		
		frame.addKeyListener(new Aksi(valueSkor));
		
		frame.add(gridku,BorderLayout.NORTH);
		frame.add(gridku2,BorderLayout.CENTER);
		frame.add(gridku3,BorderLayout.SOUTH);
		frame.setSize(300,385);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static int getAvatarPos(){
		return avatarPos;
	}
	
	public static void setAvatarPos(int x){
		avatarPos=x;
	}
    
	public static void DropItem(BuatGambar bg,String imageku){
		Map.get(bg.getIndexGambar()+8).changeImage(imageku);
		Map.get(bg.getIndexGambar()).changeImage("src/Game/images/blank.png");
		Map.get(bg.getIndexGambar()+8).setIndexGambar(bg.getIndexGambar());
		bg.setIndexGambar(bg.getIndexGambar()+8);
	}
	
	public static void MoveRight(int Posisi,String imageku){
		Map.get(Posisi+1).changeImage(imageku);
		Map.get(Posisi).changeImage("src/Game/images/blank.png");
	}
	
	public static void MoveLeft(int Posisi,String imageku){
		Map.get(Posisi-1).changeImage(imageku);
		Map.get(Posisi).changeImage("src/Game/images/blank.png");
	}
	public static void turunBenda(final BuatGambar bg,String img){
		if(bg.getIndexGambar()+8 >= 64){
			if(bg.getStatus() == 1){
				Map.get(bg.getIndexGambar()).changeImage("src/Game/images/blank.png");
			}
			ItemDrop.remove(bg.getIndexSaveGambar());
			for(int i = bg.getIndexSaveGambar();i<ItemDrop.size();i++){
				//System.out.print("index ke-"+i+"\n");
				//System.out.print("Ubah : "+ItemDrop.get(i).getIndexSaveGambar()+" -> ");
				ItemDrop.get(i).setIndexSaveGambar(ItemDrop.get(i).getIndexSaveGambar()-1);
				//System.out.print(ItemDrop.get(i).getIndexSaveGambar()+" \n");
			}
			ItemTimer.get(bg.getIndexSaveGambar()).cancel();
			ItemTimer.remove(bg.getIndexSaveGambar());
			return;
		}
		else if(bg.getIndexGambar()+8 == getAvatarPos()){
			int pointku = bg.getPoint();
			int livesku = bg.getLivesKurang();
			Map.get(bg.getIndexGambar()).changeImage("src/Game/images/blank.png");
			ItemDrop.remove(bg.getIndexSaveGambar());
			for(int i = bg.getIndexSaveGambar();i<ItemDrop.size();i++){
				ItemDrop.get(i).setIndexSaveGambar(ItemDrop.get(i).getIndexSaveGambar()-1);
			}
			valueSkor.setText(String.valueOf(Integer.parseInt(valueSkor.getText())+pointku));
			valueLives.setText(String.valueOf(Integer.parseInt(valueLives.getText())-livesku));
					
			if(Integer.parseInt(valueLives.getText()) == 0){
				timerAku.cancel();
				timerku.cancel();
				waktu.setTimeClock(0,0,0);
				JOptionPane.showMessageDialog(null, "Kamu Kalah ,"+Nama2.getText()+" Skor kamu : "+valueSkor.getText());					
			}
					
			
			ItemTimer.get(bg.getIndexSaveGambar()).cancel();
			ItemTimer.remove(bg.getIndexSaveGambar());
			return;
		}
		else
			DropItem(bg,img);	
	}
	
	public static void AnimationDrop(final BuatGambar bg,final JLabel valueSkor,final JLabel valueLives){
		Timer timerAnim = new Timer();
		ItemTimer.add(timerAnim);
		final String img = bg.getUrlName();
		TimerTask tts3 = new TimerTask() {
			@Override
			public void run() {
				turunBenda(bg,img);
			};
		};
		timerAnim.scheduleAtFixedRate(tts3,new java.util.Date(),3000);
	}
	
	public static void updateWaktu(){
		waktu.decreaseSeconds();
		valueTimer.setText(waktu.displayTime());
		if(waktu.getTimesUp()){
			JOptionPane.showMessageDialog(null, "Waktu Habis,"+Nama2.getText()+" Skor kamu : "+valueSkor.getText());
			GameMenuUtama.savegame.simpanSkor(GameMenuUtama.player.getId(), levelPlayer, Integer.parseInt(valueSkor.getText()));
			GameMenuUtama.savegame.naikLevel(GameMenuUtama.player.getId(), levelPlayer);
			timerku.cancel();
			timerAku.cancel();
		}	
	}
	
	public static void acakJatuhBenda(){
		int[] levelBuah = {
			5,9,15
		};
				
		int acak = random.nextInt(levelBuah[levelPlayer-1]);
		int cekSama;
		int acakPos;
		do{
			acakPos = random.nextInt(8);
				
			cekSama = 0;
			for(int i=0;i<ItemDrop.size();i++){
				if(acakPos == ItemDrop.get(i).getIndexGambar()%8){
					cekSama = 1;
				}
			}
		}while(cekSama != 0);
				
		acak += 1;
		Map.get(acakPos).changeImage("src/Game/images/"+levelPlayer+"/item_"+acak+".png");
		Map.get(acakPos).setIndexGambar(acakPos);
		Map.get(acakPos).setIndexSaveGambar(ItemDrop.size());
		Map.get(acakPos).setStatus(1);
		//System.out.print("Index->"+Map.get(acakPos).getIndexSaveGambar()+"\n");
		//System.out.print("Pos->"+Map.get(acakPos).getIndexGambar()+"\n");
			
				
		int[] pointBuah={};
		int[] livesBuah={};
				
		if(levelPlayer == 1){
			int[] pointBuah2 = { 
			20, 15, 25, 0 , 0
			};
					
			pointBuah = pointBuah2.clone();
					
			int[] livesBuah2 = { 
				0, 0, 0, 1 , 1
			};
					
			livesBuah = livesBuah2.clone();
					
		}
		else if(levelPlayer == 2){
			int[] pointBuah2 = { 
				20, 15, 25, 30, 25, 0 , 0 , 0 ,0 
			};
					
			pointBuah = pointBuah2.clone();
					
			int[] livesBuah2 = { 
				0, 0, 0, 0, 0, 1 , 1 , 1 , 1
			};
					
			livesBuah = livesBuah2.clone();
					
		}
		else if(levelPlayer == 3){
			int[] pointBuah2 = { 
				20, 15, 25, 30, 25, 0 , 0 , 0 ,0 , 25 , 30 , 35 , 0 , 0 ,0 
			};
					
			pointBuah = pointBuah2.clone();
					
			int[] livesBuah2 = { 
				0, 0, 0, 0, 0, 1 , 1 , 1 , 1, 0 ,0 ,0 ,1 ,1 ,1
			};
					
			livesBuah = livesBuah2.clone();
					
		}
				
		for(int i=0;i<levelBuah[levelPlayer-1];i++){
			if(acak == i+1){
				Map.get(acakPos).setPoint(pointBuah[i]);
				Map.get(acakPos).setLivesKurang(livesBuah[i]);
			}
		}
				
		AnimationDrop(Map.get(acakPos),valueSkor,valueLives);
		ItemDrop.add(Map.get(acakPos));
	}
	public static void hapusBenda(){
		int Max = ItemDrop.size();
		for(int i = 0;i<Max;i++){
			//System.out.print("Hapus : "+String.valueOf(ItemDrop.get(0).getIndexSaveGambar())+"\n");
			Map.get(ItemDrop.get(0).getIndexGambar()).changeImage("src/Game/images/blank.png");
			ItemDrop.remove(0);
		}
	}
}

