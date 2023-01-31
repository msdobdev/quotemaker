package com.ms.dob.textonphoto.adapter;


import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseBooleanArray;


import java.util.ArrayList;
import java.util.List;

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final String TAG = "SelectableAdapter";
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public boolean isSelected(int i) {
        return getSelectedItems().contains(Integer.valueOf(i));
    }

    public void toggleSelection(int i) {
        if (this.selectedItems.get(i, false)) {
            this.selectedItems.delete(i);
        } else {
            this.selectedItems.put(i, true);
        }
        notifyItemChanged(i);
    }

    public void clearSelection() {
        List<Integer> selectedItems2 = getSelectedItems();
        this.selectedItems.clear();
        for (Integer intValue : selectedItems2) {
            notifyItemChanged(intValue.intValue());
        }
    }

    public int getSelectedItemCount() {
        return this.selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        ArrayList arrayList = new ArrayList(this.selectedItems.size());
        for (int i = 0; i < this.selectedItems.size(); i++) {
            arrayList.add(Integer.valueOf(this.selectedItems.keyAt(i)));
        }
        return arrayList;
    }

    public void toggleall_iteamSelection(int i) {
        if (this.selectedItems.get(i, false)) {
            this.selectedItems.delete(i);
        } else {
            this.selectedItems.put(i, true);
        }
    }

    public void clear_all_Selection() {
        getSelectedItems();
        this.selectedItems.clear();
    }
}
