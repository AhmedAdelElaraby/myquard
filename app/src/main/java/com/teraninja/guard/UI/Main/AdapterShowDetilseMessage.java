package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.Model.BodyMessage;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemMessageDetilseBinding;

import java.util.ArrayList;

public class AdapterShowDetilseMessage extends RecyclerView.Adapter<AdapterShowDetilseMessage.ViewHolder>{
    ArrayList<BodyMessage> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

 String date;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemMessageDetilseBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_message_detilse,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.timecollction.setText(""+list.get(position).getDate());
    AdapterShowMessageBody body = new AdapterShowMessageBody();
    LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.VERTICAL,false);
    manager.setReverseLayout(true);
    holder.recyclerBinding.recone.setLayoutManager(manager);
    holder.recyclerBinding.recone.setAdapter(body);
    body.getList(list.get(position).getMessages());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<BodyMessage> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMessageDetilseBinding recyclerBinding;


        public ViewHolder(ItemMessageDetilseBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
