package net.blog.post.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ModelAndView handleException(PostNotFoundException exc){
        ModelAndView modelAndView=new ModelAndView("exception");
        PostsErrorResponse errorResponse=new PostsErrorResponse(exc.getMessage());
        modelAndView.addObject("exceptionMessage",errorResponse);
        return modelAndView;
    }
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception exc){
        System.out.println("enter in main exception");
        ModelAndView modelAndView=new ModelAndView("exception");
        PostsErrorResponse errorResponse=new PostsErrorResponse(exc.getMessage());
        modelAndView.addObject("exceptionMessage",errorResponse);
        return modelAndView;
    }



}
