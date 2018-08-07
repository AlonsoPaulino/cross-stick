package com.alonso.crossstick.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Manager {

	private Preferences preferences;
	
	public Manager(){
		preferences = Gdx.app.getPreferences("My Preferences");
	}
	
	public void addPreferences(String name, String value){
		preferences.putString(name, value);
		preferences.flush();
	}
	
	public String getPreferences(String name){
		return preferences.getString(name, "false");
	}
}
