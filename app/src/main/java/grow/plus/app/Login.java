package grow.plus.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button botaologin;
    private TextView textviewregistar;
    private ImageView botaoretroceder;
    private ProgressBar progessbar;
    private EditText memail;
    private EditText mpassword;
    private TextView redefenirPassword;

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        botaologin=(Button) findViewById(R.id.buttonLoginLogin);
        textviewregistar = (TextView) findViewById(R.id.textViewRegistarLogin);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederLogin);
        progessbar = (ProgressBar) findViewById(R.id.progressBar);
        memail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        mpassword = (EditText) findViewById(R.id.editTextTextPassword);
        redefenirPassword= (TextView) findViewById(R.id.textViewRedefinirPassword);


        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        botaoretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textviewregistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Login.this, Registar_se.class);
                startActivity(outraActivity);
            }
        });

        redefenirPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Login.this, EsqueceuPassWord.class);
                startActivity(outraActivity);
            }
        });

        botaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    memail.setError(getString(R.string.e_requerido_um_email));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError(getString(R.string.e_requerido_uma_password));
                    return;
                }
                if (password.length() < 6) {
                    mpassword.setError(getString(R.string.a_password_tem_de_ter_mais_de_6_caracters));
                    return;
                }



                progessbar.setVisibility(View.VISIBLE);
                botaologin.setVisibility(View.INVISIBLE);

                //Autentificação
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, R.string.login_efetuado_com_sucesso, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, getString(R.string.erro) + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progessbar.setVisibility(View.INVISIBLE);
                            botaologin.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

}