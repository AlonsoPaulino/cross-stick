package com.alonso.crossstick.manager

import com.alonso.crossstick.*
import com.badlogic.gdx.assets.AssetManager

/**
 * @author Luis Alonso Paulino Flores <alonsopf1@gmail.com>
 *
 */
class ResourcesLoader(val assetManager: AssetManager) {

    val TEXTURES = arrayListOf(
            BACKGROUND_CIAN,
            BACKGROUND_BRIDGE,
            BACKGROUND_NIGHT,
            BACKGROUND_MORNING,
            BACKGROUND_SHOP,
            BACKGROUND_BLUE,
            BACKGROUND_RED,
            BACKGROUND_CLOUDS_1,
            BACKGROUND_CLOUDS_2,
            BACKGROUND_TRANSPARENT,
            BACKGROUND_SCORE,
            TEXT_GAME,
            TEXT_GAME_OVER,
            ITEM_COIN,
            ITEM_COIN_BAG,
            ITEM_PRICE_25,
            ITEM_PRICE_75,
            ITEM_PRICE_150,
            ITEM_PRICE_250,
            ITEM_PRICE_300,
            ITEM_PRICE_400,
            ITEM_PRICE_500
    )

    fun load(update: (complete: Boolean) -> Unit) {

    }
}