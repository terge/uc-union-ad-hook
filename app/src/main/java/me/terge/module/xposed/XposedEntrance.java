package me.terge.module.xposed;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import me.terge.module.xposed.union.AdmobBanner;
import me.terge.module.xposed.union.AdmobInterstitial;
import me.terge.module.xposed.union.AdmobNative;

/**
 * Created by terge on 16-10-17.
 */

public class XposedEntrance implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        String pkgName = loadPackageParam.packageName;
        if (isInBlackList(pkgName)) return;

        if("com.gamecausal.motupatlu.unicornrun".equals(pkgName)){
            Log.w("terge","begain hook: com.gamecausal.motupatlu.unicornrun");
            AdmobBanner.instance().hook(loadPackageParam);
            AdmobInterstitial.instance().hook(loadPackageParam);
        }

        if("cn.xender".equals(pkgName)){
            Log.w("terge","begain hook: com.gamecausal.motupatlu.unicornrun");
            AdmobNative.instance().
        }
    }

    private boolean isInBlackList(String pkgName) {
        if(pkgName.startsWith("com.google.android"))return true;
        if(pkgName.startsWith("com.android"))return true;
        return false;
    }
}
