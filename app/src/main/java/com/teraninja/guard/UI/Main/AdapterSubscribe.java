package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.Model.Subscribe;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemRecHomeBinding;
import com.teraninja.guard.databinding.ItemsubscribeBinding;

import java.util.ArrayList;

public class AdapterSubscribe extends RecyclerView.Adapter<AdapterSubscribe.ViewHolder>{
    ArrayList<Subscribe> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemsubscribeBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemsubscribe,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.textnamepackage.setText(list.get(position).getSubscribe_package_title());
        holder.recyclerBinding.textlastday.setText(""+list.get(position).getTheNumberOfDaysLeft());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Subscribe> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemsubscribeBinding recyclerBinding;


        public ViewHolder(ItemsubscribeBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
