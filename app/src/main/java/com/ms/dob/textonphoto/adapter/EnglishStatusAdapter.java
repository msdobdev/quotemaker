package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;

public class EnglishStatusAdapter extends SelectableAdapter<EnglishStatusAdapter.ViewHolder> {
    private static EnglishClickListener clickListener;
    private static Activity mContext;
    private LayoutInflater mLayoutInflater;
    int provous = 0;
    ArrayList<Integer> textViewArrayList;

    public EnglishStatusAdapter(Activity activity, EnglishClickListener clickListener2) {
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
        viewHolder.textView.setText(MyApplication.getInstance().getEngStatusArryLst().get(i)[0]);
        viewHolder.colorline.setColorFilter(HindiStatusAdapter.randomcolor());
        TextView textView = viewHolder.numbertext;
        textView.setText("" + (i + 1));
    }

    public int getItemCount() {
        return MyApplication.getInstance().getEngStatusArryLst().size();
    }
    public interface EnglishClickListener {
        void onItemClicked_EnglishStatus(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView colorline;
         EnglishClickListener listener;
//        RelativeLayout listlayout;
        TextView numbertext;
        TextView textView;



        public ViewHolder(View view, EnglishClickListener clickListener) {
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
                this.listener.onItemClicked_EnglishStatus(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            EnglishClickListener clickListener = this.listener;
            return false;
        }
    }
}
