package com.amazonaws.demo.s3transferutility;


public class UserHelperClass {
    String movieName, movieLanguage, catagory_movie, movieYear, movieDirector,movieHero,movieActress,mp4FileUrl,thumbnailUrl;
    int flag, copies;

    public UserHelperClass() {
    }
    public UserHelperClass(String movieName, String movieLanguage,String catagory_movie,String movieYear,String movieDirector,String movieHero,String movieActress,String mp4FileUrl, String thumbnailUrl,int flag, int copies) {
        this.movieName = movieName;
        this.movieLanguage = movieLanguage;
        this.catagory_movie = catagory_movie;
        this.movieYear = movieYear;
        this.movieDirector = movieDirector;
        this.movieHero = movieHero;
        this.movieActress = movieActress;
        this.mp4FileUrl = mp4FileUrl;
        this.thumbnailUrl=thumbnailUrl;
        this.flag=flag;
        this.copies=copies;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }
    public String getCatagory_movie() {
        return catagory_movie;
    }

    public void setCatagory_movie(String catagory_movie) {
        this.catagory_movie = catagory_movie;
    }
    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
    public String getMovieHero() {
        return movieHero;
    }

    public void setMovieHero(String movieHero) {
        this.movieHero = movieHero;
    }

    public String getMovieActress() {
        return movieActress;
    }

    public void setMovieActress(String movieActress) {
        this.movieActress = movieActress;
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public String getMp4FileUrl() {
        return mp4FileUrl;
    }

    public void setMp4FileUrl(String mp4FileUrl) {
        this.mp4FileUrl = mp4FileUrl;
    }
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }


}