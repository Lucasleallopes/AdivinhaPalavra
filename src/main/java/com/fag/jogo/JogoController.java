package com.fag.jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JogoController {

    private Palavra word;
    
    //defino as palavras aqui (obs: na hora do jogo elas saem aleatorias vc tem que descobri qual é rsrs)
    private List<String> listaDePalavras = Arrays.asList("legal", "raios", "funde", "pudim", "ideia");

    @GetMapping("/jogo")
    public String index(Model model) {
        if (word == null) {
            resetGame(model);
        }

        List<String> results = getResults(model);
        model.addAttribute("result", results);
        return "index";
    }

    @PostMapping("/jogo")
    public String fazerChute(String chute, Model model) {
        List<String> results = getResults(model);

        String result = word.getInfo(chute.toLowerCase());
        model.addAttribute("result", result);

        results.add(result); // mostra o resultado atual
        model.addAttribute("results", results);

       
        if (word.isCorrect(chute.toLowerCase().toCharArray())) { // Verifica se o chute é correto
            model.addAttribute("message", "Parabéns! Você encontrou a resposta. Vamos para a próxima palavra!");
            model.addAttribute("palavra", word.getPalavrinha());
            resetGame(model);
        } else {
            model.addAttribute("message", "Errado! Tente novamente seu próximo palpite:");
        }

        return "index";
    }

    private void resetGame(Model model) {
        Random random = new Random(); 
        word = new Palavra(listaDePalavras.get(random.nextInt(listaDePalavras.size()))); //faz o sorteio das palavras
        model.addAttribute("results", new ArrayList<>()); // Reseta os resultados
    }

    @SuppressWarnings("unchecked")
    private List<String> getResults(Model model) {
        List<String> results = (List<String>) model.getAttribute("results"); // Obtém a lista de resultados do modelo.
        return (results != null) ? results : new ArrayList<>(); // Retorna a lista de resultados se não for nula, caso contrário, retorna uma nova lista vazia.
    }
}
