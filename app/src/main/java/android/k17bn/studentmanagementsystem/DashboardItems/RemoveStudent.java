package android.k17bn.studentmanagementsystem.DashboardItems;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.k17bn.studentmanagementsystem.R;
import android.k17bn.studentmanagementsystem.StudentDatabaseHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RemoveStudent extends AppCompatActivity {
    Dialog dialog;
    ImageView popclose,negpopclose;
    Button posbtn,negbtn;
    TextView postext,negtext;

    EditText rereg;
    Button removebtn;
    StudentDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_remove_student);
        dialog=new Dialog(this);
        db=new StudentDatabaseHelper(this);
        rereg=findViewById(R.id.removeedit);
        removebtn=findViewById(R.id.removebtn);
        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reg = rereg.getText().toString().trim();
                boolean already = db.checkUser(reg);
                if (!already) {
                    ShowNegPop("Student Not Found!");
                } else {
                    Integer deletedRows = db.deleteData(reg);
                    if (deletedRows > 0)
                        ShowPosPop("Student Deleted!");
                    else
                        ShowNegPop("Deletion Error");
                }
            }
        });

    }
    private void ShowNegPop(String message) {
        dialog.setContentView(R.layout.popup_neg);
        negpopclose=dialog.findViewById(R.id.popnegclose);
        negbtn=dialog.findViewById(R.id.negbtn);
        negbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        negtext=dialog.findViewById(R.id.negtext);
        negtext.setText(message);

        negpopclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void ShowPosPop(String message) {
        dialog.setContentView(R.layout.popup_pos);
        popclose=dialog.findViewById(R.id.posclose);
        posbtn=dialog.findViewById(R.id.posbtn);
        posbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        postext=dialog.findViewById(R.id.postext);
        postext.setText(message);
        popclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
