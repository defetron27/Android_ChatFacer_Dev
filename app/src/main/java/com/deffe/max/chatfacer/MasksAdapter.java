package com.deffe.max.chatfacer;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MasksAdapter extends RecyclerView.Adapter<MasksAdapter.MasksViewHolder>
{
    private Context context;
    private ArrayList<Bitmap> masks;
    private final OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(Bitmap item);
    }

    MasksAdapter(Context context, ArrayList<Bitmap> masks,OnItemClickListener listener)
    {
        this.context = context;
        this.masks = masks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.masks_items,viewGroup,false);
        return new MasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasksViewHolder masksViewHolder, int i)
    {
        Bitmap bitmap = masks.get(i);

        masksViewHolder.bind(bitmap,listener);
    }

    @Override
    public int getItemCount()
    {
        return masks.size();
    }

    class MasksViewHolder extends RecyclerView.ViewHolder
    {
        View view;

        ImageView masksView;

        MasksViewHolder(@NonNull View itemView)
        {
            super(itemView);

            view = itemView;

            masksView = view.findViewById(R.id.masks_items);
        }

        void bind(final Bitmap item, final OnItemClickListener listener)
        {
            masksView.setImageBitmap(item);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
