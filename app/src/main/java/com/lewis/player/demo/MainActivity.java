package com.lewis.player.demo;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import com.lewis.player.demo.adapter.MyFragmentPagerAdapter;
import com.lewis.player.demo.contact.TypeListContact;
import com.lewis.player.demo.entity.TypeModel;
import com.lewis.player.demo.presenter.TypePresent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements TypeListContact.TypeView {

    private TabLayout tab;
    private ViewPager viewpager;
    private List<DemoProcessHomeKeyFragenment> fragmentList;
    private List<String> titleList;
    private TypePresent typePresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 在Activity：
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(listener)
                .start();
        initView();
        typePresent = new TypePresent(this);
        typePresent.getTypeListData();
    }
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 100) {
                // TODO ...
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 100) {
                AndPermission.defaultSettingDialog(MainActivity.this, 400)
                        .setTitle("权限申请失败")
                        .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };
    private void initView() {
        fragmentList=new ArrayList<>();
        titleList=new ArrayList<>();
        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void setData(TypeModel videoModel) {
          titleList.addAll(videoModel.data);
        for (int i = 0; i < videoModel.data.size(); i++) {
            fragmentList.add(new DemoProcessHomeKeyFragenment(videoModel.data.get(i)));
        }

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList,titleList);
        viewpager.setAdapter(fragmentPagerAdapter);
        tab.setupWithViewPager(viewpager);
//设置标签的显示类型(TabLayout.MODE_FIXED参数为平均地填充。
        tab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
