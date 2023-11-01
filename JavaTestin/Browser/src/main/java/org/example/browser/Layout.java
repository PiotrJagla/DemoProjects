package org.example.browser;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mygdx.game.browser.Unit.Px;

public class Layout {
    public LayoutBox buildLayoutTree(StyledNode styledNode) {
        LayoutBox root = null;
        BoxType boxType = new BoxType();
        switch(styledNode.getDisplay()) {
            case Block:
                boxType = new BoxType();
                boxType.setBoxTypeName(BoxTypeName.BlockNode);
                boxType.setStyledNode(styledNode);
                root.setBoxType(boxType);
                break;
            case Inline:
                boxType.setBoxTypeName(BoxTypeName.InlineNode);
                boxType.setStyledNode(styledNode);
                root.setBoxType(boxType);
                break;
        }

        for (StyledNode child : styledNode.getChildren()) {
            switch(child.getDisplay()) {
                case Block:
                    root.getChildren().add(buildLayoutTree(child));
                    break;
                case Inline:
                    root.getInlineContainer().getChildren().add(buildLayoutTree(child));
                    break;
                case None:
                    break;
            }
        }

        return root;
    }

}

class LayoutBox {
    private Dimensions dimensions = new Dimensions();
    private BoxType boxType = new BoxType();
    private List<LayoutBox> children = new ArrayList<>();

    public void layout(Dimensions containingBlock) {
        switch (boxType.getBoxTypeName()) {
            case BlockNode:
                layoutBlock(containingBlock);
                break;
            case InlineNode:
                break;
            case AnonymusBlock:
                break;
        }
    }

    private void layoutBlock(Dimensions containingBlock) {
        calculateBlockWidth(containingBlock);
        calculateBlockPosition(containingBlock);
        layoutBlockChildren();
        calculateBlockHeight();
    }

    private void calculateBlockWidth(Dimensions containingBlock) {
        StyledNode sn = boxType.getStyledNode();

        Keyword auto = new Keyword();
        auto.setK("auto");
        Value widthValue = sn.value("width");
        if(widthValue == null) {
            widthValue = auto;
        }

        Length zero = new Length();
        zero.setUnit(Px);
        zero.setLength(0.0f);

        //Margin left
        Value marginLeft = sn.value("margin-left");
        if(marginLeft == null) {
            marginLeft = sn.value("margin");
            if(marginLeft == null) {
                marginLeft = zero;
            }
        }
        //Margin right
        Value marginRight = sn.value("margin-right");
        if(marginRight == null) {
            marginRight = sn.value("margin");
            if(marginRight == null) {
                marginRight = zero;
            }
        }

        //border left width
        Value borderLeft = sn.value("border-left-width");
        if(borderLeft == null) {
            borderLeft = sn.value("border-width");
            if(borderLeft == null) {
                borderLeft = zero;
            }
        }

        //Border right width
        Value borderRight = sn.value("border-right-width");
        if(borderRight == null) {
            borderRight = sn.value("border-width");
            if(borderRight == null) {
                borderRight = zero;
            }
        }

        //Pading left
        Value paddingLeft = sn.value("padding-left");
        if(paddingLeft == null) {
            paddingLeft = sn.value("padding");
            if(paddingLeft == null) {
                paddingLeft = zero;
            }
        }


        //padding right
        Value paddingRight = sn.value("padding-right");
        if(paddingRight == null) {
            paddingRight = sn.value("padding");
            if(paddingRight == null) {
                paddingRight = zero;
            }
        }


//        float total = List.of(paddingRight, paddingLeft, marginLeft, marginRight, borderLeft, borderRight).stream().map(e -> );
        Length l = (Length) paddingRight;
        





    }

    private void calculateBlockPosition(Dimensions containingBlock) {

    }

    private void layoutBlockChildren() {

    }

    private void calculateBlockHeight() {

    }



    public LayoutBox getInlineContainer() {
        switch (boxType.getBoxTypeName()) {
            case InlineNode:
            case AnonymusBlock:
                return this;
            case BlockNode:
                if(children.isEmpty()) {
                    BoxType bt = new BoxType();
                    bt.setBoxTypeName(BoxTypeName.AnonymusBlock);
                    LayoutBox newBox = new LayoutBox();
                    newBox.setBoxType(bt);
                    children.add(newBox);
                }
                else {
                    LayoutBox lastChild = children.get(children.size() - 1);
                    if(lastChild.getBoxType().getBoxTypeName() != BoxTypeName.AnonymusBlock) {
                        BoxType bt = new BoxType();
                        bt.setBoxTypeName(BoxTypeName.AnonymusBlock);
                        LayoutBox newBox = new LayoutBox();
                        newBox.setBoxType(bt);
                        children.add(newBox);
                    }
                }
                return children.get(children.size() - 1);
            default:
                return null;
        }
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public List<LayoutBox> getChildren() {
        return children;
    }

    public void setChildren(List<LayoutBox> children) {
        this.children = children;
    }
}

enum Display{
    Inline,
    Block,
    None
}

class BoxType{
    private BoxTypeName boxTypeName;
    private StyledNode styledNode;

    public BoxTypeName getBoxTypeName() {
        return boxTypeName;
    }

    public void setBoxTypeName(BoxTypeName boxTypeName) {
        this.boxTypeName = boxTypeName;
    }

    public StyledNode getStyledNode() {
        return styledNode;
    }

    public void setStyledNode(StyledNode styledNode) {
        this.styledNode = styledNode;
    }
}

enum BoxTypeName{
    BlockNode,
    InlineNode,
    AnonymusBlock,
}

class Dimensions{
    private Rectangle content = new Rectangle();
    private EdgeSizes padding = new EdgeSizes();
    private EdgeSizes border = new EdgeSizes();
    private EdgeSizes margin = new EdgeSizes();

    public Rectangle getContent() {
        return content;
    }

    public void setContent(Rectangle content) {
        this.content = content;
    }

    public EdgeSizes getPadding() {
        return padding;
    }

    public void setPadding(EdgeSizes padding) {
        this.padding = padding;
    }

    public EdgeSizes getBorder() {
        return border;
    }

    public void setBorder(EdgeSizes border) {
        this.border = border;
    }

    public EdgeSizes getMargin() {
        return margin;
    }

    public void setMargin(EdgeSizes margin) {
        this.margin = margin;
    }
}

class EdgeSizes {
    private float left = 0.0f;
    private float right= 0.0f;
    private float top= 0.0f;
    private float bottom= 0.0f;

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}
