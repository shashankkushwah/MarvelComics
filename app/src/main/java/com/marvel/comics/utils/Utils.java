package com.marvel.comics.utils;

import java.util.Collection;

/**
 * Created by Shashank on 13/10/2017.
 */

public class Utils {

    public static boolean isNullOrEmpty(final Collection<?> c) {
        return c == null || c.isEmpty();
    }

}
