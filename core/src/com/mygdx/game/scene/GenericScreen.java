package com.mygdx.game.scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GenericScreen implements Screen {

	protected Stage stage;
	
	public GenericScreen(){
		this.stage = new Stage(new StretchViewport(540, 960));
	}
	
	@Override
	public void render(float delta) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.act();
			stage.draw();
	}
	
	public Stage getStage(){
		return this.stage;
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height,true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		
	}

	
	
}
