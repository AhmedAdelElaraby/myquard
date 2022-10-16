package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.DataClient.OncilckNotification;
import com.teraninja.guard.Model.Notifications;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemNotificationBinding;
import com.teraninja.guard.databinding.ItemRecHomeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder>{
    ArrayList<Notifications> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
    OncilckNotification notification;

    public AdapterNotification(OncilckNotification notification) {
        this.notification = notification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemNotificationBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_notification,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date=list.get(position).getCreated_at();
        SimpleDateFormat spf=new SimpleDateFormat(dateFormat);
        Date newDate;
        try {
            newDate = spf.parse(date);

            spf= new SimpleDateFormat("yyyy-MM-dd");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.recyclerBinding.name.setText(list.get(position).getNotification().getTitle());
        holder.recyclerBinding.caseposation.setText(list.get(position).getNotification().getMessage());
        holder.recyclerBinding.time.setText(date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification.getidNotifications(list.get(position).getId());
            }
        });
//
//
//        holder.recyclerBinding.timetext.setText(date);
//        Picasso.get().load(url+list.get(position).getMain_image()).into(holder.recyclerBinding.image);
//        holder.recyclerBinding.news.setText(list.get(position).getDescription());
//        String image=list.get(position).getMain_image();
//       if (image==null){
//           holder.recyclerBinding.image.setVisibility(View.GONE);
//       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Notifications> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemNotificationBinding recyclerBinding;


        public ViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
