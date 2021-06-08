package grow.plus.app.QuizBd;

import android.provider.BaseColumns;

public class QuizBdQuizVerdadeiroFalso {

    private QuizBdQuizVerdadeiroFalso(){

    }
    public static class TabelaPerguntas implements BaseColumns {
        public static final String NOME_DA_TABELA = "quiz_perguntas";
        public static final String COLUNA_PERGUNTA = "pergunta";
        public static final String COLUNA_OPCAO1 = "opcao1";
        public static final String COLUNA_OPCAO2 = "opcao2";
        public static final String COLUNA_RESPOSTA_NR = "resposta_nr";
    }
}
