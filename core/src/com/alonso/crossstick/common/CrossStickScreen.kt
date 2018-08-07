package com.alonso.crossstick.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport

/**
 * @author Luis Alonso Paulino Flores <alonsopf1@gmailcom>
 *
 */

abstract class CrossStickScreen: Screen {

    val stage: Stage

    init {
        stage = Stage(StretchViewport(540.0f, 960.0f))
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        Gdx.app.log(javaClass.simpleName, "reize w: $width h: $height")

        stage.viewport.update(width, height, true)
    }

    override fun resume() {
        Gdx.app.log(javaClass.simpleName, "resume")
    }

    override fun pause() {
        Gdx.app.log(javaClass.simpleName, "pause")
    }

    override fun hide() {
        Gdx.app.log(javaClass.simpleName, "hide")
    }

    override fun dispose() {
        Gdx.app.log(javaClass.simpleName, "dispose")
    }
}