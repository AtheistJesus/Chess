package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class King extends Chess implements Piece {

    private final ImageView kingProfile;

    public King(int x, int y, int width, int height) {
        Image pawnImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/king.png")));
        kingProfile = new ImageView(pawnImage);
        kingProfile.setX(x);
        kingProfile.setY(y);
        kingProfile.setFitWidth(width);
        kingProfile.setFitHeight(height);
        kingProfile.setOnMouseClicked(mouseEvent -> currentPiece = this);
    }

    @Override
    public void move(Rectangle target) {
        if (pieceOnTarget(target) != null) {
            currentPiece = pieceOnTarget(target);
            return;
        }
        if (Math.abs(target.getX() - kingProfile.getX()) / 75 <= 1 &&
                Math.abs(target.getY() - kingProfile.getY()) / 75 <= 1) {
            kingProfile.setX(target.getX());
            kingProfile.setY(target.getY());
        }
    }

    @Override
    public ImageView getProfile() { return kingProfile; }
}