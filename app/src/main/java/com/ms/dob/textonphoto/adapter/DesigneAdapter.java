package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ms.dob.textonphoto.activity.QuotesListActivity;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.viewholder.DegineEightViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineFiveViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineFourViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineNineViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineOneViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineSevenViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineSixViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineTenViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineThreeViewHolder;
import com.ms.dob.textonphoto.viewholder.DegineTwoViewHolder;

import java.util.ArrayList;

public class DesigneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {
    private static Activity mContext;
    ArrayList<QuotesFrameDetails> arrayList;

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public DesigneAdapter(Activity activity, ArrayList<QuotesFrameDetails> arrayList2) {
        mContext = activity;
        this.arrayList = arrayList2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new DegineOneViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_1, viewGroup, false), mContext, "");
            case 1:
                return new DegineTwoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_2, viewGroup, false), mContext, "");
            case 2:
                return new DegineThreeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_3, viewGroup, false), mContext, "");
            case 3:
                return new DegineFourViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_4, viewGroup, false), mContext, "");
            case 4:
                return new DegineFiveViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_5, viewGroup, false), mContext, "");
            case 5:
                return new DegineSixViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_6, viewGroup, false), mContext, "");
            case 6:
                return new DegineSevenViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_7, viewGroup, false), mContext, "");
            case 7:
                return new DegineEightViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_8, viewGroup, false), mContext, "");
            case 8:
                return new DegineNineViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_9, viewGroup, false), mContext, "");
            case 9:
                return new DegineTenViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_10, viewGroup, false), mContext, "");
            default:
                return null;
        }
    }

    public int getItemViewType(int i) {
        switch (QuotesListActivity.poss[i]) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            default:
                return -1;
        }
    }

    public int getItemCount() {
        return this.arrayList.size();
    }
}
