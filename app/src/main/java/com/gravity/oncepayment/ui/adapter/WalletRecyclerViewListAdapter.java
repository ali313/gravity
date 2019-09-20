package com.gravity.oncepayment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.CircleView;
import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.ui.bottomSheet.AddWalletBottomSheet;
import com.gravity.oncepayment.ui.fragment.WalletFragmentDirections;
import com.gravity.oncepayment.viewModel.WalletViewModel;

import java.util.Random;

public class WalletRecyclerViewListAdapter extends ListAdapter<Wallet, WalletRecyclerViewListAdapter.CustomViewHolder> {

    private static final DiffUtil.ItemCallback<Wallet> DIFF_CALLBACK = new DiffUtil.ItemCallback<Wallet>() {
        @Override
        public boolean areItemsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return oldItem.getAmount() == newItem.getAmount() &&
                    oldItem.getColor() == newItem.getColor() &&
                    oldItem.getCreatedDate().equals(newItem.getCreatedDate()) &&
                    oldItem.getName().equals(newItem.getName());
        }
    };
    private Context context;
    private Fragment fragment;

    public WalletRecyclerViewListAdapter(Fragment fragment) {
        super(DIFF_CALLBACK);

        this.fragment = fragment;
        context = fragment.getContext();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View root = layoutInflater.inflate(R.layout.rv_bag_item, parent, false);
        return new CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bindView(getItem(position));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_ViewOptions, txt_BagName, txt_amount, txt_trasactionNum;
        private CircleView view_Color;
        private View view;


        public CustomViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            findView();
        }

        private void findView() {
            txt_ViewOptions = itemView.findViewById(R.id.txt_ViewOptions);
            txt_BagName = itemView.findViewById(R.id.txt_WalletName);
            view_Color = itemView.findViewById(R.id.wallet_Color);
            txt_amount = itemView.findViewById(R.id.txt_amount);
        }

        public void bindView(final Wallet wallet) {

            if (wallet.getName().length() > 16)
                txt_BagName.setText(wallet.getName().substring(0, 14) + "...");
            else
                txt_BagName.setText(wallet.getName());
            txt_BagName.setTextColor(wallet.getColor());

            view_Color.setColor(wallet.getColor());

            txt_amount.setText(wallet.getAmount() + "");
            if (wallet.getAmount() == 0) {
                txt_amount.setTextColor(0xFFb82525);
            }

            view.setClickable(true);
            view.setFocusable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    WalletFragmentDirections.WalletsToPaymentsAction action = WalletFragmentDirections.walletsToPaymentsAction(wallet.getId());
                    Navigation.findNavController(view).navigate(action);
                }
            });

            txt_ViewOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(context, txt_ViewOptions);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.bag_menu_option);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete:
                                    ViewModelProviders.of(fragment).get(WalletViewModel.class).delete(
                                            wallet
                                    );
                                    break;
                                case R.id.edit:
                                    AddWalletBottomSheet fragment = new AddWalletBottomSheet();
                                    fragment.setUpdateState();
                                    fragment.setWalletId(wallet.getId());
                                    fragment.show(((FragmentActivity) context).getSupportFragmentManager(), "TAG");
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

        }
    }
}
