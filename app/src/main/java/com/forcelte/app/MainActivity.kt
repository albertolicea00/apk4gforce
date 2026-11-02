package com.forcelte.app

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.btn_open_radio).setOnClickListener {
            if (!openRadioInfoMenu()) {
                Toast.makeText(this, R.string.unavailable, Toast.LENGTH_LONG).show()
            }
        }

        findViewById<TextView>(R.id.btn_info).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.txt_dial_code).setOnClickListener {
            try {
                startActivity(
                    Intent(Intent.ACTION_DIAL, Uri.parse("tel:${Uri.encode("*#*#4636#*#*")}"))
                )
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(this, R.string.unavailable, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openRadioInfoMenu(): Boolean {
        val candidates = listOf(
            ComponentName("com.android.phone", "com.android.phone.settings.RadioInfo"),
            ComponentName("com.android.phone", "com.android.phone.RadioInfo")
        )
        for (component in candidates) {
            try {
                startActivity(
                    Intent(Intent.ACTION_MAIN)
                        .setComponent(component)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                return true
            } catch (_: ActivityNotFoundException) {
            } catch (_: SecurityException) {
            }
        }
        return false
    }
}
