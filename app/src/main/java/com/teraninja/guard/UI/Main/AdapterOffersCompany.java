package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.OnclickIdOffer;
import com.teraninja.guard.Model.DatauserOffer;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemJobOffersBinding;
import com.teraninja.guard.databinding.ItemRecHomeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterOffersCompany extends RecyclerView.Adapter<AdapterOffersCompany.ViewHolder>{
    ArrayList<DatauserOffer> list = new ArrayList<>();
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd";
    OnclickIdOffer offer;

    public AdapterOffersCompany(OnclickIdOffer offer) {
        this.offer = offer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemJobOffersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_job_offers,parent,false);

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
        holder.recyclerBinding.time.setText(date);
        Picasso.get().load(url+list.get(position).getCompany().getImage()).into(holder.recyclerBinding.imagecompany);
        holder.recyclerBinding.name.setText(""+list.get(position).getCompany().getName());
        holder.recyclerBinding.posation.setText(holder.itemView.getContext().getString(R.string.function)+" : "+list.get(position).getWork_nature().getNature());
        holder.recyclerBinding.city.setText(holder.itemView.getContext().getString(R.string.citys)+" : "+list.get(position).getCity().getName());
        holder.recyclerBinding.TheNumber.setText(holder.itemView.getContext().getString(R.string.Last_date)+" : "+list.get(position).getLast_date_to_accept());
        holder.recyclerBinding.LastDate.setText(holder.itemView.getContext().getString(R.string.target_number)+" : "+list.get(position).getJob_users_count());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer.getIdOffer(list.get(position).getId(),list.get(position).getJop_type_id());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DatauserOffer> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemJobOffersBinding recyclerBinding;


        public ViewHolder(ItemJobOffersBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
