package ittouchbd.com.smartpillbox.fragments.inventory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ittouchbd.com.smartpillbox.MainActivity;
import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.adapters.DoctorNameAdapter;
import ittouchbd.com.smartpillbox.helper.ChangeFragment;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;

public class InventoryDoctorNameFM extends Fragment {

    private RecyclerView viewAllRCV;
    private DoctorNameAdapter doctorNameAdapter;
    private List<PrescriptionInfo> prescriptionInfoList;
    private ChangeFragment changeFragment;

    //Firebase
    private FirebaseDatabase firebaseDatabase;

    public InventoryDoctorNameFM() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_prescription_list, container, false);

        findViewById(view);
//        changeFragment = (MainActivity)getActivity();
//
//        doctorNameAdapter = new DoctorNameAdapter(prescriptionInfoList);
//        viewAllRCV.setLayoutManager(new LinearLayoutManager(getContext()));
//        viewAllRCV.setItemAnimator(new DefaultItemAnimator());
//        viewAllRCV.setAdapter(doctorNameAdapter);


        return view;
    }

    private void findViewById(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        changeFragment = (MainActivity)getActivity();

        viewAllRCV = view.findViewById(R.id.viewAll_rcv_Id);
        prescriptionInfoList = new ArrayList<>();

        doctorNameAdapter = new DoctorNameAdapter(prescriptionInfoList);
        viewAllRCV.setLayoutManager(new LinearLayoutManager(getContext()));
        viewAllRCV.setItemAnimator(new DefaultItemAnimator());
        viewAllRCV.setAdapter(doctorNameAdapter);

        doctorNameAdapter.setOnItemClickListener(new DoctorNameAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //Get ID of Prescription
                String prescriptionId = prescriptionInfoList.get(position).getId();
                //getDoctorName
                String doctorName = prescriptionInfoList.get(position).getDoctorInfo().getName();

                changeFragment.changeFragment(new InventoryMedicineListFM(), doctorName, prescriptionId);
            }

            @Override
            public void onItemLongClick(int position, View view) {

                String prescriptionId = prescriptionInfoList.get(position).getId();
                Toast.makeText(getContext(), "Long Clicked! No Feature Added", Toast.LENGTH_SHORT).show();
//              changeFragment.changeFragment(new UpdatePrescriptionFM(),"PrescriptionInfo", prescriptionId);
            }
        });

        getDateFromFireBase();
    }

    private void getDateFromFireBase() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList");

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                prescriptionInfoList.clear();

                if (dataSnapshot.exists()) {

                    for (DataSnapshot date : dataSnapshot.getChildren()) {
                        PrescriptionInfo newPrescriptionInfo = date.getValue(PrescriptionInfo.class);
                        prescriptionInfoList.add(newPrescriptionInfo);

                        doctorNameAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(getContext(), "No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
