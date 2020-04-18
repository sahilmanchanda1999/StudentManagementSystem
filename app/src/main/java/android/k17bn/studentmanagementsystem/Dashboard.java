package android.k17bn.studentmanagementsystem;

import android.content.Intent;
import android.graphics.Color;
import android.k17bn.studentmanagementsystem.DashboardItems.AddStudent;
import android.k17bn.studentmanagementsystem.DashboardItems.FindStudent;
import android.k17bn.studentmanagementsystem.DashboardItems.ListStudent;
import android.k17bn.studentmanagementsystem.DashboardItems.RemoveStudent;
import android.k17bn.studentmanagementsystem.DashboardItems.UpdateStudent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    ImageView addbtn,listbtn,updatebtn,removebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_dashboard);

        addbtn=findViewById(R.id.addstudentbtn);
        listbtn=findViewById(R.id.studentlistbtn);
        updatebtn=findViewById(R.id.updatestudentbtn);
        removebtn=findViewById(R.id.removestudentbtn);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, AddStudent.class));
            }
        });
        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ListStudent.class));
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, UpdateStudent.class));
            }
        });
        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, RemoveStudent.class));
            }
        });
        findViewById(R.id.findit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, FindStudent.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
}
