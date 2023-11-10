package com.fag.jogo;

import java.util.Arrays;

public class Palavra {
    private String palavrinha;
    private char[] caracteres;

    public Palavra(String palavra) {
        this.palavrinha = palavra;
        this.caracteres = palavra.toCharArray();
    }

    public String getPalavrinha() {
        return palavrinha;
    }

    public boolean contains(char c) {
        for (char ch : caracteres) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(char[] chute) {
        return Arrays.equals(caracteres, chute);
    }

    public String getInfo(String chute) {
        char[] chuteArray = chute.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chuteArray.length; i++) {
            char chuteAtual = chuteArray[i];
            if (contains(chuteAtual)) {
                if (caracteres[i] == chuteAtual) {
                    result.append('T');
                } else {
                    result.append('M');
                }
            } else {
                result.append('F');
            }
        }

        return result.toString();
    }
}
