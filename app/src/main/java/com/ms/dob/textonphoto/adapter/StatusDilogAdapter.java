package com.ms.dob.textonphoto.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.dob.textonphoto.dialog.StatusDialog;
import com.ms.dob.textonphoto.R;
//

public class StatusDilogAdapter extends SelectableAdapter<StatusDilogAdapter.ViewHolder> {
    private static StatusClickListener clickListener;
    private static Activity mContext;
    private LayoutInflater mLayoutInflater;
    int provous = 0;

    private void Hide_Unhide_WhiteTick(ViewHolder viewHolder, int i) {
    }

    public StatusDilogAdapter(Activity activity, StatusClickListener clickListener2) {
        mContext = activity;
        clickListener = clickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getItemViewType(i);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.dialog_quotes_list_item, viewGroup, false), clickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(StatusDialog.stauslist.get(i)[0]);
        TextView textView = viewHolder.numbertext;
        textView.setText("" + (i + 1));
        Hide_Unhide_WhiteTick(viewHolder, i);
    }

    public int getItemCount() {
        return StatusDialog.stauslist.size();
    }
    public interface StatusClickListener {
        void onItemClicked_Status(int i);
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private StatusClickListener listener;
        TextView numbertext;
        TextView textView;



        public ViewHolder(View view, StatusClickListener clickListener) {
            super(view);
            this.listener = clickListener;
            this.textView = (TextView) view.findViewById(R.id.tv_status);
            this.numbertext = (TextView) view.findViewById(R.id.numbertext);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClicked_Status(getAdapterPosition());
            }
        }

        public boolean onLongClick(View view) {
            StatusClickListener clickListener = this.listener;
            return false;
        }
    }
}
