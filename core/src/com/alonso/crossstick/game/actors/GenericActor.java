package com.alonso.crossstick.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public abstract class GenericActor extends Actor {

	protected TextureRegion texture_region;
	
	public GenericActor(Texture texture){
		texture_region = new TextureRegion(texture);
		setSize(texture.getWidth(), texture.getHeight());
	}
	
	public GenericActor(TextureRegion textureRegion){
		texture_region = textureRegion;
		setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
	
	public void move(float distance, float duration, int orientation){
		MoveByAction move_by_action = new MoveByAction();
		move_by_action.setAmountX(orientation*distance);
		move_by_action.setDuration(duration);
		this.addAction(move_by_action);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(texture_region, getX(), getY(), getOriginX(), getOriginY(),
				   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		super.draw(batch, parentAlpha);
	}
}
