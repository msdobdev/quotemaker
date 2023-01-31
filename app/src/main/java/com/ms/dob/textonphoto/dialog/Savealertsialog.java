//package com.ms.dob.textonphoto.dialog;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import androidx.appcompat.app.AlertDialog;
//
//import com.ms.dob.textonphoto.activity.MyCreationActivity;
//import com.ms.dob.textonphoto.asynctask.SaveStickerAsyncTask;
////
//
//public class Savealertsialog {
//
//
//    public void ShowSaveAlertOnBackPress(final Activity activity, final Bitmap bitmap) {
////        this.saveImage = new SaveImage();
//        new AlertDialog.Builder(activity).setMessage((CharSequence) "Do want to save ?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                new SaveStickerAsyncTask(activity, bitmap) {
//                    public void Ontick() {
//                    }
//
//                    public void onRecived(String str) {
//                        if (!str.equalsIgnoreCase("")) {
//                            activity.startActivity(new Intent(activity, MyCreationActivity.class));
//                            activity.finish();
////                            HomeScreenActivity.full_add(activity);
//                        }
//                    }
//                }.execute(new Uri[0]);
//            }
//        }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
////                if (!z) {
////                    HomeScreenActivity.full_add(activity);
////                }
//                activity.finish();
//            }
//        }).show();
//    }
//
//}
