package com.curiousapps.bioxlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BioXLogin.BioXBuilder(this)
            .setTitle("BioMetric Login")
            .setDescription("Description here")
            .setNegativeButtonText("OK")
            .build().bioMet()
    }
}
