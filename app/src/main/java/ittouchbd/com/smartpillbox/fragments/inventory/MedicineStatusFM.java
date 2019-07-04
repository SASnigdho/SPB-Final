package ittouchbd.com.smartpillbox.fragments.inventory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.MedicineInfo;


public class MedicineStatusFM extends Fragment {

    private TextView totalNeedToINtakeTV, alreadyIntakeTV, dueIntakeTV, currentStockTV, alreadyPurchaseTV, duePurchaseTV;
    private EditText newPurchaseET;
    private Button addNewPurchaseBT;

    private String prescriptionId;
    //Fire Base
    private FirebaseDatabase firebaseDatabase;

    //Object
    private MedicineInfo newMedicineInfo;

    public MedicineStatusFM() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_status, container, false);

        findViewById(view);
        loadMedicineStatus();

        return view;
    }

    private void findViewById(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        Bundle bundle = getArguments();
        assert bundle != null;
        prescriptionId = bundle.getString("prescriptionId");

        totalNeedToINtakeTV = view.findViewById(R.id.MS_totalNeedToIntake_tv_Id);
        alreadyIntakeTV = view.findViewById(R.id.MS_alreadyIntake_tv_Id);
        dueIntakeTV = view.findViewById(R.id.MS_dueIntake_tv_Id);
        currentStockTV = view.findViewById(R.id.MS_currentStock_tv_Id);
        alreadyPurchaseTV = view.findViewById(R.id.MS_alreadyPurchase_tv_Id);
        duePurchaseTV = view.findViewById(R.id.MS_duePurchase_tv_Id);

        newPurchaseET = view.findViewById(R.id.MS_newPurchase_edt_Id);

        addNewPurchaseBT = view.findViewById(R.id.MS_addNewPurchase_btn_Id);
        addNewPurchaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPurchase = Integer.parseInt(newPurchaseET.getText().toString().trim());
                int alreadyPurchased = newMedicineInfo.getAlreadyPurchase();
                int duePurchase = newMedicineInfo.getDuePurchase();
                int currentStock = newMedicineInfo.getCurrentStock();

                int newAlreadyPurchased = alreadyPurchased + newPurchase;
                int newDuePurchase = duePurchase - newPurchase;
                int newCurrentStock = currentStock + newPurchase;

                update(newCurrentStock, newAlreadyPurchased, newDuePurchase);
            }
        });
    }

    private void update(int newCurrentStock, int newAlreadyPurchased, int newDuePurchase) {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId).child("medicineInfo");
        userDB.child("currentStock").setValue(newCurrentStock);
        userDB.child("alreadyPurchase").setValue(newAlreadyPurchased);
        userDB.child("duePurchase").setValue(newDuePurchase);

        newPurchaseET.setText("");
    }


    private void loadMedicineStatus() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId).child("medicineInfo");

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                newMedicineInfo = dataSnapshot.getValue(MedicineInfo.class);

                setDataToTextView(newMedicineInfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setDataToTextView(MedicineInfo medicineInfo) {
        totalNeedToINtakeTV.setText("Total Need to Intake: " + medicineInfo.getTotalIntake());
        alreadyIntakeTV.setText("Already Intake: " + medicineInfo.getAlreadyIntake());
        dueIntakeTV.setText("Due Intake: " + medicineInfo.getDueIntake());

        currentStockTV.setText("Current Stock: " + medicineInfo.getCurrentStock());
        alreadyPurchaseTV.setText("Already Purchase: " + medicineInfo.getAlreadyPurchase());
        duePurchaseTV.setText("Due Purchase: " + medicineInfo.getDuePurchase());
    }

}
