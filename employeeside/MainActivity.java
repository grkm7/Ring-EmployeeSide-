package com.example.employeeside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText etID;
    private EditText etPassword;
    private Button buttonRoom;
    private TextView tvSonuc;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String sifre;
    private String password;

    private int sifreUserInt;
    private int passwordUserInt;

    private int databaseIdUserInt;

    public int roomNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.etID);
        etPassword = findViewById(R.id.etPassword);
        buttonRoom = findViewById(R.id.buttonRoom);
        tvSonuc = findViewById(R.id.tvSonuc);

        tvSonuc.setText("Enter ID and Passsword");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Employee");


        buttonRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int id =Integer.parseInt(etID.getText().toString());

                if (TextUtils.isEmpty(etID.getText().toString())) {
                    tvSonuc.setText("Please Enter Id");
                } else {
                    int idUserInt = Integer.parseInt(etID.getText().toString());

                    Query SifreSorgu = myRef.orderByChild("employee_id").equalTo(idUserInt);
                    SifreSorgu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                Employee employee = d.getValue(Employee.class);
                                String key = d.getKey();

                                employee.setEmployee_key(key);
                                sifreUserInt = employee.getEmployee_password();
                                databaseIdUserInt = employee.getEmployee_id();


                            }
                            if (TextUtils.isEmpty(etPassword.getText().toString())) {
                                tvSonuc.setText("Please Enter Password ");
                            } else {
                                passwordUserInt = Integer.parseInt(etPassword.getText().toString());

                                if (sifreUserInt == passwordUserInt && databaseIdUserInt == idUserInt) {
                                    roomNo = databaseIdUserInt;
                                    tvSonuc.setText("Başarılı");
                                    if(databaseIdUserInt%2==0) {
                                        Intent intent = new Intent(MainActivity.this, OrderList.class); //çiftler order
                                        intent.putExtra("EmpID", idUserInt);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(MainActivity.this, RoomService.class); //tekler room
                                        intent.putExtra("EmpID", idUserInt);
                                        startActivity(intent);
                                        finish();
                                    }

                                } else {
                                    tvSonuc.setText("Şifre veya ID yanlış");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}