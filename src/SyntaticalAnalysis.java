import java.io.IOException;

public class SyntaticalAnalysis {
	private LexicalAnalysis la;
	private Lexema current;
	
	private Map <String, Variable> variables = new HashMap <String, Variable>(); //ficar esperto !!!!

	public SyntaticalAnalysis(LexicalAnalysis la) throws IOException {
		this.la = la;
		this.current = la.nextToken();
	}

	public void matchToken(TokenType type) throws IOException{
		if(type == current.type){
			current = la.nextToken();
		}
		else{
			ShowError();
		}
	}

	private void unexpectedLexema() {
		// TODO Auto-generated method stub
		
	}

	private void unexpectedEOFError() {
		// TODO Auto-generated method stub
		
	}

	void procVar() throws IOException{
		String varName = current.token();
		matchToken(TokenType.VAR);
		
		Variable v = variables.get(varName);
		if(v==null){
			v=new variable(varName);
			variables.put(varName,v);
		}
		return v;
		
	}

	void procNumber() throws IOException{
		matchToken(TokenType.NUMBER);
	}

	void procString() throws IOException{
		matchToken(TokenType.STRING);
	}

	void procInput() throws IOException{
		matchToken(TokenType.INPUT);
		matchToken(TokenType.PAR_OPEN);
		procText();
		matchToken(TokenType.PAR_CLOSE);
		matchToken(TokenType.DOT_COMMA);
	}

	public void procText() {
		// TODO Auto-generated method stub

	}

	void procIf() throws IOException{
		matchToken(TokenType.IF);
		procBoolExpr();
		procStatements();
		if(current.type == TokenType.ELSE){
			matchToken(TokenType.ELSE);
			procStatements();
		}
		matchToken(TokenType.END);
	}

	void procBoolExpr() {
		// TODO Auto-generated method stub

	}
	
	void start() throws IOException{
		procStatements();
		matchToken(TokenType.END_OF_FILE);		
	}

	void procStatements() throws IOException {
		procStatement();
		while(current.type == TokenType.INPUT
				|| current.type == TokenType.SHOW
				|| current.type == TokenType.VAR
				|| current.type == TokenType.IF
				|| current.type == TokenType.WHILE
				|| current.type == TokenType.FOR){
			procStatement();
		}
	}

	public void procStatement() throws IOException {
		switch (current.type) {
			case INPUT:
				procInput();
				break;
			case SHOW:
				procSHOW();
				break;
			case ASSIGN:
				procASSIGN();
				break;
			case IF:
				procIf();
				break;
			case WHILE:
				procWHILE();
				break;
			case FOR:
				procFOR();
				break;
			default:
				//erro
				break;
		}		
	}

	void procMatrixExpr() throws IOException{
		if(current.type == TokenType.VAR){
			procVar();			
		}else if(current.type == TokenType.BRA_OPEN){
			procGen();
		} else{
			//Erro: lexema invalido //abortar

		}

		while(current.type == TokenType.DOT){
			matchToken(TokenType.DOT);
			if(current.type == TokenType.TRANSPOSED){
				procTRANSPOSED();
				//...
			}else if(current.type == TokenType.MUL){
				procMUL();
			}else{
				//erro
			}
		}
	}

	private void procGen() {
		// TODO Auto-generated method stub

	}

	void procASSIGN() throws IOException {
		matchToken(TokenType.ASSIGN);
	}

	void procPAR_OPEN() throws IOException {
		matchToken(TokenType.PAR_OPEN);
	}

	void procPAR_CLOSE() throws IOException {
		matchToken(TokenType.PAR_CLOSE);
	}

	void procDOT_COMMA() throws IOException {
		matchToken(TokenType.DOT_COMMA);
	}

	void procBRA_OPEN() throws IOException {
		matchToken(TokenType.BRA_OPEN);
	}

	void procBRA_CLOSE() throws IOException {
		matchToken(TokenType.BRA_CLOSE);
	}

	void procDOT() throws IOException {
		matchToken(TokenType.DOT);
	}

	void procRAND() throws IOException {
		matchToken(TokenType.RAND);
	}

	void procCOMMA() throws IOException {
		matchToken(TokenType.COMMA);
	}

	ShowCommand procSHOW() throws IOException {
		matchToken(TokenType.SHOW);
		matchToken(TokenType.PAR_OPEN);
		Value <?> v = procText();
		matchToken(TokenType.PAR_CLOSE);
		matchToken(TokenType.COMMA);
		ShowCommand c = new ShowCommand (v,lex.getLine());
		return c;
	}

	void procFOR() throws IOException {
		matchToken(TokenType.FOR);
	}

	void procSEQ() throws IOException {
		matchToken(TokenType.SEQ);
	}

	void procMINUS() throws IOException {
		matchToken(TokenType.MINUS);
	}

	void procPLUS() throws IOException {
		matchToken(TokenType.PLUS);
	}

	void procTIMES() throws IOException {
		matchToken(TokenType.TIMES);
	}

	void procDIV() throws IOException {
		matchToken(TokenType.DIV);
	}

	void procMOD() throws IOException {
		matchToken(TokenType.MOD);
	}

	void procValue() throws IOException {
		if(current.type == TokenType.VAR){
			procVar();
		}else if(current.type == TokenType.BRA_OPEN){
			procGen();
		}else{
			ShowError();
		}
		
		while(current.type == TokenType.DOT){
			matchToken(TokenType.DOT);
			if(current.type == TokenType.OPPOSED){
				procOpposed();
				//... transposed sum mul
			}else if(current.type == TokenType.SIZE){
				procSize();
				break;
				// rows cols val/value
			}else{
				ShowError();
			}
		}
	}

	public void ShowError() {
		if(current.type == TokenType.UNEXPECTED_EOF){
			unexpectedEOFError();
		}else{
			unexpectedLexema(); // mostrar linha e mensagem
		}
		System.exit(1);
	}

	void procEND() throws IOException {
		matchToken(TokenType.END);
	}

	void procISEQ() throws IOException {
		matchToken(TokenType.ISEQ);
	}

	void procEQUAL() throws IOException {
		matchToken(TokenType.EQUAL);
	}

	void procDIFF() throws IOException {
		matchToken(TokenType.DIFF);
	}

	void procLOWER() throws IOException {
		matchToken(TokenType.LOWER);
	}

	void procLOWER_EQUAL() throws IOException {
		matchToken(TokenType.LOWER_EQUAL);
	}

	void procGREATER() throws IOException {
		matchToken(TokenType.GREATER);
	}

	void procGREATER_EQUAL() throws IOException {
		matchToken(TokenType.GREATER_EQUAL);
	}

	void procAND() throws IOException {
		matchToken(TokenType.AND);
	}

	void procOR() throws IOException {
		matchToken(TokenType.OR);
	}

	void procELSE() throws IOException {
		matchToken(TokenType.ELSE);
	}

	void procWHILE() throws IOException {
		matchToken(TokenType.WHILE);
	}

	void procOpposed() throws IOException {
		matchToken(TokenType.OPPOSED);
	}

	void procTRANSPOSED() throws IOException {
		matchToken(TokenType.TRANSPOSED);
	}

	void procSUM() throws IOException {
		matchToken(TokenType.SUM);
	}

	void procMUL() throws IOException {
		matchToken(TokenType.MUL);
	}

	void procNULL() throws IOException {
		matchToken(TokenType.NULL);
	}

	void procFILL() throws IOException {
		matchToken(TokenType.FILL);
	}

	void procID() throws IOException {
		matchToken(TokenType.ID);
	}

	void procSize() throws IOException {
		matchToken(TokenType.SIZE);
	}

	void procROWS() throws IOException {
		matchToken(TokenType.ROWS);
	}

	void procCOLS() throws IOException {
		matchToken(TokenType.COLS);
	}


}