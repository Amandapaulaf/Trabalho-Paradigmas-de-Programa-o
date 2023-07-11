package trabalhoFinalParadigmas;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
    	//Um array para guardar os tipos de tokens
    	ArrayList<Token> saveToken = new ArrayList<Token> ();
    	//Define o caminho para o arquivo de entrada
        String arquivoDeEntrada ="C:\\Users\\Gabriel\\Desktop\\programaçao\\arquivo3.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoDeEntrada))) {
        	// Cria uma instância de LexicalAnalyzer para analisar o arquivo
        	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(reader);
            
            do {
                //Obtém o próximo token do analisador léxico
            	lexicalAnalyzer.token = lexicalAnalyzer.getNextToken();
            	
                if (lexicalAnalyzer.token != null && lexicalAnalyzer.token.getToken() == Token.TokenType.EOF) {
                    break;
                }
                System.out.println(lexicalAnalyzer.token);
                lexicalAnalyzer.expr();
                //chama o analisador lexico/sintatico para a construcao da arvore
             
            } while (lexicalAnalyzer.token != null);
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo.txt: " + e.getMessage());
        }
        
    }
}

    

