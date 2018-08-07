package com.alonso.crossstick.common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor

/**
 * @author Luis Alonso Paulino Flores <alonsopf1@gmailcom>
 *
 */

class CrossStickActor: Actor {

    val textureRegion: TextureRegion

    constructor(textureRegion: TextureRegion) {
        this.textureRegion = textureRegion
        setSize(textureRegion.regionWidth.toFloat(), textureRegion.regionHeight.toFloat())
    }

    constructor(texture: Texture) : this(TextureRegion(texture)) {
        setSize(texture.width.toFloat(), texture.height.toFloat())
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.setColor(color.r, color.g, color.b, color.a * parentAlpha)
        batch?.draw(textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation)

        super.draw(batch, parentAlpha)
    }
}