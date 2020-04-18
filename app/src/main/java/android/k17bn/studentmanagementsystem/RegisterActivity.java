package android.k17bn.studentmanagementsystem;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Dialog dialog;
    ImageView popclose,negpopclose;
    Button posbtn,negbtn;
    TextView postext,negtext;

    EditText euser,epass,eCpass;
    FloatingActionButton registerbtn;
    TextView logintxt;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_register);
        dialog=new Dialog(this);
        db=new DatabaseHelper(this);
        euser=findViewById(R.id.registeruser);
        epass=findViewById(R.id.registerpass);
        eCpass=findViewById(R.id.registerCpass);
        registerbtn=findViewById(R.id.registerbtn);
        logintxt=findViewById(R.id.registerlogin);
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });







        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = euser.getText().toString().trim();
                String pass = epass.getText().toString().trim();
                String cPass = eCpass.getText().toString().trim();
                if (user.isEmpty())
                    euser.setError("Required");
                if (pass.isEmpty())
                    epass.setError("Required");
                if (cPass.isEmpty())
                    eCpass.setError("Required");
                if (!user.isEmpty() && !pass.isEmpty() && !cPass.isEmpty()) {


                    if (pass.equals(cPass)) {
                        boolean check = db.checkUser(user, pass);
                        if (check) {
                            ShowNegPop("Student Already Exists!");
                        } else {
                            long val = db.addUser(user, pass);
                            if (val > 0) {
                                ShowPosPop("Successfully Registered");
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                ShowNegPop("Registration Error!");
                            }
                        }
                    } else {

                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }

                }
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
