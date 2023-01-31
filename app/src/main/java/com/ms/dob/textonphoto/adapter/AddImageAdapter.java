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
import com.ms.dob.textonphoto.R;
//
import java.util.ArrayList;

public class AddImageAdapter extends SelectableAdapter<AddImageAdapter.ViewHolder> {
    public static Activity mContext;
    static RecyclerView mrecyclerView;
    ArrayList<String> AllImageList;
    private AddImageClickListener clickListener;
    private LayoutInflater mLayoutInflater;

    public AddImageAdapter(Activity activity, ArrayList<String> arrayList, AddImageClickListener clickListener2) {
        mContext = activity;
        this.AllImageList = arrayList;
        this.clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_image, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            Glide.with(mContext).load(Uri.parse(this.AllImageList.get(i))).asBitmap().centerCrop().into(viewHolder.imgThumb);
        } catch (OutOfMemoryError unused) {
        }
    }

    public int getItemCount() {
        return this.AllImageList.size();
    }
    public interface AddImageClickListener {
        void onItemClicked_Addimage(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//        ImageView borderimageview;
//        ImageView gapimageview;
        ImageView imgThumb;
        private AddImageClickListener listener;



        public ViewHolder(View view, AddImageClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.imgThumb = (ImageView) view.findViewById(R.id.iv_thumb);
//            this.borderimageview = (ImageView) view.findViewById(R.id.border);
//            this.borderimageview.setVisibility(8);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            ImageSizeUtility.setAddimagesize(this.imgThumb, AddImageAdapter.mContext);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_Addimage(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            AddImageClickListener clickListener = this.listener;
            return false;
        }
    }
}
