package com.bangla.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDTO {
    private Long id;
    private Long courseId;
    private String title;
    private String videoId;
    private String content;
    private Integer orderIndex;

    public String getVideoUrl() {
        if (videoId != null && !videoId.isEmpty()) {
            return "https://www.youtube.com/embed/" + videoId;
        }
        return null;
    }
}
