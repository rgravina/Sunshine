package com.example.android.sunshine

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    private lateinit var detail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detail = findViewById(R.id.tv_weather_data_detail)
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            detail.text = intent.getStringExtra(Intent.EXTRA_TEXT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_share) {
            ShareCompat.IntentBuilder
                    .from(this)
                    .setChooserTitle("Share weather")
                    .setType("text/plain")
                    .setText(detail.text)
                    .startChooser()

        }
        return super.onOptionsItemSelected(item)
    }
}
