package com.ms.dob.textonphoto.utility;

import android.content.res.AssetManager;

import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.adapter.QuoteCategoryAdapter;
import com.ms.dob.textonphoto.model.CategoryData;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ReadJsonFromAssect {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004f A[Catch:{ JSONException -> 0x0087 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<CategoryData> getcategoryListFromAssect(android.app.Activity r5) {
        java.lang.StringBuffer stringBuffer = new java.lang.StringBuffer();
        android.content.res.AssetManager assetManager = r5.getAssets() ;
        InputStream inputStream = null;     // Catch:{ IOException -> 0x002c, all -> 0x002a }
        try {
            inputStream = assetManager.open("categorylist.txt");
// Catch:{ IOException -> 0x002c, all -> 0x002a }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader (inputStream);
            java.io.BufferedReader br = new java.io.BufferedReader(r3) ;    // Catch:{ IOException -> 0x002c, all -> 0x002a }
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                stringBuffer.append(line);
            }
            br.close();
            java.lang.String str = stringBuffer.toString();
            org.json.JSONObject jsonObject = new org.json.JSONObject(str) ;    // Catch:{ JSONException -> 0x008b }
//            r1.<init>(r7)     // Catch:{ JSONException -> 0x008b }
//                    java.lang.String r7 = "StausArray"
            org.json.JSONArray jsonArray = jsonObject.getJSONArray("CatogeyListJson") ;
            int r0=0;
            int r2 = jsonArray.length() ;    // Catch:{ JSONException -> 0x0087 }
            while (r0 < r2) {
                CategoryData categoryData = new CategoryData();     // Catch:{ JSONException -> 0x0087 }
                org.json.JSONObject jsonObject1 = jsonArray.getJSONObject(r0);     // Catch:{ JSONException -> 0x0087 }
//                java.lang.String r4 = "CatogeryName"
//                java.lang.String r4 = jsonObject1.getString("CatogeryName")     // Catch:{ JSONException -> 0x0087 }
                categoryData.setCategoryName(jsonObject1.getString("CatogeryName"));     // Catch:{ JSONException -> 0x0087 }
//                java.lang.String r4 = "NumberOfStatus"
//                java.lang.String r4 = jsonObject1.getString("NumberOfStatus")     // Catch:{ JSONException -> 0x0087 }
                categoryData.setNumberOfStatus(jsonObject1.getString("NumberOfStatus")) ;    // Catch:{ JSONException -> 0x0087 }
//                java.lang.String r4 = "CategoryIconName"
//                java.lang.String r4 = jsonObject1.getString("CategoryIconName")     // Catch:{ JSONException -> 0x0087 }
                categoryData.setCategotyImageName(jsonObject1.getString("CategoryIconName")) ;    // Catch:{ JSONException -> 0x0087 }
//                java.lang.String r4 = "Status"
//                java.lang.String r3 = jsonObject1.getString("Status") ;    // Catch:{ JSONException -> 0x0087 }
                categoryData.setStatus(jsonObject1.getString("Status"));     // Catch:{ JSONException -> 0x0087 }
//                java.util.ArrayList<com.ms.dob.textonphoto.model.CategoryData> r3 = com.ms.dob.textonphoto.utility.ArrayListUtility.categoryDataArrayList     // Catch:{ JSONException -> 0x0087 }
                MyApplication.getInstance().getCategoryDataArrayList().add(categoryData) ;    // Catch:{ JSONException -> 0x0087 }
                 r0 = r0 + 1;
            }
            return  MyApplication.getInstance().getCategoryDataArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return MyApplication.getInstance().getCategoryDataArrayList();
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            java.lang.String r4 = "categorylist.txt"
            java.io.InputStream r5 = r5.open(r4)     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            r3.<init>(r5)     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002c, all -> 0x002a }
        L_0x001a:
            java.lang.String r5 = r2.readLine()     // Catch:{ IOException -> 0x0028 }
            if (r5 == 0) goto L_0x0024
            r0.append(r5)     // Catch:{ IOException -> 0x0028 }
            goto L_0x001a
        L_0x0024:
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0028:
            r5 = move-exception
            goto L_0x002e
        L_0x002a:
            r5 = move-exception
            goto L_0x008e
        L_0x002c:
            r5 = move-exception
            r2 = r1
        L_0x002e:
            r5.printStackTrace()     // Catch:{ all -> 0x008c }
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0039:
            java.lang.String r5 = r0.toString()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0087 }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x0087 }
            java.lang.String r5 = "CatogeyListJson"
            org.json.JSONArray r5 = r0.getJSONArray(r5)     // Catch:{ JSONException -> 0x0087 }
            r0 = 0
        L_0x0049:
            int r2 = r5.length()     // Catch:{ JSONException -> 0x0087 }
            if (r0 >= r2) goto L_0x0084
            com.ms.dob.textonphoto.model.CategoryData r2 = new com.ms.dob.textonphoto.model.CategoryData     // Catch:{ JSONException -> 0x0087 }
            r2.<init>()     // Catch:{ JSONException -> 0x0087 }
            org.json.JSONObject r3 = r5.getJSONObject(r0)     // Catch:{ JSONException -> 0x0087 }
            java.lang.String r4 = "CatogeryName"
            java.lang.String r4 = r3.getString(r4)     // Catch:{ JSONException -> 0x0087 }
            r2.setCategoryName(r4)     // Catch:{ JSONException -> 0x0087 }
            java.lang.String r4 = "NumberOfStatus"
            java.lang.String r4 = r3.getString(r4)     // Catch:{ JSONException -> 0x0087 }
            r2.setNumberOfStatus(r4)     // Catch:{ JSONException -> 0x0087 }
            java.lang.String r4 = "CategoryIconName"
            java.lang.String r4 = r3.getString(r4)     // Catch:{ JSONException -> 0x0087 }
            r2.setCategotyImageName(r4)     // Catch:{ JSONException -> 0x0087 }
            java.lang.String r4 = "Status"
            java.lang.String r3 = r3.getString(r4)     // Catch:{ JSONException -> 0x0087 }
            r2.setStatus(r3)     // Catch:{ JSONException -> 0x0087 }
            java.util.ArrayList<com.ms.dob.textonphoto.model.CategoryData> r3 = com.ms.dob.textonphoto.utility.ArrayListUtility.categoryDataArrayList     // Catch:{ JSONException -> 0x0087 }
            r3.add(r2)     // Catch:{ JSONException -> 0x0087 }
            int r0 = r0 + 1
            goto L_0x0049
        L_0x0084:
            java.util.ArrayList<com.ms.dob.textonphoto.model.CategoryData> r5 = com.ms.dob.textonphoto.utility.ArrayListUtility.categoryDataArrayList
            return r5
        L_0x0087:
            r5 = move-exception
            r5.printStackTrace()
            return r1
        L_0x008c:
            r5 = move-exception
            r1 = r2
        L_0x008e:
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0096:
            throw r5
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.ms.dob.textonphoto.utility.ReadJsonFromAssect.getcategoryListFromAssect(android.app.Activity):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058 A[Catch:{ JSONException -> 0x008b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<String[]> getStatusList(android.app.Activity r7, String r8) {
        java.util.ArrayList r0 = new java.util.ArrayList();

        AssetManager astmanager = r7.getAssets();
        java.lang.StringBuffer r1 = new java.lang.StringBuffer();
        try {

            InputStream stream = astmanager.open(r8);
            java.io.InputStreamReader r4 = new java.io.InputStreamReader(stream);
            java.io.BufferedReader br = new java.io.BufferedReader(r4);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                r1.append(line);
            }
            br.close();
            java.lang.String str = r1.toString();
           int r88 = -1;
            org.json.JSONObject jsonObject = new org.json.JSONObject(str) ;    // Catch:{ JSONException -> 0x008b }
//            r1.<init>(r7)     // Catch:{ JSONException -> 0x008b }
//                    java.lang.String r7 = "StausArray"
            org.json.JSONArray jsonArray = jsonObject.getJSONArray("StausArray") ;    // Catch:{ JSONException -> 0x008b }
//            r1 = 0
            r88 = 0;
//            r2 = -1
            int r3 = jsonArray.length();
            // Catch:{ JSONException -> 0x008b }
            int r2=0;

            while (r88 < r3){
                if ((QuoteCategoryAdapter.colorcode.length - 1) <= -1) {
                    r2 = 0;
//                r3 = 2

                }else {

                    r2 = r2 + 1;
                }
                java.lang.String[] strarr = new java.lang.String[2] ;    // Catch:{ JSONException -> 0x008b }
                org.json.JSONObject r5 = jsonArray.getJSONObject(r88) ;    // Catch:{ JSONException -> 0x008b }
//                java.lang.String r6 = "Status"
//                java.lang.String r5 = r5.getString("Status")     // Catch:{ JSONException -> 0x008b }
                strarr[0] = r5.getString("Status");     // Catch:{ JSONException -> 0x008b }
                java.lang.StringBuilder strbu = new java.lang.StringBuilder () ;   // Catch:{ JSONException -> 0x008b }
//                r5.<init>()     // Catch:{ JSONException -> 0x008b }
//                java.lang.String r6 = ""
                strbu.append("")  ;   // Catch:{ JSONException -> 0x008b }
                strbu.append(r2) ;    // Catch:{ JSONException -> 0x008b }
//                java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x008b }
                strarr[1] = strbu.toString() ;    // Catch:{ JSONException -> 0x008b }
                r0.add(strarr)  ;   // Catch:{ JSONException -> 0x008b }
                r88 = r88 + 1;
            }
            return r0;
//            if (r88 >= r3) {return r0;
//            }
//            java.lang.String[] r3 = com.ms.dob.textonphoto.adapter.StatusCategoryAdpter.colorcode     // Catch:{ JSONException -> 0x008b }
//            int r3 = com.ms.dob.textonphoto.adapter.StatusCategoryAdpter.colorcode.length ;    // Catch:{ JSONException -> 0x008b }
//            r4 = 1
//            int r3 = com.ms.dob.textonphoto.adapter.StatusCategoryAdpter.colorcode.length - 1;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r0;

        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0033 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0033 }
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch:{ IOException -> 0x0033 }
            java.io.InputStream r7 = r7.open(r8)     // Catch:{ IOException -> 0x0033 }
            r4.<init>(r7)     // Catch:{ IOException -> 0x0033 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0033 }
        L_0x001d:
            java.lang.String r7 = r3.readLine()     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            if (r7 == 0) goto L_0x0027
            r1.append(r7)     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            goto L_0x001d
        L_0x0027:
            r3.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x002b:
            r7 = move-exception
            r2 = r3
            goto L_0x0090
        L_0x002e:
            r7 = move-exception
            r2 = r3
            goto L_0x0034
        L_0x0031:
            r7 = move-exception
            goto L_0x0090
        L_0x0033:
            r7 = move-exception
        L_0x0034:
            r7.printStackTrace()     // Catch:{ all -> 0x0031 }
            r2.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r7 = move-exception
            r7.printStackTrace()
        L_0x003f:
            java.lang.String r7 = r1.toString()
            r8 = -1
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x008b }
            r1.<init>(r7)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r7 = "StausArray"
            org.json.JSONArray r7 = r1.getJSONArray(r7)     // Catch:{ JSONException -> 0x008b }
            r1 = 0
            r8 = 0
            r2 = -1
        L_0x0052:
            int r3 = r7.length()     // Catch:{ JSONException -> 0x008b }
            if (r8 >= r3) goto L_0x008f
            java.lang.String[] r3 = com.ms.dob.textonphoto.adapter.StatusCategoryAdpter.colorcode     // Catch:{ JSONException -> 0x008b }
            int r3 = r3.length     // Catch:{ JSONException -> 0x008b }
            r4 = 1
            int r3 = r3 - r4
            if (r3 <= r2) goto L_0x0062
            int r2 = r2 + 1
            goto L_0x0063
        L_0x0062:
            r2 = 0
        L_0x0063:
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ JSONException -> 0x008b }
            org.json.JSONObject r5 = r7.getJSONObject(r8)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r6 = "Status"
            java.lang.String r5 = r5.getString(r6)     // Catch:{ JSONException -> 0x008b }
            r3[r1] = r5     // Catch:{ JSONException -> 0x008b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x008b }
            r5.<init>()     // Catch:{ JSONException -> 0x008b }
            java.lang.String r6 = ""
            r5.append(r6)     // Catch:{ JSONException -> 0x008b }
            r5.append(r2)     // Catch:{ JSONException -> 0x008b }
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x008b }
            r3[r4] = r5     // Catch:{ JSONException -> 0x008b }
            r0.add(r3)     // Catch:{ JSONException -> 0x008b }
            int r8 = r8 + 1
            goto L_0x0052
        L_0x008b:
            r7 = move-exception
            r7.printStackTrace()
        L_0x008f:
            return r0
        L_0x0090:
            r2.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0098
        L_0x0094:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0098:
            throw r7
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.ms.dob.textonphoto.utility.ReadJsonFromAssect.getStatusList(android.app.Activity, java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0063 A[Catch:{ JSONException -> 0x00a6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<QuotesFrameDetails> GetQuotesListFromAssect(android.app.Activity r5) {
        MyApplication.getInstance().setQuotesframdetails(new ArrayList<>());
        if (MyApplication.getInstance().getAllgalleryimagepath().size()!=0){
            java.util.ArrayList<java.lang.String> r0 = MyApplication.getInstance().getAllgalleryimagepath();
            java.util.Collections.shuffle(r0);
        }
        java.lang.StringBuffer stringBuffer = new java.lang.StringBuffer();
        android.content.res.AssetManager astmanager = r5.getAssets() ;
        InputStream ipstream = null;
        try {
            ipstream = astmanager.open("quotesdetail.txt");
            java.io.InputStreamReader r3 = new java.io.InputStreamReader(ipstream);
            java.io.BufferedReader br = new java.io.BufferedReader(r3) ;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                stringBuffer.append(line);
            }
            br.close();
            org.json.JSONObject jsonObject = new org.json.JSONObject(stringBuffer.toString()) ;    // Catch:{ JSONException -> 0x008b }
//            r1.<init>(r7)     // Catch:{ JSONException -> 0x008b }
//                    java.lang.String r7 = "StausArray"
            org.json.JSONArray jsonArray = jsonObject.getJSONArray("QuotesDetails") ;
            int r0 = 0;
            int r2 = jsonArray.length();
            while (r0 < r2) {
                QuotesFrameDetails quotesFrameDetails = new QuotesFrameDetails() ;    // Catch:{ JSONException -> 0x00a6 }
//                quotesFrameDetails.<init>()     // Catch:{ JSONException -> 0x00a6 }
                org.json.JSONObject jsonObject1 = jsonArray.getJSONObject(r0);     // Catch:{ JSONException -> 0x00a6 }
                java.lang.String[] r4 = RandomQuoetsForFirstscreen.RandomizeQuotes()  ;   // Catch:{ JSONException -> 0x00a6 }
//                r4 = r4[r0]     // Catch:{ JSONException -> 0x00a6 }
                quotesFrameDetails.setQuotes(r4[r0]) ;    // Catch:{ JSONException -> 0x00a6 }
//                java.lang.String r4 = "QuotesFrameID"
//                java.lang.String r4 =      // Catch:{ JSONException -> 0x00a6 }
                quotesFrameDetails.setQuotesFrameID(jsonObject1.getString("QuotesFrameID"));     // Catch:{ JSONException -> 0x00a6 }
//                java.lang.String r4 = "FontName"
//                java.lang.String r4 =      // Catch:{ JSONException -> 0x00a6 }
                quotesFrameDetails.setFontName(jsonObject1.getString("FontName"));     // Catch:{ JSONException -> 0x00a6 }
//                java.lang.String r4 =
//                java.lang.String r3 =      // Catch:{ JSONException -> 0x00a6 }
                quotesFrameDetails.setTextColor(jsonObject1.getString("TextColor"));     // Catch:{ JSONException -> 0x00a6 }
//                java.util.ArrayList<java.lang.String> r3 =  ;    // Catch:{ JSONException -> 0x00a6 }
//                java.lang.Object r3 =  ;    // Catch:{ JSONException -> 0x00a6 }
                java.lang.String s = (java.lang.String)MyApplication.getInstance().getAllgalleryimagepath().get(r0);     // Catch:{ JSONException -> 0x00a6 }
                quotesFrameDetails.setImagePath(s)  ;   // Catch:{ JSONException -> 0x00a6 }
//                java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r3 =      // Catch:{ JSONException -> 0x00a6 }
                MyApplication.getInstance().getQuotesframdetails().add(quotesFrameDetails)   ;  // Catch:{ JSONException -> 0x00a6 }
                 r0 = r0 + 1;
            }
            return  MyApplication.getInstance().getQuotesframdetails();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  MyApplication.getInstance().getQuotesframdetails();


        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.ms.dob.textonphoto.utility.ArrayListUtility.quotesframdetails = r0
            java.util.ArrayList<java.lang.String> r0 = com.ms.dob.textonphoto.utility.ArrayListUtility.Allgalleryimagepath
            int r0 = r0.size()
            if (r0 == 0) goto L_0x0014
            java.util.ArrayList<java.lang.String> r0 = com.ms.dob.textonphoto.utility.ArrayListUtility.Allgalleryimagepath
            java.util.Collections.shuffle(r0)
        L_0x0014:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0040, all -> 0x003e }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0040, all -> 0x003e }
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch:{ IOException -> 0x0040, all -> 0x003e }
            java.lang.String r4 = "quotesdetail.txt"
            java.io.InputStream r5 = r5.open(r4)     // Catch:{ IOException -> 0x0040, all -> 0x003e }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0040, all -> 0x003e }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0040, all -> 0x003e }
        L_0x002e:
            java.lang.String r5 = r2.readLine()     // Catch:{ IOException -> 0x003c }
            if (r5 == 0) goto L_0x0038
            r0.append(r5)     // Catch:{ IOException -> 0x003c }
            goto L_0x002e
        L_0x0038:
            r2.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x003c:
            r5 = move-exception
            goto L_0x0042
        L_0x003e:
            r5 = move-exception
            goto L_0x00ad
        L_0x0040:
            r5 = move-exception
            r2 = r1
        L_0x0042:
            r5.printStackTrace()     // Catch:{ all -> 0x00ab }
            r2.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004d:
            java.lang.String r5 = r0.toString()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00a6 }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String r5 = "QuotesDetails"
            org.json.JSONArray r5 = r0.getJSONArray(r5)     // Catch:{ JSONException -> 0x00a6 }
            r0 = 0
        L_0x005d:
            int r2 = r5.length()     // Catch:{ JSONException -> 0x00a6 }
            if (r0 >= r2) goto L_0x00a3
            com.ms.dob.textonphoto.model.QuotesFrameDetails r2 = new com.ms.dob.textonphoto.model.QuotesFrameDetails     // Catch:{ JSONException -> 0x00a6 }
            r2.<init>()     // Catch:{ JSONException -> 0x00a6 }
            org.json.JSONObject r3 = r5.getJSONObject(r0)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String[] r4 = com.ms.dob.textonphoto.utility.RandomQuoetsForFirstscreen.RandomizeQuotes()     // Catch:{ JSONException -> 0x00a6 }
            r4 = r4[r0]     // Catch:{ JSONException -> 0x00a6 }
            r2.setQuotes(r4)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String r4 = "QuotesFrameID"
            java.lang.String r4 = r3.getString(r4)     // Catch:{ JSONException -> 0x00a6 }
            r2.setQuotesFrameID(r4)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String r4 = "FontName"
            java.lang.String r4 = r3.getString(r4)     // Catch:{ JSONException -> 0x00a6 }
            r2.setFontName(r4)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String r4 = "TextColor"
            java.lang.String r3 = r3.getString(r4)     // Catch:{ JSONException -> 0x00a6 }
            r2.setTextColor(r3)     // Catch:{ JSONException -> 0x00a6 }
            java.util.ArrayList<java.lang.String> r3 = com.ms.dob.textonphoto.utility.ArrayListUtility.Allgalleryimagepath     // Catch:{ JSONException -> 0x00a6 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x00a6 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x00a6 }
            r2.setImagePath(r3)     // Catch:{ JSONException -> 0x00a6 }
            java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r3 = com.ms.dob.textonphoto.utility.ArrayListUtility.quotesframdetails     // Catch:{ JSONException -> 0x00a6 }
            r3.add(r2)     // Catch:{ JSONException -> 0x00a6 }
            int r0 = r0 + 1
            goto L_0x005d
        L_0x00a3:
            java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r5 = com.ms.dob.textonphoto.utility.ArrayListUtility.quotesframdetails
            return r5
        L_0x00a6:
            r5 = move-exception
            r5.printStackTrace()
            return r1
        L_0x00ab:
            r5 = move-exception
            r1 = r2
        L_0x00ad:
            r1.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b5:
            throw r5
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.ms.dob.textonphoto.utility.ReadJsonFromAssect.GetQuotesListFromAssect(android.app.Activity):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057 A[Catch:{ JSONException -> 0x009e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
/*
    public static java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> GetAutherQuotesListFromAssect(android.app.Activity r6, int r7) {
        com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails = new ArrayList<>();
        java.lang.StringBuffer stringBuffer = new java.lang.StringBuffer();
        android.content.res.AssetManager assetManager = r6.getAssets() ;    // Catch:{ IOException -> 0x0033, all -> 0x0031 }
        InputStream inputStream = null;   // Catch:{ IOException -> 0x0033, all -> 0x0031 }
        try {
            inputStream = assetManager.open("quotesdetail.txt");
            java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader (inputStream);
            java.io.BufferedReader br = new java.io.BufferedReader (inputStreamReader);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                stringBuffer.append(line);
            }
            br.close();
            org.json.JSONObject jsonObject = new org.json.JSONObject(stringBuffer.toString()) ;    // Catch:{ JSONException -> 0x008b }
//            r1.<init>(r7)     // Catch:{ JSONException -> 0x008b }
//                    java.lang.String r7 = "StausArray"
            org.json.JSONArray jsonArray = jsonObject.getJSONArray("QuotesDetails") ;
            int r0 = 0;
           int r2 = 0;
            int r3 = jsonArray.length();     // Catch:{ JSONException -> 0x009e }
            while (r2 < r3) {
                com.ms.dob.textonphoto.model.QuotesFrameDetails quotesFrameDetails = new com.ms.dob.textonphoto.model.QuotesFrameDetails();     // Catch:{ JSONException -> 0x009e }
//                r3.<init>()     // Catch:{ JSONException -> 0x009e }
                org.json.JSONObject r4 = jsonArray.getJSONObject(r2);     // Catch:{ JSONException -> 0x009e }
//                java.util.ArrayList<java.lang.String[]> r5 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesList     // Catch:{ JSONException -> 0x009e }
//                java.lang.Object r5 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesList.get(r2) ;    // Catch:{ JSONException -> 0x009e }
                java.lang.String[] strings = (java.lang.String[]) com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesList.get(r2);     // Catch:{ JSONException -> 0x009e }
//                String str = strings[r0];     // Catch:{ JSONException -> 0x009e }
                quotesFrameDetails.setQuotes(strings[r0]) ;    // Catch:{ JSONException -> 0x009e }
//                java.lang.String r5 = "QuotesFrameID"
//                java.lang.String r5 = ;    // Catch:{ JSONException -> 0x009e }
                quotesFrameDetails.setQuotesFrameID(r4.getString("QuotesFrameID") )   ;  // Catch:{ JSONException -> 0x009e }
//                java.lang.String r5 = "FontName"
//                java.lang.String r5 =  ;    // Catch:{ JSONException -> 0x009e }
                quotesFrameDetails.setFontName(r4.getString("FontName")) ;    // Catch:{ JSONException -> 0x009e }
//                java.lang.String r5 = "TextColor"
//                java.lang.String r4 = ) ;    // Catch:{ JSONException -> 0x009e }
                quotesFrameDetails.setTextColor(r4.getString("TextColor")) ;    // Catch:{ JSONException -> 0x009e }
//                int[] r4 = com.appsfuntimes.quoetscategory.QuoetsImageUtility.autherbigimage     // Catch:{ JSONException -> 0x009e }
//                r4 = com.appsfuntimes.quoetscategory.QuoetsImageUtility.autherbigimage[r7]  ;   // Catch:{ JSONException -> 0x009e }
                java.lang.String s = java.lang.Integer.toString(com.appsfuntimes.quoetscategory.QuoetsImageUtility.autherbigimage[r7])  ;   // Catch:{ JSONException -> 0x009e }
                quotesFrameDetails.setImagePath(s)  ;   // Catch:{ JSONException -> 0x009e }
//                java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r4 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails     // Catch:{ JSONException -> 0x009e }
                com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails.add(quotesFrameDetails);     // Catch:{ JSONException -> 0x009e }
                 r2 = r2 + 1;
            }
            return com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails;

        // Catch:{ IOException -> 0x0033, all -> 0x0031 }

        */
/*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails = r0
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            java.lang.String r4 = "quotesdetail.txt"
            java.io.InputStream r6 = r6.open(r4)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0033, all -> 0x0031 }
        L_0x0021:
            java.lang.String r6 = r2.readLine()     // Catch:{ IOException -> 0x002f }
            if (r6 == 0) goto L_0x002b
            r0.append(r6)     // Catch:{ IOException -> 0x002f }
            goto L_0x0021
        L_0x002b:
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0040
        L_0x002f:
            r6 = move-exception
            goto L_0x0035
        L_0x0031:
            r6 = move-exception
            goto L_0x00a5
        L_0x0033:
            r6 = move-exception
            r2 = r1
        L_0x0035:
            r6.printStackTrace()     // Catch:{ all -> 0x00a3 }
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0040:
            java.lang.String r6 = r0.toString()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x009e }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x009e }
            java.lang.String r6 = "QuotesDetails"
            org.json.JSONArray r6 = r0.getJSONArray(r6)     // Catch:{ JSONException -> 0x009e }
            r0 = 0
            r2 = 0
        L_0x0051:
            int r3 = r6.length()     // Catch:{ JSONException -> 0x009e }
            if (r2 >= r3) goto L_0x009b
            com.ms.dob.textonphoto.model.QuotesFrameDetails r3 = new com.ms.dob.textonphoto.model.QuotesFrameDetails     // Catch:{ JSONException -> 0x009e }
            r3.<init>()     // Catch:{ JSONException -> 0x009e }
            org.json.JSONObject r4 = r6.getJSONObject(r2)     // Catch:{ JSONException -> 0x009e }
            java.util.ArrayList<java.lang.String[]> r5 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesList     // Catch:{ JSONException -> 0x009e }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ JSONException -> 0x009e }
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ JSONException -> 0x009e }
            r5 = r5[r0]     // Catch:{ JSONException -> 0x009e }
            r3.setQuotes(r5)     // Catch:{ JSONException -> 0x009e }
            java.lang.String r5 = "QuotesFrameID"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x009e }
            r3.setQuotesFrameID(r5)     // Catch:{ JSONException -> 0x009e }
            java.lang.String r5 = "FontName"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x009e }
            r3.setFontName(r5)     // Catch:{ JSONException -> 0x009e }
            java.lang.String r5 = "TextColor"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ JSONException -> 0x009e }
            r3.setTextColor(r4)     // Catch:{ JSONException -> 0x009e }
            int[] r4 = com.appsfuntimes.quoetscategory.QuoetsImageUtility.autherbigimage     // Catch:{ JSONException -> 0x009e }
            r4 = r4[r7]     // Catch:{ JSONException -> 0x009e }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ JSONException -> 0x009e }
            r3.setImagePath(r4)     // Catch:{ JSONException -> 0x009e }
            java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r4 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails     // Catch:{ JSONException -> 0x009e }
            r4.add(r3)     // Catch:{ JSONException -> 0x009e }
            int r2 = r2 + 1
            goto L_0x0051
        L_0x009b:
            java.util.ArrayList<com.ms.dob.textonphoto.model.QuotesFrameDetails> r6 = com.ms.dob.textonphoto.utility.ArrayListUtility.autherQuotesdetails
            return r6
        L_0x009e:
            r6 = move-exception
            r6.printStackTrace()
            return r1
        L_0x00a3:
            r6 = move-exception
            r1 = r2
        L_0x00a5:
            r1.close()     // Catch:{ IOException -> 0x00a9 }
            goto L_0x00ad
        L_0x00a9:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ad:
            throw r6
        *//*

//        throw new UnsupportedOperationException("Method not decompiled: com.ms.dob.textonphoto.utility.ReadJsonFromAssect.GetAutherQuotesListFromAssect(android.app.Activity, int):java.util.ArrayList");
    }
*/

//    public static String getURLForResource(int i) {
//        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + i).toString();
//    }
}
