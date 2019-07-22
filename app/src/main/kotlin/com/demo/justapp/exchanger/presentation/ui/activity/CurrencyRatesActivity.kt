package com.demo.justapp.exchanger.presentation.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.application.ApplicationInjector
import com.demo.justapp.exchanger.di.application.HasComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesInjector
import com.demo.justapp.exchanger.extensions.addFragment
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyRatesFragment

class CurrencyRatesActivity : AppCompatActivity(), HasComponent<CurrenciesComponent> {

    private lateinit var currenciesInjector: CurrenciesInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        currenciesInjector = ApplicationInjector.applicationComponent.currenciesInjector
        getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setupToolBar()
        initFragment()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) currenciesInjector.clearDataComponent()
    }

    override fun getComponent(): CurrenciesComponent {
        return currenciesInjector.getDataComponent()
    }

    private fun initFragment() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            addFragment(CurrencyRatesFragment.newInstance(), R.id.container, CurrencyRatesFragment.TAG)
        }
    }

    private fun setupToolBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            setTitle(R.string.exchange_rates)
        }
    }

}