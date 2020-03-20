# BioXLib
Androidx Biometric Prompt Library

A simple AndroidX library so you can add a biometric login to your app.

        BioXLogin.BioXBuilder(this)
            .setTitle("BioMetric Login")
            .setDescription("Description here")
            .setNegativeButtonText("OK")
            .build().bioMet()
