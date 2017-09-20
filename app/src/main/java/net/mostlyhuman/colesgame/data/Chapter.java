package net.mostlyhuman.colesgame.data;


/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class Chapter {

    private String chapterNumber;
    private String chapterTitle;
    private int levelsCompleted;
    private boolean available;

    public Chapter(String chapterNumber, String chapterTitle,
                   int levelsCompleted,
                   boolean available) {

        this.chapterNumber = chapterNumber;
        this.chapterTitle = chapterTitle;
        this.levelsCompleted = levelsCompleted;
        this.available = available;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public int getLevelsCompleted() {
        return levelsCompleted;
    }

    public void setLevelsCompleted(int levelsCompleted) {
        this.levelsCompleted = levelsCompleted;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
