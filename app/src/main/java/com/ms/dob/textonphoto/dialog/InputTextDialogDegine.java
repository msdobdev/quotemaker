package com.ms.dob.textonphoto.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ms.dob.textonphoto.activity.EditPhotoActivity;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.model.WriteText;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.setTextWithSpanLine;

public class InputTextDialogDegine extends Dialog implements View.OnClickListener {
    boolean IsEditText;
    public EditPhotoActivity activity;

    /* renamed from: d */
    public Dialog f53d;
    EditText et_bubble_input;
    int frameid = 0;
    MultiTouchListener.OnClicklisner onClicklisner;
    String privoisString;
    String text;
    TextView tv_action_done;

    public InputTextDialogDegine(EditPhotoActivity activity2, boolean z, String str, int i) {
        super(activity2);
        this.activity = activity2;
        this.onClicklisner = this.onClicklisner;
        this.IsEditText = z;
        this.text = str;
        this.frameid = i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.add_text_layout);
        getWindow().setLayout(-1, -1);
        this.et_bubble_input = (EditText) findViewById(R.id.et_bubble_input);
        this.tv_action_done = (TextView) findViewById(R.id.tv_action_done);
        if (this.IsEditText) {
            this.et_bubble_input.setText(this.text);
            this.privoisString = this.et_bubble_input.getText().toString();
        }
        showKeyboard(this.activity);
        this.tv_action_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = InputTextDialogDegine.this.et_bubble_input.getText().toString();
                if (InputTextDialogDegine.this.IsEditText) {
                    if (obj.equalsIgnoreCase("")) {
                        obj = InputTextDialogDegine.this.privoisString;
                    }
                    TextView textView = SetTextColorFontSize.getInstance().getTextView();
                    if (InputTextDialogDegine.this.frameid == 6) {
                        setTextWithSpanLine.setTextWithSpan(textView, -1, new SpannableString(obj), 1.5f);
                    } else if (InputTextDialogDegine.this.frameid == 9) {
                        setTextWithSpanLine.setTextWithSpan(textView, Color.parseColor("#B3ffffff"), new SpannableString(obj), 1.5f);
                    } else {
                        SetTextColorFontSize.getInstance().getTextView().setText(obj);
                    }
                } else if (!obj.equalsIgnoreCase("")) {
                    WriteText.addtext(InputTextDialogDegine.this.activity, InputTextDialogDegine.this.onClicklisner, InputTextDialogDegine.this.et_bubble_input.getText().toString());
                    activity.Set_Delete_EditText(-1);
                    InputTextDialogDegine.this.hideKeyboard(InputTextDialogDegine.this.activity);
                } else {
                    WriteText.addtext(InputTextDialogDegine.this.activity, InputTextDialogDegine.this.onClicklisner, "Write Text");
                    activity.Set_Delete_EditText(-1);
                    InputTextDialogDegine.this.hideKeyboard(InputTextDialogDegine.this.activity);
                }
                InputTextDialogDegine.this.dismiss();
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

    public static void inputdialogcall(EditPhotoActivity activity2, boolean z, int i) {
        InputTextDialogDegine inputTextDialogDegine = new InputTextDialogDegine(activity2, z, SetTextColorFontSize.getInstance().getTextView().getText().toString(), i);
        inputTextDialogDegine.getWindow().requestFeature(1);
        inputTextDialogDegine.show();
        inputTextDialogDegine.setCanceledOnTouchOutside(false);
        inputTextDialogDegine.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
}
