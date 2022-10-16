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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.guard.DataClient.OncilckEmployees;
import com.teraninja.guard.Model.DataGuardPayment;
import com.teraninja.guard.Model.DataPayCvs;
import com.teraninja.guard.UI.Main.AdapterEmployees;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentFilterGuardWthiCompanyBinding;

import java.util.ArrayList;
import java.util.Locale;


public class filterGuardWthiCompany extends Fragment implements OncilckEmployees {
FragmentFilterGuardWthiCompanyBinding binding;
    MoveViewModel viewModel;
    SharedPreferences preferences;
    String l;
    String token;
    ArrayList<DataGuardPayment> list= new ArrayList<>();

    public filterGuardWthiCompany() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_filter_guard_wthi_company, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        viewModel.getguardscompany("Bearer"+token,"application/json",l);
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        AdapterEmployees employees = new AdapterEmployees(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(employees);
        binding.SendOfferJob.setOnClickListener(v -> {

            ArrayList<String> ids=employees.getguard();
            if(ids.isEmpty()){
                ShowDilogeError(getString(R.string.Please_select));
            }else {
                Intent intent= new Intent(getContext(),SendOfferJobToGuard.class);
                intent.putExtra("id",ids);
                startActivity(intent);

            }
        });
        viewModel.getguardscompany.observe(getViewLifecycleOwner(), new Observer<DataPayCvs>() {
            @Override
            public void onChanged(DataPayCvs dataPayCvs) {

                if (dataPayCvs.getStatus()==1){
                    for (int i=0;i<dataPayCvs.getData().getGuards().size();i++){
                        list.add(dataPayCvs.getData().getGuards().get(i));
                    }
                    Toast.makeText(getContext(), ""+dataPayCvs.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.theTotalNumber.setText(""+dataPayCvs.getData().getCount());
                    binding.shimmerViewContainer.setVisibility(View.GONE);
                    employees.setList(dataPayCvs.getData().getGuards());

                    dataPayCvs.setStatus(2);
                }else {
                    binding.shimmerViewContainer.setVisibility(View.GONE);

                }

            }
        });
        binding.SelectOll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employees.getguard().clear();
                viewModel.ollselected=!viewModel.ollselected;

                employees.setSelectedAll(viewModel.ollselected);



            }
        });
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    employees.getguard().clear();
                    binding.specifiedNumber.setText("0");
                    viewModel.getguardscompany("Bearer"+token,"application/json",l);

                }else{
                    employees.getguard().clear();
                    binding.specifiedNumber.setText("0");
                    ArrayList<DataGuardPayment> modelguardAccept=filter(employees.getList(),newText);
                    if (modelguardAccept!=null){
                        // this user is find
                        employees.setList(modelguardAccept);

                    }
                }


                return false;
            }
        });
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchguard();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void getid(int id_size) {
        binding.specifiedNumber.setText(""+id_size);

    }

    @Override
    public void getidoneguard(int getidoneguard) {
        filterGuardWthiCompanyDirections.ActionFilterGuardWthiCompanyToSubmitJobOffer offer =filterGuardWthiCompanyDirections.actionFilterGuardWthiCompanyToSubmitJobOffer();
        offer.setIdguard(getidoneguard);
        Navigation.findNavController(binding.getRoot()).navigate(offer);

        Toast.makeText(getContext(), ""+getidoneguard, Toast.LENGTH_SHORT).show();
    }
    public void ShowDilogeError(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_erorr);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
//
            }
        });
        textView.setText(""+text);
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    public void Searchguard(){
        Dialog myDialog;

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemsearchgurd);

        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

    private  ArrayList<DataGuardPayment> filter(ArrayList<DataGuardPayment> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<DataGuardPayment> filteredModelList = new ArrayList<>();
        for (DataGuardPayment model : models) {
            final String text = model.getCompany_guard().getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}