package grow.plus.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import grow.plus.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditarConta extends AppCompatActivity {


    static int REQUESCODE = 1000 ;
    ImageView imagemDePerfil;

    private ImageView retroceder;
    private Button cancelar;
    private Button guardar;
    private EditText nomeDoUtilizador;
    private EditText email;
    private TextView redefinirpassword;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    StorageReference storageReference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);


        retroceder = (ImageView) findViewById(R.id.imageViewRetrocederEditarConta);
        imagemDePerfil=(ImageView) findViewById(R.id.imageDePerfil);
        storageReference = FirebaseStorage.getInstance().getReference();
        cancelar=(Button) findViewById(R.id.buttonCancelar);
        guardar=(Button) findViewById(R.id.buttonGuardar);
        nomeDoUtilizador=(EditText) findViewById(R.id.editTextTextPersonName);
        email=(EditText) findViewById(R.id.editTextEmailEditarConta);
        redefinirpassword=(TextView) findViewById(R.id.textViewRedefinirPasswordEditarConta);


        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        StorageReference profileRef = storageReference.child("Utilizadores/"+fAuth.getCurrentUser().getUid()+"profileImage.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(imagemDePerfil);
            }
        });


        DocumentReference documentReference = fStore.collection("Utilizadores").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    nomeDoUtilizador.setText(documentSnapshot.getString("displayName"));
                    email.setText(documentSnapshot.getString("email"));
                }
            }
        });

        redefinirpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraactivity = new Intent(EditarConta.this, EsqueceuPassWord.class);
                startActivity(outraactivity);
            }
        });
        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        imagemDePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir galeria
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,REQUESCODE);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardaDadosNovos();
                finish();
            }
        });

    }
    private void uploadImagem(Uri imageUri){

        storageReference = FirebaseStorage.getInstance().getReference();

        final StorageReference fileRef = storageReference.child("Utilizadores/"+fAuth.getCurrentUser().getUid()+"profileImage.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imagemDePerfil);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarConta.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardaDadosNovos(){
        final String novoNomeDeUtilizador = nomeDoUtilizador.getText().toString();
        String novoEmail = email.getText().toString();

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Utilizadores").document(userID);

        Map<String, Object> user = new HashMap<>();
        user.put("displayName", novoNomeDeUtilizador);
        user.put("email", novoEmail);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditarConta.this, R.string.dados_guardados_com_sucesso, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarConta.this, getString(R.string.erro) , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                final Uri imageUri = data.getData();
                imagemDePerfil.setImageURI(imageUri);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadImagem(imageUri);
                        finish();
                    }
                });

            }
        }
    }

}