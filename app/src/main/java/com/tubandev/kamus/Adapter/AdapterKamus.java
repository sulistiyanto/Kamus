package com.tubandev.kamus.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tubandev.kamus.Model.KamusModel;
import com.tubandev.kamus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sulistiyanto on 18/03/18.
 */

public class AdapterKamus extends RecyclerView.Adapter<AdapterKamus.ViewHolder> {

    private List<KamusModel> kamusModels;
    private OnItemClickListener listener;

    public AdapterKamus(List<KamusModel> kamusModels, OnItemClickListener listener) {
        this.kamusModels = kamusModels;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(kamusModels.get(position));
    }

    @Override
    public int getItemCount() {
        return kamusModels.size();
    }

    public interface OnItemClickListener {
        void onClick(KamusModel kamusModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtWord)
        TextView txtWord;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final KamusModel kamusModel) {
            txtWord.setText(kamusModel.getWord());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(kamusModel);
                }
            });
        }
    }
}
