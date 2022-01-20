package com.seventhnode.youtubecrawler.util;

public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}
