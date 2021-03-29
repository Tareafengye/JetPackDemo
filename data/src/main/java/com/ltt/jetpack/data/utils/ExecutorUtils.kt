package com.ltt.jetpack.data.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 * @Author liutiantian
 * @Date 2021/3/29-15:57
 * @Email 772165619@qq.com
 */
object ExecutorUtils {

    val DISK_IO = Executors.newSingleThreadExecutor()

    val NETWORK_IO = Executors.newFixedThreadPool(5)

    fun main_thread(runnable: Runnable, delay: Long = 0) {
        Handler(Looper.getMainLooper())
            .postDelayed(runnable, delay)
    }
}