
public class Score {
    
    private Double completionTime;

    public Score() {
    }

    public Score(Double completionTime) {
        this.completionTime = completionTime;
    }

    public Double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Double completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Score: " + completionTime;
    }
    
}
