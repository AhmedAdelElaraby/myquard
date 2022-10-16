package com.teraninja.guard.UI.Main;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.Model.MessageShow;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemMessageDetilseBinding;
import com.teraninja.guard.databinding.ItemRecyclerChatBodyBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterShowMessageBody extends RecyclerView.Adapter<AdapterShowMessageBody.ViewHolder>{
    ArrayList<MessageShow> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

 String date;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemRecyclerChatBodyBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_recycler_chat_body,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String image=list.get(position).getType();

        date=list.get(position).getCreated_at();
        SimpleDateFormat spf=new SimpleDateFormat(dateFormat);
        Date newDate;
        try {
            newDate = spf.parse(date);

            spf= new SimpleDateFormat("HH:mm:ss");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.recyclerBinding.timemessagefield.setText(""+date);

        //  Toast.makeText(holder.itemView.getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();

        switch (image){
            case "text":
                holder.recyclerBinding.text.setText(list.get(position).getMessage());
                holder.recyclerBinding.text.setVisibility(View.VISIBLE);
                holder.recyclerBinding.videomessage.setVisibility(View.GONE);
                holder.recyclerBinding.messageimage.setVisibility(View.GONE);
                break;
            case "video":
                holder.recyclerBinding.videomessage.start();
                holder.recyclerBinding.messageimage.setVisibility(View.GONE);
                holder.recyclerBinding.videomessage.setVisibility(View.VISIBLE);
                holder.recyclerBinding.text.setVisibility(View.GONE);
                Uri uri=Uri.parse(url+list.get(position).getMessage());
                holder.recyclerBinding.videomessage.setVideoURI(uri);
                holder.recyclerBinding.videomessage.start();
                break;
            case "image":
                Picasso.get().load(url+list.get(position).getMessage()).into(holder.recyclerBinding.messageimage);

                holder.recyclerBinding.videomessage.setVisibility(View.GONE);
                holder.recyclerBinding.messageimage.setVisibility(View.VISIBLE);
                break;
            case "file":
             holder.recyclerBinding.text.setText(list.get(position).getMessage());
            holder.recyclerBinding.text.setVisibility(View.VISIBLE);
            holder.recyclerBinding.videomessage.setVisibility(View.GONE);
            holder.recyclerBinding.messageimage.setVisibility(View.GONE);
            break;
            default:

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<MessageShow> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecyclerChatBodyBinding recyclerBinding;


        public ViewHolder(ItemRecyclerChatBodyBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
