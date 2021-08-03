package com.example.employeeside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomService extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private ArrayList<Services> servicesArrayList;
    private ServiceAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private int EmpID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);

        navigationView = findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);

        EmpID=getIntent().getIntExtra("EmpID",0);

        Menu menu = navigationView.getMenu();
        MenuItem bb = menu.findItem(R.id.profile2);
        MenuItem empId= menu.findItem(R.id.id);
        MenuItem department= menu.findItem(R.id.departmentname);

        bb.setTitle("Mehmet Kaytaz");
        empId.setTitle("Employee ID: "+String.valueOf(EmpID));
        department.setTitle("Room Service Department");

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RoomService");

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        //Services s1 = new Services(123,"18:00-19:00","deletebox_name",123,"");
        //Services s2 = new Services(123,"18:00-19:00","deletebox_name",123,"");
        servicesArrayList = new ArrayList<>();

        //servicesArrayList.add(s1);
        //servicesArrayList.add(s2);

        adapter = new ServiceAdapter(this, servicesArrayList);
        rv.setAdapter(adapter);
        tumKelimeler();


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.namesurname) {
            Toast.makeText(getApplicationContext(), "lalala", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.profile2) {
            Toast.makeText(getApplicationContext(), "lalala", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void tumKelimeler() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                servicesArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Services service = d.getValue(Services.class);
                    service.setRoomService_key(d.getKey());

                    servicesArrayList.add(service);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}