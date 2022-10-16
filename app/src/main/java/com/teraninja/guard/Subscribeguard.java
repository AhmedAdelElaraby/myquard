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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.guard.DataClient.InterFacePackageId;
import com.teraninja.guard.Model.DataPackage;
import com.teraninja.guard.Model.DataSubscribeCompany;
import com.teraninja.guard.UI.Main.AdapterPayment;
import com.teraninja.guard.UI.Main.AdapterSubscribe;
import com.teraninja.guard.UI.Main.Payment;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentSubscribeguardBinding;

import java.util.Locale;
public class Subscribeguard extends Fragment implements InterFacePackageId {
FragmentSubscribeguardBinding binding;
    MoveViewModel viewModel;
    String l;
    SharedPreferences preferences;
    String token;
    Dialog myDialog;
    public Subscribeguard() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_subscribeguard, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        viewModel.getSubscribeguard("Bearer" + token, "application/json",l);
        AdapterSubscribe subscribe = new AdapterSubscribe();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(subscribe);
        viewModel.dataSubscribeguard.observe(getViewLifecycleOwner(), new Observer<DataSubscribeCompany>() {
            @Override
            public void onChanged(DataSubscribeCompany dataSubscribeCompany) {
                subscribe.getList(dataSubscribeCompany.getData());
            }
        });
    binding.Send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAddressesPopup();
        }
    });
        return binding.getRoot();
    }
    public void showAddressesPopup () {

        viewModel.getPackage("Bearer"+token,"application/json",l);
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_diloge_package);
        RecyclerView addressesRV;
        TextView textView;
        textView=myDialog.findViewById(R.id.text);
        addressesRV = myDialog.findViewById(R.id.recdiloge);
        AdapterPayment adapterpayment = new AdapterPayment(this);
        addressesRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        addressesRV.setAdapter(adapterpayment);
        viewModel.getPackage.observe(getViewLifecycleOwner(), new Observer<DataPackage>() {
            @Override
            public void onChanged(DataPackage dataPackage) {
                // Toast.makeText(context, ""+dataPackage.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(""+dataPackage.getMessage());
                adapterpayment.getList(dataPackage.getData());
            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    @Override
    public void getPackageId(int packageId) {
        Toast.makeText(getContext(), ""+packageId, Toast.LENGTH_SHORT).show();
        String packageidsend=String.valueOf(packageId);
        Intent intent = new Intent(getContext(), Payment.class);
        intent.putExtra("Packageid",packageidsend);
        intent.putExtra("type","guard");
        startActivityForResult(intent,50);
        myDialog.dismiss();
    }
}