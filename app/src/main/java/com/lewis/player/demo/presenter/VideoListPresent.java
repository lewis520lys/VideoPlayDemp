package com.lewis.player.demo.presenter;


import com.lewis.player.demo.base.BasePresenterImpl;
import com.lewis.player.demo.contact.VideoListContact;
import com.lewis.player.demo.entity.VideoModel;
import com.lewis.player.demo.http.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class VideoListPresent extends BasePresenterImpl<VideoListContact.Videoview> implements VideoListContact.Videopresenter{
    public VideoListPresent(VideoListContact.Videoview view) {
        super(view);
    }


    @Override
    public void getVideoListData(String type) {
        RetrofitFactory.getInstance()
                .getVideoListData(type)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
//                .map(new Function<TestBean, List<TestBean.StoriesBean>>() {
//                    @Override
//                    public List<TestBean.StoriesBean> apply(@NonNull TestBean testBean) throws Exception {
//                        return testBean.getStories();//转换数据
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoModel>() {
                    @Override
                    public void accept(@NonNull VideoModel videoModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setData(videoModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.dismissLoadingDialog();
//                        ExceptionHelper.handleException(throwable);
                    }
                });
    }
}
