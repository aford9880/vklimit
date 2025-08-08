package com.example.vklimit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.widget.TextView

class BlockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        val textView = TextView(this)
        textView.text = "Время истекло"
        textView.textSize = 48f
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView.setPadding(50, 50, 50, 50)

        setContentView(textView)
    }

    override fun onBackPressed() {
        // Блокируем возврат назад
    }
}