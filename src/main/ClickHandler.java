package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandler implements MouseListener {
    GamePanel gp;
    public boolean clicked = false;
    private int count;
    private int x;
    private int y;

    public ClickHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        this.clicked = true;

        if (SwingUtilities.isLeftMouseButton(e)){
            handleLeftClick();
        }

        if (SwingUtilities.isRightMouseButton(e)){
            handleRightClick();
        }
    }

    public void handleLeftClick(){

        if (mousePressedBox(40, 696, 44, 58) && gp.gameState == gp.playState) {
            gp.switchPokedexStatus();
        }
        if (mousePressedBox((gp.screenWidth - (254 * 4)) / 2, gp.screenHeight - (46 * 4) - (gp.tileSize / 8), 254*4, 46*4) && gp.gameState == gp.dialogueState) {
            gp.keyH.enterPressed = true;
            gp.buttonSound.playButtonSound();
            gp.gameState = gp.playState;
        }
    }

    public void handleRightClick(){
        if (gp.gameState == gp.battleState){
            if (gp.battle != null){
                gp.battle.rightClick();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean mousePressedBox(int worldX, int worldY, int width, int height) {
        Rectangle rect = new Rectangle(worldX, worldY, width, height);


        boolean isPointInside = (rect.contains(this.x, this.y));
        return isPointInside;
    }

    public int getCount() {
        return count;
    }
}
