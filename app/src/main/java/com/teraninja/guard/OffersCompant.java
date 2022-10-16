package com.teraninja.guard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.guard.DataClient.OnclickIdOffer;
import com.teraninja.guard.Model.DataOfferCompany;
import com.teraninja.guard.UI.Main.AdapterOffersCompany;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentOffersBinding;
import com.teraninja.guard.databinding.FragmentOffersCompantBinding;

import java.util.Locale;


public class OffersCompant extends Fragment implements OnclickIdOffer {
MoveViewModel viewModel;
FragmentOffersCompantBinding binding;
    String l;
    SharedPreferences preferences;
    String token;

    public OffersCompant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_offers_compant, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);

        viewModel.getofferscampany("Bearer" + token, "application/json",l);
        AdapterOffersCompany company = new AdapterOffersCompany(this);
        binding.recoffersguard.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.recoffersguard.setAdapter(company);
        viewModel.getoffrescompany.observe(getViewLifecycleOwner(), new Observer<DataOfferCompany>() {
            @Override
            public void onChanged(DataOfferCompany dataOfferCompany) {
                binding.shimmerViewContainer.setVisibility(View.GONE);
                company.getList(dataOfferCompany.getData());
            }
        });
        return binding.getRoot();
    }

    @Override
    public void getIdOffer(int id,int type_job_id) {
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        OffersCompantDirections.ActionOffersCompantToShowOllguardAcceptOffer offer =OffersCompantDirections.actionOffersCompantToShowOllguardAcceptOffer();
        offer.setId(String.valueOf(id));
        offer.setTypeJobId(String.valueOf(type_job_id));
        Navigation.findNavController(binding.getRoot()).navigate(offer);
    }
}