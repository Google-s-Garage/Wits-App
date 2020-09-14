package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class AccomTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accom_types);

        //set tge status bar backgound to transparent

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //setup the recyclerView using the AccomAdapter

        RecyclerView recyclerView = findViewById(R.id.accom_rv_list);
        List<accom_item> mList = new ArrayList<>(); //list that stores our accomodations

        mList.add(new accom_item(R.drawable.born_free,R.drawable.born_free_logo,4200,"The Verge1"));
        mList.add(new accom_item(R.drawable.dunwell,R.drawable.dunwell_logo,3400,"Dunwell"));
        mList.add(new accom_item(R.drawable.junction,R.drawable.wits_junction_logo,4650,"Wits Junction"));
        mList.add(new accom_item(R.drawable.kingsway_place,R.drawable.kingsway_place_logo,4500,"Kingsway Place"));
        mList.add(new accom_item(R.drawable.lapalaka,R.drawable.lapalaka_logo,4200,"Lapalaka"));
        mList.add(new accom_item(R.drawable.one,R.drawable.j_one_logo,3800,"J-One"));
        mList.add(new accom_item(R.drawable.richmond,R.drawable.richmond_logo,4500,"The RichMond"));
        mList.add(new accom_item(R.drawable.south_point,R.drawable.south_point_logo,4700,"South Point"));
        mList.add(new accom_item(R.drawable.student_digz,R.drawable.student_digzz_logo,3500,"Student Digz"));

        AccomAdapter accomAdapter = new AccomAdapter(this,mList);
        recyclerView.setAdapter(accomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}