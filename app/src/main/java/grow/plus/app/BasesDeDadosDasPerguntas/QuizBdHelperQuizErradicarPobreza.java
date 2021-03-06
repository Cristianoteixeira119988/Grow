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
        PerguntaQuiz P1 = new PerguntaQuiz("Qual o foco do objetivo numero 1 dos ODS?", "Erradicar a pobreza extrema em todos os lugares", "Garantir que todas as meninas e meninos completam o ensino prima??rio e secunda??rio que deve ser de acesso livre", "Eliminar as disparidades de ge??nero na educac??a??o e garantir a igualdade de acesso a todos os ni??veis de educac??a??o e formac??a??o profissional para os mais vulnera??veis", "Construir e melhorar instalac??o??es fi??sicas para educac??a??o", 1);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Uma das metas at?? 2030 do ODS numero 1 ???", "Adotar pol??ticas, especialmente ao n??vel fiscal, salarial e de prote????o social, e alcan??ar progressivamente uma maior igualdade", "Reduzir pelo menos para metade a proporc??a??o de homens, mulheres e crianc??as, de todas as idades, que vivem na pobreza, em todas as suas dimenso??es", "Garantir uma reparti????o justa e equitativa dos benef??cios derivados da utiliza????o dos recursos gen??ticos e promover o acesso adequado aos recursos gen??ticos", "Sustentar o crescimento econo??mico per capita de acordo com as circunsta??ncias nacionais", 2);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("Qual destes pa??ses precisa de uma maior ajuda para atingir este objetivo (Objetivo 1) dos ODS?", "Ilhas Salom??o (Oceania)", "Guin?? (??frica)", "Burundi (??frica)", "Todos estes precisam", 4);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("Quantas pessoas vivem em situa????o de probreza no mundo?", "Mais de 2,2 bilh??es", "Mais de 5,2 bilh??es ", "Mais de 8,2 bilh??es ", "Mais de 1,2 bilh??es ", 1);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("O que pode fazer para ajudar a aintigir este objetivo? (Objetivo 1 dos ODS)", "Voluntariado e Doa????es", "Arranjar um trabalho", "Deitar comiga fora", "Fazer viagens pelo mundo", 1);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Quantas pessoas t??m crise de fome cr??nica?", "240 milh??es", "431 milh??es", "842 milh??es", "144 milh??es", 3);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Qual destes nomes ajudou mais a combater a probreza no mundo?", "Papa Jo??o Paulo II", "Madre Teresa de Calcut??", "Papa Bento V", "Papa Jo??o XIII", 2);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Qual destas afirma????es ?? verdadeira?", "A maioria das pessoas que vive abaixo do Limiar Internacional da Pobreza vive em duas regi??es: a Europa e o Norte da Am??rica","183 milh??es de pessoas vivem abaixo do Limiar Internacional da Pobreza de 1 d??lar por dia" , "As baixas taxas de pobreza s??o frequentemente encontradas em pa??ses pequenos, fr??geis e afetados por conflitos.", "Em 2016, quase 10% dos trabalhadores e fam??lias viviam com menos de 1,90 d??lares por pessoa por dia", 4);
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
