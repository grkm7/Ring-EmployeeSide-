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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private ArrayList<Order> orderArrayList;
    private OrderAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private int EmpID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        navigationView=findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();

        EmpID=getIntent().getIntExtra("EmpID",0);

        // find MenuItem you want to change
        MenuItem bb = menu.findItem(R.id.profile2);
        MenuItem empId= menu.findItem(R.id.id);
        MenuItem department= menu.findItem(R.id.departmentname);
        // set new title to the MenuItem
        bb.setTitle("Kadir Ak");
        empId.setTitle("Employee ID: "+String.valueOf(EmpID));
        department.setTitle("Order Department");


        // do the same for other MenuItems
        //MenuItem nav_gallery = menu.findItem(R.id.nav_gallery);
        //nav_gallery.setTitle("NewTitleForGallery");

        // add NavigationItemSelectedListener to check the navigation clicks
        navigationView.setNavigationItemSelectedListener(this);
        drawer=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Order");

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        //Order o1 = new Order("", 1, 1, "orderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorderorder", "notenotenote", 305, 2);
        //Order o2 = new Order("", 1, 1, "orderorderorderorderorderorder", "notenotenote", 305, 2);
        orderArrayList = new ArrayList<>();
        //orderArrayList.add(o1);
        //orderArrayList.add(o2);
        adapter = new OrderAdapter(this, orderArrayList);
        rv.setAdapter(adapter);
        tumKelimeler();

        navigationView.setNavigationItemSelectedListener(this);
    }


    public void tumKelimeler() {
        Query SifreSorgu = myRef.orderByChild("valid").equalTo(1);
        SifreSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    order.setOrder_key(d.getKey());

                    orderArrayList.add(order);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    order.setOrder_key(d.getKey());

                    orderArrayList.add(order);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.namesurname){
            Toast.makeText(getApplicationContext(), "lalala", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

