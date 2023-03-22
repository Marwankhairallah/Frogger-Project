package graphicalElements;

import java.awt.*;

import environment.Car;
import gameCommons.Case;


public class Element extends Case {
    public final Color color;
    public final int template;
    public final boolean templateIS;


    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
        this.template = -1;
        this.templateIS = false;
    }
    
    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
        this.template = -1;
        this.templateIS = false;
    }

    public Element(int absc, int ord, int template) {
        super(absc, ord);
        this.template = template;
        this.color = new Color(0,0,0,0);
        this.templateIS = true;
    }

    public Element(Case c, int template) {
        super(c.absc, c.ord);
        this.template = template;
        this.color = new Color(0,0,0,0);
        this.templateIS = true;
    }

}
