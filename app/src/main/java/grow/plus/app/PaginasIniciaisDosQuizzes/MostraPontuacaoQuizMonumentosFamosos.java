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
import grow.plus.app.Quizzes.QuizHistoriaDoMundo;
import grow.plus.app.Quizzes.QuizVerdadeiroFalsoMonumentos;
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

public class MostraPontuacaoQuizMonumentosFamosos extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ_MONUMENTOS = 1;



    private TextView textViewHighscoreMonumentos;

    private int highscoreMonumentos;


    private Button Start;
    private ImageView botaoretroceder;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_pontuacao_quiz_monumentos_famosos);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewHighscoreMonumentos = findViewById(R.id.textViewHighScoreQuizMonumentos);
        Start = findViewById(R.id.buttonComecarQuizMonumentos);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederQuizMonumentos);

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
        Intent intent = new Intent(MostraPontuacaoQuizMonumentosFamosos.this, QuizVerdadeiroFalsoMonumentos.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ_MONUMENTOS);
    }

   @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       userID = fAuth.getCurrentUser().getUid();

       DocumentReference documentReference = fStore.collection("HighScoreMonumentos").document(userID);
       documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
               if (requestCode == REQUEST_CODE_QUIZ_MONUMENTOS) {
                   if (resultCode == RESULT_OK) {
                       if (documentSnapshot.exists()) {
                           String highscorestr = documentSnapshot.getString("highScoreMonumentos");
                           highscoreMonumentos = Integer.parseInt(highscorestr);
                           int score = data.getIntExtra(QuizVerdadeiroFalsoMonumentos.EXTRA_SCORE_MONUMENTOS, 0);
                           if (score > highscoreMonumentos) {
                               updateHighscore(score);
                               loadHighscore();
                           }
                       }else if(documentSnapshot.getString("highScoreMonumentos")==null){
                           highscoreMonumentos = 0;
                           int score = data.getIntExtra(QuizVerdadeiroFalsoMonumentos.EXTRA_SCORE_MONUMENTOS, 0);
                           if (score > highscoreMonumentos) {
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

        DocumentReference documentReference = fStore.collection("HighScoreMonumentos").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textViewHighscoreMonumentos.setText("HighScore: " + documentSnapshot.getString("highScoreMonumentos"));
                }else if(documentSnapshot.getString("highScoreMonumentos")==null){
                    textViewHighscoreMonumentos.setText("HighScore: 0");
                }
            }
        });
    }


    private void updateHighscore(int highscoreNew) {
        highscoreMonumentos = highscoreNew;

        userID= fAuth.getCurrentUser().getUid();

        String HighScoreSTR = String.valueOf(highscoreNew);
        DocumentReference documentReference = fStore.collection("HighScoreMonumentos").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("highScoreMonumentos", HighScoreSTR);
        documentReference.set(user);


    }

}