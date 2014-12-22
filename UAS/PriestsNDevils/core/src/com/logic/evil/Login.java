package com.logic.evil;

import java.util.Calendar;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.logic.evil.MenuUtama;
import com.logic.evil.EvilMain;
import com.logic.evil.component.HistoryLogin;
import com.logic.evil.database.DatabaseEvil;

public class Login implements Screen{
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	Stage stage;
	CameraInputController camController;
	TextureAtlas gambar;
	Skin skinGambar;
	BitmapFont font;
	TextButtonStyle backgroundStyle;
	TextButtonStyle backStyle;
	TextButtonStyle okStyle;
	TextButtonStyle boxStyle;
	TextButtonStyle inputStyle;
	TextButton backgroundButton;
	TextButton backButton;
	TextButton okButton;
	TextButton boxButton;
	TextButton usernameButton;
	TextButton passwordButton;
	TextFieldStyle usernameTextStyle;
	TextFieldStyle passwordTextStyle;
	TextFieldStyle keteranganTextStyle;
	TextField usernameText;
	TextField passwordText;
	TextField keteranganText;
	Sound clickSound;
	float soundVolume;
	EvilMain game;
	
	Login(EvilMain Permainan){
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
		
		gambar = game.assets.get("img/login/login.pack"); 
        skinGambar = new Skin();
        skinGambar.addRegions(gambar);
        
        font = game.assets.get("fnt/postcryp.fnt");
        font.setColor(Color.YELLOW);
        font.setScale((float) 1*Gdx.graphics.getWidth()/960, (float) 1*Gdx.graphics.getHeight()/540);
        
        backgroundStyle = new TextButtonStyle();
        backgroundStyle.up = skinGambar.getDrawable("background");
        backgroundStyle.font = font;
        
        backStyle = new TextButtonStyle();
        backStyle.up = skinGambar.getDrawable("back");
        backStyle.font = font;
        
        okStyle = new TextButtonStyle();
        okStyle.up = skinGambar.getDrawable("ok");
        okStyle.font = font;
        
        okStyle = new TextButtonStyle();
        okStyle.up = skinGambar.getDrawable("ok");
        okStyle.font = font;
        
        inputStyle = new TextButtonStyle();
        inputStyle.up = skinGambar.getDrawable("input");
        inputStyle.font = font;
        
        boxStyle = new TextButtonStyle();
        boxStyle.up = skinGambar.getDrawable("boxlogin");
        boxStyle.font = font;
        
        usernameTextStyle = new TextFieldStyle();
        usernameTextStyle.font = font;
        usernameTextStyle.fontColor = Color.WHITE;
        //usernameTextStyle.cursor = skinGambar.getDrawable("cursor");
        
        passwordTextStyle = new TextFieldStyle();
        passwordTextStyle.font = font;
        passwordTextStyle.fontColor = Color.WHITE;
        
        keteranganTextStyle = new TextFieldStyle();
        keteranganTextStyle.font = font;
        keteranganTextStyle.fontColor = Color.WHITE;
        
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        backButton = new TextButton("",backStyle);
        backButton.setPosition(30*Gdx.graphics.getWidth()/960, 230*Gdx.graphics.getHeight()/540);
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
        
        okButton = new TextButton("",okStyle);
        okButton.setPosition(30*Gdx.graphics.getWidth()/960, 310*Gdx.graphics.getHeight()/540);
        okButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        okButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        okButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			DatabaseEvil db = new DatabaseEvil();
        	        boolean hasil = db.cekLogin(game.pengguna);
        	        if(hasil){
        	        	HistoryLogin hl = new HistoryLogin();
        	        	hl.setIdPlayer(db.getIdByUsername(game.pengguna));
        	        	hl.setTanggal(Calendar.getInstance().getTime());
        	        	db.tambahHistoryLogin(hl);
        	        	game.setScreen(new GamePlay(game));
            	        db.closeDB();
        	        }
        	        else{
        	        	keteranganText.setText("login failure");
        	        	float fontX = (360*Gdx.graphics.getWidth()/960) + (passwordText.getWidth()/2) - ((font.getBounds(passwordText.getText()).width)/2);
        	    	    float fontY = 80*Gdx.graphics.getHeight()/540;
        	    	    keteranganText.setPosition(fontX, fontY);
            	        db.closeDB();
        	        }
        		}
            }
        });
        
        usernameButton = new TextButton("",inputStyle);
        usernameButton.setPosition(560*Gdx.graphics.getWidth()/960, 250*Gdx.graphics.getHeight()/540);
        usernameButton.setHeight(94*Gdx.graphics.getHeight()/540); 
        usernameButton.setWidth(319*Gdx.graphics.getWidth()/960); 
        usernameButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		game.sendText.setType("username");
        		game.setScreen(new Input(game));
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new Input(game));
        		}
            }
        });
        
        passwordButton = new TextButton("",inputStyle);
        passwordButton.setPosition(560*Gdx.graphics.getWidth()/960, 150*Gdx.graphics.getHeight()/540);
        passwordButton.setHeight(94*Gdx.graphics.getHeight()/540); 
        passwordButton.setWidth(319*Gdx.graphics.getWidth()/960); 
        passwordButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    	        game.sendText.setType("password");
        		game.setScreen(new Input(game));
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			//game.setScreen(new Input(game));
        		}
            }
        });
        
        boxButton = new TextButton("",boxStyle);
        boxButton.setPosition(290*Gdx.graphics.getWidth()/960, 90*Gdx.graphics.getHeight()/540); 
        boxButton.setHeight(282*Gdx.graphics.getHeight()/540); 
        boxButton.setWidth(654*Gdx.graphics.getWidth()/960); 
        
        usernameText = new TextField("",usernameTextStyle);
        float fontX = (560*Gdx.graphics.getWidth()/960) + (usernameText.getWidth()/2) - ((font.getBounds(usernameText.getText()).width)/2);
	    float fontY = 250*Gdx.graphics.getHeight()/540;
	    usernameText.setPosition(fontX, fontY);
        usernameText.setHeight(94*Gdx.graphics.getHeight()/540); 
        usernameText.setWidth(319*Gdx.graphics.getWidth()/960); 
        usernameText.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    	        game.sendText.setType("username");
        		game.setScreen(new Input(game));
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			
        		}
            }
        });
        
        passwordText = new TextField("",passwordTextStyle);
        fontX = (560*Gdx.graphics.getWidth()/960) + (passwordText.getWidth()/2) - ((font.getBounds(passwordText.getText()).width)/2);
	    fontY = 150*Gdx.graphics.getHeight()/540;
	    passwordText.setPosition(fontX, fontY);
	    passwordText.setHeight(94*Gdx.graphics.getHeight()/540); 
	    passwordText.setWidth(319*Gdx.graphics.getWidth()/960); 
	    passwordText.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    	        game.sendText.setType("password");
        		game.setScreen(new Input(game));
                return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			
        		}
            }
        });
	    
	    keteranganText = new TextField("",keteranganTextStyle);
        fontX = (360*Gdx.graphics.getWidth()/960) + (passwordText.getWidth()/2) - ((font.getBounds(passwordText.getText()).width)/2);
	    fontY = 80*Gdx.graphics.getHeight()/540;
	    keteranganText.setPosition(fontX, fontY);
	    keteranganText.setHeight(94*Gdx.graphics.getHeight()/540); 
	    keteranganText.setWidth(319*Gdx.graphics.getWidth()/960); 
	    keteranganText.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    	        return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			
        		}
            }
        });
        
        if(game.sendText.getType() != null && game.sendText.getValue() != null){
        	if(game.sendText.getType().equals("username")){
        		usernameText.setText(game.sendText.getValue());
        	    game.pengguna.setUsername(game.sendText.getValue());
        		fontX = (560*Gdx.graphics.getWidth()/960) + (usernameText.getWidth()/2) - ((font.getBounds(usernameText.getText()).width)/2);
        	    fontY = 250*Gdx.graphics.getHeight()/540;
        	    usernameText.setPosition(fontX, fontY);
        	}
        	else if(game.sendText.getType().equals("password")){
        		passwordText.setText(game.sendText.getValue());
        	    game.pengguna.setPassword(game.sendText.getValue());
        		fontX = (560*Gdx.graphics.getWidth()/960) + (passwordText.getWidth()/2) - ((font.getBounds(passwordText.getText()).width)/2);
        	    fontY = 150*Gdx.graphics.getHeight()/540;
        	    passwordText.setPosition(fontX, fontY);
        	}
        }
        if(game.pengguna.getUsername() != null){
        	usernameText.setText(game.pengguna.getUsername());
    		fontX = (560*Gdx.graphics.getWidth()/960) + (usernameText.getWidth()/2) - ((font.getBounds(usernameText.getText()).width)/2);
    	    fontY = 250*Gdx.graphics.getHeight()/540;
    	    usernameText.setPosition(fontX, fontY);
        }
        if(game.pengguna.getPassword() != null){
        	passwordText.setText(game.pengguna.getPassword());
    		fontX = (560*Gdx.graphics.getWidth()/960) + (passwordText.getWidth()/2) - ((font.getBounds(passwordText.getText()).width)/2);
    	    fontY = 150*Gdx.graphics.getHeight()/540;
    	    passwordText.setPosition(fontX, fontY);
        }
        
        stage.addActor(backgroundButton);
        stage.addActor(boxButton);
        stage.addActor(backButton);
        stage.addActor(okButton);
        stage.addActor(usernameButton);
        stage.addActor(passwordButton);
        stage.addActor(usernameText);
        stage.addActor(passwordText);
        stage.addActor(keteranganText);
        
        clickSound = game.assets.get("snd/click.mp3");
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
}
