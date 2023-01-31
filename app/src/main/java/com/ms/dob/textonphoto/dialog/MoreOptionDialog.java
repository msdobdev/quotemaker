//package com.ms.dob.textonphoto.dialog;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.ClipData;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.ClipboardManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.ms.dob.textonphoto.activity.EditPhotoActivity;
//import com.ms.dob.textonphoto.activity.MyCreationActivity;
//import com.ms.dob.textonphoto.activity.QuotesListActivity;
//import com.ms.dob.textonphoto.utility.FolderUtility;
//import com.ms.dob.textonphoto.utility.SaveImage;
//import com.ms.dob.textonphoto.asynctask.SaveStickerAsyncTask;
//import com.ms.dob.textonphoto.R;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class MoreOptionDialog extends Dialog {
//    String Copytext;
//    boolean IsAuther;
//    public Activity activity;
//    RelativeLayout copyquoteslayout;
//    RelativeLayout editlayout;
//    int possition;
//    SaveImage saveImage = new SaveImage();
//    RelativeLayout savelayout;
//    RelativeLayout sharelayout;
//    View viewbitmap;
//
//    public MoreOptionDialog(Activity activity2, int i, View view, boolean z, String str) {
//        super(activity2);
//        this.activity = activity2;
//        this.viewbitmap = view;
//        this.possition = i;
//        this.IsAuther = z;
//        this.Copytext = str;
//    }
//
//    /* access modifiers changed from: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.moreoptiondialog);
//        this.editlayout = (RelativeLayout) findViewById(R.id.ly_edit);
//        this.savelayout = (RelativeLayout) findViewById(R.id.ly_save);
//        this.sharelayout = (RelativeLayout) findViewById(R.id.ly_share);
//        this.copyquoteslayout = (RelativeLayout) findViewById(R.id.ly_copytext);
//        if (this.IsAuther) {
//            this.editlayout.setVisibility(8);
//            this.copyquoteslayout.setVisibility(0);
//        } else {
//            this.editlayout.setVisibility(0);
//            this.copyquoteslayout.setVisibility(8);
//        }
//        this.editlayout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(MoreOptionDialog.this.activity, EditPhotoActivity.class);
//                intent.putExtra("FRAMEID", "" + QuotesListActivity.poss[MoreOptionDialog.this.possition]);
//                MoreOptionDialog.this.activity.startActivity(intent);
//                MoreOptionDialog.this.dismiss();
//            }
//        });
//        this.copyquoteslayout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                if (Build.VERSION.SDK_INT < 11) {
//                    ((ClipboardManager) MoreOptionDialog.this.activity.getSystemService("clipboard")).setText(MoreOptionDialog.this.Copytext);
//                } else {
//                    ((android.content.ClipboardManager) MoreOptionDialog.this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label", MoreOptionDialog.this.Copytext));
//                }
//                Toast.makeText(MoreOptionDialog.this.activity, "Quotes Copy", Toast.LENGTH_SHORT).show();
//
//                MoreOptionDialog.this.dismiss();
//            }
//        });
//        this.savelayout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                new SaveStickerAsyncTask(MoreOptionDialog.this.activity, MoreOptionDialog.this.saveImage.takescreen_List(MoreOptionDialog.this.activity, MoreOptionDialog.this.viewbitmap)) {
//                    public void Ontick() {
//                    }
//
//                    public void onRecived(String str) {
//                        if (!str.equalsIgnoreCase("")) {
//                            MoreOptionDialog.this.activity.startActivity(new Intent(MoreOptionDialog.this.activity, MyCreationActivity.class));
//                            MoreOptionDialog.this.activity.finish();
////                            HomeScreenActivity.full_add(MoreOptionDialog.this.activity);
//                        }
//                    }
//                }.execute(new Uri[0]);
//                MoreOptionDialog.this.dismiss();
//            }
//        });
//        this.sharelayout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Bitmap takescreen_List = MoreOptionDialog.this.saveImage.takescreen_List(MoreOptionDialog.this.activity, MoreOptionDialog.this.viewbitmap);
//                storeImage(takescreen_List, "" + System.currentTimeMillis(), MoreOptionDialog.this.activity, "");
//                MoreOptionDialog.this.dismiss();
//            }
//        });
//    }
//
//    public static void ratepoup(Activity activity2, int i, View view, boolean z, String str) {
//        MoreOptionDialog moreOptionDialog = new MoreOptionDialog(activity2, i, view, z, str);
//        moreOptionDialog.getWindow().requestFeature(1);
//        moreOptionDialog.show();
//        moreOptionDialog.setCanceledOnTouchOutside(false);
//        moreOptionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//    }
//
//    public static String storeImage(Bitmap bitmap, String str, Activity activity, String str2) {
//        File ShareFolder = FolderUtility.ShareFolder();
//        if (!ShareFolder.exists()) {
//            ShareFolder.mkdirs();
//        }
//        try {
//            String str3 = ShareFolder.toString() + File.separator + str + ".png";
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
//            bufferedOutputStream.flush();
//            bufferedOutputStream.close();
//            new Intent();
//            Intent intent = new Intent("android.intent.action.SEND");
//            intent.putExtra("android.intent.extra.TEXT", "" + str2);
//            intent.setType("image/png");
//            intent.putExtra("android.intent.extra.STREAM", Uri.parse(str3));
//            intent.setFlags(268435456);
//            activity.startActivity(intent);
//            return str3;
//        } catch (FileNotFoundException e) {
//            Log.w("TAG", "Error saving image file: " + e.getMessage());
//            return "";
//        } catch (IOException e2) {
//            Log.w("TAG", "Error saving image file: " + e2.getMessage());
//            return "";
//        }
//    }
//}
