package com.ms.dob.textonphoto.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.model.WriteText;
import com.ms.dob.textonphoto.activity.QuoteEditActivity;
import com.ms.dob.textonphoto.adapter.StatusDilogAdapter;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.utility.StatusJsonFileName;
import com.ms.dob.textonphoto.R;
//
import java.util.ArrayList;

public class StatusDialog extends Dialog implements View.OnClickListener, StatusDilogAdapter.StatusClickListener {
    public static ArrayList<String[]> stauslist;
    public QuoteEditActivity activity;
    int adcont = 0;
    RelativeLayout btnlayout;

    Button language;
    MultiTouchListener.OnClicklisner onClicklisner;
    int possition;
    RecyclerView recyclerView;
    StatusDilogAdapter statusDilogAdapter;

    public StatusDialog(QuoteEditActivity activity2, String str, int i, MultiTouchListener.OnClicklisner onClicklisner2) {
        super(activity2);
        this.activity = activity2;
        this.possition = i;
        stauslist = new ArrayList<>();
        stauslist = ReadJsonFromAssect.getStatusList(this.activity, StatusJsonFileName.jsonname_hin[i]);
        this.onClicklisner = onClicklisner2;
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
        this.language.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatusDialog.stauslist = new ArrayList<>();
                if (StatusDialog.this.language.getText().toString().equalsIgnoreCase("ENGLISH")) {
                    StatusDialog.stauslist = ReadJsonFromAssect.getStatusList(StatusDialog.this.activity, StatusJsonFileName.jsonname_eng[StatusDialog.this.possition]);
                    StatusDialog.this.language.setText("HINDI");
                } else {
                    StatusDialog.this.language.setText("ENGLISH");
                    StatusDialog.stauslist = ReadJsonFromAssect.getStatusList(StatusDialog.this.activity, StatusJsonFileName.jsonname_hin[StatusDialog.this.possition]);
                }
                StatusDialog.this.StatusAdpterCall();
            }
        });
        StatusAdpterCall();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, (int) ((double) ((ScreenDimension.getHeight(this.activity) / 100) * 35)), 0, 0);
        this.btnlayout.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(1);
        layoutParams2.addRule(3, R.id.btnlayout);
        this.recyclerView.setLayoutParams(layoutParams2);
    }

    public void onItemClicked_Status(int i) {
//        HomeScreenActivity.full_add(this.activity);
        if (WriteText.weitetextview == null) {
            WriteText.addtext(this.activity, this.onClicklisner, stauslist.get(i)[0]);
            activity.Set_Delete_EditText(-1);
        } else {
            WriteText.weitetextview.setText(stauslist.get(i)[0]);
            activity.Set_Delete_EditText(-1);

//            EditingLayoutId.Set_Delete_EditText(-1);
        }
        dismiss();
    }

    public void onClick(View view) {
        view.getId();
    }

    public static void inputdialogcall(QuoteEditActivity activity2, String str, int i, MultiTouchListener.OnClicklisner onClicklisner2) {
        StatusDialog statusDialog = new StatusDialog(activity2, str, i, onClicklisner2);
        statusDialog.getWindow().requestFeature(1);
        statusDialog.show();
        statusDialog.setCanceledOnTouchOutside(false);
        statusDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    /* access modifiers changed from: private */
    public void StatusAdpterCall() {
        this.statusDilogAdapter = new StatusDilogAdapter(this.activity, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
        this.recyclerView.setAdapter(this.statusDilogAdapter);
    }
}
