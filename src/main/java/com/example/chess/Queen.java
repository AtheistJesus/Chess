package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Queen extends Chess implements Piece {

    private final ImageView queenProfile;

    public Queen(int x, int y, int width, int height) {
        Image pawnImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/queen.png")));
        queenProfile = new ImageView(pawnImage);
        queenProfile.setX(x);
        queenProfile.setY(y);
        queenProfile.setFitWidth(width);
        queenProfile.setFitHeight(height);
        queenProfile.setOnMouseClicked(mouseEvent -> currentPiece = this);
    }

    @Override
    public void move(Rectangle target) {
        if (pieceOnTarget(target) || interceptingStraight(target) || interceptingDiagonal(target)) return;
        if (queenProfile.getX() != target.getX() && queenProfile.getY() == target.getY() ||
        queenProfile.getX() == target.getX() && queenProfile.getY() != target.getY() ||
        Math.abs(target.getX() - queenProfile.getX()) == Math.abs(target.getY() - queenProfile.getY())) {
            queenProfile.setX(target.getX());
            queenProfile.setY(target.getY());
        }
    }

    @Override
    public ImageView getProfile() {
        return queenProfile;
    }
}