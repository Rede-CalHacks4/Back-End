package com.example.appengine.gettingstartedjava.helloworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


class TranslationHandler {

    private final static List<Language> languages;

    static {
        languages = TranslateOptions.getDefaultInstance().getService().listSupportedLanguages();
    }

    static Map<String, String> translateAll(final String query, String language) {

        Map<String, String> result = new HashMap<>();
        final Translate translate = TranslateOptions.getDefaultInstance().getService();
        if (language.equalsIgnoreCase("auto")) {
            language = translate.detect(query).getLanguage(); // TODO make sure this is language code
        }

        result.put(language, query);

        for (Language key : languages) {
            if (key.getCode().equalsIgnoreCase(language))
                continue;
//            if (!key.getCode().equalsIgnoreCase("ja"))
//                continue;
            Translation translation = translate.translate(query,
                                        TranslateOption.sourceLanguage(language),
                                        TranslateOption.targetLanguage(key.getCode()));
            result.put(key.getCode(), translation.getTranslatedText());
        }

        return result;
    }


    // get list of supported languages, translate each one, then return map of <langCode, translation>
    // refactor then deploy
    // if time, implement caching to reduce API workload (Mongo, MemSQL?)
}
