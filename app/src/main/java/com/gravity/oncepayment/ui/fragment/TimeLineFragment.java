package com.gravity.oncepayment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.datetime.JalaliDate;
import com.gravity.oncepayment.Utilities.datetime.MCalendar;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.ui.adapter.TimeLineAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeLineFragment extends Fragment
{
    RecyclerView ctr_recyclerView;
    TimeLineAdapter mAdapter;

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
        List<PaymentTransactionGroup> items = new ArrayList<>();

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int days = r.nextInt(6) + 1;
        int[] colors = new int[]{0xff991C1C, 0xff1C4E99, 0xff82991C, 0xff135C0E};
        String[] names = new String[]{"قسط بانک مسکن", "پول تو جیبی تقی", "شارژ ساختمون", "قسط بانک رفاه"};

        MCalendar cal = MCalendar.getToday();


        for(int i = 0; i < 6; i++)
        {
            int cnt = r.nextInt(4) + 1;
            String date = cal.getJalali().getMonthNames()[r.nextInt(12)] + " " + (r.nextInt(30) + 1) ;

            for(int j = 0; j < cnt; j++)
            {
                int loan = r.nextInt(4);

                PaymentTransactionGroup item = new PaymentTransactionGroup();
                item.setPaymentDate(date);
                item.setPriority(j);
                item.setTitle(names[loan]);
                item.setWalletColor(colors[loan]);
                item.setWalletId(loan);

                items.add(item);
            }
        }

        ctr_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mAdapter = new TimeLineAdapter(items);
        ctr_recyclerView.setAdapter(mAdapter);
    }
}
