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

public class DoctorNameAdapter extends RecyclerView.Adapter<DoctorNameAdapter.ViewHolder> {

    private List<PrescriptionInfo> prescriptionInfoList;
    private int itemPosition;

    private static ClickListener clickListener;

    public DoctorNameAdapter(List<PrescriptionInfo> prescriptionInfoList) {
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
        PrescriptionInfo currentPrescriptionInfo = prescriptionInfoList.get(i);
        viewHolder.doctorName.setText(currentPrescriptionInfo.getDoctorInfo().getName());
    }

    @Override
    public int getItemCount() {
        return prescriptionInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView doctorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.item_tv_Id);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {

            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
        void onItemLongClick(int position, View view);
    }

    public void setOnItemClickListener(ClickListener clickListener) {

        DoctorNameAdapter.clickListener = clickListener;
    }
}
