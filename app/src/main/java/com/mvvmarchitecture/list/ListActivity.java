package com.mvvmarchitecture.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mvvmarchitecture.R;
import com.mvvmarchitecture.apifactory.model.Repo;
import com.mvvmarchitecture.base.BaseActivity;
import com.mvvmarchitecture.base.ViewModelFactory;
import com.mvvmarchitecture.detail.DetailActivity;

import javax.inject.Inject;

public class ListActivity extends BaseActivity implements RepoSelectedListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private ListViewModel viewModel;

    RecyclerView listView;
    TextView errorTextView;
    View loadingView;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.recycler_view);
        errorTextView = findViewById(R.id.tv_error);
        loadingView = findViewById(R.id.loading_view);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listView.setAdapter(new RepoListAdapter(viewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(this));

        observableViewModel();
    }

    @Override
    public void onRepoSelected(Repo repo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("repo", repo);
        Intent in = new Intent(this, DetailActivity.class);
        in.putExtras(bundle);
        startActivity(in);
    }

    private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if (repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}