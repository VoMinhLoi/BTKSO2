package com.example.bktso2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Context context = LoginActivity.this;
    TextView btdk;
    Button btlogin;
    EditText usLI,paLI;
    CheckBox checkBox;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btdk = (TextView) findViewById(R.id.signUpBTLG);
        btlogin = (Button) findViewById(R.id.logInBTLG);
        usLI = (EditText) findViewById(R.id.nameETLG);
        paLI = (EditText) findViewById(R.id.passETLG);
        checkBox = findViewById(R.id.rememberCB);
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        GetDataBySharePreferences();
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }
    public void loginUser() {
        String gmail =usLI.getText().toString();
        String pass = paLI.getText().toString();

        if (TextUtils.isEmpty(gmail)) {
            usLI.setError("Không thể để trống email");
            usLI.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            paLI.setError("Không thể để trống mật khẩu");
            paLI.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(gmail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        RememberUserNamePassWord(gmail, pass);
                        startActivity(new Intent(context, ListCayThuocNam.class));
                    } else {
                        Toast.makeText(context, "Đăng nhập không thành công", Toast.LENGTH_LONG).show();
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());

                    }
                }
            });
        }
    }
    public void RememberUserNamePassWord(String gmail, String pass){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(checkBox.isChecked()){
//            SharedPreferences remember = (SharedPreferences) sharedPreferences.edit();
            editor.putString("gmail", gmail);
            editor.putString("pass",pass);
            editor.putBoolean("remember", true);
            editor.commit();
        }
        else{
            editor.remove("gmail");
            editor.remove("pass");
            editor.remove("remember");
            editor.commit();
        }
    }
    public void GetDataBySharePreferences(){
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        usLI.setText(sharedPreferences.getString("gmail",""));
        paLI.setText(sharedPreferences.getString("pass",""));
        checkBox.setChecked(sharedPreferences.getBoolean("remember",false));
    }
}