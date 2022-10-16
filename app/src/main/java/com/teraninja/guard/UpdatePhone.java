package com.teraninja.guard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.Model.DataUpdatephone;
import com.teraninja.guard.Model.SendDataRecuestCode;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUpdatePhoneBinding;

import java.util.Locale;


public class UpdatePhone extends Fragment {
FragmentUpdatePhoneBinding binding;
MoveViewModel viewModel;
    String l;
    String phone;
    SharedPreferences preferences;
    public UpdatePhone() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_update_phone, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        String token=preferences.getString("Token","no");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 phone=binding.numberphone.getText().toString().trim();
                if (phone.isEmpty()){
                    binding.inputTextLayoutnumberphone.setError(getString(R.string.number_phone));
                    return;
                }if (phone.length()<=9){
                    binding.inputTextLayoutnumberphone.setError(getString(R.string.number_phone));
                    return;
                }
                else {
                    SendDataRecuestCode code = new SendDataRecuestCode();
                    code.setPhone(phone);
                    viewModel.updatePhhone("Bearer"+token,
                            "application/json",l,code);
                }
            }
        });
        viewModel.updatephone.observe(getViewLifecycleOwner(), new Observer<DataUpdatephone>() {
            @Override
            public void onChanged(DataUpdatephone reedollnav) {
                if (reedollnav.getStatus()==1){
                    Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
                    UpdatePhoneDirections.ActionUpdatePhoneToUpdatePhoneByCode code=UpdatePhoneDirections.actionUpdatePhoneToUpdatePhoneByCode();
                    code.setPhone(phone);
                    String co=String.valueOf(reedollnav.getData());
                    code.setCode(co);
                    reedollnav.setStatus(3);
                    Navigation.findNavController(binding.getRoot()).navigate(code);


                }else {
                    Toast.makeText(getContext(), "" + reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }
}