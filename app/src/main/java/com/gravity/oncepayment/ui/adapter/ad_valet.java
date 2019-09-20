package com.gravity.oncepayment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.CircleView;
import com.gravity.oncepayment.Utilities.MyBounceInterpolator;
import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.ui.bottomSheet.AddWalletBottomSheet;
import com.gravity.oncepayment.viewModel.WalletViewModel;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ad_valet extends RecyclerView.Adapter<ad_valet.CustomViewHolder>{

    private Context context;
    private List<Wallet> wallets;
    Random r ;

    public  ad_valet(Context context, List<Wallet> wallets){
        this.context = context;
        this.wallets = wallets;
        r = new Random();
        r.setSeed(System.currentTimeMillis());
    }

    public ad_valet(Context context){
        this.context = context;
        r = new Random();
        r.setSeed(System.currentTimeMillis());
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View root = layoutInflater.inflate(R.layout.rv_bag_item, parent, false);

        return new CustomViewHolder(root);
    }

    public void setWallets(List<Wallet> wallets) {
        if(wallets != null) {
            this.wallets = wallets;
            notifyDataSetChanged();
        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        int lastPosition=0;

        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        final Wallet wallet = this.wallets.get(position);


        if (wallet.getName().length() > 16)
            holder.txt_BagName.setText(wallet.getName().substring(0, 14) + "...");
        else
            holder.txt_BagName.setText(wallet.getName());
        holder.txt_BagName.setTextColor(wallet.getColor());

        holder.view_Color.setColor(wallet.getColor());



        holder.txt_amount.setText(wallet.getAmount() + "");
        if(wallet.getAmount() == 0){
            holder.txt_amount.setTextColor(0xFFb82525);
        }

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
                            case R.id.delete:
                                ViewModelProviders.of((FragmentActivity) context).get(WalletViewModel.class).delete(
                                        wallet
                                );
                                break;
                            case R.id.edit:
                                AddWalletBottomSheet fragment = new AddWalletBottomSheet();
                                fragment.setUpdateState();
                                fragment.setWalletId(wallet.getId());
                                fragment.show(((FragmentActivity)context).getSupportFragmentManager(), "TAG");
                                break;
                            case R.id.increaseAmount:
                                //TODO
                                // افزایش اعتبار
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });


        holder.itemView.setScaleX(0.80f);
        holder.itemView.setScaleY(0.80f);
        holder.itemView.setAlpha(0);

        holder.itemView.animate()
                .setInterpolator(new MyBounceInterpolator(0.15, 15))
                .setDuration(800)
                .scaleX(1).scaleY(1)
                .alpha(1)
                .setStartDelay(r.nextInt(300));


    }


    @Override
    public int getItemCount() {
        if(this.wallets == null)
            return 0;
        return this.wallets.size();
    }





    class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_ViewOptions, txt_BagName, txt_amount,txt_trasactionNum;
        public CircleView view_Color;
        private Context context;

        public CustomViewHolder(View itemView) {
            super(itemView);

            findView();
        }
        private void findView(){
            txt_ViewOptions = itemView.findViewById(R.id.txt_ViewOptions);
            txt_BagName = itemView.findViewById(R.id.txt_WalletName);
            view_Color = itemView.findViewById(R.id.wallet_Color);
            txt_amount = itemView.findViewById(R.id.txt_amount);
//            txt_trasactionNum = itemView.findViewById(R.id.txt_trasactionNum);
        }
    }

}
