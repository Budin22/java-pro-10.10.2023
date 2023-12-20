package org.example.entity.dto;

import java.time.LocalDateTime;

public class LessonDTO {
    private String name;
    private LocalDateTime updateAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
