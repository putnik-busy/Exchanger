package com.demo.justapp.exchanger.presentation.rates.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.application.AppInjector
import com.demo.justapp.exchanger.di.application.HasComponent
import com.demo.justapp.exchanger.di.data.DataComponent
import com.demo.justapp.exchanger.di.data.DataInjector
import com.demo.justapp.exchanger.exts.addFragment
import com.demo.justapp.exchanger.presentation.rates.fragment.RatesFragment

/**
 * Активити курса валют
 *
 * @author Sergey Rodionov
 */
class RatesActivity : AppCompatActivity(), HasComponent<DataComponent> {

    private lateinit var mDataInjector: DataInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        mDataInjector = AppInjector.getAppComponent().getDataInjector()
        getComponent().inject(this)
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
        return mDataInjector.getDataComponent()
    }

    private fun initFragment() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            addFragment(RatesFragment.newInstance(), R.id.container, RatesFragment.TAG)
        }
    }

    private fun setupToolBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            setTitle(R.string.app_name)
        }
    }

}