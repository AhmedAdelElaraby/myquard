package com.teraninja.guard.UI;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.teraninja.guard.R;

public class MainActivity extends LocalizationActivity {
SharedPreferences preferences2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        preferences2=getSharedPreferences("lang", Context.MODE_PRIVATE);
//
//        setLocale(getApplicationContext(),getLocal(getApplicationContext()));
//
//
//    }
//    public  void setLocale(Context context,String locale){
//        Locale myLocale=new Locale(locale);
//        Resources resources=context.getResources();
//        DisplayMetrics dm=resources.getDisplayMetrics();
//        Configuration conf=resources.getConfiguration();
//        conf.locale=myLocale;
//        Locale.setDefault(myLocale);
//        conf.setLayoutDirection(myLocale);
//        resources.updateConfiguration(conf,dm);
//
//
//    }
//    public  String getLocal(Context context){
//        String pre="lange";
//        String Local=preferences2.getString(pre,getCurrentLocale(getApplicationContext()));
//        return Local;
//    }
//    public  String getCurrentLocale(Context context){
//        Locale currant=context.getResources().getConfiguration().locale;
//        return currant.getLanguage();
//    }
}}