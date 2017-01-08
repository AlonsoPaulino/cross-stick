package Model;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.StickHero;
import com.mygdx.game.actors.Button;
import com.mygdx.game.util.Util;

public class Character extends Button {
	
	private TextureRegion price_character;
	private int price;
	private StickHero my_game;
	private boolean state;
	private int index;
	
	public Character(StickHero my_game, float x, float y, int index){
		super(my_game.resources.texture_character[index], x, y);
		this.my_game = my_game;
		this.index = index;
		this.setWidth(getTexture().getWidth());
		this.setHeight(getTexture().getHeight());
		this.setOrigin(getWidth()/2, getHeight()/2);
		if(index != 0)this.price_character = new TextureRegion(my_game.resources.texture_prices[index]);
		else price_character = null;
		this.price = Util.PRICES[index];
	}
	
	public TextureRegion getTextureRegion(){
		return texture_region;
	}
	
	public Texture getTexture(){
		return texture_region.getTexture();
	}
	
	public String getPrice(){
		return String.valueOf(this.price);
	}
	
	public void setState(boolean state){
		this.state = state;
		if(!state){
			texture_region.setTexture(my_game.resources.texture_block_character);
		}else{
			texture_region.setTexture(my_game.resources.texture_character[index]);
		}
	}
	
	public boolean getState(){
		return this.state;
	}
	
	@Override
	public void executeAction() {
		if(!state){
			if(my_game.updateCoins(price)){
				this.setState(true);
				my_game.updateCharacter(index);
			}
		}else{
			my_game.getPlayer().setCharacter(index);
		}
		super.executeAction();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(texture_region, getX(), getY(), getOriginX(), getOriginY(),
				   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		if(!state){
			TextureRegion texture = this.price_character;
			batch.draw(texture, getX() + getWidth() - price_character.getRegionWidth(), 
					getY()+ getHeight()- price_character.getRegionHeight(), getOriginX(), getOriginY(),
					texture.getRegionWidth(), texture.getRegionHeight(), getScaleX(), getScaleY(), 
					getRotation());
		}
	}
	
	public int getIndex(){
		return this.index;
	}
}
