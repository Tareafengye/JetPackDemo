package com.ltt.jetpack.data.utils

/**
 * @Author liutiantian
 * @Date 2021/3/29-16:01
 * @Email 772165619@qq.com
 */
object Listeners {
    interface PermissionListener {
        fun onGranted()

        fun onDenied(permissions: List<String>)

        fun onShowReason()
    }
}