package com.ltt.jetpack.gank

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.ltt.jetpack.data.base.BaseActivity
import com.ltt.jetpack.gank.ui.fragment.home.HomeFragment
import com.ltt.jetpack.gank.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override var layoutId = R.layout.activity_main

    private var lastExitTime: Long = 0
    private var mCurrentFragment: Fragment? = null
    private var index = 0
    private var fragmentTag: String? = null
    private val fragmentNames = arrayOf(
        HomeFragment::class.java.name, HomeFragment::class.java.name, HomeFragment::class.java.name,
        HomeFragment::class.java.name, HomeFragment::class.java.name
    )
    private val bottomTitles = arrayOf(
        R.string.home,
        R.string.wechat,
        R.string.project,
        R.string.navigation,
        R.string.knowledge_tree
    )

    override fun initData() {
        setSupportActionBar(toolbar)
        initNavBottom()
        bottomNav()
    }

    override fun subscribeUi() {
    }


    private fun initNavBottom() {
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> index = 0
                    R.id.wechat -> index = 1
                    R.id.project -> index = 2
                    R.id.navigation -> index = 3
                    R.id.knowledge_tree -> index = 4
                }
                bottomNav()
                true
            }
        navigation_bottom.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

    private fun bottomNav() {
        toolbar.title = getString(
            if (index == 0) {
                R.string.app_name
            } else {
                bottomTitles[index]
            }
        )
        fragmentTag = fragmentNames[index]
        val fragment = getFragmentByTag(fragmentTag!!)
        showFragment(mCurrentFragment, fragment, fragmentTag!!)
    }

    private fun getFragmentByTag(name: String): Fragment {
        var fragment = supportFragmentManager.findFragmentByTag(name)
        fragment = if (fragment != null) {
            return fragment
        } else {
            try {
                Class.forName(name).newInstance() as Fragment
            } catch (e: Exception) {
                HomeFragment()
            }
        }
        return fragment
    }

    private fun showFragment(from: Fragment?, to: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        if (from == null) {
            if (to.isAdded) {
                transaction.show(to)
            } else {
                transaction.add(R.id.container, to, tag)
            }
        } else {
            if (to.isAdded) {
                transaction.hide(from).show(to)
            } else {
                transaction.hide(from).add(R.id.container, to, tag)
            }
        }
        transaction.commit()
        mCurrentFragment = to
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastExitTime > 1500) {
                ToastUtils.show(getString(R.string.exit_hint))
                lastExitTime = currentTime
                return true
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }


}