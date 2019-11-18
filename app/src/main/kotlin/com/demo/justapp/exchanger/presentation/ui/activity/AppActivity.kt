package com.demo.justapp.exchanger.presentation.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.application.ApplicationInjector
import com.demo.justapp.exchanger.di.application.HasComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesInjector
import com.demo.justapp.exchanger.presentation.ui.extensions.addFragment
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyRatesFragment


class AppActivity : AppCompatActivity(), HasComponent<CurrenciesComponent> {

    private lateinit var currenciesInjector: CurrenciesInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        applySystemUiVisibility()
        currenciesInjector = ApplicationInjector.applicationComponent.currenciesInjector
        getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
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

    private fun applySystemUiVisibility() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }

                statusBarColor = ContextCompat.getColor(context, R.color.black_20)
                navigationBarColor = ContextCompat.getColor(context, R.color.white_50)
            }
        }
    }

}