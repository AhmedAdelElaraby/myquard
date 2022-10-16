package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.OncilckPost;
import com.teraninja.guard.Model.News;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemRecHomeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterPostHome extends RecyclerView.Adapter<AdapterPostHome.ViewHolder>{
    ArrayList<News> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
    OncilckPost post;
 String date;
    public AdapterPostHome(OncilckPost post) {
        this.post = post;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemRecHomeBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_rec_home,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         date=list.get(position).getCreated_at();
        SimpleDateFormat spf=new SimpleDateFormat(dateFormat);
        Date newDate;
        try {
            newDate = spf.parse(date);

            spf= new SimpleDateFormat("yyyy-MM-dd");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.recyclerBinding.timetext.setText(holder.itemView.getContext().getString(R.string.Posted_on)+" "+date);
        Picasso.get().load(url+list.get(position).getMain_image()).into(holder.recyclerBinding.image);
        holder.recyclerBinding.news.setText(list.get(position).getDescription());
        String image=list.get(position).getMain_image();
       if (image==null){
           holder.recyclerBinding.image.setVisibility(View.GONE);
       }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               post.getPost(date,list.get(position).getDescription());
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<News> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecHomeBinding recyclerBinding;


        public ViewHolder(ItemRecHomeBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
