package com.teraninja.guard;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.guard.DataClient.OnClickShowOllStaff;
import com.teraninja.guard.DataClient.OnclikgetPackagecompany;
import com.teraninja.guard.Model.DataOllStaff;
import com.teraninja.guard.Model.DataPackageCompany;
import com.teraninja.guard.Model.DataPaymentCompany;
import com.teraninja.guard.Model.DataSendPayment;
import com.teraninja.guard.Model.DataSendSearchstaff;
import com.teraninja.guard.Model.SendCvs;
import com.teraninja.guard.UI.Main.AdapterPackageCompany;
import com.teraninja.guard.UI.Main.AdapterShowAllStaff;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentShowOllStaffBinding;

import java.util.ArrayList;
import java.util.Locale;


public class ShowOllStaff extends Fragment implements OnclikgetPackagecompany, OnClickShowOllStaff {
FragmentShowOllStaffBinding binding;
 Context context;
MoveViewModel viewModel;
String l;
    int cheek;
    Dialog myDialogpackage;
    SharedPreferences preferences;
    String type;
    String token;
    String Url;
    String titile;
    String price;
    String count;
    String taxrate;
    String taxvalue;
    Dialog myDialog;
    ArrayList<String> listget= new ArrayList<>();
    public ShowOllStaff() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_oll_staff, container, false);
       binding.shimmerViewContainer.setVisibility(View.VISIBLE);
       viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        type = preferences.getString("Type","no");
        token = preferences.getString("Token","no");

        if (getArguments()!=null){
            ShowOllStaffArgs args =ShowOllStaffArgs.fromBundle(getArguments());

            DataSendSearchstaff searchstaff = new DataSendSearchstaff();
            searchstaff.setJop_type_id(args.getJobTitle());
            searchstaff.setCity_id(args.getCity());
            searchstaff.setAge_from(args.getAgefrom());
            searchstaff.setAge_to(args.getAgeto());
            searchstaff.setNumber_of_guards(args.getNumberRequired());
            searchstaff.setGender(args.getGender());
            searchstaff.setExperience(args.getExperience());
            searchstaff.setQualification(args.getQualification());
            searchstaff.setEnglish(args.getEnglishLanguageLevel());

            viewModel.getOllstaff("Bearer"+token
                                    ,"application/json",l,searchstaff);
        }

        AdapterShowAllStaff staff = new AdapterShowAllStaff(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(staff);
        viewModel.getollstaff.observe(getViewLifecycleOwner(), new Observer<DataOllStaff>() {
            @Override
            public void onChanged(DataOllStaff dataOllStaff) {
                binding.theTotalNumber.setText(""+dataOllStaff.getData().getCount());
                binding.shimmerViewContainer.setVisibility(View.GONE);
                staff.getList(dataOllStaff.getData().getGuards());
            }
        });

        binding.SelectOll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staff.getguard().clear();
                viewModel.ollselected=!viewModel.ollselected;

                staff.setSelectedAll(viewModel.ollselected);



            }
        });

       binding.bay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               ArrayList<String> ids=staff.getguard();
               if(ids.isEmpty()){
                   ShowDilogeError(getString(R.string.Please_select));
               }else {
                   Toast.makeText(getContext(), ""+ids.get(0), Toast.LENGTH_SHORT).show();
                   viewModel.SendCVS("Bearer" + token, "application/json", l, ids);
               }






           }
       });
    viewModel.SendCvs.observe(getViewLifecycleOwner(), new Observer<SendCvs>() {
        @Override
        public void onChanged(SendCvs sendCvs) {
            if (sendCvs.getStatus()==1){
                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.dilog_of_the_situation);
                ImageView imageView = myDialog.findViewById(R.id.imagedone);
                TextView textView = myDialog.findViewById(R.id.textdone);
                textView.setText(sendCvs.getMessage());
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                Window window = myDialog.getWindow();

                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.create();
                myDialog.show();
                NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.showOllStaff, true).build();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showOllStaff_to_filterGuardWthiCompany,savedInstanceState,builder);
            }else {
                Toast.makeText(context, ""+sendCvs.getMessage(), Toast.LENGTH_SHORT).show();
                showpackage();
            }
            Toast.makeText(context, ""+sendCvs.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });
       return binding.getRoot();
    }
    public void showpackage(){
        viewModel.getPackageCompany("Bearer"+token
                ,"application/json",l);

        myDialogpackage = new Dialog(context);
        myDialogpackage.setContentView(R.layout.item_diloge_package_company);
        RecyclerView addressesRV;
        addressesRV = myDialogpackage.findViewById(R.id.recdiloge);
        AdapterPackageCompany company = new AdapterPackageCompany(this);
        addressesRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        addressesRV.setAdapter(company);
    viewModel.getPackageCompany.observe(getViewLifecycleOwner(), new Observer<DataPackageCompany>() {
        @Override
        public void onChanged(DataPackageCompany dataPackageCompany) {
            company.getList(dataPackageCompany.getData());
        }
    });

        Window window = myDialogpackage.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        myDialogpackage.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogpackage.create();
        myDialogpackage.show();
    }
    public void ShowDilogeBill(String titile,String count,String texrate,String taxvalue,String price,String url){
        Dialog myDialog;
        TextView namePackage,packagepric,number,TaxText,numberTax;
        Button button;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.item_diloge_payment_company);
        namePackage=myDialog.findViewById(R.id.namePackage);
        packagepric=myDialog.findViewById(R.id.packagepric);
        number=myDialog.findViewById(R.id.number);
        TaxText=myDialog.findViewById(R.id.TaxText);
        numberTax=myDialog.findViewById(R.id.numberTax);
        button=myDialog.findViewById(R.id.bay);
        numberTax.setText(texrate+" "+"SR");
        TaxText.setText("tax"+"("+taxvalue+")");
        number.setText(count);
        namePackage.setText(" "+titile);
        packagepric.setText(price+" "+"SR");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent intent = new Intent(getContext(),PaymentCompany.class);
                intent.putExtra("url",url);
                startActivityForResult(intent,50);

            }
        });
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }



    @Override
    public void getidOllStaff(int id_size) {

        binding.specifiedNumber.setText(""+id_size);

    }
    public void ShowDilogeError(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(context);
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

    @Override
    public void gettypepackage(int packageid, String packagetype) {

            String idpackage=String.valueOf(packageid);
            DataSendPayment payment = new DataSendPayment();
            payment.setPackage_id(idpackage);
            payment.setType(type);
            payment.setPackage_type(packagetype);
            payment.setNo_of_cvs(""+cheek);
      //  Toast.makeText(context, ""+cheek+""+idpackage+packagetype+type, Toast.LENGTH_SHORT).show();
        viewModel.getPaymentcompany("Bearer"+token,"application/json",l,payment);
            viewModel.Paymentcompany.observe(getViewLifecycleOwner(), new Observer<DataPaymentCompany>() {
                @Override
                public void onChanged(DataPaymentCompany dataPaymentCompany) {
                    if (dataPaymentCompany.getStatus()==1){
                        Url=dataPaymentCompany.getData().getUrl();
                        titile=""+dataPaymentCompany.getData().getData().getTitle();
                        count=""+dataPaymentCompany.getData().getData_price().getCount();
                        taxrate= ""+dataPaymentCompany.getData().getData_price().getTax_rate();
                        taxvalue=""+dataPaymentCompany.getData().getData_price().getTax_value();
                        price=""+dataPaymentCompany.getData().getData().getPrice();
                        myDialogpackage.dismiss();
                        ShowDilogeBill(titile,count,taxvalue,taxrate,price,Url);
                        dataPaymentCompany.setStatus(2);
                    }




                }
            });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case 50:
                        boolean stust= data.getBooleanExtra("stuts",false);
                        if (stust) {
                            viewModel.SendCVS("Bearer" + token, "application/json", l, listget);
                        }else{
                            myDialog = new Dialog(context);
                            myDialog.setContentView(R.layout.dilog_of_the_situation);
                            ImageView imageView = myDialog.findViewById(R.id.imagefalied);
                            TextView textView = myDialog.findViewById(R.id.textdone);

                            textView.setText(R.string.not_completed);
                            imageView.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            Window window = myDialog.getWindow();

                            window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            myDialog.create();
                            myDialog.show();
                        }
                        break;




                }
                break;
            case RESULT_CANCELED:
                break;

        }
    }
}