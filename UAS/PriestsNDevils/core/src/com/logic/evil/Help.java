package com.logic.evil;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.logic.evil.MenuUtama;
import com.logic.evil.EvilMain;

public class Help implements Screen{
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
	TextButtonStyle boxStyle;
	TextButton backgroundButton;
	TextButton backButton;
	TextButton boxButton;
	Sound clickSound;
	float soundVolume;
	EvilMain game;
	
	Help(EvilMain Permainan){
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
		
		gambar = game.assets.get("img/help/help.pack"); 
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
        
        boxStyle = new TextButtonStyle();
        boxStyle.up = skinGambar.getDrawable("box");
        boxStyle.font = font;
        
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        backButton = new TextButton("",backStyle);
        backButton.setPosition(380*Gdx.graphics.getWidth()/960, 20*Gdx.graphics.getHeight()/540);
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
        
        boxButton = new TextButton("",boxStyle);
        boxButton.setPosition(180*Gdx.graphics.getWidth()/960, 110*Gdx.graphics.getHeight()/540); 
        boxButton.setHeight(282*Gdx.graphics.getHeight()/540); 
        boxButton.setWidth(654*Gdx.graphics.getWidth()/960); 
        
        stage.addActor(backgroundButton);
        stage.addActor(boxButton);
        stage.addActor(backButton);
        
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
