package com.alonso.crossstick.game.actors;

import com.alonso.crossstick.game.StickHero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Background extends GenericActor{
	
	float width;
	float duration;

	public Background(StickHero my_game, Texture texture, int state){
		super(texture);
		width = getWidth();
		setWidth(width + 5);
		if(state == 1){
			this.setX(0);
			this.addAction(new SequenceAction(move_first(), Actions.forever(move())));
		}else if(state == -1){ 
			this.setX(width);
			this.addAction(Actions.forever(move()));
		}
	}
	
	public Background(StickHero my_game, Texture texture, int state, float duration){
		super(texture);
		this.duration = duration;
		width = getWidth();
		setWidth(width + 5);
		if(state == 1){
			this.setX(0);
			this.addAction(new SequenceAction(move_first(), Actions.forever(move())));
		}else if(state == -1){ 
			this.setX(width);
			this.addAction(Actions.forever(move()));
		}
	}

	private MoveToAction move_first(){
		MoveToAction move_to_action = new MoveToAction(){
			@Override
			protected void begin() {
				setX(-width);
				setDuration(duration);
				super.begin();
			}
			
			@Override
			public boolean act(float delta) {
				return super.act(delta);
			}
			
			@Override
			protected void end(){
				Background.this.setX(width);
			}
		};
		return move_to_action;
	}
	
	private MoveToAction move(){
		MoveToAction move_to_action = new MoveToAction(){
			@Override
			protected void begin() {
				setX(-1*width );
				setDuration(2*duration);
				super.begin();
			}
			
			@Override
			public boolean act(float delta) {
				return super.act(delta);
			}
			
			@Override
			protected void end() {
				Background.this.setX(width);
			}
		};
		return move_to_action;
	}
}
