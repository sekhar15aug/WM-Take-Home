package com.wm.takehome.app

import android.app.Application
import com.wm.takehome.ui.util.InjectRepository

class WMApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        // should be able to avoid with dependency injection
        appContainer = AppContainer()
        InjectRepository.listRepository = appContainer.listRepository
    }
}