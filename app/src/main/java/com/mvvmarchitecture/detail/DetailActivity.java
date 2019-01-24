package com.mvvmarchitecture.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.mvvmarchitecture.R;
import com.mvvmarchitecture.apifactory.model.Repo;
import com.mvvmarchitecture.base.BaseActivity;
import com.mvvmarchitecture.base.ViewModelFactory;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    DetailViewModel detailViewModel;

    TextView repoNameTextView;
    TextView repoDescriptionTextView;
    TextView forksTextView;
    TextView starsTextView;

    @Override
    protected int layoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repoNameTextView = findViewById(R.id.tv_repo_name);
        repoDescriptionTextView = findViewById(R.id.tv_repo_description);
        forksTextView = findViewById(R.id.tv_forks);
        starsTextView = findViewById(R.id.tv_stars);

        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
        if (getIntent().getExtras().getSerializable("repo") != null)
            detailViewModel.setSelectedRepo((Repo) getIntent().getExtras().getSerializable("repo"));
        detailViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        detailViewModel.getSelectedRepo().observe(this, repo -> {
            if (repo != null) {
                repoNameTextView.setText(repo.name);
                repoDescriptionTextView.setText(repo.description);
                forksTextView.setText(String.valueOf(repo.forks));
                starsTextView.setText(String.valueOf(repo.stars));
            }
        });
    }
}
