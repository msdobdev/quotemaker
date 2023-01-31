package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.ms.dob.textonphoto.R;
//

public class ColorListAdapter extends SelectableAdapter<ColorListAdapter.ViewHolder> {
    public static String[] colorname = {"#000000", "#FFFFFF", "#3C3C3C", "#999999", "#E94029", "#FF6C42", "#FE9843", "#FBBB37", "#FCFF2C", "#F9FD84", "#FCFFC3", "#C8FE2E", "#75E213", "#00B302", "#00D480", "#02F3D2", "#00B3F7", "#037CEF", "#514CFF", "#514CFF", "#704EFC", "#945BFF", "#CD83FE", "#FF9EFD", "#FC6D98", "#E83E5B", "#BA0100"};
    private static Activity mContext;
    private ColorClickListener clickListener;
    private LayoutInflater mLayoutInflater;

    public ColorListAdapter(Activity activity, ColorClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_color, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (isSelected(i)) {
            ((GradientDrawable) viewHolder.selectedcolorlayout.getBackground()).setColor(Color.parseColor(colorname[i]));
            viewHolder.selectedcolorlayout.setVisibility(0);
            return;
        }
        viewHolder.selectedcolorlayout.setVisibility(4);
        viewHolder.squalcoloelayout.setBackgroundColor(Color.parseColor(colorname[i]));
    }

    public int getItemCount() {
        return colorname.length;
    }
    public interface ColorClickListener {
        void onColorItemClicked(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
         ColorClickListener listener;
        RelativeLayout selectedcolorlayout;
        RelativeLayout squalcoloelayout = ((RelativeLayout) this.itemView.findViewById(R.id.colorimageview));



        public boolean onLongClick(View view) {
            return false;
        }

        public ViewHolder(View view, ColorClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.selectedcolorlayout = (RelativeLayout) view.findViewById(R.id.rv_selectionview);
            this.selectedcolorlayout.setVisibility(4);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onColorItemClicked(getAdapterPosition());
            }
        }
    }
}
