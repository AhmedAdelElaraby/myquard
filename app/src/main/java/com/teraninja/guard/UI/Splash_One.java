package com.teraninja.guard.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentSplashOneBinding;


public class Splash_One extends Fragment {
FragmentSplashOneBinding binding;
Dialog myDialog;
    SharedPreferences preferences;
    public Splash_One() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater, R.layout.fragment_splash__one,container,false);


        preferences= getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);

        binding.login.setAlpha(0f);
        binding.login.setTranslationY(50);
        binding.login.animate().alpha(1f).translationYBy(-50).setDuration(1500);
        /////
        binding.SignUpSearhWork.setAlpha(0f);
        binding.SignUpSearhWork.setTranslationY(50);
        binding.SignUpSearhWork.animate().alpha(1f).translationYBy(-50).setDuration(1500);
        //////
        binding.SignUp.setAlpha(0f);
        binding.SignUp.setTranslationY(50);
        binding.SignUp.animate().alpha(1f).translationYBy(-50).setDuration(1500);
        binding.Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata("case","no");
                savedata("Type","no");
                Intent intent = new Intent(getContext(),HomeActivity.class);

                startActivity(intent);
            }
        });
       binding.login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splash_One_to_login2);
           }
       });
binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splash_One_to_createEmaiLookingForAjob);
    }
});
        binding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splash_One_to_createEmailCompany);
            }
        });

       return binding.getRoot();
    }
    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}