package com.gravity.oncepayment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gravity.oncepayment.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

public class ad_valet extends RecyclerView.Adapter<ad_valet.CustomViewHolder>{

    private Context context;

    public  ad_valet(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View root = layoutInflater.inflate(R.layout.rv_bag_item, parent, false);

        return new CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {



        holder.txt_ViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.txt_ViewOptions);
                //inflating menu from xml resource
                popup.inflate(R.menu.bag_menu_option);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }





    class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_ViewOptions, txt_BagName, txt_amount,txt_trasactionNum;
        private Context context;

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
