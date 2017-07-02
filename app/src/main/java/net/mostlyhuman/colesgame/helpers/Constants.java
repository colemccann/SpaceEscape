package net.mostlyhuman.colesgame.helpers;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class Constants {

    public static final String CHAPTER_TITLE = "chapter_title";
    public static final String LEVEL_TITLE = "level_title";
    public static final String LEVEL_ID = "level_id";

    public static class Directions {
        public static final int UP = 360;
        public static final int RIGHT = 270;
        public static final int DOWN = 180;
        public static final int LEFT = 90;
    }

    public static class Sounds {
        public static final String BUMP = "bump";
        public static final String EXPLOSION = "explosion";
        public static final String REDIRECT = "redirect";
    }

    public static class OpenGL {
        public static final String U_MVP_MATRIX = "u_MVPMatrix";
        public static final String A_POSITION = "a_Position";
        public static final String U_TEXTURE = "u_Texture";
        public static final String A_TEX_COORDINATE = "a_TexCoordinate";
    }

    public static class Types {
        public static final char BORDER = '=';

        public static final char BLOCK = '#';

        public static final char ASTEROID = 'a';
        public static final char REDIRECT_DOWN = 'd';
        public static final char EXIT = 'e';
        public static final char DOOR_HORIZONTAL = 'h';
        public static final char REDIRECT_LEFT = 'l';
        public static final char REDIRECT_RIGHT = 'r';
        public static final char BUTTON = 'b';
        public static final char TURRET_BASE = 'T';
        public static final char TURRET = 't';
        public static final char REDIRECT_UP = 'u';
        public static final char DOOR_VERTICAL = 'v';
        public static final char WARP = 'w';
        public static final char BOMB = 'x';

        public static final char PLAYER = 'p';

    }

    public static class Levels {
        public static final String ONE_A = "Level1A";
        public static final String ONE_B = "Level1B";
    }

}
