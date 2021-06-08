package grow.plus.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class Registar_se extends AppCompatActivity {



    private ImageView botaoretroceder;
    private TextView TextLogin;
    private Button botaoregistar;
    private EditText mnomedoutilizador;
    private EditText memail;
    private EditText mpassword;
    private EditText mconfirmepassword;
    private ProgressBar progressBar;

    FirebaseFirestore fStore;
    String userID;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_se);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederRegistar);
        TextLogin = (TextView) findViewById(R.id.textViewLoginRegistar);
        botaoregistar = (Button) findViewById(R.id.buttonRegistar);
        mnomedoutilizador = (EditText) findViewById(R.id.editTextTextNomeDoUtilizador);
        memail = (EditText) findViewById(R.id.editTextTextEmailRegistar);
        mpassword = (EditText) findViewById(R.id.editTextTextPasswordRegistar);
        mconfirmepassword = (EditText) findViewById(R.id.editTextTextRepetirPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBarRegistar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }



        botaoregistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaConta();
            }
        });

        TextLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraactivity = new Intent(Registar_se.this, Login.class);
                startActivity(outraactivity);
            }
        });

        botaoretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    public void criaConta(){

        final String fullName = mnomedoutilizador.getText().toString();
        final String email = memail.getText().toString().trim();
        String password = mpassword.getText().toString().trim();
        String confirmarPassword = mconfirmepassword.getText().toString().trim();

        //Validações

        if (TextUtils.isEmpty(fullName)) {
            mnomedoutilizador.setError(getString(R.string.e_requerido_um_nome_de_utilizador));
            return;
        }
        if (TextUtils.isEmpty(email)) {
            memail.setError(getString(R.string.e_requerido_um_email));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mpassword.setError(getString(R.string.e_requerido_uma_password));
            return;
        }
        if (TextUtils.isEmpty(confirmarPassword)) {
            mconfirmepassword.setError(getString(R.string.confirme_password));
            return;
        }
        if (password.length() < 6) {
            mpassword.setError(getString(R.string.a_password_tem_de_ter_mais_de_6_caracters));
            return;
        }
        if(password.equals(confirmarPassword)){
        }else {
            mconfirmepassword.setError(getString(R.string.as_passwords_nao_coincidem));
            return;
        }



        botaoregistar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        //registar o utilizador na firebase

        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Verificação de email
                if (task.isSuccessful()) {
                    FirebaseUser fuser = fAuth.getCurrentUser();

                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Registar_se.this, R.string.foi_enviado_um_email_de_verificacao, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registar_se.this, getString(R.string.erro), Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(Registar_se.this, R.string.usuario_criado, Toast.LENGTH_SHORT).show();


                    userID= fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection(getString(R.string.utilizadores)).document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("displayName", fullName);
                    user.put("email", email);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Registar_se.this, getString(R.string.usuario) + userID, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registar_se.this, getString(R.string.erro) , Toast.LENGTH_SHORT).show();
                        }
                    });



                    startActivity(new Intent(getApplicationContext(), CarregarImagem.class));
                } else {
                    Toast.makeText(Registar_se.this, getString(R.string.erro) + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    botaoregistar.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
