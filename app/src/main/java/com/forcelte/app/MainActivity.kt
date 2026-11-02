package com.forcelte.app

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        findViewById<MaterialButton>(R.id.btn_dial_radio).setOnClickListener {
            dialCode("*#*#4636#*#*")
        }
    }

    private fun dialCode(code: String) {
        try {
            startActivity(
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:${Uri.encode(code)}"))
            )
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, R.string.unavailable, Toast.LENGTH_LONG).show()
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
