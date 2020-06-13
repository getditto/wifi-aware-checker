package com.example.wifiawarechecker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.aware.WifiAwareManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hasFeatureTextView = findViewById<TextView>(R.id.has_feature_text_view)
        val hasSystemFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE)
        if (hasSystemFeature) {
            hasFeatureTextView.text = "WiFi Aware Available"
        } else {
            hasFeatureTextView.text = "WiFi Aware Unavailable"
        }
    }
}
