package Utilitaires;

public class PointUtils implements java.io.Serializable {
    // Utility method to check if x is greater than or equal to another x
    public static boolean greaterOrEqualsX(java.awt.Point point, int otherX) {
        return point.x >= otherX;
    }

    // Utility method to check if x is less than or equal to another x
    public static boolean lessOrEqualsX(java.awt.Point point, int otherX) {
        return point.x <= otherX;
    }

    // Utility method to check if y is greater than or equal to another y
    public static boolean greaterOrEqualsY(java.awt.Point point, int otherY) {
        return point.y >= otherY;
    }

    // Utility method to check if y is less than or equal to another y
    public static boolean lessOrEqualsY(java.awt.Point point, int otherY) {
        return point.y <= otherY;
    }
}
