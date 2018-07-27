package com.demo.justapp.exchanger.presentation.rates.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.di.data.DataComponent;
import com.demo.justapp.exchanger.models.local.Rate;
import com.demo.justapp.exchanger.presentation.base.BaseFragment;
import com.demo.justapp.exchanger.presentation.rates.adapter.RatesAdapter;
import com.demo.justapp.exchanger.presentation.rates.adapter.RecyclerViewItemListener;
import com.demo.justapp.exchanger.presentation.rates.presenter.RatesPresenter;
import com.demo.justapp.exchanger.presentation.rates.view.RatesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Фрагмент курсов валют
 *
 * @author Sergey Rodionov
 */
public class RatesFragment extends BaseFragment implements RatesView, RecyclerViewItemListener {

    public static final String TAG = "RatesFragment";
    private RecyclerView mRecyclerView;
    private TextView mEmptyTextView;
    private ContentLoadingProgressBar mContentProgressBar;
    private RatesAdapter mRatesAdapter;

    @Inject
    @InjectPresenter
    RatesPresenter mRatesPresenter;

    /**
     * Создает инстанс {@link RatesFragment}
     *
     * @return инстанс {@link RatesFragment}
     */
    @NonNull
    public static RatesFragment newInstance() {
        return new RatesFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getComponent(DataComponent.class).inject(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exchanger, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        initViews(view);
        prepareAdapter();
        mRatesPresenter.attachView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mRatesPresenter.detachView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showProgress(boolean loading) {
        mContentProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    /**
     * {@inheritDoc}
     *
     * @param list
     */
    @Override
    public void showRates(@NonNull List<Rate> list) {
        mRatesAdapter.addRates(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStub() {
        mEmptyTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showErrorMessage(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, int viewType) {
        Rate rate = mRatesAdapter.getRates().get(adapterPosition);
    }

    /**
     * Провайдим презентер для Moxy
     *
     * @return презентер {@link RatesPresenter}
     */
    @ProvidePresenter
    public RatesPresenter provideFeedPresenter() {
        return mRatesPresenter;
    }

    private void initViews(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mEmptyTextView = view.findViewById(R.id.empty_text_view);
        mContentProgressBar = view.findViewById(R.id.loading_progress_bar);
    }

    private void prepareAdapter() {
        mRatesAdapter = new RatesAdapter(this);
        mRecyclerView.setAdapter(mRatesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
