package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GUIResource extends Actor{

	protected TextureRegion texture_region;
	private Animation animation;
	private float state_time = 0;
	
	public GUIResource(){
		
	}

	public GUIResource(Texture texture, float x, float y) {
		this.texture_region = new TextureRegion(texture);
		this.setHeight(texture.getHeight());
		this.setWidth(texture.getWidth());
		this.setX(x); this.setY(y);
		this.setOrigin(texture.getWidth()/2, texture.getHeight()/2);
	}
	
	public GUIResource(Texture texture, float x, float y, boolean visibility) {
		this.texture_region = new TextureRegion(texture);
		this.setHeight(texture.getHeight());
		this.setWidth(texture.getWidth());
		this.setX(x); this.setY(y);
		this.setOrigin(texture.getWidth()/2, texture.getHeight()/2);
		this.setVisible(visibility);
	}
	
	public GUIResource(Texture texture, float width, float height, float x, float y){
		this.texture_region = new TextureRegion(texture);
		this.setHeight(height);
		this.setWidth(width);
		this.setOrigin(width/2, height/2);
		this.setPosition(x, y);
	}
	
	public GUIResource(Texture texture, float width, float height, float x, float y, boolean visibility){
		this.texture_region = new TextureRegion(texture);
		this.setHeight(height);
		this.setWidth(width);
		this.setOrigin(width/2, height/2);
		this.setPosition(x, y);
		this.setVisible(visibility);
	}
	
	public GUIResource(TextureRegion[] texture_region, float time, float x, float y){
		this.texture_region = texture_region[0];
		this.setWidth(this.texture_region.getRegionWidth());
		this.setHeight(this.texture_region.getRegionHeight());
		this.setOrigin(getWidth()/2, getHeight()/2);
		this.setPosition(x, y);
		animation = new Animation(time, texture_region);
	}
	
	public void setAnimation(TextureRegion[] texture_region, float time){
		this.texture_region = texture_region[0];
		this.animation = new Animation(time, texture_region);
	}
	
	@Override
	public void act(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		state_time += Gdx.graphics.getDeltaTime();
		if(animation != null){
			if(animation.isAnimationFinished(state_time)) state_time = 0;
			texture_region = animation.getKeyFrame(state_time);
		}
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(texture_region, getX(), getY(), getOriginX(), getOriginY(),
				   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		super.draw(batch, parentAlpha);
	}

	public TextureRegion getTextureRegion(){
		return this.texture_region;
	}
	
	public Texture getTexture(){
		return texture_region.getTexture();
	}
	
	public void setTexture(Texture texture){
		this.texture_region = new TextureRegion(texture);
	}
	
}
