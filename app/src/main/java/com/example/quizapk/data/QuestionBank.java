package com.example.quizapk.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapk.controller.appcontroller;
import com.example.quizapk.models.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class QuestionBank implements AnswerListAsync {
    ArrayList<Question> questionArrayList=new ArrayList<>();
    private  String url ="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json\n";
    public ArrayList<Question> getQuestions(final AnswerListAsync callBack)
    {
        final JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(
                Request.Method.GET,
                url,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i <= response.length(); i++) {
                            Question question=new Question();
                            try {
                               question.setQuestion(response.getJSONArray(i).get(0).toString());
                               question.setAnswer(response.getJSONArray(i).getBoolean(1));
                               questionArrayList.add(question);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if(null != callBack) callBack.processFinished(questionArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        appcontroller.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }

    @Override
    public void processFinished(ArrayList<Question> questionArrayList) {

    }
}
