package cs3500.animator.provider.model;

import java.util.Map;

public class ViewModel implements IReadAnimationModel {

  private final AnimationModel delegate;

  public ViewModel(AnimationModel delegate) {
    this.delegate = delegate;
  }

  @Override
  public Map<String, IShapeAndMotion> getDrawings() {
    return delegate.getDrawings();
  }


  @Override
  public boolean isAnimationOver() throws IllegalStateException {
    return delegate.isAnimationOver();
  }

  @Override
  public Map<String, IShapeAndMotion> getInstructions() {
    return delegate.getInstructions();
  }

  @Override
  public IShapeAndMotion getImageAt(String name) {
    return delegate.getImageAt(name);
  }



  @Override
  public IShape2D getFrame(String name, int time) throws IllegalStateException {
    return delegate.getFrame(name,time);
  }

  @Override
  public boolean animationStarted() {
    return delegate.animationStarted();
  }


  @Override
  public int getXCanvas() {
    return delegate.getXCanvas();
  }

  @Override
  public int getYCanvas() {
    return delegate.getYCanvas();
  }

  @Override
  public int getWidthCanvas() {
    return delegate.getWidthCanvas();
  }

  @Override
  public int getHeightCanvas() {
    return delegate.getHeightCanvas();
  }

  @Override
  public int getEndTime() {
    return delegate.getEndTime();
  }

  @Override
  public void updateShape(String name, Position2D pos, Size2D size, Rgb color)
      throws IllegalArgumentException, IllegalStateException {
    delegate.updateShape(name,pos,size,color);
  }

}
