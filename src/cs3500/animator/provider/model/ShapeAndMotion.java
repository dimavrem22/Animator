package cs3500.animator.provider.model;

import cs3500.animator.model.Color;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import java.util.ArrayList;
import java.util.List;

public class ShapeAndMotion implements IShapeAndMotion {

  private final IAnimationModel delegate;
  private final String shapeName;
  private IShape currShape;

  public ShapeAndMotion(IAnimationModel delegate,String shapeName) {
    this.delegate = delegate;
    this.shapeName = shapeName;
    this.currShape = delegate.getShape(shapeName);
  }

  @Override
  public IMotion getMotionAtTime(int time) throws IllegalArgumentException {
    List<cs3500.animator.model.IMotion> motions = delegate.getMotions(shapeName);

    for(int i = 0; i < motions.size(); i++) {
      if (time >= motions.get(i).getStartTick() &&
          time <= motions.get(i).getLastTick()) {
        return new MotionImpl(motions.get(i));
      }
    }

    throw new IllegalArgumentException(
        "Did not find proper motion");

  }

  @Override
  public int getStartTime() {
    return delegate.getMotions(this.shapeName).get(0).getStartTick();
  }

  @Override
  public int getEndTime() {
    int size = delegate.getMotions(this.shapeName).size();
    return delegate.getMotions(this.shapeName).get(size-1).getLastTick();
  }

  @Override
  public IShape2D getShape() {

    IShape temp = delegate.getShape(this.shapeName);
    return new Shape2DImpl(temp.clone());
  }

  @Override
  public List<IMotion> getMotions() {

    List<cs3500.animator.model.IMotion> ours = delegate.getMotions(this.shapeName);
    List<IMotion> theirs = new ArrayList<IMotion>();

    for (cs3500.animator.model.IMotion motion : ours) {

      theirs.add(new MotionImpl(motion));

    }

    return theirs;
  }

  @Override
  public void addMotion(IMotion motion) throws IllegalArgumentException {

    delegate.addMotion(shapeName, new Position((int) motion.getEndPos().getX(),
        (int) motion.getEndPos().getY()),
        motion.getStartTime(),motion.getEndTime(),
     motion.getEndSize().getHeight(), motion.getEndSize().getWidth(),
        new Color(motion.getEndColor().getR(),
            motion.getEndColor().getG(),
            motion.getEndColor().getB()));

  }

  @Override
  public void sortMotionsOnStartTime() {
    return;
  }

  @Override
  public boolean checkMotionCoherence() {
    return true;
  }

  @Override
  public void updateDoNothingMotion() {

    return;

  }

  @Override
  public void linkMotions() {

    return;

  }

  @Override
  public IShapeAndMotion copyShapeAndMotion() {

    return new ShapeAndMotion(this.delegate,this.shapeName);
  }

  @Override
  public void applyMotion(int newTick) {

    this.currShape = delegate.shapeAtTick(this.shapeName,newTick);
  }

  @Override
  public void updateShape(Position2D pos, Size2D size, Rgb color) {
    this.currShape = this.currShape.create(size.getWidth(), size.getHeight(),
        new Color(color.getR(),color.getG(),color.getB()),
        new Position((int) pos.getX(),(int) pos.getY()), 1.0);

  }


}
