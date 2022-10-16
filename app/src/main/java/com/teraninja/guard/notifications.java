package com.teraninja.guard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.DataClient.OncilckNotification;
import com.teraninja.guard.Model.DataNotifications;
import com.teraninja.guard.Model.Reedollnav;
import com.teraninja.guard.UI.Main.AdapterNotification;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.Locale;


public class notifications extends Fragment implements OncilckNotification {
FragmentNotificationsBinding binding;
MoveViewModel viewModel;
    String l;
    SharedPreferences preferences;
    String token;
ArrayList<String> list= new ArrayList<>();

    public notifications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_notifications, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        if (token.isEmpty()){

        }
        if (token.equals("no")){

        }
        else {
            viewModel.getOllNotifications("Bearer"+token,
                    "application/json",l);
        }

        binding.shimmerViewContainer.setVisibility(View.VISIBLE);

        AdapterNotification adapter= new AdapterNotification(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(adapter);
 viewModel.Notifications.observe(getViewLifecycleOwner(), new Observer<DataNotifications>() {
     @Override
     public void onChanged(DataNotifications dataNotifications) {
         if (dataNotifications.getData()!=null) {
             binding.nofound.setVisibility(View.GONE);
                binding.shimmerViewContainer.setVisibility(View.GONE);
             adapter.getList(dataNotifications.getData());
         }else {
             binding.nofound.setVisibility(View.VISIBLE);
         }
     }
 });
        binding.textoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.Reedollnav("Bearer"+token,
                        "application/json",l);
            }
        });
    viewModel.Reedol.observe(getViewLifecycleOwner(), new Observer<Reedollnav>() {
        @Override
        public void onChanged(Reedollnav reedollnav) {
            Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
       return binding.getRoot();
    }

    @Override
    public void getidNotifications(int id) {

    }
}