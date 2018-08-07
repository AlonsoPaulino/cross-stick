package com.alonso.crossstick.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.alonso.crossstick.game.CrossStick;
import com.alonso.crossstick.game.util.Util;

public class Hero extends GenericActor{
	
	private Integer current_state;
	private Integer auxiliar_state_1;
	private Integer auxiliar_state_2;
	private int orientation_y;
	private float state_time;
	
	private Animation normal_animation;
	private Animation running_animation;
	
	private Animation current_animation;
	
	public Hero(final CrossStick my_game, float x, float y, TextureRegion[] normal_animation,
                TextureRegion[] running_animation, float time_animation1, float time_animation2){
		
		super(normal_animation[0]);
		this.orientation_y = 1;
		this.current_state = Util.NORMAL_STATE;
		this.auxiliar_state_1 = Util.NORMAL_STATE;
		this.auxiliar_state_2 = Util.NORMAL_STATE;
		setPosition(x - getWidth(), y-1);
		this.setOrigin(this.getWidth(), this.getHeight());
		this.normal_animation = new Animation(time_animation1, normal_animation);
		this.running_animation = new Animation(time_animation2, running_animation);
		this.current_animation = this.normal_animation;
	}
	
	@Override
	public void act(float delta) {
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		 state_time += Gdx.graphics.getDeltaTime();
		 if(current_animation.isAnimationFinished(state_time)) state_time = 0;
		 if(orientation_y == 1) texture_region = (TextureRegion) current_animation.getKeyFrame(state_time);
		 else{
			 TextureRegion texture_aux = new TextureRegion((TextureRegion) current_animation.getKeyFrame(state_time));
			 texture_aux.flip(false,true);
			 texture_region = texture_aux;
		 }
		 super.act(delta);
	}
	
	public void move(final Bridge bridge, final Tower tower, final Coin coin){
		this.current_state = Util.IS_MOVING;
		final Hero this_hero = this;
		MoveByAction move_action = new MoveByAction() {
			boolean lose = false;
			@Override
			protected void begin(){
				state_time = 0;
				auxiliar_state_1 = Util.NORMAL_STATE;
				auxiliar_state_2 = Util.NORMAL_STATE;
				float distance = bridge.getHeight() + this_hero.getWidth();
				if((this_hero.getX() + distance) > 540)distance = 540 - this_hero.getX();
				if((bridge.getX() + bridge.getHeight() + bridge.getWidth()/2) >= tower.getX() &&
				   (bridge.getX() + bridge.getHeight() + bridge.getWidth()/2) <= (tower.getX() + tower.getWidth()))
					distance = 30 + (tower.getX() + tower.getWidth()) - (getX() + getWidth());
				else{
					if((bridge.getX() + bridge.getHeight() + bridge.getWidth()/2) < tower.getX())
						distance -= 40;
					lose = true;
				} 
				setAmountX(distance);
				float velocity = 400f;
				float tiempo = distance/velocity;
				setDuration(tiempo);
				super.begin();
			}
			
			@Override
			public boolean act(float delta) {
				current_animation = running_animation;
				if((this_hero.getOrientation() == -1 && (this_hero.getX() + this_hero.getWidth()) > tower.getX())){
					current_state = Util.FALL_OUT_STATE;
					this_hero.removeAction(this);
					return true;
				}else{
					float mitad_izq = tower.getX() + tower.getWidth()/2 - 3.5f;
					float mitad_der = tower.getX() + tower.getWidth()/2 + 3.5f;
					if(((bridge.getX() + bridge.getHeight()) >= mitad_izq - 1f) && ((bridge.getX() + bridge.getHeight()) <= mitad_der + 1f)){
						auxiliar_state_1 = Util.DOUBLE_POINTS;
					}
					if(coin!=null && this_hero.orientation_y == -1){
						if((((this_hero.getX() + this_hero.getWidth())-2)>coin.getX())&& 
								(this_hero.getX() < (coin.getX() + coin.getWidth() - 1))){
							coin.remove();
							auxiliar_state_2 = Util.COIN_POINTS;
						}
					}
				}
				return super.act(delta);
			}
			
			@Override
			protected void end() {
				if(lose){
					current_state = Util.FALL_OUT_STATE;
				}else{ 
					state_time = 0;
					current_animation = normal_animation;
					current_state = Util.FINISH_MOVING_ON_THE_BRIDGE;
				}
			}
		};
		addAction(move_action);	
	}
	
	public void fall_out(){
		this.current_state = Util.IS_FALLING;
		if(orientation_y == 1)this.girar(0f);
		MoveByAction move_action = new MoveByAction();
		move_action.setAmountY(-1*Util.HEIGTH_TOWER);
		move_action.setDuration(0.8f);
		addAction(move_action);		
	}

	public void girar(float bridge_height){
		if(orientation_y == 1){
			this.setY(this.getY() - this.getHeight() - bridge_height);
			this.orientation_y = -1;
		}else{
			this.setY(this.getY() + this.getHeight() + bridge_height);
			this.orientation_y = 1;
		}
		TextureRegion texture_region_aux = new TextureRegion(this.texture_region.getTexture());
		texture_region_aux.flip(false,true);
		this.texture_region = texture_region_aux;
		this.texture_region.flip(false, true);
	}
	
	public void move(float distance, float time, int orientation){
		super.move(distance, time, orientation);
		this.current_state = Util.NORMAL_STATE;
	}
	
	public int getOrientation(){
		return this.orientation_y;
	}
	
	public void setOrientation(int orientation_y){
		this.orientation_y = orientation_y;
	}
	
	public int getAuxiliarState1(){
		return this.auxiliar_state_1;
	}
	
	public int getAuxiliarState2(){
		return this.auxiliar_state_2;
	}
	
	
	public int getCurrentState(){
		return this.current_state;
	}
	
	public void setCurrentState(int state){
		this.current_state = state;
	}
	
}
