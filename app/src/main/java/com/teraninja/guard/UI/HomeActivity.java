package com.teraninja.guard.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;
import com.teraninja.guard.Controil;
import com.teraninja.guard.Model.DataLogout;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.FragmentHomeBinding;
import com.teraninja.guard.databinding.ItemHeaderBinding;

import java.util.Locale;

public class HomeActivity extends LocalizationActivity implements DrawerLayout.DrawerListener {
    public static FragmentHomeBinding binding;
    ItemHeaderBinding header;
    private AppBarConfiguration appBarConfiguration;
MoveViewModel viewModel;
    String url="https://guard.teraninjadev.com/storage/";
    String type;
    String token;
    String image;
    String getcase;
    String caseinfo;
    SharedPreferences preferences;
    public SharedPreferences preferences2;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_home);
        String l= Locale.getDefault().getLanguage();
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
         preferences2=getSharedPreferences("lang", Context.MODE_PRIVATE);
        preferences=getSharedPreferences("ar", Context.MODE_PRIVATE);
        getcase =preferences.getString("case","no");



        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(binding.navHostFragment.getId());
         navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.nav, navController);
        View viewheader=binding.nav.getHeaderView(0);


        binding.drawer.addDrawerListener(this);
        header=ItemHeaderBinding.bind(viewheader);

        header.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getcase.equals("no")){
                    binding.drawer.close();
                    ShowDilogeError(getString(R.string.trinslation));
                    return;
                }
                else {
                    if (caseinfo!=null){
                        binding.drawer.close();
                        ShowDilogeErrorinfo(getString(R.string.You_have_added));
                    }else {
                        binding.drawer.close();
                        navController.navigate(R.id.information2);
                    }

                }
            }
        });
 header.technicalSupport.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         if (getcase.equals("no")){
             binding.drawer.close();
             ShowDilogeError(getString(R.string.trinslation));
                return;
         }else {
         binding.drawer.close();
         navController.navigate(R.id.technicalSupport);
         }
     }
 });
header.StaffRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        binding.drawer.close();
        navController.navigate(R.id.staffRequest);
    }
});

        NavHostFragment navHostFragment2 =(NavHostFragment) getSupportFragmentManager().findFragmentById(binding.navHostFragment.getId());
        NavController navController2 = navHostFragment2.getNavController();
        navController2.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId()==R.id.information2) {binding.btnNav.setVisibility(View.GONE);
                return;}
                if (destination.getId()==R.id.userProfile){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.showOllStaff){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.employees){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.staffRequest){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.offersCompant){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.subscriptionsCompany){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.technicalSupport){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.filterGuardWthiCompany){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.submitJobOffer){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.offers){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.showPostOnley){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.subscribeguard){binding.btnNav.setVisibility(View.GONE);return;}
                if (destination.getId()==R.id.showOllguardAcceptOffer){binding.btnNav.setVisibility(View.GONE);return;}

                else {

//                    binding.btnNav.setAlpha(0f);
//                    binding.btnNav.setTranslationY(50);
//                    binding.btnNav.animate().alpha(1f).translationYBy(-50).setDuration(1500);
//                    /////
//                    binding.btnNav.setAlpha(0f);
//                    binding.btnNav.setTranslationY(50);
//                    binding.btnNav.animate().alpha(1f).translationYBy(-50).setDuration(1500);
//                    //////
//                    binding.btnNav.setAlpha(0f);
//                    binding.btnNav.setTranslationY(50);
//                    binding.btnNav.animate().alpha(1f).translationYBy(-50).setDuration(1500);
                    binding.btnNav.setVisibility(View.VISIBLE);


                }
            }
        });
        NavigationUI.setupWithNavController(binding.btnNav,navController2);
        binding.btnNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeMain:
                        NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.homeMain, true).build();

                        navController2.navigate(R.id.homeMain,savedInstanceState,builder1);
                        break;
                    case R.id.notifications3:

                        if (getcase.equals("no")){

                            ShowDilogeError(getString(R.string.trinslation));

                        }
                         else {
                        NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.notifications3, true).build();

                        navController2.navigate(R.id.notifications3,savedInstanceState,builder);
                        }
                     break;
                    case R.id.messages2:
                        if (getcase.equals("no")){

                            ShowDilogeError(getString(R.string.trinslation));

                        }
                        else {
                            if (type.equals("guard")){
                                NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.messages2, true).build();

                                navController2.navigate(R.id.messages2,savedInstanceState,builder);
                            }else {
                                NavOptions builder = new NavOptions.Builder().setPopUpTo(R.id.messageCompany, true).build();

                                navController2.navigate(R.id.messageCompany,savedInstanceState,builder);
                            }

                        }
                        break;
                }
                return false;
            }
        });
header.Staff.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        binding.drawer.close();
        navController.navigate(R.id.filterGuardWthiCompany);
    }
});
header.ShowAllJobs.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (getcase.equals("no")){
            binding.drawer.close();
            ShowDilogeError(getString(R.string.trinslation));
        }
        else{
            if (type.equals("guard")){
                binding.drawer.close();

                navController.navigate(R.id.offers);
                return;
            }
            if (type.equals("company")){
                binding.drawer.close();
                navController.navigate(R.id.offersCompant);
                return;
            }
        }
    }
});
header.lang.setText(getString(R.string.languageEn));

header.lang.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (getCurrentLanguage().getLanguage().equals("en")){
            setLanguage("ar");
            header.lang.setText(getString(R.string.languageEn));

        }else {
            setLanguage("en");
            header.lang.setText(getString(R.string.languageAr));

        }
       // Log.e("how",""+getCurrentLocale(getApplicationContext()));
//        if (getCurrentLocale(getApplicationContext()).equalsIgnoreCase("en")){
//            setLocale(getApplicationContext(),"ar",true);
//        }
//        else{
//            setLocale(getApplicationContext(),"en",true);
//        }
    }
});
header.SignOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (getcase.equals("no")){
            binding.drawer.close();
            ShowDilogeError(getString(R.string.trinslation));
        }else {
            viewModel.getlogout("Bearer" + token, "application/json", l);
        }
    }
});
viewModel.getlogout.observe(this, new Observer<DataLogout>() {
    @Override
    public void onChanged(DataLogout dataLogout) {
        Toast.makeText(HomeActivity.this, ""+dataLogout.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(HomeActivity.this, Controil.class);
        startActivity(intent);
        finish();
    }
});
header.subscriptions.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (getcase.equals("no")){
            binding.drawer.close();
            ShowDilogeError(getString(R.string.trinslation));
            // Toast.makeText(HomeActivity.this, "لايمكنك الدخول هنا الا بعد تسجيل الدخول ", Toast.LENGTH_SHORT).show();
            return;
        }
        else  {
            if (type.equals("guard")){
                binding.drawer.close();

                navController.navigate(R.id.subscribeguard);
                return;
            }
            if (type.equals("company")){
                binding.drawer.close();
                navController.navigate(R.id.subscriptionsCompany);
                return;
            }

        }

    }
});
header.layoutProfileView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (getcase.equals("no")){
            binding.drawer.close();
            ShowDilogeError(getString(R.string.trinslation));
           // Toast.makeText(HomeActivity.this, "لايمكنك الدخول هنا الا بعد تسجيل الدخول ", Toast.LENGTH_SHORT).show();
            return;
        }
        else  {
            if (type.equals("guard")){
                binding.drawer.close();

            navController.navigate(R.id.userProfile);
                return;
            }
            if (type.equals("company")){
                binding.drawer.close();
                navController.navigate(R.id.profileCompany);
                return;
            }

        }

//        if (type.equals("guard")){
//            binding.drawer.close();
//
//            navController.navigate(R.id.userProfile);
//            return;
//        }
//        else{
//            binding.drawer.close();
//            navController.navigate(R.id.profileCompany);
//
//        }


    }
});
    viewModel.getProfile.observe(this, new Observer<Profile>() {
        @Override
        public void onChanged(Profile profile) {
                header.textfullname.setText(profile.getData().getName());
                caseinfo=profile.getData().getImage();
            Toast.makeText(HomeActivity.this, ""+caseinfo, Toast.LENGTH_SHORT).show();
                Picasso.get().load(url+profile.getData().getImage()).into(binding.imageProfile);
                Picasso.get().load(url+profile.getData().getImage()).into(header.imageprofile);






            Toast.makeText(HomeActivity.this, ""+type, Toast.LENGTH_SHORT).show();
        }
    });




        if (getcase.equals("no")){
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
            header.StaffRequest.setVisibility(View.GONE);
            header.information.setVisibility(View.VISIBLE);
            header.Staff.setVisibility(View.GONE);


            return;
        }
        else {
            type = preferences.getString("Type","no");
            token = preferences.getString("Token","no");
            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            if (type.equals("guard")){

                header.StaffRequest.setVisibility(View.GONE);
                header.information.setVisibility(View.VISIBLE);
                header.Staff.setVisibility(View.GONE);
                viewModel.getProfile("Bearer"+token,"application/json",l);
                return;
            }
            if (type.equals("company")){
                header.information.setVisibility(View.GONE);
                header.StaffRequest.setVisibility(View.VISIBLE);
                header.Staff.setVisibility(View.VISIBLE);
                viewModel.getProfile("Bearer"+token,"application/json",l);
                return;
            }


        }
    }

    public void ShowDilogeError(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.item_erorr);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             HomeActivity.super.onBackPressed();
//                NavController navController = Navigation.findNavController(, R.id.fragmentContainerView);
//                navController.navigateUp();
                //navController.navigate(R.id.login2);
            }
        });
        textView.setText(""+text);
        Window window = myDialog.getWindow();

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
    public void ShowDilogeErrorinfo(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.item_erorr);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                navController.navigate(R.id.updateProfileGuard);

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
    public boolean onSupportNavigateUp() {
        NavController navController =(NavController) Navigation.findNavController(binding.navHostFragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {



    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
//    public  void setLocale(Context context,String locale,boolean restart){
//       Locale myLocale=new Locale(locale);
//        Resources resources=context.getResources();
//        DisplayMetrics dm=resources.getDisplayMetrics();
//        Configuration conf=resources.getConfiguration();
//        conf.locale=myLocale;
//        Locale.setDefault(myLocale);
//        conf.setLayoutDirection(myLocale);
//        resources.updateConfiguration(conf,dm);
//        if (restart){
//            storlocalesharePrefernces(locale);
//            restartApp(context);
//        }
//    }
//    public  void restartApp(Context context){
//        Intent intent = new Intent(context,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
//    public  void storlocalesharePrefernces(String Locale){
//        String pre="lange";
//        SharedPreferences.Editor editor =preferences2.edit();
//     editor.putString(pre,Locale);
//    editor.commit();
//    }
//    public  String getCurrentLocale(Context context){
//        Locale currant=context.getResources().getConfiguration().locale;
//        return currant.getLanguage();
//    }
}