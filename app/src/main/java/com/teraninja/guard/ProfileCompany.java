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

import com.squareup.picasso.Picasso;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentProfileCompanyBinding;

import java.util.Locale;


public class ProfileCompany extends Fragment {
FragmentProfileCompanyBinding binding;
    MoveViewModel viewModel;
    String url="https://guard.teraninjadev.com/storage/";
    String l;
    SharedPreferences preferences;

    public ProfileCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile_company, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
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
              Picasso.get().load(profile.getData().getImage()).into( binding.imageprofile);
              binding.name.setText(" "+profile.getData().getName());
              binding.typecompany.setText(" "+profile.getData().getCompany_type().getName());
              binding.email.setText(""+profile.getData().getCommercial_registration_no());
              binding.AcuontBank.setText(""+profile.getData().getPhone());
              binding.emailmon.setText(""+profile.getData().getEmail());
              binding.location.setText(""+profile.getData().getCity().getName());
            }
        }
    });
        binding.seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileCompany_to_upditeProfileCompany);
            }
        });
       return binding.getRoot();
    }
}