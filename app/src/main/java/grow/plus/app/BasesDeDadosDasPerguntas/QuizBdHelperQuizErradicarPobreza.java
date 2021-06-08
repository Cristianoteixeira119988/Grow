package grow.plus.app.BasesDeDadosDasPerguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import grow.plus.app.Perguntas.PerguntaQuiz;
import grow.plus.app.QuizBd.QuizBd;


import java.util.ArrayList;
import java.util.List;

public class QuizBdHelperQuizErradicarPobreza extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizErradicarPobreza.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizErradicarPobreza(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db= db;
        final String SQL_CREATE_TABELA_PERGUNTAS = " CREATE TABLE " +
                QuizBd.TabelaPerguntas.NOME_DA_TABELA + "(" +
                QuizBd.TabelaPerguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizBd.TabelaPerguntas.COLUNA_PERGUNTA + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO1 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO2 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO3 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO4 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_TABELA_PERGUNTAS);
        PreencherTabelaPerguntas();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuizBd.TabelaPerguntas.NOME_DA_TABELA);
        onCreate(db);
    }

    public void PreencherTabelaPerguntas(){
        PerguntaQuiz P1 = new PerguntaQuiz("Qual o foco do objetivo numero 1 dos ODS?", "Erradicar a pobreza extrema em todos os lugares", "Garantir que todas as meninas e meninos completam o ensino primário e secundário que deve ser de acesso livre", "Eliminar as disparidades de género na educação e garantir a igualdade de acesso a todos os níveis de educação e formação profissional para os mais vulneráveis", "Construir e melhorar instalações físicas para educação", 1);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Uma das metas até 2030 do ODS numero 1 é?", "Adotar políticas, especialmente ao nível fiscal, salarial e de proteção social, e alcançar progressivamente uma maior igualdade", "Reduzir pelo menos para metade a proporção de homens, mulheres e crianças, de todas as idades, que vivem na pobreza, em todas as suas dimensões", "Garantir uma repartição justa e equitativa dos benefícios derivados da utilização dos recursos genéticos e promover o acesso adequado aos recursos genéticos", "Sustentar o crescimento económico per capita de acordo com as circunstâncias nacionais", 2);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("Qual destes países precisa de uma maior ajuda para atingir este objetivo (Objetivo 1) dos ODS?", "Ilhas Salomão (Oceania)", "Guiné (África)", "Burundi (África)", "Todos estes precisam", 4);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("Quantas pessoas vivem em situação de probreza no mundo?", "Mais de 2,2 bilhões", "Mais de 5,2 bilhões ", "Mais de 8,2 bilhões ", "Mais de 1,2 bilhões ", 1);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("O que pode fazer para ajudar a aintigir este objetivo? (Objetivo 1 dos ODS)", "Voluntariado e Doações", "Arranjar um trabalho", "Deitar comiga fora", "Fazer viagens pelo mundo", 1);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Quantas pessoas têm crise de fome crônica?", "240 milhões", "431 milhões", "842 milhões", "144 milhões", 3);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Qual destes nomes ajudou mais a combater a probreza no mundo?", "Papa João Paulo II", "Madre Teresa de Calcutá", "Papa Bento V", "Papa João XIII", 2);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Qual destas afirmações é verdadeira?", "A maioria das pessoas que vive abaixo do Limiar Internacional da Pobreza vive em duas regiões: a Europa e o Norte da América","183 milhões de pessoas vivem abaixo do Limiar Internacional da Pobreza de 1 dólar por dia" , "As baixas taxas de pobreza são frequentemente encontradas em países pequenos, frágeis e afetados por conflitos.", "Em 2016, quase 10% dos trabalhadores e famílias viviam com menos de 1,90 dólares por pessoa por dia", 4);
        AddPerguntas(P8);
    }

    public void AddPerguntas(PerguntaQuiz pergunta){
        ContentValues cv = new ContentValues();
        cv.put(QuizBd.TabelaPerguntas.COLUNA_PERGUNTA, pergunta.getPergunta());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO1, pergunta.getOpcao1());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO2, pergunta.getOpcao2());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO3, pergunta.getOpcao3());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO4, pergunta.getOpcao4());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR, pergunta.getRespostaNr());
        db.insert(QuizBd.TabelaPerguntas.NOME_DA_TABELA, null, cv);
    }

    public List<PerguntaQuiz>getAllPerguntas(){
        List<PerguntaQuiz> perguntaList = new ArrayList<>();
        db= getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizBd.TabelaPerguntas.NOME_DA_TABELA, null);

        if (c.moveToFirst()){
            do{
                PerguntaQuiz pergunta =  new PerguntaQuiz();
                pergunta.setPergunta(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_PERGUNTA)));
                pergunta.setOpcao1(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO1)));
                pergunta.setOpcao2(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO2)));
                pergunta.setOpcao3(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO3)));
                pergunta.setOpcao4(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO4)));
                pergunta.setRespostaNr(c.getInt(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR)));
                perguntaList.add(pergunta);

            }while (c.moveToNext());
        }

        c.close();
        return perguntaList;
    }
}
