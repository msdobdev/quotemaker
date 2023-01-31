package com.ms.dob.textonphoto.model;

import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

public class Multieffectpossition {
    public static ArrayList<Multieffectpossition> textViewArrayListshadowId = new ArrayList<>();
    int possition;
    TextView textView;

    public TextView getTextView() {
        return this.textView;
    }

    public int getPossition() {
        return this.possition;
    }

    public void setTextView(TextView textView2) {
        this.textView = textView2;
    }

    public void setPossition(int i) {
        this.possition = i;
    }

    public static int getMultieffectcolorpo(TextView textView2) {
        for (int i = 0; i < textViewArrayListshadowId.size(); i++) {
            if (textViewArrayListshadowId.get(i).getTextView() == textView2) {
                return i;
            }
        }
        return -1;
    }

    public static void additeaminlist(Activity activity, TextView textView2, int i, boolean z) {
        int multieffectcolorpo = getMultieffectcolorpo(textView2);
        if (multieffectcolorpo != -1) {
            if (!textViewArrayListshadowId.contains(textViewArrayListshadowId.get(multieffectcolorpo))) {
                Multieffectpossition multieffectpossition = new Multieffectpossition();
                multieffectpossition.setPossition(i);
                multieffectpossition.setTextView(WriteText.weitetextview);
                textViewArrayListshadowId.add(multieffectpossition);
            } else if (z) {
                Multieffectpossition multieffectpossition2 = new Multieffectpossition();
                multieffectpossition2.setPossition(i);
                multieffectpossition2.setTextView(new TextView(activity));
                textViewArrayListshadowId.set(multieffectcolorpo, multieffectpossition2);
            } else {
                Multieffectpossition multieffectpossition3 = new Multieffectpossition();
                multieffectpossition3.setPossition(i);
                multieffectpossition3.setTextView(WriteText.weitetextview);
                textViewArrayListshadowId.set(multieffectcolorpo, multieffectpossition3);
            }
        } else {
            Multieffectpossition multieffectpossition4 = new Multieffectpossition();
            multieffectpossition4.setPossition(i);
            multieffectpossition4.setTextView(WriteText.weitetextview);
            textViewArrayListshadowId.add(multieffectpossition4);
        }
    }
}
