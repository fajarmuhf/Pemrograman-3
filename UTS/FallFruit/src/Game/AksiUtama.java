package Game;

import Game.BuatGambar;
import Game.GameDrop;
import Game.GameMenuUtama;
import Game.utility.Player;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AksiUtama implements ActionListener{
	
	public JFrame frame;
	public JFrame frame2;
	public JPanel grid;
	public JPanel grid2;
	private JTextField Nama = new JTextField("",10);
	private JPasswordField Password = new JPasswordField("",10);
	private JTextField Email = new JTextField("",10);
	public GameDrop gd = new GameDrop(frame,grid,grid2,Nama,Nama.getText(),1);	
			
	public AksiUtama(JFrame x,JPanel y,JPanel z,JTextField ck,JFrame xy){
		frame = x;
		frame2 = xy;
		grid = y;
		grid2 = z;
		Nama = ck;
		
		//Untuk Game Drop
		GameDrop.namaPlayer = Nama.getText();
		GameDrop.frame2 = x;
		GameDrop.grid = y;
		GameDrop.grid2 = z;
		GameDrop.Nama2 = ck;
	}
	
	public AksiUtama(JFrame x,JPanel y,JPanel z,JTextField ck,JFrame xy,JPasswordField ck2,JTextField emailku){
		frame = x;
		frame2 = xy;
		grid = y;
		grid2 = z;
		Nama = ck;
		Password = ck2;
		Email = emailku;
		
		//Untuk Game Drop
		GameDrop.namaPlayer = Nama.getText();
		GameDrop.frame2 = x;
		GameDrop.grid = y;
		GameDrop.grid2 = z;
		GameDrop.Nama2 = ck;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("1")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			final BuatGambar kosong = new BuatGambar("src/Game/images/blank.png",0.02,0.02);
			final BuatGambar kosong2 = new BuatGambar("src/Game/images/blank.png",0.02,0.02);
			
			JLabel NamaText = new JLabel("Username : ");
			NamaText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JLabel PassText = new JLabel("Password : ");
			PassText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			Nama.setHorizontalAlignment(JTextField.CENTER);
			Nama.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			Password.setHorizontalAlignment(JTextField.CENTER);
			Password.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			JButton Oke = new JButton("Oke");
			Oke.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2,Password,new JTextField("")));
			Oke.setActionCommand("5");
			Oke.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			JButton Kembali = new JButton("Kembali");
			Kembali.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2,Password,new JTextField("")));
			Kembali.setActionCommand("4");
			Kembali.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxContent = new JPanel();
			boxContent.setBackground(new Color(0,191,255));
			boxContent.setLayout(new GridLayout(5,1));
			
			JPanel flowku = new JPanel();
			flowku.setBackground(new Color(0,191,255));
			flowku.setLayout(new FlowLayout());
			
			flowku.add(NamaText);
			
			JPanel flowku2 = new JPanel();
			flowku2.setBackground(new Color(0,191,255));
			flowku2.setLayout(new FlowLayout());
			
			flowku2.add(Nama);
			
			JPanel flowku3 = new JPanel();
			flowku3.setBackground(new Color(0,191,255));
			flowku3.setLayout(new FlowLayout());
			
			flowku3.add(PassText);
			
			JPanel flowku4 = new JPanel();
			flowku4.setBackground(new Color(0,191,255));
			flowku4.setLayout(new FlowLayout());
			
			flowku4.add(Password);
			
			JPanel flowku5 = new JPanel();
			flowku5.setBackground(new Color(0,191,255));
			flowku5.setLayout(new FlowLayout());
			
			flowku5.add(Oke);
			
			JPanel flowku6 = new JPanel();
			flowku6.setBackground(new Color(0,191,255));
			flowku6.setLayout(new FlowLayout());
			
			flowku6.add(Kembali);
			
			JPanel gridku1 = new JPanel();
			gridku1.setBackground(new Color(0,191,255));
			gridku1.setLayout(new GridLayout(1,2));
			
			gridku1.add(flowku5);
			gridku1.add(flowku6);
			
			
			boxContent.add(flowku);
			boxContent.add(flowku2);
			boxContent.add(flowku3);
			boxContent.add(flowku4);
			boxContent.add(gridku1);
			
			grid.add(Judul);
			grid.add(boxContent);
		}
		else if(e.getActionCommand().equals("2")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			
			JLabel Penjelasan = new JLabel("Gerakan keranjang ke kanan atau ke kiri untuk menangkap",JLabel.CENTER);
			Penjelasan.setForeground(Color.BLACK);
			Penjelasan.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
			
			JLabel Penjelasan2 = new JLabel("Buah buahan untuk menambah skor dan hindari hambatan",JLabel.CENTER);
			Penjelasan2.setForeground(Color.BLACK);
			Penjelasan2.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
			
			JLabel Penjelasan3 = new JLabel("selain buah.Jika terkena hambatan lives berkurang",JLabel.CENTER);
			Penjelasan3.setForeground(Color.BLACK);
			Penjelasan3.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
			
			JLabel Penjelasan4 = new JLabel("permainan berakhir setelah waktu habis",JLabel.CENTER);
			Penjelasan4.setForeground(Color.BLACK);
			Penjelasan4.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
			
			JButton BackButton = new JButton("Kembali");
			BackButton.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
			BackButton.setActionCommand("4");
			
			JPanel boxPenjelasan = new JPanel();
			boxPenjelasan.setBackground(new Color(0,191,255));
			boxPenjelasan.setLayout(new GridLayout(4,1));
			boxPenjelasan.add(Penjelasan);
			boxPenjelasan.add(Penjelasan2);
			boxPenjelasan.add(Penjelasan3);
			boxPenjelasan.add(Penjelasan4);
			
			JPanel boxBackButton = new JPanel();
			boxBackButton.setBackground(new Color(0,191,255));
			boxBackButton.setLayout(new FlowLayout());
			boxBackButton.add(BackButton);
			
			JPanel contentPanelku = new JPanel();
			contentPanelku.setBackground(new Color(0,191,255));
			contentPanelku.setLayout(new GridLayout(2,2));
			contentPanelku.add(boxPenjelasan);
			contentPanelku.add(boxBackButton);
			
			grid.add(Judul);
			grid.add(contentPanelku);
		}
		else if(e.getActionCommand().equals("3")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			
			JLabel Penjelasan = new JLabel("Level 1");
			Penjelasan.setForeground(Color.BLACK);
			
			JLabel Penjelasan2 = new JLabel("Level 2");
			Penjelasan2.setForeground(Color.BLACK);
			
			JLabel Penjelasan3 = new JLabel("Level 3");
			Penjelasan3.setForeground(Color.BLACK);
			
			JButton BackButton = new JButton("Kembali");
			BackButton.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
			BackButton.setActionCommand("4");
			//BackButton.setPreferredSize(new Dimension(120, 13));
			
			JPanel boxPenjelasan = new JPanel();
			boxPenjelasan.setBackground(new Color(0,191,255));
			boxPenjelasan.setLayout(new FlowLayout());
			boxPenjelasan.add(Penjelasan);
			
			JPanel boxPenjelasan2 = new JPanel();
			boxPenjelasan2.setBackground(new Color(0,191,255));
			boxPenjelasan2.setLayout(new FlowLayout());
			boxPenjelasan2.add(Penjelasan2);
			
			JPanel boxPenjelasan3 = new JPanel();
			boxPenjelasan3.setBackground(new Color(0,191,255));
			boxPenjelasan3.setLayout(new FlowLayout());
			boxPenjelasan3.add(Penjelasan3);
			
			JPanel boxBackButton = new JPanel();
			boxBackButton.setBackground(new Color(0,191,255));
			boxBackButton.setLayout(new FlowLayout());
			boxBackButton.add(BackButton);
			
			simpanText tulis = new simpanText();
			
			ArrayList<Player> player = new ArrayList<Player>();
			GameMenuUtama.savegame.getHighscoreByLevel(1, 5, player);
			
			JLabel Score1 = new JLabel("");
			try{
				if(player.get(0) != null){
					Score1 = new JLabel(player.get(0).getUsername()+" : "+player.get(0).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score1.setForeground(Color.BLACK);
			Score1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore1 = new JPanel();
			boxScore1.setBackground(new Color(0,191,255));
			boxScore1.setLayout(new FlowLayout());
			boxScore1.add(Score1);
			
			JLabel Score2 = new JLabel("");
			try{
				if(player.get(1) != null){
					Score2 = new JLabel(player.get(1).getUsername()+" : "+player.get(1).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score2.setForeground(Color.BLACK);
			Score2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore2 = new JPanel();
			boxScore2.setBackground(new Color(0,191,255));
			boxScore2.setLayout(new FlowLayout());
			boxScore2.add(Score2);
			
			JLabel Score3 = new JLabel("");
			try{
				if(player.get(2) != null){
					Score3 = new JLabel(player.get(2).getUsername()+" : "+player.get(2).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score3.setForeground(Color.BLACK);
			Score3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore3 = new JPanel();
			boxScore3.setBackground(new Color(0,191,255));
			boxScore3.setLayout(new FlowLayout());
			boxScore3.add(Score3);
			
			JLabel Score4 = new JLabel("");
			try{
				if(player.get(3) != null){
					Score4 = new JLabel(player.get(3).getUsername()+" : "+player.get(3).getSkor(),JLabel.CENTER);
				}
			}catch(IndexOutOfBoundsException iob){
				
			}
			Score4.setForeground(Color.BLACK);
			Score4.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore4 = new JPanel();
			boxScore4.setBackground(new Color(0,191,255));
			boxScore4.setLayout(new FlowLayout());
			boxScore4.add(Score4);
			
			JLabel Score5 = new JLabel("");
			try{
				if(player.get(4) != null){
					Score5 = new JLabel(player.get(4).getUsername()+" : "+player.get(4).getSkor(),JLabel.CENTER);
				}
			}catch(IndexOutOfBoundsException iob){
				
			}
			Score5.setForeground(Color.BLACK);
			Score5.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore5 = new JPanel();
			boxScore5.setBackground(new Color(0,191,255));
			boxScore5.setLayout(new FlowLayout());
			boxScore5.add(Score5);
			
			player = new ArrayList<Player>();
			GameMenuUtama.savegame.getHighscoreByLevel(2, 5, player);
			
			JLabel Score12 = new JLabel("");
			try{
				if(player.get(0) != null){
					Score12 = new JLabel(player.get(0).getUsername()+" : "+player.get(0).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score12.setForeground(Color.BLACK);
			Score12.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore12 = new JPanel();
			boxScore12.setBackground(new Color(0,191,255));
			boxScore12.setLayout(new FlowLayout());
			boxScore12.add(Score12);
			
			JLabel Score22 = new JLabel("");
			try{
				if(player.get(1) != null){
					Score22 = new JLabel(player.get(1).getUsername()+" : "+player.get(1).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score22.setForeground(Color.BLACK);
			Score22.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore22 = new JPanel();
			boxScore22.setBackground(new Color(0,191,255));
			boxScore22.setLayout(new FlowLayout());
			boxScore22.add(Score22);
			
			JLabel Score32 = new JLabel("");
			try{
				if(player.get(2) != null){
					Score32 = new JLabel(player.get(2).getUsername()+" : "+player.get(2).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score32.setForeground(Color.BLACK);
			Score32.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore32 = new JPanel();
			boxScore32.setBackground(new Color(0,191,255));
			boxScore32.setLayout(new FlowLayout());
			boxScore32.add(Score32);
			
			JLabel Score42 = new JLabel("");
			try{
				if(player.get(3) != null){
					Score42 = new JLabel(player.get(3).getUsername()+" : "+player.get(3).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score42.setForeground(Color.BLACK);
			Score42.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore42 = new JPanel();
			boxScore42.setBackground(new Color(0,191,255));
			boxScore42.setLayout(new FlowLayout());
			boxScore42.add(Score42);
			
			JLabel Score52 = new JLabel("");
			try{
				if(player.get(4) != null){
					Score52 = new JLabel(player.get(4).getUsername()+" : "+player.get(4).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score52.setForeground(Color.BLACK);
			Score52.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore52 = new JPanel();
			boxScore52.setBackground(new Color(0,191,255));
			boxScore52.setLayout(new FlowLayout());
			boxScore52.add(Score52);
			
			player = new ArrayList<Player>();
			GameMenuUtama.savegame.getHighscoreByLevel(3, 5, player);
			
			JLabel Score13 = new JLabel("");
			try{
				if(player.get(0) != null){
					Score13 = new JLabel(player.get(0).getUsername()+" : "+player.get(0).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score13.setForeground(Color.BLACK);
			Score13.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore13 = new JPanel();
			boxScore13.setBackground(new Color(0,191,255));
			boxScore13.setLayout(new FlowLayout());
			boxScore13.add(Score13);
			
			JLabel Score23 = new JLabel("");
			try{
				if(player.get(1) != null){
					Score23 = new JLabel(player.get(1).getUsername()+" : "+player.get(1).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score23.setForeground(Color.BLACK);
			Score23.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore23 = new JPanel();
			boxScore23.setBackground(new Color(0,191,255));
			boxScore23.setLayout(new FlowLayout());
			boxScore23.add(Score23);
			
			JLabel Score33 = new JLabel("");
			try{
				if(player.get(2) != null){
					Score33 = new JLabel(player.get(2).getUsername()+" : "+player.get(2).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score33.setForeground(Color.BLACK);
			Score33.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore33 = new JPanel();
			boxScore33.setBackground(new Color(0,191,255));
			boxScore33.setLayout(new FlowLayout());
			boxScore33.add(Score32);
			
			JLabel Score43 = new JLabel("");
			try{
				if(player.get(3) != null){
					Score43 = new JLabel(player.get(3).getUsername()+" : "+player.get(3).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score43.setForeground(Color.BLACK);
			Score43.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore43 = new JPanel();
			boxScore43.setBackground(new Color(0,191,255));
			boxScore43.setLayout(new FlowLayout());
			boxScore43.add(Score42);
			
			JLabel Score53 = new JLabel("");
			try{
				if(player.get(4) != null){
					Score53 = new JLabel(player.get(4).getUsername()+" : "+player.get(4).getSkor(),JLabel.CENTER);
				}
			}
			catch(IndexOutOfBoundsException iob){
				
			}
			Score53.setForeground(Color.BLACK);
			Score53.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JPanel boxScore53 = new JPanel();
			boxScore53.setBackground(new Color(0,191,255));
			boxScore53.setLayout(new FlowLayout());
			boxScore53.add(Score53);
			
			
			JPanel contentPanelku = new JPanel();
			contentPanelku.setBackground(new Color(0,191,255));
			contentPanelku.setLayout(new GridLayout(7,1));
			contentPanelku.add(boxPenjelasan);
			contentPanelku.add(boxScore1);
			contentPanelku.add(boxScore2);
			contentPanelku.add(boxScore3);
			contentPanelku.add(boxScore4);
			contentPanelku.add(boxScore5);
			
			JPanel contentPanelku2 = new JPanel();
			contentPanelku2.setBackground(new Color(0,191,255));
			contentPanelku2.setLayout(new GridLayout(7,1));
			contentPanelku2.add(boxPenjelasan2);
			contentPanelku2.add(boxScore12);
			contentPanelku2.add(boxScore22);
			contentPanelku2.add(boxScore32);
			contentPanelku2.add(boxScore42);
			contentPanelku2.add(boxScore52);
			
			JPanel contentPanelku3 = new JPanel();
			contentPanelku3.setBackground(new Color(0,191,255));
			contentPanelku3.setLayout(new GridLayout(7,1));
			contentPanelku3.add(boxPenjelasan3);
			contentPanelku3.add(boxScore13);
			contentPanelku3.add(boxScore23);
			contentPanelku3.add(boxScore33);
			contentPanelku3.add(boxScore43);
			contentPanelku3.add(boxScore53);
			
			JPanel isiPanel = new JPanel();
			isiPanel.setBackground(new Color(0,191,255));
			isiPanel.setLayout(new GridLayout(1,4));
			isiPanel.add(contentPanelku);
			isiPanel.add(contentPanelku2);
			isiPanel.add(contentPanelku3);
			isiPanel.add(boxBackButton);
			
			grid.add(Judul);
			grid.add(isiPanel);
		}
		else if(e.getActionCommand().equals("4")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			grid.add(Judul);
			grid.add(GameMenuUtama.gridkuContent2);
		}
		else if(e.getActionCommand().equals("5")){
			String passwords = "";
			
			for(int i=0;i<Password.getPassword().length;i++){
				passwords += Password.getPassword()[i];
			}
			
			if(GameMenuUtama.savegame.cekPlayer(Nama.getText().toString(),  passwords, GameMenuUtama.player)){
				GameMenuUtama.savegame.mendapatkanLevel(Nama.getText(),passwords, GameMenuUtama.player);
				grid.removeAll();
				grid.revalidate();
				grid.repaint();
				
				final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
				JPanel contentPanelku = new JPanel();
				
				JButton ButtonLevel1 = new JButton();
				ButtonLevel1.setForeground(new Color(0,0,0));
				ButtonLevel1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
				JPanel boxButtonLevel1 = new JPanel();
				boxButtonLevel1.setBackground(new Color(0,191,255));
				boxButtonLevel1.setLayout(new FlowLayout());
				
				JButton ButtonLevel2 = new JButton();
				ButtonLevel2.setForeground(new Color(0,0,0));
				ButtonLevel2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
				JPanel boxButtonLevel2 = new JPanel();
				boxButtonLevel2.setBackground(new Color(0,191,255));
				boxButtonLevel2.setLayout(new FlowLayout());
				
				JButton ButtonLevel3 = new JButton();
				ButtonLevel3.setForeground(new Color(0,0,0));
				ButtonLevel3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
				JPanel boxButtonLevel3 = new JPanel();
				boxButtonLevel3.setBackground(new Color(0,191,255));
				boxButtonLevel3.setLayout(new FlowLayout());
				
				
				//Level 1
				if(GameMenuUtama.player.getUnlock().get(0) == 1){
					ButtonLevel1.setText("Level 1");
					ButtonLevel1.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
					ButtonLevel1.setActionCommand("level 1");
				}
				else{
					ButtonLevel1.setText("Locked");
				}
				
				//Level 2
				if(GameMenuUtama.player.getUnlock().get(1) == 1){
					ButtonLevel2.setText("Level 2");
					ButtonLevel2.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
					ButtonLevel2.setActionCommand("level 2");
				}
				else{
					ButtonLevel2.setText("Locked");
				}
				
				//Level 2
				if(GameMenuUtama.player.getUnlock().get(2) == 1){
					ButtonLevel3.setText("Level 3");
					ButtonLevel3.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
					ButtonLevel3.setActionCommand("level 3");
				}
				else{
					ButtonLevel3.setText("Locked");
				}
				
				boxButtonLevel1.add(ButtonLevel1);
				boxButtonLevel2.add(ButtonLevel2);
				boxButtonLevel3.add(ButtonLevel3);
				contentPanelku.add(boxButtonLevel1);
				contentPanelku.add(boxButtonLevel2);
				contentPanelku.add(boxButtonLevel3);
				
				JButton ButtonUnlocked = new JButton("Kembali");
				ButtonUnlocked.setForeground(new Color(0,0,0));
				ButtonUnlocked.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
				ButtonUnlocked.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2));
				ButtonUnlocked.setActionCommand("4");
				
				JPanel boxButtonUnlocked = new JPanel();
				boxButtonUnlocked.setBackground(new Color(0,191,255));
				boxButtonUnlocked.setLayout(new FlowLayout());
				boxButtonUnlocked.add(ButtonUnlocked);
				
				contentPanelku.setBackground(new Color(0,191,255));
				contentPanelku.setLayout(new GridLayout(4,1));
				contentPanelku.add(boxButtonUnlocked);
				
				grid.add(Judul);
				grid.add(contentPanelku);
			}
			else{
				JOptionPane.showMessageDialog(null, "Maaf Password atau Username Kamu Salah !");
			}
		}
		else if(e.getActionCommand().equals("6")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			final BuatGambar kosong = new BuatGambar("src/Game/images/blank.png",0.02,0.02);
			final BuatGambar kosong2 = new BuatGambar("src/Game/images/blank.png",0.02,0.02);
			
			JLabel NamaText = new JLabel("Username : ");
			NamaText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JLabel PassText = new JLabel("Password : ");
			PassText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			JLabel EmailText = new JLabel("Email : ");
			EmailText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			Nama.setHorizontalAlignment(JTextField.CENTER);
			Nama.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			Password.setHorizontalAlignment(JTextField.CENTER);
			Password.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
			Email.setHorizontalAlignment(JTextField.CENTER);
			Email.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			JButton Oke = new JButton("Oke");
			Dimension dim = new Dimension();
			dim.setSize(100, 25);
			Oke.setPreferredSize(dim);
			Oke.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2,Password,Email));
			Oke.setActionCommand("7");
			Oke.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			JButton Kembali = new JButton("Kembali");
			Kembali.setPreferredSize(dim);
			Kembali.addActionListener(new AksiUtama(frame,grid,grid2,Nama,frame2,Password,Email));
			Kembali.setActionCommand("4");
			Kembali.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
			
			JPanel boxContent = new JPanel();
			boxContent.setBackground(new Color(0,191,255));
			boxContent.setLayout(new GridLayout(7,1));
			
			JPanel flowku = new JPanel();
			flowku.setBackground(new Color(0,191,255));
			flowku.setLayout(new FlowLayout());
			
			flowku.add(NamaText);
			
			JPanel flowku2 = new JPanel();
			flowku2.setBackground(new Color(0,191,255));
			flowku2.setLayout(new FlowLayout());
			
			flowku2.add(Nama);
			
			JPanel flowku3 = new JPanel();
			flowku3.setBackground(new Color(0,191,255));
			flowku3.setLayout(new FlowLayout());
			
			flowku3.add(PassText);
			
			JPanel flowku4 = new JPanel();
			flowku4.setBackground(new Color(0,191,255));
			flowku4.setLayout(new FlowLayout());
			
			flowku4.add(Password);
			
			JPanel flowku5 = new JPanel();
			flowku5.setBackground(new Color(0,191,255));
			flowku5.setLayout(new FlowLayout());
			
			flowku5.add(EmailText);
			
			JPanel flowku6 = new JPanel();
			flowku6.setBackground(new Color(0,191,255));
			flowku6.setLayout(new FlowLayout());
			
			flowku6.add(Email);
			
			JPanel flowku7 = new JPanel();
			flowku7.setBackground(new Color(0,191,255));
			flowku7.setLayout(new FlowLayout());
			
			flowku7.add(Oke);
			
			JPanel flowku8 = new JPanel();
			flowku8.setBackground(new Color(0,191,255));
			flowku8.setLayout(new FlowLayout());
			
			flowku8.add(Kembali);
			
			JPanel gridku1 = new JPanel();
			gridku1.setBackground(new Color(0,191,255));
			gridku1.setLayout(new GridLayout(1,2));
			
			gridku1.add(flowku7);
			gridku1.add(flowku8);
			
			boxContent.add(flowku);
			boxContent.add(flowku2);
			boxContent.add(flowku3);
			boxContent.add(flowku4);
			boxContent.add(flowku5);
			boxContent.add(flowku6);
			boxContent.add(gridku1);
			
			grid.add(Judul);
			grid.add(boxContent);
		}
		else if(e.getActionCommand().equals("7")){
			String passku="";
			for(int i=0;i<Password.getPassword().length;i++){
				passku += Password.getPassword()[i];
			}
			if(GameMenuUtama.savegame.daftarPlayer(Nama.getText().toString(), passku, Email.getText().toString())){
				JOptionPane.showMessageDialog(null, "Anda sudah mendaftar dengan Username "+Nama.getText().toString()+" !");
			}
			else{
				JOptionPane.showMessageDialog(null, "Maaf Username Sudah Terdaftar !");
			}
		}
		else if(e.getActionCommand().equals("level 1")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
			grid.add(Judul);
			grid.add(GameMenuUtama.gridkuContent2);
			
			frame.setVisible(false);
			GameMenuUtama.player.setLevel(1);
			gd.setLevel(1);
			//System.out.print(Nama.getText());
			if(GameMenuUtama.sudahMain == 0){
				String[] name = {"Art", "Dan", "Jen"};
				gd.main(name);
			}
			else{
				frame2.setVisible(true);
				resetGame();
			}
			GameMenuUtama.sudahMain = 1;
		}
		else if(e.getActionCommand().equals("level 2")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			frame.setVisible(false);
			GameMenuUtama.player.setLevel(2);
			gd.setLevel(2);
			if(GameMenuUtama.sudahMain == 0){
				String[] name = {"Art", "Dan", "Jen"};
				gd.main(name);
			}
			else{
				frame2.setVisible(true);
				resetGame();
			}
			GameMenuUtama.sudahMain = 1;
		}
		else if(e.getActionCommand().equals("level 3")){
			grid.removeAll();
			grid.revalidate();
			grid.repaint();
			
			frame.setVisible(false);
			GameMenuUtama.player.setLevel(3);
			gd.setLevel(3);
			if(GameMenuUtama.sudahMain == 0){
				String[] name = {"Art", "Dan", "Jen"};
				gd.main(name);
			}
			else{
				frame2.setVisible(true);
				resetGame();
			}
			GameMenuUtama.sudahMain = 1;
		}
		else if(e.getActionCommand().equals("menu awal")){
			GameDrop.waktu.setTimeClock(0,1,30);
			GameDrop.valueSkor.setText("0");
			GameDrop.valueLives.setText("3");
			GameDrop.timerAku.cancel();
			GameDrop.timerku.cancel();
			GameDrop.timerAnim.cancel();
			
			Timer tungguWaktu3 = new Timer();
			TimerTask tungguTask3 = new TimerTask(){
				@Override
				public void run() {
					for(int i=0;i<GameDrop.ItemDrop.size();i++){
						GameDrop.ItemTimer.get(0).cancel();
						GameDrop.ItemTimer.remove(0);
					}
				};
			};
			tungguWaktu3.schedule(tungguTask3,2000);
			
			Timer tungguWaktu2 = new Timer();
			TimerTask tungguTask2 = new TimerTask(){
				@Override
				public void run() {
					GameDrop.hapusBenda();
				};
			};
			
			tungguWaktu2.schedule(tungguTask2,4000);
			
			Timer tungguWaktu = new Timer();
			TimerTask tungguTask = new TimerTask(){
				@Override
				public void run() {
					frame2.setVisible(false);
					grid.removeAll();
					grid.revalidate();
					grid.repaint();
					
					final BuatGambar Judul = new BuatGambar("src/Game/images/title.png",1,1);
					grid.add(Judul);
					grid.add(GameMenuUtama.gridkuContent2);
					frame.setVisible(true);
				};
			};
			tungguWaktu.schedule(tungguTask,6000);
		}
		else if(e.getActionCommand().equals("reset")){
			GameDrop.waktu.setTimeClock(0,1,30);
			GameDrop.valueSkor.setText("0");
			GameDrop.valueLives.setText("3");
			GameDrop.timerAku.cancel();
			GameDrop.timerku.cancel();
			GameDrop.timerAnim.cancel();
			
			Timer tungguWaktu3 = new Timer();
			TimerTask tungguTask3 = new TimerTask(){
				@Override
				public void run() {
					for(int i=0;i<GameDrop.ItemDrop.size();i++){
						GameDrop.ItemTimer.get(0).cancel();
						GameDrop.ItemTimer.remove(0);
					}
				};
			};
			tungguWaktu3.schedule(tungguTask3,2000);
			
			Timer tungguWaktu2 = new Timer();
			TimerTask tungguTask2 = new TimerTask(){
				@Override
				public void run() {
					GameDrop.hapusBenda();
				};
			};
			tungguWaktu2.schedule(tungguTask2,4000);
			
			Timer tungguWaktu = new Timer();
			TimerTask tungguTask = new TimerTask(){
				@Override
				public void run() {
					resetGame();
				};
			};
			tungguWaktu.schedule(tungguTask,6000);
		}
		else if(e.getActionCommand().equals("exit")){
			frame.dispose();
		}
	}
	public static void resetGame(){
			Random random = new Random();
			GameDrop.timerAku = new Timer();
			GameDrop.timerku = new Timer();
			GameDrop.tts = new TimerTask(){
				@Override
				public void run() {
					GameDrop.updateWaktu();
				};
			};
			GameDrop.tts2 = new TimerTask(){
				@Override
				public void run() {
					GameDrop.acakJatuhBenda();
				};
			};
			GameDrop.timerAku.scheduleAtFixedRate(GameDrop.tts2,new java.util.Date(),3000+random.nextInt(3000));
			GameDrop.timerku.scheduleAtFixedRate(GameDrop.tts,new java.util.Date(),1000);
	}
}
