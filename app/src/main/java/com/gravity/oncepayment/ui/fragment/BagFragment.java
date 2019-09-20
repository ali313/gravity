package com.gravity.oncepayment.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gravity.oncepayment.R;
import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.ui.adapter.ad_valet;
import com.gravity.oncepayment.ui.bottomSheet.AddWalletBottomSheet;
import com.gravity.oncepayment.viewModel.WalletViewModel;

import java.util.ArrayList;
import java.util.List;

public class BagFragment extends Fragment
implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ad_valet adapter;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bag, container, false);
        init(view);
        bindRecyclerView();
        return view;
    }


    private void init(View view) {

        recyclerView = view.findViewById(R.id.rv);
        floatingActionButton = view.findViewById(R.id.fab_newWallet);
        floatingActionButton.setOnClickListener(this);

    }

    public void bindRecyclerView() {
  //      Wallet wallet = new Wallet();
//        List<Wallet> myWallet = new ArrayList<>();
//
//        myWallet.add(wallet);
//        myWallet.add(wallet);
//        myWallet.add(wallet);
//        myWallet.add(wallet);
//        myWallet.add(wallet);
//        myWallet.add(wallet);
//
//        adapter = new ad_valet(getContext(), myWallet);
        adapter = new ad_valet(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ViewModelProviders.of(this).get(WalletViewModel.class).getAll().observe(this, new Observer<List<Wallet>>() {
            @Override
            public void onChanged(List<Wallet> wallets) {
                if(wallets != null) {

                    adapter.setWallets(wallets);

                }
            }
        });


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.fab_newWallet:

                AddWalletBottomSheet fragment = new AddWalletBottomSheet();
                fragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TAG");
                break;
        }
    }
}
