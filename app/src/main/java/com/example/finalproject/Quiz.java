package com.example.finalproject;

public class Quiz {
    private int id;
    private String question;
    private String Choices[];
    private String correct;

    public Quiz(int id, String question, String[] choices, String correct) {
        this.id = id;
        this.question = question;
        Choices = choices;
        this.correct = correct;
    }
    public static Quiz[] quizzes={new Quiz(0,"What is the max age that a turtle can live up to?",new String[]{"400 years","300 years","450 years","250 years"},"400 years"),
            new Quiz(1,"Why is there so much poverty in the India?",new String[]{"I dont know","There population","They are not poor","I have never been to india"},"They are not poor"),
            new Quiz(2,"Why is Earth Round?",new String[]{"I dont know","Because it is","It is flat","I have never been to india"},"Because it is")};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return Choices;
    }

    public void setChoices(String[] choices) {
        Choices = choices;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
