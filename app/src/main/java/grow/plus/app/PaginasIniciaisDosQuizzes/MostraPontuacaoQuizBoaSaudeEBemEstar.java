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

import grow.plus.app.Quizzes.QuizBoaSaudeEBemEstar;

import grow.plus.app.Quizzes.QuizEnergiasRenovaveis;
import grow.plus.app.Quizzes.QuizOds;
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

public class MostraPontuacaoQuizBoaSaudeEBemEstar extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ_SAUDE_DE_QUALIDADE = 1;

    private TextView textViewHighscoreSaudeDeQualidade;

    private int highscoreSaudeDeQualidade;


    private Button Start;
    private ImageView botaoretroceder;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_pontuacao_quiz_boa_saude_e_bem_estar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewHighscoreSaudeDeQualidade = findViewById(R.id.textViewHighScoreQuizSaudeDeQualidade);
        Start = findViewById(R.id.buttonComecarQuizSaudeDeQualidade);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederQuizSaudeDeQualidade);


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
        Intent intent = new Intent(MostraPontuacaoQuizBoaSaudeEBemEstar.this, QuizBoaSaudeEBemEstar.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_SAUDE_DE_QUALIDADE);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("HighScoreQuizSaudeDeQualidade").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (requestCode == REQUEST_CODE_QUIZ_SAUDE_DE_QUALIDADE) {
                    if (resultCode == RESULT_OK) {
                        if (documentSnapshot.exists()) {
                            String highscorestr = documentSnapshot.getString("highScoreQuizSaudeDeQualidade");
                            highscoreSaudeDeQualidade = Integer.parseInt(highscorestr);
                            int score = data.getIntExtra(QuizBoaSaudeEBemEstar.EXTRA_SCORE_SAUDE_DE_QUALIDADE, 0);
                            if (score > highscoreSaudeDeQualidade) {
                                updateHighscore(score);
                                loadHighscore();
                            }
                        } else if (documentSnapshot.getString("highScoreQuizSaudeDeQualidade") == null) {
                            highscoreSaudeDeQualidade = 0;
                            int score = data.getIntExtra(QuizBoaSaudeEBemEstar.EXTRA_SCORE_SAUDE_DE_QUALIDADE, 0);
                            if (score > highscoreSaudeDeQualidade) {
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

        DocumentReference documentReference = fStore.collection("HighScoreQuizSaudeDeQualidade").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textViewHighscoreSaudeDeQualidade.setText("HighScore: " + documentSnapshot.getString("highScoreQuizSaudeDeQualidade"));
                }else if(documentSnapshot.getString("highScoreQuizSaudeDeQualidade")==null){
                    textViewHighscoreSaudeDeQualidade.setText("HighScore: 0");
                }
            }
        });
    }


    private void updateHighscore(int highscoreNew) {
        highscoreSaudeDeQualidade = highscoreNew;

        userID= fAuth.getCurrentUser().getUid();

        String HighScoreSTR = String.valueOf(highscoreNew);
        DocumentReference documentReference = fStore.collection("HighScoreQuizSaudeDeQualidade").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("highScoreQuizSaudeDeQualidade", HighScoreSTR);
        documentReference.set(user);


    }

}