package com.gravity.oncepayment.ui.bottomSheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import androidx.lifecycle.ViewModelProviders;
import yuku.ambilwarna.AmbilWarnaDialog;

public class AddWalletBottomSheet extends BottomSheetDialogFragment
    implements View.OnClickListener {


    private Button btn_insert;
    private EditText Name;
    private CircleView colorView;
    public int DefaultColor = 0xFF000000;
    public boolean isAdd = true;
    private TextView txt_typeOperand;
    private int walletId = -1;
    private Wallet updateWallet;

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
             this.colorView.setColor(updateWallet.getColor());
        }
    }


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

        colorView = view.findViewById(R.id.colorPicker);
        colorView.setOnClickListener(this);

        txt_typeOperand = view.findViewById(R.id.txt_typeOperand);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_insert:

                String WalletName = Name.getText().toString();
                int color = colorView.getColor();


                if(!Name.getText().toString().isEmpty()) {
                    if (isAdd) {


                        Wallet wallet = new Wallet();
                        wallet.setColor(color);
                        wallet.setName(WalletName);

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

            case R.id.colorPicker:
                OpenColorPickerDialog(false);
                break;
        }
    }


    private void OpenColorPickerDialog(boolean AlphaSupport) {

        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {

                DefaultColor = color;

                colorView.setColor(DefaultColor);
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {

            }
        });
        ambilWarnaDialog.show();

    }

}
