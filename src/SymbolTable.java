import java.util.HashMap;

public class SymbolTable{
	private HashMap<String, TokenType> st = new HashMap<>();

	public boolean contains(String lex) {
		return st.containsKey(lex);
	}

	public TokenType getSymbol(String lex){
		if(st.containsKey(lex)){
			return st.get(lex);
		}else{
			return TokenType.INVALID_LEX;
		}
	}

	public SymbolTable(){
		st.put("input", TokenType.INPUT);
		st.put("(", TokenType.PAR_OPEN);
		st.put(")", TokenType.PAR_CLOSE);
		st.put("[", TokenType.BRA_OPEN);
		st.put("]", TokenType.BRA_CLOSE);
		st.put("=", TokenType.ASSIGN); 
		st.put("input", TokenType.INPUT); 
		st.put(";", TokenType.DOT_COMMA); 
		st.put(".", TokenType.DOT);
		st.put("rand", TokenType.RAND); 
		st.put(",", TokenType.COMMA); 
		st.put("show", TokenType.SHOW); 
		st.put("for", TokenType.FOR); 
		st.put("seq", TokenType.SEQ); 
		st.put("minus", TokenType.MINUS); 
		st.put("plus", TokenType.PLUS); 
		st.put("times", TokenType.TIMES);
		st.put("/", TokenType.DIV); 
		st.put("%", TokenType.MOD); 
		st.put("value", TokenType.VALUE); 
		st.put("end", TokenType.END); 
		st.put("iseq", TokenType.ISEQ); 
		st.put("=", TokenType.EQUAL); 
		st.put("!=", TokenType.DIFF); 
		st.put("<", TokenType.LOWER); 
		st.put("<=", TokenType.LOWER_EQUAL);
		st.put(">", TokenType.GREATER); 
		st.put(">=", TokenType.GREATER_EQUAL); 
		st.put("&", TokenType.AND); 
		st.put("|", TokenType.OR); 
		st.put("if", TokenType.IF); 
		st.put("else", TokenType.ELSE); 
		st.put("while", TokenType.WHILE); 
		st.put("opposed", TokenType.OPPOSED); 
		st.put("transposed", TokenType.TRANSPOSED);
		st.put("sum", TokenType.SUM); 
		st.put("mul", TokenType.MUL); 
		st.put("null", TokenType.NULL); 
		st.put("fill", TokenType.FILL); 
		st.put("id", TokenType.ID); 
		st.put("size", TokenType.SIZE); 
		st.put("rows", TokenType.ROWS); 
		st.put("cols", TokenType.COLS);
	}
}