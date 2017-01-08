package com.mygdx.game.actors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.util.GUIResource;

public class Button extends GUIResource{
	
	public void executeAction(){};
	
	public Button(Texture texture, float x, float y) {
		super(texture,x,y);
		initButton();
	}
	
	public Button(Texture texture, float x, float y, boolean visibility) {
		super(texture,x,y, visibility);
		initButton(); 
	}
	
	public Button(Texture texture, float width, float height, float x, float y){
		super(texture, width, height, x, y);
		initButton();
	}
	
	public Button(Texture texture, float width, float height, float x, float y, boolean visibility){
		super(texture, width, height, x, y, visibility);
		initButton();
	}

	public void initButton(){
		this.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Button.this.addAction(new ScaleToAction(){
					@Override
					protected void begin() {
						super.begin();
						setScale(1.1f);
						setDuration(0.1f);						
					}
				});
				return super.touchDown(event, x, y, pointer, button);
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Button.this.addAction(
					new SequenceAction(
							new ScaleToAction(){
								@Override
								protected void begin() {
									super.begin();
									setScale(1f);
									setDuration(0.1f);		
								}
							},
							new FloatAction(){
								@Override
								protected void begin() {
									super.begin();
									setDuration(0.05f);		
								}
								
								@Override
								protected void end() {
									executeAction();
								}
							})
				);			
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}
}

