package com.example.appengine.gettingstartedjava.helloworld;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


@SuppressWarnings("serial")
@WebServlet(name = "helloworld", value = "/" )
public class HelloServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // todo encoding/decoding?
      String queryText = request.getParameter("text");
      String queryLang = request.getParameter("lang");
      if (queryText == null || queryLang == null)
          return;
      Map<String, String> resultTranslations = TranslationHandler.translateAll(queryText, queryLang);
      System.out.print(resultTranslations);
      System.out.println("----------------");
      JSONObject responseObject = new JSONObject(resultTranslations);
      System.out.println(responseObject.toString());
      System.out.println("----------------");

      //Object o =
      responseObject.write(response.getWriter()); // TODO error checking
              //Ur
      //response.getWriter().write(URLEncoder.encode(responseObject.toString(), HTTP.UTF_8)); // TODO error checking
      //response.getWriter().write(URLEncoder.encode(responseObject.toString(), "GBK"));
      //responseObject.
  }
}

