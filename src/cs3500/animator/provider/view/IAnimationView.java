package cs3500.animator.provider.view;

import java.io.IOException;

/**
 * Renders an Animation model in some manner. This class can be implemented by various
 * different types of views in order to render an animation in some manner whether
 * it's text, graphics or others.
 */
public interface IAnimationView {

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  public void render() throws IOException;



}
