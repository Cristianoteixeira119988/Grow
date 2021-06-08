package grow.plus.app.ui.Conta;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.BemVindo;
import grow.plus.app.EditarConta;
import grow.plus.app.Login;
import grow.plus.app.R;
import grow.plus.app.SplashActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class fragment_conta extends Fragment {


    private TextView user;
    private TextView email;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    ImageView imagemDePerfil;
    StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conta, container, false);

        user = view.findViewById(R.id.textViewNomeUtilizadorConta);
        email = view.findViewById(R.id.textViewEmailConta);
        imagemDePerfil = view.findViewById(R.id.imageViewPerfilConta);


        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Utilizadores/"+fAuth.getCurrentUser().getUid()+"profileImage.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imagemDePerfil);
            }
        });




            view.findViewById(R.id.buttonLogoutConta).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getContext(), getString(R.string.logout_efetuado_com_sucesso), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            });

            view.findViewById(R.id.buttonEditarConta).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent outraactivity = new Intent(getActivity(), EditarConta.class);
                    startActivity(outraactivity);
                }
            });

        DocumentReference documentReference = fStore.collection("Utilizadores").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    user.setText(documentSnapshot.getString("displayName"));
                    email.setText(documentSnapshot.getString("email"));
                }
            }
        });


        return view;
    }



}

