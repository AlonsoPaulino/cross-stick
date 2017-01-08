package com.mygdx.game;
import Model.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.scene.GameScene;
import com.mygdx.game.scene.MainScene;
import com.mygdx.game.scene.GameOverScene;
import com.mygdx.game.util.Manager;
import com.mygdx.game.util.Resources;
import com.mygdx.game.util.Util;

public class StickHero extends Game{

	private GameScene game_screen;
	private MainScene main_screen;
	private GameOverScene reset_game_screen;
	private Player player;
	private Boolean [] lsCharacters;
	public Resources resources;
	public int character;
	public Manager manager;
	public int count_games = 0;
	
	@Override
	public void create(){
		resources = new Resources();
		manager = new Manager();
		lsCharacters = new Boolean[8];
		player = new Player(this, 0);
		initCharactersPreferences();
		main_screen = new MainScene(this);
		this.setScreen(main_screen);
		Gdx.input.setInputProcessor(main_screen.getStage());
	}
	
	public void change_to_game(){
		int theme = this.getBackground();
		Texture texture_theme, texture_clouds;
		if(theme == 1){ 
			texture_theme = resources.texture_background_theme_1;
			texture_clouds = resources.texture_clouds_1;
		}else{
			texture_theme = resources.texture_background_theme_2;
			texture_clouds = resources.texture_clouds_2;
		}
		game_screen = new GameScene(this, player.getCharacter(), texture_theme, texture_clouds);
		this.setScreen(game_screen);
		Gdx.input.setInputProcessor(game_screen.getStage());
	}
	
	public void change_to_reset_screen(){
		reset_game_screen = new GameOverScene(this);
		this.setScreen(reset_game_screen);
		Gdx.input.setInputProcessor(reset_game_screen.getStage());
	}
	
	public void mostrarAdmob(){
		
	}
	
	public void share(){
		
	}
	
	public void rateUs(){
		
	}
	
	public void change_to_main_screen(){
		this.setScreen(main_screen);
		Gdx.input.setInputProcessor(main_screen.getStage());
	}
	
	public void addTegaloPoints(String points){
		
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Manager getManager(){
		return this.manager;
	}

	private int getBackground(){
		int theme = Util.randomInteger(0, 2);
		return (theme == 1)?1:2;
	}
	
	public String getCoins(){
		String coins = manager.getPreferences("coins");
		return (coins.equals("false"))? "0" : coins;
	}
	
	public void initCharactersPreferences(){
		String player = manager.getPreferences("PLAYER_OK");
		if(player.toString().trim().equals("true")){
			for(int i = 0; i < 8 ; ++i){
				String state = manager.getPreferences("c"+i);
				lsCharacters[i] = (state.toString().trim().equals("true"))?true:false;
			}
		}else{
			manager.addPreferences("PLAYER_OK","true");
			manager.addPreferences("c0","true");
			manager.addPreferences("c1","false");
			manager.addPreferences("c2","false");
			manager.addPreferences("c3","false");
			manager.addPreferences("c4","false");
			manager.addPreferences("c5","false");
			manager.addPreferences("c6","false");
			manager.addPreferences("c7","false");
			lsCharacters[0] = true;
			for(int i = 1; i < 8; ++i)lsCharacters[i] = false;
		}
	}
	
	public void updateCharacter(int index){
		manager.addPreferences("c"+index,"true");
		lsCharacters[index] = true;
	}
	
	public boolean updateCoins(int lose){
		String coins = getCoins();
		int num_coins = 10*Integer.parseInt(coins);
		if(num_coins >= lose){
			manager.addPreferences("coins", String.valueOf((int)(num_coins - lose)));
			return true;
		}else return false;
	}
	
	public boolean checkCharacter(int index){
		return lsCharacters[index];
	}
}
