package com.example.user.photocollage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {

    private ArrayList<ImgItem> imgList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    ImgAdapter(Context context, ArrayList<ImgItem> imgList) {
        this.mInflater = LayoutInflater.from(context);
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the img to the ImageView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImgItem curImg = imgList.get(position);
        holder.bindImgToViewHolder(curImg);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    // the view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.image_box);
            itemView.setOnClickListener(this);
        }

        void bindImgToViewHolder(ImgItem img) {
            if (!TextUtils.isEmpty(img.getUrl())) {
                Picasso.with(myImageView.getContext()).load(img.getUrl()).into(myImageView);
            }
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    ImgItem getItem(int id) {
        return imgList.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
