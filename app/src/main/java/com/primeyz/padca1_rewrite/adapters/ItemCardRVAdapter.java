package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.viewholders.MeditationViewHolder;


@SuppressWarnings("all")
public class ItemCardRVAdapter extends RecyclerView.Adapter<MeditationViewHolder> {

	private LayoutInflater mLayoutInflator;

	public ItemCardRVAdapter(Context context) {
		mLayoutInflator = LayoutInflater.from(context);
	}

	@NonNull
	@Override
	public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View topicItemView = mLayoutInflator.inflate(R.layout.item_view_card, parent, false);
		return new MeditationViewHolder(topicItemView);
	}

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {

    }


	@Override
	public int getItemCount() {
		return 10;
	}
}
