package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.guard.DataClient.OncilckEmployees;
import com.teraninja.guard.Model.DataGuardPayment;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemrecyclershowsalltaffBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterEmployees extends RecyclerView.Adapter<AdapterEmployees.ViewHolder> {
    ArrayList<DataGuardPayment> list = new ArrayList<>();
    ArrayList<String> listEmployees = new ArrayList<>();

    String url = "https://guard.teraninjadev.com/storage/";
    String dateFormat = "yyyy-MM-dd";
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    OncilckEmployees staff;
//    boolean isSelectedAll;

    public AdapterEmployees(OncilckEmployees staff) {
        this.staff = staff;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemrecyclershowsalltaffBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemrecyclershowsalltaff, parent, false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String date = list.get(position).getCompany_guard().getUpdated_at();
        SimpleDateFormat spf = new SimpleDateFormat(dateFormat);
        Date newDate;
        try {
            newDate = spf.parse(date);

            spf = new SimpleDateFormat("yyyy-MM-dd");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.recyclerBinding.time.setText(holder.itemView.getContext().getString(R.string.last_Update) + "  " + date);

        holder.recyclerBinding.name.setText(list.get(position).getCompany_guard().getName());
        Picasso.get().load(url + list.get(position).getCompany_guard().getImage())
                .into(holder.recyclerBinding.image);
        holder.recyclerBinding.moda.setVisibility(View.VISIBLE);

        holder.recyclerBinding.moda.setText(holder.itemView.getContext().getString(R.string.days_left) + " " + list.get(position).getThe_number_of_days_left());

        holder.recyclerBinding.crad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                staff.getidoneguard(list.get(position).guard_id);

            }
        });


        holder.recyclerBinding.select.setChecked(false);
        holder.recyclerBinding.select.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        DataGuardPayment objIncome = list.get(position);

        holder.recyclerBinding.select.setChecked(objIncome.isCheek());

        holder.recyclerBinding.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objIncome.setCheek(holder.recyclerBinding.select.isChecked());
                if (holder.recyclerBinding.select.isChecked()) {
                    String id = String.valueOf(list.get(position).getGuard_id());
                    listEmployees.add(id);
                    // Toast.makeText(holder.itemView.getContext(), "mmm" + list.get(position).getGuard_id(), Toast.LENGTH_SHORT).show();
                } else {
                    listEmployees.remove("" + list.get(position).getGuard_id());
                    // Toast.makeText(holder.itemView.getContext(), "www" + list.get(position).getGuard_id(), Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(holder.itemView.getContext(), "" + listEmployees.size(), Toast.LENGTH_SHORT).show();
                staff.getid(listEmployees.size());

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
public ArrayList<String> getguard(){
    return listEmployees;
}
    public void setSelectedAll(boolean selectedAll) {
        for (DataGuardPayment payment : list) {
            payment.setCheek(selectedAll);
            if (selectedAll) {
                listEmployees.add("" + payment.getGuard_id());
                //staff.getid(listEmployees);
            }


        }
        if (!selectedAll) {
            listEmployees.clear();
            //
        }

        staff.getid(listEmployees.size());

        notifyDataSetChanged();
    }

    public void setList(ArrayList<DataGuardPayment> list) {
        this.list = list;
        notifyDataSetChanged();

    }
    public ArrayList<DataGuardPayment> getList() {
        return this.list;

    }
public void filterlist(ArrayList<DataGuardPayment> listfilter){
        list=listfilter;
        notifyDataSetChanged();
}
    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ItemrecyclershowsalltaffBinding recyclerBinding;


        public ViewHolder(ItemrecyclershowsalltaffBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding = binding;
        }
    }
}
