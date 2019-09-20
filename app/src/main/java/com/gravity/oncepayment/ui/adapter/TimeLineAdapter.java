package com.gravity.oncepayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.room.util.StringUtil;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.CircleView;
import com.gravity.oncepayment.Utilities.TextUtils;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>
{
    List<GroupedItems> items = null;

    public TimeLineAdapter(List<PaymentTransactionGroup> items)
    {
        this.items = getGrouped(items);
    }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_timeline, parent, false);
        return new TimeLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position)
    {
        GroupedItems item = items.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount()
    {
        if(items == null) return 0;
        return items.size();
    }

    public static class TimeLineViewHolder extends ViewHolder
    {
        LinearLayout ctr_items;
        TextView ctr_date;

        public TimeLineViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ctr_items = itemView.findViewById(R.id.timeline_items);
            ctr_date = itemView.findViewById(R.id.timeline_date);
        }

        public void bind(GroupedItems item, int position)
        {
            ctr_date.setText(TextUtils.toPersianNumeric(item.date));
            ctr_items.setBackgroundColor((position % 2) == 1 ? 0xffeeeeee : 0xfff2f2f2);

            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());

            for(PaymentTransactionGroup grp : item.items)
            {
                View paymentRowView = inflater.inflate(R.layout.rv_timeline_payment, ctr_items, false);

                TextView titleTv = paymentRowView.findViewById(R.id.timeline_paymentPrice);
                CircleView cv = paymentRowView.findViewById(R.id.timeline_paymentColor);

                cv.setColor(grp.getWalletColor());
                titleTv.setText(TextUtils.toPersianNumeric( "مبلغ " + 32000 + " ریال برای " + grp.getTitle()));

                ctr_items.addView(paymentRowView);
            }

        }
    }

    public List<GroupedItems> getGrouped(List<PaymentTransactionGroup> items)
    {
        Collections.sort(items, new Comparator<PaymentTransactionGroup>()
        {
            @Override
            public int compare(PaymentTransactionGroup t0, PaymentTransactionGroup t1)
            {
                return t0.getPaymentDate().compareTo(t1.getPaymentDate());
            }
        });

        String before = null;
        GroupedItems grps = null;
        List<GroupedItems> ret = new ArrayList<>();
        for(PaymentTransactionGroup item: items)
        {
            if(!item.getPaymentDate().equals(before))
            {
                before = item.getPaymentDate();

                grps = new GroupedItems(before);

                ret.add(grps);
            }
            grps.add(item);
        }

        return ret;
    }

    private static class GroupedItems
    {
        List<PaymentTransactionGroup> items;
        String date;

        public GroupedItems(String date)
        {
            this.date = date;
            items = new ArrayList<>();
        }

        public void add(PaymentTransactionGroup item)
        {
            this.items.add(item);
        }
    }
}
