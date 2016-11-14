package me.terge.module.xposed;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import me.terge.module.xposed.bean.AdConfig;

/**
 * Created by terge on 16-10-17.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(AdConfig.class);
        AVOSCloud.initialize(this,"1bvNXVwgSkiY0wx2a7sENMQk-gzGzoHsz","9n9bPY9qI2tVA5E5ghho3Lmh");

    }
}
