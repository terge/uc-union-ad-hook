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

public class AdmobBanner extends BaseAd{
    static {
        mInstance = new AdmobBanner();
    }
    private static AdmobBanner mInstance;
    private AdmobBanner(){
//        mConfig = Config.instance().getCurrentConfig(ADN.ADMOB, AdType.BANNER);

        AdConfig config = new AdConfig();
        config.pkg("cn.xender");
        config.adn(ADN.ADMOB.toString());
        config.adType(AdType.BANNER.toString());
        config.isOn(true);
        config.adClz("com.google.android.gms.ads.AdView");
        config.adLisClz("com.google.android.gms.ads.a");
        config.methodSetAdListener("setAdListener");
        config.methodsetPid("setAdUnitId");
        config.methodLoadAd("loadAd");
        config.isShowLoadTrace(true);
        config.adListenerImpl("com.ucweb.union.ads.mediation.a.c.c$1");
        config.methodOnAdError("a");
        config.methodOnAdLoaded("onAdLoaded");
        config.methodOnAdShow("onAdOpened");
        config.isPrintCallback(true);

        mConfig = config;
    };
    public static AdmobBanner instance(){
        return mInstance;
    }


    @Override
    public void hookListenerCallback(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if(!mConfig.isPrintCallback())return;

        String adLisImpl = mConfig.adListenerImpl();
        if(adLisImpl == null){
            Log.e("terge","lost adListenerImpl class");
            return;
        }

        Log.i("terge"," hook "+TAG+" onAdFailedToLoad");
        XposedHelpers.findAndHookMethod(adLisImpl, loadPackageParam.classLoader, mConfig.methodOnAdError(), int.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdFailedToLoad:"+param.args[0]);
            }
        });
        Log.i("terge"," hook "+TAG+" onAdLoaded");
        XposedHelpers.findAndHookMethod(adLisImpl, loadPackageParam.classLoader, mConfig.methodOnAdLoaded(), new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdLoaded");
            }
        });

        Log.i("terge"," hook "+TAG+" onAdOpened");
        XposedHelpers.findAndHookMethod(adLisImpl, loadPackageParam.classLoader, mConfig.methodOnAdShow(), new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.e("terge","beforeHookedMethod： "+TAG+" onAdOpened");
            }
        });
    }

    @Override
    public void hookSetListener(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        Log.i("terge","hook setAdListener "+TAG);
        Class adLisClz = loadPackageParam.classLoader.loadClass(mConfig.adLisClz());
        XposedHelpers.findAndHookMethod(mConfig.adClz(), loadPackageParam.classLoader, mConfig.methodSetAdListener(), adLisClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String adListenerImpl = param.args[0].getClass().toString();
                Log.i("terge","before "+TAG+" setAdListener adListenerClz:"+adListenerImpl);

//                AdConfig config = new AdConfig();
//                config.pkg(appContext.getPackageName());
//                config.adn(ADN.ADMOB.toString());
//                config.adType(AdType.BANNER.toString());
//                config.adListenerImpl(adListenerImpl);
//                Config.instance().save(appContext,config);
            }
        });
    }

    @Override
    public void hookSetPub(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Log.i("terge","hook setPub "+TAG);
        XposedHelpers.findAndHookMethod(mConfig.adClz(), loadPackageParam.classLoader, mConfig.methodsetPid(), String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String pid = (String) param.args[0];
                Log.d("terge","before "+TAG+" setPub:"+pid);

                if(mConfig.isOn())return;
                Log.w("terge","modify "+TAG+"pub to whatever ");
                param.args[0] = "whatever";
            }
        });
    }

    @Override
    public void hookLoad(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException {
        Log.i("terge","hook loadAd "+TAG);
        Class adRequestClz = loadPackageParam.classLoader.loadClass("com.google.android.gms.ads.AdRequest");

        if(adRequestClz == null)return;
        XposedHelpers.findAndHookMethod(mConfig.adClz(), loadPackageParam.classLoader, mConfig.methodLoadAd(), adRequestClz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("terge","before "+TAG+" loadAd");
                if(mConfig.isShowLoadTrace()){
                    Log.d("terge","show "+TAG+"loadAd trace",new Throwable());
                }

            }
        });
    }
}
