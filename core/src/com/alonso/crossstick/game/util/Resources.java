package com.alonso.crossstick.game.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Resources {
	
	private ResourceManager manager;
	
	
	public LabelStyle label_style_letters;
	public LabelStyle label_style_numbers;
	
	public Texture texture_coin;
	public Texture texture_play_button;
	public Texture texture_tower;
	public Texture texture_tower_centre;
	public Texture texture_background_color;
	public Texture texture_background_theme_1;
	public Texture texture_background_theme_2;
	
	public Texture texture_clouds_1;
	public Texture texture_clouds_2;
	
	public Texture texture_background_options;
	public Texture texture_replay_button;
	public Texture texture_red;
	public Texture texture_home_button;
	public Texture texture_rate_us_button;
	public Texture texture_share_button;
	public Texture texture_bridge;
	
	public Texture texture_select_button;
	
	public BitmapFont bitmapfont_hero_letters;
	public BitmapFont bitmapfont_hero_numbers;
	
	public Texture texture_title_label;
	public Texture texture_game_over_label;
	public Texture texture_white_section;
	public Texture texture_transparente;
	
	public Texture[] texture_character;
	public Texture[] texture_prices;
	
	public TextureRegion sprite_normal[][];
	public TextureRegion sprite_playing[][];
	
	public Texture texture_block_character;
	
	public Resources(){
		
		manager = new ResourceManager(new AssetManager());
		
		manager.loadTexture("background_color.png");
		manager.loadTexture("replay_button.png");
		manager.loadTexture("rate_us_button.png");
		manager.loadTexture("share_button.png");
		manager.loadTexture("coin.png");
		manager.loadTexture("play_button.png");
		manager.loadTexture("fondo_construccion.jpg");
		manager.loadTexture("azul.jpg");
		manager.loadTexture("rojo.png");
		manager.loadTexture("background_theme1.png");
		manager.loadTexture("background_theme2.png");
		manager.loadTexture("clouds1.png");
		manager.loadTexture("clouds2.png");
		manager.loadTexture("background_options.png");
		manager.loadTexture("fondo_construccion.jpg");
		manager.loadTexture("rojo.png");
		manager.loadTexture("home_button.png");
		manager.loadTexture("block_character.png");
		manager.loadTexture("title_label.png");
		manager.loadTexture("game_over_label.png");
		manager.loadTexture("section_points.png");
		manager.loadTexture("transparente.png");
		manager.loadTexture("select_button.png");
		for(int i = 1; i < 9; i++) manager.loadTexture("characters/"+String.valueOf(i)+".png");
		manager.loadTexture("25.png");
		manager.loadTexture("75.png");
		manager.loadTexture("150.png");
		manager.loadTexture("250.png");
		manager.loadTexture("300.png");
		manager.loadTexture("400.png");
		manager.loadTexture("500.png");
		manager.loadBitMap("font_hero_lettes.fnt");
		manager.loadBitMap("font_hero_numbers.fnt");
		
		for(int i = 1; i < 9; i++){
			manager.loadTextureAtlas("sprites_normal/normal"+String.valueOf(i)+".atlas");
			manager.loadTextureAtlas("sprites_playing/playing"+String.valueOf(i)+".atlas");
		}
		
		while(!manager.update());
		
		for(int i = 1; i < 9; i++){
			manager.loadSkin("sprites_normal/normal"+String.valueOf(i)+".atlas");
			manager.loadSkin("sprites_playing/playing"+String.valueOf(i)+".atlas");
		}
		
		texture_background_color = manager.getTexture("background_color.png");
		texture_replay_button = manager.getTexture("replay_button.png");
		texture_rate_us_button = manager.getTexture("rate_us_button.png");
		texture_share_button = manager.getTexture("share_button.png");
		texture_coin = manager.getTexture("coin.png");
		texture_play_button = manager.getTexture("play_button.png");
		texture_tower = manager.getTexture("fondo_construccion.jpg");
		texture_tower_centre = manager.getTexture("rojo.png");
		texture_background_theme_1 = manager.getTexture("background_theme1.png");
		texture_background_theme_2 = manager.getTexture("background_theme2.png");
		texture_clouds_1 = manager.getTexture("clouds1.png");
		texture_clouds_2 = manager.getTexture("clouds2.png");
		texture_background_options = manager.getTexture("background_options.png");
		texture_red = manager.getTexture("rojo.png");
		texture_home_button = manager.getTexture("home_button.png");
		texture_block_character = manager.getTexture("block_character.png");
		texture_title_label = manager.getTexture("title_label.png");
		texture_game_over_label = manager.getTexture("game_over_label.png");
		texture_white_section = manager.getTexture("section_points.png");
		texture_transparente = manager.getTexture("transparente.png");
		texture_select_button = manager.getTexture("select_button.png");
		texture_bridge = manager.getTexture("azul.jpg");
		
		texture_character = new Texture[8];
			for(int i = 0; i < 8 ; ++i){
				int character = i+1;
				texture_character[i] = manager.getTexture("characters/"+String.valueOf(character)+".png");
			}
		
		label_style_letters = new LabelStyle(manager.getFont("font_hero_lettes.fnt"), Color.BLACK);
		label_style_numbers = new LabelStyle(manager.getFont("font_hero_numbers.fnt"), Color.BLACK);
		
		sprite_normal = new TextureRegion[8][9];
			for(int i = 0; i<8; i++){
				for(int j = 0; j < 9; j++){
					String character = String.valueOf(i+1), sprite = String.valueOf(j+1);
					sprite_normal[i][j] = manager.getTextureRegion(character+"_n"+sprite);
				}
			}
			
		sprite_playing = new TextureRegion[8][16];
			for(int i = 0; i<8; i++){
				for(int j = 0; j < 16; j++){
					String character = String.valueOf(i+1), sprite = String.valueOf(j+1);
					sprite_playing[i][j] = manager.getTextureRegion(character+"_p"+sprite);
				}
			}
		
		texture_prices = new Texture[8];
		texture_prices[1] = manager.getTexture("25.png");
		texture_prices[2] = manager.getTexture("75.png");
		texture_prices[3] = manager.getTexture("150.png");
		texture_prices[4] = manager.getTexture("250.png");
		texture_prices[5] = manager.getTexture("300.png");
		texture_prices[6] = manager.getTexture("400.png");
		texture_prices[7] = manager.getTexture("500.png");
	}
	
	public ResourceManager getManager(){
		return this.manager;
	}
}
