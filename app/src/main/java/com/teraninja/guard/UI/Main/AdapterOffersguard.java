package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.Accept;
import com.teraninja.guard.Model.OfferguardData;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemNotificationBinding;
import com.teraninja.guard.databinding.ItemoffersguardBinding;

import java.util.ArrayList;

public class AdapterOffersguard extends RecyclerView.Adapter<AdapterOffersguard.ViewHolder>{
    ArrayList<OfferguardData> list = new ArrayList<>();
    String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
Accept accept;

    public AdapterOffersguard(Accept accept) {
        this.accept = accept;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemoffersguardBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemoffersguard,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.name.setText(list.get(position).getJob().getCompany().getName());
        holder.recyclerBinding.time.setText(list.get(position).getJob().getCreated_at());
        Picasso.get().load(url+list.get(position).getJob().getCompany().getImage());
        holder.recyclerBinding.posation.setText(holder.itemView.getContext().getString(R.string.posation)+" : "+list.get(position).getJob().getJop_type().getName());
        holder.recyclerBinding.city.setText(holder.itemView.getContext().getString(R.string.city)+" : "+list.get(position).getJob().getCity().getName());
        holder.recyclerBinding.restDays.setText(holder.itemView.getContext().getString(R.string.rest_days)+" : "+list.get(position).getJob().getHoliday());
        holder.recyclerBinding.TheNumber.setText(holder.itemView.getContext().getString(R.string.the_number_of_working_hours)+" : "+list.get(position).getJob().getNo_of_hours());
        holder.recyclerBinding.Salary.setText(holder.itemView.getContext().getString(R.string.salary)+" : "+list.get(position).getJob().getSalary());
        holder.recyclerBinding.LastDate.setText(holder.itemView.getContext().getString(R.string.days_left)+" : "+list.get(position).getJob().getLast_date_to_accept());
        holder.recyclerBinding.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accept.Accept(list.get(position).getId());
                notifyDataSetChanged();
            }
        });
        holder.recyclerBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.cancel(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void getList(ArrayList<OfferguardData> list) {
        this.list=list;
        notifyDataSetChanged();

    }
    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemoffersguardBinding recyclerBinding;


        public ViewHolder(ItemoffersguardBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
