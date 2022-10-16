package com.teraninja.guard.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.teraninja.guard.Model.DataPayment;
import com.teraninja.guard.Model.DataSendPayment;
import com.teraninja.guard.R;
import com.teraninja.guard.UI.HomeActivity;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.ActivityPaymentBinding;

import java.util.Locale;

public class Payment extends AppCompatActivity   {
ActivityPaymentBinding binding;
MoveViewModel viewModel;
SharedPreferences preferences;
String token;
String l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payment);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();

        preferences=getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        String type=getIntent().getStringExtra("type");
        String packageid=getIntent().getStringExtra("Packageid");
       if (type.isEmpty()){
           return;
       }
       if(packageid.isEmpty()){
                return;
       }
       if (type.equals("guard")){
           DataSendPayment p= new DataSendPayment();
           p.setType(type);
           p.setPackage_id(packageid);
        viewModel.getPaymentguard("Bearer"+token,"application/json",l,p);

       }
       if (type.equals("company")){

       }else {

       }
viewModel.Payment.observe(this, new Observer<DataPayment>() {
    @Override
    public void onChanged(DataPayment dataPayment) {
       // Toast.makeText(Payment.this, ""+dataPayment.message+dataPayment.data.getUrl(), Toast.LENGTH_SHORT).show();
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webview.loadUrl(dataPayment.data.getUrl());





    }
});


        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
               if (url.contains("status=approved")){
                   Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                   i.putExtra("stuts",true);
                   setResult(RESULT_OK,i);
                   finish();
                   return;
               }
               if (url.contains("status=failed")){
                   Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                   i.putExtra("stuts",false);
                   setResult(RESULT_OK,i);
                   finish();
                   return;
               }
                else {
                   Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                   i.putExtra("stuts",false);
                   setResult(RESULT_OK,i);
                   Toast.makeText(Payment.this, ""+url, Toast.LENGTH_SHORT).show();
               }
            }
        };
        binding.webview.setWebViewClient(client);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}