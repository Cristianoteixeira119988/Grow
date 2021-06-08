package grow.plus.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import grow.plus.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuPassWord extends AppCompatActivity {

    private ImageView botaoretroceder;
    private Button botaoRedefinir;
    private EditText memail;
    private ImageView concluido;

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_pass_word);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederRedefinirPassword);
        botaoRedefinir = (Button) findViewById(R.id.buttonEnviarEmail);
        memail = (EditText)  findViewById(R.id.editTextTextEmailParaRedefinirPassword);
        concluido = (ImageView) findViewById(R.id.IconConcluido);

        fAuth = FirebaseAuth.getInstance();

        botaoretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botaoRedefinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = memail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    memail.setError(getString(R.string.e_requerido_um_email));
                    return;
                }
                fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EsqueceuPassWord.this, R.string.foi_enviado_linl_para_redefinir_password, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EsqueceuPassWord.this, R.string.erro, Toast.LENGTH_SHORT).show();
                    }
                });
                concluido.setVisibility(View.VISIBLE);
                botaoRedefinir.setVisibility(View.INVISIBLE);


            }
        });


    }
}