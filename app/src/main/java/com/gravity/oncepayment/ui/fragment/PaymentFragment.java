package com.gravity.oncepayment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gravity.oncepayment.R;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.ui.adapter.PaymentRecyclerViewListAdapter;
import com.gravity.oncepayment.viewModel.PaymentViewModel;

import java.util.List;

public class PaymentFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private PaymentRecyclerViewListAdapter paymentRecyclerViewListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_fragment_layout, container, false);

        floatingActionButton = view.findViewById(R.id.floating_action_button);
        recyclerView = view.findViewById(R.id.recyclerView);

        paymentRecyclerViewListAdapter = new PaymentRecyclerViewListAdapter(this);
        recyclerView.setAdapter(paymentRecyclerViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int walletId = 0;

        ViewModelProviders.of(this).get(PaymentViewModel.class).getAll(walletId)
                .observe(this, new Observer<List<Payment>>() {
                    @Override
                    public void onChanged(List<Payment> payments) {
                        paymentRecyclerViewListAdapter.submitList(payments);
                    }
                });

        return view;
    }
}
