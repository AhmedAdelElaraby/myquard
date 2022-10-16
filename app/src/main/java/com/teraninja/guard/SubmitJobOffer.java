package com.teraninja.guard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.Model.DataShowUserByUser;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentSubmitJobOfferBinding;

import java.util.ArrayList;
import java.util.Locale;


public class SubmitJobOffer extends Fragment {
FragmentSubmitJobOfferBinding binding;
    MoveViewModel viewModel;
    String url="https://guard.teraninjadev.com/storage/";
    String l;
    SharedPreferences preferences;
    int id ;
    ArrayList<String> list= new ArrayList<>();
    public SubmitJobOffer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_submit_job_offer, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getContext().getSharedPreferences("ar", Context.MODE_PRIVATE);
        String token=preferences.getString("Token","no");
        if (getArguments()!=null){
            SubmitJobOfferArgs args =SubmitJobOfferArgs.fromBundle(getArguments());
           int id  = args.getIdguard();
           list.add(String.valueOf(id));
            viewModel.ShowUserByUser("Bearer"+token,
                    "application/json",l,id);

        }


   viewModel.ShowUserByUser.observe(getViewLifecycleOwner(), new Observer<DataShowUserByUser>() {
       @Override
       public void onChanged(DataShowUserByUser dataShowUserByUser) {
           if (dataShowUserByUser.getStatus()==1){
               id=dataShowUserByUser.getData().getId();
               binding.name.setText(dataShowUserByUser.getData().getName());
               binding.numberphone.setText(dataShowUserByUser.getData().getPhone());
               Picasso.get().load(url+dataShowUserByUser.getData().getImage()).into(binding.Imageprofile);
               binding.theAge.setText(getString(R.string.the_age)+" : "+dataShowUserByUser.getData().getAge());
               binding.gender.setText(getString(R.string.gender)+" : "+dataShowUserByUser.getData().getGender());
               if (dataShowUserByUser.getData().getJop_type()!=null){
                   binding.posation.setText(getString(R.string.posation)+" : "+dataShowUserByUser.getData().getJop_type().getName());

               }
               if (dataShowUserByUser.getData().getCity()!=null&&dataShowUserByUser.getData().getDistrict()!=null){
                   binding.cuantry.setText(getString(R.string.country)+" : "+dataShowUserByUser.getData().getCity().getName()+dataShowUserByUser.getData().getDistrict().getName());

               }
               binding.qualification.setText(getString(R.string.qualification)+" : "+dataShowUserByUser.getData().getQualification());
               binding.SocialStatus.setText(getString(R.string.social_status)+" : "+dataShowUserByUser.getData().getSocial_status());
               binding.email.setText(" "+dataShowUserByUser.getData().getEmail());
               binding.exparice.setText("  "+dataShowUserByUser.getData().getExperience());
               binding.numberJobOffers.setText(" "+dataShowUserByUser.getData().getOffer_me());
               binding.numberAppear.setText(" "+dataShowUserByUser.getData().getAppear());
               binding.countreyjob.setText("  "+dataShowUserByUser.getData().getOther_cities());
               binding.AcuontBank.setText(" "+dataShowUserByUser.getData().getIban_no());
               // binding.numberSubscriptions.setText(""+profile.getData().get);


           }

       }
   });
        binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SendOfferJobToGuard.class);
                intent.putExtra("id",list);
                startActivity(intent);
            }
        });
       return binding.getRoot();
    }
}