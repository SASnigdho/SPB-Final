package ittouchbd.com.smartpillbox.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;

public class MedicineNameAdapter extends RecyclerView.Adapter<MedicineNameAdapter.ViewHolder> {


    private List<PrescriptionInfo> prescriptionInfoList;
    private int itemPosition;

    private static ClickListener clickListener;

    public MedicineNameAdapter() {
    }

    public MedicineNameAdapter(List<PrescriptionInfo> prescriptionInfoList) {
        this.prescriptionInfoList = prescriptionInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        PrescriptionInfo currentPrescription = prescriptionInfoList.get(i);
        viewHolder.medicineName.setText(currentPrescription.getMedicineInfo().getName());
    }

    @Override
    public int getItemCount() {
        return prescriptionInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView medicineName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineName = itemView.findViewById(R.id.item_tv_Id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(ClickListener clickListener) {

        MedicineNameAdapter.clickListener = clickListener;
    }
}
