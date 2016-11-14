package me.terge.module.xposed.union;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by terge on 16-10-18.
 */

public class UnionInterstitial extends BaseAd{
    static {
        mInstance = new UnionInterstitial();
    }
    private static UnionInterstitial mInstance;
    private UnionInterstitial(){};
    public static UnionInterstitial instance(){
        return mInstance;
    }


    public void turnOff(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }


    public void showTrace(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {

    }


    public void printCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        String adListenerObjClz = "com.ltad.unionAd.UnionBanner$1";
//        Log.w("terge","hook setAdListener");
////        Class adListClz = loadPackageParam.classLoader.loadClass(UConsts.Union.CLZ.AD_LISTENER);
////        XposedHelpers.findAndHookMethod(UConsts.Union.CLZ.INTERSTITIAL, loadPackageParam.classLoader, "setAdListener",adListClz, new XC_MethodHook() {
//        Class adListClz = loadPackageParam.classLoader.loadClass("com.ucweb.union.ads.c");
//        XposedHelpers.findAndHookMethod("com.ucweb.union.ads.g", loadPackageParam.classLoader, "setAdListener",adListClz, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                String clz = param.args[0].getClass().toString();
//                Log.i("terge","union-ad-listener-class:"+clz);
//            }
//        });

        Log.w("terge","hook loadAd");
//        Class adRequstClz = loadPackageParam.classLoader.loadClass(UConsts.Union.CLZ.AD_REQUEST);
//        XposedHelpers.findAndHookMethod(UConsts.Union.CLZ.INTERSTITIAL, loadPackageParam.classLoader, "loadAd", adRequstClz, new XC_MethodHook() {
        Class adRequstClz = loadPackageParam.classLoader.loadClass("com.ucweb.union.ads.d");
        XposedHelpers.findAndHookMethod("com.ucweb.union.ads.g", loadPackageParam.classLoader, "a", adRequstClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" loadAd");
            }
        });




        /**
         * public interface AdListener {
            void onAdError(Ad ad, AdError error);
            void onAdLoaded(Ad ad);
            void onAdShowed(Ad ad);
            void onAdClosed(Ad ad);
            void onAdClicked(Ad ad);
        }
         */
//        Class AdClz = loadPackageParam.classLoader.loadClass(UConsts.Union.CLZ.AD);
//        Class AdErrorClz = loadPackageParam.classLoader.loadClass(UConsts.Union.CLZ.AD_ERROR);
        Class AdClz = loadPackageParam.classLoader.loadClass("com.ucweb.union.ads.q");
        Class AdErrorClz = loadPackageParam.classLoader.loadClass("com.ucweb.union.ads.a");
        Log.w("terge","hook onAdError");
        XposedHelpers.findAndHookMethod(adListenerObjClz, loadPackageParam.classLoader, "onAdError", AdClz, AdErrorClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" onAdError");
            }
        });

        Log.w("terge","hook onAdLoaded");
        XposedHelpers.findAndHookMethod(adListenerObjClz, loadPackageParam.classLoader, "onAdLoaded", AdClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" onAdLoaded",new Throwable());
            }
        });

        Log.w("terge","hook onAdShowed");
        XposedHelpers.findAndHookMethod(adListenerObjClz, loadPackageParam.classLoader, "onAdShowed", AdClz,  new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" onAdShowed");
            }
        });

        Log.w("terge","hook onAdClosed");
        XposedHelpers.findAndHookMethod(adListenerObjClz, loadPackageParam.classLoader, "onAdClosed", AdClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" onAdClosed");
            }
        });

        Log.w("terge","hook onAdClicked");
        XposedHelpers.findAndHookMethod(adListenerObjClz, loadPackageParam.classLoader, "onAdClicked", AdClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","before "+TAG+" onAdClicked");
            }
        });

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
