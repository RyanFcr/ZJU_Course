package chapter36;

public class TestTreeModel {
  public static void main(String[] args) {
    javax.swing.JTree jTree1 = new javax.swing.JTree();
    javax.swing.tree.TreeModel model = jTree1.getModel();
    traversal(model, model.getRoot());
  }

  private static void traversal
      (javax.swing.tree.TreeModel model, Object root) {   
    System.out.print(root + " ");
    if (model.isLeaf(root)) return;
    for (int i = 0; i < model.getChildCount(root); i++) {
      traversal(model, model.getChild(root, i));
    }
  }
}
