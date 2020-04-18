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

public class UpdateStudent extends AppCompatActivity {
    Dialog dialog;
    ImageView popclose,negpopclose;
    Button posbtn,negbtn;
    TextView postext,negtext;

    EditText uereg,uename,ueEmail,uephone,uestream;
    Button updatebtn;
    StudentDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_update_student);
        dialog=new Dialog(this);

        db=new StudentDatabaseHelper(this);

        uereg=findViewById(R.id.uregedit);
        uename=findViewById(R.id.unameedit);
        ueEmail=findViewById(R.id.uemailedit);
        uephone=findViewById(R.id.uphoneedit);
        uestream=findViewById(R.id.ustreamedit);
        updatebtn=findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reg = uereg.getText().toString().trim();
                String name = uename.getText().toString().trim();
                String email = ueEmail.getText().toString().trim();
                String phone = uephone.getText().toString().trim();
                String stream = uestream.getText().toString().trim();

                if(reg.isEmpty())
                    uereg.setError("Required");
                if(name.isEmpty())
                    uename.setError("Required");
                if(email.isEmpty())
                    ueEmail.setError("Required");
                if(phone.isEmpty())
                    uephone.setError("Required");
                if(stream.isEmpty())
                    uestream.setError("Required");

                if(!reg.isEmpty() && !name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !stream.isEmpty() ) {
                    boolean already = db.checkUser(reg);
                    if (!already) {
                        ShowNegPop("Student Not Found!");
                    } else {

                        boolean isUpdated = db.updateData(reg, name, email, phone, stream);

                        if (isUpdated)
                            ShowPosPop("Student Data Updated!");
                        else
                            ShowNegPop("Deletion Error!");
                    }

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
