package com.teraninja.guard.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.guard.DataClient.OnclikgetPackagecompany;
import com.teraninja.guard.Model.PackageCompany;
import com.teraninja.guard.R;
import com.teraninja.guard.databinding.ItemDailogPaymentBinding;
import com.teraninja.guard.databinding.ItemPackageCompanyBinding;

import java.util.ArrayList;

public class AdapterPackageCompany extends RecyclerView.Adapter<AdapterPackageCompany.ViewHolder>{
    ArrayList<PackageCompany> list = new ArrayList<>();
    OnclikgetPackagecompany onclikgetPackagecompany;

    public AdapterPackageCompany(OnclikgetPackagecompany interFacePackageId) {
        this.onclikgetPackagecompany = interFacePackageId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemPackageCompanyBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_package_company,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.textone.setText(list.get(position).getTitle());
        holder.recyclerBinding.textD.setText(list.get(position).getDescription()+" "+list.get(position).getNo_of_days()+" "+list.get(position).getNo_of_cvs());
        holder.recyclerBinding.textpric.setText("SR"+list.get(position).getPrice());

        holder.recyclerBinding.SignUpSearhWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclikgetPackagecompany.gettypepackage(list.get(position).getId(),list.get(position).getType());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<PackageCompany> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemPackageCompanyBinding recyclerBinding;


        public ViewHolder(ItemPackageCompanyBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
