package grow.plus.app.BasesDeDadosDasPerguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import grow.plus.app.Perguntas.PerguntaVerdadeiroFalso;
import grow.plus.app.QuizBd.QuizBdQuizVerdadeiroFalso;

import java.util.ArrayList;
import java.util.List;

public class QuizBdHelperQuizMonumentos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizMonumentos.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizMonumentos(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db= db;
        final String SQL_CREATE_TABELA_PERGUNTAS = " CREATE TABLE " +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas.NOME_DA_TABELA + "(" +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_PERGUNTA + " TEXT, " +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO1 + " TEXT, " +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO2 + " TEXT, " +
                QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_RESPOSTA_NR + " INTEGER " +
                ")";

            db.execSQL(SQL_CREATE_TABELA_PERGUNTAS);
            PreencherTabelaPerguntas();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuizBdQuizVerdadeiroFalso.TabelaPerguntas.NOME_DA_TABELA);
        onCreate(db);
    }

    public void PreencherTabelaPerguntas(){
        PerguntaVerdadeiroFalso P1 = new PerguntaVerdadeiroFalso("A torre Eiffel tem o mesmo nome do seu criador?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P1);
        PerguntaVerdadeiroFalso P2 = new PerguntaVerdadeiroFalso("A Torre de Pisa situa-se na França?", "Falso", "Verdadeiro", 1);
        AddPerguntas(P2);
        PerguntaVerdadeiroFalso P3 = new PerguntaVerdadeiroFalso("A maior montanha da Europa Ocidental situa-se no Monte Evereste?", "Falso", "Verdadeiro", 1);
        AddPerguntas(P3);
        PerguntaVerdadeiroFalso P4 = new PerguntaVerdadeiroFalso("A estátua de bronze de D. Pedro I, erguida em homenagem á proclamação da independência situa-se na praça Tiradentes do Rio de Janeiro?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P4);
        PerguntaVerdadeiroFalso P5 = new PerguntaVerdadeiroFalso("O famoso marco construiu Napoleão Bonaparte depois da sua vitória na batalha de Austerlitzem 1805 foi o Arco do Triunfo?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P5);
        PerguntaVerdadeiroFalso P6 = new PerguntaVerdadeiroFalso("Um marco muito famoso em Londres é o Big Ben, mas o que realmente é o Big Ben? Um relógio?", "Falso", "Verdadeiro", 1);
        AddPerguntas(P6);
        PerguntaVerdadeiroFalso P7 = new PerguntaVerdadeiroFalso("A catedral de Notre-Dame situa-se em Londres, Inglaterra?", "Falso", "Verdadeiro", 1);
        AddPerguntas(P7);
        PerguntaVerdadeiroFalso P8 = new PerguntaVerdadeiroFalso("O prédio da Alfândega situa-se em Recife, Brasil?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P8);
        PerguntaVerdadeiroFalso P9 = new PerguntaVerdadeiroFalso("Victória Falls são umas cataratas assombrosas que estão localizadas entre a fronteira da Zâmbia e o Zimbábue?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P9);
        PerguntaVerdadeiroFalso P10 = new PerguntaVerdadeiroFalso("O “Anfiteatro Flaviano” é o nome correto do famoso Coliseu?", "Falso", "Verdadeiro", 2);
        AddPerguntas(P10);
    }

    public void AddPerguntas(PerguntaVerdadeiroFalso pergunta){
        ContentValues cv = new ContentValues();
        cv.put(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_PERGUNTA, pergunta.getPergunta());
        cv.put(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO1, pergunta.getOpcao1());
        cv.put(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO2, pergunta.getOpcao2());
        cv.put(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_RESPOSTA_NR, pergunta.getRespostaNr());
        db.insert(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.NOME_DA_TABELA, null, cv);
    }

    public List<PerguntaVerdadeiroFalso>getAllPerguntas(){
        List<PerguntaVerdadeiroFalso> perguntaListMonumentos = new ArrayList<>();
        db= getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizBdQuizVerdadeiroFalso.TabelaPerguntas.NOME_DA_TABELA, null);

        if (c.moveToFirst()){
            do{
                PerguntaVerdadeiroFalso pergunta =  new PerguntaVerdadeiroFalso();
                pergunta.setPergunta(c.getString(c.getColumnIndex(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_PERGUNTA)));
                pergunta.setOpcao1(c.getString(c.getColumnIndex(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO1)));
                pergunta.setOpcao2(c.getString(c.getColumnIndex(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_OPCAO2)));
                pergunta.setRespostaNr(c.getInt(c.getColumnIndex(QuizBdQuizVerdadeiroFalso.TabelaPerguntas.COLUNA_RESPOSTA_NR)));
                perguntaListMonumentos.add(pergunta);

            }while (c.moveToNext());
        }

        c.close();
        return perguntaListMonumentos;
    }
}
