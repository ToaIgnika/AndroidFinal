package com.example.toa.finalexam;

import android.graphics.Movie;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Toa on 4/12/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {

    private List<Item> itemList;
    private TextToSpeech tts;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_name);
        }
    }


    public ItemAdapter(List<Item> itemList, TextToSpeech t) {
        this.itemList = itemList;
        tts = t;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.getName());
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public CustomViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);

            //Catch the click on item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v) {
                    String txt = itemList.get(getAdapterPosition()).getName();
                    tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}