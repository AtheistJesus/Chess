package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Knight extends Chess implements Piece {

    private final ImageView knightProfile;

    public Knight(int x, int y, int width, int height) {
        Image pawnImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/knight.png")));
        knightProfile = new ImageView(pawnImage);
        knightProfile.setX(x);
        knightProfile.setY(y);
        knightProfile.setFitWidth(width);
        knightProfile.setFitHeight(height);
        knightProfile.setOnMouseClicked(mouseEvent -> currentPiece = this);
    }

    @Override
    public void move(Rectangle target) {
        if (pieceOnTarget(target)) return;
        if (Math.abs(target.getX() - knightProfile.getX()) / 75 == 2 &&
                Math.abs(target.getY() - knightProfile.getY()) / 75 == 1 ||
                Math.abs(target.getX() - knightProfile.getX()) / 75 == 1 &&
                        Math.abs(target.getY() - knightProfile.getY()) / 75 == 2) {
            knightProfile.setX(target.getX());
            knightProfile.setY(target.getY());
        }
    }

    @Override
    public ImageView getProfile() {
        return knightProfile;
    }
}