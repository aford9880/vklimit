package com.example.vklimit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.widget.TextView
import android.widget.FrameLayout
import android.view.Gravity

class BlockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        val textView = TextView(this).apply {
            text = "Время истекло"
            textSize = 48f
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            setPadding(50, 50, 50, 50)
        }

        val frameLayout = FrameLayout(this).apply {
            addView(textView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            ))
        }

        setContentView(frameLayout)
    }

    override fun onBackPressed() {
        // Блокируем возврат назад
    }
}
