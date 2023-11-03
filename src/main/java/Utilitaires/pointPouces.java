package Utilitaires;
import java.io.Serializable;

public class pointPouces implements Serializable {
  public Pouces x;
  public Pouces y;

  public pointPouces(Pouces x, Pouces y)
  {
      this.x = x;
      this.y = y;
  }
}
