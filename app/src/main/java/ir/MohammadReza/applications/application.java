package ir.MohammadReza.applications;

import android.app.Application;
import android.content.Context;

import ir.MohammadReza.DataBase.AssetDBHeleper;


public class application extends Application {


 public   static  Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        // ino mizarim ke ghabl az ejray barname data base check bshe
        new AssetDBHeleper().chechDb();
    }



    public static Context getContext(){
        return context;
    }
}
