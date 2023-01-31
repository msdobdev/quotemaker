package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.R;
//

public class FontAdapter extends SelectableAdapter<FontAdapter.ViewHolder> {
    private static FontClickListener clickListener;
    public static String[] fontname = {"f28.ttf", "f29.ttf", "f30.ttf", "f31.ttf", "f32.otf", "f1.ttf", "f2.TTF", "f3.otf", "f4.otf", "f5.ttf", "f6.ttf", "f7.ttf", "f8.ttf", "f9.ttf", "f10.ttf", "f11.TTF", "f12.ttf", "f13.ttf", "f14.TTF", "f15.ttf", "f16.ttf", "f17.ttf", "f18.ttf", "f19.ttf", "f20.ttf", "f21.ttf", "f22.ttf", "f23.ttf", "f25.ttf", "f26.otf", "f27.TTF"};
    /* access modifiers changed from: private */
    public static Activity mContext;
    private LayoutInflater mLayoutInflater;

    public FontAdapter(Activity activity, FontClickListener clickListener2) {
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
            AssetManager assets = mContext.getAssets();
            viewHolder.textView.setTypeface(Typeface.createFromAsset(assets, "font/" + fontname[i]));
        } catch (OutOfMemoryError unused) {
        }
    }

    public int getItemCount() {
        return fontname.length;
    }
    public interface FontClickListener {
        void onItemClicked_Font(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
         FontClickListener listener;
        RelativeLayout maain;
        TextView textView;



        public ViewHolder(View view, FontClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.textView = (TextView) view.findViewById(R.id.textfont);
            this.maain = (RelativeLayout) view.findViewById(R.id.fondadpterid);
            ImageSizeUtility.setFountAdpterTextSize(this.maain, FontAdapter.mContext);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_Font(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            FontClickListener clickListener = this.listener;
            return false;
        }
    }
}
