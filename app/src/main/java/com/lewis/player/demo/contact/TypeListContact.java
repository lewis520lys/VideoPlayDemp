package com.lewis.player.demo.contact;


import com.lewis.player.demo.base.BasePresenter;
import com.lewis.player.demo.base.BaseView;
import com.lewis.player.demo.entity.TypeModel;

public class TypeListContact {
  public interface TypeView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
        void setData(TypeModel videoModel);
    }

   public interface Typepresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getTypeListData();
    }
}
