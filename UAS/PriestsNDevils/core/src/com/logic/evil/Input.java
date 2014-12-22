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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.logic.evil.MenuUtama;
import com.logic.evil.EvilMain;

public class Input implements Screen{
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	Stage stage;
	CameraInputController camController;
	TextureAtlas gambar;
	Skin skinGambar;
	BitmapFont font;
	TextButtonStyle backgroundStyle;
	TextButtonStyle okStyle;
	TextButtonStyle inputStyle;
	TextButton backgroundButton;
	TextButton inputButton;
	TextButton okButton;
	TextFieldStyle inputTextStyle;
	TextField inputText;
	Sound clickSound;
	float soundVolume;
	EvilMain game;
	
	Input(EvilMain Permainan){
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
		
		gambar = game.assets.get("img/input/input.pack"); 
        skinGambar = new Skin();
        skinGambar.addRegions(gambar);
        
        font = game.assets.get("fnt/postcryp.fnt");
        font.setColor(Color.YELLOW);
        font.setScale((float) 1*Gdx.graphics.getWidth()/960, (float) 1*Gdx.graphics.getHeight()/540);
        
        backgroundStyle = new TextButtonStyle();
        backgroundStyle.up = skinGambar.getDrawable("background");
        backgroundStyle.font = font;
        
        okStyle = new TextButtonStyle();
        okStyle.up = skinGambar.getDrawable("ok");
        okStyle.font = font;
        
        inputStyle = new TextButtonStyle();
        inputStyle.up = skinGambar.getDrawable("input");
        inputStyle.font = font;
        
        inputTextStyle = new TextFieldStyle();
        inputTextStyle.font = font;
        inputTextStyle.fontColor = Color.WHITE;
        
        backgroundButton = new TextButton("",backgroundStyle);
        backgroundButton.setPosition(0*Gdx.graphics.getWidth()/960, 0*Gdx.graphics.getHeight()/540); 
        backgroundButton.setHeight(540*Gdx.graphics.getHeight()/540); 
        backgroundButton.setWidth(960*Gdx.graphics.getWidth()/960); 
        
        inputButton = new TextButton("",inputStyle);
        inputButton.setPosition(350*Gdx.graphics.getWidth()/960, 310*Gdx.graphics.getHeight()/540); 
        inputButton.setHeight(94*Gdx.graphics.getHeight()/540); 
        inputButton.setWidth(319*Gdx.graphics.getWidth()/960); 
        
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
        			Gdx.input.setOnscreenKeyboardVisible(false);
        	        game.sendText.setValue(inputText.getText());
        	        game.setScreen(new Login(game));
        		}
            }
        });
        
        inputText = new TextField("", inputTextStyle);
        inputText.setPosition(350*Gdx.graphics.getWidth()/960, 325*Gdx.graphics.getHeight()/540); 
        inputText.setHeight(74*Gdx.graphics.getHeight()/540); 
        inputText.setWidth(320*Gdx.graphics.getWidth()/960); 
        inputText.setMaxLength(10);
        inputText.setTextFieldListener(new TextFieldListener(){
        	public void keyTyped(TextField textField,char c){
        		float fontX = (350*Gdx.graphics.getWidth()/960) + (inputText.getWidth()/2) - ((font.getBounds(inputText.getText()).width)/2);
        	    float fontY = inputText.getY();
        	    inputText.setPosition(fontX,fontY);
        	}
        });
        inputText.setOnscreenKeyboard(new TextField.OnscreenKeyboard() {
            @Override
            public void show(boolean visible) {
            	long idClick = clickSound.play();
        		clickSound.setVolume(idClick, soundVolume);
        		Gdx.input.setOnscreenKeyboardVisible(true);
            }
        });
        
        stage.setKeyboardFocus(inputText);
        stage.addActor(backgroundButton);
        stage.addActor(inputButton);
        stage.addActor(inputText);
        stage.addActor(okButton);
        
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
