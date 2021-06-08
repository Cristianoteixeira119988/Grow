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

public class QuizBdHelperQuizOds extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase db;

    public QuizBdHelperQuizOds(@Nullable Context context) {
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
        PerguntaQuiz P1 = new PerguntaQuiz("Quantos Objetivos de Desenvolvimento Sustentável (ODS) foram aceites, por todas as Nações do mundo, como parte da Agenda 2030?", "17", "8", "10", "16", 1);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Cada ODS é suportado por um conjunto de metas específicas, objetivos específicos que estão associados a esse Objetivo. Quantas metas existem no total?", "99", "1,016", "169", "51", 3);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("O ODS 1 é sobre a pobreza. Qual é o objetivo?", "Cortar a pobreza pela metade até 2030", " Reduzir a pobreza em 75% até 2030", "Eliminar a pobreza em todas as suas formas e lugares", " Ajudar cada país a progredir na redução da pobreza", 3);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("Qual dos seguintes itens não faz parte do Objetivo 17?", "Mobilizar os recursos financeiros necessários para alcançar os Objetivos", "Promover torneios e festivais desportivos internacionais para promover os Objetivos", " Apoiar os países em desenvolvimento a desenvolver as capacidades de que necessitam em áreas como tecnologia", "Reforçar o comércio, especialmente para ajudar os países em desenvolvimento a aumentar as suas exportações e a dinamizarem as suas economias", 2);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("Na Agenda de 2030, o Objetivo de Desenvolvimento Sustentável 13, sobre ação climática, tem um \"*\" (asterisco). Porquê?", "Porque falar de alterações climáticas é mais importante do que todos os outros objetivos", "É uma nota alertando que nas negociações não foi possível chegar a um acordo sobre as reais metas deste objetivo", " Reconhecer que a Convenção-Quadro das Nações Unidas sobre as Alterações Climáticas é o principal fórum internacional, intergovernamental para negociar a resposta global às alterações climáticas", "Porque o Objetivo sobre as mudanças climáticas está em constante mudança", 3);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Qual dos seguintes objetivos não faz parte dos ODS?", " Acesso a energia sustentável para todos", " Disponibilidade de água e saneamento para todos", "Prestação de serviços de internet para todos", "Promoção de empregos dignos para todos", 3);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Os desafios de igualdade são especificamente mencionados em quantos dos Objetivos de Desenvolvimento Sustentável (sem incluir as metas)?", " Em dois deles: o Objetivo 6 sobre a água e o Objetivo 12 sobre produção e consumo sustentáveis", " Em quatro deles: o Objetivo 2 sobre a fome, o Objetivo 7 sobre energia, o Objetivo 8 sobre crescimento económico e emprego, e o Objetivo 14 sobre a preservação dos oceanos e mares", "Em três deles: Objetivo 4 sobre educação, Objetivo 5 sobre género e Objetivo 10 sobre redução de desigualdade dentro e entre países", "Em um deles: Objetivo 16 sobre a promoção de sociedades pacíficas e justas para todos", 3);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Qual dos seguintes itens não faz parte do Objetivo 15, Proteger a Vida Terrestre?", "Travar e reverter a degradação da terra", "Travar a de perda de biodiversidade", "Integrar os valores dos ecossistemas e da biodiversidade no planeamento nacional e local", "Utilização da biotecnologia", 4);
        AddPerguntas(P8);
        PerguntaQuiz P9 = new PerguntaQuiz("Qual das seguintes afirmações não é verdadeira sobre os ODS?", "Incentivam a promoção da saúde, bem-estar e educação para todos, em todas as idades", "Promovem explicitamente a inovação", "Incluem o desenvolvimento de cidades, infraestrutura e indústria sustentáveis", "São um tratado internacional juridicamente vinculativo que todas as nações devem seguir", 4);
        AddPerguntas(P9);
        PerguntaQuiz P10 = new PerguntaQuiz("O que podemos fazer para apoiar a concretização dos Objetivos de Desenvolvimento Sustentáve?", "Manter os governos e o setor privado responsáveis e apoiar organizações da sociedade civil", "Desenvolver projetos e parcerias e participar nas iniciativas existentes para a concretização de um ou mais objetivos", "Usar o meu papel na sociedade - como professores, decisores políticos, consumidores… - para apoiar os ODS, tomar decisões que promovam os Objetivos e promover ações para implementar os Objetivos", "Tudo acima ... e mais", 4);
        AddPerguntas(P10);
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
