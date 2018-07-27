package com.demo.justapp.exchanger.presentation.rates.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.di.application.HasComponent;
import com.demo.justapp.exchanger.di.data.DataComponent;
import com.demo.justapp.exchanger.di.data.DataInjector;
import com.demo.justapp.exchanger.presentation.rates.fragment.RatesFragment;
import com.demo.justapp.exchanger.utils.ActivityUtils;

/**
 * Активити курса валют
 *
 * @author Sergey Rodionov
 */
public class RatesActivity extends AppCompatActivity implements HasComponent<DataComponent> {

    private DataComponent mDataComponent;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDataComponent = DataInjector.createDataComponent();
        mDataComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        setupToolBar();
        initFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            DataInjector.clearDataModule();
        }
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment == null) {
            ActivityUtils.addFragmentInActivity(fragmentManager, RatesFragment.newInstance(),
                    R.id.container, RatesFragment.TAG);
        }
    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataComponent getComponent() {
        return mDataComponent;
    }
}
