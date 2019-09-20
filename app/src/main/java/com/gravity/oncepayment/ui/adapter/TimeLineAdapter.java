package com.gravity.oncepayment.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.room.util.StringUtil;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.CircleView;
import com.gravity.oncepayment.Utilities.TextUtils;
import com.gravity.oncepayment.Utilities.VertTextView;
import com.gravity.oncepayment.Utilities.VerticalTextView;
import com.gravity.oncepayment.Utilities.datetime.MCalendar;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>
{
    List<GroupedItems> items = null;
    public double firstJd;
    public double lastJd;

    public TimeLineAdapter(List<PaymentTransactionGroup> items)
    {
        addItems(items);
    }

    public void addItems(List<PaymentTransactionGroup> items)
    {
        if(this.items == null)
            this.items = new ArrayList<>();


        this.items.addAll(getGrouped(items));

        Collections.sort(this.items, new Comparator<GroupedItems>()
        {
            @Override
            public int compare(GroupedItems t0, GroupedItems t1)
            {
                if(t0.jd < t1.jd) return -1;
                if(t0.jd > t1.jd) return 1;
                return 0;
            }
        });
        if(this.items.size() > 0)
        {
            firstJd = this.items.get(0).jd;
            lastJd = this.items.get(this.items.size() - 1).jd;

            Log.d("eeeeee", "firstJd: " + firstJd + "    lastJd: " + lastJd);
        }
        notifyDataSetChanged();
    }

    public void clear()
    {
        this.items.clear();
        notifyDataSetChanged();
    }

    public int getTodayPostion()
    {
        double today = MCalendar.getToday().julianDay;
        int pos = 0;
        for(GroupedItems item: this.items)
        {
            if(item.jd >= today)
                return pos;
            pos++;
        }
        return 0;
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
        View ctr_leftBar;

        public TimeLineViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ctr_items = itemView.findViewById(R.id.timeline_items);
            ctr_date = itemView.findViewById(R.id.timeline_date);
            ctr_leftBar = itemView.findViewById(R.id.timeline_leftBar);
        }

        public void bind(GroupedItems item, int position)
        {
            ctr_date.setText(TextUtils.toPersianNumeric(item.getDate()).replace(" ", "\n"));
            itemView.setBackgroundColor((position % 2) == 1 ? 0xfff2f2f2 : 0xfff8f8f8);
            ctr_leftBar.setBackgroundColor((position % 2) == 1 ? 0x308DCADD : 0x30CFC777);
//            ctr_date.setTopDown(false);

            ctr_items.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());

            for(PaymentTransactionGroup grp : item.items)
            {
                View paymentRowView = inflater.inflate(R.layout.rv_timeline_payment, ctr_items, false);

                TextView titleTv = paymentRowView.findViewById(R.id.timeline_paymentPrice);
                CircleView cv = paymentRowView.findViewById(R.id.timeline_paymentColor);


                cv.setColor(grp.getWalletColor());
                titleTv.setText(TextUtils.toPersianNumeric( "مبلغ " + commafy(String.valueOf(grp.getPrice())) + " ریال برای " + grp.getTitle()));

                ctr_items.addView(paymentRowView);
            }

        }

        private String commafy(String inputNum)
        {
            String regex = "(\\d)(?=(\\d{3})+$)";
            String [] splittedNum = inputNum.split("\\.");
            if(splittedNum.length==2)
            {
                return splittedNum[0].replaceAll(regex, "$1,")+"."+splittedNum[1];
            }
            else
            {
                return inputNum.replaceAll(regex, "$1,");
            }
        }
    }

    public List<GroupedItems> getGrouped(List<PaymentTransactionGroup> items)
    {
        if(items == null || items.isEmpty())
            return new ArrayList<>();

        Collections.sort(items, new Comparator<PaymentTransactionGroup>()
        {
            @Override
            public int compare(PaymentTransactionGroup t0, PaymentTransactionGroup t1)
            {
                return t0.getPaymentDate().compareTo(t1.getPaymentDate());
            }
        });

        double before = -1;
        GroupedItems grps = null;
        List<GroupedItems> ret = new ArrayList<>();
        for(PaymentTransactionGroup item: items)
        {

            double jd = MCalendar.getJD(MCalendar.JalaliType, item.getPaymentDate());
            Log.d("ddddd", "jd: " + jd);
            if(jd != before)
            {
                before = jd;

                grps = new GroupedItems(jd);

                ret.add(grps);
            }
            grps.add(item);
        }

        return ret;
    }

    private static class GroupedItems
    {
        List<PaymentTransactionGroup> items;
        public double jd;

        public GroupedItems(double jd)
        {
            this.jd = jd;
            items = new ArrayList<>();
        }

        public void add(PaymentTransactionGroup item)
        {
            this.items.add(item);
        }

        public String getDate()
        {
            return new MCalendar(jd).getJalali().toString("%d\n%ms");
        }
    }
}
