package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.utility.MultiColorEffectDetails;
import com.ms.dob.textonphoto.R;
//

public class MultiColorAdapter extends SelectableAdapter<MultiColorAdapter.ViewHolder> {
    private static MultiColorClickListener clickListener;
    public static String[] fontname;
    public static Activity mContext;
    private LayoutInflater mLayoutInflater;
    MultiColorEffectDetails multiColorEffectDetails = new MultiColorEffectDetails();

    public MultiColorAdapter(Activity activity, MultiColorClickListener clickListener2) {
        mContext = activity;
        clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_font, viewGroup, false), clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            this.multiColorEffectDetails.Setmulticolor_Effect(viewHolder.textView, i, false);
        } catch (OutOfMemoryError unused) {
        }
    }

    public int getItemCount() {
        return this.multiColorEffectDetails.effect_c1.length;
    }
    public interface MultiColorClickListener {
        void onItemClicked_MulticolorEffect(int i);
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MultiColorClickListener listener;
        RelativeLayout maain;
        TextView textView;



        public ViewHolder(View view, MultiColorClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.textView = (TextView) view.findViewById(R.id.textfont);
            this.maain = (RelativeLayout) view.findViewById(R.id.fondadpterid);
            ImageSizeUtility.setFountAdpterTextSize(this.maain, MultiColorAdapter.mContext);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_MulticolorEffect(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            MultiColorClickListener clickListener = this.listener;
            return false;
        }
    }
}
