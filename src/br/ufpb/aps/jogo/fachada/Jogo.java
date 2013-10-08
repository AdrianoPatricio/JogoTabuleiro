package br.ufpb.aps.jogo.fachada;

import java.util.List;

import br.ufpb.aps.jogo.controle.GerenteJogador;
import br.ufpb.aps.jogo.controle.GerentePersonagem;
import br.ufpb.aps.jogo.controle.Questionario;
import br.ufpb.aps.jogo.controle.Tabuleiro;
import br.ufpb.aps.jogo.entidade.Dado;
import br.ufpb.aps.jogo.entidade.Jogador;
import br.ufpb.aps.jogo.entidade.Personagem;
import br.ufpb.aps.jogo.entidade.Questao;

/**
 * Essa classe sera a fachada do jogo.
 * 
 * @authors Jonathas Firmo, Adriano Patricio and Lucas Cruz
 */
public class Jogo {

	private Questionario questionario = new Questionario();
	private GerentePersonagem gerentePersonagem = new GerentePersonagem();
	private Tabuleiro tabuleiro = new Tabuleiro();
	private GerenteJogador gerenteJogador = new GerenteJogador();

	public void cadastrarJogador(Jogador jogador) {
		gerenteJogador.cadastrarJogador(jogador);
	}

	public List<Jogador> listarJogador() {
		return gerenteJogador.listarJogadores();
	}

	public void removerJogador(Jogador jogador) {
		gerenteJogador.removerJogador(jogador);
	}

	public Questao criarQuestao(Questao questao) {
		return tabuleiro.criarQuestao(questao);
	}

	public void criarDado(Dado d) {
		tabuleiro.criarDado(d);
	}

	public void cadastrarQuestao(Questao questao) {
		questionario.cadastrarQuestao(questao);
	}

	public List<Questao> listarQuestoes() {
		return questionario.mostrarQuestoes();
	}

	public void removerQuestao(Questao questao) {
		questionario.removerQuestao(questao);
	}

	public Questao alterarQuestao(Questao questao) {
		return questionario.alterarQuestao(questao);
	}

	public int jogarDado() {
		return tabuleiro.jogarDado();

	}

	public int getValorDoDado() {
		return tabuleiro.getValorDoDado();
	}

	/*public void setValorDado(int valor) {
		tabuleiro.setValorDado(valor);
	}*/

	public Personagem getPersonagem() {
		return gerentePersonagem.getPersonagem();
	}

	public void criarPersonagem() {
		Personagem p = new Personagem();
		p.setNome(getNomeJogador());
		gerentePersonagem.adicionarPersonagem(p);
	}

	public boolean isEscolhaPersonagemX() {
		return tabuleiro.isEscolhaPersonagemX();
	}

	public void setEscolhaPersonagemX(boolean b) {
		tabuleiro.setEscolhaPersonagemX(b);
	}

	// s� trabalha com o personagem X
	public int getPosicaoPersonagemX() {
		return tabuleiro.getPosicaoPersonagemX();
	}
	public int getPosicaoPersonagemY(){
		return tabuleiro.getPosicaoPersonagemY();
	}

	
	public boolean verificarPersonagemXNoTabuleiro() {
		return tabuleiro.verificarPersonagemXNoTabuleiro();
	}

	public boolean verificarPersonagemYNoTabuleiro() {
		return tabuleiro.verificarPersonagemYNoTabuleiro();
	}

	public String getRespostaPersonagemX() {
		return tabuleiro.getRespostaPersonagemX();
	}

	public void setRespostaPersonagemX(String alternativa) {

		tabuleiro.setRespostaPersonagemX(alternativa);
	}
	
	public void setRespostaPersonagemY(String alternativa) {

		tabuleiro.setRespostaPersonagemY(alternativa);
	}

	public boolean acabou() {
		return tabuleiro.acabou();
	}

	public boolean isResultadoQuestao() {
		return tabuleiro.isResultadoQuestao();
	}

	public int verificarScore() {
		return tabuleiro.valorDoScore();
	}

	public boolean isSurpresa() {
		return tabuleiro.isSurpresa();
	}

	public void casaSurpresa() {
		tabuleiro.casaSurpresa();
	}

	public void surpresaBoa() {
		tabuleiro.surpresaBoa();
	}

	public void surpresaRuim() {
		tabuleiro.surpresaRuim();
	}

	public boolean alternativaValida(String alternativa) {
		return tabuleiro.alternativaValida(alternativa);
	}

	public void setNomeJogador(String nome) {
		gerentePersonagem.alterarNome(nome);
	}

	public String getNomeJogador() {
		return gerentePersonagem.obterNome();
	}

	public boolean encerrarAntesDoTempo() {
		return true;
	}

	public int getTamanhoNome() {
		return gerentePersonagem.getTamanhoNome();

	}

}
