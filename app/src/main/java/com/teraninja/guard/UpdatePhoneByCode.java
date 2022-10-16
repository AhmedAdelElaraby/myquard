package com.teraninja.guard;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.teraninja.guard.Model.DataUpdatephone;
import com.teraninja.guard.Model.SendDataRecuestCode;
import com.teraninja.guard.Model.SendVerifyAccoun;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUpdatePhoneByCodeBinding;

import java.util.Locale;


public class UpdatePhoneByCode extends Fragment {
FragmentUpdatePhoneByCodeBinding binding;
String phone;
String code;
MoveViewModel viewModel;
    SharedPreferences preferences;
    String l;
    public UpdatePhoneByCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_update_phone_by_code, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        String token=preferences.getString("Token","no");
        if (getArguments()!=null){
            UpdatePhoneByCodeArgs args =UpdatePhoneByCodeArgs.fromBundle(getArguments());
            phone = args.getPhone();
            code=args.getCode();
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
       binding.txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {

                if (str.toString().equals(code)) {
                    SendVerifyAccoun verifyAccoun = new SendVerifyAccoun(phone,code);

                    viewModel.updateCode("Bearer"+token,
                            "application/json",l,verifyAccoun);
                    Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                   binding.txtPinEntry.setText(null);
                }
            }
        });
        binding.reternSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendDataRecuestCode phoneoop = new SendDataRecuestCode();
                phoneoop.setPhone(phone);
                viewModel.updatePhhone("Bearer"+token,
                        "application/json",l,phoneoop);

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
        viewModel.updatephone.observe(getViewLifecycleOwner(), new Observer<DataUpdatephone>() {
            @Override
            public void onChanged(DataUpdatephone reedollnav) {
                if (reedollnav.getStatus()==1){
                    Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
                    code=String.valueOf(reedollnav.getData());




                }else {
                    Toast.makeText(getContext(), "" + reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.SendcodeUpdate.observe(getViewLifecycleOwner(), new Observer<DataUpdatephone>() {
            @Override
            public void onChanged(DataUpdatephone dataUpdatephone) {
                if (dataUpdatephone.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataUpdatephone.getMessage(), Toast.LENGTH_SHORT).show();
                    NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.updatePhoneByCode, true).build();

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updatePhoneByCode_to_profileCompany,savedInstanceState,builder);
                }
            }
        });
        return binding.getRoot();

    }
}