package grow.plus.app.ui.HighScores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizBoaSaudeEBemEstar;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizErradicarPobreza;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizEstatuas;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizHistoriaDoMundo;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizMonumentosFamosos;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizODS;
import grow.plus.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class HighScores extends Fragment {


    private TextView highScoreQuizOds;
    private TextView HighScoreQuizMonumentos;
    private TextView HighScoreQuizErradicarPobreza;
    private TextView HighScoreQuizHistoriaDoMundo;
    private TextView HighScoreQuizSaudeEBemEstar;
    private TextView HighScoreQuizQuisEstatuas;
    private TextView HighScoreQuizEnergiasRenovaveis;
    private Button apagar;


    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_highscores, container, false);

        highScoreQuizOds = view.findViewById(R.id.textViewHighScoreQuizOds);
        HighScoreQuizMonumentos = view.findViewById(R.id.textViewHighScoreQuizMonumentos);
        HighScoreQuizErradicarPobreza = view.findViewById(R.id.textViewHighScoreQuizErradicarPobreza);
        HighScoreQuizHistoriaDoMundo = view.findViewById(R.id.textViewHighScoreQuizHistoriaDoMundo);
        HighScoreQuizSaudeEBemEstar = view.findViewById(R.id.textViewHighScoreQuizBoaSaudeEBemEstar);
        HighScoreQuizQuisEstatuas = view.findViewById(R.id.textViewHighScoreQuizEstatuasFamosas);
        HighScoreQuizEnergiasRenovaveis = view.findViewById(R.id.textViewHighScoreQuizEnergiasRenovaveis);
        apagar = view.findViewById(R.id.buttonApagarTodos);


        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("HighScoreQuizOds").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    highScoreQuizOds.setText(getString(R.string.objetivos_de_desenvolvimento_sustent_vel)  +": "+ documentSnapshot.getString("highScoreQuizOds") +"/6" );
                }else if (documentSnapshot.getString("highScoreQuizOds")==null){
                    highScoreQuizOds.setText(getString(R.string.objetivos_de_desenvolvimento_sustent_vel)  +": 0/6");
                }
            }
        });
        DocumentReference documentReference1 = fStore.collection("HighScoreEnergiasRenovaveis").document(userID);
        documentReference1.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizEnergiasRenovaveis.setText(getString(R.string.objetivo_de_ter_energias_renov_veis_e_acess_veis)  +": "+ documentSnapshot.getString("highScoreEnergiasRenovaveis") +"/6" );
                }else if (documentSnapshot.getString("highScoreEnergiasRenovaveis")==null){
                    HighScoreQuizEnergiasRenovaveis.setText(getString(R.string.objetivo_de_ter_energias_renov_veis_e_acess_veis)  +": 0/6");
                }
            }
        });
        DocumentReference documentReference2 = fStore.collection("HighScoreQuizSaudeDeQualidade").document(userID);
        documentReference2.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizSaudeEBemEstar.setText(getString(R.string.objetivo_de_ter_boa_sa_de_e_bem_estar)  +": "+ documentSnapshot.getString("highScoreQuizSaudeDeQualidade") +"/6" );
                }else if (documentSnapshot.getString("highScoreQuizSaudeDeQualidade")==null){
                    HighScoreQuizSaudeEBemEstar.setText(getString(R.string.objetivo_de_ter_boa_sa_de_e_bem_estar)  +": 0/6");
                }
            }
        });
        DocumentReference documentReference3 = fStore.collection("HighScoreErradicarProbreza").document(userID);
        documentReference3.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizErradicarPobreza.setText(getString(R.string.objetivo_de_erradicar_a_pobreza)  +": "+ documentSnapshot.getString("highScoreErradicarProbreza") +"/6" );
                }else if (documentSnapshot.getString("highScoreErradicarProbreza")==null){
                    HighScoreQuizErradicarPobreza.setText(getString(R.string.objetivo_de_erradicar_a_pobreza)  +": 0/6");
                }
            }
        });


        DocumentReference documentReference4 = fStore.collection("HighScoreEstatuas").document(userID);
        documentReference4.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizQuisEstatuas.setText(getString(R.string.est_tuas_famosas)  +": "+ documentSnapshot.getString("highScoreEstatuas") +"/6" );
                }else if (documentSnapshot.getString("highScoreEstatuas")==null){
                    HighScoreQuizQuisEstatuas.setText(getString(R.string.est_tuas_famosas)  +": 0/6");
                }
            }
        });

        DocumentReference documentReference5 = fStore.collection("HighScoreHistoriaDoMundo").document(userID);
        documentReference5.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizHistoriaDoMundo.setText(getString(R.string.hist_ria_do_mundo)  +": "+ documentSnapshot.getString("highScoreHistoriaDoMundo") +"/6" );
                }else if (documentSnapshot.getString("highScoreHistoriaDoMundo")==null||documentSnapshot==null){
                    HighScoreQuizHistoriaDoMundo.setText(getString(R.string.hist_ria_do_mundo)  +": 0/6");
                }
            }
        });

        DocumentReference documentReference6 = fStore.collection("HighScoreMonumentos").document(userID);
        documentReference6.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    HighScoreQuizMonumentos.setText(getString(R.string.monumentos_famosos)  +": "+ documentSnapshot.getString("highScoreMonumentos") +"/6" );
                }else if (documentSnapshot.getString("highScoreMonumentos")==null){
                    HighScoreQuizMonumentos.setText(getString(R.string.monumentos_famosos)  +": 0/6");
                }
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = fAuth.getCurrentUser().getUid();

                String HighScore = "0";
                DocumentReference documentReference7 = fStore.collection("HighScoreQuizOds").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("highScoreQuizOds", HighScore);
                documentReference7.set(user);

                DocumentReference documentReference8 = fStore.collection("HighScoreEnergiasRenovaveis").document(userID);
                Map<String, Object> user1 = new HashMap<>();
                user1.put("highScoreEnergiasRenovaveis", HighScore);
                documentReference8.set(user1);

                DocumentReference documentReference9 = fStore.collection("HighScoreQuizSaudeDeQualidade").document(userID);
                Map<String, Object> user2 = new HashMap<>();
                user2.put("highScoreQuizSaudeDeQualidade", HighScore);
                documentReference9.set(user2);

                DocumentReference documentReference10 = fStore.collection("HighScoreErradicarProbreza").document(userID);
                Map<String, Object> user3 = new HashMap<>();
                user3.put("highScoreErradicarProbreza", HighScore);
                documentReference10.set(user3);

                DocumentReference documentReference11 = fStore.collection("HighScoreEstatuas").document(userID);
                Map<String, Object> user4 = new HashMap<>();
                user4.put("highScoreEstatuas", HighScore);
                documentReference11.set(user4);


                DocumentReference documentReference12 = fStore.collection("HighScoreHistoriaDoMundo").document(userID);
                Map<String, Object> user5 = new HashMap<>();
                user5.put("highScoreHistoriaDoMundo", HighScore);
                documentReference12.set(user5);

                DocumentReference documentReference13 = fStore.collection("HighScoreMonumentos").document(userID);
                Map<String, Object> user6 = new HashMap<>();
                user6.put("highScoreMonumentos", HighScore);
                documentReference13.set(user6);
            }


        });




        return view;


    }

}