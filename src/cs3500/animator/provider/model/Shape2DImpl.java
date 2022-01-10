package cs3500.animator.provider.model;

import cs3500.animator.model.IShape;
import java.util.Objects;

public class Shape2DImpl implements IShape2D {

  private IShape delegate;

  public Shape2DImpl(IShape delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  @Override
  public Rgb getRgb() {
    return new Rgb(delegate.getColor().getRed(),
        delegate.getColor().getGreen(),
        delegate.getColor().getBlue());
  }

  @Override
  public Size2D getSize() {
    return new Size2D(
        delegate.getWidth(),delegate.getHeight()
    );
  }

  @Override
  public Position2D getPos() {
    return new Position2D(delegate.getCurrentPosition().getX(),
        delegate.getCurrentPosition().getY());
  }

  @Override
  public void move(Position2D newPos) {

    return;

  }

  @Override
  public void resize(Size2D newSize) {

    return;

  }

  @Override
  public void colorize(Rgb newColor) {
    return;

  }

  @Override
  public IShape2D copyIShape2D() {
    return null;
  }

  @Override
  public String shapeName() {
    return delegate.toString();
  }
}
