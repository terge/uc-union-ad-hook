package me.terge.module.xposed.union;

import android.app.Application;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import me.terge.module.xposed.bean.AdConfig;

/**
 * Created by terge on 16-10-17.
 */

public  abstract class BaseAd implements IADNHook {
    public final String TAG = getClass().getSimpleName();
    protected AdConfig mConfig;
    protected Application appContext;


    private BaseAd initContext(XC_LoadPackage.LoadPackageParam loadPackageParam){
        XposedHelpers.findAndHookMethod("android.app.Application", loadPackageParam.classLoader, "onCreate", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                appContext = (Application) param.thisObject;
                Log.e("terge","get appContext:"+appContext);

//                AVObject.registerSubclass(AdConfig.class);
//                AVOSCloud.initialize(appContext,"OWsIXz4Ysdz5FXA7ICWHAp3J-gzGzoHsz","GkTKw18rpNCTf0KFxQK7r1BN");
//                Config.instance().fetch(appContext, appContext.getPackageName(),ADN.ADMOB, AdType.BANNER);
            }

        });
        return this;
    }

    public void hook(XC_LoadPackage.LoadPackageParam loadPackageParam){
        try {
            hookLoad(loadPackageParam);
            hookSetPub(loadPackageParam);
            hookSetListener(loadPackageParam);
            hookListenerCallback(loadPackageParam);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




}
