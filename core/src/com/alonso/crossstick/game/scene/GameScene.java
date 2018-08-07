package com.alonso.crossstick.game.scene;

import com.alonso.crossstick.domain.Player;
import com.alonso.crossstick.util.TextLabel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.alonso.crossstick.game.CrossStick;
import com.alonso.crossstick.game.actors.Background;
import com.alonso.crossstick.game.actors.Hero;
import com.alonso.crossstick.game.actors.Bridge;
import com.alonso.crossstick.game.actors.Tower;
import com.alonso.crossstick.game.util.Util;

public class GameScene extends GenericScreen{

	private float speed;
	private Player player;
	private CrossStick my_game;
	private Hero hero;
	private Bridge last_bridge;
	private Bridge bridge;
	private Tower first_tower;
	private Tower second_tower;
	private Background background;
	private Background background_invested;
	private Background clouds;
	private Background clouds_invested;
	
	private boolean start_moving;
	private boolean cycle = false;
	private boolean game_finish;
	private boolean activate_click;
	//private Coin coin;
	//private Coin last_coin;
	private float state_time = 0;
	private Sound hit_sound;
	private Sound hero_fall_out;
	private Sound bonus;
	boolean flag = true;
	
	//private Label coin_image;
	//private TextLabel coins_points;
	private TextLabel score_points;
	
	public GameScene(CrossStick my_game, int character, Texture background_theme, Texture clouds_theme){
		this.my_game = my_game;
		
		this.hit_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.mp3"));
		this.hero_fall_out = Gdx.audio.newSound(Gdx.files.internal("sounds/fall_out.mp3"));
		this.bonus = Gdx.audio.newSound(Gdx.files.internal("sounds/bonus.mp3"));
		
		this.activate_click = true;
		
		this.background = new Background(my_game, background_theme, 1, 100);
		this.background_invested = new Background(my_game, background_theme, -1, 100);
		
		this.clouds = new Background(my_game, clouds_theme, 1, 20);
		this.clouds_invested = new Background(my_game, clouds_theme, -1, 20);
		
		/*this.coin_image = new Label(my_game.resources.texture_coin, 500, 915);
		this.coins_points = new TextLabel(my_game.getCoins(), my_game.resources.label_style_numbers,
												440, 890, 0.35f, 0.35f);
		this.coins_points.setX(coin_image.getX() - this.coins_points.getWidth());*/
		this.score_points = new TextLabel("0",my_game.resources.label_style_numbers,
													270, 800, 0.5f, 0.5f);
		
		this.last_bridge = null;
		this.cycle = true;
		this.start_moving = false;
		this.game_finish = false;
		this.first_tower = new Tower(0,Util.TOWER_INITIAL_WIDTH, false, my_game);
		this.second_tower = new Tower(Util.randomFloat(Util.TOWER_MIN_X, Util.TOWER_MAX_X), 
				Util.randomFloat(Util.TOWER_MIN_WIDTH, Util.TOWER_MAX_WIDTH), true, my_game);
		this.my_game = my_game;
		this.player = my_game.getPlayer();
		
		this.hero = new Hero(my_game, first_tower.getX() + first_tower.getWidth(), Util.HEIGTH_TOWER, 
				my_game.resources.sprite_normal[character], my_game.resources.sprite_playing[character], 0.09f,0.045f);
		
		this.bridge = new Bridge(my_game);
		this.stage.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(activate_click){
					if(flag){
						if(hero!=null)hero.clearActions();
						if(bridge!=null)bridge.clearActions();
						if(first_tower!=null)first_tower.clearActions();
						if(second_tower!=null)second_tower.clearActions();
						if(last_bridge!=null)last_bridge.clearActions();
						//if(coin!=null)coin.clearActions();
						//if(last_coin!=null)last_coin.clearActions();
						hit_sound.loop(1,3,1);
						flag = false;
					}
					if(hero.getCurrentState() == Util.IS_MOVING){
						if((hero.getX() + hero.getWidth()) <= second_tower.getX()){
							hero.girar(bridge.getWidth());
							return super.touchDown(event, x, y, pointer, button);
						}
					}
					if(!start_moving){
						start_moving = true;
						bridge.buildUp();
						return super.touchDown(event, x, y, pointer, button);
					}
				}
				return false;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				hit_sound.stop();
				if(activate_click){
					bridge.fall_out();
					super.touchUp(event, x, y, pointer, button);
				}
			}
		});
		
		stage.addActor(background);
		stage.addActor(background_invested);
		stage.addActor(clouds);
		stage.addActor(clouds_invested);
		stage.addActor(first_tower);
		stage.addActor(second_tower);
		stage.addActor(bridge);
		stage.addActor(hero);
		//stage.addActor(coin_image);
		//stage.addActor(coins_points);
		stage.addActor(score_points);
	}
	
	@Override
	public void render(float delta){
		checkForClick(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		if(!game_finish){
			if(this.cycle){
				if(bridge.getCurrentState()== Util.IS_LYING && hero.getCurrentState()== Util.NORMAL_STATE){
					hero.move(bridge,second_tower, null);
				}
				if(hero.getCurrentState() == Util.FINISH_MOVING_ON_THE_BRIDGE){
					int state_1 = hero.getAuxiliarState1();
					int state_2 = hero.getAuxiliarState2();
						if(state_1 == Util.DOUBLE_POINTS){
							bonus.play();
							player.winPoints(2);
						}else{
							player.winPoints(1);
						}
						if(state_2 == Util.COIN_POINTS){
							Integer value_coin = Integer.parseInt(my_game.getCoins());
							value_coin = value_coin + 1;
							my_game.manager.addPreferences("coins", String.valueOf(value_coin));
							//coins_points.setText(String.valueOf(value_coin));
						}
					score_points.setText(String.valueOf(player.getPoints()));
					cycle = false;
				}else if((hero.getCurrentState() == Util.FALL_OUT_STATE) || (hero.getX() < 0)){
						hero_fall_out.play(1,2,1);
						activate_click = false;
						hero.clearActions(); hero.fall_out();
						bridge.clearActions(); bridge.fall_out_lose();
						this.game_finish = true;
				}
			}else{
				reset();
			}
		}else{
			if(hero.getY()<1){
				gameOver();
			}
		}
	}

	public void reset(){
		hero.remove();
		float distance_tower = second_tower.getX()+second_tower.getWidth() - Util.TOWER_INITIAL_WIDTH;
		float distance_hero = hero.getX() + hero.getWidth() - Util.TOWER_INITIAL_WIDTH - 10;
		this.first_tower.move(distance_tower, Util.TIME_RESET, -1);
		this.second_tower.move(distance_tower, Util.TIME_RESET, -1);
		/*if(coin!=null){
			coin.reset(distance_tower, Util.TIME_RESET, -1);
			this.last_coin = coin;
			coin = null;
		}*/
		this.first_tower = second_tower;
			float new_x = Util.randomFloat(Util.TOWER_MIN_X, Util.TOWER_MAX_X);
			float new_width = Util.randomFloat(Util.TOWER_MIN_WIDTH, Util.TOWER_MAX_WIDTH);
			int state = Util.randomInteger(0,2);
			/*if(state == 1){
				coin = new Coin(540, Util.HEIGTH_TOWER - bridge.getWidth()-20f, my_game);
				coin.move(540 - ((((Util.TOWER_INITIAL_WIDTH)+ new_x)/2)-10), Util.TIME_RESET, -1);
				stage.addActor(coin);
			}else{
				coin = null;
			}*/
			
		Tower new_tower = new Tower(540, new_width , true, my_game);
		new_tower.move(540 - new_x, Util.TIME_RESET, -1);
		stage.addActor(new_tower);
		this.second_tower = new_tower;
		this.bridge.move(distance_tower, Util.TIME_RESET, -1);
		if(last_bridge == null){last_bridge = bridge; stage.addActor(last_bridge);}
		else{last_bridge.remove(); last_bridge = bridge;} 
		Bridge new_bridge = new Bridge(my_game);
		stage.addActor(new_bridge);
		stage.addActor(hero);
		this.hero.move(distance_hero, Util.TIME_RESET,-1);
		bridge = new_bridge;
		start_moving = false;
		cycle = true;
		activate_click = false;
		state_time = 0;
	}
	

	public void checkForClick(float delta){
		if(!start_moving && cycle && !activate_click && !game_finish){
			state_time += delta;
				if(state_time > Util.TIME_RESET){
					this.increaseSpeed();
					activate_click = true;
					flag = true;
					
					float distance = 0;
					float time = 0;
						if(speed > 0 ){ 
							distance = Util.TOWER_INITIAL_WIDTH;
							time = distance/speed;
						}
						//if(coin!=null)coin.move(distance,time, -1);
						//if(last_coin!=null)last_coin.move(distance,time,-1);
						hero.move(distance, time, -1);
						first_tower.move(distance,time, -1);
						second_tower.move(distance,time, -1);
						last_bridge.move(distance,time, -1);
						bridge.move(distance, time, -1);
				}		
		}
	}
	
	public void gameOver(){
		this.stage.clear();
		hero_fall_out.stop();
		my_game.change_to_reset_screen();
	}
	
	public void increaseSpeed(){
		if(speed < 20){
			this.speed += 2.5f;
		}
	}
}
