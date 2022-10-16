package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.DataClient.InterFacePackageId;
import com.teraninja.guard.Model.Package_Data;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemCondationBinding;
import com.teraninja.guard.databinding.ItemDailogPaymentBinding;

import java.util.ArrayList;

public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.ViewHolder>{
    ArrayList<Package_Data> list = new ArrayList<>();
    InterFacePackageId interFacePackageId;

    public AdapterPayment(InterFacePackageId interFacePackageId) {
        this.interFacePackageId = interFacePackageId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemDailogPaymentBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_dailog_payment,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.textone.setText(list.get(position).getTitle());
        holder.recyclerBinding.textD.setText(list.get(position).getDescription()+"  "+list.get(position).getNo_of_days());
        holder.recyclerBinding.textpric.setText("SR"+list.get(position).getPrice());
        holder.recyclerBinding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interFacePackageId.getPackageId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Package_Data> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDailogPaymentBinding recyclerBinding;


        public ViewHolder(ItemDailogPaymentBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
