package trabalhoFinalParadigmas;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Classe LexicalAnalyzer respons�vel pela an�lise l�xica do programa

public class LexicalAnalyzer {
	
		//Constantes para representar as classes dos caracteres
	    private static final int LETTER = 0;//Letra
	    private static final int DIGIT = 1;//D�gito
	    private static final int UNKNOWN = 99;//Caractere desconhecido

	    private BufferedReader reader;//Leitor de arquivo
	    private char nextChar;//Pr�ximo caractere
	    private int charClass;//Classe do caractere (LETRA, DIGITO, DESCONHECIDO)

	    private StringBuilder lexeme;//Armazena o lexema atual
	    private Map<String,Token.TokenType>reservedWords;

	    public Token token;
	    
	    //Construtor da classe LexicalAnalyzer
	    public LexicalAnalyzer(BufferedReader reader) {
	    	token= new Token(null,null);
	        this.reader = reader;
	        lexeme = new StringBuilder();
	        reservedWords = createReservedWordsMap();
	        getChar();
	        
	    }

	    public Token getNextToken() {
	        lexeme.setLength(0);//Limpar o conte�do da lexeme atual

	        getNonBlank();

	        if (charClass == LETTER) {
	            addChar();
	            getChar();
	            while (charClass == LETTER || charClass == DIGIT) {
	                addChar();
	                getChar();
	            }
	            String lexemeStr = lexeme.toString();
	            Token.TokenType
	            reservedWordToken = reservedWords.get(lexemeStr);
	            if(reservedWordToken!=null) {
	            	return new Token(reservedWordToken, lexemeStr);
	            }

	            else {
	            	return new Token(Token.TokenType.IDENT, lexemeStr);
	            }

	        } else if (charClass == DIGIT) {
	            addChar();
	            getChar();
	            while (charClass == DIGIT) {
	                addChar();
	                getChar();
	            }
	            return new Token(Token.TokenType.INT_LIT, lexeme.toString());
	        } else if (nextChar == '\n') {
	        	//se for uma quebra e linha adiciona ao lexema e l� o prox caractere
	            getChar();
	            return new Token(Token.TokenType.NEWLINE, lexeme.toString());
	        } else if (charClass == UNKNOWN) {
	        	//Verificar operadores conhecidos
	            switch (nextChar) {
	                case '=':
	                    getChar();
	                    return new Token(Token.TokenType.ASSIGN_OP, "=");
	                case '+':
	                    getChar();
	                    return new Token(Token.TokenType.ADD_OP, "+");
	                case '-':
	                    getChar();
	                    return new Token(Token.TokenType.SUB_OP, "-");
	                case '*':
	                    getChar();
	                    return new Token(Token.TokenType.MULT_OP, "*");
	                case '/':
	                    getChar();
	                    return new Token(Token.TokenType.DIV_OP, "/");
	                case '(':
	                    getChar();
	                    return new Token(Token.TokenType.LEFT_PAREN, "(");
	                case ')':
	                    getChar();
	                    return new Token(Token.TokenType.RIGHT_PAREN, ")");
	                case '.':
	                	getChar();
	                	return new Token(Token.TokenType.PONT, ".");
	                case ';':
	                    getChar();
	                    return new Token(Token.TokenType.PONTVIRG, ";");
	                case '\"':
	                	getChar();
	                	return new Token(Token.TokenType.ASP, "\"");
	                default:
	                	//Caractere desconhecido
	                    getChar();
	                    return null;//ou lance uma exce��o, dependendo do que voc� deseja fazer

	            }
	        } else if (charClass == Token.TokenType.EOF.ordinal()) {
	        	//Fim do arquivo de entrada
	            return new Token(Token.TokenType.EOF, "");
	        } else {
	            return null;//ou lance uma exce��o, dependendo do que voc� deseja fazer

	        }
	    }
		//L� o pr�ximo caractere do arquivo
	    private void getChar() {
	        try {
	            int nextInt = reader.read();
	            if (nextInt != -1) {
	                nextChar = (char) nextInt;
	                if (Character.isLetter(nextChar)) {
	                    charClass = LETTER;
	                } else if (Character.isDigit(nextChar)) {
	                    charClass = DIGIT;
	                } else {
	                    charClass = UNKNOWN;
	                }
	            } else {
	                charClass = Token.TokenType.EOF.ordinal();
	            }
	        } catch (IOException e) {
	            charClass = Token.TokenType.EOF.ordinal();
	        }
	    }
	    
	    //Adiciona o caractere atual ao lexema
	    private void addChar() {
	        lexeme.append(nextChar);
	    }

	    //Ignora caracteres em branco
	    private void getNonBlank() {
	        while(Character.isWhitespace(nextChar)) {
	        	getChar();
	        
	        }
	        }
	    
	    
	    private Map<String,Token.TokenType> createReservedWordsMap() {
	        Map<String, Token.TokenType> reservedWords = new HashMap<>();

	        reservedWords.put("if", Token.TokenType.IF);
	        reservedWords.put("else", Token.TokenType.ELSE);

	        return reservedWords;
	    }

	    void expr() {
	    	System.out.println("Enter <expr>\n");
	    	/* Analisa sintaticamente o primeiro termo */
	    	term();
	    	/* Desde que o pr�ximo token seja + ou -, obtenha o pr�ximo
	    	token e analise sintaticamente o pr�ximo termo */
	    	while ( this.token.getToken() == Token.TokenType.ADD_OP || this.token.getToken() ==Token.TokenType.SUB_OP) {
	    	this.token=getNextToken();
	    	System.out.println(this.token);
	    	term();
	    	}
	    	System.out.println("Exit <expr>\n");
	    	} /* Fim da fun��o expr */
	    
	    void term() {
	    	System.out.println("Enter <term>\n");
	    	/* Analisa sintaticamente o primeiro termo */
	    	factor();
	    	/* Desde que o pr�ximo token seja + ou -, obtenha o pr�ximo
	    	token e analise sintaticamente o pr�ximo termo */
	    	while (this.token.getToken() == Token.TokenType.MULT_OP ||  this.token.getToken()== Token.TokenType.DIV_OP) {
	    	this.token=getNextToken();
	    	System.out.println(this.token);
	    	factor();
	    	}
	    	System.out.println("Exit <term>\n");
	    	} /* Fim da fun��o term */
	    void factor() {
	    	System.out.println("Enter <factor>\n");
	    	/* Determina qual RHS */
	    	    if (this.token.getToken() == Token.TokenType.IDENT || this.token.getToken() == Token.TokenType.INT_LIT) {
	    		/* Obt�m o pr�ximo token */
	    		   this.token=getNextToken();
	    		   System.out.println(this.token);
	    	    }
	    	       
	    		/* Se a RHS � (<expr>), chame lex para passar o par�ntese
	    		esquerdo, chame expr e verifique pelo par�ntese
	    		direito */
	    		else {
					if (this.token.getToken() == Token.TokenType.LEFT_PAREN) {
						this.token = getNextToken();
						System.out.println(this.token);
						expr();
						if (this.token.getToken() == Token.TokenType.RIGHT_PAREN) {
							this.token = getNextToken();
							System.out.println(this.token);
						}
						else
							System.out.println("Erro detectado!!");
					} /* Fim do if (nextToken == ... */
					/*
					 * N�o era um identificador, um literal inteiro ou um par�ntese esquerdo
					 */
					else
						System.out.println("Erro detectado!!");
				} /* Fim do else */
	    		System.out.println("Exit <factor>\n");
	    		} /* Fim da fun��o factor */   
	}




