package com.demo.justapp.exchanger.presentation.rates.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.application.AppInjector
import com.demo.justapp.exchanger.di.application.HasComponent
import com.demo.justapp.exchanger.di.data.DataComponent
import com.demo.justapp.exchanger.di.data.DataInjector
import com.demo.justapp.exchanger.presentation.rates.fragment.RatesFragment
import com.demo.justapp.exchanger.utils.ActivityUtils

/**
 * Активити курса валют
 *
 * @author Sergey Rodionov
 */
class RatesActivity : AppCompatActivity(),
        HasComponent<DataComponent> {

    private lateinit var mDataComponent: DataComponent
    private lateinit var mDataInjector: DataInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        mDataInjector = AppInjector.getAppComponent().getDataInjector()
        mDataComponent = mDataInjector.createDataComponent()
        mDataComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setupToolBar()
        initFragment()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            mDataInjector.clearDataComponent()
        }
    }

    override fun getComponent(): DataComponent {
        return mDataComponent
    }

    private fun initFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment: Fragment? = fragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            ActivityUtils.addFragmentInActivity(fragmentManager, RatesFragment.newInstance(),
                    R.id.container, RatesFragment.TAG)
        }
    }

    private fun setupToolBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            supportActionBar!!.setTitle(R.string.app_name)
        }
    }

}