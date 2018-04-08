package com.lewis.player.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.nicevideoplayer.NiceVideoPlayer;
import com.lewis.nicevideoplayer.NiceVideoPlayerManager;
import com.lewis.player.R;
import com.lewis.player.demo.adapter.VideoAdapter;
import com.lewis.player.demo.adapter.holder.VideoViewHolder;
import com.lewis.player.demo.base.CompatHomeKeyFragment;
import com.lewis.player.demo.contact.VideoListContact;
import com.lewis.player.demo.entity.VideoModel;
import com.lewis.player.demo.presenter.VideoListPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoJianjun on 2017/7/7.
 * 如果你需要在播放的时候按下Home键能暂停，回调此Fragment又继续的话，需要继承自CompatHomeKeyFragment
 */
@SuppressLint("ValidFragment")
public class DemoProcessHomeKeyFragenment extends CompatHomeKeyFragment implements VideoListContact.Videoview {

    private RecyclerView mRecyclerView;
    private  String type;
    private VideoListPresent videoListPresent;
    private List<VideoModel.Video> videoList;
    private VideoAdapter adapter;

    public DemoProcessHomeKeyFragenment(String type) {
      this.type=type;
        videoList=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        videoListPresent = new VideoListPresent(this);
        videoListPresent.getVideoListData(type);
        adapter = new VideoAdapter(getActivity(),videoList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((VideoViewHolder) holder).mVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });
    }

    @Override
    public void setData(VideoModel videoModel) {
       videoList.addAll(videoModel.data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
