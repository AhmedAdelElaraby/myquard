package com.teraninja.guard;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.guard.databinding.FragmentHomeBinding;
import com.teraninja.guard.databinding.ItemHeaderBinding;

public class Home extends Fragment{
    FragmentHomeBinding binding;
    ItemHeaderBinding header;
    DrawerLayout drawerLayout;
    NavController navcontroller;
    AppBarConfiguration appBarConfiguration;
    public Home() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        header =DataBindingUtil.inflate(inflater,R.layout.item_header,container,false);
//        appBarConfiguration= new AppBarConfiguration(action_home2_to_information2,binding.drawer);
        header.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ahmed","gmail");
               // Navigation.findNavController(binding.getRoot()).navigate(action_home2_to_information2);
            }
        });





        return binding.getRoot();
    }



}