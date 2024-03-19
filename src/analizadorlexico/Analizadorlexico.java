/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexico;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizadorlexico {
    public static void main(String[] args) {
        String sourceCode = """
            int x := 5;
            int y := 10;
            if x <= 10 {
                print "bfhjk";
                y := x + y;
            } else {
                for i := 1 to 5 {
                    print "k";
                }
            }
            """;

        // Definición de la tabla de símbolos
        Map<String, String> symbolTable = new HashMap<>();

        // Definición de expresiones regulares
        String identifierPattern = "[a-zA-Z][a-zA-Z0-9]{0,14}";
        String integerConstantPattern = "([1-9][0-9]{0,1}|100|0)";
        String operatorPattern = "\\+|\\-|\\*|\\/|:=|>=|<=|>|<|=|<>|\\{|\\}|\\[|\\]|\\(|\\)|,|;|\\.\\.";
        String stringPattern = "\"[bfhjk]+\"";
        String keywordPattern = "if|else|for|print|int|[bfhjk]+";

        // Combinación de todas las expresiones regulares
        String combinedPattern = String.format("(%s)|(%s)|(%s)|(%s)|(%s)|(%s)",
                identifierPattern, integerConstantPattern, operatorPattern, stringPattern, keywordPattern, stringPattern);

        Pattern pattern = Pattern.compile(combinedPattern);
        Matcher matcher = pattern.matcher(sourceCode);

        // Análisis léxico e impresión de tokens
        while (matcher.find()) {
            String token = matcher.group();
            if (token.matches(identifierPattern)) {
                System.out.println("--Identificador: " + token);
                symbolTable.put(token, "identificador");
            } else if (token.matches(integerConstantPattern)) {
                System.out.println("--Constante entera: " + token);
                symbolTable.put(token, "entero");
            } else if (token.matches(operatorPattern)) {
                System.out.println("--Operador: " + token);
            } else if (token.matches(stringPattern)) {
                System.out.println("--Cadena de caracteres: " + token);
            } else if (token.matches(keywordPattern)) {
                System.out.println("--Palabra reservada: " + token);
            } else {
                System.out.println("--Token no reconocido: " + token);
            }
        }

        // Impresión de la tabla de símbolos
        System.out.println("\nTabla de Símbolos:");
        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
