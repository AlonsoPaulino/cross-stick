package UtilActorLocator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/** <u> METODOS PARA LA EDICION Y COLOCACION DE ACTORES EN ESCENA </u>: 
 * <p>
 * <h2> 1) Agregar la funcion {@link ActorLocator #locateActor locateActor(actor)} a algun actor. </h2>
 * <h2> 2) Seleccionar a dicho actor (touchdown): </h2>	
 * 		- Removerá todos sus listener (clear), impidiendo que tengan efecto acciones de juego. <br>
 * 		- Agregará un DragListener para desplazarlo libremente por el escenario. <br>
 * 		- Mostrará estadisticas adicionales. <br>
 * <h2> 3) Usar keyevents que afectarán las caracteristicas del actor seleccionado: </h2>
 * 	<b> - Keys.LEFT : </b> 	Desplaza 1px a la izquierda. <br>
 * 	<b>	- Keys.RIGHT: </b> 	Desplaza 1px a la derecha. <br>
 * 	<b>	- Keys.DOWN: </b>	Desplaza 1px hacia abajo. <br>
 * 	<b>	- Keys.UP: </b> 	Desplaza 1px hacia arriba. <br>
 * 	<b>	- Keys.A: </b>		Reduce el ancho en 1px. <br>
 * 	<b>	- Keys.D: </b>		Aumenta el ancho en 1px. <br>
 * 	<b>	- Keys.S: </b>		Reduce el alto 1px. <br>
 * 	<b>	- Keys.W: </b>		Aumenta el alto 1px. <br>
 * 	<b>	- Keys.Z: </b>		Atenuar (alpha = 0). <br>
 * 	<b>	- Keys.X: </b>		Desatenuar (alpha = 1). <br>
 *  <b> - Keys.J: </b> 		Desplaza 0.1px a la izquierda. <br>
 * 	<b>	- Keys.L: </b> 		Desplaza 0.1px a la derecha. <br>
 * 	<b>	- Keys.K: </b>		Desplaza 0.1px hacia abajo. <br>
 * 	<b>	- Keys.I: </b> 		Desplaza 0.1px hacia arriba. <br>
 * @param keyCode El valor de la tecla que fué presionado
 * @author canyouescape
 */	
public class ActorLocator extends Group {
	
	public static BitmapFont FNT_CODEFONT;
	public static Texture REG_VERDE;
	
	protected Actor locatable_;
	private RegionActor regIndicador;	
	private TextLabel txtEstadisticas1, txtEstadisticas2;	
	
	public ActorLocator() {
				
		FNT_CODEFONT = new BitmapFont(
				Gdx.files.internal("fonts/codefont.fnt"), 
				Gdx.files.internal("fonts/codefont.png"), 
				false);
		REG_VERDE = new Texture("resources/reg_verde.png");
		
		LabelStyle styCodefont_blanco = new LabelStyle(FNT_CODEFONT, Color.YELLOW);
		
		regIndicador = new RegionActor(REG_VERDE);
		regIndicador.setColor(1f, 1f, 1f, 0f);
		regIndicador.setTouchable(Touchable.disabled);		
		addActor(regIndicador);
		
		txtEstadisticas1 = new TextLabel("", styCodefont_blanco, 10, 60, 0.5f);
		addActor(txtEstadisticas1);		
		
		txtEstadisticas2 = new TextLabel("", styCodefont_blanco, 300, 60, 0.5f);
		addActor(txtEstadisticas2);
	}
	
	/** Ubica un actor en el escenario removiendo sus listeners y agregandole un {@link DragListener} para
	 * que sea facilmente desplazable. Cada nuevo <b>TouchDown</b> seleccionará un diferente actor localizable. */
	public void locateActor(final Actor actor) {
		
		actor.clearActions();	// anular listeners y acciones
		actor.clearListeners();
		actor.addListener(new DragListener() {
			float iniX, iniY;
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {				
				locatable_ = actor;
				iniX = x;
				iniY = y;
				actualizarEstadisticas();
				actor.getParent().addActor(regIndicador);		// NEW !!
				regIndicador.setBounds(locatable_.getX(), locatable_.getY(), locatable_.getWidth(), locatable_.getHeight());
				regIndicador.toFront();
				regIndicador.setColor(0.5f, 1f, 0.5f, 0.5f);
				regIndicador.addAction(Actions.fadeOut(0.1f));
				return super.touchDown(event, x, y, pointer, button);
			}
			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				actor.setPosition(actor.getX()+x-iniX, actor.getY()+y-iniY);
				actualizarEstadisticas();				
				super.drag(event, x, y, pointer);
			}
		});
	}
	
	/** Evalúa la tecla presionada, la cual modificará los parámetros del actor localizable actual según el
	 * código de tecla. 
	 * @param keyCode El código de teclado presionado.*/
	public void evaluarTeclado(int keyCode) {
		
		if (locatable_ == null) return;
		
		switch (keyCode) {
		case Keys.LEFT:		locatable_.moveBy(-1, 0); break;
		case Keys.RIGHT:	locatable_.moveBy(+1, 0); break;
		case Keys.DOWN:		locatable_.moveBy(0, -1); break;
		case Keys.UP:		locatable_.moveBy(0, +1); break;		
		case Keys.A:		locatable_.setSize(locatable_.getWidth()-1, locatable_.getHeight()); break;
		case Keys.D:		locatable_.setSize(locatable_.getWidth()+1, locatable_.getHeight()); break;
		case Keys.S:		locatable_.setSize(locatable_.getWidth(), locatable_.getHeight()-1); break;
		case Keys.W:		locatable_.setSize(locatable_.getWidth(), locatable_.getHeight()+1); break;		
		case Keys.Z:		locatable_.setColor(1f, 1f, 1f, 0f); break;
		case Keys.X:		locatable_.setColor(1f, 1f, 1f, 1f); break;
		case Keys.J:	locatable_.moveBy(-0.1f, 0); break;
		case Keys.L:	locatable_.moveBy(+0.1f, 0); break;
		case Keys.K:	locatable_.moveBy(0, -0.1f); break;
		case Keys.I:	locatable_.moveBy(0, +0.1f); break;
		
		case Keys.F:		locatable_.setSize(locatable_.getWidth()-50, locatable_.getHeight()); break;
		case Keys.H:		locatable_.setSize(locatable_.getWidth()+50, locatable_.getHeight()); break;
		case Keys.G:		locatable_.setSize(locatable_.getWidth(), locatable_.getHeight()-50); break;
		case Keys.T:		locatable_.setSize(locatable_.getWidth(), locatable_.getHeight()+50); break;
		default: break;
		}
		
		actualizarEstadisticas();
	}
	
	/** Actualiza las etiquetas que muestran las estadística del actor localizable */
	private void actualizarEstadisticas() {
		
		txtEstadisticas1.setText("x = "+locatable_.getX() + "\ny = "+locatable_.getY());
		txtEstadisticas2.setText("width = "+locatable_.getWidth() + "\nheight = "+locatable_.getHeight());
		txtEstadisticas1.toFront();
		txtEstadisticas2.toFront();
	}
}
