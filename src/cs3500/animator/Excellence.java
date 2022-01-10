package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import cs3500.animator.model.AnimationModel.Builder;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;


/**
 * Excellence class is used for running animations. It constructs a model and a view based on the
 * user's input arguments.
 */
public final class Excellence {

  /**
   * The main method is used to run the animations. The user passes in arguments which specify the
   * input file from which to construct the model, the output file to which to write the details
   * of the animation, the type of view which the user desires to see, and the speed at which the
   * animation should run. The parameters for the animation should be preceded by specifiers such as
   * -in -out -speed -view.
   * @param args -in -out -speed -view specifiers followed by animation parameters.
   *             The -in and -view are necessary for a program to run successfully.
   *             System.out is the default -out. 1 is the default -speed.
   * @throws IOException  if unable to render
   */
  public static void main(String[] args) throws IOException {

    Readable inFile = null;
    Writer output = null;
    int speed = 1;
    String viewType = null;

    for (int i = 0; i < args.length; i++) {

      if (args[i].equals("-in")) {
        if (i + 1 < args.length) {
          try {
            inFile = new FileReader("resources/" + args[i+1]);
            //System.out.println(args[i+1]);
          } catch (FileNotFoundException e) {
            errorPopUp("File not valid!");
            return;
          }
        }
      }

      if (args[i].equals("-out")) {
        if (i + 1 < args.length) {
          try {
            output = new FileWriter(args[i + 1]);
          } catch (IOException e) {
            errorPopUp("Can't write to filename!");
            return;
          }
        }
      }

      if (args[i].equals("-speed")) {
        if (i + 1 < args.length) {
          try {
            speed = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException e) {
            errorPopUp("Can't get speed.");
            return;
          }
        }
      }

      if (args[i].equals("-view")) {
        if (i + 1 < args.length) {
          viewType = args[i + 1];
        }
      }

    }

    if (inFile == null) {
      errorPopUp("In file not found");
      return;
    }

    IAnimationModel temp = AnimationReader.parseFile(inFile, new Builder(500,
        500,0,0));
    IReadOnlyModel model = new ReadOnlyModel(temp);
    //System.out.println(model.getAllShapeNames());
    IView view = null;
    ViewCreator vc = new ViewCreator();
    boolean isVisual = false;

    if (viewType == null) {
      errorPopUp("View type not specified");
      return;
    }

    Appendable out = new StringBuilder();
    switch (viewType) {
      case "text":
        view = vc.create(ViewType.TEXTUAL,model,speed,out);
        break;
      case "svg":
        view = vc.create(ViewType.SVG,model,speed,out);
        break;

      case "visual":
        view = vc.create(ViewType.VISUAL,model,speed,out);
        isVisual = true;
        break;

      case "interactive":
        view = vc.create(ViewType.INTERACTIVE,model,speed,out);
        isVisual = true;
        break;

      default:
        errorPopUp("View type not available");
        return;
    }

    IController controller = null;

      controller = new Controller(view);
      controller.execute();

    if (output != null) {

      output.append(out.toString());
      output.close();

    }
    else {
      view = vc.create(ViewType.TEXTUAL,model,speed,out);
      controller = new Controller(view);
      controller.execute();
      System.out.println(out);
    }
  }

  /**
   * Makes error popup.
   *
   * @param errorMessage the error message to be shown.
   */
  private static void errorPopUp(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, "Error Occurred", JOptionPane.ERROR_MESSAGE);
  }

}

