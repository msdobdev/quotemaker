package com.ms.dob.textonphoto.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.dob.textonphoto.activity.QuoteEditActivity;
import com.ms.dob.textonphoto.R;


public class ShowStatusInDialog extends Dialog implements View.OnClickListener {
    String Catname;
    public Activity activity;
    TextView copyttext;

    /* renamed from: d */
//    public Dialog f51d;
    String setText;
    TextView shatetext;
    TextView statustext;
    TextView usetext;

    public ShowStatusInDialog(Activity activity2, String str, String str2) {
        super(activity2);
        this.activity = activity2;
        this.setText = str;
        this.Catname = str2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quotes_detail_layout);
        this.statustext = (TextView) findViewById(R.id.tv_quotes_text);
        this.copyttext = (TextView) findViewById(R.id.btn_copytext);
        this.shatetext = (TextView) findViewById(R.id.btn_sharetext);
        this.usetext = (TextView) findViewById(R.id.btn_use);
        this.statustext.setText(this.setText);
        this.usetext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ShowStatusInDialog.this.activity, QuoteEditActivity.class);
                intent.putExtra("STATUSTEXT", ShowStatusInDialog.this.setText);
                intent.putExtra("IMAGEPATH", "" + "file:///android_asset/Background/bg_1.webp");
//                intent.putExtra("ISNOTIFACTION", false);
                ShowStatusInDialog.this.activity.startActivity(intent);
                ShowStatusInDialog.this.dismiss();
            }
        });
        this.copyttext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT < 11) {
                    ((ClipboardManager) ShowStatusInDialog.this.activity.getSystemService("clipboard")).setText(ShowStatusInDialog.this.setText);
                } else {
                    ((android.content.ClipboardManager) ShowStatusInDialog.this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label", ShowStatusInDialog.this.setText));
                }
                Toast.makeText(ShowStatusInDialog.this.activity, "Text copy", Toast.LENGTH_SHORT).show();

                ShowStatusInDialog.this.dismiss();
//                HomeScreenActivity.full_add(ShowStatusInDialog.this.activity);
            }
        });
        this.shatetext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", ShowStatusInDialog.this.setText);
                ShowStatusInDialog.this.activity.startActivity(intent);
                ShowStatusInDialog.this.dismiss();
            }
        });
    }

    public void onClick(View view) {
        view.getId();
    }

    public static void CallStatusDetailDilog(Activity activity2, String str, String str2) {
        ShowStatusInDialog showStatusInDialog = new ShowStatusInDialog(activity2, str, str2);
        showStatusInDialog.getWindow().requestFeature(1);
        showStatusInDialog.show();
        showStatusInDialog.setCanceledOnTouchOutside(false);
        showStatusInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
}
