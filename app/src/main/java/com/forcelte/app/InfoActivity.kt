package com.forcelte.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<android.view.View>(R.id.btn_back).setOnClickListener { finish() }

        val info = packageManager.getPackageInfo(packageName, 0)
        findViewById<TextView>(R.id.txt_version).text =
            getString(R.string.info_version, info.versionName)

        findViewById<com.google.android.material.button.MaterialButton>(R.id.link_docs).setOnClickListener {
            openUrl("https://github.com/albertolicea00/apk4gforce/blob/main/documentation.md")
        }

        findViewById<TextView>(R.id.link_repo).setOnClickListener {
            openUrl("https://github.com/albertolicea00/apk4gforce")
        }

        findViewById<TextView>(R.id.link_privacy).setOnClickListener {
            openUrl("https://github.com/albertolicea00/apk4gforce/blob/main/PRIVACY_POLICY.md")
        }
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
