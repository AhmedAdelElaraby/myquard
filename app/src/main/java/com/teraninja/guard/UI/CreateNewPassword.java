package com.teraninja.guard.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.Model.DataNewPassWord;
import com.teraninja.guard.Model.SendNewPassWoed;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentCreateNewPasswordBinding;

import java.util.Locale;


public class CreateNewPassword extends Fragment {
FragmentCreateNewPasswordBinding binding;
MoveViewModel viewModel;
String phone;
String code;

    public CreateNewPassword() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_new_password, container, false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        String l= Locale.getDefault().getLanguage();

        if (getArguments()!=null){
            CreateNewPasswordArgs  args =CreateNewPasswordArgs.fromBundle(getArguments());
          code= args.getCode();
           phone=args.getPhone();
        }
binding.send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String password=binding.password.getText().toString();
        String confirmpassword=binding.confirmpassword.getText().toString();
        if (password.isEmpty()){
            binding.password.setError(getString(R.string.password));
            return;
        }
        if (confirmpassword.isEmpty()){
            binding.confirmpassword.setError(getString(R.string.confirm_password));
            return;
        }
        if (password.equals(confirmpassword)){

        }
        else {
            binding.password.setError(getString(R.string.casepassword));
            binding.confirmpassword.setError(getString(R.string.casepassword));

            return;
        }
        if (password.length()<=8){
            binding.password.setError(getString(R.string.lantghpassword));
            return;

        }
        else{
            SendNewPassWoed sendNewPassWoed = new SendNewPassWoed(phone,code,password,confirmpassword);

            viewModel.newPassword("application/json",l,sendNewPassWoed);


        }
    }
});

viewModel.dataNewPassWord.observe(getViewLifecycleOwner(), new Observer<DataNewPassWord>() {
    @Override
    public void onChanged(DataNewPassWord dataNewPassWord) {
        Toast.makeText(getContext(), ""+dataNewPassWord.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    return binding.getRoot();
    }
}