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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.guard.Model.DataProblem;
import com.teraninja.guard.Model.DataReasons;
import com.teraninja.guard.Model.SendDataProblem;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentTechnicalSupportBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class TechnicalSupport extends Fragment {
FragmentTechnicalSupportBinding binding;
MoveViewModel viewModel;
ArrayList<String> listReasons= new ArrayList<>();
ArrayList<Integer> listReasonsid= new ArrayList<>();
String idreasons;
    Uri uri;
    String l;
    MultipartBody.Part part = null;
    SharedPreferences preferences;
    String token;
    Dialog myDialog;
    String type;
    int num=0;
    public TechnicalSupport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_technical_support, container, false);
       viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        viewModel.getReasons("application/json",l);

        viewModel.Reasonslivedata.observe(getViewLifecycleOwner(), new Observer<DataReasons>() {
            @Override
            public void onChanged(DataReasons dataReasons) {
                listReasons.clear();
                listReasonsid.clear();
                listReasons.add("Chose Cause of the problem");
                listReasonsid.add(0);
                for (int i=0;i<dataReasons.getData().size();i++){
                    listReasons.add(dataReasons.getData().get(i).getReason());
                    listReasonsid.add(dataReasons.getData().get(i).getId());
                }
                ArrayAdapter<String> Reasons = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listReasons);
                 binding.cantreyJob.setAdapter(Reasons);

            }
        });
     binding.cantreyJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           idreasons =""+listReasonsid.get(position).toString();
             Toast.makeText(getContext(), ""+idreasons, Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     binding.upload.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             showdilogechose();
         }
     });
    binding.file.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Problem=binding.problem.getText().toString().trim();
            if (Problem.isEmpty()){
                binding.problem.setError("Please Enter your Problem");
                return;
            }
            if (idreasons.equals("0")){
                Toast.makeText(getContext(), "Please Chose Cause of the problem", Toast.LENGTH_SHORT).show();
                return;
            }else{
                if (num==0){
                    SendDataProblem problem = new SendDataProblem();
                    problem.setContact_reason_id(idreasons);
                    problem.setMessage(Problem);

                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("contact_reason_id",problem.getContact_reason_id());
                    builder.addFormDataPart("message",problem.getMessage());
                    RequestBody requestBody = builder.build();
                    viewModel.getProblem("Bearer"+token,"application/json",l,requestBody);

                }else {
                    if (part==null){
                        Toast.makeText(getContext(), "Please Chose Photo", Toast.LENGTH_SHORT).show();
                    }else {
                        SendDataProblem problem = new SendDataProblem();
                        problem.setContact_reason_id(idreasons);
                        problem.setMessage(Problem);
                        problem.setFile(part);
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addFormDataPart("contact_reason_id", problem.getContact_reason_id());
                        builder.addFormDataPart("message", problem.getMessage());
                        builder.addPart(problem.getFile());
                        builder.addFormDataPart("type",type);
                        RequestBody requestBody = builder.build();
                        viewModel.getProblem("Bearer" + token, "application/json", l, requestBody);
                    }
                }

            }

        }
    });
    viewModel.problem.observe(getViewLifecycleOwner(), new Observer<DataProblem>() {
        @Override
        public void onChanged(DataProblem dataProblem) {
            if (dataProblem.getStatus()==1){
                Toast.makeText(getContext(), ""+dataProblem.getMessage(), Toast.LENGTH_SHORT).show();
                myDialog = new Dialog(getContext());
                myDialog.setContentView(R.layout.dilog_of_the_situation);
                ImageView imageView = myDialog.findViewById(R.id.imagedone);
                TextView textView = myDialog.findViewById(R.id.textdone);
                textView.setText(R.string.casemessage);
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                Window window = myDialog.getWindow();

                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.create();
                myDialog.show();
                dataProblem.setStatus(3);
            }else
            {
                Toast.makeText(getContext(), ""+dataProblem.getMessage(), Toast.LENGTH_SHORT).show();
            }
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

                        binding.textTrins.setText(""+uri.getPath());
                        num=1;
                        saveImage();

                        Toast.makeText(getContext(), ""+uri.getPath(), Toast.LENGTH_SHORT).show();
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
        part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
    }
    public void showdilogechose(){
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_chose_uplode);
        TextView photo = myDialog.findViewById(R.id.uploadPhoto);
        TextView video = myDialog.findViewById(R.id.upload_Viduo);
        TextView cancel = myDialog.findViewById(R.id.cancel);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="image";
                myDialog.dismiss();
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
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="video";
                myDialog.dismiss();
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
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
}