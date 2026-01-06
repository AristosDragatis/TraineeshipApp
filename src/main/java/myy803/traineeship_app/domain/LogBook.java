package myy803.traineeship_app.domain;

import jakarta.persistence.*;

@Entity
public class LogBook {
    @Id
    private Integer id;
    private String date;
    private String content;

    @ManyToOne
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
