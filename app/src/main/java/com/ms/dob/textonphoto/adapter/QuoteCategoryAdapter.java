package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ms.dob.textonphoto.model.CategoryData;
import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

public class QuoteCategoryAdapter extends SelectableAdapter<QuoteCategoryAdapter.ViewHolder> {
    private static final String TAG = "StatusCategoryAdpter";
    public static String[] colorcode = {"#ffc9bb", "#f6b1bb", "#e092c2", "#cd7bc9", "#9f54cf", "#7d50c7", "#8f6dff", "#5868e2", "#5892e2", "#72b5ee", "#93e4ff", "#ffc9bb", "#f6b1bb", "#e092c2", "#cd7bc9", "#9f54cf", "#7d50c7", "#8f6dff", "#5868e2", "#5892e2", "#72b5ee", "#93e4ff"};
    public static int listViewItemType;
    /* access modifiers changed from: private */
    public static Activity mContext;
    static RecyclerView mrecyclerView;
    private QuotecatClickListener clickListener;
    private LayoutInflater mLayoutInflater;

    public QuoteCategoryAdapter(Activity activity, QuotecatClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_quotes_cat, viewGroup, false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CategoryData categoryData = MyApplication.getInstance().getCategoryDataArrayList().get(i);
        viewHolder.titlename.setText(categoryData.getCategoryName());
        Glide.with(mContext).load(Integer.valueOf(getCatIcon(categoryData.getCategotyImageName()))).centerCrop().placeholder((int) R.drawable.ic_loader).into(viewHolder.icon);
//        listViewItemType = i;
    }

    public int getItemCount() {
//        Log.e(TAG, "getItemCount: ++++++"+MyApplication.getInstance().getCategoryDataArrayList().size() );
        return MyApplication.getInstance().getCategoryDataArrayList().size();
    }
    public interface QuotecatClickListener {
        void onItemClicked_Category(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView icon;
        private QuotecatClickListener listener;
        RelativeLayout relativeLayout;
        TextView titlename;



        public ViewHolder(View view, QuotecatClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.icon = (ImageView) view.findViewById(R.id.iview_icon);
            this.titlename = (TextView) view.findViewById(R.id.tv_name);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.mainlayout);
            ImageSizeUtility.setCatergoryimageSize(QuoteCategoryAdapter.mContext, this.relativeLayout, this.icon, this.titlename);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_Category(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            QuotecatClickListener clickListener = this.listener;
            return false;
        }
    }

    private int getCatIcon(String str) {
        if (str.equalsIgnoreCase("loveicon")) {
            return R.drawable.ic_love;
        }
        if (str.equalsIgnoreCase("romantivicon")) {
            return R.drawable.ic_romantic;
        }
        if (str.equalsIgnoreCase("flirticon")) {
            return R.drawable.ic_flirt;
        }
        if (str.equalsIgnoreCase("sadicon")) {
            return R.drawable.ic_sad;
        }
        if (str.equalsIgnoreCase("breakupicon")) {
            return R.drawable.ic_breackup;
        }
        if (str.equalsIgnoreCase("hateicon")) {
            return R.drawable.ic_hate;
        }
        if (str.equalsIgnoreCase("missongyouicon")) {
            return R.drawable.ic_miss_you;
        }
        if (str.equalsIgnoreCase("sorryicon")) {
            return R.drawable.ic_sorry;
        }
        if (str.equalsIgnoreCase("quetsicon")) {
            return R.drawable.ic_quotes;
        }
        if (str.equalsIgnoreCase("motivationalicon")) {
            return R.drawable.ic_motivate;
        }
        if (str.equalsIgnoreCase("friendshipicon")) {
            return R.drawable.ic_friendship;
        }
        if (str.equalsIgnoreCase("goodmrorningicon")) {
            return R.drawable.ic_goodmorning;
        }
        if (str.equalsIgnoreCase("goodniticon")) {
            return R.drawable.ic_goodnight;
        }
        if (str.equalsIgnoreCase("happybirthdayicon")) {
            return R.drawable.ic_happy_birthday;
        }
        if (str.equalsIgnoreCase("valendayicon")) {
            return R.drawable.ic_valentine;
        }
        if (str.equalsIgnoreCase("republiicicon")) {
            return R.drawable.ic_republic;
        }
        if (str.equalsIgnoreCase("gudluckicon")) {
            return R.drawable.ic_good_luck;
        }
        if (str.equalsIgnoreCase("happyicon")) {
            return R.drawable.ic_happy;
        }
        if (str.equalsIgnoreCase("attidude")) {
            return R.drawable.ic_attitude;
        }
        if (str.equalsIgnoreCase("whatsapp")) {
            return R.drawable.ic_q_whatsapp;
        }
        if (str.equalsIgnoreCase("facebook")) {
            return R.drawable.ic_q_facebook;
        }
        return str.equalsIgnoreCase("alltimefav") ? R.drawable.ic_favorite : R.mipmap.ic_launcher;
    }

}
