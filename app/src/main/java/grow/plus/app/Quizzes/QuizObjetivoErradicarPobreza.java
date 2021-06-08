package grow.plus.app.Quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import grow.plus.app.BasesDeDadosDasPerguntas.QuizBdHelperQuizErradicarPobreza;
import grow.plus.app.Perguntas.PerguntaQuiz;
import grow.plus.app.R;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizObjetivoErradicarPobreza extends AppCompatActivity {

    public static final String EXTRA_SCORE_ERRADICAR_POBREZA = "extraScoreErradicarPobreza";

    //tempo decescente para desponder á pergunta
    private static final long COUNTDOWN_IN_MILLIS = 60000;

    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private TextView textViewTempo;
    private TextView textViewNrPergunta;
    private TextView textViewScore;
    private TextView textViewPergunta;
    private RadioGroup rbGroup;
    private Button opcao1;
    private Button opcao2;
    private Button opcao3;
    private Button opcao4;
    private Button confirmar;

    private ColorStateList textColorDefault;


    private List<PerguntaQuiz> perguntaList;

    private int perguntaCounter;
    private int perguntaCountTotal;

    private PerguntaQuiz currentPergunta;

    private int score;
    private boolean respostas;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_objetivo_erradicar_pobreza);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        textViewNrPergunta = findViewById(R.id.textViewPerguntaNrErradicarPobreza);
        textViewScore = findViewById(R.id.textViewScoreErradicarPobreza);
        textViewPergunta = findViewById(R.id.textViewPerguntaErradicarPobreza);
        textViewTempo = findViewById(R.id.textViewTempoErradicarPobreza);
        opcao1 = findViewById(R.id.radioButtonOpcao1ErradicarPobreza);
        opcao2 = findViewById(R.id.radioButtonOpcao2ErradicarPobreza);
        opcao3 = findViewById(R.id.radioButtonOpcao3ErradicarPobreza);
        opcao4 = findViewById(R.id.radioButtonOpcao4ErradicarPobreza);
        confirmar = findViewById(R.id.buttonConfirmarErradicarPobreza);
        rbGroup = findViewById(R.id.radioGroupErradicarPobreza);

        textColorDefault=opcao1.getTextColors();
        textColorDefaultCd=textViewTempo.getTextColors();

        QuizBdHelperQuizErradicarPobreza bdHelper = new QuizBdHelperQuizErradicarPobreza(this);
        perguntaList = bdHelper.getAllPerguntas();
        perguntaCountTotal= 6;
        Collections.shuffle(perguntaList);

        showProximaPergunta();

        //selecinar uma opçao para poder confirmar
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!respostas){
                    if(opcao1.isClickable() ||  opcao2.isClickable() || opcao3.isClickable() || opcao4.isClickable()){
                        checkResposta();
                    } else {
                        Toast.makeText(QuizObjetivoErradicarPobreza.this, R.string.por_davor_selecionar_uma_opcao, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showProximaPergunta();
                }
            }
        });

    }

    //funcao checar resposta
    private void checkResposta(){

        countDownTimer.cancel();//para o tempo
        respostas = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int perguntaNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (perguntaNr == currentPergunta.getRespostaNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }

    //mostrar a resposta correta
    private void showSolution() {
        switch (currentPergunta.getRespostaNr()) {
            case 1:
                opcao1.setTextColor(Color.parseColor("#008D09"));
                textViewPergunta.setText("A resposta 1 era a correta");
                break;
            case 2:
                opcao2.setTextColor(Color.parseColor("#008D09"));
                textViewPergunta.setText("A resposta 2 era a correta");
                break;
            case 3:
                opcao3.setTextColor(Color.parseColor("#008D09"));
                textViewPergunta.setText("A resposta 3 era a correta");
                break;
            case 4:
                opcao4.setTextColor(Color.parseColor("#008D09"));
                textViewPergunta.setText("A resposta 4 era a correta");
                break;
        }
        if (perguntaCounter < perguntaCountTotal) {
            confirmar.setText(R.string.avancar);
        } else {
            confirmar.setText(R.string.terminar);
        }
    }

    //mostrar proxima pergunta
    private void showProximaPergunta(){
        opcao1.setTextColor(textColorDefault);
        opcao2.setTextColor(textColorDefault);
        opcao3.setTextColor(textColorDefault);
        opcao4.setTextColor(textColorDefault);
        rbGroup.clearCheck();

        if(perguntaCounter < perguntaCountTotal){
            currentPergunta = perguntaList.get(perguntaCounter);
            textViewPergunta.setText(currentPergunta.getPergunta());
            opcao1.setText(currentPergunta.getOpcao1());
            opcao2.setText(currentPergunta.getOpcao2());
            opcao3.setText(currentPergunta.getOpcao3());
            opcao4.setText(currentPergunta.getOpcao4());
            perguntaCounter++;
            textViewNrPergunta.setText(getString(R.string.pergunta) + perguntaCounter + "/" + perguntaCountTotal);
            respostas = false;
            confirmar.setText(R.string.confirmar);

            //Comecar a contar o tempo decrescente
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkResposta();
            }
        }.start();
    }
    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewTempo.setText(timeFormatted);
        //quando o tempo estiver a acabar é apresentado cor vermelha
        if (timeLeftInMillis < 10000) {
            Toast.makeText(this, R.string.o_tempo_esta_a_terminar, Toast.LENGTH_SHORT).show();
            textViewTempo.setTextColor(Color.RED);
        } else if(timeLeftInMillis==00000) {
            Toast.makeText(this, R.string.tempo_esgotado, Toast.LENGTH_SHORT).show();
        }else {
            textViewTempo.setTextColor(textColorDefaultCd);
        }
    }


    //Envia valor do score total
    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE_ERRADICAR_POBREZA, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    //função primir 2 vezes para sair do quiz
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, R.string.Presione_de_novo_para_sair, Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}