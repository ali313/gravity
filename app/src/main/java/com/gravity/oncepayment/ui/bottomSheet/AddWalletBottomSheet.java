package com.gravity.oncepayment.ui.bottomSheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.gravity.oncepayment.model.pojos.Wallet;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AddWalletBottomSheet extends BottomSheetDialogFragment
    implements View.OnClickListener {


    private Button btn_insert;
    private EditText Name;
    private CircleView colorView;
    public int DefaultColor = 0xFF000000;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_add_wallet, null);
        dialog.setContentView(view);
        init(view);
     //   findView(view);


       // this.txt_Message.setText(this.Message);

    }

    private void init(View view){
        btn_insert = view.findViewById(R.id.btn_insert);
        Name = view.findViewById(R.id.et_WalletName);
        colorView = view.findViewById(R.id.colorPicker);
        colorView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.et_WalletName:
            String WalletName = Name.getText().toString();
            //TODO
                // insert into table
            break;

            case R.id.colorPicker:

                Log.d("mohammad", "mohammad");
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
