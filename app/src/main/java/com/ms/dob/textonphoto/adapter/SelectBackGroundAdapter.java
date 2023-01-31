package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.utility.GradientColorUtility;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.R;
//
import java.util.ArrayList;

public class SelectBackGroundAdapter extends SelectableAdapter<SelectBackGroundAdapter.ViewHolder> {
    private static final String TAG = "SelectBackGroundAdpter";
//    public static int listViewItemType;
    private static Activity mContext;
    static RecyclerView mrecyclerView;
    private SelectClickListener clickListener;
    int colorpo = 0;
    ArrayList<String> imagepath;
    private LayoutInflater mLayoutInflater;

    public SelectBackGroundAdapter(Activity activity, SelectClickListener clickListener2, ArrayList<String> arrayList) {
        mContext = activity;
        this.clickListener = clickListener2;
        this.imagepath = arrayList;
//        Log.e(TAG, "SelectBackGroundAdpter: imagepath++++++"+imagepath.size() );
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_background, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        listViewItemType = i;
        if (i >= this.imagepath.size()) {
            this.colorpo = i - this.imagepath.size();
            if (i % 2 != 0) {
                viewHolder.uperlayout.setVisibility(0);
                viewHolder.bottomlayout.setVisibility(8);
                GradientColorUtility.changeGradientColor(mContext, this.colorpo, viewHolder.bgim1, ((int) ImageSizeUtility.wright) / 2);
                return;
            }
            viewHolder.uperlayout.setVisibility(8);
            viewHolder.bottomlayout.setVisibility(0);
            GradientColorUtility.changeGradientColor(mContext, this.colorpo, viewHolder.bgim2, ((int) ImageSizeUtility.wright) / 2);
        } else if (i % 2 != 0) {
            viewHolder.uperlayout.setVisibility(0);
            viewHolder.bottomlayout.setVisibility(8);
            Glide.with(mContext).load(Uri.parse(this.imagepath.get(i))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(viewHolder.bgim1);
        } else {
            viewHolder.uperlayout.setVisibility(8);
            viewHolder.bottomlayout.setVisibility(0);
            Glide.with(mContext).load(Uri.parse(this.imagepath.get(i))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(viewHolder.bgim2);
        }
    }

    public int getItemCount() {
        return this.imagepath.size() + GradientColorUtility.f239c1.length;
    }
    public interface SelectClickListener {
        void onItemClicked_BG(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView bgim1;
        ImageView bgim2;
        RelativeLayout bottomlayout;
        private SelectClickListener listener;
        RelativeLayout mainlayout;
        RelativeLayout uperlayout;



        public ViewHolder(View view, SelectClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.mainlayout = (RelativeLayout) view.findViewById(R.id.layoutmain);
            this.uperlayout = (RelativeLayout) view.findViewById(R.id.laybg1);
            this.bottomlayout = (RelativeLayout) view.findViewById(R.id.laybg2);
            this.bgim1 = (ImageView) view.findViewById(R.id.imbg1);
            this.bgim2 = (ImageView) view.findViewById(R.id.imbg2);
            imagesize(this);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_BG(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            SelectClickListener clickListener = this.listener;
            return false;
        }
    }

    public static void imagesize(ViewHolder viewHolder) {
        float width = (((float) ScreenDimension.getWeight(mContext)) / 100.0f) * 95.0f;
        float f = width / 2.0f;
        int i = (int) f;
        viewHolder.uperlayout.getLayoutParams().width = i;
        int i2 = (int) (f + (f / 6.0f));
        viewHolder.uperlayout.getLayoutParams().height = i2;
        viewHolder.bgim1.getLayoutParams().height = i;
        viewHolder.bottomlayout.getLayoutParams().width = i;
        viewHolder.bottomlayout.getLayoutParams().height = i2;
        viewHolder.bgim2.getLayoutParams().height = i;
    }
}
