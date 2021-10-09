package Comment;

import News.News;

public class Comment {
    private int id ;
    private String comment=null;
    private String user;
    private News news;

    public Comment() {

    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Comment(String comment, String user,News news) {
        this.comment = comment;
        this.user = user;
        this.news = news;
    }

    public Comment(int id, String comment, String user, News news) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.news = news;
    }

//    public Comment(String comment, String user) {
//        this.comment = comment;
//        this.user = user;
//    }
//
//    public Comment(int id, String comment, String user) {
//        this.id = id;
//        this.comment = comment;
//        this.user = user;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "Comment{" +comment;
//    }
}
