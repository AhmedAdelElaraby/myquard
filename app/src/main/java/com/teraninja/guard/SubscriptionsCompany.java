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

import com.teraninja.guard.Model.DataSubscribeCompany;
import com.teraninja.guard.UI.Main.AdapterSubscribe;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentSubscriptionsCompanyBinding;

import java.util.Locale;


public class SubscriptionsCompany extends Fragment {
FragmentSubscriptionsCompanyBinding binding;
MoveViewModel viewModel;
    String l;
    SharedPreferences preferences;
    String token;
    public SubscriptionsCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_subscriptions_company, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
       viewModel.getSubscribeCompany("Bearer" + token, "application/json",l);

        AdapterSubscribe subscribe = new AdapterSubscribe();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(subscribe);
        viewModel.dataSubscribeCompany.observe(getViewLifecycleOwner(), new Observer<DataSubscribeCompany>() {
            @Override
            public void onChanged(DataSubscribeCompany dataSubscribeCompany) {
                subscribe.getList(dataSubscribeCompany.getData());
            }
        });

        return binding.getRoot();
    }
}