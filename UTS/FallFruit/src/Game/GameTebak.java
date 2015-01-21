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

public class GameTebak {
    public static void main(String[] arg){
		final JFrame frame = new JFrame("Game Tebak Gambar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gridku = new JPanel();
		gridku.setBackground(new Color(0,191,255));
		gridku.setLayout(new GridLayout(4,3));
		gridku.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		
		LinearGradient gridku2 = new LinearGradient(new Color(65,105,225),new Color(0,0,205));
		gridku2.setBackground(new Color(0,0,128));
		gridku2.setLayout(new GridLayout(2,2));
		
		JPanel gridku3 = new JPanel( new FlowLayout() );
		
		final JLabel pembuat = new JLabel("Dibuat oleh : Fajar Muhammad F - 2013");
		pembuat.setForeground(new Color(0,0,139));
		pembuat.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		final Random random = new Random();
		
		JLabel skor = new JLabel("Skor : ");
		skor.setForeground(new Color(224,255,255));
		skor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		final JLabel valueSkor = new JLabel("0");
		valueSkor.setForeground(new Color(224,255,255));
		valueSkor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
		JLabel timer = new JLabel("Timer : ");
		timer.setForeground(new Color(224,255,255));
		timer.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		final JLabel valueTimer = new JLabel("0");
		valueTimer.setForeground(new Color(224,255,255));
		valueTimer.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			
		final Clock waktu = new Clock();
		waktu.resetClock();
		waktu.increaseSeconds();
		valueTimer.setText(waktu.displayTime());		
				
		java.util.Timer timerku = new Timer();
		TimerTask tts = new TimerTask() {
			@Override
			public void run() {
				waktu.increaseSeconds();
				valueTimer.setText(waktu.displayTime());	
			};
		};
		timerku.scheduleAtFixedRate(tts,new java.util.Date(),1000);
						
		final java.util.List<BuatGambar> daftar = new ArrayList<BuatGambar>();
		final java.util.List<BuatGambar> openDaftar = new ArrayList<BuatGambar>();
		final java.util.List<Integer> daftarGambar = new ArrayList<Integer>();
			
		for(int i=0;i<16;i++){
			final BuatGambar si = new BuatGambar("Game/images/tutup.png",0.15,0.15);
			int acak = random.nextInt(8);
			int cek = 0;
			
			do{
				cek = 0;
				acak = new Random().nextInt(8);
				for(int y=0;y<i;y++){
					if(acak == daftarGambar.get(y)){
						cek++;
					}
				}
				
			}while(cek == 2);
			daftarGambar.add(acak);
			
			si.setIndexGambar(acak);
			si.setChangeUrl("Game/images/"+(acak+1)+".png");
			daftar.add(si);
		}
		
		MouseListener listener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				final BuatGambar gambar = (BuatGambar) e.getSource();
				if(gambar.getUrlName() == "Game/images/tutup.png"){
					if(openDaftar.size() == 0){
						gambar.changeImage(gambar.getChangeUrl());
						openDaftar.add(gambar);
					}
					else{
						gambar.changeImage(gambar.getChangeUrl());
						if(openDaftar.get(0).getIndexGambar() == gambar.getIndexGambar()){
							int SkorLama = Integer.parseInt(valueSkor.getText());
							SkorLama += 20;
							valueSkor.setText(String.valueOf(SkorLama));
							openDaftar.remove(0);
							if(valueSkor.getText().equals("160")){
								int opsi = JOptionPane.showConfirmDialog(null,"Selamat anda menang !\n Mau main lagi ?", "Game Tebak", JOptionPane.YES_NO_OPTION);
								if(opsi == JOptionPane.YES_OPTION){
									for(int i=0;i<16;i++){
										daftar.get(i).changeImage("Game/images/tutup.png");
									}
								}
								else{
									frame.dispose();
								}
							}
						}
						else{
							java.util.Timer timer = new Timer();
							TimerTask tt = new TimerTask() {
								@Override
								public void run() {						
									gambar.changeImage("Game/images/tutup.png");									
									openDaftar.get(0).changeImage("Game/images/tutup.png");
									openDaftar.remove(0);
								};
							};
							timer.schedule(tt,500);
						}
					}
				}
			}
		};
		
		for(int i=0;i<16;i++){
			final BuatGambar si = new BuatGambar("Game/images/tutup.png",0.15,0.15);
			daftar.get(i).addMouseListener(listener);
			gridku.add(daftar.get(i));
		}
		
		gridku2.add(skor);
		gridku2.add(valueSkor);
		
		gridku2.add(timer);
		gridku2.add(valueTimer);
		
		gridku3.add(pembuat);
		
		frame.add(gridku,BorderLayout.NORTH);
		frame.add(gridku2,BorderLayout.CENTER);
		frame.add(gridku3,BorderLayout.SOUTH);
		frame.setSize(300,440);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

