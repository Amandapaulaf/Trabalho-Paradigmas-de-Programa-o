package trabalhoFinalParadigmas;

import trabalhoFinalParadigmas.Token.TokenType;

public class Token {
	
	//Classe Token que representa um token do programa
		//Enumeração para os tipos de token possíveis
	    public enum TokenType {
	        INT_LIT,//números inteiros
	        IDENT,//identificadores
	        ASSIGN_OP,//operadores de atribuição
	        ADD_OP,//operador de adição
	        SUB_OP,//operador de subtração
	        MULT_OP,//operador de multiplicação
	        DIV_OP,//operador de divisão
	        LEFT_PAREN,//parêntese esquerdo
	        RIGHT_PAREN,//parêntrese direito
	        EOF,//fim de arquivo
	        NEWLINE,//nova linha
	        IF,//condicional
	        ELSE,//condicional
	        PONT,//ponto final
	        PONTVIRG,//ponto e vírgula
	        ASP//aspas
	    }

	    private TokenType token;//Tipo do token
	    private String lexeme;//Valor do token (lexema)
	    

	    //Construtor da classe Token
	    public Token(TokenType token, String lexeme) {
	        this.token = token;
	        this.lexeme = lexeme;
	    }
	    //Métodos getter para obter o tipo e o valor do token
	    public TokenType getToken() {
	        return token;
	    }

	    public String getLexeme() {
	        return lexeme;
	    }
	    //Sobrescrita do método toString para imprimir o token
	    @Override
	    public String toString() {
	        return "Next token is: " + token.toString() + ", Next lexeme is " + lexeme;
	    }
	    
	}


