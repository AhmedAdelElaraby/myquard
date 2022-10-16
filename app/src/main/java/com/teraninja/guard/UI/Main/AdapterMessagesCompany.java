package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.DataClient.OnclickIdMessage;
import com.teraninja.guard.Model.MessageCompnay;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemNotificationBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterMessagesCompany extends RecyclerView.Adapter<AdapterMessagesCompany.ViewHolder>{
    ArrayList<MessageCompnay> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
    OnclickIdMessage message;
    public AdapterMessagesCompany(OnclickIdMessage message) {
        this.message = message;
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

            spf= new SimpleDateFormat("yyyy-MM-dd hh");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.recyclerBinding.time.setText(date);
//        Picasso.get().load(url+list.get(position).);
        holder.recyclerBinding.caseposation.setText(list.get(position).getMessage());
        holder.recyclerBinding.name.setText(list.get(position).getConversation().getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.getId(list.get(position).getConversation_id());
            }
        });
//        String image=list.get(position).getMain_image();
//       if (image==null){
//           holder.recyclerBinding.image.setVisibility(View.GONE);
//       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<MessageCompnay> list) {
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
