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

import com.teraninja.guard.DataClient.Accept;
import com.teraninja.guard.Model.DataOfferguard;
import com.teraninja.guard.Model.Reedollnav;
import com.teraninja.guard.UI.Main.AdapterOffersguard;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentOffersBinding;

import java.util.Locale;


public class offers extends Fragment implements Accept {
FragmentOffersBinding binding;
    String l;
    SharedPreferences preferences;
    String token;
    MoveViewModel viewModel;
    public offers() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_offers, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
       viewModel.getofferguard("Bearer" + token, "application/json",l);
        AdapterOffersguard offersguard = new AdapterOffersguard(this);
       binding.recoffersguard.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       binding.recoffersguard.setAdapter(offersguard);
       viewModel.getoffreguard.observe(getViewLifecycleOwner(), new Observer<DataOfferguard>() {
           @Override
           public void onChanged(DataOfferguard dataOfferguard) {
               offersguard.getList(dataOfferguard.getData());
               offersguard.notifyDataSetChanged();
           }
       });
       viewModel.Accepted.observe(getViewLifecycleOwner(), new Observer<Reedollnav>() {
           @Override
           public void onChanged(Reedollnav reedollnav) {
               Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });
       viewModel.Reject.observe(getViewLifecycleOwner(), new Observer<Reedollnav>() {
           @Override
           public void onChanged(Reedollnav reedollnav) {
               Toast.makeText(getContext(), ""+reedollnav.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
       return binding.getRoot();
    }

    @Override
    public void Accept(int id) {
    viewModel.Accepted("Bearer" + token, "application/json",l,id);
    }

    @Override
    public void cancel(int id) {
    viewModel.Reject("Bearer" + token, "application/json",l,id);
    }
}