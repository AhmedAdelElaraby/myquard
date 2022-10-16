package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.DataClient.OnClickRecyclerCondation;
import com.teraninja.guard.Model.DataItem;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemCondationBinding;
import com.teraninja.guard.databinding.ItemRecHomeBinding;

import java.util.ArrayList;

public class AdapterItemCondation extends RecyclerView.Adapter<AdapterItemCondation.ViewHolder>{
    ArrayList<DataItem> list = new ArrayList<>();
OnClickRecyclerCondation onClickRecyclerCondation;

    public AdapterItemCondation(OnClickRecyclerCondation onClickRecyclerCondation) {
        this.onClickRecyclerCondation = onClickRecyclerCondation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemCondationBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_condation,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos=position;
        pos++;
        holder.recyclerBinding.itemone.setText(pos+" - "+list.get(position).getCondition());
        onClickRecyclerCondation.getitemid(list.get(position).getJop_type_id());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DataItem> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemCondationBinding recyclerBinding;


        public ViewHolder(ItemCondationBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
