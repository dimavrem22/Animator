package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.provider.view.IAnimationViewToIViewAdapter;
import cs3500.animator.provider.view.InteractiveVisualAnimationView;

/**
 * Class that creates the proper View for the animation.
 */
public class ViewCreator {

  /**
   * Represents the view types of the animation.
   * <ul>
   *   <li>TEXTUAL- represents the textual version</li>
   *   <li>SVG - represents the svg version</li>
   *   <li>VISUAL - represents the non-original gui version</li>
   *   <li>INTERACTIVE - represents the interactive version</li>
   *   <li>PROVIDER - represents the provider interactive (hybrid) version</li>
   * </ul>
   */
  public enum ViewType {
    TEXTUAL, SVG, VISUAL, INTERACTIVE, PROVIDER;
  }

  /**
   * Creates the proper {@link IView}.
   *
   * @param type  the ViewType to create
   * @param model the model to pass to the view
   * @param tempo the tempo of the view
   * @param out the Appendable which holds rendering data for {@link SVGView} and
   *            {@link TextualView}
   * @return a view based on the given parameters
   */
  public static IView create(ViewType type, IReadOnlyModel model, int tempo,
      Appendable out) {
    switch (type) {
      case TEXTUAL:
        return new TextualView(model,out, tempo);
      case SVG:
        return new SVGView(model,out, tempo);
      case VISUAL:
        return new VisualViewOld(model, tempo);
      case INTERACTIVE:
        return new VisualView(model, tempo);
      /*case PROVIDER:
        return new IAnimationViewToIViewAdapter(new InteractiveVisualAnimationView(
            new IReadOnlyModelToIReadAnimationModelAdapter(model),tempo));*/


      default:
        return new TextualView(model,out, tempo);
    }
  }


}
