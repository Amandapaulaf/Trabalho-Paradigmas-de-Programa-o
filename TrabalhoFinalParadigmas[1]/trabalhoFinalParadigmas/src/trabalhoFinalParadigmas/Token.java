package trabalhoFinalParadigmas;

import trabalhoFinalParadigmas.Token.TokenType;

public class Token {
	
	//Classe Token que representa um token do programa
		//Enumera��o para os tipos de token poss�veis
	    public enum TokenType {
	        INT_LIT,//n�meros inteiros
	        IDENT,//identificadores
	        ASSIGN_OP,//operadores de atribui��o
	        ADD_OP,//operador de adi��o
	        SUB_OP,//operador de subtra��o
	        MULT_OP,//operador de multiplica��o
	        DIV_OP,//operador de divis�o
	        LEFT_PAREN,//par�ntese esquerdo
	        RIGHT_PAREN,//par�ntrese direito
	        EOF,//fim de arquivo
	        NEWLINE,//nova linha
	        IF,//condicional
	        ELSE,//condicional
	        PONT,//ponto final
	        PONTVIRG,//ponto e v�rgula
	        ASP//aspas
	    }

	    private TokenType token;//Tipo do token
	    private String lexeme;//Valor do token (lexema)
	    

	    //Construtor da classe Token
	    public Token(TokenType token, String lexeme) {
	        this.token = token;
	        this.lexeme = lexeme;
	    }
	    //M�todos getter para obter o tipo e o valor do token
	    public TokenType getToken() {
	        return token;
	    }

	    public String getLexeme() {
	        return lexeme;
	    }
	    //Sobrescrita do m�todo toString para imprimir o token
	    @Override
	    public String toString() {
	        return "Next token is: " + token.toString() + ", Next lexeme is " + lexeme;
	    }
	    
	}


