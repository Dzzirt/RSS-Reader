package com.app.dzzirt.rss_reader.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dzzirt on 04.01.2017.
 */

public class NetUtils {
    public static boolean download(OutputStream outputStream, String source, int timeout) {
        InputStream inputStream = null;
        try {
            URL url = new URL(source);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            urlConnection.setReadTimeout(timeout);
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0)
            {
                outputStream.write(buffer, 0, bufferLength);
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return true;
    }
}
