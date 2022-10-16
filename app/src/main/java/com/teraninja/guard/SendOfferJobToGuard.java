package com.teraninja.guard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.teraninja.guard.Model.DataCity;
import com.teraninja.guard.Model.DataResponseoffersjob;
import com.teraninja.guard.Model.DataWorkNatures;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.SendDataOfferJob;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.ActivitySendOfferJobToGuardBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SendOfferJobToGuard extends LocalizationActivity {
    ActivitySendOfferJobToGuardBinding binding;
    MoveViewModel viewModel;
    ArrayList<Integer> listid= new ArrayList<>();
    ArrayList<String> listcitys= new ArrayList<>();
    ArrayList<String> listDistricts= new ArrayList<>();
    ArrayList<Integer> listDistrictsid= new ArrayList<>();
    ArrayList<String> listworknat= new ArrayList<>();
    ArrayList<Integer> listworknatid= new ArrayList<>();
    int cuontrey;
    int city;
    int worknat;
    String typejobsend;
    String days;
    String datatype = "0";
    String detilse;
    DatePickerDialog.OnDateSetListener setListener;
    SharedPreferences preferences;
    String l;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_send_offer_job_to_guard);
        ArrayList<String> list=getIntent().getStringArrayListExtra("id");
        Toast.makeText(this, ""+list.get(0), Toast.LENGTH_SHORT).show();
        viewModel = ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences=getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
         l= Locale.getDefault().getLanguage();
        viewModel.getCity("application/json",l);
        viewModel.WorkVatures("application/json",l);

        viewModel.getcity.observe(this, new Observer<ModelCity>() {
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
                ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listcitys);
                binding.city.setAdapter(adaptercity);
            }
        });
        binding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuontrey= listid.get(position);
                viewModel.getDistricts(listid.get(position),"application/json",l);
                Toast.makeText(SendOfferJobToGuard.this, ""+listid.get(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         String []typejob = {getString(R.string.Chose_Job_title),getString(R.string.guard),getString(R.string.Organizer),getString(R.string.volunteer_)};
        ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typejob);
        binding.namejob.setAdapter(adaptercity);
        binding.namejob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.namejob.getSelectedItem().toString()){
                    case "اختر المسمي الوظيفي":
                        typejobsend="0";
                        break;
                    case "Chose Job title":
                        typejobsend="0";
                        break;
                    case "حارس امن":
                        typejobsend="1";
                        break;
                    case "guard":
                        typejobsend="1";
                        break;
                    case "منظم":
                        typejobsend="2";
                        break;
                    case "Organizer":
                        typejobsend="2";
                        break;
                    case "متطوع":
                        typejobsend="3";
                        break;
                    case "volunteer":
                        typejobsend="3";
                        break;
                    default:

                }
                Toast.makeText(SendOfferJobToGuard.this, ""+typejobsend, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String[] holday={getString(R.string.chose_number_work),getString(R.string.one_day),getString(R.string.two_day)};
        ArrayAdapter<String> adapternumberdayswork = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, holday);
        binding.restwork.setAdapter(adapternumberdayswork);
        binding.restwork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.restwork.getSelectedItem().toString()){
                    case "اختر عدد ايام الاجازات":
                        days="0";
                        break;
                    case "chose number holidays Days":
                        days="0";
                        break;
                    case "One Day":
                        days="one_day";
                        break;
                    case "Two Day":
                        days="two_day";
                        break;
                    case "يوم واحد":
                        days="one_day";
                        break;
                    case "يومين":
                        days="two_day";
                        break;
                    default:
                        days=binding.restwork.getSelectedItem().toString();
                }
                Toast.makeText(SendOfferJobToGuard.this, ""+days, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewModel.getDistricts.observe(this, new Observer<DataCity>() {
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
                ArrayAdapter<String> adapterDistricts = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listDistricts);
                binding.district.setAdapter(adapterDistricts);
            }
        });
        binding.district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=listDistrictsid.get(position);
                 Toast.makeText(SendOfferJobToGuard.this, ""+city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Calendar cal= Calendar.getInstance();
         int year=cal.get(Calendar.YEAR);
         int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
       binding.lastDates.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DatePickerDialog dialog= new DatePickerDialog(SendOfferJobToGuard.this
                       ,R.style.MaterialAlertDialog_Material3,setListener,year,month,day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

               dialog.show();
           }
       });
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                i1 =i1+1;
                String date=i+"-"+i1+"-"+i2;
                datatype=date;

                binding.lastDates.setText(date);
                Toast.makeText(SendOfferJobToGuard.this, ""+datatype, Toast.LENGTH_SHORT).show();


            }
        };
    viewModel.worknat.observe(this, new Observer<DataWorkNatures>() {
        @Override
        public void onChanged(DataWorkNatures dataWorkNatures) {
            listworknat.clear();
            listworknatid.clear();
            listworknat.add(getString(R.string.Choose_the_nature));
            listworknatid.add(0);
            for (int i=0;i<dataWorkNatures.getData().size();i++) {

                listworknat.add(dataWorkNatures.getData().get(i).getNature());
                listworknatid.add(dataWorkNatures.getData().get(i).getId());
            }
            ArrayAdapter<String> adapterDistricts = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listworknat);
            binding.watherwork.setAdapter(adapterDistricts);
        }
    });
        binding.watherwork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                worknat=listworknatid.get(position);
                if (worknat==1){
                    binding.detise.setVisibility(View.VISIBLE);
                }
                else {
                    binding.detise.setVisibility(View.GONE);

                }

                 Toast.makeText(SendOfferJobToGuard.this, ""+worknat, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.SendOfferJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String salary=binding.Salary.getText().toString().trim();
              String numofworddays=binding.numofworddays.getText().toString().trim();
              String working_hours=binding.workingHours.getText().toString().trim();
              String city1=String.valueOf(cuontrey);
              String dis=String.valueOf(city);
              String worknatare=String.valueOf(worknat);
              detilse=binding.detise.getText().toString().trim();

                if (salary.isEmpty()){
                  binding.inputTextLayoutsalary.setError(getString(R.string.salary));
                  return;
              }
              if (numofworddays.isEmpty()){
                  binding.inputTextLayoutworkday.setError(getString(R.string.the_number_of_working_days));
                  return;
              }
              if (working_hours.isEmpty()){
                  binding.inputTextLayoutworkingHours.setError(getString(R.string.the_number_of_working_hours));
                  return;
              }
              if (city1.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.ChoseCity), Toast.LENGTH_SHORT).show();
                    return;
              }
              if (typejobsend.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.Chose_Job_title), Toast.LENGTH_SHORT).show();
                    return;
              }
              if (days.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.chose_number_work), Toast.LENGTH_SHORT).show();
                  return;
              }
              if (dis.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.Chosedistrict), Toast.LENGTH_SHORT).show();
                  return;
              }
              if (datatype.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.last_date_to_accept), Toast.LENGTH_SHORT).show();
                  return;
              }
              if (worknatare.equals("0")){
                  Toast.makeText(SendOfferJobToGuard.this, ""+getString(R.string.Choose_the_nature), Toast.LENGTH_SHORT).show();
                  return;
              }



              else{
                  Toast.makeText(SendOfferJobToGuard.this, "bbbbb", Toast.LENGTH_SHORT).show();
                  SendDataOfferJob offerJob = new SendDataOfferJob();
                  offerJob.setCity_id(city1);
                  offerJob.setDistrict_id(dis);
                  offerJob.setHoliday(days);
                  offerJob.setJop_type_id(typejobsend);
                  offerJob.setNo_of_days(numofworddays);
                  offerJob.setNo_of_hours(working_hours);
                  offerJob.setLast_date_to_accept(datatype);
                  offerJob.setSalary(salary);
                  offerJob.setWork_nature_id(worknatare);
                  offerJob.setWork_nature_text(detilse);
                  offerJob.setUsers(list);
//                  JsonObject imgObject = new JsonObject();
//                  imgObject.addProperty("holiday",days);

                  viewModel.sendofferjob("Bearer"+token,"application/json",l,offerJob);
              }
            }
        });
        viewModel.dataoffersjob.observe(this, new Observer<DataResponseoffersjob>() {
            @Override
            public void onChanged(DataResponseoffersjob dataResponseoffersjob) {
                if (dataResponseoffersjob.getStatus()==1){
                    Toast.makeText(SendOfferJobToGuard.this, ""+dataResponseoffersjob.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}