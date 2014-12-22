package com.logic.evil;

import java.util.Calendar;
import java.util.Date;

import com.badlogic.gdx.InputMultiplexer;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.logic.evil.component.Player;
import com.logic.evil.database.DatabaseEvil;

public class MenuUtama implements Screen {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	Stage stage;
	CameraInputController camController;
	TextureAtlas gambar;
	Skin skinGambar;
	BitmapFont font;
	TextButtonStyle loginStyle;
	TextButtonStyle registerStyle;
	TextButtonStyle scoreStyle;
	TextButtonStyle helpStyle;
	TextButtonStyle aboutStyle;
	TextButtonStyle quitStyle;
	TextButtonStyle backgroundStyle;
	TextButton loginButton;
	TextButton registerButton;
	TextButton scoreButton;
	TextButton helpButton;
	TextButton aboutButton;
	TextButton quitButton;
	TextButton backgroundButton;
	Sound clickSound;
	float volumeSound;
    ModelBatch modelBatch;
	Array<ModelInstance> instances = new Array<ModelInstance>();
	InputMultiplexer inputMultiplexer;
	AssetManager assets;
	Model model;
	ModelInstance agumon;
	AnimationController animAgumon;
	Environment environment;
	EvilMain game;
	boolean loading;
	boolean agumonloading;
	
	MenuUtama(EvilMain Permainan){
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
		
		modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
        
        camController.update();
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
        
        camController = new CameraInputController(cam);
        
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(camController);
        Gdx.input.setInputProcessor(inputMultiplexer); 
        
        modelBatch = new ModelBatch();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
		gambar = game.assets.get("img/menuutama/menuutama.pack"); 
        skinGambar = new Skin();
        skinGambar.addRegions(gambar);
        
        font = game.assets.get("fnt/postcryp.fnt");
        font.setColor(Color.YELLOW);
        
        backgroundStyle = new TextButtonStyle();
        backgroundStyle.up = skinGambar.getDrawable("background");
        backgroundStyle.font = font;
        
        loginStyle = new TextButtonStyle();
        loginStyle.up = skinGambar.getDrawable("login");
        loginStyle.font = font;
        
        registerStyle = new TextButtonStyle();
        registerStyle.up = skinGambar.getDrawable("register");
        registerStyle.font = font;
        
        scoreStyle = new TextButtonStyle();
        scoreStyle.up = skinGambar.getDrawable("score");
        scoreStyle.font = font;
        
        helpStyle = new TextButtonStyle();
        helpStyle.up = skinGambar.getDrawable("help");
        helpStyle.font = font;
        
        aboutStyle = new TextButtonStyle();
        aboutStyle.up = skinGambar.getDrawable("about");
        aboutStyle.font = font;
        
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        loginButton = new TextButton("",loginStyle);
        loginButton.setPosition(30*Gdx.graphics.getWidth()/960, 330*Gdx.graphics.getHeight()/540); 
        loginButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        loginButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        loginButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, volumeSound);
        		return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new Login(game));
        		}
            }
        });
        
        registerButton = new TextButton("",registerStyle);
        registerButton.setPosition(30*Gdx.graphics.getWidth()/960, 250*Gdx.graphics.getHeight()/540); 
        registerButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        registerButton.setWidth(241*Gdx.graphics.getWidth()/960);
        registerButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, volumeSound);
        		return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new Register(game));
        		}
            }
        });
        
        scoreButton = new TextButton("",scoreStyle);
        scoreButton.setPosition(30*Gdx.graphics.getWidth()/960, 170*Gdx.graphics.getHeight()/540); 
        scoreButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        scoreButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        scoreButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, volumeSound);
        		return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new Score(game));
        		}
            }
        });
        
        helpButton = new TextButton("",helpStyle);
        helpButton.setPosition(680*Gdx.graphics.getWidth()/960, 330*Gdx.graphics.getHeight()/540); 
        helpButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        helpButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        helpButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, volumeSound);
        		return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new Help(game));
        		}
            }
        });
        
        aboutButton = new TextButton("",aboutStyle);
        aboutButton.setPosition(680*Gdx.graphics.getWidth()/960, 250*Gdx.graphics.getHeight()/540); 
        aboutButton.setHeight(64*Gdx.graphics.getHeight()/540); 
        aboutButton.setWidth(241*Gdx.graphics.getWidth()/960); 
        aboutButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		final long idClick = clickSound.play();
        		clickSound.setVolume(idClick, volumeSound);
        		return true;
        	};
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(x >= (event.getTarget()).getX() && x < (event.getTarget()).getX()+(event.getTarget()).getWidth() && y >= (event.getTarget()).getY() && y <= (event.getTarget()).getY()+(event.getTarget()).getHeight()){
        			game.setScreen(new About(game));
        		}
            }
        });
        
        stage.addActor(backgroundButton);
        stage.addActor(loginButton);
        stage.addActor(registerButton);
        stage.addActor(scoreButton);
        stage.addActor(helpButton);
        stage.addActor(aboutButton);
        
        loading = true;
        
        /*DatabaseEvil db = new DatabaseEvil();
        Player player = new Player();
        player.setId(1);
        player.setUsername("fajar");
        player.setPassword("123");
        player.setTanggal(Calendar.getInstance().getTime());
        
        db.tambahPlayer(player);
        db.tampilPlayer();
        db.closeDB();
        */
        
        clickSound = game.assets.get("snd/click.mp3");
        volumeSound = game.soundFxVolume;
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
        instances.clear();
        modelBatch.dispose();
        assets.dispose();
        model.dispose();
        clickSound.dispose();
		game.bgmMusic.dispose();
		game.assets.dispose();
	}

}
