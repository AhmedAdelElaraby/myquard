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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.guard.DataClient.OncilckEmployees;
import com.teraninja.guard.Model.DataResponcemessage;
import com.teraninja.guard.Model.ModelShowOllguardAcceptOffer;
import com.teraninja.guard.Model.ModelguardAccept;
import com.teraninja.guard.Model.SendDataChatGrupe;
import com.teraninja.guard.Model.SendDataShowOllguardAcceptOffer;
import com.teraninja.guard.UI.Main.AdapterShowguardAcceptOffer;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentShowOllguardAcceptOfferBinding;

import java.util.ArrayList;
import java.util.Locale;


public class ShowguardAcceptOffer extends Fragment implements OncilckEmployees {
FragmentShowOllguardAcceptOfferBinding binding;
String Id;
String typejobid;
    MoveViewModel viewModel;
    SharedPreferences preferences;
    String l;
    String token;
    ArrayList<ModelguardAccept> acceptArrayList= new ArrayList<>();
AdapterShowguardAcceptOffer acceptOffer;
    public ShowguardAcceptOffer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_ollguard_accept_offer, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        if (getArguments()!=null){
            ShowguardAcceptOfferArgs offerArgs = ShowguardAcceptOfferArgs.fromBundle(getArguments());
            Id=offerArgs.getId();
            typejobid=offerArgs.getTypeJobId();
            SendDataShowOllguardAcceptOffer offer = new SendDataShowOllguardAcceptOffer();
            offer.setOffer_id(Id);
            viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer);
            binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        }

        acceptOffer = new AdapterShowguardAcceptOffer(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(acceptOffer);
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu =new PopupMenu(getContext(),binding.filter);
                menu.getMenuInflater().inflate(R.menu.item_menu_popup,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Accept:
                                acceptOffer.getguard().clear();
                                binding.specifiedNumber.setText("0");

                                SendDataShowOllguardAcceptOffer offer = new SendDataShowOllguardAcceptOffer();
                                offer.setOffer_id(Id);
                                offer.setFilter("accept");
                                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer);
                                break;
                            case R.id.rejected:
                                acceptOffer.getguard().clear();
                                binding.specifiedNumber.setText("0");

                                SendDataShowOllguardAcceptOffer offer2 = new SendDataShowOllguardAcceptOffer();
                                offer2.setOffer_id(Id);
                                offer2.setFilter("reject");
                                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer2);
                                break;
                            case R.id.Noresponse:
                                acceptOffer.getguard().clear();
                                binding.specifiedNumber.setText("0");

                                SendDataShowOllguardAcceptOffer offer3 = new SendDataShowOllguardAcceptOffer();
                                offer3.setOffer_id(Id);
                                offer3.setFilter("new");
                                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer3);
                                break;
                            default:
                                SendDataShowOllguardAcceptOffer offer4 = new SendDataShowOllguardAcceptOffer();
                                offer4.setOffer_id(Id);
                                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer4);

                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
        binding.SendOfferJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> ids=acceptOffer.getguard();

                if(ids.isEmpty()) {
                    ShowDilogeError(getString(R.string.Please_select));
                }else{
                    SendDataChatGrupe grupe = new SendDataChatGrupe();
                    grupe.setJop_type_id(typejobid);
                    grupe.setUsers(ids);
                    viewModel.ResponceMessage("Bearer"+token,"application/json",l,grupe);
//
                    Toast.makeText(getContext(), ""+ids.size(), Toast.LENGTH_SHORT).show();
                }

                }
        });
        viewModel.ResponceMessage.observe(getViewLifecycleOwner(), new Observer<DataResponcemessage>() {
            @Override
            public void onChanged(DataResponcemessage dataResponcemessage) {
                Toast.makeText(getContext(), ""+dataResponcemessage.getMessage(), Toast.LENGTH_SHORT).show();
                if (dataResponcemessage.getStatus()==1){
                    Intent intent = new Intent(getContext(),CreateChat.class);

                    intent.putExtra("id",dataResponcemessage.getData().getId());
                    startActivity(intent);
                }
            }
        });
        binding.SelectOll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptOffer.getguard().clear();
                viewModel.ollselected=!viewModel.ollselected;

                acceptOffer.setSelectedAll(viewModel.ollselected);



            }
        });

     viewModel.showOllguardAcceptOffer.observe(getViewLifecycleOwner(), new Observer<ModelShowOllguardAcceptOffer>() {
         @Override
         public void onChanged(ModelShowOllguardAcceptOffer modelShowOllguardAcceptOffer) {
             acceptOffer.setList(modelShowOllguardAcceptOffer.getData().getGuards());

             binding.shimmerViewContainer.setVisibility(View.GONE);

             binding.theTotalNumber.setText(" "+modelShowOllguardAcceptOffer.getData().getGuardsCount());
             Toast.makeText(getContext(), ""+modelShowOllguardAcceptOffer.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });
    binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
//            if (query.isEmpty()){
//                SendDataShowOllguardAcceptOffer offer5 = new SendDataShowOllguardAcceptOffer();
//                offer5.setOffer_id(Id);
//
//                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer5);
//
//
//            }else {
//                SendDataShowOllguardAcceptOffer offer5 = new SendDataShowOllguardAcceptOffer();
//                offer5.setOffer_id(Id);
//                offer5.setSearch(query);
//                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer5);
//
//            }

//            ArrayList<ModelguardAccept> modelguardAccept=filter(acceptOffer.getList(),query);
//            if (modelguardAccept!=null){
//                // this user is find
//                acceptOffer.setList(modelguardAccept);
//
//            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (newText.isEmpty()){
                acceptOffer.getguard().clear();
                binding.specifiedNumber.setText("0");

                SendDataShowOllguardAcceptOffer offer5 = new SendDataShowOllguardAcceptOffer();
                offer5.setOffer_id(Id);
                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer5);

            }else{

                SendDataShowOllguardAcceptOffer offer5 = new SendDataShowOllguardAcceptOffer();
                offer5.setOffer_id(Id);
                offer5.setSearch(newText);
                viewModel.getOllguardAcceptOffer("Bearer"+token,"application/json",l,offer5);
            }

//            ArrayList<ModelguardAccept> modelguardAccept=filter(acceptOffer.getList(),newText);
//            if (modelguardAccept!=null){
//                // this user is find
//                acceptOffer.setList(modelguardAccept);
//
//            }
            return false;
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
        ShowguardAcceptOfferDirections.ActionShowOllguardAcceptOfferToSubmitJobOffer offer =ShowguardAcceptOfferDirections.actionShowOllguardAcceptOfferToSubmitJobOffer();
        offer.setIdguard(getidoneguard);
        Navigation.findNavController(binding.getRoot()).navigate(offer);
    }
    private  ArrayList<ModelguardAccept> filter(ArrayList<ModelguardAccept> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<ModelguardAccept> filteredModelList = new ArrayList<>();
        for (ModelguardAccept model : models) {
            final String text = model.getUser().getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
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
                }
            });
            textView.setText(""+text);
            Window window = myDialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.create();
            myDialog.show();
        }


}