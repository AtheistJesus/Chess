package com.example.chess;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public interface Piece {
    void move(Rectangle target);

    ImageView getProfile();
}