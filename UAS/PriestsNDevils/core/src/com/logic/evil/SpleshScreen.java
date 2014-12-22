package com.logic.evil;

import java.io.File;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class SpleshScreen  implements Screen{
	public interface FileHandleResolever{
		public FileHandle resolve(String file);
	}
	SpriteBatch batch;
	Texture img;
	Texture emptyT;
	Texture fullT;
	NinePatch empty;
	NinePatch full;
	OrthographicCamera cam;
	Stage stage;
	CameraInputController camController;
	TextureAtlas gambar;
	Skin skinGambar;
	BitmapFont font;
	TextButtonStyle backgroundStyle;
	TextButton backgroundButton;
	Music clickSound;
	float soundVolume;
	EvilMain game;
    Animation walkAnimation;  
    Texture walkSheet;      
    TextureRegion[] walkFrames;  
    SpriteBatch spriteBatch;     
    TextureRegion currentFrame;
    boolean animStatus;
    boolean load;

    float stateTime;
	
	SpleshScreen(EvilMain Permainan){
		game = Permainan;
	}
	
	public void doneLoading(){
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		if(game.assets.update() && load){
			doneLoading();
		}

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		stateTime += Gdx.graphics.getDeltaTime();           // #15
		if(animStatus){
			currentFrame = walkAnimation.getKeyFrame(stateTime, false);  // #16
		}
		else{
			currentFrame = walkAnimation.getKeyFrame(stateTime, false);  // #16
			if(game.assets.getProgress() == 1){
            	game.setScreen(new MenuUtama(game));
            	game.bgmMusic.play();
			}
		}
		
		batch.begin();
		batch.draw(currentFrame,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		empty.draw(batch, 140*Gdx.graphics.getWidth()/960, 70*Gdx.graphics.getHeight()/540, 720*Gdx.graphics.getWidth()/960, 30*Gdx.graphics.getHeight()/540);
		full.draw(batch, 140*Gdx.graphics.getWidth()/960, 70*Gdx.graphics.getHeight()/540, game.assets.getProgress()*720*Gdx.graphics.getWidth()/960, 30*Gdx.graphics.getHeight()/540);
		font.drawMultiLine(batch,(int)(game.assets.getProgress()*100)+"% loaded",500*Gdx.graphics.getWidth()/960,94*Gdx.graphics.getHeight()/540,0, BitmapFont.HAlignment.CENTER);
		batch.end();
		
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
		
		loadAssets();
		
		font = new BitmapFont(Gdx.files.internal("fnt/postcryp.fnt"),Gdx.files.internal("fnt/postcryp.png"),false);
        font.setColor(Color.BLACK);
        font.setScale((float) 0.5*Gdx.graphics.getWidth()/960, (float) 0.5*Gdx.graphics.getHeight()/540);
		
        gambar = new TextureAtlas("img/spleshscreen/spleshscreen.pack"); 
        skinGambar = new Skin();
        skinGambar.addRegions(gambar);
        
		backgroundStyle = new TextButtonStyle();
        backgroundStyle.up = skinGambar.getDrawable("background");
        backgroundStyle.font = font;
        
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        stage.addActor(backgroundButton);
		
		emptyT=new Texture("img/spleshscreen/empty.png");
		fullT=new Texture("img/spleshscreen/full.png");
		empty=new NinePatch(new TextureRegion(emptyT,24,24),8,8,8,8);
		full=new NinePatch(new TextureRegion(fullT,24,24),8,8,8,8);
		
		float delay = (float)(15.0/4); // seconds
        
		Timer.schedule(new Task(){
            @Override
            public void run() {
            	animStatus = false;
            }
        }, delay);
		
        walkFrames = new TextureRegion[34];
        int index = 0;
        for (int i = 0; i < 34; i++) {
        	walkSheet = new Texture(Gdx.files.internal("img/spleshscreen/logo/logo"+(i+1)+".png")); 
            walkFrames[index++] = new TextureRegion(walkSheet);
        }
        walkAnimation = new Animation(0.15f, walkFrames);
        stateTime = 0f; 
        animStatus = true;
        
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("snd/splash.mp3"));
        soundVolume = game.soundBgmVolume;
        clickSound.play();
        clickSound.setVolume(3*soundVolume);
    }
	
	public void loadAssets(){
		game.assets.load("fnt/postcryp.fnt", BitmapFont.class);
		game.assets.load("fnt/postcrypsmall.fnt", BitmapFont.class);
        game.assets.load("img/spleshscreen/empty.png", Texture.class);
		game.assets.load("img/spleshscreen/full.png", Texture.class);
		game.assets.load("img/spleshscreen/spleshscreen.pack", TextureAtlas.class);
		game.assets.load("img/menuutama/menuutama.pack", TextureAtlas.class);
		game.assets.load("img/login/login.pack", TextureAtlas.class);
		game.assets.load("img/register/register.pack", TextureAtlas.class);
		game.assets.load("img/about/about.pack", TextureAtlas.class);
		game.assets.load("img/score/score.pack", TextureAtlas.class);
		game.assets.load("img/help/help.pack", TextureAtlas.class);
		game.assets.load("img/gameplay/gameplay.pack", TextureAtlas.class);
		game.assets.load("img/input/input.pack", TextureAtlas.class);
        game.assets.load("snd/click.mp3", Sound.class);
        game.assets.load("snd/keyboard.mp3", Sound.class);
        game.assets.load("snd/devillaugh.mp3", Sound.class);
        game.assets.load("snd/win.mp3", Sound.class);
        game.assets.load("snd/splash.mp3",Music.class);
        game.assets.load("snd/bgm.mp3", Music.class);
        game.assets.load("snd/forest.mp3", Music.class);
        load = true;
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
		img.dispose();
		font.dispose();
		gambar.dispose();
		skinGambar.dispose();
		stage.dispose();
		clickSound.dispose();
		game.bgmMusic.dispose();
		game.assets.dispose();
		walkSheet.dispose();
	}

}
