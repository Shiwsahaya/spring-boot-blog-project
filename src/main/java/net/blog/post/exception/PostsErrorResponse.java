package net.blog.post.exception;

public class PostsErrorResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public PostsErrorResponse() {

    }

    public PostsErrorResponse(String message) {
        this.message = message;
        System.out.println(message);
    }
}
