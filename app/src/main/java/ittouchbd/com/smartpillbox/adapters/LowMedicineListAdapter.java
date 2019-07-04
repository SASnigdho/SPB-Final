package ittouchbd.com.smartpillbox.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ittouchbd.com.smartpillbox.R;

public class LowMedicineListAdapter extends RecyclerView.Adapter<LowMedicineListAdapter.ViewHolder> {

    private ArrayList<String> lowMedicineNameList;

    public LowMedicineListAdapter(ArrayList<String> lowMedicineNameList) {
        this.lowMedicineNameList = lowMedicineNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.medicineName.setText(lowMedicineNameList.get(i));
    }

    @Override
    public int getItemCount() {
        return lowMedicineNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView medicineName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.item_tv_Id);
        }
    }
}
