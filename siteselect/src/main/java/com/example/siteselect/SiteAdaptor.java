package com.example.siteselect;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiteAdaptor extends RecyclerView.Adapter {
    private static final String TAG = "Cycleradaptor";


    private List<site> sitelist;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (sitelist != null) {
            return sitelist.size();
        } else {
            return 0;
        }
    }
}


