package com.example.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class King extends Chess implements Piece {

    private final ImageView kingProfile;
    private boolean hasMoved = false;


    public King(int x, int y, int width, int height) {
        Image pawnImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/king.png")));
        kingProfile = new ImageView(pawnImage);
        kingProfile.setX(x);
        kingProfile.setY(y);
        kingProfile.setFitWidth(width);
        kingProfile.setFitHeight(height);
        kingProfile.setOnMouseClicked(mouseEvent -> {
            previousPiece = currentPiece;
            currentPiece = this;
        });
    }

    @Override
    public void move(Rectangle target) {
        Piece piece = pieceOnTarget(target);
        if (piece instanceof Rook && !this.hasMoved && !((Rook) piece).hasMoved) {
            castle(piece);
            return;
        }
        if (piece != null) {
            currentPiece = piece;
            return;
        }
        if (Math.abs(target.getX() - kingProfile.getX()) / 75 <= 1 &&
                Math.abs(target.getY() - kingProfile.getY()) / 75 <= 1) {
            kingProfile.setX(target.getX());
            kingProfile.setY(target.getY());
            hasMoved = true;
        }
    }

    void castle(Piece piece) {
        allPieces.remove(piece);
        if (!pieceInTheWayOf(piece)) {
            if (Objects.requireNonNull(piece).getProfile().getX() < kingProfile.getX()) {
                kingProfile.setX(kingProfile.getX() - 150);
                Objects.requireNonNull(piece).getProfile().setX(kingProfile.getX() + 75);
            }
            else {
                kingProfile.setX(kingProfile.getX() + 150);
                Objects.requireNonNull(piece).getProfile().setX(kingProfile.getX() - 75);
            }
            hasMoved = true;
        }
        allPieces.add(piece);
    }

    @Override
    public ImageView getProfile() { return kingProfile; }
}