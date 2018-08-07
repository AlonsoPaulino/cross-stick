package com.alonso.crossstick.game.util;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourceManager {

	private AssetManager asset_manager;
	private Skin skin;
	private final String PATH_TEXTURES = "resources/";
	private final String PATH_FONTS = "fonts/";
	//private final String PATH_SOUNDS = "sounds/";
	
	public ResourceManager(AssetManager asset_manager) {
		this.asset_manager = asset_manager;
		this.skin = new Skin();
	}
	
	public void loadTexture(String path){
		TextureParameter parameter = new TextureParameter();
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;
		asset_manager.load(PATH_TEXTURES+path, Texture.class, parameter);
	}
	
	public void loadTextureAtlas(String path){
		asset_manager.load(PATH_TEXTURES+path, TextureAtlas.class);
	}
	
	public void loadBitMap(String path){
		BitmapFontParameter parameter = new BitmapFontParameter();
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;
		asset_manager.load(PATH_FONTS+path, BitmapFont.class, parameter);
	}
	
	public Texture getTexture(String path){
		return asset_manager.get(PATH_TEXTURES+path, Texture.class);
	}
	
	public void loadSkin(String path){
		skin.addRegions(getTextureAtlas(path));
	}
	
	public TextureAtlas getTextureAtlas(String path){
		return asset_manager.get(PATH_TEXTURES+path, TextureAtlas.class);
	}
	
	public BitmapFont getFont(String path){
		return asset_manager.get(PATH_FONTS+path, BitmapFont.class);
	}
	
	public TextureRegion getTextureRegion(String name){
		return this.skin.getRegion(name);
	}	
	
	public boolean update(){
		return this.asset_manager.update();
	}
}
