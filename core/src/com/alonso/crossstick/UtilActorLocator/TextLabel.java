package com.alonso.crossstick.UtilActorLocator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/** Un tipo extendido de {@link Label} pero con constructores m�s �giles.
 * @author canyouescape
 */
public class TextLabel extends Label {

	LabelStyle style;
	
	public TextLabel(String text, LabelStyle style, float x, float y) {
		
		this (text, style, x, y, 1f, 1f);
	}
	
	public TextLabel(String text, LabelStyle style, float x, float y, float fontScale) {
		
		this (text, style, x, y, fontScale, fontScale);
	}
	
	public TextLabel(String text, LabelStyle style, float x, float y, float fontScaleX, float fontScaleY) {
		
		super (text, style);
		
		this.style = style;
		
		setPosition(x, y);
		setFontScale(fontScaleX, fontScaleY);
	}
	
	/** Permite configurar el color de la fuente */
	public void setFontColor(Color color) {
		
		style.fontColor = color;		
	}
	
	/** permite cambiar la fuente */
	public void setFont(BitmapFont font) {
		
		style.font = font;		
	}
}
