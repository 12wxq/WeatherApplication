package com.example.weatherapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapplication.R;
import com.example.weatherapplication.bean.OtherTipsBean;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
   private Context mContext;
   private List<OtherTipsBean> mTipsBeans;

    public TipsAdapter(Context mContext, List<OtherTipsBean> mTipsBeans) {
        this.mContext = mContext;
        this.mTipsBeans = mTipsBeans;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item_layout, parent, false);
        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        OtherTipsBean otherTipsBean = mTipsBeans.get(position);
        holder.tvTitle.setText(otherTipsBean.getTitle());
        holder.tvlevel.setText(otherTipsBean.getLevel());
        holder.tvDesc.setText(otherTipsBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return (mTipsBeans==null)?0:mTipsBeans.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder{
      TextView tvTitle,tvDesc,tvlevel;
        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc=itemView.findViewById(R.id.tv_desc);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvlevel=itemView.findViewById(R.id.tv_level);
        }
    }
}
