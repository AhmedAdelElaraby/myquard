package com.teraninja.guard.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.teraninja.guard.Model.DataLogin;
import com.teraninja.guard.Model.DataSendLogin;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentLoginBinding;

import java.util.Locale;


public class Login extends Fragment {
FragmentLoginBinding binding;
MoveViewModel viewModel;
    String phone;
    SharedPreferences preferences;
    String tokennotivicaton;
    public Login() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
gettokenvotivication();
        FadingCircle doubleBounce = new FadingCircle();
        binding.spinkit.setIndeterminateDrawable(doubleBounce);

        String l= Locale.getDefault().getLanguage();
        preferences= getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
binding.back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
});

binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        phone=binding.numberphone.getText().toString().trim();
        String password=binding.password.getText().toString().trim();
        if (phone.isEmpty()){
            binding.numberphone.setError(getString(R.string.number_phone));
            return;
        }
        if (phone.length()<=9){
            binding.numberphone.setError(getString(R.string.number_phone));
            return;
        }
        if (password.isEmpty()){
            binding.password.setError(getString(R.string.password));
            return;
        }
        else {
            binding.spinkit.setVisibility(View.VISIBLE);

            DataSendLogin data = new DataSendLogin(phone,password,tokennotivicaton);
            viewModel.getlogin("application/json",l,data);


        }
    }
});


viewModel.data.observe(getViewLifecycleOwner(), new Observer<DataLogin>() {
    @Override
    public void onChanged(DataLogin dataLogin) {
        if (dataLogin.status==1){
         savedata("Token",dataLogin.getData().getToken());
         savedata("Type",dataLogin.getData().getUser().getType());
         savedata("case","yes");

         Intent intent = new Intent(getContext(),HomeActivity.class);
        startActivity(intent);
        binding.spinkit.setVisibility(View.GONE);
        getActivity().finish();
        return;
        }
        if(dataLogin.status==0){
            binding.spinkit.setVisibility(View.GONE);
            Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (dataLogin.status==2){
            binding.spinkit.setVisibility(View.GONE);

            Toast.makeText(getContext(), ""+dataLogin.getMessage(), Toast.LENGTH_SHORT).show();
            LoginDirections.ActionLogin2ToVerificationUser user = LoginDirections.actionLogin2ToVerificationUser();
            user.setNumber(phone);
            Navigation.findNavController(binding.getRoot()).navigate(user);
            dataLogin.setStatus(3);

        }
    }
});

binding.ForgetPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        binding.spinkit.setVisibility(View.VISIBLE);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login2_to_forgetPassword);
        binding.spinkit.setVisibility(View.GONE);
    }
});


return binding.getRoot();
    }
public void gettokenvotivication(){
    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
        @Override
        public void onComplete(@NonNull Task<String> task) {
            if (task.isSuccessful()){
                tokennotivicaton=task.getResult();
                //Toast.makeText(getContext(), ""+tokennotivicaton, Toast.LENGTH_SHORT).show();
            }

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {

        }
    });
}
    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}