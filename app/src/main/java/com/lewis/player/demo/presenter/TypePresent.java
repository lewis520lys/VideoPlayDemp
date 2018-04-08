package com.lewis.player.demo.presenter;


import com.lewis.player.demo.base.BasePresenterImpl;
import com.lewis.player.demo.contact.TypeListContact;
import com.lewis.player.demo.entity.TypeModel;
import com.lewis.player.demo.http.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TypePresent extends BasePresenterImpl<TypeListContact.TypeView> implements TypeListContact.Typepresenter{
    public TypePresent(TypeListContact.TypeView view) {
        super(view);
    }





    @Override
    public void getTypeListData() {
        RetrofitFactory.getInstance()
                .getTypeListData()
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
                .subscribe(new Consumer<TypeModel>() {
                    @Override
                    public void accept(@NonNull TypeModel videoModel) throws Exception {
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
