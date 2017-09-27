package com.omega_r.dzzirt.rss_reader.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by nikita on 21.09.17.
 */

public class FileUtils {

    public static final String TEMP_FILENAME = "temp";

    public static File getTempFile() throws IOException {
        return File.createTempFile(TEMP_FILENAME, null);
    }

}
