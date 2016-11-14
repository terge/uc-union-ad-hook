package me.terge.module.xposed.union;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import me.terge.module.xposed.consts.UConsts;

/**
 * Created by terge on 16-10-18.
 */

public class UnionBanner extends BaseAd{
    static {
        mInstance = new UnionBanner();
    }
    private static UnionBanner mInstance;
    private UnionBanner(){};
    public static UnionBanner instance(){
        return mInstance;
    }



    public void turnOff(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }


    public void showTrace(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }

    String adListnerClz = null;

    public void printCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        Log.i("terge",TAG+" printCallback");
        //获取AdListener的class
        if(adListnerClz == null){
            Log.i("terge",TAG+" hook setAdListener");
            XposedHelpers.findAndHookMethod("com.ucweb.union.ads.g", loadPackageParam.classLoader, UConsts.Union.METHOD.SET_AD_LISTENER, Object.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    adListnerClz = param.args[0].getClass().toString();
                    Log.d("terge","before - setAdAListener - listenerClz:"+adListnerClz);
                }
            });
        }else {
            Log.i("terge",TAG+" hook onAdError");
            XposedHelpers.findAndHookMethod(adListnerClz, loadPackageParam.classLoader, "onAdError", Object.class, Object.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("terge","before - onAdError - showTrace:",new Throwable());
                }
            });
        }
    }

    @Override
    public void hookSetListener(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }

    @Override
    public void hookSetPub(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }

    @Override
    public void hookLoad(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }

    @Override
    public void hookListenerCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) {

    }
}
