package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.net.Uri;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.utility.GradientColorUtility;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
//

public class BackgroundAdapter extends SelectableAdapter<BackgroundAdapter.ViewHolder> {
    public  Activity mContext;
//    static RecyclerView mrecyclerView;
    private BackgroundClickListener clickListener;
    private LayoutInflater mLayoutInflater;

    public BackgroundAdapter(Activity activity, BackgroundClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_image, viewGroup, false), this.clickListener,mContext);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i == 0) {
            try {
                Glide.with(mContext).load(Integer.valueOf(R.drawable.ic_gallery)).asBitmap().centerCrop().into(viewHolder.imgThumb);
            } catch (OutOfMemoryError unused) {
            }
        } else if (i == 1) {
            Glide.with(mContext).load(Integer.valueOf(R.drawable.ic_colored_background)).asBitmap().centerCrop().into(viewHolder.imgThumb);
        } else {
            int size = MyApplication.getInstance().getBackgroundarrylist().size() + 1;
            int i2 = i - size;
            if (i < size) {
                Glide.with(mContext).load(Uri.parse(MyApplication.getInstance().getBackgroundarrylist().get(i - 2))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(viewHolder.imgThumb);
                return;
            }
            double weight = (double) ScreenDimension.getWeight(mContext);
            Double.isNaN(weight);
            GradientColorUtility.changeGradientColor(mContext, i2, viewHolder.imgThumb, (int) (weight / 4.5d));
        }
    }

    public int getItemCount() {
        return MyApplication.getInstance().getBackgroundarrylist().size() + 1 + GradientColorUtility.f239c1.length;
    }
    public interface BackgroundClickListener {
        void onItemClicked_AddBG(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//        ImageView borderimageview;
//        ImageView gapimageview;
        ImageView imgThumb;
        private BackgroundClickListener listener;



        public ViewHolder(View view, BackgroundClickListener clickListener,Activity mContext) {
            super(view);
            this.listener = clickListener;
            this.imgThumb = (ImageView) view.findViewById(R.id.iv_thumb);
//            this.borderimageview = (ImageView) view.findViewById(R.id.border);
//            this.borderimageview.setVisibility(8);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            ImageSizeUtility.setAddimagesize(this.imgThumb, mContext);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_AddBG(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            BackgroundClickListener clickListener = this.listener;
            return false;
        }
    }
}
