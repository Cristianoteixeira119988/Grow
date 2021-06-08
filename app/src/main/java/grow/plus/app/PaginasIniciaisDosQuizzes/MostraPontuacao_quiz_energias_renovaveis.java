package grow.plus.app.PaginasIniciaisDosQuizzes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import grow.plus.app.Quizzes.QuizEnergiasRenovaveis;
import grow.plus.app.Quizzes.QuizEstatuas;
import grow.plus.app.Quizzes.QuizObjetivoErradicarPobreza;
import grow.plus.app.Quizzes.QuizOds;
import grow.plus.app.R;

public class MostraPontuacao_quiz_energias_renovaveis extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ_ENERGIAS_RENOVAVEIS = 1;

    private TextView textViewHighscoreEnergiasRenovaveis;

    private int highscoreEnergiasRenovaveis;



    private Button Start;
    private ImageView botaoretroceder;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_pontuacao_quiz_energias_renovaveis);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewHighscoreEnergiasRenovaveis = findViewById(R.id.textViewHighScoreQuizEnergiasRenovaveis);
        Start = findViewById(R.id.buttonComecarQuizEnergiasRenovaveis);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederQuizEnergiasRenovaveis);

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
        Intent intent = new Intent(MostraPontuacao_quiz_energias_renovaveis.this, QuizEnergiasRenovaveis.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_ENERGIAS_RENOVAVEIS);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("HighScoreEnergiasRenovaveis").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (requestCode == REQUEST_CODE_QUIZ_ENERGIAS_RENOVAVEIS) {
                    if (resultCode == RESULT_OK) {
                        if (documentSnapshot.exists()) {
                            String highscorestr = documentSnapshot.getString("highScoreEnergiasRenovaveis");
                            highscoreEnergiasRenovaveis = Integer.parseInt(highscorestr);
                            int score = data.getIntExtra(QuizEnergiasRenovaveis.EXTRA_SCORE_ENERGIAS_RENOVAVEIS, 0);
                            if (score > highscoreEnergiasRenovaveis) {
                                updateHighscore(score);
                                loadHighscore();
                            }
                        }else if(documentSnapshot.getString("highScoreEnergiasRenovaveis")==null||Integer.parseInt(documentSnapshot.getString("highScoreEnergiasRenovaveis"))==0){
                            highscoreEnergiasRenovaveis = 0;
                            int score = data.getIntExtra(QuizEnergiasRenovaveis.EXTRA_SCORE_ENERGIAS_RENOVAVEIS, 0);
                            if (score > highscoreEnergiasRenovaveis) {
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

        DocumentReference documentReference = fStore.collection("HighScoreEnergiasRenovaveis").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textViewHighscoreEnergiasRenovaveis.setText("HighScore: " + documentSnapshot.getString("highScoreEnergiasRenovaveis"));
                }else if(documentSnapshot.getString("highScoreEnergiasRenovaveis")==null||Integer.parseInt(documentSnapshot.getString("highScoreEnergiasRenovaveis"))==0){
                    textViewHighscoreEnergiasRenovaveis.setText("HighScore: 0");
                }
            }
        });
    }


    private void updateHighscore(int highscoreNew) {
        highscoreEnergiasRenovaveis = highscoreNew;

        userID= fAuth.getCurrentUser().getUid();

        String HighScoreSTR = String.valueOf(highscoreNew);
        DocumentReference documentReference = fStore.collection("HighScoreEnergiasRenovaveis").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("highScoreEnergiasRenovaveis", HighScoreSTR);
        documentReference.set(user);


    }


}