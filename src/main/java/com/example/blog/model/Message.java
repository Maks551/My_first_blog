package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends AbstractBaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "topic", nullable = false)
    private String topic;

    @NotBlank
    @Column(name = "message")
    private String message;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Message() {
    }

    public Message(String topic, String message, LocalDateTime dateTime) {
        this(null, topic, message, dateTime);
    }

    public Message(Integer id, String topic, String message, LocalDateTime dateTime) {
        super(id);
        this.topic = topic;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String thema) {
        this.topic = thema;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", thema=" + topic +
                ", message=" + message +
                ", dateTime=" + dateTime +
                '}';
    }
}
