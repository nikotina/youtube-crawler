package com.seventhnode.youtubecrawler.jobs.Message;

public class DownloadProgressMessage {

        private String videoId;
        private String state;
        private float progress;

        public DownloadProgressMessage(String videoId)
        {
            this.videoId = videoId;
        }

        public String getVideoIde() {
            return videoId;
        }

        public String getState() {
            return state;
        }

        public float getProgress() {
            return progress;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setProgress(float progress) {
            this.progress = progress;
        }
}
