package com.ziadsyahrul.submissionandroidexpertdicoding.utils

import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor @VisibleForTesting constructor(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    private class MainThreadExecutor : Executor {
        private val mainThreadhandler = android.os.Handler(Looper.getMainLooper())

        override fun execute(p0: Runnable) {
            mainThreadhandler.post(p0)
        }


    }
}