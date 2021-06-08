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

public class QuizBdHelperQuizHistoriaDoMundo extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizHistoriaDoMundo.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizHistoriaDoMundo(@Nullable Context context) {
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
        PerguntaQuiz P1 = new PerguntaQuiz("Qual dos acontecimentos seguintes foi pioneiro da Revolução Francesa?", "Queda da Bastilha", "Crise econômica", "Primavera dos povos", "Fim da era Napoleônica", 1);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Em que data começou a primeira guerra mundial?", "11 de novembro de 1918", "28 de julho de 1914", "10 de janeiro de 1910", "1 de outubro de 1900",2);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("Em que ano se iniciou a segunda guerra mundial", "1939", "1945", "1942", "1935", 1);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("Que acontecimento inicou a segunda guerra mundial?", "Derrota dos Impérios Centrais", "Tentativa de derrubar o governo alemão", "Invasão da frança pela Alemanha", "Invasão da Polônia pela Alemanha", 4);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("Qual data marca o fim da segunda guerra mundial?", "25 de novembro de 1944", "10 de outubro de 1943", "2 de setembro de 1945", "1 de dezembro de 1940", 3);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Quem descubriu a américa?", "Cristóvão Colombo", "Pedro Álvares Cabral", "Vasco da Gama", "Luís de Camões", 1);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Qual o país que lançou o primeiro satélite no espaço?", "União das Repúblicas Socialistas Soviéticas", "Inglaterra", "Estados Unidos", "China", 1);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Qual ocidental foi o primeiro a explorar a China?", "Sócrates", "Balboa", "Marco Polo", "Platão", 3);
        AddPerguntas(P8);
        PerguntaQuiz P9 = new PerguntaQuiz("Qual das alternativas abaixo NÃO faz parte das Sete Maravilhas do Mundo?", "Grande Pirâmide de Giza", "Estátua da Liberdade", "Estátua de Zeus em Olímpia", "Colosso de Rodes", 2);
        AddPerguntas(P9);
        PerguntaQuiz P10 = new PerguntaQuiz("Quem conquistou o Egito em 30 A.C?", "Romanos", "Chineses", "Ingleses", "Canadenses", 1);
        AddPerguntas(P10);
        PerguntaQuiz P11 = new PerguntaQuiz("1", "1", "2", "3", "3", 1);
        AddPerguntas(P11);
        PerguntaQuiz P12 = new PerguntaQuiz("2", "1", "2", "3", "3", 1);
        AddPerguntas(P12);
        PerguntaQuiz P13 = new PerguntaQuiz("3", "1", "2", "3", "3", 1);
        AddPerguntas(P13);
        PerguntaQuiz P14 = new PerguntaQuiz("4", "1", "2", "3", "3", 1);
        AddPerguntas(P14);
        PerguntaQuiz P15 = new PerguntaQuiz("5", "1", "2", "3", "3", 1);
        AddPerguntas(P15);
        PerguntaQuiz P16 = new PerguntaQuiz("6", "1", "2", "3", "3", 1);
        AddPerguntas(P16);
        PerguntaQuiz P17 = new PerguntaQuiz("7", "1", "2", "3", "3", 1);
        AddPerguntas(P17);
        PerguntaQuiz P18 = new PerguntaQuiz("8", "1", "2", "3", "3", 1);
        AddPerguntas(P18);
        PerguntaQuiz P19 = new PerguntaQuiz("9", "1", "2", "3", "3", 1);
        AddPerguntas(P19);
        PerguntaQuiz P20 = new PerguntaQuiz("10", "1", "2", "3", "3", 1);
        AddPerguntas(P20);
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

    public List<PerguntaQuiz> getAllPerguntas(){
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

