package com.ltt.jetpack.gank

import android.content.Context
import com.ltt.jetpack.data.BaseApplication

/**
 * @Author liutiantian
 * @Date 2021/3/29-17:38
 * @Email 772165619@qq.com
 */
class App:BaseApplication() {
    companion object{
        lateinit var instance:Context
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}