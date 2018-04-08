package com.lewis.player.demo.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lewis.nicevideoplayer.NiceVideoPlayer;
import com.lewis.nicevideoplayer.TxVideoPlayerController;
import com.lewis.player.R;
import com.lewis.player.demo.entity.VideoModel;

/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder {

    public TxVideoPlayerController mController;
    public NiceVideoPlayer mVideoPlayer;

    public VideoViewHolder(View itemView) {
        super(itemView);
        mVideoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.nice_video_player);
        // 将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
        params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
        params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
        mVideoPlayer.setLayoutParams(params);
    }

    public void setController(TxVideoPlayerController controller) {
        mController = controller;
        mVideoPlayer.setController(mController);
    }

    public void bindData(VideoModel.Video video) {
        mController.setTitle(video.title);
        mController.setLenght(video.length);
        Glide.with(itemView.getContext())
                .load(video.imageUrl)
                .placeholder(R.drawable.img_default)
                .crossFade()
                .into(mController.imageView());
        mVideoPlayer.setUp(video.videoUrl, null);
    }
}
