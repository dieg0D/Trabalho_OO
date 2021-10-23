import java.util.ArrayList;

public class Categoria extends CategoriaBase {
  private ArrayList<SubCategoria> subcategorias;

  public void setSubcategorias(ArrayList<SubCategoria> subcategorias) {
    this.subcategorias = subcategorias;
  }

  public ArrayList<SubCategoria> getSubcategorias() {
    return subcategorias;
  }
}
