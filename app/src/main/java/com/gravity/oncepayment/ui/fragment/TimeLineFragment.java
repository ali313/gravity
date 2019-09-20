package com.gravity.oncepayment.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.datetime.MCalendar;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.ui.adapter.TimeLineAdapter;
import com.gravity.oncepayment.viewModel.PaymentTransactionGroupViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeLineFragment extends Fragment
{
    RecyclerView ctr_recyclerView;
    TimeLineAdapter mAdapter;
    boolean isLoading = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ctr_recyclerView = view.findViewById(R.id.timeline_recycler);

        loadList();
        return view;
    }

    private void loadList()
    {
//        List<PaymentTransactionGroup> items = getRandomItems(MCalendar.getToday().julianDay - 60, 120, true);
        mAdapter = new TimeLineAdapter(null);
        ViewModelProviders.of(this)
                .get(PaymentTransactionGroupViewModel.class)
                .getAllUpsByDateRange("0", 1000).observe(this, new Observer<List<PaymentTransactionGroup>>()
        {
            @Override
            public void onChanged(List<PaymentTransactionGroup> paymentTransactionGroups)
            {
                mAdapter.clear();
                mAdapter.addItems(paymentTransactionGroups);
                ctr_recyclerView.setAdapter(mAdapter);

                ctr_recyclerView.scrollToPosition(mAdapter.getTodayPostion());
            }
        });

//        List<PaymentTransactionGroup> items =
        ctr_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

//        ctr_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
//        {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
//            {
////                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                if (!isLoading && hasMore())
//                {
//                    if (linearLayoutManager != null)
//                    {
//                        if(linearLayoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount() - 1)
//                        {
//                            //bottom of list
//                            loadAfter();
//                        }
//                        else if(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0)
//                        {
//                            //top of list
//                            loadBefore();
//                        }
//                    }
//                }
//            }
//        });
    }

    private boolean hasMore()
    {
        return true;
    }

    private void loadBefore()
    {
        Log.d("ccccc", "loadBefore");

        isLoading = true;
        List<PaymentTransactionGroup> items = getRandomItems(mAdapter.firstJd - 1, 10, false);
        mAdapter.addItems(items);
        isLoading = false;
    }

    private void loadAfter()
    {
        Log.d("ccccc", "loadAfter");
        isLoading = true;
        List<PaymentTransactionGroup> items = getRandomItems(mAdapter.lastJd + 1, 10, true);
        mAdapter.addItems(items);
        isLoading = false;
    }

    private List<PaymentTransactionGroup> getRandomItems(double fromJd, int count, boolean isAfter)
    {
        List<PaymentTransactionGroup> items = new ArrayList<>();

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
//        int days = r.nextInt(6) + 1;
        int[] colors = new int[]{0xff991C1C, 0xff1C4E99, 0xff82991C, 0xff135C0E};
        String[] names = new String[]{"قسط بانک مسکن", "پول تو جیبی تقی", "شارژ ساختمون", "قسط بانک رفاه"};

        for(int i = 0; i < count; i++)
        {
            int cnt = r.nextInt(4) + 1;
            double diffJd = r.nextInt(50);
            if(isAfter) fromJd += diffJd;
            else fromJd -= diffJd;

            Log.d("ccccc", "newJd: " + fromJd);

            MCalendar c = new MCalendar(fromJd);
            String date = c.getJalali().toString("%Y/%M/%D");

            for(int j = 0; j < cnt; j++)
            {
                int loan = r.nextInt(4);

                PaymentTransactionGroup item = new PaymentTransactionGroup();
                item.setPaymentDate(date);
                item.setPriority(j);
                item.setTitle(" " + fromJd);
                item.setWalletColor(colors[loan]);
                item.setWalletId(loan);

                items.add(item);
            }
        }

        return items;
    }
}
