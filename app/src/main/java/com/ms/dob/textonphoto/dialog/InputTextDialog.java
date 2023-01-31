package com.ms.dob.textonphoto.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ms.dob.textonphoto.model.WriteText;
import com.ms.dob.textonphoto.activity.QuoteEditActivity;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.R;

public class InputTextDialog extends Dialog implements View.OnClickListener {
    boolean IsEditText;
    public QuoteEditActivity activity;

    /* renamed from: d */
//    public Dialog f233d;
    EditText et_bubble_input;
    MultiTouchListener.OnClicklisner onClicklisner;
    String privoisString;
    TextView tv_action_done;

    public InputTextDialog(QuoteEditActivity activity2, MultiTouchListener.OnClicklisner onClicklisner2, boolean z) {
        super(activity2);
        this.activity = activity2;
        this.onClicklisner = onClicklisner2;
        this.IsEditText = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.add_text_layout);
        getWindow().setLayout(-1, -1);
        this.et_bubble_input = (EditText) findViewById(R.id.et_bubble_input);
        this.tv_action_done = (TextView) findViewById(R.id.tv_action_done);
        if (this.IsEditText) {
            this.et_bubble_input.setText(WriteText.GetText(WriteText.weitetextview));
            this.privoisString = this.et_bubble_input.getText().toString();
        }
        showKeyboard(this.activity);
        this.tv_action_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = InputTextDialog.this.et_bubble_input.getText().toString();
                if (InputTextDialog.this.IsEditText) {
                    if (obj.equalsIgnoreCase("")) {
                        obj = InputTextDialog.this.privoisString;
                    }
                    WriteText.weitetextview.setText(obj);
                    activity.Set_Delete_EditText(-1);
                } else if (!obj.equalsIgnoreCase("")) {
                    WriteText.addtext(InputTextDialog.this.activity, InputTextDialog.this.onClicklisner, InputTextDialog.this.et_bubble_input.getText().toString());
                    activity.Set_Delete_EditText(-1);

                    InputTextDialog.this.hideKeyboard(InputTextDialog.this.activity);
                } else {
                    WriteText.addtext(InputTextDialog.this.activity, InputTextDialog.this.onClicklisner, "Write Text");
                    activity.Set_Delete_EditText(-1);

                    InputTextDialog.this.hideKeyboard(InputTextDialog.this.activity);
                }
                InputTextDialog.this.dismiss();
            }
        });
    }

    public void onClick(View view) {
        view.getId();
    }

    public void showKeyboard(Activity activity2) {
        if (activity2 != null) {
            getWindow().setSoftInputMode(5);
        }
    }

    public void hideKeyboard(Activity activity2) {
        if (activity2 != null) {
            activity2.getWindow().setSoftInputMode(3);
        }
    }

    public static void inputdialogcall(MultiTouchListener.OnClicklisner onClicklisner2, QuoteEditActivity activity2, boolean z) {
        InputTextDialog inputTextDialog = new InputTextDialog(activity2, onClicklisner2, z);
        inputTextDialog.getWindow().requestFeature(1);
        inputTextDialog.show();
        inputTextDialog.setCanceledOnTouchOutside(false);
        inputTextDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
}
