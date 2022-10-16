package com.teraninja.guard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.teraninja.guard.UI.HomeActivity;
import com.teraninja.guard.databinding.ActivityPaymentCompanyBinding;
public class PaymentCompany extends AppCompatActivity {
    ActivityPaymentCompanyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payment_company);
        String url=getIntent().getStringExtra("url");
        if (url.isEmpty()){

        }if (url!=null){
            WebSettings webSettings = binding.webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadsImagesAutomatically(true);
            binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            binding.webview.loadUrl(url);

        }

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

                    //Toast.makeText(PaymentCompany.this, ""+url, Toast.LENGTH_SHORT).show();
                }
            }
        };
        binding.webview.setWebViewClient(client);



    }
}