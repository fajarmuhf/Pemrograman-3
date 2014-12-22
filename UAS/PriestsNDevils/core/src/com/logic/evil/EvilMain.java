package com.logic.evil;

//import io.socket.SocketIO;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.logic.evil.SpleshScreen;
import com.logic.evil.EvilMain;
import com.logic.evil.component.SendText;
import com.logic.evil.component.Player;
import com.logic.evil.component.Skor;

public class EvilMain extends Game{
	SpriteBatch batch;
	Texture img;
	EvilMain Permainan = this;
	public float soundFxVolume;
	public float soundBgmVolume;
	public Music bgmMusic;
	public Preferences saveData;
	public AssetManager assets;
	public Player pengguna;
	public Skor skor;
	public SendText sendText;
	//public SocketIO socket;
	
	@Override
	public void create () {
		assets = new AssetManager();
		saveData = Gdx.app.getPreferences( "profile" );
		pengguna = new Player();
		skor = new Skor();
		sendText = new SendText();
		if(saveData.getBoolean("simpan") == false){
			soundFxVolume = 0.25f;
			soundBgmVolume = 0.125f;
			saveData.putFloat("fxVolume", soundFxVolume);
			saveData.putFloat("bgmVolume", soundBgmVolume);
    		saveData.putBoolean("simpan", true);
			saveData.flush();
		}
		else{
			soundFxVolume = saveData.getFloat("fxVolume");
			soundBgmVolume = saveData.getFloat("bgmVolume");
		}

		bgmMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/bgm.mp3"));
		bgmMusic.setVolume(soundBgmVolume);                 // sets the volume to half the maximum volume
		bgmMusic.setLooping(true);

		//socket = new SocketIO();
		
		setScreen(new SpleshScreen(Permainan));
	}
}
