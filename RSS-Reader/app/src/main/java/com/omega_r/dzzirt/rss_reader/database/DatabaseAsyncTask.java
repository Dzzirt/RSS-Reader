package com.omega_r.dzzirt.rss_reader.database;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.omega_r.dzzirt.rss_reader.model.DatabaseProvider;

import java.sql.SQLException;

/**
 * Created by nikita on 21.09.17.
 */

public abstract class DatabaseAsyncTask<T> extends AsyncTask<Void, Void, DatabaseAsyncTask.DatabaseResult<T>> {

    private static final String TAG = DatabaseAsyncTask.class.getSimpleName();

    @Nullable
    private DatabaseProvider.Callback<T> mCallback;

    public DatabaseAsyncTask(@Nullable DatabaseProvider.Callback<T> callback) {
        mCallback = callback;
        execute();
    }

    @Override
    protected DatabaseResult<T> doInBackground(Void... params) {
        try {
            return new DatabaseResult<>(true, runTask());
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            return new DatabaseResult<>(false, null);
        }
    }

    @Override
    protected void onPostExecute(DatabaseResult<T> result) {
        if (mCallback == null) return;

        if (result.isSuccess) {
            mCallback.onDataFetched(result.data);
        } else {
            mCallback.onError();
        }
    }

    protected abstract T runTask() throws SQLException;

    protected static class DatabaseResult<T> {
        boolean isSuccess;
        T data;

        DatabaseResult(boolean isSuccess, T data) {
            this.isSuccess = isSuccess;
            this.data = data;
        }

    }


}