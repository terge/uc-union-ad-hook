package me.terge.module.xposed.consts;

/**
 * Created by terge on 16-10-17.
 */

public class UConsts {
    public static class Facebook{
        private static final String PKG = "com.facebook.ads";
        public static class CLZ{
            public static final String AD_INTER_LISTENER = PKG+"."+"InterstitialAdListener";
            public static final String INTERSTITIAL = PKG+"."+"InterstitialAd";
            public static final String AD_IMPR_LISTENER = PKG+"."+"ImpressionListener";
        }
    }

    public static class Admob{
        public static final String PKG = "com.google.android.gms.ads";
        public static class CLZ{
            public static final String AD_REQUEST = PKG+"."+"AdRequest";
            public static final String BANNER_AD_VIEW = PKG+"."+"AdView";
            public static final String INTERSTITIAL = PKG+"."+"InterstitialAd";
            public static final String AD_LISTENER = PKG+"."+"AdListener";
        }

        public static class METHOD{
            public static final String SET_AD_LISTENER = "setAdListener";
            public static final String SET_AD_UNIT_ID = "setAdUnitId";
            public static final String LOAD_AD = "loadAd";
        }
    }

    public static class Union{
        public static final String PKG = "com.ucweb.union.ads";

        public static class CLZ{
            public static final String BANNER_VIEW = PKG+"."+"BannerAdView";
            public static final String AD_LISTENER = PKG+"."+"AdListener";
            public static final String AD_REQUEST = PKG+"."+"AdRequest";
            public static final String INTERSTITIAL = PKG+"."+"InterstitialAd";
            public static final String AD = PKG+"."+"Ad";
            public static final String AD_ERROR = PKG+"."+"AdError";
        }
        public static class METHOD{
            public static final String SET_AD_LISTENER = "setAdListener";
        }

    }
}
