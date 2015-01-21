package Game;

import Game.BuatGambar;
import Game.GameDrop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Aksi implements KeyListener{
	final JLabel Skor;
	
	public Aksi(final JLabel valueSkor){
		Skor=valueSkor;
	}
	
	public void keyTyped(KeyEvent e) {
		
    }
    
    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			if(Game.GameDrop.getAvatarPos() < 63 ){
				Game.GameDrop.MoveRight(Game.GameDrop.getAvatarPos(),"src/Game/images/basket.png");
				Game.GameDrop.setAvatarPos(Game.GameDrop.getAvatarPos()+1);
				for(int i = 0;i<Game.GameDrop.ItemDrop.size();i++){
					if(Game.GameDrop.ItemDrop.get(i).getIndexGambar() == Game.GameDrop.getAvatarPos()){
						int pointku=Game.GameDrop.ItemDrop.get(i).getPoint();
						Skor.setText(String.valueOf(Integer.parseInt(Skor.getText())+pointku));
						//Ganti status
						Game.GameDrop.ItemDrop.get(i).setStatus(0);
					};
				}
			}
        }
        else if(e.getKeyCode() == 37){
			if(Game.GameDrop.getAvatarPos() > 56 ){
				Game.GameDrop.MoveLeft(Game.GameDrop.getAvatarPos(),"src/Game/images/basket.png");
				Game.GameDrop.setAvatarPos(Game.GameDrop.getAvatarPos()-1);
				for(int i = 0;i<Game.GameDrop.ItemDrop.size();i++){
					if(Game.GameDrop.ItemDrop.get(i).getIndexGambar() == Game.GameDrop.getAvatarPos()){
						int pointku=Game.GameDrop.ItemDrop.get(i).getPoint();
						Skor.setText(String.valueOf(Integer.parseInt(Skor.getText())+pointku));
						//Ganti status
						Game.GameDrop.ItemDrop.get(i).setStatus(0);
					};
				}
			}
		}
    }
    
    public void keyReleased(KeyEvent e) {
        
    }
}
