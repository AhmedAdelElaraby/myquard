package com.teraninja.guard;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.OncilckPost;
import com.teraninja.guard.Model.ModelHome;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.UI.HomeActivity;
import com.teraninja.guard.UI.Main.AdapterPostHome;
import com.teraninja.guard.UI.Main.AdapterSlider;
import com.teraninja.guard.UI.MoveViewModel;
import com.teraninja.guard.databinding.FragmentHomeMainBinding;

import java.util.Locale;


public class HomeMain extends Fragment implements OncilckPost {
FragmentHomeMainBinding binding;
MoveViewModel viewModel;
AdapterPostHome adapterPostHome;
AdapterSlider adapterSlider;
Runnable runnable;
Handler handler;
String url="https://guard.teraninjadev.com/storage/";
    SharedPreferences preferences;
    String token;
    String getcase;
    String type;
    public HomeMain() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home_main, container, false);
        viewModel= ViewModelProviders.of(this).get(MoveViewModel.class);
        handler = new Handler();

        String l= Locale.getDefault().getLanguage();
        preferences=getActivity().getSharedPreferences("ar", Context.MODE_PRIVATE);
        type = preferences.getString("Type","no");

        getcase =preferences.getString("case","no");
        if (getcase.equals("yes")){
            token = preferences.getString("Token","no");
            viewModel.getProfile("Bearer"+token,"application/json",l);
        }else {

        }

        viewModel.getNews("application/json",l);
        adapterSlider = new AdapterSlider();
        binding.viewpager.setAdapter(adapterSlider);
        adapterPostHome = new AdapterPostHome(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(adapterPostHome);
        binding.briefcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getcase.equals("no")){
                    ShowDilogeError(getString(R.string.trinslation));

                }else {
                    if (type.equals("guard")){

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeMain_to_offers);
                    }else{

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeMain_to_offersCompant);
                    }
                }
            }
        });
        viewModel.getNews.observe(getViewLifecycleOwner(), new Observer<ModelHome>() {
            @Override
            public void onChanged(ModelHome modelHome) {


                adapterSlider.getList(modelHome.getData().getHome_images());

                adapterPostHome.getList(modelHome.getData().getNews());
                binding.shimmerViewContainer.setVisibility(View.GONE);

                    Log.i("ahmed",modelHome.getData().getNews().get(0).getDescription());




            }
        });
        viewModel.getProfile.observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                Picasso.get().load(url+profile.getData().getImage()).into(binding.imageProfile);


            }
        });


        startAutoSlider(adapterSlider.getItemCount());




binding.imageProfile.setOnClickListener(v -> {
    if (getcase.equals("no")){
        ShowDilogeError(getString(R.string.trinslation));

    }else {
        if (getActivity() instanceof HomeActivity) {
            HomeActivity.binding.drawer.open();
        }
    }
});



        return binding.getRoot();
    }


    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = binding.viewpager.getCurrentItem();
               // pos = pos +1;
                if (pos >= count)
                    pos = pos +1;
                else
                    pos=0;

                binding.viewpager.setCurrentItem(pos);





                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

    }
    public void ShowDilogeError(String text){
        Dialog myDialog;
        TextView textView;
        Button button;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_erorr);
        textView= myDialog.findViewById(R.id.texttitile);
        button=myDialog.findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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


    @Override
    public void getPost(String time, String Post) {
        HomeMainDirections.ActionHomeMainToShowPostOnley postOnley = HomeMainDirections.actionHomeMainToShowPostOnley();
        postOnley.setTime(getString(R.string.Posted_on)+time);
        postOnley.setPost(Post);
        Navigation.findNavController(binding.getRoot()).navigate(postOnley);
    }

}