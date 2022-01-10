package cs3500.animator.provider.model;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;

public class MotionImpl implements IMotion {

  private final cs3500.animator.model.IMotion delegate;

  public MotionImpl(cs3500.animator.model.IMotion delegate) {
    this.delegate = delegate;
  }

  @Override
  public boolean getSynchro() {
    return false;
  }

  @Override
  public boolean getDoNothing() {
    return false;
  }

  @Override
  public IMotion copyMotion() {
    return null;
  }

  @Override
  public String getShapeName() {
    return delegate.getShapeName();
  }

  @Override
  public int getEndTime() {
    return delegate.getLastTick();
  }

  @Override
  public int getStartTime() {
    return delegate.getStartTick();
  }

  @Override
  public Position2D getStartPos() {
    IPosition pos = delegate.getShape().getCurrentPosition();
    return new Position2D(pos.getX(),pos.getY());
  }

  @Override
  public Position2D getEndPos() {
    IPosition pos = delegate.getFinalPosition();
    return new Position2D(pos.getX(),pos.getY());
  }

  @Override
  public Size2D getStartSize() {
    return new Size2D(delegate.getShape().getWidth(),delegate.getShape().getHeight());
  }

  @Override
  public Size2D getEndSize() {
    return new Size2D(delegate.getFinalWidth(),delegate.getFinalHeight());
  }

  @Override
  public Rgb getStartColor() {
    return new Rgb(delegate.getShape().getColor().getRed(),
        delegate.getShape().getColor().getGreen(),
        delegate.getShape().getColor().getBlue());
  }

  @Override
  public Rgb getEndColor() {
    return new Rgb(delegate.getFinalColor().getRed(),
        delegate.getFinalColor().getGreen(),
        delegate.getFinalColor().getBlue());
  }

  @Override
  public String toTextualString() {
    return "";
  }
}
