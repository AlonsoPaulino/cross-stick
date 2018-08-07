package com.alonso.crossstick;

import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.alonso.crossstick.game.StickHero;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.alonso.crossstick.R;

public class AndroidLauncher extends AndroidApplication{

	View gameView;
	StickHero my_game;
	RelativeLayout layout;

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		my_game = new StickHero(){
			@Override
			public void create() {
				super.create();
			}

			@Override
			public void rateUs() {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
						getContext().getPackageName().toString())));
				super.rateUs();
			}

			@Override
			public void addTegaloPoints(String points){

				if(isPackageInstalled("com.kodevian.tegalo")){
					JSONObject json = new JSONObject();
					String data = "";
					try{
						json.put("name","Cross Stick");
						json.put("token", "cd091a38-b32f-46bd-9796-b2a63591878a");
						json.put("points", points);
					}catch(Exception e){
						e.printStackTrace();
					}
					data= json.toString();
					Intent intent = new Intent("android.intent.action.MAIN");
					intent.setComponent(ComponentName.unflattenFromString("com.kodevian.tegalo/com.kodevian.tegalo.main.MainActivity"));
					intent.addCategory("android.intent.category.SEND");
					intent.setDataAndType(Uri.parse(data),"GAME");
					startActivity(intent);
				}
			}

			@Override
			public void share() {
				/*Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				String sharingText = "Find out playing this game! " +
						getString(R.string.app_url, getPackageName());
				sharingIntent.putExtra(Intent.EXTRA_TEXT, sharingText);
				startActivity(Intent.createChooser(sharingIntent, "Share using"));
				super.share();*/
			}
		};

		gameView = initializeForView(my_game);

		layout = new RelativeLayout(this);
		layout.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));

		RelativeLayout.LayoutParams layoutParamsGame = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParamsGame.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		layout.addView(gameView, layoutParamsGame);
		setContentView(layout);
	}

	@Override
	public void onBackPressed() {

		final AlertDialog.Builder alert = new AlertDialog.Builder(
				AndroidLauncher.this);

		alert.setTitle("Exit game");
		alert.setMessage("Are you sure to quit the game?");

		alert.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
										int whichButton) {
						finish();
					}
				});

		alert.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
										int whichButton) {

					}
				});

		alert.show();

	}

	public boolean isPackageInstalled(String packageName){
		PackageManager pm = this.getPackageManager();
		try {
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

}
