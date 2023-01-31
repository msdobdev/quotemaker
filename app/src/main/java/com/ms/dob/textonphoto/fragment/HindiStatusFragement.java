package com.ms.dob.textonphoto.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.adapter.HindiStatusAdapter;
import com.ms.dob.textonphoto.dialog.ShowStatusInDialog;
import com.ms.dob.textonphoto.utility.StatusJsonFileName;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;

public class HindiStatusFragement extends Fragment implements HindiStatusAdapter.HindiClickListener {
    int adcount = 0;
    int possition;
    RecyclerView recyclerView;
    View rootView;
    HindiStatusAdapter statusDetailsAdpter;

    public HindiStatusFragement(int i) {
        this.possition = i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.statusdetails_recyleview, viewGroup, false);
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.statusrecyleview);
        this.adcount = 0;
        MyApplication.getInstance().setHindiStatusArryLst(new ArrayList<>());
        MyApplication.getInstance().setHindiStatusArryLst(ReadJsonFromAssect.getStatusList(getActivity(), StatusJsonFileName.jsonname_hin[this.possition]));
//        ArrayListUtility.HindiStatusArryLst = new ArrayList<>();
//        ArrayListUtility.HindiStatusArryLst = ReadJsonFromAssect.getStatusList(getActivity(), StatusJsonFileName.jsonname_hin[this.possition]);
        StatusAdpterCall();
        return this.rootView;
    }

    private void StatusAdpterCall() {
        this.statusDetailsAdpter = new HindiStatusAdapter(getActivity(), this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.statusDetailsAdpter);
    }

    public void onItemClicked_HindiStatus(int i) {
//        this.adcount++;
//        if (this.adcount == 5) {
//            this.adcount = 0;
//            HomeScreenActivity.full_add(getActivity());
//        }
        ShowStatusInDialog.CallStatusDetailDilog(getActivity(), MyApplication.getInstance().getHindiStatusArryLst().get(i)[0], "HINDI");
    }
}
