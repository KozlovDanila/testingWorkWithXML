package models;

public class Answer {
    private String element;
    private int index;
    private boolean stateAnswer;

    public Answer(String element, int index) {
        this.element = element;
        this.index = index;
        stateAnswer = false;
    }

    public String getStringWithAnswer() {
        return element;
    }

    public int getIndex() {
        return index;
    }

    public String[] getAnswers() {
        return element.split(" ");
    }

    public void setTrueAnswer() {
        stateAnswer = true;
    }

    public boolean getAnswerState() {
        return stateAnswer;
    }
}
