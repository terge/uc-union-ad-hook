package me.terge.module.xposed;

import android.content.Context;
import android.content.Intent;

import me.terge.module.xposed.bean.AdConfig;
import me.terge.module.xposed.consts.ADN;
import me.terge.module.xposed.consts.AdType;

/**
 * Created by terge on 16-10-18.
 */

public class Config {


    private static Config mInstance;
    private Config(){
    };
    public static Config instance(){
        if(mInstance == null){
            synchronized (Config.class){
                if(mInstance == null){
                    mInstance = new Config();
                }
            }
        }
        return mInstance;
    }


    public AdConfig getCurrentConfig(ADN adn, AdType adType){
        AdConfig config = new AdConfig();
        config.pkg("com.gamecausal.motupatlu.unicornrun");
        config.adn(adn.toString());
        config.adType(adType.toString());
        config.isOn(true);
        config.adClz("com.google.android.gms.ads.AdView");
        config.adLisClz("com.google.android.gms.ads.AdListener");
        config.isShowLoadTrace(true);
        config.methodSetAdListener("setAdListener");
        config.methodsetPid("setAdUnitId");
        config.adListenerImpl("com.ucweb.union.ads.mediation.a.c.c$1");
        config.isPrintCallback(true);
//        config.methodOnAdError("onAdError");
//        config.methodOnAdLoaded("onAdLoaded");
//        config.methodOnAdShow("onAdShow");
        return config;
    }
    
    public void save(Context context,AdConfig config){
        Intent intent = new Intent();
        intent.setClassName("me.terge.module.xposed","me.terge.module.xposed.MainActivity");
        intent.putExtra("do","save");
        intent.putExtra("data",config.toString());
        context.startActivity(intent);
    }

    public void fetch(Context context,String pkg,ADN adn, AdType adType){
        Intent intent = new Intent();
        intent.setClassName("me.terge.module.xposed","me.terge.module.xposed.MainActivity");
        intent.putExtra("do","fetch");
        intent.putExtra("pkg","pkg");
        intent.putExtra("adn",adn.toString());
        intent.putExtra("adType",adType.toString());
        context.startActivity(intent);
    }


    public void saveToSdcard(String config) {
        // TODO: 16-10-25
    }
}
