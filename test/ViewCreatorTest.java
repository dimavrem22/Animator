import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.provider.view.IAnimationViewToIViewAdapter;
import cs3500.animator.provider.view.InteractiveVisualAnimationView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import cs3500.animator.view.VisualView;
import cs3500.animator.view.VisualViewOld;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test class for ViewCreator: The class provides unit tests for the methods implemented in the
 * {@link ViewCreator} class.
 */
public class ViewCreatorTest {


  // testing create method
  @Test
  public void createTestVisual() {
    IReadOnlyModel m = new ReadOnlyModel(new AnimationModel());
    ViewCreator vc = new ViewCreator();
    Appendable out = new StringBuilder();
    IView result = vc.create(ViewCreator.ViewType.VISUAL, m, 10,out);
    assertTrue(result instanceof VisualViewOld);
  }


  @Test
  public void createTestSVG() {
    IReadOnlyModel m = new ReadOnlyModel(new AnimationModel());
    ViewCreator vc = new ViewCreator();
    Appendable out = new StringBuilder();
    IView result = vc.create(ViewCreator.ViewType.SVG, m, 10,out);
    assertTrue(result instanceof SVGView);
  }

  @Test
  public void createTestTXT() {
    IReadOnlyModel m = new ReadOnlyModel(new AnimationModel());
    ViewCreator vc = new ViewCreator();
    Appendable out = new StringBuilder();
    IView result = vc.create(ViewCreator.ViewType.TEXTUAL, m, 10,out);
    assertTrue(result instanceof TextualView);
  }

  @Test
  public void createTestInteractive() {
    IReadOnlyModel m = new ReadOnlyModel(new AnimationModel());
    ViewCreator vc = new ViewCreator();
    Appendable out = new StringBuilder();
    IView result = vc.create(ViewCreator.ViewType.INTERACTIVE, m, 10,out);
    assertTrue(result instanceof VisualView);
  }

  @Test
  public void createTestHybrid() {
    IReadOnlyModel m = new ReadOnlyModel(new AnimationModel());
    ViewCreator vc = new ViewCreator();
    Appendable out = new StringBuilder();
    IView result = vc.create(ViewType.PROVIDER, m, 10,out);
    assertTrue(result instanceof IAnimationViewToIViewAdapter);
  }
}
