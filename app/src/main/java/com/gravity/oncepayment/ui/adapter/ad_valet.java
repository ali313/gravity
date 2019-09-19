package com.gravity.oncepayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gravity.oncepayment.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ad_valet extends RecyclerView.Adapter<ad_valet.CustomViewHolder>{


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View root = layoutInflater.inflate(R.layout.rv_bag_item, parent, false);

        return new CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }





    class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_ViewOptions, txt_BagName, txt_amount,txt_trasactionNum;
        public CustomViewHolder(View itemView) {
            super(itemView);
            findView();
        }
        private void findView(){
            txt_ViewOptions = itemView.findViewById(R.id.txt_ViewOptions);
            txt_BagName = itemView.findViewById(R.id.txt_BagName);
            txt_amount = itemView.findViewById(R.id.txt_amount);
            txt_trasactionNum = itemView.findViewById(R.id.txt_trasactionNum);
        }
    }

}
