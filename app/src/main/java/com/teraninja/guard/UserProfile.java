package com.teraninja.guard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.UI.HomeActivity;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUserProfileBinding;

import java.util.Locale;


public class UserProfile extends Fragment {
FragmentUserProfileBinding binding;
MoveViewModel viewModel;
    String url="https://guard.teraninjadev.com/storage/";
String l;
    SharedPreferences preferences;
    String getcase;
    public UserProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_profile, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getContext().getSharedPreferences("ar", Context.MODE_PRIVATE);
        String token=preferences.getString("Token","no");
        if (token.isEmpty()){

        }
        if (token.equals("no")){

        }
        else {
            viewModel.getProfile("Bearer"+token,
                    "application/json",l);
        }

        viewModel.getProfile.observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                if (profile!=null){
                    getcase=profile.getData().getImage();
                    Toast.makeText(getContext(), ""+profile.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.name.setText(profile.getData().getName());
                    binding.numberphone.setText(profile.getData().getPhone());
                    Picasso.get().load(url+profile.getData().getImage()).into(binding.Imageprofile);
                    binding.theAge.setText(getString(R.string.the_age)+" : "+profile.getData().getAge());
                     binding.gender.setText(getString(R.string.gender)+" : "+profile.getData().getGender());
               if (profile.getData().getJop_type()!=null){
                   binding.posation.setText(getString(R.string.posation)+" : "+profile.getData().getJop_type().getName());

               }
               if (profile.getData().getCity()!=null&&profile.getData().getDistrict()!=null){
                   binding.cuantry.setText(getString(R.string.country)+" : "+profile.getData().getCity().getName()+profile.getData().getDistrict().getName());

               }
               binding.qualification.setText(getString(R.string.qualification)+" : "+profile.getData().getQualification());
                binding.SocialStatus.setText(getString(R.string.social_status)+" : "+profile.getData().getSocial_status());
                binding.email.setText(" "+profile.getData().getEmail());
                binding.exparice.setText("  "+profile.getData().getExperience());
                binding.numberJobOffers.setText(" "+profile.getData().getOffer_me());
                binding.numberAppear.setText(" "+profile.getData().getAppear());
                binding.countreyjob.setText("  "+profile.getData().getOther_cities());
                binding.AcuontBank.setText(" "+profile.getData().getIban_no());
               // binding.numberSubscriptions.setText(""+profile.getData().get);


                }
                else {
                    Toast.makeText(getContext(), "no found", Toast.LENGTH_SHORT).show();
                }


//

            }
        });
        binding.seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getcase!=null){
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userProfile_to_updateProfileGuard);

                }
                else {
                    ShowDilogeErrorinfo(getString(R.string.You_cannot_modify));
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome= new Intent(getContext(), HomeActivity.class);
                startActivity(backHome);

            }
        });

        return binding.getRoot();
    }
    public void ShowDilogeErrorinfo(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_erorr);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_userProfile_to_information2);

            }
        });
        textView.setText(""+text);
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

}