package com.teraninja.guard.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.Model.DataRecuestCode;
import com.teraninja.guard.Model.GetVerifyAccoun;
import com.teraninja.guard.Model.SendDataRecuestCode;
import com.teraninja.guard.Model.SendVerifyAccoun;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentVerificationUserBinding;

import java.util.Locale;


public class VerificationUser extends Fragment {
FragmentVerificationUserBinding binding;
String phone;
MoveViewModel viewModel;
    public VerificationUser() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_verification_user, container, false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        String l= Locale.getDefault().getLanguage();

        if (getArguments()!=null){
            VerificationUserArgs args =VerificationUserArgs.fromBundle(getArguments());
            phone = args.getNumber();
            SendDataRecuestCode code = new SendDataRecuestCode();
            code.setPhone(phone);
            viewModel.getDataRequestcode("application/json",l,code);
            Toast.makeText(getContext(), ""+phone, Toast.LENGTH_SHORT).show();
        }
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.reternSendPassword.setEnabled(false);
                binding.reternSendPassword.setText("remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext

            }

            public void onFinish() {
                binding.reternSendPassword.setEnabled(true);
                binding.reternSendPassword.setText(getString(R.string.reternsendpassword));

            }

        }.start();



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.verificationUser, true).build();

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_verificationUser_to_createEmaiLookingForAjob
                        ,savedInstanceState,builder);
            }
        });
        binding.SendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n1=binding.number1.getText().toString().trim();
                String n2=binding.number2.getText().toString().trim();
                String n3=binding.number3.getText().toString().trim();
                String n4=binding.number4.getText().toString().trim();
                String n5=binding.number5.getText().toString().trim();
                String n6=binding.number6.getText().toString().trim();
                if (n1.isEmpty()){
                    binding.number1.setError("is Empty");
                    return;
                }
                if (n2.isEmpty()){
                    binding.number2.setError("is Empty");
                    return;
                }
                if (n3.isEmpty()){
                    binding.number3.setError("is Empty");
                    return;
                }
                if (n4.isEmpty()){
                    binding.number4.setError("is Empty");
                    return;
                }
                if (n5.isEmpty()){
                    binding.number5.setError("is Empty");
                    return;
                }
                if (n6.isEmpty()){
                    binding.number6.setError("is Empty");
                    return;
                }
                else{
                    SendVerifyAccoun  verifyAccoun = new SendVerifyAccoun(phone,n1+n2+n3+n4+n5+n6);
                    viewModel.getVerifyAccount("application/json",l,verifyAccoun);








                }
            }
        });

        viewModel.getVerifyAccoun.observe(getViewLifecycleOwner(), new Observer<GetVerifyAccoun>() {
            @Override
            public void onChanged(GetVerifyAccoun getVerifyAccoun) {
                if (getVerifyAccoun.status==0){
                    Toast.makeText(getContext(), ""+getVerifyAccoun.message, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (getVerifyAccoun.status==1){
                    Toast.makeText(getContext(), ""+getVerifyAccoun.message, Toast.LENGTH_SHORT).show();
                    getVerifyAccoun.setStatus(2);

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_verificationUser_to_login2);
                }

            }
        });

        binding.reternSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendDataRecuestCode data = new SendDataRecuestCode();
                data.setPhone(phone);
                viewModel.getDataRequestcode("application/json",l,data);
                new CountDownTimer(120000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        binding.reternSendPassword.setEnabled(false);
                        binding.reternSendPassword.setText("remaining: " + millisUntilFinished / 1000);
                        //here you can have your logic to set text to edittext

                    }

                    public void onFinish() {
                        binding.reternSendPassword.setEnabled(true);
                        binding.reternSendPassword.setText(getString(R.string.reternsendpassword));

                    }

                }.start();




            }
        });

        viewModel.dataRecuestCode.observe(getViewLifecycleOwner(), new Observer<DataRecuestCode>() {
            @Override
            public void onChanged(DataRecuestCode dataRecuestCode) {

                Toast.makeText(getContext(), ""+dataRecuestCode.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}