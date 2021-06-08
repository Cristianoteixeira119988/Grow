package grow.plus.app.PaginasIniciaisDosQuizzes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.Quizzes.QuizEnergiasRenovaveis;
import grow.plus.app.Quizzes.QuizEstatuas;
import grow.plus.app.Quizzes.QuizHistoriaDoMundo;
import grow.plus.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MostraPontuacaoQuizHistoriaDoMundo extends AppCompatActivity {


    private static final int REQUEST_CODE_QUIZ_HISTORIA_DO_MUNDO = 1;


    private TextView textViewHighscoreHistoriaDoMundo;

    private int highscoreHistoriaDoMundo;


    private Button Start;
    private ImageView botaoretroceder;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_pontuacao_quiz_historia_do_mundo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewHighscoreHistoriaDoMundo = findViewById(R.id.textViewHighScoreQuizHistoriaDoMundo);
        Start = findViewById(R.id.buttonComecarQuizHistoriaDoMundo);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederQuizHistoriaDoMundo);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        loadHighscore();

        botaoretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });


    }
    private void startQuiz() {
        Intent intent = new Intent(MostraPontuacaoQuizHistoriaDoMundo.this, QuizHistoriaDoMundo.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_HISTORIA_DO_MUNDO);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("HighScoreHistoriaDoMundo").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (requestCode == REQUEST_CODE_QUIZ_HISTORIA_DO_MUNDO) {
                    if (resultCode == RESULT_OK) {
                        if (documentSnapshot.exists()) {
                            String highscorestr = documentSnapshot.getString("highScoreHistoriaDoMundo");
                            highscoreHistoriaDoMundo = Integer.parseInt(highscorestr);
                            int score = data.getIntExtra(QuizHistoriaDoMundo.EXTRA_SCORE_HISTORIA_DO_MUNDO, 0);
                            if (score > highscoreHistoriaDoMundo) {
                                updateHighscore(score);
                                loadHighscore();
                            }
                        }else if(documentSnapshot.getString("highScoreHistoriaDoMundo")==null){
                            highscoreHistoriaDoMundo = 0;
                            int score = data.getIntExtra(QuizHistoriaDoMundo.EXTRA_SCORE_HISTORIA_DO_MUNDO, 0);
                            if (score > highscoreHistoriaDoMundo) {
                                updateHighscore(score);
                                loadHighscore();
                            }
                        }
                    }
                }
            }
        });
    }
    private void loadHighscore() {

        userID= fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("HighScoreHistoriaDoMundo").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textViewHighscoreHistoriaDoMundo.setText("HighScore: " + documentSnapshot.getString("highScoreHistoriaDoMundo"));
                }else if(documentSnapshot.getString("highScoreHistoriaDoMundo")==null){
                    textViewHighscoreHistoriaDoMundo.setText("HighScore: 0");
                }
            }
        });
    }


    private void updateHighscore(int highscoreNew) {
        highscoreHistoriaDoMundo = highscoreNew;

        userID= fAuth.getCurrentUser().getUid();

        String HighScoreSTR = String.valueOf(highscoreNew);
        DocumentReference documentReference = fStore.collection("HighScoreHistoriaDoMundo").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("highScoreHistoriaDoMundo", HighScoreSTR);
        documentReference.set(user);


    }


}
