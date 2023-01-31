package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.dob.textonphoto.R;

import java.util.ArrayList;

public class EditingOptionAdapter extends SelectableAdapter<EditingOptionAdapter.ViewHolder> {
    private  Activity mContext;
    private EditingClickListener clickListener;
    public int[] imageselected = {R.drawable.ic_select_background, R.drawable.ic_select_quote, R.drawable.ic_add_text, R.drawable.ic_text_color, R.drawable.ic_text_size, R.drawable.ic_text_alignment, R.drawable.ic_shadow, R.drawable.ic_multicolor_text, R.drawable.ic_font_style, R.drawable.ic_addemoji, R.drawable.ic_canvas, R.drawable.ic_canvas};
    private LayoutInflater mLayoutInflater;
    ArrayList<String> optionname;

    public EditingOptionAdapter(Activity activity, EditingClickListener clickListener2) {
        mContext = activity;
        this.clickListener = clickListener2;
        adoptionname_in_list();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.list_item_edit_bottom_option,
                viewGroup,
                false), this.clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.titlename.setText(this.optionname.get(i));
        viewHolder.icon.setImageResource(this.imageselected[i]);
    }

    public int getItemCount() {
        return this.optionname.size();
    }
    public interface EditingClickListener {
        void OnEditOptionIteamClick(int i);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView icon;
         EditingClickListener listener;
        TextView titlename;



        public boolean onLongClick(View view) {
            return false;
        }

        public ViewHolder(View view, EditingClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.icon = (ImageView) view.findViewById(R.id.iview_icon);
            this.titlename = (TextView) view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.OnEditOptionIteamClick(getAdapterPosition());
            }
        }
    }

    private void adoptionname_in_list() {
        if (this.optionname == null) {
            this.optionname = new ArrayList<>();
            this.optionname.add("BG");
            this.optionname.add("Status");
            this.optionname.add("Text");
            this.optionname.add("Color");
            this.optionname.add("Size");
            this.optionname.add("Font");
            this.optionname.add("Shadow");
            this.optionname.add("Multi Color");
            this.optionname.add("Alignment ");
            this.optionname.add("Add Emoji");
            this.optionname.add("Canvas");
        }
    }
}
