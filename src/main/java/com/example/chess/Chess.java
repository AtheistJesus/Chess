package com.example.chess;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Chess extends Application {

    protected static final int WIDTH = 75, HEIGHT = 75;

    protected static List<Piece> allPieces = new ArrayList<>();

    protected static Piece previousPiece;
    protected static Piece currentPiece;

    public static Group board = new Group();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chess [WIP]");
        drawWhiteSpaces(board);
        drawGreenSpaces(board);
        drawGrid(board);
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(i * 75, 450, WIDTH, HEIGHT);
            board.getChildren().add(pawn.getProfile());
            allPieces.add(pawn);
        }
        Rook rook1 = new Rook(0, 525, WIDTH, HEIGHT);
        Rook rook2 = new Rook(525, 525, WIDTH, HEIGHT);
        board.getChildren().addAll(rook1.getProfile(), rook2.getProfile());
        allPieces.add(rook1);
        allPieces.add(rook2);
        Bishop bishop1 = new Bishop(150, 525, WIDTH, HEIGHT);
        Bishop bishop2 = new Bishop(375, 525, WIDTH, HEIGHT);
        board.getChildren().addAll(bishop1.getProfile(), bishop2.getProfile());
        allPieces.add(bishop1);
        allPieces.add(bishop2);
        Knight knight1 = new Knight(75, 525, WIDTH, HEIGHT);
        Knight knight2 = new Knight(450, 525, WIDTH, HEIGHT);
        board.getChildren().addAll(knight1.getProfile(), knight2.getProfile());
        allPieces.add(knight1);
        allPieces.add(knight2);
        Queen queen = new Queen(300, 525, WIDTH, HEIGHT);
        board.getChildren().add(queen.getProfile());
        allPieces.add(queen);
        King king = new King(225, 525, WIDTH, HEIGHT);
        board.getChildren().add(king.getProfile());
        allPieces.add(king);
        Scene scene = new Scene(board,600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private static void drawWhiteSpaces(Group group) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 600; j += 150) {
                Rectangle white = new Rectangle(j, i * 150, 75, 75);
                white.setFill(Color.WHITE);
                white.setOnMouseClicked(mouseEvent -> currentPiece.move(white));
                group.getChildren().add(white);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 75; j < 600; j += 150) {
                Rectangle white = new Rectangle(j, 75 + (i * 150), 75, 75);
                white.setFill(Color.WHITE);
                white.setOnMouseClicked(mouseEvent -> currentPiece.move(white));
                group.getChildren().add(white);
            }
        }
    }

    private static void drawGreenSpaces(Group group) {
        for (int i = 0; i < 4; i++) {
            for (int j = 75; j < 600; j += 150) {
                Rectangle green = new Rectangle(j, i * 150, 75, 75);
                green.setFill(Color.LIGHTGREEN);
                green.setOnMouseClicked(mouseEvent -> currentPiece.move(green));
                group.getChildren().add(green);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 600; j += 150) {
                Rectangle green = new Rectangle(j, 75 + (i * 150), 75, 75);
                green.setFill(Color.LIGHTGREEN);
                green.setOnMouseClicked(mouseEvent -> currentPiece.move(green));
                group.getChildren().add(green);
            }
        }
    }

    private static void drawGrid(Group group) {
        for (int i = 75; i < 600; i += 75) {
            Line line = new Line(i, 0, i, 600);
            group.getChildren().add(line);
        }
        for (int i = 75; i < 600; i += 75) {
            Line line = new Line(0, i, 600, i);
            group.getChildren().add(line);
        }
    }

    public static Piece pieceOnTarget(Rectangle target) {
        for (Piece piece : allPieces) {
            if (target.contains(piece.getProfile().getX(), piece.getProfile().getY())) {
                return piece;
            }
        }
        return null;
    }

    public static boolean pieceInTheWayOf(Rectangle target) {
        int currentX = (int)currentPiece.getProfile().getX();
        int currentY = (int)currentPiece.getProfile().getY();
        while (currentX != target.getX() || currentY != target.getY()) {
            currentX += (int)Math.signum(target.getX() - currentX) * 75;
            currentY += (int)Math.signum(target.getY() - currentY) * 75;
            for (Piece piece : allPieces) {
                if (piece.getProfile().getX() == currentX && piece.getProfile().getY() == currentY) return true;
            }
        }
        return false;
    }

    public static boolean pieceInTheWayOf(Piece target) {
        int currentX = (int)currentPiece.getProfile().getX();
        int currentY = (int)currentPiece.getProfile().getY();
        while (currentX != target.getProfile().getX() || currentY != target.getProfile().getY()) {
            currentX += (int)Math.signum(target.getProfile().getX() - currentX) * 75;
            currentY += (int)Math.signum(target.getProfile().getY() - currentY) * 75;
            for (Piece piece : allPieces) {
                if (piece.getProfile().getX() == currentX && piece.getProfile().getY() == currentY) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}