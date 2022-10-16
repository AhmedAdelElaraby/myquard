package com.teraninja.guard;

import static com.teraninja.guard.utils.ImagePickerHelper.getPathFromUri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.teraninja.guard.Model.DataDetilsMessage;
import com.teraninja.guard.Model.Modelsendmessage;
import com.teraninja.guard.Model.SendMessage;
import com.teraninja.guard.UI.Main.AdapterShowDetilseMessage;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.ActivityCreateChatBinding;

import java.io.File;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateChat extends AppCompatActivity  {
ActivityCreateChatBinding binding;
MoveViewModel viewModel;
    SharedPreferences preferences;
    String l;
    String token;
    MultipartBody.Part part;
    AdapterShowDetilseMessage message;
    String type;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_create_chat);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        l= Locale.getDefault().getLanguage();
        preferences=getSharedPreferences("ar", Context.MODE_PRIVATE);
        token = preferences.getString("Token","no");
        int id=getIntent().getIntExtra("id",0);
       if (id==0){

       }
        viewModel.getmessageDitalse("Bearer"+token,"application/json",l,id);

        message = new AdapterShowDetilseMessage();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        manager.setReverseLayout(true);
       binding.recchat.setLayoutManager(manager);
       binding.recchat.setAdapter(message);
       binding.add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu menu =new PopupMenu(getApplicationContext(),binding.add);
               menu.getMenuInflater().inflate(R.menu.item_message,menu.getMenu());
               menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.photo:
                               type ="image";
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
                               break;
                           case R.id.video:
                               type = "video";
                               PermissionListener permissionlistener2 = new PermissionListener() {
                                   @Override
                                   public void onPermissionGranted() {
                                       // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent();
                                       intent.setType("image/*");
                                       intent.setAction(Intent.ACTION_GET_CONTENT);
                                       startActivityForResult(Intent.createChooser(intent, "Select Video"),1);

                                   }

                                   @Override
                                   public void onPermissionDenied(List<String> deniedPermissions) {
                                       // Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                                   }


                               };
                               TedPermission.create()
                                       .setPermissionListener(permissionlistener2)
                                       .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                                       .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                       .check();
                               break;
                           case R.id.file:
                               type="file";
                               PermissionListener permissionlistener3 = new PermissionListener() {
                                   @Override
                                   public void onPermissionGranted() {
                                       // Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent();
                                       intent.setType("image/*");
                                       intent.setAction(Intent.ACTION_GET_CONTENT);
                                       startActivityForResult(Intent.createChooser(intent, "Select File"),1);

                                   }

                                   @Override
                                   public void onPermissionDenied(List<String> deniedPermissions) {
                                       // Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                                   }


                               };
                               TedPermission.create()
                                       .setPermissionListener(permissionlistener3)
                                       .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                                       .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                       .check();
                               break;
                           default:
                               menu.dismiss();
                       }
                       return true;
                   }
               });
               menu.show();
           }
       });
       binding.send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              String message= binding.editText.getText().toString().trim();
              if (message==null&&part==null){
                  Toast.makeText(CreateChat.this, "لا يمكن ارسال رساله فارغه", Toast.LENGTH_SHORT).show();
                        return;
              }
               else {
                   if (message!=null){
                       type="text";
                       SendMessage message123= new SendMessage();
                       message123.setType(type);
                       message123.setConversation_id(String.valueOf(id));
                       message123.setMessage(message);
                       MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                       builder.addFormDataPart("conversation_id", message123.getConversation_id())
                               .addFormDataPart("type", message123.getType())
                               .addFormDataPart("message",message123.getMessage());


                               //.addPart(dataSendCV.getImage())


                       RequestBody requestBody = builder.build();
                       viewModel.SendMessage("Bearer"+token,"application/json",l,requestBody);

                   }
                 if (part!=null){
                       SendMessage message123= new SendMessage();
                       message123.setType(type);
                       message123.setConversation_id(String.valueOf(id));
                       message123.setMessage2(part);
                       MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                       builder.addFormDataPart("conversation_id", message123.getConversation_id())
                               .addFormDataPart("type", message123.getType())
//                               addFormDataPart("message",message123.getMessage())
                       .addPart(message123.getMessage2());


                       RequestBody requestBody = builder.build();
                       viewModel.SendMessage("Bearer"+token,"application/json",l,requestBody);
                   }
               }

           }
       });
       viewModel.modelsendmessageMutableLiveData.observe(this, new Observer<Modelsendmessage>() {
           @Override
           public void onChanged(Modelsendmessage modelsendmessage) {
               Toast.makeText(CreateChat.this, ""+modelsendmessage.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
        viewModel.getmessageDitalse.observe(this, new Observer<DataDetilsMessage>() {
            @Override
            public void onChanged(DataDetilsMessage dataDetilsMessage) {
                message.getList(dataDetilsMessage.getData());
                Toast.makeText(CreateChat.this, ""+dataDetilsMessage.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case 1:
                        uri=data.getData();
                        saveImage2();
                        break;


                }
                break;
            case RESULT_CANCELED:
                break;

        }

    }
    private void saveImage2() {
        RequestBody fileReqBody = null;

        File file = new File(getPathFromUri(getApplicationContext(),uri));
        // Create a request body with file and image media type
        fileReqBody = RequestBody.create( file, MediaType.parse("image/*"));
        // Create MultipartBody.Part using file request-body,file name and part name
        part = MultipartBody.Part.createFormData("message", file.getName(), fileReqBody);
    }
}