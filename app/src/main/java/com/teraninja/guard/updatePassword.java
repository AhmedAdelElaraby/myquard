package com.teraninja.guard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.Model.Reedollnav;
import com.teraninja.guard.Model.SendDataUpdtePassword;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUpdatePasswordBinding;

import java.util.Locale;


public class updatePassword extends Fragment {
FragmentUpdatePasswordBinding binding;
    MoveViewModel viewModel;
    String l;
    SharedPreferences preferences;
    String token;
    public updatePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_update_password, container, false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword=binding.oldpassword.getText().toString().trim();
                String newpassword=binding.newpassword.getText().toString().trim();
                String confarmpassword=binding.confearmpassword.getText().toString().trim();
                if (oldpassword.isEmpty()){
                    binding.inputTextLayoutoldpassword.setError(getString(R.string.old_password));
                    return;
                }
                if (newpassword.isEmpty()){
                    binding.inputTextLayoutnewpassword.setError(getString(R.string.please_enter_new_password));
                    return;
                }
                if (confarmpassword.isEmpty()){
                    binding.inputTextLayoutconfaempassword.setError(getString(R.string.confirm_password));
                    return;
                }
                else{
                    SendDataUpdtePassword updtePassword = new SendDataUpdtePassword();
                    updtePassword.setOld_password(oldpassword);
                    updtePassword.setPassword(newpassword);
                    updtePassword.setPassword_confirmation(confarmpassword);
                    viewModel.updatePassword("Bearer"+token,"application/json",l,updtePassword);
                }
            }
        });
viewModel.updatepassword.observe(getViewLifecycleOwner(), new Observer<Reedollnav>() {
    @Override
    public void onChanged(Reedollnav reedollnav) {
        Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
        return binding.getRoot();
    }
}