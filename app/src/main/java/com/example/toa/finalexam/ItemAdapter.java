package com.example.toa.finalexam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Toa on 4/12/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {

    private List<Item> itemList;
    private TextToSpeech tts;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_name);
        }
    }


    public ItemAdapter(List<Item> itemList, TextToSpeech t, Context cont) {
        this.itemList = itemList;
        //tts = t;
        context = cont;
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
        if ((position + 1) % 2 == 0) {
            holder.title.setTextColor(Color.RED);
        } else {
            holder.title.setTextColor(Color.BLUE);
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements TextToSpeech.OnInitListener{
        private TextView title;
        public String url;
        public CustomViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);
            System.out.println("HMMM" + getAdapterPosition());


            tts = new TextToSpeech(context, this);
            tts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
                @Override
                public void onUtteranceCompleted(String utteranceId) {
                    link();
                }
            });
            //Catch the click on item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v) {
                    String txt = itemList.get(getAdapterPosition()).getName();
                    url = itemList.get(getAdapterPosition()).getUrl();
                    tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }

        public void link() {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }


        @Override
        public void onInit(int status) {

        }


    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}