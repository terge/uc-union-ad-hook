package me.terge.module.xposed.union;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import me.terge.module.xposed.bean.AdConfig;
import me.terge.module.xposed.consts.ADN;
import me.terge.module.xposed.consts.AdType;

/**
 * Created by terge on 16-10-18.
 */

public class AdmobInterstitial extends BaseAd{
    static {
        mInstance = new AdmobInterstitial();
    }
    private static AdmobInterstitial mInstance;
    private AdmobInterstitial(){
        AdConfig config = new AdConfig();
        config.pkg("com.gamecausal.motupatlu.unicornrun");
        config.adn(ADN.ADMOB.toString());
        config.adType(AdType.INTERSTITAL.toString());
        config.isOn(true);
        config.adClz("com.google.android.gms.ads.InterstitialAd");
        config.adLisClz("com.google.android.gms.ads.AdListener");
        config.methodSetAdListener("setAdListener");
        config.methodsetPid("setAdUnitId");
        config.methodLoadAd("loadAd");
        config.adListenerImpl("com.ucweb.union.ads.mediation.a.c.c$1");
        config.methodOnAdError("onAdFailedToLoad");
        config.methodOnAdLoaded("onAdLoaded");
        config.methodOnAdShow("onAdOpened");
        config.isPrintCallback(true);

        mConfig = config;
    };
    public static AdmobInterstitial instance(){
        return mInstance;
    }




    @Override
    public void hookSetListener(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        final Class<?> adlistenerClz = loadPackageParam.classLoader.loadClass(mConfig.adLisClz());
        Log.i("terge","hook "+TAG+" setAdListener");
        XposedHelpers.findAndHookMethod(mConfig.adClz(),loadPackageParam.classLoader,mConfig.methodSetAdListener(),adlistenerClz,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String clz = param.args[0].getClass().toString();
                mConfig.adListenerImpl(clz);
                Log.d("terge","before "+TAG+" setAdListener class:"+clz);
            }
        });
    }

    @Override
    public void hookSetPub(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        Log.i("terge","hook  setPub "+TAG);
        XposedHelpers.findAndHookMethod(mConfig.adClz(), loadPackageParam.classLoader, mConfig.methodsetPid(), String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String pub = param.args[0].toString();
                Log.i("terge","before "+TAG+" setPub:"+pub);
                if(mConfig.isOn())return;
                Log.w("terge","modify "+TAG+" pub to:whatever");
                param.args[0] = "whatever";
            }
        });
    }

    @Override
    public void hookLoad(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        Log.i("terge","hook adRequest.loadAd");
        Class<?> adRequestClz = loadPackageParam.classLoader.loadClass("com.google.android.gms.ads.AdRequest");
        XposedHelpers.findAndHookMethod(mConfig.adClz(), loadPackageParam.classLoader,mConfig.methodLoadAd(),adRequestClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("terge","before "+TAG+" loadAd");
                if(mConfig.isShowLoadTrace()){
                    Log.d("terge","show "+TAG+" loadAd trace",new Throwable());
                }
            }

        });
    }

    @Override
    public void hookListenerCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if(!mConfig.isPrintCallback())return;
        String listenerClz = mConfig.adListenerImpl();
        Log.i("terge"," hook "+TAG+" onAdFailedToLoad");
        XposedHelpers.findAndHookMethod(listenerClz, loadPackageParam.classLoader, mConfig.methodOnAdError(), int.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdError:"+param.args[0]);
            }
        });
        Log.i("terge"," hook "+TAG+" onAdLoaded");
        XposedHelpers.findAndHookMethod(listenerClz, loadPackageParam.classLoader, mConfig.methodOnAdLoaded(), new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdLoaded");
            }
        });

        Log.i("terge"," hook "+TAG+" onAdOpened");
        XposedHelpers.findAndHookMethod(listenerClz, loadPackageParam.classLoader, mConfig.methodOnAdShow(), new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdShow");
            }
        });
    }
}
