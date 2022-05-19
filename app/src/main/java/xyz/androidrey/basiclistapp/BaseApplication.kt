package xyz.androidrey.basiclistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {

        private var appInstance: BaseApplication? = null

        val instance: BaseApplication
            @Synchronized get() {
                if (appInstance == null) {
                    appInstance =
                        BaseApplication()
                }
                return appInstance!!
            }
    }

    override fun onCreate() {
        appInstance = this
        super.onCreate()
    }
}