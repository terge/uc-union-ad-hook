package me.terge.module.xposed.union;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by terge on 16-10-26.
 */
public interface IADNHook {


    abstract void hookSetListener(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException;

    abstract void hookSetPub(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException;

    abstract void hookLoad(XC_LoadPackage.LoadPackageParam loadPackageParam) throws ClassNotFoundException;

    abstract void hookListenerCallback(XC_LoadPackage.LoadPackageParam loadPackageParam);
}
