package com.nouf.projects.clothingtermsdesign;

public class machines {


    private String arabic_term ;
    private String english_term ;
    private String imgUri_term ;
    private String video_link ;

    public machines(String arabic_term, String english_term, String imgUri_term) {
        this.arabic_term = arabic_term;
        this.english_term = english_term;
        this.imgUri_term = imgUri_term;
    }


    public machines() {
    }



    public String getArabic_term() {
        return arabic_term;
    }

    public void setArabic_term(String arabic_term) {
        this.arabic_term = arabic_term;
    }

    public String getEnglish_term() {
        return english_term;
    }

    public void setEnglish_term(String english_term) {
        this.english_term = english_term;
    }

    public String getImgUri_term() {
        return imgUri_term;
    }

    public void setImgUri_term(String imgUri_term) {
        this.imgUri_term = imgUri_term;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }
}
