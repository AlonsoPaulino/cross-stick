package com.alonso.crossstick.game.scene;

import com.alonso.crossstick.game.CrossStick;
import com.alonso.crossstick.game.actors.Background;
import com.alonso.crossstick.game.actors.Button;
import com.alonso.crossstick.game.actors.Label;

public class MainScene extends GenericScreen{

	private CrossStick my_game;
	private Background background;
	
	private Button play_button;
	//private Button select_button;
	private Button rate_us_button;
	private Button share_button;
	
	//private Character[] ls_characters;
	
	private Label title_label;
	//private Label section_select_character;
	private Label hero_init_texture;
	//private TextLabel coins_points;

	public MainScene(final CrossStick my_game){
		super();
		this.my_game = my_game;
		
		//this.createCharacters();
		
		background = new Background(my_game, my_game.resources.texture_background_color, 0);
		background.setSize(560, 970);
		//section_select_character = new Label(my_game.resources.texture_background_options,48, 66f, false);
		hero_init_texture = new Label(my_game.resources.sprite_playing[0], 0.05f, 82f, 465f);
		hero_init_texture.setBounds(90,490, 110.5f, 132.6f);
		
		/*this.coins_points = new TextLabel(my_game.getCoins(), my_game.resources.label_style_numbers,
												440, 890, 0.35f, 0.35f);*/
		
		//this.coins_points.setVisible(false);
		
		title_label = new Label(my_game.resources.texture_title_label, 89f, 493f);
		
		play_button = new Button(my_game.resources.texture_play_button, 0, 249){
			@Override
			public void executeAction() {
				my_game.change_to_game();
				super.executeAction();
			}
		};
	
		/*Posiciones de los botons si se toma como visible el boton de seleccionar personaje*/
		/*rate_us_button = new Button(my_game.resources.texture_rate_us_button, 75.5f, 133.5f){
			@Override
			public void executeAction() {
				my_game.rateUs();
				super.executeAction();
			}
		};
		
		share_button = new Button(my_game.resources.texture_share_button, 225, 133.5f){
			@Override
			public void executeAction() {
				my_game.share();
				super.executeAction();
			}
		};*/
		
		rate_us_button = new Button(my_game.resources.texture_rate_us_button, 150.25f, 133.5f){
			@Override
			public void executeAction() {
				my_game.rateUs();
				super.executeAction();
			}
		};
		
		share_button = new Button(my_game.resources.texture_share_button, 299.75f, 133.5f){
			@Override
			public void executeAction() {
				my_game.share();
				super.executeAction();
			}
		};
	
		/*select_button = new Button(my_game.resources.texture_select_button, 370, 133.5f){
			@Override
			public void executeAction() {
				show_panel(true, false);
				super.executeAction();
			}
		};*/
		
	    this.stage.addActor(background);
		this.stage.addActor(play_button);
		//this.stage.addActor(select_button);
		this.stage.addActor(rate_us_button);
	    this.stage.addActor(share_button);
	    this.stage.addActor(hero_init_texture);
	    //this.stage.addActor(section_select_character);
	    this.stage.addActor(title_label);
	    //this.stage.addActor(coins_points);
	    //for(int i = 0; i < ls_characters.length ; ++i)this.stage.addActor(ls_characters[i]);
	}
	
	public void show_panel(boolean select, boolean main){
		//play_button.setVisible(main); select_button.setVisible(main);
		rate_us_button.setVisible(main); share_button.setVisible(main);
		//section_select_character.setVisible(select);
		//this.coins_points.setText(my_game.getCoins());
		//coins_points.setVisible(select);
		title_label.setVisible(main);
		hero_init_texture.setVisible(main);
		/*for(int i = 0; i < ls_characters.length ; ++i){
			ls_characters[i].setVisible(select);
		}*/
	}
	
	/*private void createCharacters(){
		ls_characters = new Character[Util.NUM_CHARACTERS];
		float y = 622f;
			for(int i = 0; i < Util.NUM_CHARACTERS; i++){
				if(i%2 == 0){
					ls_characters[i] = new Character(my_game, 59.5f, y - i*180, i){
						@Override
						public void executeAction() {
							if(getState()){
								super.executeAction();
								hero_init_texture.setAnimation(my_game.resources.sprite_playing[getIndex()], 0.05f);
								show_panel(false, true);
							}else{
								super.executeAction();
							}
						}
					};
				}else{
					ls_characters[i] = new Character(my_game, 274f, y - i*180, i){
						@Override
						public void executeAction() {
							if(getState()){
								super.executeAction();
								hero_init_texture.setAnimation(my_game.resources.sprite_playing[getIndex()], 0.05f);
								show_panel(false, true);
							}else{
								super.executeAction();
							}
						}
					};
				}
				ls_characters[i].setVisible(false);
				y = y - i*180;
			}
			
		ls_characters[0].setPosition(59.5f, 622f);
		ls_characters[1].setPosition(274, 622f);
		ls_characters[2].setPosition(59.5f, 442);
		ls_characters[3].setPosition(274, 442);
		ls_characters[4].setPosition(59.5f, 262);
		ls_characters[5].setPosition(274, 262);
		ls_characters[6].setPosition(59.5f, 82);
		ls_characters[7].setPosition(274, 82);
		
		for(int i = 0; i < 8; ++i) ls_characters[i].setState(my_game.checkCharacter(i));
	}*/
}

