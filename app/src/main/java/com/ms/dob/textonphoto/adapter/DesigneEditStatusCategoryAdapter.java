package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.R;

public class DesigneEditStatusCategoryAdapter extends SelectableAdapter<DesigneEditStatusCategoryAdapter.ViewHolder> {
    public static String[] catname = {"Love", "Romantic", "Flirty", "Sad", "Breakup", "Hate", "Missing You", "Sorry", "Quotes", "Motivation", "FrindShip", "Good Morning", "Good Night", "Happy BirthDay", "Valentine's Day", "Republic Day", "Good Luck", "Happy", "Attitude", "WhatsApp", "Facebook", "All Time Fav"};
    private static DesignEditClickListener clickListener;
    /* access modifiers changed from: private */
    public static Activity mContext;
    private LayoutInflater mLayoutInflater;

    public DesigneEditStatusCategoryAdapter(Activity activity, DesignEditClickListener clickListener2) {
        mContext = activity;
        clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_font, viewGroup, false), clickListener);


    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(catname[i]);
    }

    public int getItemCount() {
        return catname.length;
    }
    public interface DesignEditClickListener {
        void onItemClicked_StatusCategory(int i);
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
         DesignEditClickListener listener;
        RelativeLayout maain;
        TextView textView;



        public ViewHolder(View view, DesignEditClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.textView = (TextView) view.findViewById(R.id.textfont);
            this.textView.setGravity(3);
            this.maain = (RelativeLayout) view.findViewById(R.id.fondadpterid);
            ImageSizeUtility.setFountAdpterTextSize(this.maain, DesigneEditStatusCategoryAdapter.mContext);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_StatusCategory(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            DesignEditClickListener clickListener = this.listener;
            return false;
        }
    }
}
