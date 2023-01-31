package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ms.dob.textonphoto.R;


public class EditColorListAdapter extends SelectableAdapter<EditColorListAdapter.ViewHolder1> {
    public static String[] colorname = {"#000000", "#FFFFFF", "#3C3C3C", "#999999", "#E94029", "#FF6C42", "#FE9843", "#FBBB37", "#FCFF2C", "#F9FD84", "#FCFFC3", "#C8FE2E", "#75E213", "#00B302", "#00D480", "#02F3D2", "#00B3F7", "#037CEF", "#514CFF", "#514CFF", "#704EFC", "#945BFF", "#CD83FE", "#FF9EFD", "#FC6D98", "#E83E5B", "#BA0100"};
    private  Activity mContext;
    private EditClickListener clickListener;
    private LayoutInflater mLayoutInflater;

    public EditColorListAdapter(Activity activity, EditClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
    }

    public ViewHolder1 onCreateViewHolder(ViewGroup viewGroup, int i) {
//        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder1(this.mLayoutInflater.inflate(R.layout.list_item_color, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder1 viewHolder, int i) {

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
    public interface EditClickListener {
        void onColorItemClicked(int i);
    }
     class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private EditClickListener listener;
        RelativeLayout selectedcolorlayout;
        RelativeLayout squalcoloelayout = ((RelativeLayout) this.itemView.findViewById(R.id.colorimageview));



        public boolean onLongClick(View view) {
            return false;
        }

        public ViewHolder1(View view, EditClickListener clickListener) {
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
