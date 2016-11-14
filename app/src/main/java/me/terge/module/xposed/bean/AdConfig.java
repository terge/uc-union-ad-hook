package me.terge.module.xposed.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by terge on 16-10-25.
 */
@AVClassName("AdConfig")
public class AdConfig extends AVObject{

    private final String PKG = "pkg";
    private final String ADN = "adn";
    private final String AD_TYPE = "adType";
    private final String IS_ON = "isOn";
    private final String AD_CLZ = "adClz";
    private final String AD_LISTENER_CLZ = "adLisClz";



    public String pkg(){
        return getString(PKG);
    }
    public void pkg(String pkg){
        put(PKG,pkg);
    }

    public String adn(){
        return getString(ADN);
    }
    public void adn(String adn){
        put(ADN,adn);
    }


    public String adType(){
        return getString(AD_TYPE);
    }
    public void adType(String adType){
        put(AD_TYPE,adType);
    }


    public boolean isOn(){
        return getBoolean(IS_ON);
    }
    public void isOn(boolean isOn){
        put(IS_ON,isOn);
    }


    public String adClz(){
        return getString(AD_CLZ);
    }
    public void adClz(String adClz){
        put(AD_CLZ,adClz);
    }

    public String adLisClz(){
        return getString(AD_LISTENER_CLZ);
    }
    public void adLisClz(String adLisClz){
        put(AD_LISTENER_CLZ,adLisClz);
    }

    private final String IS_SHOW_LOAD_TRACE = "isShowLoadTrace";
    public boolean isShowLoadTrace(){
        return getBoolean(IS_SHOW_LOAD_TRACE);
    }
    public void isShowLoadTrace(boolean isShowLoadTrace){
        put(IS_SHOW_LOAD_TRACE,isShowLoadTrace);
    }

    /**
     * setAdListener
     */
    private final String METHOD_SET_LISTENER_CLZ = "methodSetAdListener";
    public String methodSetAdListener(){
        return getString(METHOD_SET_LISTENER_CLZ);
    }
    public void methodSetAdListener(String methodSetAdListener){
        put(METHOD_SET_LISTENER_CLZ,methodSetAdListener);
    }

    /**
     * setPUB
     */
    private final String METHOD_SET_PID = "methodsetPid";
    public String methodsetPid(){
        return getString(METHOD_SET_PID);
    }
    public void methodsetPid(String methodsetPid){
        put(METHOD_SET_PID,methodsetPid);
    }


    /**
     * loadAd
     */
    private final String METHOD_LOAD_AD = "methodLoadAd";
    public String methodLoadAd(){
        return getString(METHOD_LOAD_AD);
    }
    public void methodLoadAd(String methodLoadAd){
        put(METHOD_LOAD_AD,methodLoadAd);
    }


    /**
     * adListener的实现类
     */
    private final String AD_LISTENER_IMP = "adListenerImpl";
    public String adListenerImpl(){
        return getString(AD_LISTENER_IMP);
    }
    public void adListenerImpl(String adListenerImpl){
        put(AD_LISTENER_IMP,adListenerImpl);
    }


    /**
     * isPrintCallback
     */
    private final String IS_PRINT_CALLBACK = "isPrintCallback";
    public boolean isPrintCallback(){
        return getBoolean(IS_PRINT_CALLBACK);
    }
    public void isPrintCallback(boolean isPrintCallback){
        put(IS_PRINT_CALLBACK,isPrintCallback);
    }


    /**
     * method onAdError
     */
    private final String METHOD_ON_AD_ERROR = "methodOnAdError";
    public String methodOnAdError(){
        return getString(METHOD_ON_AD_ERROR);
    }
    public void methodOnAdError(String methodOnAdError){
        put(METHOD_ON_AD_ERROR,methodOnAdError);
    }


    /**
     * method onAdLoaded
     */
    private final String METHOD_ON_AD_LOADED = "methodOnAdLoaded";
    public String methodOnAdLoaded(){
        return getString(METHOD_ON_AD_LOADED);
    }
    public void methodOnAdLoaded(String methodOnAdLoaded){
        put(METHOD_ON_AD_LOADED,methodOnAdLoaded);
    }


    /**
     * method onAdShow
     */
    private final String METHOD_ON_AD_SHOW = "methodOnAdShow";
    public String methodOnAdShow(){
        return getString(METHOD_ON_AD_SHOW);
    }
    public void methodOnAdShow(String methodOnAdShow){
        put(METHOD_ON_AD_SHOW,methodOnAdShow);
    }


}
