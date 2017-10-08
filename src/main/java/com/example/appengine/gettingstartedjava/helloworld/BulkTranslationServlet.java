package com.example.appengine.gettingstartedjava.helloworld;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@SuppressWarnings("serial")
@WebServlet(name = "translation", value = "/" )
public class BulkTranslationServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

      // todo error checking
      String queryText = request.getParameter("text");
      String queryLang = request.getParameter("lang");
      if (queryText == null || queryLang == null)
          return;
      Map<String, String> resultTranslations = TranslationHandler.translateAll(queryText, queryLang);
      JSONObject responseObject = new JSONObject(resultTranslations);

      response.setCharacterEncoding("utf8");
      responseObject.write(response.getWriter());

  }
}

