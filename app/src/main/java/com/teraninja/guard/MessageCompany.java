package com.teraninja.guard;

import android.content.Context;
import android.content.Intent;
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

import com.teraninja.guard.DataClient.OnclickIdMessage;
import com.teraninja.guard.Model.DataMessageCompany;
import com.teraninja.guard.UI.Main.AdapterMessagesCompany;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentMessageCompanyBinding;

import java.util.Locale;


public class MessageCompany extends Fragment implements OnclickIdMessage {
FragmentMessageCompanyBinding binding;
    MoveViewModel viewModel;
    String l;
    String token;
    SharedPreferences preferences;
    String type;

    public MessageCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_message_company, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        l= Locale.getDefault().getLanguage();
        type = preferences.getString("Type","no");
        viewModel.MessageCompany("Bearer"+token
                ,"application/json",l);

        AdapterMessagesCompany messages = new AdapterMessagesCompany(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(messages);
        viewModel.MessageCompany.observe(getViewLifecycleOwner(), new Observer<DataMessageCompany>() {
            @Override
            public void onChanged(DataMessageCompany dataMassege) {
                Toast.makeText(getContext(), ""+dataMassege.getMessage(), Toast.LENGTH_SHORT).show();
                if (dataMassege.getData()==null){

                }else {
                    messages.getList(dataMassege.getData());

                }
                // messages.getList(dataMassege.getData());
            }
        });



        return binding.getRoot();

    }

    @Override
    public void getId(int id) {
        Intent intent = new Intent(getContext(),CreateChat.class);

        intent.putExtra("id",id);
        startActivity(intent);
    }
}