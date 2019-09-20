package com.gravity.oncepayment.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gravity.oncepayment.R;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.viewModel.PatmentTransactionViewModel;

import java.util.List;

import static android.view.View.VISIBLE;

public class PaymentRecyclerViewListAdapter extends ListAdapter<Payment, PaymentRecyclerViewListAdapter.ViewHolder> {

    private static final long REVEAL_DURATION = 1000;
    private static final DiffUtil.ItemCallback<Payment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Payment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Payment oldItem, @NonNull Payment newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Payment oldItem, @NonNull Payment newItem) {
            return oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getPrice() == newItem.getPrice() &&
                    oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getWalletId() == newItem.getWalletId();
        }
    };

    private Fragment fragment;

    public PaymentRecyclerViewListAdapter(Fragment fragment) {
        super(DIFF_CALLBACK);

        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(getItem(position), fragment);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Button showButton;
        private Button editButton;
        private Button deleteButton;
        private TextView priceTextView;
        private TextView titleTextView;
        private TextView priorityTextView;
        private TextView descriptionTextView;
        private TextView numberTextView;
        private TextView remainedNumberTextView;
        private LinearLayout buttonsLinearLayout;
        private ImageView settingImageView;

        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            showButton = itemView.findViewById(R.id.btn_show);
            editButton = itemView.findViewById(R.id.btn_edit);
            deleteButton = itemView.findViewById(R.id.btn_delete);
            priceTextView = itemView.findViewById(R.id.tv_price);
            titleTextView = itemView.findViewById(R.id.tv_title);
            priorityTextView = itemView.findViewById(R.id.tv_priority);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
            numberTextView = itemView.findViewById(R.id.tv_number);
            remainedNumberTextView = itemView.findViewById(R.id.tv_remained_number);
            buttonsLinearLayout = itemView.findViewById(R.id.buttons_linear);
            settingImageView = itemView.findViewById(R.id.setting_image_view);
        }

        public void bindView(Payment payment, Fragment fragment) {

            priceTextView.setText(String.valueOf("قیمت: " + payment.getPrice()));
            titleTextView.setText(String.valueOf(payment.getTitle()));
            priorityTextView.setText(String.valueOf("اولویت: " + payment.getPriority()));
            descriptionTextView.setText(String.valueOf("توضیحات: " + payment.getDescription()));

            ViewModelProviders.of(fragment).get(PatmentTransactionViewModel.class).getAll(payment.getId())
                    .observe(fragment, new Observer<List<PaymentTransaction>>() {
                        @Override
                        public void onChanged(List<PaymentTransaction> paymentTransactions) {
                            numberTextView.setText(String.valueOf("تعداد: " + paymentTransactions.size()));

                            int counter = 0;
                            for (int i = 0; i < paymentTransactions.size(); i++) {
                                if (!paymentTransactions.get(i).isPayed())
                                    counter++;
                            }

                            remainedNumberTextView.setText(String.valueOf("تعداد باقی مانده: " + counter));
                        }
                    });

            settingImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int xCenter = buttonsLinearLayout.getWidth() / 2;
                    int yCenter = 0;
                    int radius = buttonsLinearLayout.getWidth() / 2;

                    if (buttonsLinearLayout.getVisibility() == View.INVISIBLE) {
                        show(xCenter, yCenter, radius);
                    } else {
                        hide(xCenter, yCenter, radius);
                    }
                }
            });

            showButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });
        }

        private void show(final int centerX, int centerY, double radius) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = ViewAnimationUtils.createCircularReveal(buttonsLinearLayout, centerX, centerY, 0, (float) radius);
                animator.setDuration(REVEAL_DURATION);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        buttonsLinearLayout.setVisibility(VISIBLE);
                        settingImageView.setImageResource(R.drawable.ic_clear_black);
                        settingImageView.setBackgroundResource(R.drawable.circle_white_background);
                    }
                });
                animator.start();
            } else {
                buttonsLinearLayout.setVisibility(VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                animation.setDuration(REVEAL_DURATION / 2);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        buttonsLinearLayout.setVisibility(VISIBLE);
                        settingImageView.setBackgroundResource(R.drawable.circle_white_background);
                        settingImageView.setImageResource(R.drawable.ic_clear_black);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                buttonsLinearLayout.startAnimation(animation);
            }
        }

        private void hide(int centerX, int centerY, double radius) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = ViewAnimationUtils.createCircularReveal(buttonsLinearLayout, centerX, centerY, (float) radius, 0);
                animator.setDuration(REVEAL_DURATION);
                animator.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        settingImageView.setBackgroundResource(R.drawable.circle_colored_background);
                        settingImageView.setImageResource(R.drawable.ic_settings_black);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        buttonsLinearLayout.setVisibility(View.INVISIBLE);
                    }
                });
                animator.start();
            } else {
                buttonsLinearLayout.setVisibility(VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        settingImageView.setBackgroundResource(R.drawable.circle_colored_background);
                        settingImageView.setImageResource(R.drawable.ic_settings_black);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        buttonsLinearLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                buttonsLinearLayout.startAnimation(animation);
            }
        }
    }
}
