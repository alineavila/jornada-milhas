package com.alineavila.jornadamilhas.infra;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class IntegraOpenAi {

    private static final String API_KEY = "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";
    OpenAiService service = new OpenAiService(API_KEY);

    public String realizaOPrompt(String nomeDestino) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Faça uma descrição turistica sobre a localidade "+ nomeDestino)
                .model("text-davinci-003")
                .maxTokens(800)
                .build();
        var result = service.createCompletion(completionRequest).getChoices().get(0).getText();
        System.out.println(result);
        return result;
    }
}
