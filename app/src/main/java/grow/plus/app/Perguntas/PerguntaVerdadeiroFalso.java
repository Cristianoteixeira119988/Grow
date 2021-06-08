package grow.plus.app.Perguntas;

public class PerguntaVerdadeiroFalso {

    private String pergunta;
    private String opcao1;
    private String opcao2;
    private int respostaNr;

    public PerguntaVerdadeiroFalso() {

    }
    public PerguntaVerdadeiroFalso(String pergunta, String opcao1, String opcao2, int respostaNr) {
        this.pergunta = pergunta;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.respostaNr = respostaNr;

    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public int getRespostaNr() {
        return respostaNr;
    }

    public void setRespostaNr(int respostaNr) {
        this.respostaNr = respostaNr;
    }
}
