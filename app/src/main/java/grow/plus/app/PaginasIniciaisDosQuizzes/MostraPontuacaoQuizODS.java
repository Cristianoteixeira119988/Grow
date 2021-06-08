package grow.plus.app.PaginasIniciaisDosQuizzes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.Quizzes.QuizOds;
import grow.plus.app.R;
import grow.plus.app.Registar_se;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MostraPontuacaoQuizODS extends AppCompatActivity {


    private static final int REQUEST_CODE_QUIZ = 1;

    private TextView textViewHighscore;

    private int highscore;


    private Button Start;
    private ImageView botaoretroceder;


    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_pontuacao_quiz_ods);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewHighscore = findViewById(R.id.textViewHighScoreQuiz);
        Start = findViewById(R.id.buttonComecarQuiz);
        botaoretroceder = (ImageView) findViewById(R.id.imageViewRetrocederQuiz);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        loadHighscore();

        userID = fAuth.getCurrentUser().getUid();


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
        Intent intent = new Intent(MostraPontuacaoQuizODS.this, QuizOds.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("HighScoreQuizOds").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (requestCode == REQUEST_CODE_QUIZ) {
                    if (resultCode == RESULT_OK) {
                        if (documentSnapshot.exists()) {
                            String highscorestr = documentSnapshot.getString("highScoreQuizOds");
                            highscore = Integer.parseInt(highscorestr);
                            int score = data.getIntExtra(QuizOds.EXTRA_SCORE, 0);
                                if (score > highscore) {
                                    updateHighscore(score);
                                    loadHighscore();
                                }
                        }else if(documentSnapshot.getString("highScoreQuizOds")==null){
                            highscore = 0;
                            int score = data.getIntExtra(QuizOds.EXTRA_SCORE, 0);
                                if (score > highscore) {
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

        DocumentReference documentReference = fStore.collection("HighScoreQuizOds").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    textViewHighscore.setText("HighScore: " + documentSnapshot.getString("highScoreQuizOds"));
                }else if(documentSnapshot.getString("highScoreQuizOds")==null){
                    textViewHighscore.setText("HighScore: 0");
                }
            }
        });
    }


    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;

        userID= fAuth.getCurrentUser().getUid();

        String HighScoreSTR = String.valueOf(highscoreNew);
        DocumentReference documentReference = fStore.collection("HighScoreQuizOds").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("highScoreQuizOds", HighScoreSTR);
        documentReference.set(user);


    }




}