package com.alonso.crossstick.game.actors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.alonso.crossstick.CrossStick;
import com.alonso.crossstick.game.util.Util;

public class Bridge extends GenericActor{
	
	private Integer current_state;
	private TiledDrawable td;
	
	public Bridge(CrossStick my_game){
		super(my_game.resources.texture_bridge);
		td = new TiledDrawable(texture_region);
		this.current_state = Util.NORMAL_STATE;
		this.setHeight(0f);
		this.setWidth(Util.BRIDGE_WIDTH);
		this.setPosition(Util.TOWER_INITIAL_WIDTH - getWidth()/2, Util.HEIGTH_TOWER - 1);
	}
	
	@Override
	public void act(float delta){
		if(current_state == Util.GROW_UP_STATE)this.setHeight(this.getHeight() + 10.8f);
		super.act(delta);
	}

	public void buildUp(){
		this.current_state = Util.GROW_UP_STATE;
	}
	
	public void fall_out(){
		RotateToAction rotate = new RotateToAction(){
			@Override
			protected void begin() {
				current_state = Util.FALL_OUT_STATE;
				setOrigin(getWidth()/2, 0);
				setRotation(-90);
				setDuration(0.5f);
				setInterpolation(Interpolation.pow2In);
				super.begin();
			}
			
			@Override
			protected void end() {
				current_state = Util.IS_LYING;
				super.end();
			}
		};
		this.addAction(rotate);
	}

	public void fall_out_lose(){
		RotateToAction rotate = new RotateToAction(){
			@Override
			protected void begin() {
				current_state = Util.FALL_OUT_LOSE;
				setRotation(-180);
				setDuration(0.4f);
				setInterpolation(Interpolation.pow2In);
				super.begin();
			}
		};
		this.addAction(rotate);
	}
	
	public void setCurrentState(int current_state){
		this.current_state = current_state;
	}
	
	public int getCurrentState(){
		return current_state;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(td.getRegion(), getX(), getY(), getOriginX(), getOriginY(),
			   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}	
}