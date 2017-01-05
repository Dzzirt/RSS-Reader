package com.app.dzzirt.rss_reader.common;

import android.content.Context;

import java.io.File;

/**
 * Created by Dzzirt on 05.01.2017.
 */

public class FileManager {
    private Context m_context;

    public FileManager(Context context) {
        m_context = context;
    }

    public File getInternalStorageFile(String filename) {
        return  new File(m_context.getFilesDir(), filename);
    }
}
