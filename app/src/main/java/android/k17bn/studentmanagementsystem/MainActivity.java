/*Q2: Create a android app for “Student Management System” for student details. In which you have to use the concept of either SQLite Database or Room Database. Avoid directly copying the code from any student/website.. Solution must be unique for each student.*/

package android.k17bn.studentmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        x=sharedPreferences.getString("logged","");
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(x.equals("yes"))
                {
                    startActivity(new Intent(MainActivity.this,Dashboard.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));

                }
            }
        },1);
    }
}
