package com.teraninja.guard;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.teraninja.guard.utils.ImagePickerHelper.getPathFromUri;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.FadingCircle;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.guard.DataClient.InterFacePackageId;
import com.teraninja.guard.DataClient.OnClickRecyclerCondation;
import com.teraninja.guard.Model.DataCV;
import com.teraninja.guard.Model.DataCity;
import com.teraninja.guard.Model.DataPackage;
import com.teraninja.guard.Model.DataSendCV;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.ModelCondation;
import com.teraninja.guard.UI.HomeActivity;
import com.teraninja.guard.UI.Main.AdapterItemCondation;
import com.teraninja.guard.UI.Main.AdapterPayment;
import com.teraninja.guard.UI.Main.Payment;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentInformationBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Information extends Fragment implements OnClickRecyclerCondation, InterFacePackageId {
FragmentInformationBinding binding;
MoveViewModel viewModel;
AdapterItemCondation adapterItemCondation;
ArrayList<Integer> listid= new ArrayList<>();
ArrayList<String> listcitys= new ArrayList<>();
ArrayList<String> listDistricts= new ArrayList<>();
ArrayList<Integer> listDistrictsid= new ArrayList<>();
String typeJobId;
    String l;
    String gender;
    int cuontrey;
    int city;
    Uri uri;
    MultipartBody.Part part = null;
    Uri uri2;
    MultipartBody.Part part2 = null;
    String CtiyJob;
    String SocialStatus;
    String item="none";
    String English;
     String expriance;
    String typeid;
    String name;
    String number;
    String age;
    String casegender;
    String qualification;
    String cantrey;
    String casecity;
    String bank;
    String email;
    Dialog myDialog;
    SharedPreferences preferences;
    String token;
    public Information() {
        // Required empty public constructor
    }


    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_information, container, false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");

        viewModel.getCondation(1,"application/json",l);
        viewModel.getCity("application/json",l);
        FadingCircle doubleBounce = new FadingCircle();
        binding.spinkit.setIndeterminateDrawable(doubleBounce);

        binding.guard.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
        binding.guard.setTextColor(getResources().getColor(R.color.white));
        adapterItemCondation= new AdapterItemCondation(this);
        binding.theConditions.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       binding.theConditions.setAdapter(adapterItemCondation);
       binding.guard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewModel.getCondation(1,"application/json",l);
               binding.guard.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
               binding.guard.setTextColor(getResources().getColor(R.color.white));
               binding.Organizer.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
               binding.Organizer.setTextColor(getResources().getColor(R.color.black));
               binding.volunteer.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
               binding.volunteer.setTextColor(getResources().getColor(R.color.black));


           }
       });
        binding.Organizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getCondation(2,"application/json",l);
                binding.Organizer.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
                binding.Organizer.setTextColor(getResources().getColor(R.color.white));

                binding.guard.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
                binding.guard.setTextColor(getResources().getColor(R.color.black));
                binding.volunteer.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
                binding.volunteer.setTextColor(getResources().getColor(R.color.black));


            }
        });
        binding.volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getCondation(3,"application/json",l);
                binding.volunteer.setBackground(getResources().getDrawable(R.drawable.style_buttonsignup));
                binding.volunteer.setTextColor(getResources().getColor(R.color.white));
                binding.guard.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
                binding.guard.setTextColor(getResources().getColor(R.color.black));
                binding.Organizer.setBackground(getResources().getDrawable(R.drawable.stilye_button_info));
                binding.Organizer.setTextColor(getResources().getColor(R.color.black));


            }
        });
        viewModel.getcondation.observe(getViewLifecycleOwner(), new Observer<ModelCondation>() {
            @Override
            public void onChanged(ModelCondation modelCondation) {
                adapterItemCondation.getList(modelCondation.getData());
                //Toast.makeText(getContext(), ""+modelCondation.getData().get(0).getCondition(), Toast.LENGTH_SHORT).show();
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
       binding.inputTextLayoutNationalIdentificationNumber2.setOnClickListener(new View.OnClickListener() {
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

        binding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeid=typeJobId;
                 name=binding.fullname.getText().toString().trim();
                 number=binding.numberphone.getText().toString().trim();
                 age=binding.old.getText().toString().trim();
                 casegender=gender;
                 qualification=binding.Qualification.getText().toString().trim();

                 cantrey=String.valueOf(cuontrey);
                 casecity=String.valueOf(city);




                 bank=binding.BankAccount.getText().toString().trim();
                 email=binding.Email.getText().toString().trim();


                if (typeid.isEmpty()){Toast.makeText(context, "Chose Type Job", Toast.LENGTH_SHORT).show();return; }
                if (name.isEmpty()){binding.inputTextLayoutfullname.setError("Enter your name");return;}
                if (name.equals(" ")){binding.inputTextLayoutfullname.setError("Enter your name");return;}
                if (number.isEmpty()){binding.inputTextLayoutnumberphone.setError("Enter your number phone");return;}
                if (number.length()<=10){ binding.inputTextLayoutnumberphone.setError(getString(R.string.casenumberphone));return; }
                if (age.isEmpty()){ binding.inputTextLayoutold.setError("Enter your age");return; }
                if (casegender.isEmpty()){ Toast.makeText(context, "Chose Gender", Toast.LENGTH_SHORT).show();return; }
                if (casegender.equals(getString(R.string.choosegender))){ Toast.makeText(context, ""+getString(R.string.choosegender), Toast.LENGTH_SHORT).show();return; }
                if (qualification.isEmpty()){ binding.inputTextLayoutQualification.setError("Enter Your qualification");return; }
                if (cantrey.isEmpty()){ Toast.makeText(context, ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();return; }
                if (cantrey.equals("0")){ Toast.makeText(context, ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();return; }
                if (casecity.isEmpty()){ Toast.makeText(context, ""+getString(R.string.Chosedistrict), Toast.LENGTH_SHORT).show();return; }
                if (casecity.equals("0")){ Toast.makeText(context, ""+getString(R.string.Chosedistrict), Toast.LENGTH_SHORT).show();return; }
                if (part==null){binding.inputTextLayoutpersonalPicture.setError(getString(R.string.personal_picture));return; }
                if (CtiyJob.isEmpty()){ Toast.makeText(context, ""+getString(R.string.Chose_Cities_to_work_in), Toast.LENGTH_SHORT).show(); return;}
                if (CtiyJob.equals(getString(R.string.Chose_Cities_to_work_in))){Toast.makeText(context, ""+getString(R.string.Chose_Cities_to_work_in), Toast.LENGTH_SHORT).show(); return; }
                if (expriance.isEmpty()){
                    Toast.makeText(context, "اختر عدد سنوات الخبره", Toast.LENGTH_SHORT).show();
                    return;}
                if (expriance.equals(getString(R.string.Chose_Experience))){
                    Toast.makeText(context, "اختر عدد سنوات الخبره", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (SocialStatus.isEmpty()){Toast.makeText(context, ""+getString(R.string.Chose_Social_status), Toast.LENGTH_SHORT).show();return; }
                if (part2==null){binding.inputTextLayoutNationalIdentificationNumber2.setError(getString(R.string.attach_the_national_id_or_residence)); return;}
                if (SocialStatus.equals(getString(R.string.Chose_Social_status))){Toast.makeText(context, ""+getString(R.string.Chose_Social_status), Toast.LENGTH_SHORT).show();return; }
                if (English.isEmpty()){Toast.makeText(context, ""+getString(R.string.expertise_in_the_English_language),Toast.LENGTH_SHORT).show();return;}
                if(English.equals(getString(R.string.expertise_in_the_English_language))){Toast.makeText(context, ""+getString(R.string.expertise_in_the_English_language),Toast.LENGTH_SHORT).show();return;}
                if (bank.isEmpty()){binding.inputTextLayoutBankAccount.setError(getString(R.string.bank_account));return;}
                if (email.isEmpty()){binding.inputTextLayoutEmail.setError(getString(R.string.email));return;}
                if (binding.radioButton1.isChecked()){
                    item="military_experience";
                    }
                if (binding.radioButton2.isChecked()){
                    item="experience_of_the_filed_of_security";

                }
                if (binding.radioButton3.isChecked()){
                    item="no_experience ";
                   }
                if (item.equals("none")){
                    Toast.makeText(context, ""+getString(R.string.do_you_have_experience), Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                showAddressesPopup();
                      //  Toast.makeText(context, ""+typeid+name+number+age+casegender+qualification+cantrey+casecity+CtiyJob+expriance+SocialStatus+English+bank+email+item, Toast.LENGTH_SHORT).show();
                }
            }
        });
        String [] listgender={getString(R.string.choosegender),getString(R.string.male),getString(R.string.female)};
// Adapter spinner gender
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listgender);
        binding.gender.setAdapter(adapter);
        binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (binding.gender.getSelectedItem().toString()){
                   case"ذكر":
                       gender="male";
                       break;
                   case"انثي":
                       gender="female";
                       break;
                   default:
                       gender=binding.gender.getSelectedItem().toString();
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Adapter spinner city

        viewModel.getcity.observe(getViewLifecycleOwner(), new Observer<ModelCity>() {
            @Override
            public void onChanged(ModelCity modelCity) {
                listcitys.clear();
                listid.clear();
                listcitys.add(getString(R.string.ChoseCity));
                listid.add(0);
                for (int i =0; i<modelCity.getData().size();i++){

                    listid.add(modelCity.getData().get(i).getId());
                    listcitys.add(modelCity.getData().get(i).getName());

                }
               // Toast.makeText(getContext(), ""+listid.get(0), Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listcitys);
                binding.cantrey.setAdapter(adaptercity);
            }
        });
        binding.cantrey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuontrey= listid.get(position);
           // Toast.makeText(getContext(), ""+cuontrey, Toast.LENGTH_SHORT).show();
                viewModel.getDistricts(listid.get(position),"application/json",l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewModel.getDistricts.observe(getViewLifecycleOwner(), new Observer<DataCity>() {
            @Override
            public void onChanged(DataCity dataCity) {
                listDistricts.clear();
                listDistrictsid.clear();
                listDistricts.add(getString(R.string.Chosedistrict));
                listDistrictsid.add(0);
                for (int i=0;i<dataCity.getData().size();i++) {

                    listDistricts.add(dataCity.getData().get(i).getName());
                    listDistrictsid.add(dataCity.getData().get(i).getId());
                }
                ArrayAdapter<String> adapterDistricts = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listDistricts);
                binding.city.setAdapter(adapterDistricts);
            }
        });
        binding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=listDistrictsid.get(position);
              // Toast.makeText(context, ""+city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String [] listothercities={getString(R.string.Chose_Cities_to_work_in),getString(R.string.Yes),getString(R.string.No)};
        ArrayAdapter<String> selectedothercities = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listothercities);
        binding.cantreyJob.setAdapter(selectedothercities);
        binding.cantreyJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.cantreyJob.getSelectedItem().toString()){
                    case"نعم":
                        CtiyJob="yes";
                        break;
                    case"لا":
                        CtiyJob="no";
                        break;
                    default:
                        CtiyJob=binding.cantreyJob.getSelectedItem().toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String [] listSocialstatus={getString(R.string.Chose_Social_status),getString(R.string.single),getString(R.string.married)};
        ArrayAdapter<String> selectedSocialstatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listSocialstatus);
        binding.SocialStatus.setAdapter(selectedSocialstatus);
        binding.SocialStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (binding.SocialStatus.getSelectedItem().toString()){

            case"أعزب":
                SocialStatus="single";
                break;
            case"متزوج":
                SocialStatus="married";
                break;
            default:
                SocialStatus=binding.SocialStatus.getSelectedItem().toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        String [] listexpirinceEnglah={getString(R.string.expertise_in_the_English_language),getString(R.string.poor),getString(R.string.good),getString(R.string.very_good),getString(R.string.excellent)};
        ArrayAdapter<String> selectedExprinceEnglash = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexpirinceEnglah);
        binding.inputTextLayoutEngelah.setAdapter(selectedExprinceEnglash);
        binding.inputTextLayoutEngelah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             // English=binding.inputTextLayoutEngelah.getSelectedItem().toString();
            switch (binding.inputTextLayoutEngelah.getSelectedItem().toString()){
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
                    English=binding.inputTextLayoutEngelah.getSelectedItem().toString();
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewModel.CV.observe(getViewLifecycleOwner(), new Observer<DataCV>() {
            @Override
            public void onChanged(DataCV dataCV) {
                if (dataCV.getStatus()==1){
                    binding.spinkit.setVisibility(View.GONE);
                    Toast.makeText(context, ""+dataCV.getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog = new Dialog(context);
                    myDialog.setContentView(R.layout.dilog_of_the_situation);
                    ImageView imageView = myDialog.findViewById(R.id.imagedone);
                    TextView textView = myDialog.findViewById(R.id.textdone);
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    Window window = myDialog.getWindow();

                    window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.create();
                    myDialog.show();
                    NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.information2, true).build();

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_information2_to_userProfile,savedInstanceState,builder1);
                }else{
                    Toast.makeText(context, ""+dataCV.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.spinkit.setVisibility(View.GONE);

                }







            }
        });
        String [] listexpirince={getString(R.string.Chose_Experience),getString(R.string.from_1_year_to_5_years),getString(R.string.from_6_year_to_10_years)};
        ArrayAdapter<String> selectedExprince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexpirince);
        binding.inputTextLayoutexparinse.setAdapter(selectedExprince);
        binding.inputTextLayoutexparinse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              switch (binding.inputTextLayoutexparinse.getSelectedItem().toString()){
                  case"من 1 سنه الي 5 سنوات":
                      expriance="from_1_year_to_5_years";
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                      break;
                  case "من 6 سنوات الي 10 سنوات":
                      expriance="from_6_year_to_10_years";
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                      break;
                  default:
                      expriance=binding.inputTextLayoutexparinse.getSelectedItem().toString();
                     // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();

              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),HomeActivity.class);

                startActivity(intent);
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
                        binding.inputTextLayoutNationalIdentificationNumber2.setText(""+uri2.getPath());
                    saveImage2();
                        break;
                    case 50:
                       boolean stust= data.getBooleanExtra("stuts",false);
                       if (stust){
                            binding.spinkit.setVisibility(View.VISIBLE);
                           DataSendCV dataSendCV = new DataSendCV();
                           dataSendCV.setJop_type_id(typeid);
                           dataSendCV.setName(name);
                           dataSendCV.setEmail(email);
                           dataSendCV.setAge(age);
                           dataSendCV.setGender(casegender);
                           dataSendCV.setImage(part);
                           dataSendCV.setCity_id(cantrey);
                           dataSendCV.setDistrict_id(casecity);
                           dataSendCV.setIdentification_id(part2);
                           dataSendCV.setIban_no(bank);
                           dataSendCV.setExperience(expriance);
                           dataSendCV.setQualification(qualification);
                           dataSendCV.setExperience_type(item);
                           dataSendCV.setSocial_status(SocialStatus);
                           dataSendCV.setOther_cities(CtiyJob);
                           dataSendCV.setEnglish(English);


                           MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                           builder.addFormDataPart("jop_type_id", dataSendCV.getJop_type_id())
                                   .addFormDataPart("name", dataSendCV.getName())
                                   .addFormDataPart("email",dataSendCV.getEmail())
                                   .addFormDataPart("age", dataSendCV.getAge())
                                   .addFormDataPart("gender", dataSendCV.getGender())
                                   .addPart(dataSendCV.getImage())
                                   .addFormDataPart("city_id", dataSendCV.getCity_id())
                                   .addFormDataPart("district_id",dataSendCV.getDistrict_id())
                                   .addPart(dataSendCV.getIdentification_id())
                                   .addFormDataPart("iban_no",dataSendCV.getIban_no())
                                   .addFormDataPart("experience",dataSendCV.getExperience())
                                   .addFormDataPart("qualification", dataSendCV.getQualification())
                                   .addFormDataPart("experience_type",dataSendCV.getExperience_type())
                                   .addFormDataPart("social_status",dataSendCV.getSocial_status())
                                   .addFormDataPart("other_cities",dataSendCV.getOther_cities())
                                   .addFormDataPart("english",dataSendCV.getEnglish());

                           RequestBody requestBody = builder.build();
                           viewModel.getCv("Bearer"+token,"application/json",l,requestBody);

                       }else {
                           Toast.makeText(context, "no", Toast.LENGTH_SHORT).show();

                           myDialog = new Dialog(context);
                           myDialog.setContentView(R.layout.dilog_of_the_situation);
                           ImageView imageView = myDialog.findViewById(R.id.imagefalied);
                           TextView textView = myDialog.findViewById(R.id.textfalied);
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

    public void showAddressesPopup () {

        viewModel.getPackage("Bearer"+token,"application/json",l);
        myDialog = new Dialog(context);
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
    public void getitemid(int type_id) {
        typeJobId=String.valueOf(type_id);
       // Toast.makeText(context, ""+typeJobId, Toast.LENGTH_SHORT).show();
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

    @Override
    public void getPackageId(int packageId) {
        String packageidsend=String.valueOf(packageId);
        Intent intent = new Intent(getContext(),Payment.class);
        intent.putExtra("Packageid",packageidsend);
        intent.putExtra("type","guard");
        startActivityForResult(intent,50);
        myDialog.dismiss();

    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        HomeActivity.binding.drawer.open();
////        HomeActivity.binding.drawer.close();
//    }
}