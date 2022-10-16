package com.teraninja.guard;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.guard.databinding.FragmentShowPostOnleyBinding;


public class ShowPostOnley extends Fragment {
FragmentShowPostOnleyBinding binding;

    public ShowPostOnley() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_post_onley, container, false);
        if (getArguments()!=null){
            ShowPostOnleyArgs onleyArgs =ShowPostOnleyArgs.fromBundle(getArguments());
            binding.time.setText(onleyArgs.getTime());
            binding.post.setText(onleyArgs.getPost());

        }
    binding.back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    });

        return binding.getRoot();

    }
}