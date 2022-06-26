package com.example.practicaltest.navigation

import android.content.Intent

object MainNavigation: DynamicFeature<Intent> {

    private const val MAIN = "com.example.practicaltest.app.ui.LoginActivity"

    override val dynamicStart: Intent?
        get() = MAIN.loadIntent()

}