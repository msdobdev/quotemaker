package com.ms.dob.textonphoto.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.dob.textonphoto.adapter.QuoetEditDialogAdapter;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.utility.StatusJsonFileName;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.setTextWithSpanLine;

import java.util.ArrayList;

public class EditQuoetsDialog extends Dialog implements View.OnClickListener, QuoetEditDialogAdapter.QuoetsClickListener {
    public static ArrayList<String[]> stauslist;
    public Activity activity;
    int adcont = 0;
    RelativeLayout btnlayout;
    String catname;

    /* renamed from: d */
    public Dialog f52d;
    int frameid = 0;
    Button language;
    int possition;
    QuoetEditDialogAdapter quoetEditDialogAdapter;
    RecyclerView recyclerView;

    public EditQuoetsDialog(Activity activity2, String str, int i, int i2) {
        super(activity2);
        this.activity = activity2;
        this.frameid = i2;
        this.possition = i;
        stauslist = new ArrayList<>();
        stauslist = ReadJsonFromAssect.getStatusList(this.activity, StatusJsonFileName.jsonname_hin[i]);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_choose_quotes);
        this.adcont = 0;
        getWindow().setLayout(-1, -1);
        this.recyclerView = (RecyclerView) findViewById(R.id.ry_quoteslist);
        this.language = (Button) findViewById(R.id.btn_language);
        this.btnlayout = (RelativeLayout) findViewById(R.id.btnlayout);
        this.language.setText("ENGLISH");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, (int) ((double) ((ScreenDimension.getHeight(this.activity) / 100) * 35)), 0, 0);
        this.btnlayout.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(1);
        layoutParams2.addRule(3, R.id.btnlayout);
        this.recyclerView.setLayoutParams(layoutParams2);
        this.language.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditQuoetsDialog.stauslist = new ArrayList<>();
                if (EditQuoetsDialog.this.language.getText().toString().equalsIgnoreCase("ENGLISH")) {
                    EditQuoetsDialog.stauslist = ReadJsonFromAssect.getStatusList(EditQuoetsDialog.this.activity, StatusJsonFileName.jsonname_eng[EditQuoetsDialog.this.possition]);
                    EditQuoetsDialog.this.language.setText("HINDI");
                } else {
                    EditQuoetsDialog.this.language.setText("ENGLISH");
                    EditQuoetsDialog.stauslist = ReadJsonFromAssect.getStatusList(EditQuoetsDialog.this.activity, StatusJsonFileName.jsonname_hin[EditQuoetsDialog.this.possition]);
                }
                EditQuoetsDialog.this.StatusAdpterCall();
            }
        });
        StatusAdpterCall();
    }

    public void onItemClicked_Status(int i) {
        TextView textView = SetTextColorFontSize.getInstance().getTextView();
        String str = stauslist.get(i)[0];
        if (this.frameid == 6) {
            setTextWithSpanLine.setTextWithSpan(textView, -1, new SpannableString(str), 1.5f);
        } else if (this.frameid == 9) {
            setTextWithSpanLine.setTextWithSpan(textView, Color.parseColor("#B3ffffff"), new SpannableString(str), 1.5f);
        } else {
            SetTextColorFontSize.getInstance().getTextView().setText(str);
        }
        dismiss();
    }

    public void onClick(View view) {
        view.getId();
    }

    public static void QuoetsDialog(Activity activity2, String str, int i, int i2) {
        EditQuoetsDialog editQuoetsDialog = new EditQuoetsDialog(activity2, str, i, i2);
        editQuoetsDialog.getWindow().requestFeature(1);
        editQuoetsDialog.show();
        editQuoetsDialog.setCanceledOnTouchOutside(false);
        editQuoetsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    /* access modifiers changed from: private */
    public void StatusAdpterCall() {
        this.quoetEditDialogAdapter = new QuoetEditDialogAdapter(this.activity, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
        this.recyclerView.setAdapter(this.quoetEditDialogAdapter);
    }
}
