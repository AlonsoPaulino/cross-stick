package com.alonso.crossstick.UtilActorLocator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** La clase RegionActor constituye la clase m�s gen�rica de cualquier tipo de actor en escena utilizado en nuestro game.
 * <p>
 * Consta simplemente de un actor con una {@link TextureRegion region} con la cual es dibujada; y pos�e los m�s vers�tiles
 * tipos de constructores a partir de Texture y TextureRegion, permitiendo incluso que el actor tenga una regi�n nula, 
 * es decir, que est� en escena pero no muestre un cuerpo visible.
 * <p>
 * La mayor�a de subtipos de actores que se usar�n en el game extender�n de esta gen�rica clase. 
 * <p>
 * Para utilidades de rotaci�n, el centro de origen vendr� ajustado por defecto en el centro del actor
 * @author canyouescape
 * */
public class RegionActor extends Actor {

	protected TextureRegion region;
	
	private static final float NATURAL_SCALE_WIDTH = 1f;			// Por defecto estos valores deber�n ser 1f.
	private static final float NATURAL_SCALE_HEIGHT = 1f;			//	
	
	//*** Textures...
	
	// tama�o natural:
	
	public RegionActor(Texture texture) {
		
		this (new TextureRegion(texture), 0, 0, (int) (texture.getWidth() * NATURAL_SCALE_WIDTH), 
				(int) (texture.getHeight() * NATURAL_SCALE_HEIGHT), true);
	}	
	
	public RegionActor(Texture texture, boolean isVisible) {
		
		this (new TextureRegion(texture), 0, 0, (int) (texture.getWidth() * NATURAL_SCALE_WIDTH), 
				(int) (texture.getHeight() * NATURAL_SCALE_HEIGHT), isVisible);
	}
	
	public RegionActor(Texture texture, float x, float y) {
		
		this (new TextureRegion(texture), x, y, (int) (texture.getWidth() * NATURAL_SCALE_WIDTH), 
				(int) (texture.getHeight() * NATURAL_SCALE_HEIGHT), true);
	}
	
	public RegionActor(Texture texture, float x, float y, boolean isVisible) {
				
		this (new TextureRegion(texture), x, y, (int) (texture.getWidth() * NATURAL_SCALE_WIDTH), 
				(int) (texture.getHeight() * NATURAL_SCALE_HEIGHT), isVisible);
	}
	
	// tama�o a escala:
	
	public RegionActor(Texture texture, float x, float y, float size) {
		
		this (new TextureRegion(texture), x, y, size, size, true);
	}
	
	public RegionActor(Texture texture, float x, float y, float size, boolean isVisible) {
		
		this (new TextureRegion(texture), x, y, size, size, isVisible);
	}

	public RegionActor(Texture texture, float x, float y, float width, float height) {
		
		this (new TextureRegion(texture), x, y, width, height, true);
	}
	
	public RegionActor(Texture texture, float x, float y, float width, float height, boolean isVisible) {
		
		this (new TextureRegion(texture), x, y, width, height, isVisible);		
	}
	
	//*** TextureRegions...
	
	// TAMA�O NATURAL
	
	public RegionActor(TextureRegion region) {
		
		this (region, 0, 0, (int) (region.getRegionWidth() * NATURAL_SCALE_WIDTH), 
				(int) (region.getRegionHeight() * NATURAL_SCALE_HEIGHT), true);
	}	
	
	public RegionActor(TextureRegion region, boolean isVisible) {
		
		this (region, 0, 0, (int) (region.getRegionWidth() * NATURAL_SCALE_WIDTH), 
				(int) (region.getRegionHeight() * NATURAL_SCALE_HEIGHT), isVisible);
	}
	
	public RegionActor(TextureRegion region, float x, float y) {
		
		this (region, x, y, (int) (region.getRegionWidth() * NATURAL_SCALE_WIDTH), 
				(int) (region.getRegionHeight() * NATURAL_SCALE_HEIGHT), true);
	}
	
	public RegionActor(TextureRegion region, float x, float y, boolean isVisible) {
		
		this (region, x, y, (int) (region.getRegionWidth() * NATURAL_SCALE_WIDTH), 
				(int) (region.getRegionHeight() * NATURAL_SCALE_HEIGHT), isVisible);
	}
	
	// TAMA�O A ESCALA
	
	public RegionActor(TextureRegion region, float x, float y, float size) {
		
		this (region, x, y, size, size, true);
	}
	
	public RegionActor(TextureRegion region, float x, float y, float size, boolean isVisible) {
		
		this (region, x, y, size, size, isVisible);
	}

	public RegionActor(TextureRegion region, float x, float y, float width, float height) {
		
		this (region, x, y, width, height, true);
	}
	
	public RegionActor(TextureRegion region, float x, float y, float width, float height, boolean isVisible) {
		
		this.region = region;
		setBounds(x, y, width, height);
		setOrigin(getWidth()/2, getHeight()/2);
		setVisible(isVisible);
	}
	
	//********* ACTOR SIN REGION... (REGION = NULO) ***********
	
	public RegionActor(float x, float y, float size) {
		
		this (x, y, size, size, true);
	}
	
	public RegionActor(float x, float y, float size, boolean isVisible) {
		
		this (x, y, size, size, isVisible);
	}
	
	public RegionActor(float x, float y, float width, float height) {
		
		this (x, y, width, height, true);
	}
	
	public RegionActor(float x, float y, float width, float height, boolean isVisible) {
		
		setBounds(x, y, width, height);
		setOrigin(getWidth()/2, getHeight()/2);
		setVisible(isVisible);
	}
	
	// SETTER
	
	public void setRegion() {		
		this.region = null;
	}
	
	public void setRegion(Texture texture) {	
		setRegion(new TextureRegion(texture));
	}
	
	public void setRegion (TextureRegion region) {
		this.region = region;
	}
	
	// GETTER
	
	public TextureRegion getRegion() {
		return region;
	}
	
	// DRAW...
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		if (region == null) return;
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 
				getScaleX(), getScaleY(), getRotation());		
	}
}
