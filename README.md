# BioXLib
Androidx Biometric Prompt Library

A simple AndroidX library so you can add a biometric login to your app.

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Add the dependency to build.gradle(app) module:

	dependencies {
	        implementation 'com.github.Txagg1992:BioXLib:0.1.0'
	}
        
In the onCreate of your activity:

        BioXLogin.BioXBuilder(this)
            .setTitle("BioMetric Login")
            .setDescription("Description here")
            .setNegativeButtonText("OK")
            .build().bioMet()
