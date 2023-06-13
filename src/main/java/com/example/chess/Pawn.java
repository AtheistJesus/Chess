package com.example.chess;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Pawn extends Chess implements Piece {

    private final ImageView pawnProfile;
    private boolean firstMove = true;

    public Pawn(int x, int y, int width, int height) {
        Image pawnImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pawn.png")));
        pawnProfile = new ImageView(pawnImage);
        pawnProfile.setX(x);
        pawnProfile.setY(y);
        pawnProfile.setFitWidth(width);
        pawnProfile.setFitHeight(height);
        pawnProfile.setOnMouseClicked(mouseEvent -> {
            previousPiece = currentPiece;
            currentPiece = this;
        });
    }

    @Override
    public void move(Rectangle target) {
        Piece piece = pieceOnTarget(target);
        if (piece != null) {
            currentPiece = piece;
            return;
        }
        if (pieceInTheWayOf(target)) return;
        if (pawnProfile.getX() == target.getX()) {
            if (pawnProfile.getY() == 75 && target.getY() == 0) promote();
            if (firstMove && pawnProfile.getY() - 150 == target.getY()) pawnProfile.setY(pawnProfile.getY() - 150);
            else if (pawnProfile.getY() - 75 == target.getY()) pawnProfile.setY(pawnProfile.getY() - 75);
            if (pawnProfile.getY() - 150 <= target.getY()) firstMove = false;
        }
    }

    @Override
    public ImageView getProfile() { return pawnProfile; }

    public void promote() {
        Rectangle promotionCover = new Rectangle(600, 600, Color.LIGHTGRAY);
        promotionCover.setOpacity(0.75);
        Rectangle promotionMenu = new Rectangle();
        promotionMenu.setWidth(150);
        promotionMenu.setHeight(150);
        promotionMenu.setFill(Color.WHITE);
        if (pawnProfile.getX() <= 225) promotionMenu.setX(pawnProfile.getX());
        else promotionMenu.setX(pawnProfile.getX() - 75);
        promotionMenu.setY(75);
        ImageView queenImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/queen.png"))));
        Button queen = new Button("", queenImage);
        queen.setScaleX(0.125);
        queen.setScaleY(0.125);
        queen.setLayoutX(promotionMenu.getX() - 227.5);
        queen.setLayoutY(promotionMenu.getY() - 222.5);
        ImageView rookImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rook.png"))));
        Button rook = new Button("", rookImage);
        rook.setScaleX(0.125);
        rook.setScaleY(0.125);
        rook.setLayoutX(promotionMenu.getX() - 150);
        rook.setLayoutY(promotionMenu.getY() - 222.5);
        ImageView bishopImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bishop.png"))));
        Button bishop = new Button("", bishopImage);
        bishop.setScaleX(0.125);
        bishop.setScaleY(0.125);
        bishop.setLayoutX(promotionMenu.getX() - 227.5);
        bishop.setLayoutY(promotionMenu.getY() - 147.5);
        ImageView knightImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/knight.png"))));
        Button knight = new Button("", knightImage);
        knight.setScaleX(0.125);
        knight.setScaleY(0.125);
        knight.setLayoutX(promotionMenu.getX() - 150);
        knight.setLayoutY(promotionMenu.getY() - 147.5);
        queen.setOnMouseClicked(mouseEvent -> {
            board.getChildren().removeAll(promotionCover, promotionMenu, queen, rook, bishop, knight, this.pawnProfile);
            Queen promotedQueen = new Queen((int)getProfile().getX(), (int)getProfile().getY(), WIDTH, HEIGHT);
            allPieces.remove(this);
            board.getChildren().add(promotedQueen.getProfile());
            allPieces.add(promotedQueen);
            currentPiece = promotedQueen;
        });
        rook.setOnMouseClicked(mouseEvent -> {
            board.getChildren().removeAll(promotionCover, promotionMenu, queen, rook, bishop, knight, this.pawnProfile);
            Rook promotedRook = new Rook((int)getProfile().getX(), (int)getProfile().getY(), WIDTH, HEIGHT);
            allPieces.remove(this);
            board.getChildren().add(promotedRook.getProfile());
            allPieces.add(promotedRook);
            currentPiece = promotedRook;
        });
        bishop.setOnMouseClicked(mouseEvent -> {
            board.getChildren().removeAll(promotionCover, promotionMenu, queen, rook, bishop, knight, this.pawnProfile);
            Bishop promotedBishop = new Bishop((int)getProfile().getX(), (int)getProfile().getY(), WIDTH, HEIGHT);
            allPieces.remove(this);
            board.getChildren().add(promotedBishop.getProfile());
            allPieces.add(promotedBishop);
            currentPiece = promotedBishop;
        });
        knight.setOnMouseClicked(mouseEvent -> {
            board.getChildren().removeAll(promotionCover, promotionMenu, queen, rook, bishop, knight, this.pawnProfile);
            Knight promotedKnight = new Knight((int)getProfile().getX(), (int)getProfile().getY(), WIDTH, HEIGHT);
            allPieces.remove(this);
            board.getChildren().add(promotedKnight.getProfile());
            allPieces.add(promotedKnight);
            currentPiece = promotedKnight;
        });
        board.getChildren().addAll(promotionCover, promotionMenu);
        board.getChildren().addAll(queen, rook, bishop, knight);
    }
}