package com.gravity.oncepayment.ui.bottomSheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gravity.oncepayment.MainActivity;
import com.gravity.oncepayment.R;
import com.gravity.oncepayment.Utilities.CircleView;
import com.gravity.oncepayment.Utilities.TextUtils;
import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.viewModel.WalletViewModel;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import yuku.ambilwarna.AmbilWarnaDialog;

public class AddWalletBottomSheet extends BottomSheetDialogFragment
    implements View.OnClickListener {


    private Button btn_insert;
    private EditText Name;
    private CircleView colorPicker1,colorPicker2,colorPicker3,colorPicker4,colorPicker5;
    public int DefaultColor = 0xFF000000;
    public boolean isAdd = true;
    private TextView txt_typeOperand;
    private int walletId = -1;
    private Wallet updateWallet;
    private int selectedColor;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);


        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_add_wallet, null);
        dialog.setContentView(view);
        init(view);

        if(!isAdd){

            txt_typeOperand.setText("ویرایش");

             updateWallet = ViewModelProviders.of(this).get(WalletViewModel.class).getWallet(this.walletId);
             this.Name.setText(updateWallet.getName());
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

            float factor = getContext().getResources().getDisplayMetrics().density;
            selectedColor = updateWallet.getColor();
             switch (updateWallet.getColor()){
                 case Color.YELLOW:

                     this.colorPicker1.getLayoutParams().height = (int)(45 * factor);
                     this.colorPicker1.getLayoutParams().width = (int)(45 * factor);


                     
                     break;
                 case Color.RED:
                     this.colorPicker2.getLayoutParams().height = (int)(45 * factor);
                     this.colorPicker2.getLayoutParams().width = (int)(45 * factor);
                     
                     break;
                 case Color.BLACK:
                     this.colorPicker4.getLayoutParams().height = (int)(45 * factor);
                     this.colorPicker4.getLayoutParams().width = (int)(45 * factor);
                     
                     break;
                 case Color.BLUE:
                     this.colorPicker3.getLayoutParams().height = (int)(45 * factor);
                     this.colorPicker3.getLayoutParams().width = (int)(45 * factor);
                     
                     break;
                 case Color.MAGENTA:
                     this.colorPicker5.getLayoutParams().height = (int)(45 * factor);
                     this.colorPicker5.getLayoutParams().width = (int)(45 * factor);
                     
                     break;
             }

             //this.colorView.setColor(updateWallet.getColor());
        }
    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NO_FRAME,0);
//    }

    public void setUpdateState(){
        this.isAdd = false;
    }

    public void setWalletId(int walletId){
        this.walletId = walletId;
    }

    public void setAddState(){
        this.isAdd = true;
    }

    private void init(View view){
        btn_insert = view.findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);

        Name = view.findViewById(R.id.et_WalletName);


        colorPicker1 = view.findViewById(R.id.colorPicker1);
        colorPicker1.setColor(Color.YELLOW);
        colorPicker1.setOnClickListener(this);

        colorPicker2 = view.findViewById(R.id.colorPicker2);
        colorPicker2.setColor(Color.RED);
        colorPicker2.setOnClickListener(this);

        colorPicker3 = view.findViewById(R.id.colorPicker3);
        colorPicker3.setColor(Color.BLUE);
        colorPicker3.setOnClickListener(this);

        colorPicker4 = view.findViewById(R.id.colorPicker4);
        colorPicker4.setColor(Color.BLACK);

        colorPicker4.setOnClickListener(this);

        colorPicker5 = view.findViewById(R.id.colorPicker5);
        colorPicker5.setColor(Color.MAGENTA);
        colorPicker5.setOnClickListener(this);

        txt_typeOperand = view.findViewById(R.id.txt_typeOperand);
    }

    @Override
    public void onClick(View view) {
        float factor = getContext().getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        switch (view.getId()) {

            case R.id.btn_insert:

                String WalletName = Name.getText().toString();
                int color = selectedColor;
                String createdDate = "";


                if(!Name.getText().toString().isEmpty()) {
                    if (isAdd) {


                        Wallet wallet = new Wallet();
                        wallet.setColor(color);
                        wallet.setName(WalletName);
                        wallet.setCreatedDate(createdDate);

                        ViewModelProviders.of(this).get(WalletViewModel.class).insert(wallet);

                    } else {
                        updateWallet.setName(WalletName);
                        updateWallet.setColor(color);
                        ViewModelProviders.of(this).get(WalletViewModel.class).update(updateWallet);

                    }
                    this.dismiss();
                }
                else{
                    Toast.makeText(getContext(), "لطفا نامی برای کیف پول جدید انتخاب کنید!", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.colorPicker1:
                selectedColor = Color.YELLOW;
                resetColorsViewSize();

                layoutParams.width = (int)(factor * 45) ;
                layoutParams.height = (int)(factor * 45) ;
                colorPicker1.setLayoutParams(layoutParams);
                break;
            case R.id.colorPicker2:
                selectedColor = Color.RED;
                resetColorsViewSize();
                layoutParams.width = (int)(factor * 45) ;
                layoutParams.height = (int)(factor * 45) ;
                colorPicker2.setLayoutParams(layoutParams);
                break;
            case R.id.colorPicker3:
                resetColorsViewSize();
                selectedColor = Color.BLUE;
                layoutParams.width = (int)(factor * 45) ;
                layoutParams.height = (int)(factor * 45) ;

                colorPicker3.setLayoutParams(layoutParams);
                break;
            case R.id.colorPicker4:
                resetColorsViewSize();
                selectedColor = Color.BLACK;
                layoutParams.width = (int)(factor * 45) ;
                layoutParams.height = (int)(factor * 45) ;

                colorPicker4.setLayoutParams(layoutParams);
                break;
            case R.id.colorPicker5:
                resetColorsViewSize();
                selectedColor = Color.MAGENTA;
                layoutParams.width = (int)(factor * 45) ;
                layoutParams.height = (int)(factor * 45) ;

                colorPicker5.setLayoutParams(layoutParams);
                break;
        }
    }

    public void resetColorsViewSize(){
        float factor = getContext().getResources().getDisplayMetrics().density;
        this.colorPicker1.getLayoutParams().height = (int)(40 * factor);
        this.colorPicker1.getLayoutParams().width = (int)(40 * factor);

        this.colorPicker2.getLayoutParams().height = (int)(40 * factor);
        this.colorPicker2.getLayoutParams().width = (int)(40 * factor);

        this.colorPicker3.getLayoutParams().height = (int)(40 * factor);
        this.colorPicker3.getLayoutParams().width = (int)(40 * factor);

        this.colorPicker4.getLayoutParams().height = (int)(40 * factor);
        this.colorPicker4.getLayoutParams().width = (int)(40 * factor);

        this.colorPicker5.getLayoutParams().height = (int)(40 * factor);
        this.colorPicker5.getLayoutParams().width = (int)(40 * factor);



    }


//    private void OpenColorPickerDialog(boolean AlphaSupport) {
//
//        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
//            @Override
//            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {
//
//                DefaultColor = color;
//
//                colorView.setColor(DefaultColor);
//            }
//
//            @Override
//            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {
//
//            }
//        });
//        ambilWarnaDialog.show();
//
//    }

}
