package com.alonso.crossstick.game.actors;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.alonso.crossstick.CrossStick;

public class Coin extends GenericActor{
	
	public Coin(float x, float y, CrossStick my_game){
		super(my_game.resources.texture_coin);
		this.setBounds(x, y, 20f, 20f);
	}	
	
	
	public void reset(float distance, float duration, int orientation) {
		MoveByAction move_by_action = new MoveByAction(){
			@Override
			protected void end() {
				this.actor.remove();
				super.end();
			}
		};
		move_by_action.setAmountX(orientation*distance);
		move_by_action.setDuration(duration);
		this.addAction(move_by_action);
	}
}
