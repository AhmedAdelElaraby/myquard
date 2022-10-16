package com.teraninja.guard.UI;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.teraninja.guard.utils.ImagePickerHelper.getPathFromUri;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.guard.Model.DataCompany;
import com.teraninja.guard.Model.DataSendCompany;
import com.teraninja.guard.Model.DataTems;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.ModelTypeCompany;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentCreateEmailCompanyBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class CreateEmailCompany extends Fragment {
FragmentCreateEmailCompanyBinding binding;
MoveViewModel viewModel;
ArrayList<String> listTypecompany= new ArrayList<>();
ArrayList<Integer> listtypeidCompany= new ArrayList<Integer>();
String Id;
String l;
int cuontrey;

    ArrayList<Integer> listid= new ArrayList<>();
    ArrayList<String> listcitys= new ArrayList<>();
    public CreateEmailCompany() {
        // Required empty public constructor
    }



    Uri uri;
    MultipartBody.Part part = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_create_email_company, container, false);
       viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        viewModel.getTypeCompany("application/json",l);
        viewModel.getCity("application/json",l);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.inputTextLayoutcommercialregisterphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        // Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };
                TedPermission.create()
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
            }
        });
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberhostriy=binding.CommercialRegistrationNo.getText().toString();
                String namecompany=binding.CompanyName.getText().toString().trim();
                String numberphone=binding.numberphone.getText().toString().trim();
                String email=binding.email.getText().toString().trim();
                String location=String.valueOf(cuontrey);
                String password=binding.password.getText().toString();
                String confirmpassword=binding.confermpassword.getText().toString();
                boolean cheek=binding.yes.isChecked();
                if (numberhostriy.isEmpty()){
                    binding.CommercialRegistrationNo.setError(getString(R.string.commercial_registration_no));
                    return;
                }
                if (namecompany.isEmpty()){
                    binding.CompanyName.setError(getString(R.string.company_name));
                    return;
                }
                if (numberphone.isEmpty()){
                    binding.numberphone.setError(getString(R.string.number_phone));
                    return;
                }
                if (email.isEmpty()){
                    binding.email.setError(getString(R.string.email));
                    return;
                }
                if (location.equals("0")){
                    Toast.makeText(getContext(), ""+getString(R.string.Headquarters), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()){
                    binding.password.setError(getString(R.string.password));
                    return;
                }
                if (Id.equals("0")){
                    Toast.makeText(getContext(), ""+getString(R.string.typecompany), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirmpassword.isEmpty()){
                    binding.confermpassword.setError(getString(R.string.confirm_password));
                    return;
                }
                if (cheek==true){

                }if (cheek==false){
                    binding.yes.setError(getString(R.string.yes));
                    return;
                }
                else{
                    DataSendCompany sendCompany = new DataSendCompany(namecompany,email,numberphone,password,confirmpassword,"company",numberhostriy,part,Id,location);
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("name", String.valueOf(sendCompany.getName()))
                            .addFormDataPart("email", sendCompany.getEmail())
                            .addFormDataPart("phone", sendCompany.getPhone())
                            .addFormDataPart("password", sendCompany.getPassword())
                            .addFormDataPart("password_confirmation", sendCompany.getPassword_confirmation())
                            .addFormDataPart("type", "company")
                            .addFormDataPart("commercial_registration_no", String.valueOf(sendCompany.getCommercial_registration_no()))
                            .addFormDataPart("company_type_id", String.valueOf(sendCompany.getCompany_type_id()))
                            .addFormDataPart("city_id", String.valueOf(sendCompany.getCity_id()));
                    if (sendCompany.getCommercial_registration_image() != null) {

                        builder.addPart(sendCompany.getCommercial_registration_image());
                        RequestBody requestBody = builder.build();


                        viewModel.Company("application/json",l,requestBody);
                        binding.spinkit.setVisibility(View.VISIBLE);
                    }else {
                        binding.inputTextLayoutcommercialregisterphoto.setError("Select Commercial registration image ");

                    }






                }
            }
        });
viewModel.getTypeCompany.observe(getViewLifecycleOwner(), new Observer<ModelTypeCompany>() {
    @Override
    public void onChanged(ModelTypeCompany modelTypeCompany) {
        listTypecompany.clear();
        listtypeidCompany.clear();
        listtypeidCompany.add(0);
        listTypecompany.add(getString(R.string.typecompany));

        for (int i=0;i<modelTypeCompany.getData().size();i++){
            listTypecompany.add(modelTypeCompany.getData().get(i).getName());
            listtypeidCompany.add(modelTypeCompany.getData().get(i).getId());
        }
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTypecompany);
         binding.TypeofCompany.setAdapter(adapter);
    }
});
binding.TypeofCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       Id=String.valueOf(listtypeidCompany.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        viewModel.getcity.observe(getViewLifecycleOwner(), new Observer<ModelCity>() {
            @Override
            public void onChanged(ModelCity modelCity) {
                listcitys.clear();
                listid.clear();
                listcitys.add(getString(R.string.Headquarters));
                listid.add(0);
                for (int i =0; i<modelCity.getData().size();i++){

                    listid.add(modelCity.getData().get(i).getId());
                    listcitys.add(modelCity.getData().get(i).getName());

                }
                ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listcitys);
                binding.Headquarters.setAdapter(adaptercity);
            }
        });
        binding.Headquarters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuontrey= listid.get(position);

              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    viewModel.dataCompany.observe(getViewLifecycleOwner(), new Observer<DataCompany>() {
        @Override
        public void onChanged(DataCompany dataCompany) {
            if (dataCompany.getStatus()==1){
                binding.spinkit.setVisibility(View.GONE);
                showAddressesPopup(dataCompany.getMessage());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.createEmailCompany, true).build();
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createEmailCompany_to_login2,savedInstanceState,builder);
                }
            }, 3000);


            }else {
                binding.spinkit.setVisibility(View.GONE);

                Toast.makeText(getContext(), ""+dataCompany.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }
    });
    binding.yes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShowDilogeError();
        }
    });
        return binding.getRoot();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Create a file object using file path
      //TODO get URI And put it into uri var
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case 1:
                        uri=data.getData();
                        binding.inputTextLayoutcommercialregisterphoto.setText(""+uri.getPath());
                        saveImage();
                        break;


                }
                break;
            case RESULT_CANCELED:
                break;

        }



    }

    private void saveImage() {
        RequestBody fileReqBody = null;

        File file = new File(getPathFromUri(getContext(),uri));
        // Create a request body with file and image media type
        fileReqBody = RequestBody.create( file, MediaType.parse("image/*"));
        // Create MultipartBody.Part using file request-body,file name and part name
        part = MultipartBody.Part.createFormData("commercial_registration_image", file.getName(), fileReqBody);
    }

    public void ShowDilogeError(){
        Dialog myDialog;
        TextView textView;
        TextView textv;
        Button button;
        View view;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_erorr);
        viewModel.gettems("application/json",l);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        view=myDialog.findViewById(R.id.view);
        textv=myDialog.findViewById(R.id.text);
        textv.setText(R.string.Terms_and_Conditions);
        view.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        viewModel.gettems.observe(getViewLifecycleOwner(), new Observer<DataTems>() {
            @Override
            public void onChanged(DataTems dataTems) {
                textView.setText(""+dataTems.getData().getTerms()+"\n"+dataTems.getData().getPolicy());
            }
        });

        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    public void showAddressesPopup (String message) {
        Dialog myDialog;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dilog_of_the_situation);
        ImageView imageView;
        TextView textView;
        imageView=myDialog.findViewById(R.id.imagedone);
        textView=myDialog.findViewById(R.id.textdone);
        textView.setVisibility(View.VISIBLE);
        textView.setText(""+message);
        imageView.setVisibility(View.VISIBLE);
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

}