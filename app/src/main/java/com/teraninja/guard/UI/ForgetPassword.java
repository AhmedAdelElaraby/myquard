package com.teraninja.guard.UI;

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

import com.teraninja.guard.Model.DataRecuestCode;
import com.teraninja.guard.Model.SendDataRecuestCode;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentForgetPasswordBinding;


public class ForgetPassword extends Fragment {
FragmentForgetPasswordBinding binding;
MoveViewModel viewModel;
    String number;
    public ForgetPassword() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password,container,false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 number=binding.numberphone.getText().toString().trim();
                if (number.isEmpty()){
                    binding.numberphone.setError(getString(R.string.number_phone));
                    return;
                }
                else {
                    SendDataRecuestCode data = new SendDataRecuestCode();
                    data.setPhone(number);

                    viewModel.getDataRequestcode("application/json","ar",data);
                }
            }
        });

        viewModel.dataRecuestCode.observe(getViewLifecycleOwner(), new Observer<DataRecuestCode>() {
            @Override
            public void onChanged(DataRecuestCode dataRecuestCode) {
                if (dataRecuestCode.status==0){
                    Toast.makeText(getContext(), ""+dataRecuestCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else if (dataRecuestCode.status==1){
                    Toast.makeText(getContext(), ""+dataRecuestCode.getMessage(), Toast.LENGTH_SHORT).show();
                    dataRecuestCode.setStatus(2);
                    ForgetPasswordDirections.ActionForgetPasswordToActivateMessage forget= ForgetPasswordDirections.actionForgetPasswordToActivateMessage();
                    forget.setNumber(number);
                    Navigation.findNavController(binding.getRoot()).navigate(forget);

                }
            }
        });



        return binding.getRoot();
    }
}