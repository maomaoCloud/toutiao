package com.tiaotiaopoker;

import java.security.NoSuchAlgorithmException;

public interface Security {
    public abstract String encrypt(String input,
                                   String key);

    public abstract String decrypt(String input,
                                   String key) throws NoSuchAlgorithmException;
}
