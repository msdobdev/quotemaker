package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.dob.textonphoto.R;


public class RatioAdapter extends SelectableAdapter<RatioAdapter.ViewHolder> {
    private static Activity mContext;
    public static float[] rationH = {1.0f, 1.0f, 4.0f, 32.0f, 9.0f};
    public static float[] rationW = {1.0f, 1.0f, 5.0f, 27.0f, 16.0f};
    private RatioClickListener clickListener;
    public int[] imageunselected = {R.drawable.ic_orignal_ratio, R.drawable.ic_square_ratio, R.drawable.ic_rectangle_ratio, R.drawable.ic_landscape_ratio, R.drawable.ic_portrait_ratio};
    private LayoutInflater mLayoutInflater;
    String[] name = {"orignal", "1:1", "4:5", "16:9", "9:16"};

    public RatioAdapter(Activity activity, RatioClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_ratio, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.titlename.setText(this.name[i]);
        if (isSelected(i)) {
            viewHolder.icon.setImageResource(this.imageunselected[i]);
        } else {
            viewHolder.icon.setImageResource(this.imageunselected[i]);
        }
    }

    public int getItemCount() {
        return this.imageunselected.length;
    }
    public interface RatioClickListener {
        void onRatioItemClicked(int i);
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView icon;
        private RatioClickListener listener;
        TextView titlename = ((TextView) this.itemView.findViewById(R.id.tv_name));



        public boolean onLongClick(View view) {
            return false;
        }

        public ViewHolder(View view, RatioClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.icon = (ImageView) view.findViewById(R.id.iview_icon);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onRatioItemClicked(getAdapterPosition());
            }
        }
    }
}
