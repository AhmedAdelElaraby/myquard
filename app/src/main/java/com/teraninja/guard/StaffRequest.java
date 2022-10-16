package com.teraninja.guard;

import android.os.Bundle;

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

import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentStaffRequestBinding;

import java.util.ArrayList;
import java.util.Locale;


public class StaffRequest extends Fragment {
FragmentStaffRequestBinding binding;
String typeJob_title;
String Qualification;
String expriance;
String English;
String gender;
MoveViewModel viewModel;
ArrayList<Integer> listid= new ArrayList<>();
ArrayList<String> listcitys= new ArrayList<>();
int cuontrey;
    public StaffRequest() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_staff_request, container, false);
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
       String l= Locale.getDefault().getLanguage();

        viewModel.getCity("application/json",l);
        String [] Job_title={getString(R.string.Chose_Job_title),getString(R.string.security_guard),getString(R.string.Organizer),getString(R.string.volunteer_)};

        ArrayAdapter<String> Job_titleSelected = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Job_title);
        binding.JobTitle.setAdapter(Job_titleSelected);

        binding.JobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.JobTitle.getSelectedItem().toString()){
                    case"Chose Job title":
                        typeJob_title="0";
                        break;
                    case"اختر المسمي الوظيفي":
                        typeJob_title="0";
                        break;
                    case "حارس امن":
                        typeJob_title="1";
                        break;
                    case"security guard":
                        typeJob_title="1";
                        break;
                    case "منظم":
                        typeJob_title="2";
                        break;
                    case "Organizer":
                        typeJob_title="2";
                        break;
                    case "متطوع":
                        typeJob_title="3";
                        break;
                    case "volunteer":
                        typeJob_title="3";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        String[] listqualification={getString(R.string.qualification),getString(R.string.primary),getString(R.string.middle),getString(R.string.secondary),getString(R.string.other)};
        ArrayAdapter<String> selectedqualification = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listqualification);
        binding.Qualification.setAdapter(selectedqualification);
        binding.Qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (binding.Qualification.getSelectedItem().toString()){
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
                       Qualification=binding.Qualification.getSelectedItem().toString();

               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String [] listexpirince={getString(R.string.Chose_Experience),getString(R.string.from_1_year_to_5_years),getString(R.string.from_6_year_to_10_years)};
        ArrayAdapter<String> selectedExprince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexpirince);
        binding.Experience.setAdapter(selectedExprince);
        binding.Experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.Experience.getSelectedItem().toString()){
                    case"من 1 سنه الي 5 سنوات":
                        expriance="from_1_year_to_5_years";
                        // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                        break;
                    case "من 6 سنوات الي 10 سنوات":
                        expriance="from_6_year_to_10_years";
                        // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        expriance=binding.Experience.getSelectedItem().toString();
                        // Toast.makeText(context, ""+expriance, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String [] listexpirinceEnglah={getString(R.string.expertise_in_the_English_language),getString(R.string.poor),getString(R.string.good),getString(R.string.very_good),getString(R.string.excellent)};
        ArrayAdapter<String> selectedExprinceEnglash = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listexpirinceEnglah);
        binding.EnglishLanguageLevel.setAdapter(selectedExprinceEnglash);
        binding.EnglishLanguageLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // English=binding.inputTextLayoutEngelah.getSelectedItem().toString();
                switch (binding.EnglishLanguageLevel.getSelectedItem().toString()){
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
                        English=binding.EnglishLanguageLevel.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String [] listgender={getString(R.string.choosegender),getString(R.string.male),getString(R.string.female)};
// Adapter spinner gender
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listgender);
        binding.citystuaff.setAdapter(adapter);
        binding.citystuaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.citystuaff.getSelectedItem().toString()){
                    case"ذكر":
                        gender="male";
                        break;
                    case"انثي":
                        gender="female";
                        break;
                    default:
                        gender=binding.citystuaff.getSelectedItem().toString();
                }
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
            listcitys.add(getString(R.string.ChoseCity));
            listid.add(0);
            for (int i =0; i<modelCity.getData().size();i++){

                listid.add(modelCity.getData().get(i).getId());
                listcitys.add(modelCity.getData().get(i).getName());

            }
            ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listcitys);
            binding.cantreystuaff.setAdapter(adaptercity);
        }
    });
        binding.cantreystuaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuontrey= listid.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
























       binding.Search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String oldfrom=binding.oldfrom.getText().toString().trim();
               String oldto=binding.oldto.getText().toString().trim();
               String The_required_number=binding.TheRequiredNumber.getText().toString().trim();
               String cantrey=String.valueOf(cuontrey);
                if (typeJob_title.equals("0")){
                    Toast.makeText(getContext(), ""+getString(R.string.Chose_Job_title), Toast.LENGTH_SHORT).show();
                    return;
                }
               if (expriance.isEmpty()){
                   Toast.makeText(getContext(), "اختر عدد سنوات الخبره", Toast.LENGTH_SHORT).show();
                   return;}
               if (expriance.equals(getString(R.string.Chose_Experience))){
                   Toast.makeText(getContext(), "اختر عدد سنوات الخبره", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (English.isEmpty()){
                   Toast.makeText(getContext(), ""+getString(R.string.expertise_in_the_English_language),Toast.LENGTH_SHORT).show();
                   return;}
               if(English.equals(getString(R.string.expertise_in_the_English_language))){
                   Toast.makeText(getContext(), ""+getString(R.string.expertise_in_the_English_language),Toast.LENGTH_SHORT).show();
                   return;}
               if (gender.isEmpty()){ Toast.makeText(getContext(), "Chose Gender", Toast.LENGTH_SHORT).show();
                   return; }
               if (gender.equals(getString(R.string.choosegender))){
                   Toast.makeText(getContext(), ""+getString(R.string.choosegender), Toast.LENGTH_SHORT).show();
                   return; }
               if (Qualification.equals(getString(R.string.qualification))){
                   Toast.makeText(getContext(), ""+getString(R.string.qualification), Toast.LENGTH_SHORT).show();
                   return;
               }
               if (Qualification.isEmpty()){
                   Toast.makeText(getContext(), ""+getString(R.string.qualification), Toast.LENGTH_SHORT).show();
                   return;
               }
               if (oldfrom.isEmpty()){
                   binding.inputTextLayoutoldfrom.setError(getString(R.string.least_18_workers));
                   return;
               }
               int y=Integer.parseInt(oldfrom);
               if (y<=17){
                   binding.inputTextLayoutoldfrom.setError(getString(R.string.least_18_workers));
                   return;
               }
               if (oldto.isEmpty()){
                binding.inputTextLayoutoldto.setError(getString(R.string.than_60_years_old));
                return;
               }
               int x=Integer.parseInt(oldto);
               if (x>60){
                   binding.inputTextLayoutoldto.setError(getString(R.string.than_60_years_old));
                   return;
               }
               if (cantrey.isEmpty()){ Toast.makeText(getContext(), ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();return; }
               if (cantrey.equals("0")){ Toast.makeText(getContext(), ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();return; }
               if (The_required_number.isEmpty()){
                   binding.inputTextLayoutTheRequiredNumber.setError(getString(R.string.the_required_number));
                   return;
               }
                else {
                   StaffRequestDirections.ActionStaffRequestToShowOllStaff staff= StaffRequestDirections.actionStaffRequestToShowOllStaff();
                    staff.setJobTitle(typeJob_title);
                    staff.setQualification(Qualification);
                    staff.setExperience(expriance);
                    staff.setEnglishLanguageLevel(English);
                    staff.setAgefrom(oldfrom);
                    staff.setAgeto(oldto);
                    staff.setCity(cantrey);
                    staff.setGender(gender);
                    staff.setNumberRequired(The_required_number);
                   Navigation.findNavController(binding.getRoot()).navigate(staff);

               }
           }
       });




       return binding.getRoot();
    }
}