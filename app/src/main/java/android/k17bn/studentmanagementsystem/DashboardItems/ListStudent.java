package android.k17bn.studentmanagementsystem.DashboardItems;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.k17bn.studentmanagementsystem.R;
import android.k17bn.studentmanagementsystem.StudentDatabaseHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListStudent extends AppCompatActivity {

    StudentDatabaseHelper db;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_list_student);

        db=new StudentDatabaseHelper(this);
        textView=findViewById(R.id.listText);
        viewAll();
    }

    public void viewAll() {

        Cursor res=db.getAllData();
        if(res.getCount()==0){
            Toast.makeText(this, "NO Record Found!", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Reg :" +res.getString(1)+"\n");
            buffer.append("Name :" +res.getString(2)+"\n");
            buffer.append("Email :" +res.getString(3)+"\n");
            buffer.append("Phone :" +res.getString(4)+"\n");
            buffer.append("Stream :" +res.getString(5)+"\n");
            buffer.append("\n\n\n");


        }
        textView.setText(buffer.toString());

    }

}
