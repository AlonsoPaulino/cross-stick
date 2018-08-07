package com.alonso.crossstick.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.alonso.crossstick.game.util.GUIResource;

public class Label extends GUIResource {

	public Label(Texture texture, float x, float y) {
		super(texture,x,y);
	}
	
	public Label(Texture texture, float x, float y, boolean visibility) {
		super(texture,x,y, visibility);
	}
	
	public Label(Texture texture, float width, float height, float x, float y){
		super(texture, width, height, x, y);
	}
	
	public Label(Texture texture, float width, float height, float x, float y, boolean visibility){
		super(texture, width, height, x, y, visibility);
	}
	
	public Label(TextureRegion texture[], float time, float x, float y){
		super(texture,time,x,y);
	}
}
