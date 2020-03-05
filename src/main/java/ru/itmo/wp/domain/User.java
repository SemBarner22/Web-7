package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(
        indexes = @Index(columnList = "creationTime"),
        uniqueConstraints = @UniqueConstraint(columnNames = "login")
)
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    private String login;

    @CreationTimestamp
    private Date creationTime;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean disabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
