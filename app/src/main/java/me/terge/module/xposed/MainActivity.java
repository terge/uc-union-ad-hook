package me.terge.module.xposed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import me.terge.module.xposed.bean.AdConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fetchConfig("pkg","adn","adtype");
            }
        });
        
        doSyncData(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        doSyncData(intent);
    }

    private void doSyncData(Intent intent) {
        if(intent == null )return ;
        Bundle bundle = intent.getExtras();
        if(bundle == null)return;
        String what = bundle.getString("do",null);
        if(what == null)return;
        switch (what){
            case "save":
                saveConfig(bundle.getString("data",null));
                break;
            case "fetch":
                fetchConfig(bundle.getString("pkg",null),bundle.getString("adn",null),bundle.getString("adType",null));
                break;
            default:
                break;
        }
        
    }

    private void saveConfig(String data) {
        Log.d("terge","saveConfig:"+data);
        if(data == null)return;
        try {
            AdConfig config = (AdConfig) AdConfig.parseAVObject(data);
            config.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    Log.i("terge","config save:"+( e!= null?"success":e.getMessage()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fetchConfig(String pkg,String adn,String adType){
        Log.e("terge","fetchConfig pkg:"+pkg+" adn:"+adn+" adType:"+adType);

            AdConfig config = new AdConfig();
                config.pkg("com.a.b.c.d");
                config.adn("adn");
                config.adType("adType");
                config.isOn(true);
                config.adClz("adClz");
                config.adLisClz("adLisClz");
                config.isShowLoadTrace(true);
                config.methodSetAdListener("setAdListener");
                config.methodsetPid("setPid");
                config.adListenerImpl("adListenerImpl");
                config.isPrintCallback(true);
                config.methodOnAdError("onAdError");
                config.methodOnAdLoaded("onAdLoaded");
                config.methodOnAdShow("onAdShow");

            config.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    Log.e("terge","save result:"+(e == null?"success":e.getMessage()));
                }
            });

//        AVQuery<AdConfig> pkgQuery = new AVQuery<>("AdConfig");
//        pkgQuery.whereEqualTo("pkg",pkg);
//
//        AVQuery<AdConfig> adnQuery = new AVQuery<>("AdConfig");
//        adnQuery.whereEqualTo("adn",adn);
//
//        AVQuery<AdConfig> adTypeQuery = new AVQuery<>("AdConfig");
//        adTypeQuery.whereEqualTo("adType",adType);
//
//        AVQuery<AdConfig> query = AVQuery.and(Arrays.asList(pkgQuery,adnQuery,adTypeQuery));
//        query.findInBackground(new FindCallback<AdConfig>() {
//            @Override
//            public void done(List<AdConfig> list, AVException e) {
//                Log.i("terge","config fetch:"+( e!= null?"success":e.getMessage()));
//                if(list != null && list.size() != 0){
//                    AdConfig config = list.get(0);
//                    Config.instance().saveToSdcard(config.toString());
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
