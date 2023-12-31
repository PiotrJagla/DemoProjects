package com.mygdx.game.browser;

import com.badlogic.gdx.math.Rectangle;

import javax.crypto.spec.ChaCha20ParameterSpec;
import java.util.ArrayList;
import java.util.List;

public class Paint {

    public List<DisplayCommand> buildDisplayList(LayoutBox layoutRoot) {
        List<DisplayCommand> list = new ArrayList<>();
        renderLayoutBox(list, layoutRoot);
        return list;
    }

    private void renderLayoutBox(List<DisplayCommand> list, LayoutBox layoutRoot) {
        renderBackground(list, layoutRoot);
        renderBorders(list, layoutRoot);

        for (LayoutBox child : layoutRoot.getChildren()) {
            renderLayoutBox(list,child);
        }
    }

    private void renderBackground(List<DisplayCommand> list, LayoutBox layoutRoot) {
        Color color = getColor(layoutRoot, "background");
        if(color != null) {
            SolidColor sc = new SolidColor();
            sc.setColor(color);
            sc.setRect(layoutRoot.getDimensions().borderBox());
            list.add(sc);
        }
        else {
            SolidColor sc = new SolidColor();

            sc.setColor(new Color());
            sc.setRect(layoutRoot.getDimensions().borderBox());
            list.add(sc);

        }
    }

    private void renderBorders(List<DisplayCommand> list, LayoutBox layoutRoot) {
        Color color = getColor(layoutRoot, "border-color");
        if(color == null) {
            color = new Color();
        }

        Dimensions d = layoutRoot.getDimensions();
        Rectangle border_box = d.borderBox();

        //Left border
        Rectangle rect = new Rectangle();
        rect.x = border_box.x;
        rect.y = border_box.y;
        rect.width = d.getBorder().getLeft();
        rect.height = border_box.height;
        SolidColor sc = new SolidColor();
        sc.setRect(rect);
        sc.setColor(color);



        //Right border
        rect = new Rectangle();
        rect.x = border_box.x + border_box.width - d.getBorder().getRight();
        rect.y = border_box.y;
        rect.width = d.getBorder().getRight();
        rect.height = border_box.height;
        sc = new SolidColor();
        sc.setRect(rect);
        sc.setColor(color);

        //Top border
        rect = new Rectangle();
        rect.x = border_box.x;
        rect.y = border_box.y;
        rect.width = border_box.width;
        rect.height = d.getBorder().getTop();
        sc = new SolidColor();
        sc.setRect(rect);
        sc.setColor(color);

        //Bottom border
        rect = new Rectangle();
        rect.x = border_box.x;
        rect.y = border_box.y + border_box.height - d.getBorder().getBottom();
        rect.width = border_box.width;
        rect.height = d.getBorder().getBottom();
        sc = new SolidColor();
        sc.setRect(rect);
        sc.setColor(color);
    }

    private Color getColor(LayoutBox layoutBox, String name) {
        switch(layoutBox.getBoxType().getBoxTypeName()) {
            case BlockNode:
            case InlineNode:
                Value v = layoutBox.getBoxType().getStyledNode().value(name);
                if(v instanceof Color) {
                    return (Color) v;
                }
                else {
                    return null;
                }
            case AnonymusBlock:
                return null;
            default:
                return null;
        }
    }
}


abstract class DisplayCommand {

}

class SolidColor extends DisplayCommand {
    private Color color;
    private Rectangle rect;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}