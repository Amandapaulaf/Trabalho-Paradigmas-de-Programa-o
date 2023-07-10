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
        String arquivoDeEntrada ="C:\\Users\\Gabriel\\Desktop\\programa�ao\\arquivo3.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoDeEntrada))) {
        	// Cria uma inst�ncia de LexicalAnalyzer para analisar o arquivo
        	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(reader);
            
            do {
                //Obt�m o pr�ximo token do analisador l�xico
            	lexicalAnalyzer.token = lexicalAnalyzer.getNextToken();
            	
                if (lexicalAnalyzer.token != null && lexicalAnalyzer.token.getToken() == Token.TokenType.EOF) {
                    break;
                }
                System.out.println(lexicalAnalyzer.token);
                lexicalAnalyzer.expr();
                //Imprime o token obtido
             
            } while (lexicalAnalyzer.token != null);
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo.txt: " + e.getMessage());
        }
        
    }
}

    

