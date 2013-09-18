package br.ufpb.aps.jogo.controle;

import br.ufpb.aps.jogo.entidade.Dado;
import br.ufpb.aps.jogo.entidade.Questao;
import br.ufpb.aps.jogo.entidade.Surpresa;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;

public class Tabuleiro implements Surpresa {

	private String tabuleiro[] = new String[] { null, null, null, null,
			null };
	private Dado dado = new Dado();
	private Questionario questionario = new Questionario();
	private GerentePersonagem gp = new GerentePersonagem();

	private boolean contemSurpresa = false;
	private String respostaPersonagem = "";
	private int score;
	private boolean responder = false;
	private boolean resultado;
	private boolean personagemXDefinido;
	private boolean iniciouJogo = false;
	private boolean proximaJogadaX;

	public void setEscolhaPersonagemX(boolean b) {
		if (iniciouJogo) {
			throw new ExcecaoJogoTabuleiro("O jogo ja foi iniciado!");
		}
		this.personagemXDefinido = true;
		this.proximaJogadaX = b;
	}

	public boolean isEscolhaPersonagemX() {
		return proximaJogadaX;
	}

	public void setRespostaPersonagemX(String alternativa) {

		if (!alternativaValida(alternativa)) {
			throw new ExcecaoJogoTabuleiro("Alternativa invalida!");
		}
		if (!isResponder()) {
			throw new ExcecaoJogoTabuleiro(
					"Nao pode responder antes da pergunta ser exibida!");
		}
		if (alternativa.equals(questionario.getQuestao().getGabarito())) {
			resultado = true;
			moverPersonagemXNoTabuleiro();

		} else {

			resultado = false;
		}
		adicionarPontuacao(resultado);

		this.respostaPersonagem = alternativa;
	}

	public void setRespostaPersonagemY(String alternativa) {

		if (!alternativaValida(alternativa)) {
			throw new ExcecaoJogoTabuleiro("Alternativa invalida!");
		}
		if (!isResponder()) {
			throw new ExcecaoJogoTabuleiro(
					"Nao pode responder antes da pergunta ser exibida!");
		}
		if (alternativa.equals(questionario.getQuestao().getGabarito())) {
			resultado = true;
			moverPersonagemYNoTabuleiro();

		} else {

			resultado = false;
		}
		adicionarPontuacao(resultado);

		this.respostaPersonagem = alternativa;
	}

	public int jogarDado() {

		if (acabou()) {
			throw new ExcecaoJogoTabuleiro("O jogo ja foi acabado!");
		}

		if (!personagemXDefinido) {
			throw new ExcecaoJogoTabuleiro(" O Personagem nao foi definido!");
		}

		iniciouJogo = true;
		return dado.lancarDado();

	}

	// falta corrigir esse metodo
	public boolean verificarPersonagemNoTabuleiro() {
		String personagem = tabuleiro[getPosicaoPersonagemX()];

		if (personagem == null) {
			throw new ExcecaoJogoTabuleiro("Personagem nulo!");
		}
		if (personagem == "X") {
			return true;
		}
		return false;

	}

	private void moverPersonagemXNoTabuleiro() {
		String escolha = "X";

		removePosicao(getPosicaoPersonagemX());
		setAvancarPersonagemX();
		insereNaPosicao(getPosicaoPersonagemX(), escolha);

		 //mostrarTabuleiro();
		proximaJogadaX = !proximaJogadaX;
	}

	private void moverPersonagemYNoTabuleiro() {
		String escolha = "Y";

		removePosicao(getPosicaoPersonagemY());
		setAvancarPersonagemY();
		insereNaPosicao(getPosicaoPersonagemY(), escolha);
		// mostrarTabuleiro();
		proximaJogadaX = !proximaJogadaX;
	}

	private void setAvancarPersonagemX() {
		gp.getPersonagem().setPosicaoX(dado.getValorDoDado());
	}

	private void setAvancarPersonagemY() {
		gp.getPersonagem().setPosicaoY(dado.getValorDoDado());
	}

	public int getPosicaoPersonagemX() {
		return gp.getPersonagem().getPosicaoX();
	}

	public int getPosicaoPersonagemY() {
		return gp.getPersonagem().getPosicaoY();
	}

	// falta fazer para personagem Y
	public String getRespostaPersonagemX() {
		return respostaPersonagem;
	}

	public int getScore() {

		return score;
	}

	public void setScore(int score) {
		if (score < 0) {
			throw new ExcecaoJogoTabuleiro("Valor irregular no score!");
		}
		this.score = score;
	}

	// corrigir os numeros magicos
	private void adicionarPontuacao(boolean resultado) {
		if (resultado == true)

			score += 3;
		if (getScore() != 0 && resultado == false) {
			score -= 1;
		}
		if (getScore() == 0 && resultado == false)
			return;
	}

	public boolean alternativaValida(String alternativa) {
		boolean result = false;
		if ((alternativa.equals("a") || alternativa.equals("b"))
				|| alternativa.equals("c")) {
			result = true;
		}
		return result;
	}

	public boolean isResponder() {
		return responder;
	}

	public void setResponder(boolean responder) {
		this.responder = responder;
	}

	public boolean isResultadoQuestao() {
		return resultado;
	}

	public boolean acabou() {
		int size = tabuleiro.length;
		if (gp.getPersonagem().getPosicaoX() == size - 1) {
			return true;
		}
		return false;
	}

	public int getValorDoDado() {
		return dado.getValorDoDado();
	}

	public void casaSurpresa() {
		contemSurpresa = true;
	}

	public boolean isSurpresa() {
		return contemSurpresa;
	}

	public void surpresaBoa() {
		gp.getPersonagem().setPosicaoX(1);
	}

	public void surpresaRuim() {
		gp.getPersonagem().setPosicaoX(-1);
	}

	public Questao criarQuestao(Questao q) {
		if (getValorDoDado() == 0) {
			throw new ExcecaoJogoTabuleiro(
					"Questao nao pode ser exibida antes de lancar o dado!");
		}
		setResponder(true);
		questionario.getQuestao().setGabarito(q.getGabarito());
		questionario.criarQuestao(q);
		return q;
	}

	// metodo so para visualizacao do tabuleiro,ele nao ficara no projeto
	/*public void mostrarTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				System.out.println(tabuleiro[i][j]);
			}

		}
		System.out.println();
	}*/

	public void insereNaPosicao(int posicao, String elemento) {
		tabuleiro[posicao] = elemento;
	}

	public void removePosicao(int posicao) {
		tabuleiro[posicao] = null;

	}
	// [[0,0],[1,1],[2,2]]
}
