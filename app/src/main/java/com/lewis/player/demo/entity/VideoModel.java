package com.lewis.player.demo.entity;

import java.util.List;

public class VideoModel {
    public String msg;
    public List<Video> data;
    public class Video{
        public String title;
        public long length;
        public String imageUrl;
        public String videoUrl;
    }

}
