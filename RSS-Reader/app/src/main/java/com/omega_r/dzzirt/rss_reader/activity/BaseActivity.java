package com.omega_r.dzzirt.rss_reader.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.app.dzzirt.rss_reader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.09.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    protected boolean needToShowActionBar() {
        return true;
    }

    protected boolean needToShowBackButton() {
        return true;
    }

    protected abstract String getToolbarTitle();

    private void init() {
        ButterKnife.bind(this);
        initActionBar();
    }

    private void initActionBar() {
        if (needToShowActionBar() && mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        if (needToShowBackButton()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(getToolbarTitle());
            }
        }
    }

    @CallSuper
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    protected void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
