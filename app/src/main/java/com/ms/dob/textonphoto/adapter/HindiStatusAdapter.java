package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;
import java.util.Random;

public class HindiStatusAdapter extends SelectableAdapter<HindiStatusAdapter.ViewHolder> {
    private static HindiClickListener clickListener;
    private static Activity mContext;
    private LayoutInflater mLayoutInflater;
//    int provous = 0;
    ArrayList<Integer> textViewArrayList;

    public HindiStatusAdapter(Activity activity, HindiClickListener clickListener2) {
        mContext = activity;
        clickListener = clickListener2;
        this.textViewArrayList = new ArrayList<>();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_quotes, viewGroup, false), clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(MyApplication.getInstance().getHindiStatusArryLst().get(i)[0]);
        viewHolder.colorline.setColorFilter(randomcolor());
        TextView textView = viewHolder.numbertext;
        textView.setText("" + (i + 1));
    }

    public int getItemCount() {
        return MyApplication.getInstance().getHindiStatusArryLst().size();
    }
    public interface HindiClickListener {
        void onItemClicked_HindiStatus(int i);
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView colorline;
        private HindiClickListener listener;
//        RelativeLayout listlayout;
        TextView numbertext;
        TextView textView;



        public ViewHolder(View view, HindiClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.textView = (TextView) view.findViewById(R.id.tv_status);
//            this.listlayout = (RelativeLayout) view.findViewById(R.id.listlayout);
            this.numbertext = (TextView) view.findViewById(R.id.numbertext);
            this.colorline = (ImageView) view.findViewById(R.id.iv_selection_indicater);
            this.colorline.setVisibility(4);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_HindiStatus(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            HindiClickListener clickListener = this.listener;
            return false;
        }
    }

    public static int randomcolor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
