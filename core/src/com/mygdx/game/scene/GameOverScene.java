package com.mygdx.game.scene;
import Model.Player;
import UtilActorLocator.TextLabel;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.StickHero;
import com.mygdx.game.actors.Background;
import com.mygdx.game.actors.Button;
import com.mygdx.game.actors.Label;

public class GameOverScene extends GenericScreen{

	private StickHero my_game;
	private Background backgound;
	private Player player;
	
	BitmapFont bitmap_font_1;
	
	private Label game_over_label, section_points_label;
	
	private TextLabel score_string_label;
	private TextLabel best_string_label;
	private TextLabel score_points_label;
	private TextLabel best_points_label;
	
	private Button replay_button;
	private Button share_button;
	private Button home_button;
	private Button rate_us_button;
	
	
	public GameOverScene(final StickHero my_game){
		this.my_game = my_game;
		this.backgound = new Background(my_game, my_game.resources.texture_background_color, 0);
		backgound.setSize(560, 970);
		this.stage.addActor(backgound);
		this.player = my_game.getPlayer();
		initComponents();
	}

	public void initComponents(){

		game_over_label = new Label(my_game.resources.texture_game_over_label, 41.5f, 775);
		section_points_label = new Label(my_game.resources.texture_white_section, 45f, 460f);
		
		score_string_label = new TextLabel("SCORE", my_game.resources.label_style_letters ,229.85f, 669.35f, 0.35f, 0.35f);
		best_string_label = new TextLabel("BEST", my_game.resources.label_style_letters, 241.85f, 553.85f, 0.35f,0.35f);
		
		score_points_label = new TextLabel("0", my_game.resources.label_style_numbers,0.95f, 0.95f);
		score_points_label.setY(620f); score_points_label.setX(270 - score_points_label.getWidth()/2);
		
		best_points_label = new TextLabel("0", my_game.resources.label_style_numbers, 0.95f, 0.95f);
		best_points_label.setY(490); best_points_label.setX(270 - best_points_label.getWidth()/2);
		
		Integer score_actual = player.getPoints();
		String value = (my_game.getManager().getPreferences("best"));
		
		if(value.equals("false")){
			try {
				my_game.getManager().addPreferences("best", String.valueOf(score_actual));
			}catch (Exception e) {
				e.printStackTrace();
			}
			score_points_label.setText(player.toString());
		}else{
			int best = Integer.parseInt(value);
			if(score_actual > best){
				score_string_label.setText("NEW BEST!");
				score_points_label.setText(player.toString());
				best_points_label.setText(player.toString());
				try {
					my_game.manager.addPreferences("best", player.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				score_string_label.setText("SCORE");
				score_points_label.setText(player.toString());
				best_points_label.setText(String.valueOf(best));
			}
		}
		
		replay_button = new Button(my_game.resources.texture_replay_button, 0, 249){
			@Override
			public void executeAction() {
				my_game.change_to_game();
				super.executeAction();
			}
		};

		rate_us_button = new Button(my_game.resources.texture_rate_us_button, 75.5f, 133.5f){
			@Override
			public void executeAction(){
				my_game.rateUs();
			}
		};
		
		share_button = new Button(my_game.resources.texture_share_button, 225, 133.5f){
			@Override
			public void executeAction() {
				my_game.share();
				super.executeAction();
			}
		};
		
		home_button = new Button(my_game.resources.texture_home_button, 370, 133.5f){
			@Override
			public void executeAction() {
				//my_game.change_to_main_screen();
				my_game.change_to_main_screen();
				super.executeAction();
			}
		};
		
		stage.addActor(section_points_label);
		stage.addActor(score_string_label);
		stage.addActor(score_points_label);
		stage.addActor(best_string_label);
		stage.addActor(best_points_label);
		stage.addActor(game_over_label);
		stage.addActor(replay_button);
		stage.addActor(home_button);
		stage.addActor(share_button);
		stage.addActor(rate_us_button);
		
		if(!player.toString().equals("0"))
			this.my_game.addTegaloPoints(player.toString());
		
		player.resetPoints();
	}
}
