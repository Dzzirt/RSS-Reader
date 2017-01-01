package com.app.dzzirt.rss_reader.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class UpgradingDatabaseOpenHelper extends DaoMaster.OpenHelper {
    public UpgradingDatabaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public UpgradingDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        // Insert code for database upgrading
    }
}
