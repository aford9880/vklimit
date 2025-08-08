package com.example.vklimit

import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val targetPackage = "com.vk.tv"
    private val limitMillis = 2 * 60 * 60 * 1000L // 2 часа

    private var usedTimeToday = 0L
    private var lastCheckTime = 0L

    private lateinit var usageStatsManager: UsageStatsManager
    private var trackingJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        lastCheckTime = System.currentTimeMillis()
        startTracking()
    }

    private fun startTracking() {
        trackingJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                checkUsage()
                delay(5000)
            }
        }
    }

    private fun checkUsage() {
        val now = System.currentTimeMillis()

        // Сброс в полночь
        val calendar = Calendar.getInstance()
        if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0) {
            usedTimeToday = 0L
        }

        val events = usageStatsManager.queryEvents(lastCheckTime, now)
        val usageEvent = UsageEvents.Event()

        while (events.hasNextEvent()) {
            events.getNextEvent(usageEvent)
            if (usageEvent.packageName == targetPackage) {
                when (usageEvent.eventType) {
                    UsageEvents.Event.MOVE_TO_FOREGROUND -> {
                        lastCheckTime = usageEvent.timeStamp
                    }
                    UsageEvents.Event.MOVE_TO_BACKGROUND -> {
                        val delta = usageEvent.timeStamp - lastCheckTime
                        if (delta > 0) usedTimeToday += delta
                    }
                }
            }
        }

        lastCheckTime = now

        if (usedTimeToday >= limitMillis) {
            showBlockScreen()
        }
    }

    private fun showBlockScreen() {
        val intent = Intent(this, BlockActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        trackingJob?.cancel()
        super.onDestroy()
    }
}