package com.lewis.player.demo.contact;


import com.lewis.player.demo.base.BasePresenter;
import com.lewis.player.demo.base.BaseView;
import com.lewis.player.demo.entity.VideoModel;

public class VideoListContact {
  public interface Videoview extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
        void setData(VideoModel videoModel);
    }

   public interface Videopresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getVideoListData(String type);
    }
}
