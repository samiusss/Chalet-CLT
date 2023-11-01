
public class MurDrawer {

    private final ChaletController controller;
    private Dimension initialDimension;


    public MurDrawer(BasketController controller, Dimension initialDimension)
    {
        this.controller = controller;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawMur(g);
        drawAccessoires(g);
    }

    private void drawAccessoires(Graphics g)
    {
        //...
    }

    private void drawMurs(Graphics g) {
        // donner option de choisir les initial dimensions
        //
        int width = (int) initialDimension.getWidth();
        int height = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(width/4, (int)(height/1.75), width/2, height/4);
    }
}
