package me.terge.module.xposed.union;

import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import me.terge.module.xposed.consts.UConsts;

/**
 * Created by terge on 16-10-14.
 */

public class FacebookInterstitial extends BaseAd {

    static {
        mInstance = new FacebookInterstitial();
    }
    private static FacebookInterstitial mInstance;
    private FacebookInterstitial(){};
    public static FacebookInterstitial instance(){
        return mInstance;
    }



    public void turnOff(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Log.i("terge","turnOff "+TAG);
        XposedHelpers.findAndHookConstructor(UConsts.Facebook.CLZ.INTERSTITIAL, loadPackageParam.classLoader, Context.class,String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.w("terge","beforeHookedMethod： facebook.interstitial- modify PUB to: whaterer");
                param.args[1] = "whaterer";
            }

        });
    }


    public void showTrace(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod(UConsts.Facebook.CLZ.INTERSTITIAL, loadPackageParam.classLoader,"loadAd", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.w("terge","beforeHookedMethod： fackbook.InterstitialAd.loadAd()",new Throwable());
            }

        });
    }

    private final String KEY_AD_LISTENER_CLZ = "facebook-ad-listener-class";
    private final String KEY_IMPR_LISTENER_CLZ= "facebook-impr-listener-class";

    public void printCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {


        String listenerClz = "com.ucweb.union.ads.a.a.f";
        String imprListenerClz = null;
        if(listenerClz == null){
            Log.w("terge","hook "+TAG + " setAdListener");
            Class adLisClz = loadPackageParam.classLoader.loadClass(UConsts.Facebook.CLZ.AD_INTER_LISTENER);
            XposedHelpers.findAndHookMethod(UConsts.Facebook.CLZ.INTERSTITIAL, loadPackageParam.classLoader, "setAdListener", adLisClz, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String clz = param.args[0].getClass().toString();
//                    Config.instance(appContext).save(KEY_AD_LISTENER_CLZ,clz);
                    Log.i("terge","facebook-ad-listener-class:"+clz);
                }
            });


            Log.w("terge","hook "+TAG + " setImpressionListener");
            Class imprLisClz = loadPackageParam.classLoader.loadClass(UConsts.Facebook.CLZ.AD_IMPR_LISTENER);
            XposedHelpers.findAndHookMethod(UConsts.Facebook.CLZ.INTERSTITIAL, loadPackageParam.classLoader, "setImpressionListener", imprLisClz, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String clz = param.args[0].getClass().toString();
//                    Config.instance(appContext).save(KEY_IMPR_LISTENER_CLZ,clz);
                    Log.i("terge","facebook-impr-listener-class:"+clz);
                }
            });
        }else{
            /**
             * public interface InterstitialAdListener extends AdListener {
                     void onInterstitialDisplayed(Ad var1);

                     void onInterstitialDismissed(Ad var1);
             }
             */
            Log.w("terge","hook "+TAG + "onInterstitialDisplayed");
            XposedHelpers.findAndHookMethod(listenerClz, loadPackageParam.classLoader, "onInterstitialDisplayed", Object.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.e("terge","beforeHookedMethod：  "+ TAG+" onInterstitialDisplayed");
                }
            });

            Log.w("terge","hook "+TAG + "onInterstitialDismissed");
            XposedHelpers.findAndHookMethod(listenerClz, loadPackageParam.classLoader, "onInterstitialDismissed", Object.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.e("terge","beforeHookedMethod：  "+ TAG+" onInterstitialDismissed");
                }
            });

//            Log.w("terge","hook "+TAG + "onLoggingImpression");
//            XposedHelpers.findAndHookMethod(imprListenerClz, loadPackageParam.classLoader, "onLoggingImpression", Object.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.e("terge","beforeHookedMethod： "+ TAG+" onLoggingImpression");
//                }
//            });


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
