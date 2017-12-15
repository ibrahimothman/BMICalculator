package com.dell.bmicalculator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dell on 9/19/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private ArrayList<InfoModel> info;
    private DatabaseHelper databaseHelper;

    public HistoryAdapter(Context context,ArrayList<InfoModel> info) {
        this.context = context;
        this.info = info;
    }

    // inflate
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item,parent,false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView dateText,ageText,resultText,noHistory;
        private ImageView delteIcon;
        private int index;

        public HistoryViewHolder(final View itemView) {
            super(itemView);
            delteIcon = (ImageView) itemView.findViewById(R.id.delete_icon);
            dateText = (TextView)itemView.findViewById(R.id.date);
            ageText = (TextView)itemView.findViewById(R.id.age);
            resultText = (TextView)itemView.findViewById(R.id.result);
            noHistory = (TextView)itemView.findViewById(R.id.no_history);
            delteIcon.setOnClickListener(this);

        }

        public void onBind(int position){
            index = position;
            ageText.setText(info.get(position).getAge());
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            dateText.setText(currentDate);
            resultText.setText(info.get(position).getBmiResult());

        }


        @Override
        public void onClick(View view) {
            Log.d("from adapter: ",index+"");
            info.remove(index);
            if (index == 0){
                notifyDataSetChanged();
            }else{
                notifyItemRemoved(index);
            }
          // databaseHelper = new DatabaseHelper(context);
              //  databaseHelper.REMOVE_RECORD(index+1);

        }
    }
}
