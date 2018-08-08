package com.alonso.crossstick.extensions

import com.alonso.crossstick.FONTS_PATH
import com.alonso.crossstick.TEXTURES_PATH
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas

/**
 * @author Luis Alonso Paulino Flores <alonsopf1@gmail.com>
 *
 */

fun AssetManager.loadTexture(resource: String) {
    val parameter = TextureLoader.TextureParameter()
    parameter.minFilter = Texture.TextureFilter.Linear
    parameter.magFilter = Texture.TextureFilter.Linear
    load(TEXTURES_PATH + resource, Texture::class.java, parameter)
}

fun AssetManager.loadTextureAtlas(resource: String) {
    load(TEXTURES_PATH + resource, TextureAtlas::class.java)
}

fun AssetManager.loadFont(resource: String) {
    val parameter = BitmapFontLoader.BitmapFontParameter()
    parameter.minFilter = Texture.TextureFilter.Linear
    parameter.magFilter = Texture.TextureFilter.Linear
    load(FONTS_PATH + resource, BitmapFont::class.java)
}