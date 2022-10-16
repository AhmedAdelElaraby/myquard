package com.teraninja.guard;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.teraninja.guard.utils.ImagePickerHelper.getPathFromUri;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.ModelTypeCompany;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.Model.SendDataUpdateProfileCompany;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUpditeProfileCompanyBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpditeProfileCompany extends Fragment {
FragmentUpditeProfileCompanyBinding binding;
    MoveViewModel viewModel;
    String url="https://guard.teraninjadev.com/storage/";
    String l;
    Uri uri;
    SharedPreferences preferences;
    ArrayList<String> arrayListname= new ArrayList<>();
    ArrayList<Integer> arrayListid= new ArrayList<>();
    ArrayList<String> typecompanyname= new ArrayList<>();
    ArrayList<Integer> typecompanyid= new ArrayList<>();
    int city_id;
    int company_id;
    MultipartBody.Part part = null;

    public UpditeProfileCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     binding= DataBindingUtil.inflate(inflater,R.layout.fragment_updite_profile_company, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        String token=preferences.getString("Token","no");
        if (token.isEmpty()){

        }
        if (token.equals("no")){

        }
        else {
            viewModel.getProfile("Bearer"+token,
                    "application/json",l);



        }
        binding.inputTextLayoutnumberphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_upditeProfileCompany_to_updatePhone);
            }
        });
        viewModel.getProfile.observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                if (profile!=null){
                    binding.CompanyName.setText(" "+profile.getData().getName());
                    binding.email.setText(""+profile.getData().getEmail());
                    binding.inputTextLayoutnumberphone.setText(""+profile.getData().getPhone());
                    binding.CommercialRegistrationNo.setText(""+profile.getData().getCommercial_registration_no());
                    arrayListname.add(profile.getData().city.getName());
                    arrayListid.add(profile.getData().city.getId());
                    typecompanyname.add(profile.getData().company_type.getName());
                    typecompanyid.add(profile.getData().company_type.getId());

                }
                ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayListname);
                binding.inputTextLayoutHeadquarters.setAdapter(adaptercity);
                ArrayAdapter<String> typycompany = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, typecompanyname);
                binding.TypeofCompany.setAdapter(typycompany);
                viewModel.getCity("application/json",l);
                viewModel.getTypeCompany("application/json",l);
            }
        });
      viewModel.getcity.observe(getViewLifecycleOwner(), new Observer<ModelCity>() {
          @Override
          public void onChanged(ModelCity modelCity) {
              for (int i=0;i<modelCity.getData().size();i++){
                  arrayListname.add(modelCity.getData().get(i).getName());
                  arrayListid.add(modelCity.getData().get(i).getId());


              }
          }
      });
      binding.inputTextLayoutHeadquarters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            city_id= arrayListid.get(position);
              Toast.makeText(getContext(), ""+city_id, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
      binding.TypeofCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              company_id=typecompanyid.get(position);
              Toast.makeText(getContext(), ""+company_id, Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });

        viewModel.getTypeCompany.observe(getViewLifecycleOwner(), new Observer<ModelTypeCompany>() {
            @Override
            public void onChanged(ModelTypeCompany modelTypeCompany) {


                for (int i=0;i<modelTypeCompany.getData().size();i++){
                    typecompanyname.add(modelTypeCompany.getData().get(i).getName());
                    typecompanyid.add(modelTypeCompany.getData().get(i).getId());

                }

            }
        });

        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.CompanyName.getText().toString().trim();
                String CommercialRegistrationNo=binding.CommercialRegistrationNo.getText().toString().trim();
                String email=binding.email.getText().toString().trim();
                if (name.isEmpty()){
                    binding.CompanyName.setError(getString(R.string.company_name));
                    return;
                }
                if (CommercialRegistrationNo.isEmpty()){
                    binding.CommercialRegistrationNo.setError(getString(R.string.commercial_registration_no));
                    return;
                }
                if (email.isEmpty()){
                    binding.email.setError(getString(R.string.email));
                    return;
                }
                else{
                    if (part==null){
                        SendDataUpdateProfileCompany company = new SendDataUpdateProfileCompany();
                        company.setName(name);
                        company.setCommercial_registration_no(CommercialRegistrationNo);
                        company.setEmail(email);
                        company.setCity_id(String.valueOf(city_id));
                        company.setType("company");
                        company.setCompany_type_id(String.valueOf(company_id));

                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addFormDataPart("name", company.getName())
                                .addFormDataPart("email", company.getEmail())
                                .addFormDataPart("company_type_id",company.getCompany_type_id())
                                .addFormDataPart("commercial_registration_no", company.getCommercial_registration_no())
                                .addFormDataPart("type", company.getType())
                                .addFormDataPart("city_id", company.getCity_id());
                        RequestBody requestBody = builder.build();
                        viewModel.updateprofilcompany("Bearer"+token,
                                "application/json",l,requestBody);
                    }else{
                    SendDataUpdateProfileCompany company = new SendDataUpdateProfileCompany();
                    company.setName(name);
                    company.setCommercial_registration_no(CommercialRegistrationNo);
                    company.setEmail(email);
                    company.setCity_id(String.valueOf(city_id));
                    company.setType("company");
                    company.setCompany_type_id(String.valueOf(company_id));
                    company.setImage(part);
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("name", company.getName())
                            .addFormDataPart("email", company.getEmail())
                            .addFormDataPart("company_type_id",company.getCompany_type_id())
                            .addFormDataPart("commercial_registration_no", company.getCommercial_registration_no())
                            .addFormDataPart("type", company.getType())
                            .addFormDataPart("city_id", company.getCity_id())
                            .addPart(company.getImage());
                    RequestBody requestBody = builder.build();
                    viewModel.updateprofilcompany("Bearer"+token,
                            "application/json",l,requestBody);}
                }
            }
        });
        viewModel.updatecompany.observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                Toast.makeText(getContext(), ""+profile.getMessage(), Toast.LENGTH_SHORT).show();
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
                        .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
            }
        });
        binding.inputTextLayoutpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_upditeProfileCompany_to_updatePassword);
            }
        });
    return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
}