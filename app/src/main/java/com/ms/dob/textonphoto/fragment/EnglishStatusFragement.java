package com.ms.dob.textonphoto.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.adapter.EnglishStatusAdapter;
import com.ms.dob.textonphoto.dialog.ShowStatusInDialog;
import com.ms.dob.textonphoto.utility.StatusJsonFileName;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;

public class EnglishStatusFragement extends Fragment implements EnglishStatusAdapter.EnglishClickListener{
    int adcount = 0;
    EnglishStatusAdapter englishStatusAdapter;
    int possition;
    RecyclerView recyclerView;
    View rootView;

    public EnglishStatusFragement(int i) {
        this.possition = i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.statusdetails_recyleview, viewGroup, false);
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.statusrecyleview);
        this.adcount = 0;
        MyApplication.getInstance().setEngStatusArryLst(new ArrayList<>());
        MyApplication.getInstance().setEngStatusArryLst(ReadJsonFromAssect.getStatusList(getActivity(), StatusJsonFileName.jsonname_eng[this.possition])); ;
        StatusAdpterCall();
        this.recyclerView.getLayoutManager().setAutoMeasureEnabled(true);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
        return this.rootView;
    }

    private void StatusAdpterCall() {
        this.englishStatusAdapter = new EnglishStatusAdapter(getActivity(), this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.englishStatusAdapter);
    }

    public void onItemClicked_EnglishStatus(int i) {
//        this.adcount++;
//        if (this.adcount == 5) {
//            this.adcount = 0;
//            HomeScreenActivity.full_add(getActivity());
//        }
        ShowStatusInDialog.CallStatusDetailDilog(getActivity(), MyApplication.getInstance().getEngStatusArryLst().get(i)[0], "ENGLISH");
    }
}
