package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Bishop extends Chess implements Piece {

    private final ImageView bishopProfile;

    public Bishop(int x, int y, int width, int height) {
        bishopProfile = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bishop.png"))));
        bishopProfile.setX(x);
        bishopProfile.setY(y);
        bishopProfile.setFitWidth(width);
        bishopProfile.setFitHeight(height);
        bishopProfile.setOnMouseClicked(mouseEvent -> currentPiece = this);
    }

    @Override
    public void move(Rectangle target) {
        if (pieceInTheWayOf(target)) return;
        if (Math.abs(target.getX() - bishopProfile.getX()) == Math.abs(target.getY() - bishopProfile.getY())) {
            bishopProfile.setX(target.getX());
            bishopProfile.setY(target.getY());
        }
    }

    @Override
    public ImageView getProfile() { return bishopProfile; }
}