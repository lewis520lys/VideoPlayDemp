package com.lewis.player.demo.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class BaseApplication extends Application {
    /*
    * 初始化TAG
    * */
    private  static String TAG=BaseApplication.class.getName();

    /*Activity堆*/
    private Stack<Activity> activityStack = new Stack<Activity>();
    // 提供一个单件
    private static BaseApplication application;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //Avoiding the 64K Limit

    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        printAppParameter();

    }
    public static Context getContext()
    {
        return application;
    }

    public static BaseApplication getInstance()
    {
        return application;
    }
    /*打印出一些app的参数*/
    private void printAppParameter(){
       // LogUtils.d(TAG, "OS : "+ Build.VERSION.RELEASE + " ( " + Build.VERSION.SDK_INT + " )");
        //DeviceMgr.ScrSize realSize = DeviceMgr.getScreenRealSize(this);
        //LogUtils.d(TAG, "Screen Size: " + realSize.w + " X " + realSize.h);

    }

    public void addActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    public void removeActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }

    //获取最后一个Activity
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回寨内Activity的总数
    public int howManyActivities() {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    //关闭所有Activity
    public void finishAActivities() {
        for (int i = 0, size = activityStack.size()-1; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }
}