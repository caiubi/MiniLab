import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

public class LexicalAnalysis implements AutoCloseable {

	private PushbackInputStream input;
	private SymbolTable st;
	private int line;

	public LexicalAnalysis(String fileName) throws FileNotFoundException{
		input = new PushbackInputStream(new FileInputStream(fileName));
		st = new SymbolTable();
		line = 0;
	}

	@Override
	public void close() throws Exception {
		input.close();
	}
	
	public Lexema nextToken() throws IOException{
		Lexema lex = new Lexema("", TokenType.END_OF_FILE);
		int estado = 1;
		int c;
		
		while(estado != 8){
			c = input.read();
			switch(estado){
				case 1:
					if(Character.isDigit(c)){
						lex.token += c;
						estado = 3;
					}else if(c == '!'){
						lex.token += c;
						estado = 4;						
					}else if(c == '>' || c == '<' || c == '='){
						lex.token += c;
						estado = 5;
					}else if(Character.isLetter(c)){
						lex.token += c;
						estado = 6;					
					}else{
						lex.token += c;
						lex.type = TokenType.INVALID_TOKEN;
						return lex;
					}
					break;
				case 4:
					if(c == '='){
						lex.token += c;
						estado = 8;						
					}else{
						if(c == -1){
							lex.type = TokenType.UNEXPECTED_EOF;
							return lex;
						}else{
							lex.type = TokenType.INVALID_TOKEN;
							return lex;							
						}
					}
					break;
				case 5:
					if(c == '='){
						lex.token += c;
						estado = 8;
					}else if(c != -1){
						input.unread(c);
					}
					estado = 8;
					break;
				case 3:
					if(Character.isDigit(c)){
						lex.token += c;
						estado = 3;
					}else{

						if(c != -1)
							input.unread(c);
						lex.type = TokenType.NUMBER;
						return lex;
					}
					break;

				case 6:
					if(Character.isLetter(c)){
						lex.token += c;
						estado = 6;
					}else{

						if(c != -1)
							input.unread(c);
						estado = 8;
					}
					break;
				case 7:
					if(Character.isLetter(c)){
						lex.token += c;
						estado = 7;
					}else{
						if(c == '"'){
							lex.token += c;
							lex.type = TokenType.STRING;
						}
						if(c != -1)
							input.unread(c);
						estado = 8;
					}
					break;
			}
		}
		
		if(st.contains(lex.token)){
			lex.type = st.getSymbol(lex.token);
		}else{
			lex.type = TokenType.VAR;
		}
		
		return lex;
	}

	public int line() {
		return line;
	}

	
	
}