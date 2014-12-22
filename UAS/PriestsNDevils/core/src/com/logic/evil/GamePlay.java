package com.logic.evil;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.logic.evil.MenuUtama;
import com.logic.evil.EvilMain;
import com.logic.evil.component.Player;
import com.logic.evil.component.Position;
import com.logic.evil.component.Unit;
import com.logic.evil.database.DatabaseEvil;


public class GamePlay implements Screen {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	Stage stage;
	CameraInputController camController;
	TextureAtlas gambar;
	Skin skinGambar;
	BitmapFont font;
	TextButtonStyle backgroundStyle;
	TextButtonStyle optionStyle;
	TextButtonStyle priestStyle;
	TextButtonStyle devilStyle;
	TextButtonStyle priestflipStyle;
	TextButtonStyle devilflipStyle;
	TextButtonStyle devilinStyle;
	TextButtonStyle deviloutStyle;
	TextButtonStyle priestinStyle;
	TextButtonStyle priestoutStyle;
	TextButtonStyle goStyle;
	TextButtonStyle shipemptyStyle;
	TextButtonStyle shippriestStyle;
	TextButtonStyle shipdevilStyle;
	TextButtonStyle shiptwodevilStyle;
	TextButtonStyle shiptwopriestStyle;
	TextButtonStyle shippriestdevilStyle;
	TextButtonStyle winboxStyle;
	TextButtonStyle losetimeboxStyle;
	TextButtonStyle losedeadboxStyle;
	TextButtonStyle backStyle;
	TextButton backgroundButton;
	TextButton optionButton;
	TextButton goButton;
	TextButton priestinRButton;
	TextButton priestinLButton;
	TextButton priestoutButton;
	TextButton devilinRButton;
	TextButton devilinLButton;
	TextButton deviloutButton;
	TextButton priest1Button;
	TextButton priest2Button;
	TextButton priest3Button;
	TextButton devil1Button;
	TextButton devil2Button;
	TextButton devil3Button;
	TextButton boxButton;
	TextButton backButton;
	TextButton shipButton;
	LabelStyle timerStyle;
	LabelStyle skorStyle;
	Label timerText;
	Label skorText;
	Sound clickSound;
	Sound devilSound;
	Sound winSound;
	float soundVolume;
	EvilMain game;
	
	ArrayList<Unit> PulauKananUnit = new ArrayList<Unit>();
	ArrayList<Unit> PulauKiriUnit = new ArrayList<Unit>();
	ArrayList<Unit> PerahuUnit = new ArrayList<Unit>();
	
	ArrayList<Position> PositionPulauKanan = new ArrayList<Position>();
	ArrayList<Position> PositionPulauKiri = new ArrayList<Position>();
	
	String PerahuPosisi = "Kanan";
	boolean pressableButton = true;
	boolean pressableTime = true;
	float timeSinceCollision = 0f;
	
	int waktu = 180;
	int skor = 100;
	
	GamePlay(EvilMain Permainan){
		game = Permainan;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		cam.update();
		
		if(pressableTime){
			timeSinceCollision += Gdx.graphics.getDeltaTime();
			if(timeSinceCollision > 1.0f) {
				if(waktu > 1){
					waktu--;
					skor = ((waktu * 100)/ 60) ;
					timerText.setText("Timer : "+waktu);
					skorText.setText("Skor : "+skor);
					timeSinceCollision=0f;
				}
				else{
					waktu=0;
					skor = ((waktu * 100)/ 60) ;
					timerText.setText("Timer : "+waktu);
					skorText.setText("Skor : "+skor);
					boxButton.setStyle(losetimeboxStyle);
					timeSinceCollision=0f;
					boxButton.setVisible(true);
			        backButton.setVisible(true);
			        pressableButton = false;
			        long idClick = devilSound.play();
			        devilSound.setVolume(idClick, soundVolume);
			        pressableTime = false;
				}
			    // do collision stuff
			} else {
			    // ignore the collision
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		cam = new OrthographicCamera(10f, 10f * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));		
        cam.position.set(10, 5, 10);
        cam.direction.set(-1, -1, -1);
        cam.near = 1f;
        cam.far = 300f;
        cam.update(); 
        
        stage = new Stage(new ScreenViewport());
        stage.clear();
        
        Gdx.input.setInputProcessor(stage); 
        
        batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		gambar = game.assets.get("img/gameplay/gameplay.pack"); 
        skinGambar = new Skin();
        skinGambar.addRegions(gambar);
        
        font = game.assets.get("fnt/postcryp.fnt");
        font.setColor(Color.YELLOW);
        font.setScale((float) 1*Gdx.graphics.getWidth()/960, (float) 1*Gdx.graphics.getHeight()/540);
        
        backgroundStyle = new TextButtonStyle();
        backgroundStyle.up = skinGambar.getDrawable("background");
        backgroundStyle.font = font;
        
        optionStyle = new TextButtonStyle();
        optionStyle.up = skinGambar.getDrawable("option");
        optionStyle.font = font;
        
        goStyle = new TextButtonStyle();
        goStyle.up = skinGambar.getDrawable("go");
        goStyle.font = font;
        
        priestStyle = new TextButtonStyle();
        priestStyle.up = skinGambar.getDrawable("priest");
        priestStyle.font = font;
        
        priestflipStyle = new TextButtonStyle();
        priestflipStyle.up = skinGambar.getDrawable("priestflip");
        priestflipStyle.font = font;
        
        priestinStyle = new TextButtonStyle();
        priestinStyle.up = skinGambar.getDrawable("priestin");
        priestinStyle.font = font;
        
        priestoutStyle = new TextButtonStyle();
        priestoutStyle.up = skinGambar.getDrawable("priestout");
        priestoutStyle.font = font;
        
        devilStyle = new TextButtonStyle();
        devilStyle.up = skinGambar.getDrawable("devil");
        devilStyle.font = font;
        
        devilflipStyle = new TextButtonStyle();
        devilflipStyle.up = skinGambar.getDrawable("devilflip");
        devilflipStyle.font = font;
        
        devilinStyle = new TextButtonStyle();
        devilinStyle.up = skinGambar.getDrawable("devilin");
        devilinStyle.font = font;
        
        deviloutStyle = new TextButtonStyle();
        deviloutStyle.up = skinGambar.getDrawable("devilout");
        deviloutStyle.font = font;
        
        shipemptyStyle = new TextButtonStyle();
        shipemptyStyle.up = skinGambar.getDrawable("shipempty");
        shipemptyStyle.font = font;
        
        shippriestStyle = new TextButtonStyle();
        shippriestStyle.up = skinGambar.getDrawable("shippriest");
        shippriestStyle.font = font;
        
        shipdevilStyle = new TextButtonStyle();
        shipdevilStyle.up = skinGambar.getDrawable("shipdevil");
        shipdevilStyle.font = font;
        
        shiptwopriestStyle = new TextButtonStyle();
        shiptwopriestStyle.up = skinGambar.getDrawable("shiptwopriest");
        shiptwopriestStyle.font = font;
        
        shiptwodevilStyle = new TextButtonStyle();
        shiptwodevilStyle.up = skinGambar.getDrawable("shiptwodevil");
        shiptwodevilStyle.font = font;
        
        shippriestdevilStyle = new TextButtonStyle();
        shippriestdevilStyle.up = skinGambar.getDrawable("shippriestdevil");
        shippriestdevilStyle.font = font;
        
        winboxStyle = new TextButtonStyle();
        winboxStyle.up = skinGambar.getDrawable("winbox");
        winboxStyle.font = font;
        
        losetimeboxStyle = new TextButtonStyle();
        losetimeboxStyle.up = skinGambar.getDrawable("losetimebox");
        losetimeboxStyle.font = font;
        
        losedeadboxStyle = new TextButtonStyle();
        losedeadboxStyle.up = skinGambar.getDrawable("losedeadbox");
        losedeadboxStyle.font = font;
        
        backStyle = new TextButtonStyle();
        backStyle.up = skinGambar.getDrawable("back");
        backStyle.font = font;
        
        timerStyle = new LabelStyle();
		timerStyle.font = font;
        
		skorStyle = new LabelStyle();
		skorStyle.font = font;
		
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        optionButton = new TextButton("",optionStyle);
        optionButton.setPosition(20*Gdx.graphics.getWidth()/960, 20*Gdx.graphics.getHeight()/540);
        optionButton.setHeight(98*Gdx.graphics.getHeight()/540); 
        optionButton.setWidth(97*Gdx.graphics.getWidth()/960); 
        optionButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new MenuUtama(game));
        		}
            }
        });
        
        goButton = new TextButton("",goStyle);
        goButton.setPosition(350*Gdx.graphics.getWidth()/960, 460*Gdx.graphics.getHeight()/540);
        goButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        goButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        goButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			if(PerahuPosisi.equals("Kanan") && PerahuUnit.size() != 0 && pressableButton == true){
        				RunnableAction completeAction = Actions.run(new Runnable() {
        		            @Override
        		            public void run() {
        		            	pressableButton = true;
        		                priestinLButton.setVisible(true);
        		                devilinLButton.setVisible(true);
        		                priestinRButton.setVisible(false);
        		                devilinRButton.setVisible(false);
                			    cekKalahKiri();
        		            }
        		        });
        				pressableButton = false;
        				shipButton.addAction(Actions.sequence(Actions.moveTo(300*Gdx.graphics.getWidth()/960, 60*Gdx.graphics.getHeight()/540 , 3f) , completeAction));
        		        PerahuPosisi = "Kiri";
        		        cekKalahKanan();
        			}
        			else if(PerahuPosisi.equals("Kiri") && PerahuUnit.size() != 0 && pressableButton == true){
        				RunnableAction completeAction = Actions.run(new Runnable() {
        		            @Override
        		            public void run() {
        		            	pressableButton = true;
        		                priestinRButton.setVisible(true);
        		                devilinRButton.setVisible(true);
        		                priestinLButton.setVisible(false);
        		                devilinLButton.setVisible(false);
                			    cekKalahKanan();
        		            }
        		        });
        				
        				pressableButton = false;
        				shipButton.addAction(Actions.sequence(Actions.moveTo(560*Gdx.graphics.getWidth()/960, 60*Gdx.graphics.getHeight()/540, 3f) , completeAction));
        			    PerahuPosisi = "Kanan";    
        			    cekKalahKiri();
        			}
        		}
            }
        });
        
        priestoutButton = new TextButton("",priestoutStyle);
        priestoutButton.setPosition(350*Gdx.graphics.getWidth()/960, 380*Gdx.graphics.getHeight()/540);
        priestoutButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        priestoutButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        priestoutButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kanan") && pressableButton == true){
	        				if(PerahuUnit.size() > 0){
	        					for(int i=0;i<PerahuUnit.size();i++){
	        						if(PerahuUnit.get(i).getTipe().equals("Priest")){
			        					PerahuUnit.get(i).getButtonName().setVisible(true);
			        					PerahuUnit.get(i).getButtonName().setStyle(priestStyle);
			        					Position pos = PositionPulauKanan.get(PulauKananUnit.size());
			        					PerahuUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
			        					PulauKananUnit.add(PerahuUnit.get(i));
			    	        			PerahuUnit.remove(PerahuUnit.get(i));
			    	        			if(PerahuUnit.size() == 1){
				    	        			if(PerahuUnit.get(0).getTipe().equals("Priest")){
				    	        				shipButton.setStyle(shippriestStyle);
				    	        			}
				    	        			else{
				    	        				shipButton.setStyle(shipdevilStyle);
				    	        			}
			    	        			}
			    	        			else{
			    	        				shipButton.setStyle(shipemptyStyle);
			    	        			}
			    		            	i = PerahuUnit.size();
			        				}
	        					}
	        					aturPosisi();
	        					cekMenang();
		        			}
        				}
        				else{
        					if(PerahuUnit.size() > 0 && pressableButton == true){
	        					for(int i=0;i<PerahuUnit.size();i++){
	        						if(PerahuUnit.get(i).getTipe().equals("Priest")){
			        					PerahuUnit.get(i).getButtonName().setVisible(true);
			        					PerahuUnit.get(i).getButtonName().setStyle(priestflipStyle);
			        					Position pos = PositionPulauKiri.get(PulauKiriUnit.size());
			        					PerahuUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
			        					PulauKiriUnit.add(PerahuUnit.get(i));
			    	        			PerahuUnit.remove(PerahuUnit.get(i));
			    	        			if(PerahuUnit.size() == 1){
				    	        			if(PerahuUnit.get(0).getTipe().equals("Priest")){
				    	        				shipButton.setStyle(shippriestStyle);
				    	        			}
				    	        			else{
				    	        				shipButton.setStyle(shipdevilStyle);
				    	        			}
			    	        			}
			    	        			else{
			    	        				shipButton.setStyle(shipemptyStyle);
			    	        			}
			    		            	i = PerahuUnit.size();
			        				}
	        					}
	        					aturPosisi();
	        					cekMenang();
		        			}
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        deviloutButton = new TextButton("",deviloutStyle);
        deviloutButton.setPosition(350*Gdx.graphics.getWidth()/960, 300*Gdx.graphics.getHeight()/540);
        deviloutButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        deviloutButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        deviloutButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kanan") && pressableButton == true){
	        				if(PerahuUnit.size() > 0){
	        					for(int i=0;i<PerahuUnit.size();i++){
	        						if(PerahuUnit.get(i).getTipe().equals("Devil")){
			        					PerahuUnit.get(i).getButtonName().setVisible(true);
			        					PerahuUnit.get(i).getButtonName().setStyle(devilStyle);
			        					Position pos = PositionPulauKanan.get(PulauKananUnit.size());
			        					PerahuUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
			        					PulauKananUnit.add(PerahuUnit.get(i));
			    	        			PerahuUnit.remove(PerahuUnit.get(i));
			    	        			if(PerahuUnit.size() == 1){
				    	        			if(PerahuUnit.get(0).getTipe().equals("Devil")){
				    	        				shipButton.setStyle(shipdevilStyle);
				    	        			}
				    	        			else{
				    	        				shipButton.setStyle(shippriestStyle);
				    	        			}
			    	        			}
			    	        			else{
			    	        				shipButton.setStyle(shipemptyStyle);
			    	        			}
			    		            	i = PerahuUnit.size();
			        				}
	        					}
	        					aturPosisi();
	        					cekMenang();
		        			}
        				}
        				else{
        					if(PerahuUnit.size() > 0 && pressableButton == true){
	        					for(int i=0;i<PerahuUnit.size();i++){
	        						if(PerahuUnit.get(i).getTipe().equals("Devil")){
			        					PerahuUnit.get(i).getButtonName().setVisible(true);
			        					PerahuUnit.get(i).getButtonName().setStyle(devilflipStyle);
			        					Position pos = PositionPulauKiri.get(PulauKiriUnit.size());
			        					PerahuUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
			        					PulauKiriUnit.add(PerahuUnit.get(i));
			    	        			PerahuUnit.remove(PerahuUnit.get(i));
			    	        			if(PerahuUnit.size() == 1){
				    	        			if(PerahuUnit.get(0).getTipe().equals("Devil")){
				    	        				shipButton.setStyle(shipdevilStyle);
				    	        			}
				    	        			else{
				    	        				shipButton.setStyle(shippriestStyle);
				    	        			}
			    	        			}
			    	        			else{
			    	        				shipButton.setStyle(shipemptyStyle);
			    	        			}
			    		            	i = PerahuUnit.size();
			        				}
	        					}
	        					aturPosisi();
	        					cekMenang();
		        			}
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        priestinRButton = new TextButton("",priestinStyle);
        priestinRButton.setPosition(680*Gdx.graphics.getWidth()/960, 380*Gdx.graphics.getHeight()/540);
        priestinRButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        priestinRButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        priestinRButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kanan") && pressableButton == true){
	        				if(PerahuUnit.size() == 0){
	        					for(int i=0;i<PulauKananUnit.size();i++){
	        						if(PulauKananUnit.get(i).getTipe().equals("Priest")){
		        						PulauKananUnit.get(i).getButtonName().setVisible(false);
	    	        					PerahuUnit.add(PulauKananUnit.get(i));
	    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
	    		            			shipButton.setStyle(shippriestStyle);
	    		        				i = PulauKananUnit.size();
	        						}
	        					}
		        			}
		        			else if(PerahuUnit.size() == 1){
		        				if(PerahuUnit.get(0).getTipe().equals("Priest")){
		        					for(int i=0;i<PulauKananUnit.size();i++){
		        						if(PulauKananUnit.get(i).getTipe().equals("Priest")){
		        							PulauKananUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKananUnit.get(i));
		    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
		    		            			shipButton.setStyle(shiptwopriestStyle);
		    		        				i = PulauKananUnit.size();
		        						}
		        					}
		        				}
		        				else{
		        					for(int i=0;i<PulauKananUnit.size();i++){
		        						if(PulauKananUnit.get(i).getTipe().equals("Priest")){
		        							PulauKananUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKananUnit.get(i));
		    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
		    	        					shipButton.setStyle(shippriestdevilStyle);
		    	        					i = PulauKananUnit.size();
		        						}
		        					}
		        				}
		        			}
        					aturPosisi();
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        devilinRButton = new TextButton("",devilinStyle);
        devilinRButton.setPosition(680*Gdx.graphics.getWidth()/960, 300*Gdx.graphics.getHeight()/540);
        devilinRButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        devilinRButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        devilinRButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kanan") && pressableButton == true){
	        				if(PerahuUnit.size() == 0){
	        					for(int i=0;i<PulauKananUnit.size();i++){
	        						if(PulauKananUnit.get(i).getTipe().equals("Devil")){
	        							PulauKananUnit.get(i).getButtonName().setVisible(false);
	    	        					PerahuUnit.add(PulauKananUnit.get(i));
	    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
	    		            			shipButton.setStyle(shipdevilStyle);
	    		        				i = PulauKananUnit.size();
	        						}
	        					}
		        			}
		        			else if(PerahuUnit.size() == 1){
		        				if(PerahuUnit.get(0).getTipe().equals("Devil")){
		        					for(int i=0;i<PulauKananUnit.size();i++){
		        						if(PulauKananUnit.get(i).getTipe().equals("Devil")){
		        							PulauKananUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKananUnit.get(i));
		    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
		    		            			shipButton.setStyle(shiptwodevilStyle);
		    		        				i = PulauKananUnit.size();
		        						}
		        					}
		        				}
		        				else{
		        					for(int i=0;i<PulauKananUnit.size();i++){
		        						if(PulauKananUnit.get(i).getTipe().equals("Devil")){
		        							PulauKananUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKananUnit.get(i));
		    	        					PulauKananUnit.remove(PulauKananUnit.get(i));
		    	        					shipButton.setStyle(shippriestdevilStyle);
		    	        					i = PulauKananUnit.size();
		        						}
		        					}
		        				}
		        			}
        					aturPosisi();
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        timerText = new Label("Timer : "+waktu,timerStyle);
        timerText.setPosition(680f*Gdx.graphics.getWidth()/960, 480f*Gdx.graphics.getHeight()/540);
        timerText.setWidth(275f*Gdx.graphics.getWidth()/960);
        timerText.setHeight(42f*Gdx.graphics.getHeight()/540);
        timerText.setAlignment(Align.center);
        
        priestinLButton = new TextButton("",priestinStyle);
        priestinLButton.setPosition(30*Gdx.graphics.getWidth()/960, 380*Gdx.graphics.getHeight()/540);
        priestinLButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        priestinLButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        priestinLButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kiri") && pressableButton == true){
	        				if(PerahuUnit.size() == 0){
	        					for(int i=0;i<PulauKiriUnit.size();i++){
	        						if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
		        						PulauKiriUnit.get(i).getButtonName().setVisible(false);
	    	        					PerahuUnit.add(PulauKiriUnit.get(i));
	    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
	    		            			shipButton.setStyle(shippriestStyle);
	    		        				i = PulauKiriUnit.size();
	        						}
	        					}
		        			}
		        			else if(PerahuUnit.size() == 1){
		        				if(PerahuUnit.get(0).getTipe().equals("Priest")){
		        					for(int i=0;i<PulauKiriUnit.size();i++){
		        						if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
		        							PulauKiriUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKiriUnit.get(i));
		    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
		    		            			shipButton.setStyle(shiptwopriestStyle);
		    		        				i = PulauKiriUnit.size();
		        						}
		        					}
		        				}
		        				else{
		        					for(int i=0;i<PulauKiriUnit.size();i++){
		        						if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
		        							PulauKiriUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKiriUnit.get(i));
		    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
		    	        					shipButton.setStyle(shippriestdevilStyle);
		    	        					i = PulauKiriUnit.size();
		        						}
		        					}
		        				}
		        			}
        					aturPosisi();
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        priestinLButton.setVisible(false);
        
        devilinLButton = new TextButton("",devilinStyle);
        devilinLButton.setPosition(30*Gdx.graphics.getWidth()/960, 300*Gdx.graphics.getHeight()/540);
        devilinLButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        devilinLButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        devilinLButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new MenuUtama(game));
        			try{
        				if(PerahuPosisi.equals("Kiri") && pressableButton == true){
	        				if(PerahuUnit.size() == 0){
	        					for(int i=0;i<PulauKiriUnit.size();i++){
	        						if(PulauKiriUnit.get(i).getTipe().equals("Devil")){
	        							PulauKiriUnit.get(i).getButtonName().setVisible(false);
	    	        					PerahuUnit.add(PulauKiriUnit.get(i));
	    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
	    		            			shipButton.setStyle(shipdevilStyle);
	    		        				i = PulauKananUnit.size();
	        						}
	        					}
		        			}
		        			else if(PerahuUnit.size() == 1){
		        				if(PerahuUnit.get(0).getTipe().equals("Devil")){
		        					for(int i=0;i<PulauKiriUnit.size();i++){
		        						if(PulauKiriUnit.get(i).getTipe().equals("Devil")){
		        							PulauKiriUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKiriUnit.get(i));
		    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
		    		            			shipButton.setStyle(shiptwodevilStyle);
		    		        				i = PulauKiriUnit.size();
		        						}
		        					}
		        				}
		        				else{
		        					for(int i=0;i<PulauKiriUnit.size();i++){
		        						if(PulauKiriUnit.get(i).getTipe().equals("Devil")){
		        							PulauKiriUnit.get(i).getButtonName().setVisible(false);
		    	        					PerahuUnit.add(PulauKiriUnit.get(i));
		    	        					PulauKiriUnit.remove(PulauKiriUnit.get(i));
		    	        					shipButton.setStyle(shippriestdevilStyle);
		    	        					i = PulauKiriUnit.size();
		        						}
		        					}
		        				}
		        			}
        					aturPosisi();
        				}
        			}
        			catch(java.lang.NullPointerException npe){
        				
        			}
        		}
            }
        });
        
        devilinLButton.setVisible(false);
        
        skorText = new Label("Skor : "+skor,timerStyle);
        skorText.setPosition(30f*Gdx.graphics.getWidth()/960, 480f*Gdx.graphics.getHeight()/540);
        skorText.setWidth(275f*Gdx.graphics.getWidth()/960);
        skorText.setHeight(42f*Gdx.graphics.getHeight()/540);
        skorText.setAlignment(Align.center);
        
        priest1Button = new TextButton("",priestStyle);
        priest1Button.setPosition(730*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        priest1Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        priest1Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        priest2Button = new TextButton("",priestStyle);
        priest2Button.setPosition(760*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        priest2Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        priest2Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        priest3Button = new TextButton("",priestStyle);
        priest3Button.setPosition(790*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        priest3Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        priest3Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        devil1Button = new TextButton("",devilStyle);
        devil1Button.setPosition(820*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        devil1Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        devil1Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        devil2Button = new TextButton("",devilStyle);
        devil2Button.setPosition(850*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        devil2Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        devil2Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        devil3Button = new TextButton("",devilStyle);
        devil3Button.setPosition(880*Gdx.graphics.getWidth()/960, 130*Gdx.graphics.getHeight()/540);
        devil3Button.setHeight(79*Gdx.graphics.getHeight()/540); 
        devil3Button.setWidth(35*Gdx.graphics.getWidth()/960); 
        
        shipButton = new TextButton("",shipemptyStyle);
        shipButton.setPosition(560*Gdx.graphics.getWidth()/960, 60*Gdx.graphics.getHeight()/540);
        shipButton.setHeight(52*Gdx.graphics.getHeight()/540); 
        shipButton.setWidth(119*Gdx.graphics.getWidth()/960); 
        
        boxButton = new TextButton("",winboxStyle);
        boxButton.setPosition(260*Gdx.graphics.getWidth()/960, 120*Gdx.graphics.getHeight()/540);
        boxButton.setHeight(317*Gdx.graphics.getHeight()/540); 
        boxButton.setWidth(440*Gdx.graphics.getWidth()/960); 
        boxButton.setVisible(false);
        
        backButton = new TextButton("",backStyle);
        backButton.setPosition(360*Gdx.graphics.getWidth()/960, 30*Gdx.graphics.getHeight()/540);
        backButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        backButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        backButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new MenuUtama(game));
        		}
            }
        });
        backButton.setVisible(false);
        
        //Menambahkan Unit
        tambahUnit();
        
        setPosisiKanan();
        setPosisiKiri();
        
        stage.addActor(backgroundButton);
        stage.addActor(optionButton);
        stage.addActor(goButton);
        stage.addActor(priestoutButton);
        stage.addActor(deviloutButton);
        stage.addActor(timerText);
        stage.addActor(priestinRButton);
        stage.addActor(devilinRButton);
        stage.addActor(skorText);
        stage.addActor(priestinLButton);
        stage.addActor(devilinLButton);
        stage.addActor(priest1Button);
        stage.addActor(priest2Button);
        stage.addActor(priest3Button);
        stage.addActor(devil1Button);
        stage.addActor(devil2Button);
        stage.addActor(devil3Button);
        stage.addActor(shipButton);
        stage.addActor(boxButton);
        stage.addActor(backButton);
        
        clickSound = game.assets.get("snd/click.mp3");
        devilSound = game.assets.get("snd/devillaugh.mp3");
        winSound = game.assets.get("snd/win.mp3");
        soundVolume = game.soundFxVolume;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		font.dispose();
		gambar.dispose();
		skinGambar.dispose();
		stage.dispose();
		clickSound.dispose();
		game.bgmMusic.dispose();
		game.bgmMusic.dispose();
		game.assets.dispose();
	}
	
	public void tambahUnit(){
		Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Priest1");
        unit.setTipe("Priest");
        unit.setLokasi("Kanan");
        unit.setButtonName(priest1Button);
        
        PulauKananUnit.add(unit);
        
		unit = new Unit();
        unit.setId(2);
        unit.setName("Priest2");
        unit.setTipe("Priest");
        unit.setLokasi("Kanan");
        unit.setButtonName(priest2Button);
        
        PulauKananUnit.add(unit);
        
        unit = new Unit();
        unit.setId(3);
        unit.setName("Priest3");
        unit.setTipe("Priest");
        unit.setLokasi("Kanan");
        unit.setButtonName(priest3Button);
        
        PulauKananUnit.add(unit);
        
        unit = new Unit();
        unit.setId(4);
        unit.setName("Devil1");
        unit.setTipe("Devil");
        unit.setLokasi("Kanan");
        unit.setButtonName(devil1Button);
        
        PulauKananUnit.add(unit);
        
        unit = new Unit();
        unit.setId(5);
        unit.setName("Devil2");
        unit.setTipe("Devil");
        unit.setLokasi("Kanan");
        unit.setButtonName(devil2Button);
        
        PulauKananUnit.add(unit);
        
        unit = new Unit();
        unit.setId(6);
        unit.setName("Devil3");
        unit.setTipe("Devil");
        unit.setLokasi("Kanan");
        unit.setButtonName(devil3Button);
        
        PulauKananUnit.add(unit);
        
	}
	
	public void setPosisiKanan(){
        Position pos = new Position();
        pos.setX(730*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);

        pos = new Position();
        pos.setX(760*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);

        pos = new Position();
        pos.setX(790*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);

        pos = new Position();
        pos.setX(820*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);

        pos = new Position();
        pos.setX(850*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);
        
        pos = new Position();
        pos.setX(880*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKanan.add(pos);

                        
	}
	
	public void setPosisiKiri(){
        Position pos = new Position();
        pos.setX(200*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);

        pos = new Position();
        pos.setX(170*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);

        pos = new Position();
        pos.setX(140*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);

        pos = new Position();
        pos.setX(110*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);

        pos = new Position();
        pos.setX(90*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);
        
        pos = new Position();
        pos.setX(60*Gdx.graphics.getWidth()/960);
        pos.setY(130*Gdx.graphics.getHeight()/540);
        
        PositionPulauKiri.add(pos);

                        
	}
	
	public void aturPosisi(){
		for(int i=0;i<PulauKananUnit.size();i++){
			Position pos = PositionPulauKanan.get(i);
			PulauKananUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
		}
		
		for(int i=0;i<PulauKiriUnit.size();i++){
			Position pos = PositionPulauKiri.get(i);
			PulauKiriUnit.get(i).getButtonName().setPosition(pos.getX(), pos.getY());
		}
	}
	
	public void cekMenang(){
		if(PulauKiriUnit.size() == 6){
			pressableTime = false;
			boxButton.setStyle(winboxStyle);
			boxButton.setVisible(true);
	        backButton.setVisible(true);
	        pressableButton = false;
	        long idClick = winSound.play();
	        winSound.setVolume(idClick, soundVolume);
	        
	        DatabaseEvil db = new DatabaseEvil();
	        game.skor.setIdplayer(db.getIdByUsername(game.pengguna));
	        game.skor.setSkor(skor);
	        db.tambahSkor(game.skor);
	        db.closeDB();
		}
	}
	public void cekKalahKanan(){
		int banyakPendeta=0;
		int banyakIblis=0;
		
		for(int i=0;i<PulauKananUnit.size();i++){
			if(PulauKananUnit.get(i).getTipe().equals("Priest")){
				banyakPendeta++;
			}
			else{
				banyakIblis++;
			}
		}
		
		if(PerahuPosisi.equals("Kanan")){
			for(int i=0;i<PerahuUnit.size();i++){
				if(PerahuUnit.get(i).getTipe().equals("Priest")){
					banyakPendeta++;
				}
				else{
					banyakIblis++;
				}
			}
		}
		
		if(banyakPendeta < banyakIblis && banyakPendeta != 0){
			pressableTime = false;
			boxButton.setStyle(losedeadboxStyle);
			boxButton.setVisible(true);
	        backButton.setVisible(true);
	        pressableButton = false;
	        
	        System.out.print("Mati Kanan");
	        for(int i=0;i<PulauKananUnit.size();i++){
				if(PulauKananUnit.get(i).getTipe().equals("Priest")){
					PulauKananUnit.get(i).getButtonName().addAction(Actions.sequence(Actions.moveTo(PulauKananUnit.get(i).getButtonName().getX(),540*Gdx.graphics.getHeight()/540,2f)));
					PulauKananUnit.get(i).getButtonName().addAction(Actions.sequence(Actions.fadeOut(1f) , Actions.removeActor()));
				}
			}
	        /*if(PerahuPosisi.equals("Kanan")){
				for(int i=0;i<PerahuUnit.size();i++){
					if(PulauKananUnit.get(i).getTipe().equals("Priest")){
						PerahuUnit.get(i).getButtonName().addAction(Actions.sequence( Actions.moveTo(PerahuUnit.get(i).getButtonName().getX(),540*Gdx.graphics.getHeight()/540,2f)));
						PerahuUnit.get(i).getButtonName().addAction(Actions.sequence( Actions.fadeOut(1f) , Actions.removeActor()));
					}
				}
	        }*/
	        long idClick = devilSound.play();
	        devilSound.setVolume(idClick, soundVolume);
		}
	}
	public void cekKalahKiri(){
		int banyakPendeta=0;
		int banyakIblis=0;
		
		for(int i=0;i<PulauKiriUnit.size();i++){
			if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
				banyakPendeta++;
			}
			else{
				banyakIblis++;
			}
		}
		
		if(PerahuPosisi.equals("Kiri")){
			for(int i=0;i<PerahuUnit.size();i++){
				if(PerahuUnit.get(i).getTipe().equals("Priest")){
					banyakPendeta++;
				}
				else{
					banyakIblis++;
				}
			}
		}
		
		if(banyakPendeta < banyakIblis && banyakPendeta != 0){
			pressableTime = false;
			boxButton.setStyle(losedeadboxStyle);
			boxButton.setVisible(true);
	        backButton.setVisible(true);
	        pressableButton = false;

	        System.out.print("Mati Kiri");
	        for(int i=0;i<PulauKiriUnit.size();i++){
				if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
					PulauKiriUnit.get(i).getButtonName().addAction(Actions.sequence(Actions.moveTo(PulauKiriUnit.get(i).getButtonName().getX(),540*Gdx.graphics.getHeight()/540,2f)));
					PulauKiriUnit.get(i).getButtonName().addAction(Actions.sequence(Actions.fadeOut(1f) , Actions.removeActor()));
				}
			}
	        /*if(PerahuPosisi.equals("Kiri")){
				for(int i=0;i<PerahuUnit.size();i++){
					if(PulauKiriUnit.get(i).getTipe().equals("Priest")){
						PerahuUnit.get(i).getButtonName().addAction(Actions.sequence( Actions.moveTo(PerahuUnit.get(i).getButtonName().getX(),540*Gdx.graphics.getHeight()/540,2f)));
						PerahuUnit.get(i).getButtonName().addAction(Actions.sequence( Actions.fadeOut(1f) , Actions.removeActor()));
					}
				}
	        }*/
	        long idClick = devilSound.play();
	        devilSound.setVolume(idClick, soundVolume);
		}
	}
}
