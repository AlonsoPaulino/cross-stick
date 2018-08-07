package com.alonso.crossstick

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import com.alonso.crossstick.game.CrossStick
import com.badlogic.gdx.backends.android.AndroidApplication

class CrossStickApplication: AndroidApplication() {

    lateinit var game: CrossStick

    lateinit var gameView: View
    lateinit var parentLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)

        game = object: CrossStick() {

            override fun rateUs() {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                super.rateUs()
            }
        }

        gameView = initializeForView(game)

        parentLayout = RelativeLayout(this)
        parentLayout.layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT)).apply {
            addRule(RelativeLayout.ALIGN_PARENT_TOP)
        }

        parentLayout.addView(gameView)

        setContentView(parentLayout)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure to quit CrossStick?")
            .setPositiveButton("Yes", { d, _ ->
                d.dismiss()
                finish()
            })
            .setNegativeButton("No", { d, _ ->
                d.dismiss()
            }).show()
    }
}