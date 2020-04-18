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

public class AddStudent extends AppCompatActivity {

    Dialog dialog;
    ImageView popclose,negpopclose;
    Button posbtn,negbtn;
    TextView postext,negtext;

    EditText ereg,ename,eEmail,ephone,estream;
    Button addbtn;
    StudentDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_add_student);


        dialog=new Dialog(this);
        
        db=new StudentDatabaseHelper(this);

        ereg=findViewById(R.id.regedit);
        ename=findViewById(R.id.nameedit);
        eEmail=findViewById(R.id.emailedit);
        ephone=findViewById(R.id.phoneedit);
        estream=findViewById(R.id.streamedit);
        addbtn=findViewById(R.id.insertbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg = ereg.getText().toString().trim();
                String name = ename.getText().toString().trim();
                String email = eEmail.getText().toString().trim();
                String phone = ephone.getText().toString().trim();
                String stream = estream.getText().toString().trim();

                if(reg.isEmpty())
                    ereg.setError("Required");
                if(name.isEmpty())
                    ename.setError("Required");
                if(email.isEmpty())
                    eEmail.setError("Required");
                if(phone.isEmpty())
                    ephone.setError("Required");
                if(stream.isEmpty())
                    estream.setError("Required");

                if(!reg.isEmpty() && !name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !stream.isEmpty() ) {


                    boolean already = db.checkUser(reg);
                    if (already) {
                        ShowNegPop("Student Already Added");
                    } else {
                        boolean isInserted = db.insertData(reg, name, email, phone, stream);
                        if (isInserted) {
                            ShowPosPop("Student Added!!");
                        } else {
                            ShowNegPop("Insertion Error!");
                        }
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
