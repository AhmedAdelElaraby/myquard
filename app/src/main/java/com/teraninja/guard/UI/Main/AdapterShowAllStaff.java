package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.OnClickShowOllStaff;
import com.teraninja.guard.Model.DataGuard;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemRecHomeBinding;
import com.teraninja.guard.databinding.ItemrecyclershowsalltaffBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterShowAllStaff extends RecyclerView.Adapter<AdapterShowAllStaff.ViewHolder>{
    ArrayList<DataGuard> list = new ArrayList<>();
    ArrayList<String> listidstaff= new ArrayList<>();
    //HashSet<String> listidstaff = new
     String url="https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd";
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    OnClickShowOllStaff staff;

    public AdapterShowAllStaff(OnClickShowOllStaff staff) {
        this.staff = staff;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemrecyclershowsalltaffBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemrecyclershowsalltaff,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date=list.get(position).getUpdated_at();
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

        holder.recyclerBinding.name.setText(list.get(position).getName());
        Picasso.get().load(url+list.get(position).getImage())
                .into(holder.recyclerBinding.image);

        holder.recyclerBinding.select.setChecked(false);
        holder.recyclerBinding.select.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        DataGuard objIncome = list.get(position);

        holder.recyclerBinding.select.setChecked(objIncome.isCheek());

        holder.recyclerBinding.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objIncome.setCheek(holder.recyclerBinding.select.isChecked());
                if (holder.recyclerBinding.select.isChecked()) {
                    String id = String.valueOf(list.get(position).getId());
                    listidstaff.add(id);
                    // Toast.makeText(holder.itemView.getContext(), "mmm" + list.get(position).getGuard_id(), Toast.LENGTH_SHORT).show();
                } else {
                    listidstaff.remove("" + list.get(position).getId());
                    // Toast.makeText(holder.itemView.getContext(), "www" + list.get(position).getGuard_id(), Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(holder.itemView.getContext(), "" + listEmployees.size(), Toast.LENGTH_SHORT).show();
                staff.getidOllStaff(listidstaff.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public ArrayList<String> getguard(){
        return listidstaff;
    }
    public void setSelectedAll(boolean selectedAll) {
        for (DataGuard payment : list) {
            payment.setCheek(selectedAll);
            if (selectedAll) {
                listidstaff.add("" + payment.getId());
                //staff.getid(listEmployees);
            }


        }
        if (!selectedAll) {
            listidstaff.clear();
            //
        }

        staff.getidOllStaff(listidstaff.size());

        notifyDataSetChanged();
    }
    public void getList(ArrayList<DataGuard> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemrecyclershowsalltaffBinding recyclerBinding;


        public ViewHolder(ItemrecyclershowsalltaffBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
