package com.qfdqc.views.pulltoloadmoreview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qfdqc.views.pulltoloadmoreview.R;

import java.util.ArrayList;

/**
 * Description: <br>
 *
 * @author : dell - XiaomiLi<br>
 * Date: 2018-09-03<br>
 * Time: 8:47<br>
 * UpdateDescription：<br>
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    ArrayList<String> mList = new ArrayList<>();
    Context mContext;

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    public void setList(ArrayList<String> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
