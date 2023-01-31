package com.ms.dob.textonphoto.utility;

import java.util.Random;

public class RandomQuoetsForFirstscreen {
    public static String[] Quotes = {"Nothing is impossible, the word itself says “I’m possible”!", "Whether you think you can or you think you can’t, you’re right.", "Remember no one can make you feel inferior without your consent.", "I can’t change the direction of the wind, but I can adjust my sails to always reach my destination", "Believe you can and you’re halfway there.", "To handle yourself, use your head; to handle others, use your heart.", "Be faithful in small things because it is in them that your strength lies.", "We ourselves feel that what we are doing is just a drop in the ocean. But the ocean would be less because of that missing drop.", "Kind words can be short and easy to speak, but their echoes are truly endless.", "Be the change that you want to see in the world.", "Nobody can hurt me without my permission", "The weak can never forgive. Forgiveness is an attribute of the strong.", "Gravity explains the motions of the planets but it cannot explain who sets the planets in motion", "Plato is my friend, Aristotle is my friend, but my greatest friend is truth.", "No great discovery was ever made without a bold guess.", "Be faithful in small things because it is in them that your strength lies.", "We ourselves feel that what we are doing is just a drop in the ocean. But the ocean would be less because of that missing drop.", "If you can’t feed a hundred people, then feed just one."};

    public static String[] RandomizeQuotes() {
        String[] strArr = new String[Quotes.length];
        System.arraycopy(Quotes, 0, strArr, 0, Quotes.length);
        Random random = new Random();
        for (int i = 0; i < strArr.length; i++) {
            int nextInt = random.nextInt(strArr.length);
            String str = strArr[i];
            strArr[i] = strArr[nextInt];
            strArr[nextInt] = str;
        }
        return strArr;
    }
}
