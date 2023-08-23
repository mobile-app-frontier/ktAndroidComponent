package com.kt.basickit.pollingcenter

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

object PollingController: Application.ActivityLifecycleCallbacks {

    var aliveActivities: Int = 0

    fun init(application: Application) {
        Log.d("polling controller", "new polling controller created")
        application.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        Log.d("polling controller", "activity onActivityResumed ${activity.componentName.shortClassName}, currentAliveActivityCount: $aliveActivities")
        if(didAppearInForeground()) {
            Log.d("polling controller", "app is in foreground")
            PollingCenter.resumeTimer()
        }
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {
        Log.d("polling controller", "activity onActivityStopped ${activity.componentName.shortClassName}, currentAliveActivityCount: $aliveActivities")
        if(isAppInBackground()) {
            Log.d("polling controller", "app is in background")
            PollingCenter.cancelTimer()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        Log.d("polling controller", "activity onActivityDestroyed ${activity.componentName.shortClassName}")
    }
}

private fun PollingController.isAppInBackground(): Boolean {
    return --aliveActivities == 0
}

private fun PollingController.didAppearInForeground(): Boolean {
    return ++aliveActivities == 1
}
