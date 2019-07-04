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
import ittouchbd.com.smartpillbox.adapters.MedicineNameAdapter;
import ittouchbd.com.smartpillbox.helper.ChangeFragment;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;


public class InventoryMedicineListFM extends Fragment {

    private RecyclerView medicineListRCV;
    private MedicineNameAdapter medicineNameAdapter;
    private List<PrescriptionInfo> prescriptionInfoList;

    private ChangeFragment changeFragment;
    //Fire Base
    private FirebaseDatabase firebaseDatabase;
    private String prescriptionID;

    public InventoryMedicineListFM() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_medicine_list, container, false);

        findViewById(view);

        return view;
    }

    private void findViewById(View view) {
        changeFragment = (MainActivity) getActivity();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Bundle bundle = getArguments();
        prescriptionID = bundle.getString("prescriptionId");

        medicineListRCV = view.findViewById(R.id.imlf_rcv_Id);
        prescriptionInfoList = new ArrayList<>();

        medicineNameAdapter = new MedicineNameAdapter(prescriptionInfoList);
        medicineListRCV.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineListRCV.setItemAnimator(new DefaultItemAnimator());
        medicineListRCV.setAdapter(medicineNameAdapter);

        medicineNameAdapter.setOnItemClickListener(new MedicineNameAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                changeFragment.changeFragment(new MedicineStatusFM(), "Medicine Status", prescriptionID);
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

                        if (newPrescriptionInfo.getId() == prescriptionID){
                            prescriptionInfoList.add(newPrescriptionInfo);
                            medicineNameAdapter.notifyDataSetChanged();
                        }
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
