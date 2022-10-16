package com.teraninja.guard.UI;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.guard.Model.DataSendRegister;
import com.teraninja.guard.Model.DataTems;
import com.teraninja.guard.Model.GetRegister;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentCreateEmaiLookingForAjobBinding;

import java.util.Locale;
import java.util.regex.Pattern;

public class CreateEmaiLookingForAjob extends Fragment {
FragmentCreateEmaiLookingForAjobBinding binding;
MoveViewModel  viewModel;
String phone;
    String language;
    public CreateEmaiLookingForAjob() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_create_emai_looking_for_ajob,container,false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
         language= Locale.getDefault().getLanguage();
    binding.back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    });
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=binding.username.getText().toString().trim();
                phone=binding.numberphone.getText().toString().trim();
                String email= binding.Email.getText().toString().trim();
                String password= binding.Password.getText().toString().trim();
                String confirmpassword= binding.confirmpassword.getText().toString().trim();
                boolean checked = binding.yes.isChecked();
                if (username.isEmpty()){
                    binding.inputTextLayoutUsername.setError(getString(R.string.name_user));
                    return;
                }
                if (phone.isEmpty()){
                    binding.inputTextLayoutnumberphone.setError(getString(R.string.number_phone));
                    return;
                }
                if (email.isEmpty()){
                    binding.inputTextLayoutEmail.setError(getString(R.string.email));
                    return;
                }
                if (password.isEmpty()){
                    binding.inputTextLayoutPassword.setError(getString(R.string.password));
                    return;
                }
                if (confirmpassword.isEmpty()){
                    binding.inputTextLayoutconfertpassword.setError(getString(R.string.confirm_password));
                    return;
                }
                if (password.equals(confirmpassword)){

                }
                else {
                    binding.Password.setError(getString(R.string.casepassword));
                    binding.confirmpassword.setError(getString(R.string.casepassword));

                    return;
                }

                if (phone.length()<=10){
                    binding.numberphone.setError(getString(R.string.casenumberphone));
                    return;
                }

                if (isValidEmailId(email)){

                }
                else {
                    binding.Email.setError(getString(R.string.caseemail));
                    return;
                }
                if (checked==false){
                    binding.yes.setError("Select");
                    return;
                }
                else {
                    DataSendRegister sendRegister = new DataSendRegister();
                    sendRegister.setName(username);
                    sendRegister.setEmail(email);
                    sendRegister.setPhone(phone);
                    sendRegister.setPassword(password);
                    sendRegister.setPassword_confirmation(confirmpassword);
                    sendRegister.setType("guard");
                     viewModel.getcreateuser("application/json",language,sendRegister);
                    binding.spinkit.setVisibility(View.VISIBLE);

                }
            }
        });

     viewModel.dataregister.observe(getViewLifecycleOwner(), new Observer<GetRegister>() {
         @Override
         public void onChanged(GetRegister dataRegister) {


             if (dataRegister.status==0){
                 Toast.makeText(getContext(), ""+dataRegister.message, Toast.LENGTH_SHORT).show();
                binding.spinkit.setVisibility(View.GONE);
             }else if (dataRegister.status==1) {
                 showAddressesPopup(""+dataRegister.message);
                 binding.spinkit.setVisibility(View.GONE);
                 NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.createEmaiLookingForAjob, true).build();

                 CreateEmaiLookingForAjobDirections.ActionCreateEmaiLookingForAjobToVerificationUser user= CreateEmaiLookingForAjobDirections.actionCreateEmaiLookingForAjobToVerificationUser();
                 user.setNumber(phone);
                 Navigation.findNavController(binding.getRoot()).navigate(user,builder);
                 dataRegister.setStatus(4);
             }
                  else if (dataRegister.getStatus()==3){
                 binding.spinkit.setVisibility(View.GONE);
                 Toast.makeText(getContext(), "" + dataRegister.message, Toast.LENGTH_SHORT).show();
                    dataRegister.setStatus(4);

             }





         }
     });
     binding.yes.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            ShowDilogeError();
         }
     });
        return binding.getRoot();
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    public void ShowDilogeError(){
        Dialog myDialog;
        TextView textView;
        TextView textv;
        Button button;
        View view;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_erorr);
        viewModel.gettems("application/json",language);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        view=myDialog.findViewById(R.id.view);
        textv=myDialog.findViewById(R.id.text);
        textv.setText(R.string.Terms_and_Conditions);
        view.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
viewModel.gettems.observe(getViewLifecycleOwner(), new Observer<DataTems>() {
    @Override
    public void onChanged(DataTems dataTems) {
        textView.setText(""+dataTems.getData().getTerms()+"\n"+dataTems.getData().getPolicy());
    }
});

        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

    public void showAddressesPopup (String message) {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dilog_of_the_situation);
        ImageView imageView;
        TextView textView;
        imageView=myDialog.findViewById(R.id.imagedone);
        textView=myDialog.findViewById(R.id.textdone);
        textView.setVisibility(View.VISIBLE);
        textView.setText(""+message);
        imageView.setVisibility(View.VISIBLE);
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

}