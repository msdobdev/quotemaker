package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.utility.FolderUtility;

import com.ms.dob.textonphoto.R;

import java.io.File;
import java.util.ArrayList;

public class MyCreationAdapter extends SelectableAdapter<MyCreationAdapter.ViewHolder> {
    private  ClickListener clickListener;
    /* access modifiers changed from: private */
    public  Activity mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String[]> createdimagepath ;


    public MyCreationAdapter(Activity activity, ClickListener clickListener2, ArrayList<String[]> pathlist) {
        mContext = activity;
        clickListener = clickListener2;
        createdimagepath = pathlist;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.mycreation_adpter_layout, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String path = createdimagepath.get(i)[0];
        Glide.with(mContext).load(Uri.fromFile(new File(path)).toString()).centerCrop().placeholder((int) R.drawable.ic_loader).into(viewHolder.showimage);
        String filename = path.substring(path.lastIndexOf("/") + 1);
        viewHolder.tv_name.setText(filename);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClicked_Mycreation(path);
                }
            }
        });
        viewHolder.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext).setMessage((CharSequence) "Do want to delete image?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i1) {
                        if (i != -1) {
//                            String str = path;
                            FolderUtility.DeleteFiles(path);
                            createdimagepath.remove(i);
                            notifyItemRemoved(i);
                            notifyItemRangeChanged(i, getItemCount() - i);
                            FolderUtility.ScaneGallery(mContext, path, true);
                            if (getItemCount()==0){
                                clickListener.noCreation();
                            }
                        }
                    }
                }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FolderUtility.DeleteFiles(path);
//                HomeScreenActivity.full_add(activity);
                    }
                }).show();
            }
        });
    }

    public int getItemCount() {
        return createdimagepath!=null?createdimagepath.size():0;
    }

    public interface ClickListener {
        void onItemClicked_Mycreation(String path);
        void noCreation();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
//        RelativeLayout clickmarzine;
        ImageView deleteimage;
//        RelativeLayout imagelayout;
        TextView tv_name;
//        private ClickListener listener;
        //        ImageView shareimage;
        ImageView showimage;



        public ViewHolder(View view) {
            super(view);
//            this.listener = clickListener;
            this.showimage = (ImageView) view.findViewById(R.id.iv_creation);
//            this.shareimage = (ImageView) view.findViewById(R.id.shareimageview);
            this.deleteimage = (ImageView) view.findViewById(R.id.deleteimageview);
            this.tv_name = view.findViewById(R.id.tv_name);
//            this.imagelayout = (RelativeLayout) view.findViewById(R.id.svd);
//            view.setOnClickListener(this);
//            view.setOnLongClickListener(this);
//            ShareDeleteButtonclick();
        }

//        public void onClick(View view) {
//            if (this.listener != null) {
//                this.listener.onItemClicked_Mycreation(getAdapterPosition());
//            }
//        }
//
//        public boolean onLongClick(View view) {
////            ClickListener clickListener = this.listener;
//            return false;
//        }

/*
        private void ShareDeleteButtonclick() {
//            this.shareimage.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    int adapterPosition = ViewHolder.this.getAdapterPosition();
//                    if (adapterPosition != -1) {
//                        RateShare.Shateimage(MyApplication.getInstance().getCreatedimagepath().get(adapterPosition)[0], MyCreationAdpter.mContext);
//                    }
//                }
//            });
            this.deleteimage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
//                    Delete_MyCreation(MyCreationAdpter.mContext, ViewHolder.this.getAdapterPosition());
                    new AlertDialog.Builder(activity).setMessage((CharSequence) "Do want to delete image?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i != -1) {
                                String str = MyApplication.getInstance().getCreatedimagepath().get(i)[0];
                                FolderUtility.DeleteFiles(str);
                                MyApplication.getInstance().getCreatedimagepath().remove(MyApplication.getInstance().getCreatedimagepath().get(i));
                                ShowMyCreation.nodifieAdpter(i);
                                FolderUtility.ScaneGallery(activity, str, true);
                            }
                        }
                    }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
//                HomeScreenActivity.full_add(activity);
                        }
                    }).show();
                }
            });
        }
*/
    }


}
