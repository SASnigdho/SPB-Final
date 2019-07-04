package ittouchbd.com.smartpillbox.fragments;


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

import ittouchbd.com.smartpillbox.Models.Prescription;
import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.adapters.LowMedicineListAdapter;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class LowMedicineFM extends Fragment {
    private View view;
    //Firebase Database
    private FirebaseDatabase firebaseDatabase;

    private RecyclerView lowMedicineListRV;
    private LowMedicineListAdapter lowMedicineListAdapter;
    private ArrayList<String> medicineNameList;

    private ArrayList<PrescriptionInfo> prescriptionInfoList;

    public LowMedicineFM(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_low_medicine, container, false);

        initialize();

//        medicineNameList = new ArrayList<String>();
//        lowMedicineListAdapter = new LowMedicineListAdapter(medicineNameList);
//        lowMedicineListRV.setLayoutManager(new LinearLayoutManager(getContext()));
//        lowMedicineListRV.setItemAnimator(new DefaultItemAnimator());
//        lowMedicineListRV.setAdapter(lowMedicineListAdapter);

        return view;
    }

    private void initialize() {
        initializeBasicVariable();
        loadMedicineName();
    }


    private void initializeBasicVariable() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        recyclerViewInitialize();
    }

    private void recyclerViewInitialize() {
        lowMedicineListRV = view.findViewById(R.id.lowMedicineList_rcv_Id);
        medicineNameList = new ArrayList<String>();
        lowMedicineListAdapter = new LowMedicineListAdapter(medicineNameList);
        lowMedicineListRV.setLayoutManager(new LinearLayoutManager(getContext()));
        lowMedicineListRV.setItemAnimator(new DefaultItemAnimator());
        lowMedicineListRV.setAdapter(lowMedicineListAdapter);
    }

    private void loadMedicineName() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList");

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        PrescriptionInfo prescription = data.getValue(PrescriptionInfo.class);

                        if (prescription.getMedicineInfo().getCurrentStock() < 5) {
                            medicineNameList.add(prescription.getMedicineInfo().getName());
                            lowMedicineListAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "There is no low Medicine", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
