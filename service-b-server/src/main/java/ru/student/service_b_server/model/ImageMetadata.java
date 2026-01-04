package ru.student.service_b_server.model;

import java.time.LocalDateTime;

public class ImageMetadata {

    private String camera;
    private String author;
    private int width;
    private int height;
    private LocalDateTime createdAt;

    public ImageMetadata() {
    }

    public ImageMetadata(String camera, String author, int width, int height, LocalDateTime createdAt) {
        this.camera = camera;
        this.author = author;
        this.width = width;
        this.height = height;
        this.createdAt = createdAt;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
