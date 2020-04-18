package android.k17bn.studentmanagementsystem;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {
    Dialog dialog;
    ImageView popclose,negpopclose;
    Button posbtn,negbtn;
    TextView postext,negtext;

    EditText euser,epass;
    FloatingActionButton loginbtn;
    TextView registertxt;
    DatabaseHelper db;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        dialog=new Dialog(this);
        sharedPreferences=getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        db=new DatabaseHelper(this);
        euser=findViewById(R.id.loginuser);
        epass=findViewById(R.id.loginpass);
        loginbtn=findViewById(R.id.loginbtn);
        registertxt=findViewById(R.id.loginregister);
        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = euser.getText().toString().trim();
                String pass = epass.getText().toString().trim();
                if (user.isEmpty())
                    euser.setError("Required");
                if (pass.isEmpty())
                    epass.setError("Required");
                if (!user.isEmpty() && !pass.isEmpty()) {
                    boolean res = db.checkUser(user, pass);
                    if (res) {
                        ShowPosPop("Successfully Logged In!");
                        //editor.putBoolean("logged",true);
                        editor.putString("logged","yes");
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, Dashboard.class));
                    } else {
                        ShowNegPop("Log In Error!");
                        //editor.putBoolean("logged",false);
                        editor.putString("logged","no");
                        editor.commit();
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
