package ittouchbd.com.smartpillbox.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.SheduleTime;

public class SheduleAdapter extends RecyclerView.Adapter<SheduleAdapter.ViewHolder> {

    private ArrayList<SheduleTime> times;

    public SheduleAdapter(ArrayList<SheduleTime> times) {
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.timeTV.setText(times.get(i).getTimeString());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView timeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            timeTV = (TextView) itemView.findViewById(R.id.item_tv_Id);
        }
    }
}
