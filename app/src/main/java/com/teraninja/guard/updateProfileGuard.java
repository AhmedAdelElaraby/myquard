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
import com.teraninja.guard.Model.DataCV;
import com.teraninja.guard.Model.DataCity;
import com.teraninja.guard.Model.DataSendCV;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentUpdateProfileGuardBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class updateProfileGuard extends Fragment {
    FragmentUpdateProfileGuardBinding binding;
    MoveViewModel viewModel;
    String l;
    SharedPreferences preferences;
    String token;
    ArrayList<String> listQualification= new ArrayList<>();
    ArrayList<String> listcity= new ArrayList<>();
    ArrayList<Integer> listcityId= new ArrayList<>();
    ArrayList<String> listdistrict= new ArrayList<>();
    ArrayList<Integer> listdistrictId=new ArrayList<>();
    ArrayList<String> listother_city= new ArrayList<>();
    ArrayList<String> listexprince= new ArrayList<>();
    ArrayList<String> listcoshilmedia= new ArrayList<>();
    ArrayList<String> Engailsh= new ArrayList<>();
    Uri uri;
    Uri uri2;
    MultipartBody.Part part = null;
    String exprice_type;
    MultipartBody.Part part2 = null;
    int city;
    int Districts;
    String CtiyJob;
    ArrayList<String> listexprincetype= new ArrayList<>();
    String Qualification;
    String expriance;
    String gender;
    String English;
    public updateProfileGuard() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_update_profile_guard, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        if (token.isEmpty()){

        }
        if (token.equals("no")){

        }
        else {
            viewModel.getProfile("Bearer"+token,
                    "application/json",l);
        }
       // viewModel.getCity("application/json",l);

            binding.inputTextLayoutnumberphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateProfileGuard_to_updatePhone);

                }
            });
binding.inputTextLayoutpassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateProfileGuard_to_updatePassword);
    }
});
     viewModel.getProfile.observe(getViewLifecycleOwner(), new Observer<Profile>() {
         @Override
         public void onChanged(Profile profile) {
             if (profile!=null){
                 binding.fullname.setText(profile.getData().getName());
                // exprice_type=profile.getData().gete
                 binding.inputTextLayoutnumberphone.setText(profile.getData().getPhone());
                 binding.age.setText(""+profile.getData().getAge());
                 listcity.add(""+profile.getData().getCity().getName());
                 listdistrict.add(""+profile.getData().getDistrict().getName());
                 listcityId.add(profile.getData().getCity().getId());
                 listdistrictId.add(profile.getData().getDistrict().getId());
                 listQualification.add(""+profile.getData().getQualification());
                 listother_city.add(""+profile.getData().getOther_cities());
                 listexprince.add(""+profile.getData().getExperience());
                 listcoshilmedia.add(""+profile.getData().getSocial_status());
                 Engailsh.add(""+profile.getData().getEnglish());
                binding.AcuontBank.setText(""+profile.getData().getIban_no());
                binding.email.setText(""+profile.getData().getEmail());
                if (profile.getData().getNo_experience()==1){
                    listexprincetype.add(getString(R.string.there_is_no_experience));

                }else if (profile.getData().getMilitary_experience()==1){
                     listexprincetype.add(getString(R.string.previous_military_experience));

                 }
                else if (profile.getData().getExperience_of_the_filed_of_security()==1){
                    listexprincetype.add(getString(R.string.experienced_in_the_field_of_security));


                }else{

                }

             }
//adapter
             ArrayAdapter<String> exprince_type = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexprincetype);
             binding.inputTextLayoutExprinceType.setAdapter(exprince_type);
             listexprincetype.add(getString(R.string.there_is_no_experience));
             listexprincetype.add(getString(R.string.previous_military_experience));
             listexprincetype.add(getString(R.string.experienced_in_the_field_of_security));
             ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listcity);
             binding.cantrey.setAdapter(adaptercity);
             ArrayAdapter<String> adapterdistrict = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listdistrict);
             binding.city.setAdapter(adapterdistrict);
             listQualification.add(getString(R.string.primary));
             listQualification.add(getString(R.string.middle));
             listQualification.add(getString(R.string.secondary));
             listQualification.add(getString(R.string.other));
             ArrayAdapter<String> adapterQualification = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listQualification);
             binding.inputTextLayoutQualification.setAdapter(adapterQualification);
             listother_city.add(getString(R.string.Yes));
             listother_city.add(getString(R.string.No));
             ArrayAdapter<String> adapterother_city = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listother_city);
             binding.inputTextLayoutcuntreyJob.setAdapter(adapterother_city);
             listexprince.add(getString(R.string.from_1_year_to_5_years));
             listexprince.add(getString(R.string.from_6_year_to_10_years));
             ArrayAdapter<String> adapterexprince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexprince);
             binding.inputTextLayoutexprinse.setAdapter(adapterexprince);
             listcoshilmedia.add(getString(R.string.male));
             listcoshilmedia.add(getString(R.string.female));
             ArrayAdapter<String> adaptercoshilmedia = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listcoshilmedia);
             binding.inputTextLayoutsoshilmedia.setAdapter(adaptercoshilmedia);
             Engailsh.add(getString(R.string.poor));
             Engailsh.add(getString(R.string.good));
             Engailsh.add(getString(R.string.very_good));
             Engailsh.add(getString(R.string.excellent));
             ArrayAdapter<String> inputTextLayoutEngilsh = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Engailsh);
             binding.inputTextLayoutEngilsh.setAdapter(inputTextLayoutEngilsh);
             viewModel.getCity("application/json",l);


         }
     });
     binding.Edit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String name=binding.fullname.getText().toString().trim();
             String age=binding.age.getText().toString().trim();
             String citId=String.valueOf(city);
             String DistrictsId=String.valueOf(Districts);
             String AcunteBank=binding.AcuontBank.getText().toString().trim();
             String Email=binding.email.getText().toString().trim();
             if (name.isEmpty()){
                 binding.inputTextLayoutfullname.setError(getString(R.string.full_name));
                 return;
             }
             if (age.isEmpty()){
                 binding.inputTextLayoutage.setError(getString(R.string.the_age));
                 return;
             }
             if (AcunteBank.isEmpty()){
                 binding.inputTextLayoutAcunteBank.setError(getString(R.string.bank_account));
                 return;
             }
             if (Email.isEmpty()){
                 binding.inputTextLayoutEmail.setError(getString(R.string.email));
                 return;
             }
             if (citId.isEmpty()){
                 Toast.makeText(getContext(), ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();
                 return;
             }
             if (DistrictsId.isEmpty()){
                 Toast.makeText(getContext(), ""+getString(R.string.Chosedistrict), Toast.LENGTH_SHORT).show();
                 return;
             }
             else {
              if (part==null&&part2==null){
                  //Toast.makeText(getContext(), "null oll", Toast.LENGTH_SHORT).show();
                  DataSendCV sendCV = new DataSendCV();
                  sendCV.setName(name);
                  sendCV.setAge(age);
                  sendCV.setCity_id(citId);
                  sendCV.setDistrict_id(DistrictsId);
                  sendCV.setIban_no(AcunteBank);
                  sendCV.setEmail(Email);
                  sendCV.setEnglish(English);
                  sendCV.setSocial_status(gender);
                  sendCV.setExperience(expriance);
                  sendCV.setOther_cities(CtiyJob);
                  sendCV.setQualification(Qualification);
                 sendCV.setExperience_type(exprice_type);



                  MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

                          builder.addFormDataPart("name", sendCV.getName())
                          .addFormDataPart("email",sendCV.getEmail())
                          .addFormDataPart("age", sendCV.getAge())
                          .addFormDataPart("city_id", sendCV.getCity_id())
                          .addFormDataPart("district_id",sendCV.getDistrict_id())
                          .addFormDataPart("iban_no",sendCV.getIban_no())
                          .addFormDataPart("experience",sendCV.getExperience())
                          .addFormDataPart("qualification", sendCV.getQualification())
                          .addFormDataPart("experience_type",sendCV.getExperience_type())
                          .addFormDataPart("social_status",sendCV.getSocial_status())
                          .addFormDataPart("other_cities",sendCV.getOther_cities())
                          .addFormDataPart("english",sendCV.getEnglish());

                  RequestBody requestBody = builder.build();
                 viewModel.updateCvguard("Bearer"+token,
                         "application/json",l,requestBody);
              }
              else if (part==null){
                  DataSendCV sendCV2 = new DataSendCV();
                  sendCV2.setName(name);
                  sendCV2.setAge(age);
                  sendCV2.setCity_id(citId);
                  sendCV2.setDistrict_id(DistrictsId);
                  sendCV2.setIban_no(AcunteBank);
                  sendCV2.setEmail(Email);
                  sendCV2.setEnglish(English);
                  sendCV2.setIdentification_id(part2);
                  sendCV2.setSocial_status(gender);
                  sendCV2.setExperience(expriance);
                  sendCV2.setOther_cities(CtiyJob);
                  sendCV2.setQualification(Qualification);
                  sendCV2.setExperience_type(exprice_type);
                  MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                  builder.addFormDataPart("name", sendCV2.getName())
                          .addFormDataPart("email",sendCV2.getEmail())
                          .addFormDataPart("age", sendCV2.getAge())
                          .addFormDataPart("city_id", sendCV2.getCity_id())
                          .addFormDataPart("district_id",sendCV2.getDistrict_id())
                          .addFormDataPart("iban_no",sendCV2.getIban_no())
                          .addPart(sendCV2.getIdentification_id())
                          .addFormDataPart("experience",sendCV2.getExperience())
                          .addFormDataPart("qualification", sendCV2.getQualification())
                          .addFormDataPart("experience_type",sendCV2.getExperience_type())
                          .addFormDataPart("social_status",sendCV2.getSocial_status())
                          .addFormDataPart("other_cities",sendCV2.getOther_cities())
                          .addFormDataPart("english",sendCV2.getEnglish());
                  RequestBody requestBody = builder.build();
                  viewModel.updateCvguard("Bearer"+token,
                          "application/json",l,requestBody);
                  Toast.makeText(getContext(), "part one", Toast.LENGTH_SHORT).show();
              }
             else if (part2==null){
                  DataSendCV sendCV2 = new DataSendCV();
                  sendCV2.setName(name);
                  sendCV2.setAge(age);
                  sendCV2.setCity_id(citId);
                  sendCV2.setDistrict_id(DistrictsId);
                  sendCV2.setIban_no(AcunteBank);
                  sendCV2.setEmail(Email);
                  sendCV2.setEnglish(English);
                  sendCV2.setImage(part);
                  sendCV2.setSocial_status(gender);
                  sendCV2.setExperience(expriance);
                  sendCV2.setOther_cities(CtiyJob);
                  sendCV2.setQualification(Qualification);
                  sendCV2.setExperience_type(exprice_type);
                  MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                  builder.addFormDataPart("name", sendCV2.getName())
                          .addFormDataPart("email",sendCV2.getEmail())
                          .addFormDataPart("age", sendCV2.getAge())
                          .addFormDataPart("city_id", sendCV2.getCity_id())
                          .addFormDataPart("district_id",sendCV2.getDistrict_id())
                          .addFormDataPart("iban_no",sendCV2.getIban_no())
                          .addPart(sendCV2.getImage())
                          .addFormDataPart("experience",sendCV2.getExperience())
                          .addFormDataPart("qualification", sendCV2.getQualification())
                          .addFormDataPart("experience_type",sendCV2.getExperience_type())
                          .addFormDataPart("social_status",sendCV2.getSocial_status())
                          .addFormDataPart("other_cities",sendCV2.getOther_cities())
                          .addFormDataPart("english",sendCV2.getEnglish());
                  RequestBody requestBody = builder.build();
                  viewModel.updateCvguard("Bearer"+token,
                          "application/json",l,requestBody);
                  Toast.makeText(getContext(), "part two", Toast.LENGTH_SHORT).show();

              }
              else{
                  DataSendCV sendCV2 = new DataSendCV();
                  sendCV2.setName(name);
                  sendCV2.setAge(age);
                  sendCV2.setCity_id(citId);
                  sendCV2.setDistrict_id(DistrictsId);
                  sendCV2.setIban_no(AcunteBank);
                  sendCV2.setEmail(Email);
                  sendCV2.setEnglish(English);
                  sendCV2.setImage(part);
                  sendCV2.setIdentification_id(part2);
                  sendCV2.setSocial_status(gender);
                  sendCV2.setExperience(expriance);
                  sendCV2.setOther_cities(CtiyJob);
                  sendCV2.setQualification(Qualification);
                  sendCV2.setExperience_type(exprice_type);
                  MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                  builder.addFormDataPart("name", sendCV2.getName())
                          .addFormDataPart("email",sendCV2.getEmail())
                          .addFormDataPart("age", sendCV2.getAge())
                          .addFormDataPart("city_id", sendCV2.getCity_id())
                          .addFormDataPart("district_id",sendCV2.getDistrict_id())
                          .addFormDataPart("iban_no",sendCV2.getIban_no())
                          .addPart(sendCV2.getImage())
                          .addPart(sendCV2.getIdentification_id())
                          .addFormDataPart("experience",sendCV2.getExperience())
                          .addFormDataPart("qualification", sendCV2.getQualification())
                          .addFormDataPart("experience_type",sendCV2.getExperience_type())
                          .addFormDataPart("social_status",sendCV2.getSocial_status())
                          .addFormDataPart("other_cities",sendCV2.getOther_cities())
                          .addFormDataPart("english",sendCV2.getEnglish());
                  RequestBody requestBody = builder.build();
                  viewModel.updateCvguard("Bearer"+token,
                          "application/json",l,requestBody);
                  Toast.makeText(getContext(), "no null", Toast.LENGTH_SHORT).show();
              }
             }

            // Toast.makeText(getContext(), ""+city+"\n"+Districts+"\n"+CtiyJob+"\n"+Qualification+"\n"+expriance+"\n"+gender+"\n"+English, Toast.LENGTH_SHORT).show();
         }
     });
     viewModel.getcity.observe(getViewLifecycleOwner(), new Observer<ModelCity>() {
         @Override
         public void onChanged(ModelCity modelCity) {

             for (int i=0;i<modelCity.getData().size();i++){
                 listcity.add(modelCity.getData().get(i).getName());
                 listcityId.add(modelCity.getData().get(i).getId());

             }
         }
     });
     binding.cantrey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // viewModel.getCity("application/json",l);
             city=listcityId.get(position);
             viewModel.getDistricts(city,"application/json",l);
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     viewModel.getDistricts.observe(getViewLifecycleOwner(), new Observer<DataCity>() {
         @Override
         public void onChanged(DataCity dataCity) {
             listdistrict.clear();
             listdistrictId.clear();
             for (int i=0;i<dataCity.getData().size();i++){
                 listdistrict.add(dataCity.getData().get(i).getName());
                 listdistrictId.add(dataCity.getData().get(i).getId());
             }
         }
     });
     binding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             Districts=listdistrictId.get(position);
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.inputTextLayoutQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             switch (binding.inputTextLayoutQualification.getSelectedItem().toString()){
                 case"اختر الموهل الدراسي":
                     Qualification="Choose the course";
                     break;
                 case "ابتدائي":
                     Qualification="primary";
                     break;
                 case"متوسط":
                     Qualification="middle";
                     break;
                 case"ثانوي":
                     Qualification="secondary";
                     break;
                 case"غير ذلك":
                     Qualification="other";
                     break;
                 default:
                     Qualification=binding.inputTextLayoutQualification.getSelectedItem().toString();

             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.inputTextLayoutcuntreyJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             switch (binding.inputTextLayoutcuntreyJob.getSelectedItem().toString()){
                 case"نعم":
                     CtiyJob="yes";
                     break;
                 case"لا":
                     CtiyJob="no";
                     break;
                 default:
                     CtiyJob=binding.inputTextLayoutcuntreyJob.getSelectedItem().toString();
             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.inputTextLayoutexprinse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             switch (binding.inputTextLayoutexprinse.getSelectedItem().toString()){
                 case"من 1 سنه الي 5 سنوات":
                     expriance="from_1_year_to_5_years";
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                     break;
                 case "من 6 سنوات الي 10 سنوات":
                     expriance="from_6_year_to_10_years";
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                     break;
                 default:
                     expriance=binding.inputTextLayoutexprinse.getSelectedItem().toString();
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();

             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.inputTextLayoutsoshilmedia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             switch (binding.inputTextLayoutsoshilmedia.getSelectedItem().toString()){
                 case"ذكر":
                     gender="male";
                     break;
                 case"انثي":
                     gender="female";
                     break;
                 default:
                     gender=binding.inputTextLayoutsoshilmedia.getSelectedItem().toString();
             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.inputTextLayoutEngilsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             switch (binding.inputTextLayoutEngilsh.getSelectedItem().toString()){
                 case "ضعيف":
                     English="poor";
                     break;
                 case "جيد":
                     English="good";
                     break;
                 case "جيد جدا":
                     English="very_good";
                     break;
                 case "ممتاز":
                     English="excellent";
                     break;
                 default:
                     English=binding.inputTextLayoutEngilsh.getSelectedItem().toString();
             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
        binding.inputTextLayoutExprinceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (binding.inputTextLayoutExprinceType.getSelectedItem().toString()){
                    case "There is no experience":
                        exprice_type="no_experience";
                        break;
                    case"لايوجد خبره":
                        exprice_type="no_experience";
                        break;
                    case "ذو خبره في مجال الحراسات":
                        exprice_type="experience_of_the_filed_of_security";
                        break;
                    case "Experienced in the field of security":
                        exprice_type="experience_of_the_filed_of_security";

                        break;
                    case "ذو خبره عسكريه سابقه":
                        exprice_type="military_experience";
                        break;
                    case "Previous military experience":
                        exprice_type="military_experience";
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.inputTextLayoutpersonalPicture.setOnClickListener(new View.OnClickListener() {
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
        binding.inputTextLayoutphotocomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),2);

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
        viewModel.UpdateCvguard.observe(getViewLifecycleOwner(), new Observer<DataCV>() {
            @Override
            public void onChanged(DataCV dataCV) {
                Toast.makeText(getContext(), ""+dataCV.getMessage(), Toast.LENGTH_SHORT).show();
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
                        binding.inputTextLayoutpersonalPicture.setText(""+uri.getPath());
                        saveImage();
                        break;
                    case 2:
                        uri2=data.getData();
                        binding.inputTextLayoutphotocomi.setText(""+uri2.getPath());
                        saveImage2();
                        break;
//                    case 50:
//                        boolean stust= data.getBooleanExtra("stuts",false);
//                        if (stust){
//                            binding.spinkit.setVisibility(View.VISIBLE);
//                            DataSendCV dataSendCV = new DataSendCV(typeid,name,email,age,casegender
//                                    ,part,cantrey,casecity,part2,bank,expriance
//                                    ,qualification,item,SocialStatus,CtiyJob,English);
//
//                            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//                            builder.addFormDataPart("jop_type_id", dataSendCV.getJop_type_id())
//                                    .addFormDataPart("name", dataSendCV.getName())
//                                    .addFormDataPart("email",dataSendCV.getEmail())
//                                    .addFormDataPart("age", dataSendCV.getAge())
//                                    .addFormDataPart("gender", dataSendCV.getGender())
//                                    .addPart(dataSendCV.getImage())
//                                    .addFormDataPart("city_id", dataSendCV.getCity_id())
//                                    .addFormDataPart("district_id",dataSendCV.getDistrict_id())
//                                    .addPart(dataSendCV.getIdentification_id())
//                                    .addFormDataPart("iban_no",dataSendCV.getIban_no())
//                                    .addFormDataPart("experience",dataSendCV.getExperience())
//                                    .addFormDataPart("qualification", dataSendCV.getQualification())
//                                    .addFormDataPart("experience_type",dataSendCV.getExperience_type())
//                                    .addFormDataPart("social_status",dataSendCV.getSocial_status())
//                                    .addFormDataPart("other_cities",dataSendCV.getOther_cities())
//                                    .addFormDataPart("english",dataSendCV.getEnglish());
//
//                            RequestBody requestBody = builder.build();
//                            viewModel.getCv("Bearer"+token,"application/json",l,requestBody);

//                        }else {
//
//                        }

//                        break;
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
        part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);
    }
    private void saveImage2() {
        RequestBody fileReqBody = null;

        File file = new File(getPathFromUri(getContext(),uri2));
        // Create a request body with file and image media type
        fileReqBody = RequestBody.create( file, MediaType.parse("image/*"));
        // Create MultipartBody.Part using file request-body,file name and part name
        part2 = MultipartBody.Part.createFormData("identification_id", file.getName(), fileReqBody);
    }
}