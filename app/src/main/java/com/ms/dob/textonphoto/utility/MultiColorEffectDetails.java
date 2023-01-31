package com.ms.dob.textonphoto.utility;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.TextView;

import com.ms.dob.textonphoto.adapter.ColorListAdapter;
import com.ms.dob.textonphoto.model.WriteText;

public class MultiColorEffectDetails {
    public static String[] effect_c1 = {"#F97C3C", "#FF000C", "#2B26C1", "#808080", "#FF7919", "#ff0000", "#c2554a", "#77bf17", "#1cdf0a", "#140E00", "#545792", "#9400d3", "#ff0000", "#e9967a", "#f4a460", "#FF006E", "#30097C", "#2100FF", "#7F0037", "#000000", "#FFA500"};
    String[] effect_c2 = {"#FDB54E", "#CC000A", "#2321A8", "#FF00FF", "#FFA788", "#ff7f00", "#f7a4e9", "#477d3f", "#2e2463", "#2be807", "#a52a2a", "#bdb76b", "#2e8b57", "#0000ff", "#000080", "#FF2D4D", "#FBF9FF", "#74FF49", "#707C76", "#56B72A", "#C17A00"};
    String[] effect_c3 = {"#64B678", "#CC6C00", "#30A58A", "#FF49B3", "#000000", "#ffff00", "#f9d216", "#d9db05", "#07caed", "#cec55d", "#8b8989", "#708090", "#0000ff", "#8D9912", "#99000C", "#FFFEF9", "#FF7270", "#FFC61E", "#7A3B27", "#AFAEAC", "#DB1D00"};
    String[] effect_c4 = {"#478AEA", "#CC9714", "#2FA35F", "#FF796B", "#8989FF", "#00ff00", "#11ef2c", "#d4f12e", "#869f9d", "#05fee9", "#FFED54", "#FFA366", "#96FFF6", "#404040", "#21233F", "#000000", "#FF26DE", "#FF6A00", "#770100", "#B54869", "#DB1DBE"};
    String[] effect_c5 = {"#8446CC", "#CCBF12", "#49A02E", "#FF4D21", "#4A4A89", "#00ffff", "#a55d6b", "#98e8b8", "#5b3196", "#f11ee9", "#3D1A01", "#003A3A", "#303823", "#503F7F", "#7F0037", "#00137F", "#70B2FF", "#FF006E", "#7701C9", "#00B21A", "#D63550"};

    public MultiColorEffectDetails() {
    }


    public void Setmulticolor_Effect(TextView textView, int i, boolean z) {
        if (textView != null) {
            WriteText.SetTextColor(textView, ColorListAdapter.colorname[i]);
            textView.getPaint().setShader((Shader) null);
            if (!z) {
                textView.setTextColor(Color.parseColor(this.effect_c1[i]));
                textView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, textView.getPaint().measureText(WriteText.GetText(textView)), 0.0f, new int[]{Color.parseColor(this.effect_c1[i]), Color.parseColor(this.effect_c2[i]), Color.parseColor(this.effect_c3[i]), Color.parseColor(this.effect_c4[i]), Color.parseColor(this.effect_c5[i])}, (float[]) null, Shader.TileMode.MIRROR));
            }
        }
    }
}
