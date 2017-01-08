package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.StickHero;
import com.mygdx.game.util.Util;

public class Tower extends GenericActor{
	
	private TextureRegion texture_region_mid;
	private boolean activate;
	
	public Tower(float x , float width, boolean activate, StickHero my_game){
		super(my_game.resources.texture_tower);
		this.activate = activate;
		if(activate)texture_region_mid = new TextureRegion(my_game.resources.texture_red);
		this.setBounds(x, 0, width, Util.HEIGTH_TOWER);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(activate)batch.draw(texture_region_mid, getX() + (getWidth()/2) - 3.5f, getY() + getHeight() - 5, getOriginX(), getOriginY(),
				7f, 5f, getScaleX(), getScaleY(), getRotation());
	}
}
