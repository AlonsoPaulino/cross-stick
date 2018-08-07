package com.alonso.crossstick.domain;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.alonso.crossstick.CrossStick;

public class Player {

	private Integer points;
	private TextureRegion character_selected;
	private int character_index;
	private CrossStick my_game;
	
	public Player(CrossStick my_game, int index){
		this.my_game = my_game;
		character_index = index;
		character_selected = new TextureRegion(my_game.resources.texture_character[character_index]);
		this.points = 0;
	}
	
	public void winPoints(int points){
		this.points = this.points + points;
	}
	
	public void resetPoints(){
		this.points = 0;
	}
	
	public Integer getPoints(){
		return this.points;
	}
	
	@Override
	public String toString() {
		return String.valueOf(points);
	}
	
	public void setCharacter(int position){
		character_index = position;
		this.character_selected = new TextureRegion(my_game.resources.texture_character[position]); 
	}
	
	public int getCharacter(){
		return character_index;
	}
}
