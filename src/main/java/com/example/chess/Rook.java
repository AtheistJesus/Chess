package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Rook extends Chess implements Piece {

    private final ImageView rookProfile;

    public Rook(int x, int y, int width, int height) {
        rookProfile = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rook.png"))));
        rookProfile.setX(x);
        rookProfile.setY(y);
        rookProfile.setFitWidth(width);
        rookProfile.setFitHeight(height);
        rookProfile.setOnMouseClicked(mouseEvent -> currentPiece = this);
    }
    @Override
    public void move(Rectangle target) {
        if (pieceOnTarget(target) || interceptingStraight(target)) return;
        if (rookProfile.getX() != target.getX() && rookProfile.getY() == target.getY()) {
            rookProfile.setX(target.getX());
        }
        else if (rookProfile.getX() == target.getX() && rookProfile.getY() != target.getY()) {
            rookProfile.setY(target.getY());
        }
    }

    @Override
    public ImageView getProfile() {
        return rookProfile;
    }
}