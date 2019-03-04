package com.ismailhakkiaydin.recyclerviewcalisma;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<Manzara> mDataList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Manzara> data){
        //inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater=LayoutInflater.from(context);
        this.mDataList = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Manzara tiklanılanManzara = mDataList.get(position);
        holder.setData(tiklanılanManzara, position);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void deleteItem(int position){

        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDataList.size());
    }

    public void addItem(int position, Manzara kopyalanacakManzara){
        mDataList.add(position, kopyalanacakManzara);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mDataList.size());

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mManzaraBaslik, mManzaraAciklama;
        ImageView mManzaraResmi, mSilResmi, mKopyalaResmi;
        int tiklananItemPosition=0;
        Manzara kopyalanackManzara;

        public MyViewHolder(View itemView) {
            super(itemView);

            mManzaraBaslik = itemView.findViewById(R.id.tvManzaraBaslik);
            mManzaraAciklama = itemView.findViewById(R.id.tvManzaraTanim);
            mManzaraResmi = itemView.findViewById(R.id.imgManzara);
            mSilResmi = itemView.findViewById(R.id.imgSil);
            mKopyalaResmi = itemView.findViewById(R.id.imgKopyala);

            mSilResmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(tiklananItemPosition);
                }
            });

            mKopyalaResmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addItem(tiklananItemPosition,kopyalanackManzara);
                }
            });

        }

        public void setData(Manzara tiklanılanManzara, int position) {

            this.mManzaraBaslik.setText(tiklanılanManzara.getBaslik());
            this.mManzaraAciklama.setText(tiklanılanManzara.getTanim());
            this.mManzaraResmi.setImageResource(tiklanılanManzara.getImageID());
            this.tiklananItemPosition=position;
            this.kopyalanackManzara=tiklanılanManzara;

        }
    }

}
