package com.teraninja.guard.UI.Main;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.Model.HomeImage;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemRecHomeBinding;
import com.teraninja.guard.databinding.ItemSliderBinding;

import java.util.ArrayList;

public class AdapterSlider extends RecyclerView.Adapter<AdapterSlider.ViewHolder>{
    ArrayList<HomeImage> list = new ArrayList<>();
    String url="https://guard.teraninjadev.com/storage/";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemSliderBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_slider,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String type=list.get(position).getType();
    if (type.equals("image")){
        Picasso.get().load(url+list.get(position).getPath()).into(holder.recyclerBinding.imageslider);
        holder.recyclerBinding.videoslider.setVisibility(View.GONE);
        holder.recyclerBinding.imageslider.setVisibility(View.VISIBLE);

    }if (type.equals("video")){
            holder.recyclerBinding.videoslider.start();
        holder.recyclerBinding.imageslider.setVisibility(View.GONE);
            holder.recyclerBinding.videoslider.setVisibility(View.VISIBLE);

            Uri uri=Uri.parse(url+list.get(position).getPath());
        holder.recyclerBinding.videoslider.setVideoURI(uri);
        holder.recyclerBinding.videoslider.start();


        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<HomeImage> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemSliderBinding recyclerBinding;


        public ViewHolder(ItemSliderBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
